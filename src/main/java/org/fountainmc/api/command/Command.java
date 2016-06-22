package org.fountainmc.api.command;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Command {

    String name();

    String permission() default "";

    String noPermissionMessage() default "You do not have permission to run this command";

    String[] aliases() default {};

    String description() default "";

    String usage() default "";

    Class<? extends CommandSender> allow() default CommandSender.class;

}
