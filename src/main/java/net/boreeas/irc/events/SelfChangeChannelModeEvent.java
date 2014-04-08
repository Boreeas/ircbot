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
public class SelfChangeChannelModeEvent extends SelfModeChangeEvent{

    private String target;

    public SelfChangeChannelModeEvent(String target, Map<Character, String> adding, Map<Character, String> removing) {
        super(adding, removing);
        this.target = target;
    }

    public String target() {
        return target;
    }
}
