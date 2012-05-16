package se.wendt.p4l.com;

import se.wendt.p4l.WorkerId;

public interface MessageGatewayForCoordinator {
	
	void sendMessage(WorkerId worker, Object message);

}
