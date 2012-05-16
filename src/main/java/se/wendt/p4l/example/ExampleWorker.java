package se.wendt.p4l.example;

import se.wendt.p4l.CoordinatorHandle;
import se.wendt.p4l.JobOffer;
import se.wendt.p4l.JobResult;
import se.wendt.p4l.JobResultPublishedListener;
import se.wendt.p4l.Worker;
import se.wendt.p4l.WorkerId;
import se.wendt.p4l.impl.ExecutionContextBasicImpl;

public class ExampleWorker implements Worker {

	private final WorkerId workerId;
	private CoordinatorHandle coordinatorHandle;

	private int jobsRequested = 0;
	private int jobsOffered = 0;
	private int jobsAccepted = 0;
	private int jobsExecuted = 0;
	private int jobsFinished = 0;

	public ExampleWorker(WorkerId clientId) {
		this.workerId = clientId;
		new Thread(new Runnable() {
			@Override
			public void run() {
				while (true) {
					System.out.printf("Jobs offered, accepted, executed, finished: %s, %s, %s, %s, %s\n", jobsRequested, jobsOffered,
							jobsAccepted, jobsExecuted, jobsFinished);
					try {
						Thread.sleep(2000);
					} catch (InterruptedException e) {
					}
				}
			}
		}).start();
	}

	@Override
	public WorkerId getWorkerId() {
		return workerId;
	}

	@Override
	public String toString() {
		return ExampleWorker.class.getSimpleName() + "[" + workerId + "]";
	}

	@Override
	public void jobOffered(final JobOffer job) {
		jobsOffered++;
		job.acceptJob();
		jobsAccepted++;
		new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
				}
				requestNextJob();
				jobsExecuted++;
				job.getJob().execute(
						new ExecutionContextBasicImpl(ExampleWorker.this, new JobResultPublishedListener() {
							@Override
							public void resultPublished(JobResult<?> jobResult) {
								coordinatorHandle.jobFinished(jobResult);
							}
						}));
				jobsFinished++;
			}
		}).start();
	}

	private void requestNextJob() {
		new Thread(new Runnable() {
			@Override
			public void run() {
				coordinatorHandle.requestJob(workerId);
				jobsRequested ++;
			}
		}).start();
	}

	@Override
	public void setCoordinatorHandle(final CoordinatorHandle coordinatorHandle) {
		this.coordinatorHandle = coordinatorHandle;
		requestNextJob();
	}

}
