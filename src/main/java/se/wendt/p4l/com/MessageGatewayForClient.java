package se.wendt.p4l.com;


public interface MessageGatewayForClient {

	void sendMessage(Object message);
	
	void setObjectHandler(ObjectHandler handler);

}
