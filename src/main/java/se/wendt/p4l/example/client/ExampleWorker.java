package se.wendt.p4l.example.client;

import se.wendt.p4l.Client;
import se.wendt.p4l.ClientId;
import se.wendt.p4l.JobOffer;
import se.wendt.p4l.JobResult;
import se.wendt.p4l.JobResultPublishedListener;
import se.wendt.p4l.ServerHandle;
import se.wendt.p4l.impl.ExecutionContextBasicImpl;

public class ExampleWorker implements Client {

	private final ClientId clientId;
	private ServerHandle serverHandle;

	public ExampleWorker(ClientId clientId) {
		this.clientId = clientId;
	}
	
	@Override
	public String toString() {
		return ExampleWorker.class.getSimpleName() + "[" + clientId + "]";
	}

	@Override
	public void jobOffered(final JobOffer job) {
		System.out.println("offered: " + job);
		job.acceptJob();
		new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
				}
				requestNextJob();
				job.getJob().execute(new ExecutionContextBasicImpl(ExampleWorker.this, new JobResultPublishedListener() {
					@Override
					public void resultPublished(JobResult<?> jobResult) {
						serverHandle.jobFinished(jobResult);
					}
				}));
			}
		}).start();
	}

	private void requestNextJob() {
		new Thread(new Runnable() {
			@Override
			public void run() {
				serverHandle.requestJob(clientId);
			}
		}).start();
	}
	
	@Override
	public void setServerHandle(final ServerHandle serverHandle) {
		this.serverHandle = serverHandle;
		serverHandle.requestJob(clientId);
	}

}
