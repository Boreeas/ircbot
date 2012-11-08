/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package net.boreeas.irc.events;

/**
 *
 * @author Boreeas
 */
public class OtherJoinedChannelEvent extends Event {

    private String name;
    private String channel;

    public OtherJoinedChannelEvent(String name, String channel) {
        this.name = name;
        this.channel = channel;
    }

    public String getName() {
        return name;
    }

    public String getChannel() {
        return channel;
    }
}
