package org.fountainmc.api.command;

public class CommandInfo<T extends Sender> {
    private final String[] array;
    private final Sender sender;

    public CommandInfo(Sender sender, String[] array) {
        this.array = array;
        this.sender = sender;
    }

    public String[] getArguments() {
        return array;
    }

    public T getSender() {
        return (T) sender;
    }

    public String mergeArguments(int startingIndex, // starts at 0
                      int endIndex) {
        int currentIndex = startingIndex;
        StringBuilder builder = new StringBuilder();
        while (currentIndex != endIndex) {
            builder.append(array[currentIndex] + " ");
            currentIndex++;
        }
        return builder.toString().trim();
    }
}
