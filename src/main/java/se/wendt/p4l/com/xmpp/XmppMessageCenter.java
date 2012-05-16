package se.wendt.p4l.com.xmpp;

import se.wendt.p4l.Coordinator;
import se.wendt.p4l.Worker;
import se.wendt.p4l.com.ObjectHandler;
import se.wendt.p4l.com.impl.CoordinatorHandleWrapper;
import se.wendt.p4l.com.impl.CoordinatorWrapper;
import se.wendt.p4l.com.impl.WorkerWrapper;
import se.wendt.p4l.com.xmpp.XmppConfiguration;
import se.wendt.p4l.com.xmpp.XmppMessageGateway;

public class XmppMessageCenter {

	public static void startServer(XmppConfiguration configuration) throws Exception {
		VysperServer server = new VysperServer();
		server.start(configuration);
	}

	public static void setupCoordinator(XmppConfiguration configuration, Coordinator<?> server) {
		XmppMessageGateway gateway = XmppMessageGateway.server(configuration);
		ObjectHandler objectHandler = new CoordinatorWrapper(server, gateway);
		gateway.setObjectHandler(objectHandler);
	}

	public static void setupWorker(XmppConfiguration configuration, Worker worker) {
		String userName = worker.getWorkerId().toString();
		XmppMessageGateway gateway = XmppMessageGateway.client(configuration, userName, userName);
		ObjectHandler clientWrapper = new WorkerWrapper(worker, gateway);
		gateway.setObjectHandler(clientWrapper);
		worker.setCoordinatorHandle(new CoordinatorHandleWrapper(gateway));
	}

}
