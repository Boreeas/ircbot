package net.boreeas.irc.plugins;

import net.boreeas.irc.IrcBot;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author malte
 */
public class PluginManager {

    private static final Log logger = LogFactory.getLog("Plugins");
    private final Set<String> files;
    private final ArrayList<Plugin> loadedPlugins = new ArrayList<Plugin>();
    private final IrcBot loadRequester;
    private Set<Plugin> scheduledUnload = new HashSet<>();

    public PluginManager(Set<String> configuredFiles, IrcBot loader) {
        this.loadRequester = loader;
        this.files = configuredFiles;
    }

    /**
     * Loads all plugins from WORKING_DIR/plugins.
     */
    public void loadAllPlugins() {

        loadAllPlugins(System.getProperty("user.dir") + "/plugins");
    }

    String stripFiletypeSuffix(String filename) {

        String[] parts = filename.split("\\.");

        if (parts.length == 1) {
            return filename;
        }

        if (parts.length == 2) {
            return parts[0];
        }

        return StringUtils.join(parts, '.', 0, parts.length - 1);
    }

    /**
     * Loads all plugins from the specified location.
     * <p/>
     * @param pathToPluginFolder The location of the plugins
     */
    public void loadAllPlugins(String pathToPluginFolder) {

        File dir = new File(pathToPluginFolder);

        if (dir.isFile()) {
            throw new RuntimeException(
                    "Cannot load all plugins from single file, must be dir");
        }

        if (!dir.exists()) {
            dir.mkdirs();
        }

        String[] pluginJarNames = dir.list(new FilenameFilter() {

            @Override
            public boolean accept(File dir, String name) {

                return files.contains(name)
                       || files.contains(stripFiletypeSuffix(name));
            }
        });

        logger.debug("The following files passed the filter: " + Arrays.toString(pluginJarNames));

        for (String fileName: pluginJarNames) {
            try {
                loadPlugin(new File(dir, fileName));
            } catch (IOException | PluginLoadException ex) {
                logger.error("Unable to load plugin " + fileName, ex);
            }
        }
    }

    /**
     * Loads a plugin from WORKING_DIR/plugins/filename
     * <p/>
     * @param filename The filename of the plugin jar file
     * <p/>
     * @return The loaded plugin
     * <p/>
     * @throws java.io.FileNotFoundException If no file with that name exists
     * @throws java.io.IOException           If an IOException occurs while loading the
     *                               plugin
     * @throws PluginLoadException   If an exception occurs while enabling the
     *                               plugin
     */
    public Plugin loadPlugin(String filename) throws FileNotFoundException,
                                                     IOException,
                                                     PluginLoadException {

        File pluginDir = new File(System.getProperty("user.dir"), "plugins");
        return loadPlugin(new File(pluginDir, filename));
    }

    /**
     * Loads a plugin from the specified file
     * <p/>
     * @param pluginFile The file that contains the plugin
     * <p/>
     * @return The loaded plugin
     * <p/>
     * @throws java.io.FileNotFoundException If no file with that name exists
     * @throws java.io.IOException           If an IOException occurs while loading the
     *                               plugin
     * @throws PluginLoadException   If an exception occurs while enabling the
     *                               plugin
     */
    public Plugin loadPlugin(File pluginFile) throws IOException,
                                                     PluginLoadException {

        logger.info("Loading plugin " + pluginFile);

        if (!pluginFile.exists()) {
            throw new FileNotFoundException(pluginFile.getName());
        }

        PluginLoader loader = new PluginLoader();
        Plugin loadedPlugin = null;


        try {

            loadedPlugin = loader.loadPlugin(pluginFile);
            loadedPlugin.onEnable(loadRequester);
        } catch (Exception ex) {

            if (loadedPlugin != null) {
                // Unregister any commands the plugin may have registered
                loadRequester.getCommandHandler().unregisterPlugin(loadedPlugin);
            }

            throw new PluginLoadException(ex);
        }

        synchronized (this) {
            if (scheduledUnload.contains(loadedPlugin)) {
                disablePlugin(loadedPlugin);
                throw new PluginLoadException("Fatal error during load - immediate unload occured");
            } else {
                loadedPlugins.add(loadedPlugin);
            }
        }

        return loadedPlugin;
    }

    /**
     * In case a fatal error occurred during plugin loading, a plugin
     * can be scheduled to be immediately unloaded after the loading
     * process is finished. This will have the same effect as if
     * @see #disablePlugin(Plugin) was called immediately for this plugin.
     * <p/>
     * Note that this is only effective if called during the plugin loading
     * process. If an error occurs any later, @see #disablePlugin(Plugin) should
     * be called instead.
     * @param plugin The plugin that should be unloaded
     */
    public void scheduleImmediateUnload(Plugin plugin) {
        scheduledUnload.add(plugin);
    }

    /**
     * Disables the first plugin with the given name.
     * <p/>
     * @param pluginname The name of the plugin to disable
     * <p/>
     * @return <code>true</code> if a plugin with that name was found,
     *         <code>false</code> otherwise.
     */
    public synchronized boolean disablePlugin(String pluginname) {

        Plugin match = getPlugin(pluginname);

        if (match != null) {
            disablePlugin(match);
            return true;
        }

        return false;
    }

    /**
     * Disables the given plugin.
     * <p/>
     * @param plugin The plugin to disable
     */
    public synchronized void disablePlugin(Plugin plugin) {
        plugin.onDisable();
        loadedPlugins.remove(plugin);
        loadRequester.getCommandHandler().unregisterPlugin(plugin);
        loadRequester.unregisterAllEventListeners(plugin);
    }

    /**
     * Disables all plugins.
     */
    public void disableAllPlugins() {

        saveAllPlugins();

        for (Plugin plugin: loadedPlugins) {
            plugin.onDisable();
            loadRequester.getCommandHandler().unregisterPlugin(plugin);
            loadRequester.unregisterAllEventListeners(plugin);
        }

        loadedPlugins.clear();
    }

    /**
     * Saves all plugins.
     */
    public void saveAllPlugins() {
        for (Plugin plugin: loadedPlugins) {
            plugin.save();
        }
    }

    /**
     * Returns the names of all loaded plugins
     * <p/>
     * @return The names
     */
    public String[] loadedPlugins() {
        String[] names = new String[loadedPlugins.size()];

        synchronized (this) {
            for (int i = 0; i < loadedPlugins.size(); i++) {
                names[i] = loadedPlugins.get(i).getPluginName();
            }
        }

        return names;
    }

    /**
     * Returns the filenames of all plugins in WORKING_DIR/plugins
     * <p/>
     * @return The filenames
     */
    public String[] availablePlugins() {
        File pluginDir = new File(System.getProperty("user.dir"), "plugins");
        return availablePlugins(pluginDir);
    }

    /**
     * Returns the filenames of all plugins in the specified directory
     * <p/>
     * @param pluginDir The directory to check
     * <p/>
     * @return The filenames
     */
    public String[] availablePlugins(File pluginDir) {

        return pluginDir.list(new FilenameFilter() {

            @Override
            public boolean accept(File dir, String name) {
                return name.endsWith(".jar");
            }
        });
    }

    /**
     * Returns the first plugin to match the given name
     * <p/>
     * @param name The name of the plugin
     * <p/>
     * @return The plugin, or <code>null</code> if no plugin with that name
     *         exists
     */
    public synchronized Plugin getPlugin(String name) {

        for (Plugin plugin: loadedPlugins) {
            if (name.equalsIgnoreCase(plugin.getPluginName())) {
                return plugin;
            }
        }

        return null;
    }
}
