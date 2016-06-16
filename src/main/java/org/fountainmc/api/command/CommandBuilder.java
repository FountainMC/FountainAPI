package org.fountainmc.api.command;

import static com.google.common.base.Preconditions.checkNotNull;

public class CommandBuilder {
    private final CommandManager commandManager;

    private String name;
    private String permission = "";
    private String noPermissionMessage = "You do not have permission to run this command";
    private String[] aliases = new String[0];
    private String usage = "";
    private String description = "";
    private InvokeHandler invokeHandler = (args, sender) -> {};
    private Class<? extends CommandSender> allow = CommandSender.class;

    protected CommandBuilder(CommandManager commandManager, String name) {
        checkNotNull(name);
        checkNotNull(commandManager);
        this.name = name;
        this.commandManager = commandManager;
    }

    public CommandBuilder permission(String name) {
        this.permission = name;
        return this;
    }

    public CommandBuilder noPermissionMessage(String message) {
        this.noPermissionMessage = message;
        return this;
    }

    public CommandBuilder aliases(String... aliases) {
        this.aliases = aliases;
        return this;
    }

    public CommandBuilder usage(String usageMsg) {
        this.usage = usageMsg;
        return this;
    }

    public CommandBuilder description(String desc) {
        this.description = desc;
        return this;
    }

    public CommandBuilder allowOnly(Class<? extends CommandSender> senderC) {
        this.allow = senderC;
        return this;
    }

    public interface InvokeHandler {
        void invoke(String[] args, CommandSender sender);
    }

    public CommandBuilder invokeHandler(InvokeHandler handler) {
        this.invokeHandler = handler;
        return this;
    }

    public void register() {
        ICommand<CommandSender> iCommand = new ICommand<CommandSender>() {
            @Override public String name() {
                return name;
            }

            @Override public String permission() {
                return permission;
            }

            @Override public String[] aliases() {
                return aliases;
            }

            @Override public String description() {
                return description;
            }

            @Override public String usage() {
                return usage;
            }

            @Override public String noPermissionMessage() {
                return noPermissionMessage;
            }

            @Override public Class<CommandSender> allow() {
                return CommandSender.class;
            }

            @Override public void onExecute(CommandSender sender, String[] args) {
                if (allow.isAssignableFrom(sender.getClass())) {
                    invokeHandler.invoke(args, allow.cast(sender));
                }
            }
        };
        commandManager.registerCommand(iCommand);
    }
}
