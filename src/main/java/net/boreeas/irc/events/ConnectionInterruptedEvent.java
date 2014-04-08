/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package net.boreeas.irc.events;

/**
 *
 * @author Boreeas
 */
public class ConnectionInterruptedEvent extends Event {

    private Exception cause;

    public ConnectionInterruptedEvent(Exception cause) {
        this.cause = cause;
    }

    public Exception cause() {
        return cause;
    }
}
