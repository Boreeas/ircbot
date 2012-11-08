/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package net.boreeas.irc.events;

/**
 *
 * @author Boreeas
 */
public class SelfLeaveChannelEvent extends Event {

    private String channel;
    private String reason;

    public SelfLeaveChannelEvent(String channel) {
        this(channel, null);
    }

    public SelfLeaveChannelEvent(String channel, String reason) {
        this.channel = channel;
        this.reason = reason;
    }

    public String channel() { return channel; }
    public String reason() { return reason; }

    public void setReason(String reason) {
        this.reason = reason;
    }
}
