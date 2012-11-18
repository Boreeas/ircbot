/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.boreeas.irc;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import net.boreeas.irc.events.*;
import net.boreeas.irc.plugins.Plugin;

/**
 *
 * @author Boreeas
 */
public class EventPump implements EventListener {

    private Map<Plugin, Set<EventListener>> byPlugin =
                                            new HashMap<Plugin, Set<EventListener>>();
    private Set<EventListener> eventListeners = new HashSet<EventListener>();

    void addEventListener(Plugin plugin, EventListener listener) {

        eventListeners.add(listener);

        Set<EventListener> forPlugin = byPlugin.get(plugin);
        if (forPlugin == null) {
            forPlugin = new HashSet<EventListener>();
            byPlugin.put(plugin, forPlugin);
        }

        forPlugin.add(listener);
    }

    void removeEventListener(EventListener listener) {
        eventListeners.remove(listener);
    }

    void removeAllListeners(Plugin plugin) {

        Set<EventListener> forPlugin = byPlugin.get(plugin);

        if (forPlugin != null) {
            eventListeners.removeAll(forPlugin);
            byPlugin.remove(plugin);
        }
    }

    @Override
    public void onSelfJoinChannel(SelfJoinChannelEvent evt) {
        for (EventListener listener: eventListeners) {
            listener.onSelfJoinChannel(evt);
        }
    }

    @Override
    public void onSelfLeaveChannel(SelfLeaveChannelEvent evt) {
        for (EventListener listener: eventListeners) {
            listener.onSelfLeaveChannel(evt);
        }
    }

    @Override
    public void onSendMessage(SendMessageEvent evt) {
        for (EventListener listener: eventListeners) {
            listener.onSendMessage(evt);
        }
    }

    @Override
    public void onSendNotice(SendMessageEvent evt) {
        for (EventListener listener: eventListeners) {
            listener.onSendNotice(evt);
        }
    }

    @Override
    public void onSelfDisconnected(SelfDisconnectedEvent evt) {
        for (EventListener listener: eventListeners) {
            listener.onSelfDisconnected(evt);
        }
    }

    @Override
    public void onSelfModeChange(SelfModeChangeEvent evt) {
        for (EventListener listener: eventListeners) {
            listener.onSelfModeChange(evt);
        }
    }

    @Override
    public void onSelfChangeChannelMode(SelfChangeChannelModeEvent evt) {
        for (EventListener listener: eventListeners) {
            listener.onSelfChangeChannelMode(evt);
        }
    }

    @Override
    public void onUserJoinedChannel(UserJoinedChannelEvent evt) {
        for (EventListener listener: eventListeners) {
            listener.onUserJoinedChannel(evt);
        }
    }

    @Override
    public void onUserLeftChannel(UserLeftChannelEvent evt) {
        for (EventListener listener: eventListeners) {
            listener.onUserLeftChannel(evt);
        }
    }

    @Override
    public void onMessageReceived(MessageReceivedEvent evt) {
        for (EventListener listener: eventListeners) {
            listener.onMessageReceived(evt);
        }
    }

    @Override
    public void onNoticeReceived(MessageReceivedEvent evt) {
        for (EventListener listener: eventListeners) {
            listener.onNoticeReceived(evt);
        }
    }

    @Override
    public void onUserQuitNetwork(UserQuitNetworkEvent evt) {
        for (EventListener listener: eventListeners) {
            listener.onUserQuitNetwork(evt);
        }
    }

    @Override
    public void onChannelModeChange(ChannelModeChangeEvent evt) {
        for (EventListener listener: eventListeners) {
            listener.onChannelModeChange(evt);
        }
    }

    @Override
    public void onPingReceived(PingEvent evt) {
        for (EventListener listener: eventListeners) {
            listener.onPingReceived(evt);
        }
    }

    @Override
    public void onWelcomeReceived(WelcomeReceivedEvent evt) {
        for (EventListener listener: eventListeners) {
            listener.onWelcomeReceived(evt);
        }
    }

    @Override
    public void onSupportListReceived(SupportListReceivedEvent evt) {
        for (EventListener listener: eventListeners) {
            listener.onSupportListReceived(evt);
        }
    }

    @Override
    public void onConnected(ConnectedEvent evt) {
        for (EventListener listener: eventListeners) {
            listener.onConnected(evt);
        }
    }

    @Override
    public void onConnectionInterrupted(ConnectionInterruptedEvent evt) {
        for (EventListener listener: eventListeners) {
            listener.onConnectionInterrupted(evt);
        }
    }

    @Override
    public void onCommandTriggered(CommandTriggeredEvent evt) {
        for (EventListener listener: eventListeners) {
            listener.onCommandTriggered(evt);
        }
    }

    @Override
    public void onToggleMute(ToggleMuteEvent evt) {
        for (EventListener listener: eventListeners) {
            listener.onToggleMute(evt);
        }
    }

    @Override
    public void onAccessLevelChange(AccessLevelChangeEvent evt) {
        for (EventListener listener: eventListeners) {
            listener.onAccessLevelChange(evt);
        }
    }

    @Override
    public void onServerNotice(ServerNoticeEvent evt) {
        for (EventListener listener: eventListeners) {
            listener.onServerNotice(evt);
        }
    }
}
