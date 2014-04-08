/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package net.boreeas.irc.plugins;

/**
 *
 * @author Boreeas
 */
@SuppressWarnings("serial")
class InvalidPluginVersionException extends RuntimeException {

    public InvalidPluginVersionException(String string) {
        super(string);
    }

}
