/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.boreeas.irc.events;

/**
 *
 * @author Boreeas
 */
public class CommandTriggeredEvent extends Event {

    private String name;
    private String command;

    public CommandTriggeredEvent(String name, String command) {
        this.name = name;
        this.command = command;
    }

    public String name() {
        return name;
    }

    public String command() {
        return command;
    }
}