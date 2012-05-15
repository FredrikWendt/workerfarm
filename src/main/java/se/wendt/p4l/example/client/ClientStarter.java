package se.wendt.p4l.example.client;

import java.util.UUID;

import se.wendt.p4l.com.MessageGatewayForClient;
import se.wendt.p4l.com.ObjectHandler;
import se.wendt.p4l.com.xmpp.XmppConfiguration;
import se.wendt.p4l.com.xmpp.XmppMessageGateway;
import se.wendt.p4l.impl.ClientIdImpl;

public class ClientStarter {

	public static void main(String... args) {
		XmppConfiguration config = new XmppConfiguration();
		String numberPart = Integer.toHexString(UUID.randomUUID().hashCode());
		String userName = "worker-"  + numberPart ;
		MessageGatewayForClient bridge = XmppMessageGateway.client(config, userName, userName);

		ServerHandleWrapper serverHandle = new ServerHandleWrapper(bridge);
		ClientIdImpl cid = new ClientIdImpl(userName+ "@" + config.getServerHostname());
		ExampleWorker worker = new ExampleWorker(cid);

		ObjectHandler objectHandler = new ClientWrapper(worker, bridge);
		bridge.setObjectHandler(objectHandler);
		
		worker.setServerHandle(serverHandle);
	}

}
