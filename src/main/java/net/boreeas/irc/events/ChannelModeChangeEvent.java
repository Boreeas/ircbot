/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.boreeas.irc.events;

import net.boreeas.irc.User;

import java.util.*;

/**
 *
 * @author Boreeas
 */
public class ChannelModeChangeEvent extends Event {

    public static final Set<Character> modesWithParams =
                                        new HashSet<Character>();

    static {
        // A list of modes that require a parameter. Any mode that is not in
        // this list is assumed to not have a parameter. Only non-conflicting
        // modes are chosen. If two implementations or the RFC conflict,
        // non is added.
        modesWithParams.add('b');   // RFC1459
        modesWithParams.add('e');   // RFC2811
        modesWithParams.add('f');   // Unreal
        modesWithParams.add('h');   // RFC2811
        modesWithParams.add('I');   // RFC2811
        modesWithParams.add('J');   // Dancer
        modesWithParams.add('k');   // RFC1459
        modesWithParams.add('l');   // RFC1459
        modesWithParams.add('o');   // RFC1459
        modesWithParams.add('v');   // RFC1459
        modesWithParams.add('!');   // KineIRCd
    }
    private User user;
    private String channel;
    private Map<Character, String> addedModes;
    private Map<Character, String> removedModes;

    public ChannelModeChangeEvent(User user, String channel,
                                  Map<Character, String> added,
                                  Map<Character, String> removed) {

        this.user = user;
        this.channel = channel;
        this.addedModes = added;
        this.removedModes = removed;
    }

    public ChannelModeChangeEvent(User user, String channel, String modes, String[] params) {

        this.user = user;
        this.channel = channel;
        this.addedModes = new HashMap<Character, String>();
        this.removedModes = new HashMap<Character, String>();

        boolean adding = true;
        int paramIndex = 0;

        for (int i = 0; i < modes.length(); i++) {

            char mode = modes.charAt(i);

            if (mode == '-') {

                adding = false;
            } else if (mode == '+') {

                adding = true;
            } else if (modesWithParams.contains(mode) && paramIndex < params.length) {

                (adding
                 ? addedModes
                 : removedModes).put(mode, params[paramIndex]);
                paramIndex++;
            } else {

                (adding
                 ? addedModes
                 : removedModes).put(mode, "");
            }
        }
    }

    public User user() {
        return user;
    }

    public String channel() {
        return channel;
    }

    public Map<Character, String> addedModes() {
        return Collections.unmodifiableMap(addedModes);
    }

    public Map<Character, String> removedModes() {
        return Collections.unmodifiableMap(removedModes);
    }
}
