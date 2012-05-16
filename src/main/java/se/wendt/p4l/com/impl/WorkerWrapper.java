package se.wendt.p4l.com.impl;

import se.wendt.p4l.Worker;
import se.wendt.p4l.Job;
import se.wendt.p4l.JobOffer;
import se.wendt.p4l.com.Command;
import se.wendt.p4l.com.CommandType;
import se.wendt.p4l.com.MessageGatewayForWorker;
import se.wendt.p4l.com.ObjectHandler;

public class WorkerWrapper implements ObjectHandler {

	private final Worker worker;
	private final MessageGatewayForWorker gateway;

	public WorkerWrapper(Worker worker, MessageGatewayForWorker gateway) {
		this.worker = worker;
		this.gateway = gateway;
	}

	@Override
	public void handle(Object object) {
		Command command = (Command) object;
		switch (command.getCommandType()) {
			case OFFER_JOB:
				final Job job = (Job) command.getPayload();
				worker.jobOffered(new JobOffer() {
					private static final long serialVersionUID = 1L;

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
