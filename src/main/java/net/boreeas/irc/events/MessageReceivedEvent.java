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
public class MessageReceivedEvent extends Event {

    private User user;
    private String target;
    private String message;

    public MessageReceivedEvent(User user, String target, String message) {
        this.user = user;
        this.target = target;
        this.message = message;
    }

    public User user() {
        return user;
    }

    public String target() {
        return target;
    }

    public String message() {
        return message;
    }
}
