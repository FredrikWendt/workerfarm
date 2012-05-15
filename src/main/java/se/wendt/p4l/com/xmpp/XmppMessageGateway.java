package se.wendt.p4l.com.xmpp;

import org.jivesoftware.smack.Chat;
import org.jivesoftware.smack.ChatManager;
import org.jivesoftware.smack.ChatManagerListener;
import org.jivesoftware.smack.Connection;
import org.jivesoftware.smack.ConnectionConfiguration;
import org.jivesoftware.smack.ConnectionListener;
import org.jivesoftware.smack.MessageListener;
import org.jivesoftware.smack.XMPPConnection;
import org.jivesoftware.smack.XMPPException;
import org.jivesoftware.smack.packet.Message;

import se.wendt.p4l.ClientId;
import se.wendt.p4l.com.MessageGatewayForClient;
import se.wendt.p4l.com.MessageGatewayForServer;
import se.wendt.p4l.com.ObjectHandler;
import se.wendt.p4l.impl.ClientIdImpl;

public class XmppMessageGateway implements ConnectionListener, ChatManagerListener, MessageListener, MessageGatewayForServer, MessageGatewayForClient {

	private ChatManager chatManager;
	private Connection connection;
	private final String serverJabberId;
	private final ClientIdImpl serverId;
	private ObjectHandler objectHandler;

	public static XmppMessageGateway server(XmppConfiguration config) {
		return new XmppMessageGateway(config, config.getCoordinatorUsername(), config.getCoordinatorPassword());
	}
	
	public static XmppMessageGateway client(XmppConfiguration config, String user, String pass) {
		return new XmppMessageGateway(config, user, pass);
	}
	
	private XmppMessageGateway(XmppConfiguration config, String user, String pass) {
		this.serverJabberId = config.getServerJabberId();
		this.serverId = new ClientIdImpl(serverJabberId);
		try {
			this.setupConnection(config.getServerHostname(), user, pass);
		} catch (XMPPException e) {
			e.printStackTrace();
		}
	}

	private void setupConnection(String server, String user, String pass) throws XMPPException {
		ConnectionConfiguration connectionConfiguration = new ConnectionConfiguration(server);
		connectionConfiguration.setDebuggerEnabled(true);
		connectionConfiguration.setReconnectionAllowed(true);
		connectionConfiguration.setRosterLoadedAtLogin(false);
		connectionConfiguration.setSendPresence(false);

		disableSecurityChecks(connectionConfiguration);
		Connection c = new XMPPConnection(connectionConfiguration);
		c.connect();
		c.login(user, pass);
		c.addConnectionListener(this);
		connection = c;
		chatManager = c.getChatManager();
		chatManager.addChatListener(this);
	}

	private void disableSecurityChecks(ConnectionConfiguration connectionConfiguration) {
		connectionConfiguration.setExpiredCertificatesCheckEnabled(false);
		connectionConfiguration.setNotMatchingDomainCheckEnabled(false);
		connectionConfiguration.setSelfSignedCertificateEnabled(false);
		connectionConfiguration.setVerifyChainEnabled(false);
		connectionConfiguration.setVerifyRootCAEnabled(false);
	}

	// -- connectionlistner

	@Override
	public void connectionClosed() {
		System.out.println("connection closed");
	}

	@Override
	public void connectionClosedOnError(Exception e) {
		System.out.println("connection closed on error");
	}

	@Override
	public void reconnectingIn(int seconds) {
		System.out.println("reconnecting in " + seconds);
	}

	@Override
	public void reconnectionSuccessful() {
		System.out.println("reconnection successful");
	}

	@Override
	public void reconnectionFailed(Exception e) {
		System.out.println("reconnection failed");
	}

	// -- chat manager

	@Override
	public void chatCreated(Chat chat, boolean createdLocally) {
		if (!createdLocally) {
			chat.addMessageListener(this);
			try {
				chat.sendMessage("welcome");
			} catch (XMPPException e) {
				e.printStackTrace();
			}
		}
	}

	// -- message listener

	@Override
	public void processMessage(Chat chat, Message message) {
		Object object = message.getProperty("object");
		if (objectHandler == null) {
			System.out.println("No object handler set");
		} else {
			objectHandler.handle(object);
		}
	}
	
	public void setObjectHandler(ObjectHandler objectHandler) {
		this.objectHandler = objectHandler;
	}

	@Override
	public void sendMessage(ClientId clientId, Object message) {
		Message msg = new Message(clientId.toString());
		msg.setProperty("object", message);
		connection.sendPacket(msg);
	}

	@Override
	public void sendMessage(Object message) {
		sendMessage(serverId, message);
	}

}
