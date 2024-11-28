package com.example.demo.annotation;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.METHOD})
@Repeatable(ApiResponses.class)
public @interface ApiResponse {
    String description();
    Class<?> response() default Void.class;
    int responseCode() default 200;
}