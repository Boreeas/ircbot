/*
 * This file is public domain
 */
package net.boreeas.irc;

import java.lang.reflect.Method;
import java.util.Arrays;
import junit.framework.TestCase;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.plist.PropertyListConfiguration;

/**
 *
 * @author malte
 */
public class IrcBotTest extends TestCase {

    public IrcBotTest(String testName) {
        super(testName);
    }

    public void testSplitArgs() throws Throwable {

        try {
        Method splitArgs =
               IrcBot.class.getDeclaredMethod("splitArgs", String.class);
        splitArgs.setAccessible(true);

        String[] result = (String[]) splitArgs.invoke(null, "xyz abc def :");
        System.out.println("\"xyz abc def :\" → " + Arrays.toString(result));
        assertTrue("Empty last element should be dropped", Arrays.equals(result, new String[]{"xyz", "abc", "def"}));

        result = (String[]) splitArgs.invoke(null, "xyz abc def");
        System.out.println("\"xyz abc def\" → " + Arrays.toString(result));
        assertTrue("Strings without last elem indicator should be parsed correctly", Arrays.equals(result, new String[]{"xyz", "abc", "def"}));

        result = (String[]) splitArgs.invoke(null, "xyz abc :def 124");
        System.out.println("\"xyz abc :def 124\" → " + Arrays.toString(result));
        assertTrue("Last element should be recognized as one", Arrays.equals(result, new String[]{"xyz", "abc", "def 124"}));
        } catch (Throwable ex) {
            ex.printStackTrace();
            throw ex;

        }
    }
}
