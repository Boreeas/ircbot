/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.boreeas.irc.plugins;

import net.boreeas.irc.IrcBot;

/**
 *
 * @author malte
 */
public interface Plugin {

    /**
     * Called when the plugin is enabled.
     * @param bot The IrcBot that loaded the plugin
     */
    public void onEnable(IrcBot bot);

    /**
     * Called when the plugin is disabled.
     */
    public void onDisable();


    /**
     * Save all persistent data of this plugin.
     */
    public void save();

    /**
     * Returns the name of this plugin.
     * @return The name
     */
    public String getPluginName();

    /**
     * Returns the command prefix to use when registering commands.
     * @return The prefix to use
     */
    public String getCommandPrefix();

    /**
     * Returns the short overview over the intention of this plugin.
     * @return The plugin description
     */
    public String getDescription();

    /**
     * Returns the version of the plugin.
     * @return The version of the plugin
     */
    public String getVersion();


    /**
     * Returns the filename of the plugin file to load when this plugin is
     * reloaded.
     * @return The filename of the plugin jar file.
     */
    public String reloadTarget();
}
