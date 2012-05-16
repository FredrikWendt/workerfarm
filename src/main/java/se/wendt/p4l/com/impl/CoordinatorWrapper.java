package se.wendt.p4l.com.impl;

import se.wendt.p4l.Coordinator;
import se.wendt.p4l.Job;
import se.wendt.p4l.JobId;
import se.wendt.p4l.JobRequest;
import se.wendt.p4l.JobResult;
import se.wendt.p4l.WorkerId;
import se.wendt.p4l.com.Command;
import se.wendt.p4l.com.CommandType;
import se.wendt.p4l.com.MessageGatewayForCoordinator;
import se.wendt.p4l.com.ObjectHandler;

public class CoordinatorWrapper implements ObjectHandler {

	private final Coordinator<?> server;
	private final MessageGatewayForCoordinator gateway;

	public CoordinatorWrapper(Coordinator<?> server, MessageGatewayForCoordinator gateway) {
		this.server = server;
		this.gateway = gateway;
	}

	@Override
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void handle(Object object) {
		Command command = (Command) object;
		switch (command.getCommandType()) {
			case REQUEST_JOB:
				final WorkerId clientId = (WorkerId) command.getPayload();
				server.jobRequested(new JobRequest() {
					private static final long serialVersionUID = 1L;

					@Override
					public void offerJob(Job job) {
						gateway.sendMessage(clientId, new Command(CommandType.OFFER_JOB, job));
					}
					
					@Override
					public WorkerId getWorkerId() {
						return clientId;
					}
				});
				
			case JOB_ACCEPTED: 
				server.jobAccepted((JobId) command.getPayload());
				
			case JOB_FINISHED:
				server.jobFinished((JobResult) command.getPayload());
		}
	}

}
