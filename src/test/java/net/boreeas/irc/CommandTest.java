/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.boreeas.irc;

import java.io.IOException;
import junit.framework.TestCase;

/**
 *
 * @author malte
 */
public class CommandTest extends TestCase {

    public CommandTest(String testName) {
        super(testName);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }

    /**
     * Test of getReplyTarget method, of class Command.
     */
    public void testGetReplyTarget() {

        System.out.println("getReplyTarget");

        Command instance = new Command(null) {

            @Override
            public String getTrigger() {
                return null;
            }

            @Override
            public void execute(User sender, String target, String[] args)
                    throws IOException {
            }

            @Override
            public String help() {
                throw new UnsupportedOperationException("Not supported yet.");
            }
        };


        System.out.println("Test 1: Nick, nick");

        String sender = "xyz";
        String msgTarget = "abc";

        String result = instance.getReplyTarget(sender, msgTarget);
        assertEquals(sender, result);



        System.out.println("Test 2: Nick, channel");

        sender = "xyz";
        msgTarget = "#abc";

        result = instance.getReplyTarget(sender, msgTarget);
        assertEquals(msgTarget, result);
    }
}
