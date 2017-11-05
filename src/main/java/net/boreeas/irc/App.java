package net.boreeas.irc;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.plist.PropertyListConfiguration;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;

/**
 * Hello world!
 *
 */
public class App {

    private static final Log logger = LogFactory.getLog("RSS2IRC");

    private App() {
    }

    public static void main(String[] args) {

        File file = new File(System.getProperty("user.dir"));
        FilenameFilter botConfigFilter = (dir, name) -> name.startsWith("bot_") && name.endsWith(".plist");

        File[] files = file.listFiles(botConfigFilter);
        if (files == null) {
            logger.error("Working dir is not a valid directory");
            System.exit(1);
        }

        if (files.length == 0) {
            logger.error("No configurations found");
            System.exit(1);
        }

        logger.info("Found " + files.length + "  configs:");
        for (File f: files) {
            logger.info("  " + f.getName());
        }

        for (File botConf: files) {
            try {
                IrcBot bot = new IrcBot(new PropertyListConfiguration(botConf.
                        getName()));

                logger.info("Starting " + bot);
                bot.connect();
                bot.start();
            } catch (ConfigurationException ex) {
                logger.fatal("Error while loading configuration file " + botConf,
                             ex);
            } catch (IOException ex) {
                logger.fatal("Unable to start bot for config file " + botConf,
                             ex);
            }
        }
    }
}
