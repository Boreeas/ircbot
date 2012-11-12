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
public class CommandTriggeredEvent extends Event {

    private User user;
    private String command;

    public CommandTriggeredEvent(User user, String command) {
        this.user = user;
        this.command = command;
    }

    public User user() {
        return user;
    }

    public String command() {
        return command;
    }
}