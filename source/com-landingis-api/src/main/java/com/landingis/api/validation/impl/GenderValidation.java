package com.landingis.api.validation.impl;

import com.landingis.api.constant.LandingISConstant;
import com.landingis.api.validation.Gender;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Objects;

public class GenderValidation implements ConstraintValidator<Gender, Integer> {
    private boolean allowNull;

    @Override
    public void initialize(Gender constraintAnnotation) {
        allowNull = constraintAnnotation.allowNull();
    }

    @Override
    public boolean isValid(Integer gender, ConstraintValidatorContext constraintValidatorContext) {
        if(gender == null && allowNull){
            return true;
        }
        if(!Objects.equals(gender, LandingISConstant.GENDER_FEMALE)
                && !Objects.equals(gender, LandingISConstant.GENDER_MALE)
                && !Objects.equals(gender, LandingISConstant.GENDER_OTHER)){
            return false;
        }
        return true;
    }
}
