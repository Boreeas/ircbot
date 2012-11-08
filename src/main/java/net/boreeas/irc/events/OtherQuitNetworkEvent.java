/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package net.boreeas.irc.events;

/**
 *
 * @author Boreeas
 */
public class OtherQuitNetworkEvent extends Event {

    private String name;
    private String reason;

    public OtherQuitNetworkEvent(String name, String reason) {
        this.name = name;
        this.reason = reason;
    }

    public String name() {
        return name;
    }

    public String reason() {
        return reason;
    }
}
