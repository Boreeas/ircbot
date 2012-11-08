/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.boreeas.irc.events;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Boreeas
 */
public class ChannelModeChangeEvent extends Event {

    private String name;
    private Map<Character, String> addedModes;
    private Map<Character, String> removedModes;

    public ChannelModeChangeEvent(String name, Map<Character, String> added, Map<Character, String> removed) {

        this.name = name;
        this.addedModes = added;
        this.removedModes = removed;
    }

    public ChannelModeChangeEvent(String name, String modes, String[] params) {

        this.name = name;
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
            } else {

                (adding ? addedModes : removedModes).put(mode, params[paramIndex]);
                paramIndex++;
            }
        }
    }

    public String name() {
        return name;
    }

    public Map<Character, String> addedModes() {
        return Collections.unmodifiableMap(addedModes);
    }

    public Map<Character, String> removedModes() {
        return Collections.unmodifiableMap(removedModes);
    }
}
