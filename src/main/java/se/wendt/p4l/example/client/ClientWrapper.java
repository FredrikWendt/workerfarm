package se.wendt.p4l.example.client;

import se.wendt.p4l.Client;
import se.wendt.p4l.Job;
import se.wendt.p4l.JobOffer;
import se.wendt.p4l.com.Command;
import se.wendt.p4l.com.CommandType;
import se.wendt.p4l.com.MessageGatewayForClient;
import se.wendt.p4l.com.ObjectHandler;

public class ClientWrapper implements ObjectHandler {

	private final Client client;
	private final MessageGatewayForClient gateway;

	public ClientWrapper(Client client, MessageGatewayForClient gateway) {
		this.client = client;
		this.gateway = gateway;
	}

	@Override
	public void handle(Object object) {
		Command command = (Command) object;
		switch (command.getCommandType()) {
			case OFFER_JOB:
			 final Job job = (Job) command.getPayload();
				client.jobOffered(new JobOffer() {
					@Override
					public String toString() {
						return "JobOffer[" + job + "]";
					}
					
					@Override
					public Job getJob() {
						return job;
					}
					
					@Override
					public void acceptJob() {
						gateway.sendMessage(new Command(CommandType.JOB_ACCEPTED, job.getJobId()));
					}
				});
				return;
			default:
				System.out.println("oops");
		}
	}

}
