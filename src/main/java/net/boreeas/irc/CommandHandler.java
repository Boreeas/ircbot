/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.boreeas.irc;

import net.boreeas.irc.plugins.Plugin;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Boreeas
 */
public class CommandHandler {

    private static final Log log = LogFactory.getLog("Command");
    private Map<String, Map<String, Command>> registeredCommands =
                                              new HashMap<String, Map<String, Command>>();

    public void registerCommand(Plugin plugin, Command command) {

        String trigger = command.getTrigger().toLowerCase();
        getCommandMapForPlugin(plugin).put(trigger, command);
    }

    public String[] getRegisteredPrefixes() {

        return registeredCommands.keySet().toArray(new String[0]);
    }

    public String[] getRegisteredCommands(String prefix) {

        Map<String, Command> commands = registeredCommands.get(prefix.toLowerCase());

        if (commands == null) {
            return new String[0];
        }

        return commands.keySet().toArray(new String[0]);
    }

    public String getHelp(String prefix, String command) throws NoSuchCommandException {

        Map<String, Command> commands = registeredCommands.get(prefix.toLowerCase());

        if (commands == null) {
            throw new NoSuchCommandException("Unknown command prefix: " + prefix);
        }

        Command c = commands.get(command.toLowerCase());

        if (c == null) {
            throw new NoSuchCommandException("Unknown command with prefix " + prefix + ": " + command);
        }

        return c.help();
    }

    private Map<String, Command> getCommandMapForPlugin(Plugin plugin) {

        Map<String, Command> map =
                             registeredCommands.get(plugin.getCommandPrefix());

        if (map == null) {
            map = new HashMap<String, Command>();
            registeredCommands.put(plugin.getCommandPrefix(), map);
        }

        return map;
    }

    public void unregisterCommand(Plugin plugin, Command command) {

        String trigger = command.getTrigger().toLowerCase();
        getCommandMapForPlugin(plugin).remove(trigger);
    }

    public void unregisterPlugin(Plugin plugin) {

        registeredCommands.remove(plugin.getCommandPrefix());
    }

    public boolean callCommand(String pluginPrefix, String cmd,
                               User sender, String target, String[] args)
            throws IOException {

        String prefix = pluginPrefix.toLowerCase();
        Map<String, Command> commands = registeredCommands.get(prefix);

        if (commands != null) {

            Command c = commands.get(cmd.toLowerCase());

            if (c != null) {
                try {

                    c.execute(sender, target, args);
                } catch (RuntimeException ex) {

                    log.error("Error while executing command '" + pluginPrefix
                              + " " + cmd + "'", ex);
                } catch (NoSuchMethodError err) {

                    log.error("Error while executing command '" + pluginPrefix + " " + cmd + "'", err);
                }

                return true;
            }
        }

        return false;
    }
}
