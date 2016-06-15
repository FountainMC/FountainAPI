package org.fountainmc.api.chat;

import com.google.common.collect.ImmutableSet;
import org.fountainmc.api.chat.events.ClickEvent;
import org.fountainmc.api.chat.values.Text;

import java.util.*;
import java.util.regex.Pattern;

/**
 * This class is used to parse the text in a legacy chat message using a state machine.
 */
class LegacyChatConverter {
    private final String text;
    private final List<LegacyChatPart> parts = new ArrayList<>();
    private final Set<ChatColor> formattingFound = EnumSet.noneOf(ChatColor.class);
    private boolean parsed = false;
    private static final Pattern url = Pattern.compile( "^(?:(https?)://)?([-\\w_\\.]{2,}\\.[a-z]{2,4})(/\\S*)?$" );

    LegacyChatConverter(String text) {
        this.text = text;
    }

    public void parse() {
        // - Formatting codes that are grouped together are part of the same component.
        //   (i.e. &c&lTEST)
        StringBuilder foundText = new StringBuilder();
        StringBuilder foundUrl = new StringBuilder();
        State state = State.TEXT;
        formattingFound.add(ChatColor.WHITE);
        textParse: for (int i = 0; i < text.length(); i++) {
            char at = text.charAt(i);
            switch (state) {
                case LOOKING_FOR_CODE:
                    // Is this a code?
                    Optional<ChatColor> colorOptional = ChatColor.getForChar(at);
                    if (colorOptional.isPresent()) {
                        // Yes, it is.
                        ChatColor color = colorOptional.get();

                        if (foundText.length() > 0 || color == ChatColor.RESET) {
                            // New text was found (or we're looking at a RESET), so this is a component.
                            parts.add(new LegacyChatPart(ImmutableSet.copyOf(formattingFound), null, foundText.toString()));
                            foundText.delete(0, foundText.length());
                        }

                        if (color == ChatColor.RESET) {
                            formattingFound.clear();
                            formattingFound.add(ChatColor.WHITE);
                        } else {
                            if (!color.isFormatting()) {
                                formattingFound.removeIf(c -> !c.isFormatting());
                            }
                            formattingFound.add(colorOptional.get());
                        }
                    } else {
                        // No, it's text. Proceed to add '&' and proceed as if we were looking at text.
                        foundText.append('&');
                        foundText.append(at);
                        state = State.TEXT;
                        continue textParse;
                    }
                    break;
                case TEXT:
                    if (at == '&') {
                        state = State.LOOKING_FOR_CODE;
                    } else if (at == ' ') {
                    } else {
                        foundText.append(at);
                    }
                    break;
            }
            if (at == '&') {
                state = State.LOOKING_FOR_CODE;
            }
        }

        // Finalize what we have
        if (foundText.length() > 0) {
            parts.add(new LegacyChatPart(ImmutableSet.copyOf(formattingFound), null, foundText.toString()));
        }

        parsed = true;
    }

    public Component<?>[] toComponents() {
        List<Component<Text>> components = new ArrayList<>();
        for (LegacyChatPart part : parts) {
            Component<Text> current = Components.forPlainText(part.text);
            for (ChatColor color : part.formattingFound) {
                if (!color.isFormatting()) {
                    current = current.withColor(color);
                } else {
                    switch (color) {
                        case BOLD:
                            current = current.withBold(true);
                            break;
                        case UNDERLINE:
                            current = current.withUnderlined(true);
                            break;
                        case ITALIC:
                            current = current.withItalic(true);
                            break;
                        case OBFUSCATED:
                            current = current.withObfuscated(true);
                            break;
                        case STRIKETHROUGH:
                            current = current.withStrikethrough(true);
                            break;
                    }
                }
            }
            if (part.url != null) {
                current = current.withClickEvent(ClickEvent.openUrl(part.url));
            }
            components.add(current);
        }
        if (components.isEmpty()) {
            components.add(Components.EMPTY_TEXT);
        }
        return components.toArray(new Component[components.size()]);
    }

    private class LegacyChatPart {
        private final Set<ChatColor> formattingFound;
        private final String url;
        private final String text;

        private LegacyChatPart(Set<ChatColor> formattingFound, String url, String text) {
            this.formattingFound = formattingFound;
            this.url = url;
            this.text = text;
        }
    }

    private enum State {
        TEXT,
        LOOKING_FOR_CODE,
        LOOKING_FOR_URL
    }
}
