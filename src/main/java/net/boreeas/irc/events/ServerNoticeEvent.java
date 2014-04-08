/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package net.boreeas.irc.events;

/**
 *
 * @author Boreeas
 */
public class ServerNoticeEvent extends Event {

    private String server;
    private String message;

    public ServerNoticeEvent(String server, String message) {
        this.server = server;
        this.message = message;
    }

    public String server() {
        return server;
    }

    public String message() {
        return message;
    }
}
