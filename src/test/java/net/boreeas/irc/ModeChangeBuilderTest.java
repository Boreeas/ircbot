/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.boreeas.irc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import junit.framework.TestCase;

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
        assertEquals(3, result.size());
        assertEquals('+', result.get(0).charAt(0));
        assertEquals('+', result.get(1).charAt(0));
        assertTrue(result.get(1).contains("-"));
        assertEquals('-', result.get(2).charAt(0));

        for (String part: result) {
            if (part.contains("a"))
                assertTrue(part.contains("xyz"));
            if (part.contains("b"))
                assertTrue(part.contains("abc"));
            if (part.contains("c"))
                assertTrue(part.contains("123"));
            if (part.contains("e"))
                assertTrue(part.contains("qwe"));
            if (part.contains("f"))
                assertTrue(part.contains("asd"));
            if (part.contains("A"))
                assertTrue(part.contains("XYZ"));
            if (part.contains("C"))
                assertTrue(part.contains("!§$"));
            if (part.contains("D"))
                assertTrue(part.contains("QWE"));
            if (part.contains("E"))
                assertTrue(part.contains("ASD"));
        }
    }
}
