/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package net.boreeas.irc.events;

/**
 *
 * @author Boreeas
 */
public class EventListener {

    public void onSelfJoinChannel(SelfJoinChannelEvent evt)  {}
    public void onSelfLeaveChannel(SelfLeaveChannelEvent evt)  {}
    public void onSendMessage(SendMessageEvent evt)  {}
    public void onSendNotice(SendMessageEvent evt)  {}
    public void onSelfDisconnected(SelfDisconnectedEvent evt)  {}
    public void onSelfModeChange(SelfModeChangeEvent evt)  {}
    public void onSelfChangeChannelMode(SelfChangeChannelModeEvent evt)  {}

    public void onUserJoinedChannel(UserJoinedChannelEvent evt)  {}
    public void onUserLeftChannel(UserLeftChannelEvent evt)  {}
    public void onMessageReceived(MessageReceivedEvent evt)  {}
    public void onNoticeReceived(MessageReceivedEvent evt)  {}
    public void onUserQuitNetwork(UserQuitNetworkEvent evt)  {}
    public void onUserChangedNick(UserChangedNickEvent evt)  {}
    public void onChannelModeChange(ChannelModeChangeEvent evt)  {}
    public void onNamesReceived(NamesReceivedEvent evt) {}

    public void onPingReceived(PingEvent evt)  {}
    public void onWelcomeReceived(WelcomeReceivedEvent evt)  {}
    public void onSupportListReceived(SupportListReceivedEvent evt)  {}
    public void onConnected(ConnectedEvent evt)  {}
    public void onConnectionInterrupted(ConnectionInterruptedEvent evt)  {}
    public void onServerNotice(ServerNoticeEvent evt)  {}
    public void onCommandTriggered(CommandTriggeredEvent evt)  {}
    public void onToggleMute(ToggleMuteEvent evt)  {}
    public void onAccessLevelChange(AccessLevelChangeEvent evt)  {}

    public void onUnknownLine(UnknownLineEvent evt)  {}
}
