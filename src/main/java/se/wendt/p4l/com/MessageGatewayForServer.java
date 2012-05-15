package se.wendt.p4l.com;

import se.wendt.p4l.ClientId;

public interface MessageGatewayForServer {
	
	void sendMessage(ClientId client, Object message);

	void setObjectHandler(ObjectHandler objectHandler);
	
}
