package se.wendt.p4l.example.server;

import se.wendt.p4l.com.MessageGatewayForServer;
import se.wendt.p4l.com.ObjectHandler;
import se.wendt.p4l.com.xmpp.XmppConfiguration;
import se.wendt.p4l.com.xmpp.VysperServer;
import se.wendt.p4l.com.xmpp.XmppMessageGateway;

public class ServerStarter {

	public static void main(String[] args) throws Exception {
		new VysperServer().start(new XmppConfiguration());
		ExampleServer server = new ExampleServer();
		MessageGatewayForServer gateway = XmppMessageGateway.server(new XmppConfiguration());
		ObjectHandler objectHandler = new ServerWrapper(server, gateway);
		gateway.setObjectHandler(objectHandler);
	}

}
