/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net;

import junit.framework.TestCase;
import net.boreeas.irc.ModeChangeBuilder;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author malte
 */
public class ModeChangeBuilderTest extends TestCase {

    public ModeChangeBuilderTest(String testName) {
        super(testName);
    }

    /**
     * Test of getAdding method, of class ModeChangeBuilder.
     */
    public void testGetAdding() {

        ModeChangeBuilder mcb = new ModeChangeBuilder();
        mcb.addMode('a');
        mcb.addMode('b', "xyz");

        Map<Character, String> expected = new HashMap<Character, String>();
        expected.put('a', "");
        expected.put('b', "xyz");
        assertEquals(mcb.getAdding(), expected);
    }

    /**
     * Test of getRemoving method, of class ModeChangeBuilder.
     */
    public void testGetRemoving() {
        ModeChangeBuilder mcb = new ModeChangeBuilder();
        mcb.removeMode('a');
        mcb.removeMode('b', "xyz");

        Map<Character, String> expected = new HashMap<Character, String>();
        expected.put('a', "");
        expected.put('b', "xyz");
        assertEquals(mcb.getRemoving(), expected);
    }

    /**
     * Test of format method, of class ModeChangeBuilder.
     */
    public void testFormat() {
        ModeChangeBuilder mcb = new ModeChangeBuilder();
        mcb.addMode('a', "xyz");
        mcb.addMode('b', "abc");
        mcb.addMode('c', "123");
        mcb.addMode('d');
        mcb.addMode('e', "qwe");
        mcb.addMode('f', "asd");
        mcb.removeMode('A', "XYZ");
        mcb.removeMode('B');
        mcb.removeMode('C', "!§$");
        mcb.removeMode('D', "QWE");
        mcb.removeMode('E', "ASD");
        mcb.removeMode('F');

        // expected = something like "+abcd xyz abc 123", "+ef-AB qwe asd XYZ", "-CDEF !§$ QWE ASD"

        List<String> result = mcb.format();
        System.out.println(result);
        assertEquals(3, result.size());
        assertEquals('+', result.get(0).charAt(0));
        assertEquals('+', result.get(1).charAt(0));
        assertTrue("Second line does not contain change to REMOVEMODE", result.get(1).contains("-"));
        assertEquals('-', result.get(2).charAt(0));

        for (String part: result) {
            System.out.println("Testing: " + result);
            String modes = part.split(" ")[0];
            if (modes.contains("a"))
                assertTrue("Mode a set without param xyz", part.contains("xyz"));
            if (modes.contains("b"))
                assertTrue("Mode b set without param abc", part.contains("abc"));
            if (modes.contains("c"))
                assertTrue("Mode c set without param 123", part.contains("123"));
            if (modes.contains("e"))
                assertTrue("Mode e set without param qwe", part.contains("qwe"));
            if (modes.contains("f"))
                assertTrue("Mode f set without param asd", part.contains("asd"));
            if (modes.contains("A"))
                assertTrue("Mode A set without param XYZ", part.contains("XYZ"));
            if (modes.contains("C"))
                assertTrue("Mode C set without param !§$", part.contains("!§$"));
            if (modes.contains("D"))
                assertTrue("Mode D set without param QWE", part.contains("QWE"));
            if (modes.contains("E"))
                assertTrue("Mode E set without param ASD", part.contains("ASD"));
        }
    }
}
