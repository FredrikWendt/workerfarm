package se.wendt.p4l.impl;

import se.wendt.p4l.JobId;
import se.wendt.p4l.JobResult;

public class FailedJobResult implements JobResult<Throwable> {

	private static final long serialVersionUID = 1L;
	private final JobId jobId;
	private final Throwable cause;

	public FailedJobResult(JobId jobId, Throwable cause) {
		this.jobId = jobId;
		this.cause = cause;
	}

	@Override
	public JobId getJobId() {
		return jobId;
	}

	@Override
	public boolean wasSuccessful() {
		return false;
	}

	@Override
	public Throwable getResult() {
		return cause;
	}

}
