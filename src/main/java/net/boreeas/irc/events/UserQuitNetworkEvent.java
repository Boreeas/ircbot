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
public class UserQuitNetworkEvent extends Event {

    private User user;
    private String reason;

    public UserQuitNetworkEvent(User user, String reason) {
        this.user = user;
        this.reason = reason;
    }

    public User user() {
        return user;
    }

    public String reason() {
        return reason;
    }
}
