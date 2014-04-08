/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net;

import junit.framework.TestCase;
import net.boreeas.irc.CTCP;

/**
 *
 * @author malte
 */
public class CTCPTest extends TestCase {

    public CTCPTest(String testName) {
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
     * Test of bold method, of class CTCP.
     */
    public void testBold() {

        System.out.println("bold");

        String unbolded = "test";
        String expResult = CTCP.BOLD + unbolded + CTCP.RESET;
        String result = CTCP.bold(unbolded);
        assertEquals(expResult, result);
    }

    /**
     * Test of underline method, of class CTCP.
     */
    public void testUnderline() {

        System.out.println("underline");

        String notUnderlined = "test";
        String expResult = CTCP.UNDERLINE + notUnderlined + CTCP.RESET;
        String result = CTCP.underline(notUnderlined);
        assertEquals(expResult, result);
    }
}
