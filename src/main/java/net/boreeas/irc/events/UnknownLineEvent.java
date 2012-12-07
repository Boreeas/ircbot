/*
 * This file is public domain
 */

package net.boreeas.irc.events;

public class UnknownLineEvent {

    private String[] parts;

    public UnknownLineEvent(String[] parts) {
        this.parts = parts;
    }

    public String[] getParts() {
        return parts;
    }
}
