package net.boreeas.irc.events;

import java.util.Set;

/**
 * @author Malte Schütze
 */
public class NamesReceivedEvent extends Event {
    public final String channel;
    public final Set<String> names;

    public NamesReceivedEvent(String channel, Set<String> names) {
        this.channel = channel;
        this.names = names;
    }
}
