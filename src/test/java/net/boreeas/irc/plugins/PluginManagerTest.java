/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.boreeas.irc.plugins;

import java.util.HashSet;
import junit.framework.TestCase;

/**
 *
 * @author malte
 */
public class PluginManagerTest extends TestCase {

    public PluginManagerTest(String testName) {
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
     * Test of stripFiletypeSuffix method, of class PluginManager.
     */
    public void testStripFiletypeSuffix() {

        System.out.println("stripFiletypeSuffix");
        PluginManager instance = new PluginManager(new HashSet<String>(), null);


        System.out.println("Test 1: File without suffix");

        String filename = "test";
        String expResult = "test";
        String result = instance.stripFiletypeSuffix(filename);
        assertEquals(expResult, result);

        
        System.out.println("Test 2: File with suffix");

        filename = "test.jar";
        expResult = "test";
        result = instance.stripFiletypeSuffix(filename);
        assertEquals(expResult, result);


        System.out.println("Test 3: File with dots and suffix");

        filename = "xyz.test.jar";
        expResult = "xyz.test";
        result = instance.stripFiletypeSuffix(filename);
        assertEquals(expResult, result);
    }
}
