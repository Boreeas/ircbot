/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package net.boreeas.irc.events;

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

    public Map<Character, String> getAddedModes() {
        return addedModes;
    }

    public Map<Character, String> getRemovedModes() {
        return removedModes;
    }
}
