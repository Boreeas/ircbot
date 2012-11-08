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
    void onSelfQuitNetwork(SelfQuitNetworkEvent evt);
    void onNetsplit(NetsplitEvent evt);
    void onSelfModeChange(SelfModeChangeEvent evt);

    void onOtherJoinedChannel(OtherJoinedChannelEvent evt);
    void onOtherLeftChannel(OtherLeftChannelEvent evt);
    void onMessageReceived(MessageReceivedEvent evt);
    void onNoticeReceived(MessageReceivedEvent evt);
    void onOtherQuitNetwork(OtherQuitNetworkEvent evt);
    void onChannelModeChange(ChannelModeChangeEvent evt);

    void onCommandTriggered(CommandTriggeredEvent evt);
    void onToggleMute(ToggleMuteEvent evt);
    void onAccessLevelChange(AccessLevelChangeEvent evt);
    void onPingReceived(PingEvent evt);
    void onConnected(ConnectedEvent evt);
    void onWelcomeReceived(WelcomeReceivedEvent evt);
}
