/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.boreeas.irc;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.IOException;

/**
 *
 * @author Boreeas
 */
public abstract class Command {

    protected IrcBot bot;
    protected final static Log log = LogFactory.getLog("Command");

    public Command(IrcBot bot) {
        this.bot = bot;
    }

    protected boolean require(String nick, BotAccessLevel level)
            throws IOException {

        return bot.getAccessLevel(nick, true).compareTo(level) >= 0;
    }

    protected boolean require(String nick, String chan,
                              ChannelAccessLevel level) {

        return bot.getChanAccess(nick, chan).compareTo(level) > 0;
    }

    @Deprecated
    protected void nope(String nick, String target, BotAccessLevel level)
            throws IOException {

        bot.sendNotice(target, nick + ": BotAccessLevel " + level
                               + " is needed.");
    }

    @Deprecated
    protected String getReplyTarget(String sender, String msgTarget) {

        return (msgTarget.startsWith("#")) ? msgTarget : sender;
    }

    /**
     * Returns the trigger used to execute this command
     * @return The trigger
     */
    public abstract String getTrigger();

    /**
     * Executes the command
     * @param sender The person who requested execution
     * @param target The target of the execution request
     * @param args The arguments to the command
     * @throws java.io.IOException
     */
    public abstract void execute(User sender, String target, String[] args)
            throws IOException;

    public abstract String help();
}
