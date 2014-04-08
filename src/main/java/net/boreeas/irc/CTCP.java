/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.boreeas.irc;

/**
 *
 * @author Boreeas
 */
public class CTCP {

    private CTCP() {}

    public static final char REQUEST = 0x01;
    public static final char BOLD = 0x02;
    public static final char COLOR = 0x03;
    public static final char RESET = 0x0F;
    public static final char UNDERLINE = 0x1F;

    public enum Request {

        ACTION("ACTION"),
        VERSION("VERSION");

        private String key;

        private Request(String key) {
            this.key = key;
        }

        public String format(String args) {
            return REQUEST + key + " " + args + REQUEST;
        }
    }


    public static String bold(String unbolded) {
        return BOLD + unbolded + RESET;
    }

    public static String underline(String notUnderlined) {
        return UNDERLINE + notUnderlined + RESET;
    }
}
