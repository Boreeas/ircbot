/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package net.boreeas.irc.events;

/**
 *
 * @author Boreeas
 */
public class SupportListReceivedEvent extends Event {

    private String[] supports;

    public SupportListReceivedEvent(String[] supports) {
        this.supports = supports;
    }

    public String[] supports() {
        return supports.clone();
    }
}
