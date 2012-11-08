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
public class PluginLoadException extends Exception {

    public PluginLoadException() {}
    public PluginLoadException(String msg) {super(msg);}
    public PluginLoadException(Throwable cause) {super(cause);}
    public PluginLoadException(String msg, Throwable cause) {super(msg, cause);}
}
