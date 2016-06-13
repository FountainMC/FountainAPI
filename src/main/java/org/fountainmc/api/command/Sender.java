package org.fountainmc.api.command;

public interface Sender {
    String getName();

    void sendMessage(String... messages);
}
