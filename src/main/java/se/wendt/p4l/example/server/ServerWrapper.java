package se.wendt.p4l.example.server;

import se.wendt.p4l.ClientId;
import se.wendt.p4l.Job;
import se.wendt.p4l.JobId;
import se.wendt.p4l.JobRequest;
import se.wendt.p4l.JobResult;
import se.wendt.p4l.Server;
import se.wendt.p4l.com.Command;
import se.wendt.p4l.com.CommandType;
import se.wendt.p4l.com.MessageGatewayForServer;
import se.wendt.p4l.com.ObjectHandler;

public class ServerWrapper implements ObjectHandler {

	private final Server server;
	private final MessageGatewayForServer gateway;

	public ServerWrapper(Server server, MessageGatewayForServer gateway) {
		this.server = server;
		this.gateway = gateway;
	}

	@Override
	public void handle(Object object) {
		Command command = (Command) object;
		switch (command.getCommandType()) {
			case REQUEST_JOB:
				final ClientId clientId = (ClientId) command.getPayload();
				server.jobRequested(new JobRequest() {
					@Override
					public void offerJob(Job job) {
						gateway.sendMessage(clientId, new Command(CommandType.OFFER_JOB, job));
					}
					
					@Override
					public ClientId getClientId() {
						return clientId;
					}
				});
				
			case JOB_ACCEPTED: 
				server.jobAccepted((JobId) command.getPayload());
				
			case JOB_FINISHED:
				server.jobFinished((JobResult<?>) command.getPayload());
		}
	}

}
