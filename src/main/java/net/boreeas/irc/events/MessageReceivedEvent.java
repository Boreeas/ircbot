/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package net.boreeas.irc.events;

/**
 *
 * @author Boreeas
 */
public class MessageReceivedEvent extends Event {

    private String name;
    private String target;
    private String message;

    public MessageReceivedEvent(String name, String target, String message) {
        this.name = name;
        this.target = target;
        this.message = message;
    }

    public String name() {
        return name;
    }

    public String target() {
        return target;
    }

    public String message() {
        return message;
    }
}
