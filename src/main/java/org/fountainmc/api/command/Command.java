package org.fountainmc.api.command;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Command {
    String name();

    String usage() default "";

    String[] aliases() default {};

    SenderType allow() default SenderType.NONE;
}
