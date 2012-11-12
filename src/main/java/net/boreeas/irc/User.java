/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.boreeas.irc;

/**
 *
 * @author Boreeas
 */
public class User {

    private String nick;
    private String user;
    private String host;

    public User(String nick, String user, String host) {
        this.nick = nick;
        this.user = user;
        this.host = host;
    }

    public User(String hostmask) {
        String[] nickAndRest = hostmask.split("!");
        String[] userAndHost = nickAndRest[1].split("@");

        this.nick = nickAndRest[0];

        if (userAndHost[0].startsWith("~")) {
            userAndHost[0] = userAndHost[0].substring(1);
        }

        this.user = userAndHost[0];
        this.host = userAndHost[1];
    }

    public String nick() {
        return nick;
    }

    public String user() {
        return user;
    }

    public String host() {
        return host;
    }

    @Override
    public String toString() {
        return nick + "!" + user + "@" + host;
    }

    @Override
    public boolean equals(Object other) {

        if (other == null) {
            return false;
        }

        if (other instanceof String) {

            return equals(new User((String) other));
        } else if (other instanceof User) {

            User u = (User) other;
            return u.nick().equalsIgnoreCase(nick)
                   && u.user().equalsIgnoreCase(user)
                   && u.host().equalsIgnoreCase(host);
        }

        return false;
    }

    @Override
    public int hashCode() {

        int hash = 3;

        hash = 37 * hash + (this.nick != null
                            ? this.nick.hashCode()
                            : 0);

        hash = 37 * hash + (this.user != null
                            ? this.user.hashCode()
                            : 0);

        hash = 37 * hash + (this.host != null
                            ? this.host.hashCode()
                            : 0);
        return hash;
    }
}
