package se.wendt.p4l.example.client;

import se.wendt.p4l.ClientId;
import se.wendt.p4l.JobId;
import se.wendt.p4l.JobResult;
import se.wendt.p4l.ServerHandle;
import se.wendt.p4l.com.Command;
import se.wendt.p4l.com.CommandType;
import se.wendt.p4l.com.MessageGatewayForClient;

public class ServerHandleWrapper implements ServerHandle {

	private MessageGatewayForClient bridge;

	public ServerHandleWrapper(MessageGatewayForClient bridge) {
		this.bridge = bridge;
	}

	@Override
	public void requestJob(ClientId clientId) {
		bridge.sendMessage(new Command(CommandType.REQUEST_JOB, clientId));
	}

	@Override
	public void jobAccepted(JobId jobId) {
		bridge.sendMessage(new Command(CommandType.JOB_ACCEPTED, jobId));
	}

	@Override
	public void jobFinished(JobResult<?> jobResult) {
		bridge.sendMessage(new Command(CommandType.JOB_FINISHED, jobResult));
	}

}
