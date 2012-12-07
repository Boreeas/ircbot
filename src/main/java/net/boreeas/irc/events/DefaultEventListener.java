/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package net.boreeas.irc.events;

/**
 * A default event listener. Plugin-created event listeners are encouraged
 * to extend this class instead of implementing EventListener. Only the methods
 * required need to be overridden, by default, none of this methods do anything.
 * @author Boreeas
 */
public class DefaultEventListener implements EventListener {

    @Override
    public void onSelfJoinChannel(SelfJoinChannelEvent evt) {
    }

    @Override
    public void onSelfLeaveChannel(SelfLeaveChannelEvent evt) {
    }

    @Override
    public void onSendMessage(SendMessageEvent evt) {
    }

    @Override
    public void onSendNotice(SendMessageEvent evt) {
    }

    @Override
    public void onSelfDisconnected(SelfDisconnectedEvent evt) {
    }

    @Override
    public void onSelfModeChange(SelfModeChangeEvent evt) {
    }

    @Override
    public void onUserJoinedChannel(UserJoinedChannelEvent evt) {
    }

    @Override
    public void onUserLeftChannel(UserLeftChannelEvent evt) {
    }

    @Override
    public void onMessageReceived(MessageReceivedEvent evt) {
    }

    @Override
    public void onNoticeReceived(MessageReceivedEvent evt) {
    }

    @Override
    public void onUserQuitNetwork(UserQuitNetworkEvent evt) {
    }

    @Override
    public void onChannelModeChange(ChannelModeChangeEvent evt) {
    }

    @Override
    public void onCommandTriggered(CommandTriggeredEvent evt) {
    }

    @Override
    public void onToggleMute(ToggleMuteEvent evt) {
    }

    @Override
    public void onAccessLevelChange(AccessLevelChangeEvent evt) {
    }

    @Override
    public void onPingReceived(PingEvent evt) {
    }

    @Override
    public void onConnected(ConnectedEvent evt) {
    }

    @Override
    public void onWelcomeReceived(WelcomeReceivedEvent evt) {
    }

    @Override
    public void onSelfChangeChannelMode(SelfChangeChannelModeEvent evt) {
    }

    @Override
    public void onSupportListReceived(SupportListReceivedEvent evt) {
    }

    @Override
    public void onConnectionInterrupted(ConnectionInterruptedEvent evt) {
    }

    @Override
    public void onServerNotice(ServerNoticeEvent evt) {
    }

    @Override
    public void onUserChangedNick(UserChangedNickEvent evt) {
    }

    @Override
    public void onUnknownLine(UnknownLineEvent evt) {
    }

}
