/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package net.boreeas.irc.events;

/**
 *
 * @author Boreeas
 */
public class ToggleMuteEvent extends Event {

    private String channel;
    private boolean active;

    public ToggleMuteEvent(String channel, boolean active) {
        this.channel = channel;
        this.active = active;
    }

    public String channel() {
        return channel;
    }

    public boolean muteActive() {
        return active;
    }
}
