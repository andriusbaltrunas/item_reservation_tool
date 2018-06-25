package com.item.reservation.tool.utils.validators;

import com.item.reservation.tool.utils.annotation.ComparePasswords;
import lombok.extern.log4j.Log4j;
import org.apache.commons.lang3.StringUtils;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.lang.reflect.Field;

@Log4j
public class ComparePasswordsValidator implements ConstraintValidator<ComparePasswords, Object> {

    private ComparePasswords comparePasswords;

    @Override
    public void initialize(ComparePasswords constraintAnnotation) {
        comparePasswords = constraintAnnotation;
    }

    @Override
    public boolean isValid(Object o, ConstraintValidatorContext constraintValidatorContext) {
        String firstVal = getValue(o, comparePasswords.password());
        String secondVal = getValue(o, comparePasswords.repeatPassword());
        return firstVal.equals(secondVal);
    }

    private String getValue(Object o, String fieldName) {
        String value = StringUtils.EMPTY;
        if (o != null && StringUtils.isNotBlank(fieldName)) {
            try {
                Field field = o.getClass().getDeclaredField(fieldName);
                field.setAccessible(true);
                value = (String) field.get(o);
            } catch (Exception e) {
                log.error("Can not find filed ", e);
            }
        }
        return value;
    }
}