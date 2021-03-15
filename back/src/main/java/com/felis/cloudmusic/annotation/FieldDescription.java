package com.felis.cloudmusic.annotation;


import java.lang.annotation.*;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.ANNOTATION_TYPE})
public @interface FieldDescription {

    String info() default "";

    String column() default "";

    String alias() default "";

}
