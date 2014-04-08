/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package net.boreeas.irc.events;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Boreeas
 */
public class SelfModeChangeEvent extends Event {

    private Map<Character, String> addedModes;
    private Map<Character, String> removedModes;

    public SelfModeChangeEvent(Map<Character, String> addedModes, Map<Character, String> removedModes) {
        this.addedModes = addedModes;
        this.removedModes = removedModes;
    }

    public SelfModeChangeEvent(String modes, String[] params) {

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
            } else if (ChannelModeChangeEvent.modesWithParams.contains(mode)
                       && paramIndex < params.length) {

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

    public Map<Character, String> getAddedModes() {
        return addedModes;
    }

    public Map<Character, String> getRemovedModes() {
        return removedModes;
    }
}
