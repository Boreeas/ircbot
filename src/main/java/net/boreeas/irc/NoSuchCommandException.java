/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package net.boreeas.irc;

/**
 *
 * @author Boreeas
 */
public class NoSuchCommandException extends Exception {

    public NoSuchCommandException(String string) {
        super(string);
    }

}
