/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package net.boreeas.irc.events;

/**
 *
 * @author Boreeas
 */
public interface EventListener {

    void onSelfJoinChannel(SelfJoinChannelEvent evt);
    void onSelfLeaveChannel(SelfLeaveChannelEvent evt);
    void onSendMessage(SendMessageEvent evt);
    void onSendNotice(SendMessageEvent evt);
    void onSelfDisconnected(SelfDisconnectedEvent evt);
    void onSelfModeChange(SelfModeChangeEvent evt);
    void onSelfChangeChannelMode(SelfChangeChannelModeEvent evt);

    void onUserJoinedChannel(UserJoinedChannelEvent evt);
    void onUserLeftChannel(UserLeftChannelEvent evt);
    void onMessageReceived(MessageReceivedEvent evt);
    void onNoticeReceived(MessageReceivedEvent evt);
    void onUserQuitNetwork(UserQuitNetworkEvent evt);
    void onChannelModeChange(ChannelModeChangeEvent evt);

    void onPingReceived(PingEvent evt);
    void onWelcomeReceived(WelcomeReceivedEvent evt);
    void onSupportListReceived(SupportListReceivedEvent evt);
    void onConnected(ConnectedEvent evt);
    void onConnectionInterrupted(ConnectionInterruptedEvent evt);
    void onServerNotice(ServerNoticeEvent evt);
    void onCommandTriggered(CommandTriggeredEvent evt);
    void onToggleMute(ToggleMuteEvent evt);
    void onAccessLevelChange(AccessLevelChangeEvent evt);
}
