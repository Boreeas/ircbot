/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.boreeas.irc;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 *
 * @author Boreeas
 */
public class Preferences {

    // Used for parsing file
    enum State {

        GLOBAL, CHANNEL, GLOBAL_DEFAULT, CHANNEL_DEFAULT;
        private String param;

        public String getParam() {
            return param;
        }

        public void setParam(String param) {
            this.param = param;
        }
    }

    public static final String GLOBAL_WHOX = "whox";
    public static final String GLOBAL_CHANTYPES = "chantypes";
    public static final String CHANNEL_CMD_PREFIX = "cmd-prefix";

    private static final Log logger = LogFactory.getLog("Preferences");

    private final String file;

    private Map<String, String> globalPreferences;
    private Map<String, Map<String, String>> channelPreferences;
    private Map<String, String> globalDefaults;
    private Map<String, String> channelDefaults;

    public Preferences(String file) {

        globalPreferences = new HashMap<String, String>();
        channelPreferences = new HashMap<String, Map<String, String>>();
        globalDefaults = new HashMap<String, String>();
        channelDefaults = new HashMap<String, String>();

        globalDefaults.put(GLOBAL_WHOX, "false");
        globalDefaults.put(GLOBAL_CHANTYPES, "#");
        channelDefaults.put(CHANNEL_CMD_PREFIX, "!");

        this.file = file;

        try {
            loadFromFile();
        } catch (IOException ex) {
            logger.error("Unable to load preference file " + file, ex);
        }
    }

    private void loadFromFile() throws IOException {

        File target = new File(file);

        if (!target.exists()) {
            return; // No saved preferences yet
        }

        State currentState = State.GLOBAL;
        BufferedReader reader =
                       new BufferedReader(new InputStreamReader(new FileInputStream(target)));

        try {
            String in;
            while ((in = reader.readLine()) != null) {
                in = in.trim();

                if (in.isEmpty() || in.startsWith("#")) {
                    continue;
                }

                if (in.equalsIgnoreCase("[global]")) {

                    currentState = State.GLOBAL;
                } else if (in.equalsIgnoreCase("[global-default]")) {

                    currentState = State.GLOBAL_DEFAULT;
                } else if (in.equalsIgnoreCase("[channel-defaul]")) {

                    currentState = State.CHANNEL_DEFAULT;
                } else if (in.toLowerCase().startsWith("[channel:") && in.endsWith("]")) {

                    currentState = State.CHANNEL;
                    String chan = in.split(":")[1];
                    chan = chan.substring(0, chan.length() - 1); // Cut off trailing ]
                    currentState.setParam(chan);
                } else {

                    String[] parts = in.split("=", 2);  // key, value

                    if (parts.length < 2) {
                        logger.error("Unable to parse preference line " + in);
                        continue;
                    }

                    if (currentState == State.GLOBAL) {
                        setString(parts[0], parts[1]);
                    } else if (currentState == State.CHANNEL) {
                        setString(currentState.getParam(), parts[0], parts[1]);
                    } else if (currentState == State.GLOBAL_DEFAULT) {
                        globalDefaults.put(parts[0].toLowerCase(), parts[1]);
                    } else if (currentState == State.CHANNEL_DEFAULT) {
                        channelDefaults.put(parts[0].toLowerCase(), parts[1]);
                    }
                }
            }
        } finally {
            reader.close();
        }
    }

    private void saveToFile() throws IOException {

        OutputStreamWriter writer =
                           new OutputStreamWriter(new FileOutputStream(file));

        try {
            writer.write("[global]\r\n");
            for (Entry<String, String> entry: globalPreferences.entrySet()) {
                writer.write(entry.getKey() + "=" + entry.getValue() + "\r\n");
            }

            writer.write("[global-defaults]\r\n");
            for (Entry<String, String> entry: globalDefaults.entrySet()) {
                writer.write(entry.getKey() + "=" + entry.getValue() + "\r\n");
            }

            writer.write("[channel-defaults]\r\n");
            for (Entry<String, String> entry: channelDefaults.entrySet()) {
                writer.write(entry.getKey() + "=" + entry.getValue() + "\r\n");
            }

            for (Entry<String, Map<String, String>> entry:
                 channelPreferences.entrySet()) {
                writer.write("[channel:" + entry.getKey() + "]\r\n");

                for (Entry<String, String> chanEntry:
                     entry.getValue().entrySet()) {
                    writer.write(chanEntry.getKey() + "=" + chanEntry.getValue() + "\r\n");
                }
            }
        } finally {
            writer.flush();
            writer.close();
        }
    }

    /**
     * Returns the string in the global preferences associated with the
     * key. If no value is associated with the key, searches the global defaults
     * and returns the default value, or null if no default value is set.
     * @param key The key for the value
     * @return The value associated with the key
     */
    public String getString(String key) {

        String global = globalPreferences.get(key.toLowerCase());

        if (global == null) {
            return globalDefaults.get(key.toLowerCase());
        }

        return global;
    }

    public void setString(String key, String value) {
        globalPreferences.put(key.toLowerCase(), value);

        try {
            saveToFile();
        } catch (IOException ex) {
            logger.error("Can't save preferences", ex);
        }
    }

    /**
     * Returns the int associated with the key. If no value is associated,
     * or the associated value is not an int, returns 0.
     * @param key The key for the value.
     * @return The value associated with the key, or 0.
     */
    public int getInt(String key) {
        try {
            return Integer.parseInt(getString(key));
        } catch (NumberFormatException ex) {
            return 0;
        }
    }

    public void setInt(String key, int value) {
        setString(key, Integer.toString(value));
    }

    /**
     * Returns the double associated with the key. If no value is associated,
     * or the associated value is not a double, returns 0.
     * @param key The key for the value.
     * @return The value associated with the key, or 0.
     */
    public double getDouble(String key) {
        try {
            return Double.parseDouble(getString(key));
        } catch (NumberFormatException ex) {
            return 0;
        }
    }

    public void setDouble(String key, double value) {
        setString(key, Double.toString(value));
    }

    /**
     * Returns the boolean value of the string associated with the
     * key.
     * @param key The key for the value
     * @return <code>true</code> if and only if the value is not null and
     * equals, ignoring case, the literal "true", else <code>false</code>.
     */
    public boolean getBoolean(String key) {
        return Boolean.valueOf(getString(key));
    }

    public void setBoolean(String key, boolean value) {
        setString(key, Boolean.toString(value));
    }

    /**
     * Returns the long associated with the key. If no value is associated,
     * or the associated value is not a long, returns 0.
     * @param key The key for the value.
     * @return The value associated with the key, or 0.
     */
    public long getLong(String key) {
        return Long.parseLong(getString(key));
    }

    public void setLong(String key, long value) {
        setString(key, Long.toString(value));
    }

    /**
     * Returns the string associated with the given key in the specified
     * channel. If no value is associated, searches the channel default values
     * and returns default value, or <code>null</code> if no value is set.
     * @param channel The channel for which the value should be searched.
     * @param key The key for the value.
     * @return The value associated with the key.
     */
    public String getString(String channel, String key) {

        Map<String, String> chanPref = channelPreferences.get(channel.toLowerCase());

        if (chanPref != null) {

            String result = chanPref.get(key.toLowerCase());

            if (result == null) {
                // Nonexistant key, check defaults instead
                return getChannelDefault(key);
            }

            return result;
        }

        // Nonexistant preferences for this channel, check defaults instead
        return getChannelDefault(key);
    }

    private String getChannelDefault(String key) {
        return channelDefaults.get(key.toLowerCase());
    }

    public void setString(String channel, String key, String value) {

        Map<String, String> chanPref =
                            channelPreferences.get(channel.toLowerCase());

        if (chanPref == null) {
            chanPref = new HashMap<String, String>();
            channelPreferences.put(channel.toLowerCase(), chanPref);
        }

        chanPref.put(key.toLowerCase(), value);

        try {
            saveToFile();
        } catch (IOException ex) {
            logger.error("Can't save preferences", ex);
        }
    }

    /**
     * Returns the int associated with the given key in the specified
     * channel. If no value is associated, or the value is not an int, returns
     * 0.
     * @param channel The channel for which the value should be searched.
     * @param key The key for the value.
     * @return The value associated with the key.
     */
    public int getInt(String channel, String key) {
        try {
            return Integer.parseInt(getString(channel, key));
        } catch (NumberFormatException ex) {
            return 0;
        }
    }

    public void setInt(String channel, String key, int value) {
        setString(channel, key, Integer.toString(value));
    }

    /**
     * Returns the double associated with the given key in the specified
     * channel. If no value is associated, or the value is not an double, returns
     * 0.
     * @param channel The channel for which the value should be searched.
     * @param key The key for the value.
     * @return The value associated with the key.
     */
    public double getDouble(String channel, String key) {
        try {
            return Double.parseDouble(getString(channel, key));
        } catch (NumberFormatException ex) {
            return 0;
        }
    }

    public void setDouble(String channel, String key, double value) {
        setString(channel, key, Double.toString(value));
    }

    /**
     * Returns the boolean value of the value associated with the key
     * in the specified channel.
     * @param channel The channel for which the value should be searched.
     * @param key The key for the value.
     * @return <code>true</code> if and only if the value is not null and
     * equals, ignoring case, the literal "true".
     */
    public boolean getBoolean(String channel, String key) {
        return Boolean.valueOf(getString(channel, key));
    }

    public void setBoolean(String channel, String key, boolean value) {
        setString(channel, key, Boolean.toString(value));
    }

    /**
     * Returns the long associated with the given key in the specified
     * channel. If no value is associated, or the value is not an long, returns
     * 0.
     * @param channel The channel for which the value should be searched.
     * @param key The key for the value.
     * @return The value associated with the key.
     */
    public long getLong(String channel, String key) {
        try {
            return Long.parseLong(getString(channel, key));
        } catch (NumberFormatException ex) {
            return 0;
        }
    }

    public void setLong(String channel, String key, long value) {
        setString(channel, key, Long.toString(value));
    }
}
