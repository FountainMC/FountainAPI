package org.fountainmc.api.command;

import java.lang.reflect.Method;

public class CommandContainer {
    private final Command annotation;
    private final Method method;

    public CommandContainer(Command info, Method enforcer) {
        this.annotation = info;
        this.method = enforcer;
    }

    public Method getMethod() {
        return method;
    }

    public Command getAnnotation() {
        return annotation;
    }
}
