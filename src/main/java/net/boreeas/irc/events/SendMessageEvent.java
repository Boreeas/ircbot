/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.boreeas.irc.events;

/**
 *
 * @author Boreeas
 */
public class SendMessageEvent extends Event {

    private String target;
    private String message;

    public SendMessageEvent(String target, String message) {
        this.target = target;
        this.message = message;
    }

    public String getTarget() {
        return target;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
