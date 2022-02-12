/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.landingis.api.intercepter;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.landingis.api.dto.ApiMessageDto;
import com.landingis.api.jwt.JWTUtils;
import com.landingis.api.jwt.UserJwt;
import com.landingis.api.utils.ConfigurationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;


@Slf4j
@Component
public class MyIntercepter implements HandlerInterceptor {
    ObjectMapper mapper = new ObjectMapper();

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        if ( !httpServletRequest.getDispatcherType().name().equals("REQUEST") ) {
            return true;
        }
        long startTime = System.currentTimeMillis();
        httpServletRequest.setAttribute("startTime", startTime);

        String requestUri = httpServletRequest.getRequestURI();
        String[] uriByPassAuth = ConfigurationService.getInstance().getStringArray("uri.bypassAuths");
        boolean contains = Arrays.stream(uriByPassAuth).anyMatch(requestUri::contains);
        if(contains){
            return true;
        }
        boolean validSign = checkHeader(httpServletRequest);
        if (!validSign) {
            ApiMessageDto<Long> apiMessageDto = new ApiMessageDto<>();
            apiMessageDto.setResult(false);
            apiMessageDto.setMessage("Invalid token");
            httpServletResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED, mapper.writeValueAsString(apiMessageDto));
        }

        return validSign;
    }

    private Boolean checkHeader(HttpServletRequest request){
        String header = request.getHeader("Authorization");
        if (header == null || !header.startsWith("Bearer ")) {
            return false;
        }

        String authToken = header.substring(7);
        DecodedJWT decodedJWT = JWTUtils.verifierJWT(JWTUtils.ALGORITHMS_HMAC, authToken);
        if(decodedJWT == null){
            return false;
        }

        //lay ra quyen
        UserJwt qrJwt = JWTUtils.getSessionFromToken(decodedJWT);
        SecurityContext securityContext = SecurityContextHolder.getContext();
        securityContext.setAuthentication(new MyAuthentication(qrJwt));

        log.info("jwt user verify ne: {}", qrJwt);

        //check permission here
        String requestUri = request.getRequestURI();
        String[] uriByPassAuth = qrJwt.getPemission().split(",");

        return Arrays.stream(uriByPassAuth).anyMatch(requestUri::contains);
    }
    /**
     * get full url request
     *
     * @param req
     * @return
     */
    private static String getUrl(HttpServletRequest req) {
        String reqUrl = req.getRequestURL().toString();
        String queryString = req.getQueryString();   // d=789
        if (!StringUtils.isEmpty(queryString)) {
            reqUrl += "?" + queryString;
        }
        Logger logger = Logger.getLogger("newLoggerName");
        logger.log(Level.WARNING, reqUrl);
        return reqUrl;
    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {
        long startTime = (Long) httpServletRequest.getAttribute("startTime");
        long endTime = System.currentTimeMillis();
        long executeTime = endTime - startTime;
        log.debug("[" + getUrl(httpServletRequest) + "] executeTime : " + executeTime + "ms");

        if (e != null) {
            log.error("afterCompletion>> " + e.getMessage());

        }
    }
}
