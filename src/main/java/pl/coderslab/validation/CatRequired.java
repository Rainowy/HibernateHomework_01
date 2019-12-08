package pl.coderslab.validation;
import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.constraints.NotNull;
import java.lang.annotation.*;

@Constraint(validatedBy = CatRequiredValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)

public @interface CatRequired {
    String message() default "{catRequired.error.message}";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

}
