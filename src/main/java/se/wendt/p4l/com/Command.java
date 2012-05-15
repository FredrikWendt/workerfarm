package se.wendt.p4l.com;

import java.io.Serializable;


public class Command implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private final CommandType commandType;
	private final Object payload;
	
	public Command(CommandType type, Object payload) {
		this.commandType = type;
		this.payload = payload;
	}

	public CommandType getCommandType() {
		return commandType;
	}

	public Object getPayload() {
		return payload;
	}

}
