package se.wendt.p4l.example;

import se.wendt.p4l.JobId;
import se.wendt.p4l.JobRequest;
import se.wendt.p4l.JobResult;
import se.wendt.p4l.Coordinator;

/**
 * Example of a simple coordinator.
 * <p>
 * The coordinator knows what jobs needs to be carried out. In this case, we have infinite
 * {@link ExampleJob}s to do.
 */
public class ExampleCoordinator implements Coordinator<String> {

	private int jobsRequested = 0;
	private int jobsOffered = 0;
	private int jobsAccepted = 0;
	private int jobsFinished = 0;

	public ExampleCoordinator() {
		new Thread(new Runnable() {

			@Override
			public void run() {
				while (true) {
					System.out.printf("Requested, offered, accepted, finished: %s, %s, %s\n", jobsRequested, jobsOffered, jobsAccepted,
							jobsFinished);
					try {
						Thread.sleep(2000);
					} catch (InterruptedException e) {
					}
				}
			}
		}).start();
	}

	@Override
	public void jobAccepted(JobId job) {
		jobsAccepted++;
	}

	@Override
	public void jobFinished(JobResult<String> job) {
		jobsFinished++;
		System.out.printf("Job finished: %s => %s\n", job, job.getResult());
	}

	@Override
	public void jobRequested(JobRequest request) {
		jobsRequested++;
		System.out.printf("Job requested by %s\n", request.getWorkerId());
		request.offerJob(new ExampleJob());
		jobsOffered++;
	}

}
