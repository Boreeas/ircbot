/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package net.boreeas.irc.events;

import net.boreeas.irc.BotAccessLevel;

/**
 *
 * @author Boreeas
 */
public class AccessLevelChangeEvent extends Event {

    private String name;
    private BotAccessLevel newAccessLevel;

    public AccessLevelChangeEvent(String name, BotAccessLevel level) {
        this.name = name;
        this.newAccessLevel = level;
    }

    public String name() {
        return name;
    }

    public BotAccessLevel newAccessLevel() {
        return newAccessLevel;
    }
}
