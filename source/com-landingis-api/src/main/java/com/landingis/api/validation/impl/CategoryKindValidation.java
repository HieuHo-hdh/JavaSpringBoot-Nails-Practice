package com.landingis.api.validation.impl;

import com.landingis.api.constant.LandingISConstant;
import com.landingis.api.validation.CategoryKind;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Objects;

public class CategoryKindValidation implements ConstraintValidator<CategoryKind, Integer> {
    private boolean allowNull;

    @Override
    public void initialize(CategoryKind constraintAnnotation) { allowNull = constraintAnnotation.allowNull(); }

    @Override
    public boolean isValid(Integer categoryKind, ConstraintValidatorContext constraintValidatorContext) {
        if(categoryKind == null && allowNull) {
            return true;
        }
        if(!Objects.equals(categoryKind, LandingISConstant.CATEGORY_KIND_IMPORT)
            && !Objects.equals(categoryKind, LandingISConstant.CATEGORY_KIND_EXPORT)
                && !Objects.equals(categoryKind, LandingISConstant.CATEGORY_KIND_PRODUCT)
                && !Objects.equals(categoryKind, LandingISConstant.CATEGORY_KIND_COLLABORATOR_PRODUCT)
                && !Objects.equals(categoryKind, LandingISConstant.CATEGORY_KIND_NEWS_INTERNAL)
                && !Objects.equals(categoryKind, LandingISConstant.CATEGORY_KIND_NEWS_COLLABORATOR)
        ) {
            return false;
        }
        return true;
    }
}
