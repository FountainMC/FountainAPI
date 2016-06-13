package org.fountainmc.api.command;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class CommandManager {
    private final List<CommandContainer> commands = new ArrayList<>();

    public void registerCommands(Class<?> commandClass) {
        for (Method method : commandClass.getDeclaredMethods()) {
            if (method.isAnnotationPresent(Command.class)) {
                Command command = method.getAnnotation(Command.class);
                CommandContainer commandContainer = new CommandContainer(command, method);
                commands.add(commandContainer);
            }
        }
    }

    public CommandContainer[] getCommands() {
        return commands.toArray(new CommandContainer[commands.size()]);
    }
}
