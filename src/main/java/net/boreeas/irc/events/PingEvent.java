/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package net.boreeas.irc.events;

/**
 *
 * @author Boreeas
 */
public class PingEvent extends Event {

    private String code;

    public PingEvent(String code) {
        this.code = code;
    }

    public String code() {
        return code;
    }
}
