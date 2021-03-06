package com.gw.aoplibrary.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by GongWen on 17/1/17.
 */

@Retention(RetentionPolicy.CLASS)
@Target({ElementType.CONSTRUCTOR,ElementType.METHOD})
public @interface DebugLog {
}
