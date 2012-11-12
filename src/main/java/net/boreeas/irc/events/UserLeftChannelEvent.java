/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package net.boreeas.irc.events;

import net.boreeas.irc.User;

/**
 *
 * @author Boreeas
 */
public class UserLeftChannelEvent extends Event {

    private User user;
    private String channel;
    private String reason = "";

    public UserLeftChannelEvent(User user, String channel) {
        this.user = user;
        this.channel = channel;
    }

    public UserLeftChannelEvent(User user, String channel, String reason) {
        this(user, channel);
        this.reason = reason;
    }

    public User getUser() {
        return user;
    }

    public String getChannel() {
        return channel;
    }

    public String getReason() {
        return reason;
    }
}
