/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.boreeas.irc;

import junit.framework.TestCase;

/**
 *
 * @author malte
 */
public class UserTest extends TestCase {

    private User user = new User("nick", "user", "host");
    private User user2 = new User("nick!user@host");

    public UserTest(String testName) {
        super(testName);
    }

    /**
     * Test of nick method, of class User.
     */
    public void testNick() {
        assertEquals(user.nick(), "nick");
        assertEquals(user2.nick(), "nick");
    }

    /**
     * Test of user method, of class User.
     */
    public void testUser() {
        assertEquals(user.user(), "user");
        assertEquals(user2.user(), "user");
    }

    /**
     * Test of host method, of class User.
     */
    public void testHost() {
        assertEquals(user.host(), "host");
        assertEquals(user2.host(), "host");
    }

    /**
     * Test of toString method, of class User.
     */
    public void testToString() {
        assertEquals(user.toString(), "nick!user@host");
        assertEquals(user2.toString(), "nick!user@host");
    }


    public void testEquals() {
        assertEquals(user, user2);
        assertEquals(user2, "nick!user@host");
    }
}
