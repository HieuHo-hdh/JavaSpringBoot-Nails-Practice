package com.landingis.api.controller;

import com.landingis.api.constant.LandingISConstant;
import com.landingis.api.dto.ApiMessageDto;
import com.landingis.api.dto.ErrorCode;
import com.landingis.api.dto.ResponseListObj;
import com.landingis.api.dto.settings.SettingsDto;
import com.landingis.api.exception.RequestException;
import com.landingis.api.form.settings.CreateSettingsForm;
import com.landingis.api.form.settings.UpdateSettingsForm;
import com.landingis.api.mapper.SettingsMapper;
import com.landingis.api.service.LandingIsApiService;
import com.landingis.api.storage.criteria.SettingsCriteria;
import com.landingis.api.storage.model.Settings;
import com.landingis.api.storage.repository.GroupRepository;
import com.landingis.api.storage.repository.SettingsRepository;
import com.landingis.api.utils.ConvertUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
//import org.apache.logging.log4j.Logger;
import javax.validation.Valid;
import java.util.Objects;

@RestController
@RequestMapping("/v1/settings")
@CrossOrigin(origins = "*", allowedHeaders = "*")
@Slf4j
public class SettingsController extends ABasicController {
//    private static final Logger LOGGER = LogManager.getLogger("SettingsController");

    PasswordEncoder passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();

    @Autowired
    SettingsRepository settingsRepository;

    @Autowired
    GroupRepository groupRepository;

    @Autowired
    SettingsMapper settingsMapper;

    @Autowired
    LandingIsApiService landingIsApiService;

    @GetMapping(value = "/list", produces = MediaType.APPLICATION_JSON_VALUE)
    public ApiMessageDto<ResponseListObj<SettingsDto>> getList(SettingsCriteria settingsCriteria, Pageable pageable) {
        if (!isAdmin()) {
            throw new RequestException(ErrorCode.SETTINGS_ERROR_UNAUTHORIZED, "Not allow get list");
        }
        ApiMessageDto<ResponseListObj<SettingsDto>> responseListObjApiMessageDto = new ApiMessageDto<>();

        Page<Settings> listSettings = settingsRepository.findAll(settingsCriteria.getSpecification(), pageable);

        ResponseListObj<SettingsDto> responseListObj = new ResponseListObj<>();
        responseListObj.setData(settingsMapper.fromEntityListToSettingsDtoList(listSettings.getContent()));
        responseListObj.setPage(pageable.getPageNumber());
        responseListObj.setTotalPage(listSettings.getTotalPages());
        responseListObj.setTotalElements(listSettings.getTotalElements());

        responseListObjApiMessageDto.setData(responseListObj);
        responseListObjApiMessageDto.setMessage("List settings success");
        return responseListObjApiMessageDto;
    }

    @GetMapping(value = "/get/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ApiMessageDto<SettingsDto> get(@PathVariable("id") Long id)
    {
        if (!isAdmin())
        {
            throw new RequestException(ErrorCode.SETTINGS_ERROR_UNAUTHORIZED, "Not allow get settings");
        }
        ApiMessageDto<SettingsDto> result = new ApiMessageDto<>();
        Settings settings = settingsRepository.findById(id).orElse(null);

        if (settings == null)
        {
            throw new RequestException(ErrorCode.SETTINGS_ERROR_NOT_FOUND, "Not found settings");
        }
        result.setData(settingsMapper.fromEntityToSettingsDto(settings));
        result.setMessage("Set Settings Success");
        return result;
    }

    @PostMapping(value = "/create", produces = MediaType.APPLICATION_JSON_VALUE)
    public ApiMessageDto<String> create(@Valid @RequestBody CreateSettingsForm createSettingsForm, BindingResult bindingResult) {

        if(!isAdmin()){
            throw new RequestException(ErrorCode.SETTINGS_ERROR_UNAUTHORIZED, "Not allow to create");
        }
        ApiMessageDto<String> apiMessageDto = new ApiMessageDto<>();

        Integer settingsGroupId = settingsMapper.fromCreateSettingsFormToEntity(createSettingsForm).getGroupId();
        if(!settingsGroupId.equals(LandingISConstant.SETTING_GROUP_ID_ADMIN) &&
            !settingsGroupId.equals(LandingISConstant.SETTING_GROUP_ID_CUSTOMER)
        )
        {
            throw new RequestException(ErrorCode.SETTINGS_ERROR_NOT_FOUND_GROUP, "Group is not valid");
        }

        Integer settingsKind = settingsMapper.fromCreateSettingsFormToEntity(createSettingsForm).getKind();
        if(!settingsKind.equals(LandingISConstant.SETTING_KIND_ON_OFF) &&
                !settingsKind.equals(LandingISConstant.SETTING_KIND_TEXT) &&
                !settingsKind.equals(LandingISConstant.SETTING_KIND_DATE) &&
                !settingsKind.equals(LandingISConstant.SETTING_KIND_TIME) &&
                !settingsKind.equals(LandingISConstant.SETTING_KIND_TIMESTAMP) &&
                !settingsKind.equals(LandingISConstant.SETTING_KIND_UPLOAD)
        )
        {
            throw new RequestException(ErrorCode.SETTINGS_ERROR_NOT_FOUND_KIND, "Kind is not valid");
        }

        Settings settings = settingsMapper.fromCreateSettingsFormToEntity(createSettingsForm);

        if(settings.getGroup().split("::").length <= 1)
        {
            throw new RequestException(ErrorCode.SETTINGS_ERROR_INVALID_GROUP, "Group is not valid");
        }
        else
        {
            String firstElement = settings.getGroup().split("::")[0];
            String secondElement = settings.getGroup().split("::")[1];
            if (StringUtils.isEmpty(firstElement))
            {
                throw new RequestException(ErrorCode.SETTINGS_ERROR_INVALID_GROUP, "Group is not valid");
            }

            if (StringUtils.isEmpty(secondElement))
            {
                throw new RequestException(ErrorCode.SETTINGS_ERROR_INVALID_GROUP, "Group is not valid");
            }
            else if (!Objects.equals(ConvertUtils.convertStringToLong(secondElement).toString(), secondElement)){
                throw new RequestException(ErrorCode.SETTINGS_ERROR_INVALID_GROUP, "Group is not valid");
            }
        }


        Settings settingsKey = settingsRepository.findFirstByKey(settingsMapper.fromCreateSettingsFormToEntity(createSettingsForm).getKey());
        if(settingsKey != null){
            throw new RequestException(ErrorCode.SETTINGS_ERROR_KEY_DUPLICATED, "Key duplicated");
        }
        settingsRepository.save(settings);
        apiMessageDto.setMessage("Create settings success");
        return apiMessageDto;
    }

    @PutMapping(value = "/update", produces = MediaType.APPLICATION_JSON_VALUE)
    public ApiMessageDto<String> update(@Valid @RequestBody UpdateSettingsForm updateSettingsAdminForm, BindingResult bindingResult) {
        if(!isAdmin()){
            throw new RequestException(ErrorCode.SETTINGS_ERROR_UNAUTHORIZED, "Not allowed to update.");
        }
        ApiMessageDto<String> apiMessageDto = new ApiMessageDto<>();
        Settings settings = settingsRepository.findById(updateSettingsAdminForm.getId()).orElse(null);

        if(settings == null)
        {
            throw new RequestException(ErrorCode.SETTINGS_ERROR_NOT_FOUND, "Not found settings");
        }

        settingsMapper.fromUpdateSettingsFormToEntity(updateSettingsAdminForm, settings);

        settingsRepository.save(settings);
        apiMessageDto.setMessage("Update settings success");
        return apiMessageDto;

    }

    @DeleteMapping(value = "/delete/{id}")
    public ApiMessageDto<SettingsDto> delete(@Valid @PathVariable("id") Long id)
    {
        if(!isAdmin())
        {
            throw new RequestException(ErrorCode.SETTINGS_ERROR_UNAUTHORIZED, "Not allow to delete");
        }
        ApiMessageDto<SettingsDto> result = new ApiMessageDto<>();
        Settings settings = settingsRepository.findById(id).orElse(null);

        if (settings == null)
        {
            throw new RequestException(ErrorCode.SETTINGS_ERROR_NOT_FOUND, "Not found settings");
        }

        settingsRepository.delete(settings);
        result.setMessage("Delete Settings Sucess");
        return result;
    }
}
