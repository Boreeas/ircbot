/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.boreeas.irc;

import java.io.IOException;
import java.util.TimerTask;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 *
 * @author Boreeas
 */
public class TimeoutCheck extends TimerTask {

    private static final Log logger = LogFactory.getLog("TimeoutCheck");
    public static final long TIMEOUT = 1000 * 60 * 10; // 10 minute timeout

    private final IrcBot bot;
    private long timeSinceLastServerMessage;

    public TimeoutCheck(IrcBot bot) {
        this.bot = bot;
    }


    @Override
    public void run() {

        if (timeSinceLastServerMessage > TIMEOUT) {

            logger.error("Connection timed out. Reconnecting...");
            bot.reconnect();
        } else if (timeSinceLastServerMessage > TIMEOUT / 2) {

            logger.info("No message for several minutes. Sending ping");

            try {
                bot.send("PING :STILLCONNECTED");
            } catch (IOException ex) {

                logger.fatal("Unable to send ping. Reconnecting");
                bot.reconnect();
            }
        }
    }
}
