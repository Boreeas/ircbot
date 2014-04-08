/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package net.boreeas.irc.events;

/**
 *
 * @author Boreeas
 */
public class SelfJoinChannelEvent extends Event {

    private String channel;

    public SelfJoinChannelEvent(String channel) {
        this.channel = channel;
    }

    public String channel() {
        return channel;
    }
}
