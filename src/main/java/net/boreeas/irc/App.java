package net.boreeas.irc;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.plist.PropertyListConfiguration;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

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
        FilenameFilter botConfigFilter = new FilenameFilter() {

            public boolean accept(File dir, String name) {

                return name.startsWith("bot_") && name.endsWith(".plist");
            }
        };

        for (File botConf: file.listFiles(botConfigFilter)) {

            try {
                IRCBot bot = new IRCBot(new PropertyListConfiguration(botConf.
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
