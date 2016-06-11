package org.fountainmc.api.plugin;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface Plugin {

    String id();

    String name() default "";

    String version() default "";

    String description() default "";

    String url() default "";

    String[] author() default {};
}
