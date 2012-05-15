package se.wendt.p4l.example.server;

import se.wendt.p4l.JobId;
import se.wendt.p4l.JobRequest;
import se.wendt.p4l.JobResult;
import se.wendt.p4l.Server;
import se.wendt.p4l.example.ExampleJob;

public class ExampleServer implements Server {

	@Override
	public void jobAccepted(JobId job) {
		System.out.println("Job accepted: " + job);
	}

	@Override
	public void jobFinished(JobResult<?> job) {
		System.out.printf("Job (%s) finished: %s\n", job, job.getResult());
	}

	@Override
	public void jobRequested(JobRequest request) {
		request.offerJob(new ExampleJob());
	}

}
