package unapec.facturacion.annotation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = RNCCedulaValidator.class)
@Target( {ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface RNCCedulaConstraint {
    String message() default "RNC o Cédula inválido";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
