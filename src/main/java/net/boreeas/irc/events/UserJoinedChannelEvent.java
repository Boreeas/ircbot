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
public class UserJoinedChannelEvent extends Event {

    private User user;
    private String channel;

    public UserJoinedChannelEvent(User user, String channel) {
        this.user = user;
        this.channel = channel;
    }

    public User getName() {
        return user;
    }

    public String getChannel() {
        return channel;
    }
}
