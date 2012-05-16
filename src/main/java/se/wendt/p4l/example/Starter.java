package se.wendt.p4l.example;

import java.util.UUID;

import se.wendt.p4l.com.xmpp.XmppConfiguration;
import se.wendt.p4l.com.xmpp.XmppMessageCenter;
import se.wendt.p4l.impl.WorkerIdImpl;

/**
 * Example of how to start:
 * <ul>
 * <li>an XMPP server that pushes messages back and forth between coordinator and workers</li>
 * <li>an example coordinator</li>
 * <li>an example worker</li>
 * </ul>
 */
public class Starter {

	public static void main(String... args) throws Exception {

		if (args.length > 0) {
			XmppConfiguration configuration = new XmppConfiguration();

			if ("worker".equals(args[0])) {
				int numberOfWorkers = 1; 
				if (args.length > 1) {
					numberOfWorkers = Integer.parseInt(args[1]);
				}
				for (int i = 0; i < numberOfWorkers; i++) {
					String numberPart = Integer.toHexString(UUID.randomUUID().hashCode());
					String userName = "worker-" + numberPart;
					WorkerIdImpl workerId = new WorkerIdImpl(userName + "@" + configuration.getServerHostname());
					ExampleWorker worker = new ExampleWorker(workerId);
					XmppMessageCenter.setupWorker(configuration, worker);
					System.out.printf("worker %s/%s started\n", i, numberOfWorkers);
				}

			} else if ("coordinator".equals(args[0])) {
				XmppMessageCenter.setupCoordinator(configuration, new ExampleCoordinator());
				System.out.println("coordinator started");

			} else if ("server".equals(args[0])) {
				XmppMessageCenter.startServer(configuration);
				System.out.println("server started");

			}

		}
		if (args.length == 0) {
			System.out.println("Start with arg [server|coordinator|worker[ n]]");
			System.exit(-1);
		}

	}

}
