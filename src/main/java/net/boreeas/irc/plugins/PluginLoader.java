package net.boreeas.irc.plugins;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Properties;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

/**
 *
 * @author malte
 */
public class PluginLoader {

    private static final Log logger = LogFactory.getLog("PluginLoader");

    public Plugin loadPlugin(File pluginFile) throws IOException {

        try {

            String mainClass = getMainClass(pluginFile);
            return loadPlugin(pluginFile.toURI().toURL(), mainClass);
        } catch (ClassNotFoundException ex) {

            logger.error("Can't load plugin " + pluginFile.getName()
                         + ": Missing main class");
        } catch (IllegalAccessException ex) {

            logger.error(null, ex);
        } catch (InstantiationException ex) {

            logger.error(null, ex);
        }

        return null;
    }

    private String getMainClass(File pluginFile) throws IOException {

        JarFile pluginJar = new JarFile(pluginFile);
        JarEntry config = pluginJar.getJarEntry("config.properties");

        if (config == null) {
            throw new RuntimeException("Missing config.properties");
        }

        InputStream in = pluginJar.getInputStream(config);

        try {

            Properties prop = new Properties();
            prop.load(in);

            String mainClass = prop.getProperty("main");

            if (mainClass == null) {
                throw new RuntimeException("Missing main class declaration");
            }

            return mainClass;
        } finally {

            in.close();
        }
    }

    private Plugin loadPlugin(URL target, String mainClass) throws
            ClassNotFoundException,
            InstantiationException,
            IllegalAccessException {

        PluginClassLoader classLoader = new PluginClassLoader(new URL[] {
                    target
                });

        Class<Plugin> plugClass = classLoader.loadPlugin(mainClass);

        Plugin plugin = plugClass.newInstance();
        return plugin;
    }
}
