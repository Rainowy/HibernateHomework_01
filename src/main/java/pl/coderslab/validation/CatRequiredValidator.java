package pl.coderslab.validation;

import pl.coderslab.entity.Category;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.lang.annotation.Annotation;

public class CatRequiredValidator implements ConstraintValidator<CatRequired, Category> {

    @Override
    public void initialize(CatRequired constraintAnnotation) {

    }

    @Override
    public boolean isValid(Category category, ConstraintValidatorContext constraintValidatorContext) {
        return category != null;
    }
}
