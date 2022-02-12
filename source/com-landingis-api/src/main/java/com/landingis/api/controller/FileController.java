package com.landingis.api.controller;


import com.landingis.api.dto.ApiMessageDto;
import com.landingis.api.dto.UploadFileDto;
import com.landingis.api.service.LandingIsApiService;
import com.landingis.api.form.UploadFileForm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.core.io.Resource;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/v1/file")
@CrossOrigin(origins = "*", allowedHeaders = "*")
@Slf4j
public class FileController {
    @Autowired
    LandingIsApiService landingIsApiService;
    @PostMapping(value = "/upload" ,consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces= MediaType.APPLICATION_JSON_VALUE)
    public ApiMessageDto<UploadFileDto> upload(@Valid UploadFileForm uploadFileForm, BindingResult bindingResult) {

        ApiMessageDto<UploadFileDto> apiMessageDto = landingIsApiService.storeFile(uploadFileForm);
        apiMessageDto.setResult(true);
        return apiMessageDto;

    }

    @GetMapping("/download/{folder}/{fileName:.+}")
    @Cacheable("images")
    public ResponseEntity<Resource> downloadFile(@PathVariable String folder,@PathVariable String fileName, HttpServletRequest request) throws FileNotFoundException {
        Resource  resource= landingIsApiService.loadFileAsResource(folder , fileName);
        String contentType = null;
        try {
            contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
        } catch (IOException ex) {
            log.info("Could not determine file type.");
        }
        if(contentType == null) {
            contentType = "application/octet-stream";
        }

        return ResponseEntity.ok()
                .cacheControl(CacheControl.maxAge(7776000, TimeUnit.SECONDS))
                .contentType(MediaType.parseMediaType(contentType))
                //.header(HttpHeaders.EXPIRES, expires)
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    }


}
