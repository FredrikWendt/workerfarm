package se.wendt.p4l.com.impl;

import se.wendt.p4l.CoordinatorHandle;
import se.wendt.p4l.JobId;
import se.wendt.p4l.JobResult;
import se.wendt.p4l.WorkerId;
import se.wendt.p4l.com.Command;
import se.wendt.p4l.com.CommandType;
import se.wendt.p4l.com.MessageGatewayForWorker;

/**
 * Knows what send over the wire (using the message gateway), in order to express the intents/methods/signals
 * defined in {@link CoordinatorHandle}.
 */
public class CoordinatorHandleWrapper implements CoordinatorHandle {

	private MessageGatewayForWorker gateway;

	public CoordinatorHandleWrapper(MessageGatewayForWorker bridge) {
		this.gateway = bridge;
	}

	@Override
	public void requestJob(WorkerId clientId) {
		gateway.sendMessage(new Command(CommandType.REQUEST_JOB, clientId));
	}

	@Override
	public void jobAccepted(JobId jobId) {
		gateway.sendMessage(new Command(CommandType.JOB_ACCEPTED, jobId));
	}

	@Override
	public void jobFinished(JobResult<?> jobResult) {
		gateway.sendMessage(new Command(CommandType.JOB_FINISHED, jobResult));
	}

}
