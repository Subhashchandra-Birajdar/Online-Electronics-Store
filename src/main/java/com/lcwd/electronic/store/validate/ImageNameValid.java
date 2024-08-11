package com.lcwd.electronic.store.validate;

import jakarta.persistence.Table;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Target({ElementType.FIELD,ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = ImageNameValidator.class)
// ImageNameValidator.class - in this class we will write custom validation logic .
public @interface ImageNameValid {

    // default message
    String message() default "Invalid ImageName, provide proper ImageName";

    //error message
    Class<?>[] groups() default { };

    // additional information about annotation
    Class<? extends Payload>[] payload() default { };
}
