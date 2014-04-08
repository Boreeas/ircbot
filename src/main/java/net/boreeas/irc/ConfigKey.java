/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package net.boreeas.irc;

/**
 *
 * @author Boreeas
 */
public enum ConfigKey {

    HOST("host"),
    PORT("port"),
    NICK("nick"),
    USER("user"),
    REALNAME("desc"),
    CHANNELS("channels"),
    MODS("access_mod"),
    ADMINS("access_admin"),
    OWNER("access_owner"),
    PLUGIN_DIR("plugin_dir", "plugins"),
    PLUGINS("plugins");

    private String key;
    private String defaultValue;
    private boolean required = true;

    private ConfigKey(String key) {
        this.key = key;
    }

    private ConfigKey(String key, String def) {
        this.key = key;
        this.defaultValue = def;
        this.required = false;
    }

    public String key() {
        return key;
    }

    public String defaultValue() {
        if (required) {
            throw new RuntimeException(toString() + " (" + key + ") is " + "required and has no default value");
        }
        return defaultValue;
    }

    public String getOrDefault() {
        if (this.key == null || this.key.isEmpty()) {
            return defaultValue;
        } else {
            return key;
        }
    }

    public boolean isRequired() {
        return required;
    }

}
