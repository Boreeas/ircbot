/*
 * This file is public domain
 */

package net.boreeas.irc.events;

import net.boreeas.irc.User;

public class UserChangedNickEvent {

    private User oldUser;
    private String newNick;

    public UserChangedNickEvent(User oldUser, String newNick) {
        this.oldUser = oldUser;
        this.newNick = newNick;
    }

    public User getOldUser() {
        return oldUser;
    }

    public String getOldNick() {
        return oldUser.nick();
    }

    public String getNewNick() {
        return newNick;
    }
}
