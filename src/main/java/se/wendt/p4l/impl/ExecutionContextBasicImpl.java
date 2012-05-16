package se.wendt.p4l.impl;

import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

import se.wendt.p4l.Worker;
import se.wendt.p4l.ExecutionContext;
import se.wendt.p4l.JobResult;
import se.wendt.p4l.JobResultPublishedListener;

public class ExecutionContextBasicImpl implements ExecutionContext {

	private final Worker client;
	private Set<JobResultPublishedListener> listeners = new CopyOnWriteArraySet<JobResultPublishedListener>();

	public ExecutionContextBasicImpl(Worker client) {
		this.client = client;
	}

	public ExecutionContextBasicImpl(Worker client, JobResultPublishedListener jobResultPublishedListener) {
		this(client);
		listeners.add(jobResultPublishedListener);
	}

	@Override
	public Worker getExecutingWorker() {
		return client;
	}

	@Override
	public void publishJobResult(JobResult<?> jobResult) {
		for (JobResultPublishedListener listener : listeners) {
			listener.resultPublished(jobResult);
		}
	}

	@Override
	public void addJobResultPublishedListener(JobResultPublishedListener listener) {
		listeners.add(listener);
	}

}
