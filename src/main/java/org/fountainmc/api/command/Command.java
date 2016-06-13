package org.fountainmc.api.command;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Command {

    public String name();

    public String permission() default "";

    public String noPermissionMessage() default "You do not have permission to run this command";

    public String[] aliases() default {};

    public String description() default "";

    public String usage() default "";

    public Class<? extends CommandSender> allow() default CommandSender.class;
}
