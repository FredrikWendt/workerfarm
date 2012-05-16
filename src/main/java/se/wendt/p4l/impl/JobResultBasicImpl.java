package se.wendt.p4l.impl;

import java.io.Serializable;

import se.wendt.p4l.JobId;
import se.wendt.p4l.JobResult;

/**
 * Generic job result - make sure the result class is {@link Serializable}.
 */
public class JobResultBasicImpl<T> implements JobResult<T> {

	private static final long serialVersionUID = 1L;

	private final JobId jobId;
	private final T result;

	public JobResultBasicImpl(JobId jobId, T result) {
		makeSureResultIsSerializable(result);
		this.jobId = jobId;
		this.result = result;
	}

	@Override
	public String toString() {
		return JobResultBasicImpl.class.getSimpleName() + "[success," + jobId + "]";
	}

	private void makeSureResultIsSerializable(T result) {
		Class<? extends Object> clz = result.getClass();
		if (!Serializable.class.isAssignableFrom(clz)) {
			throw new IllegalArgumentException("Result class " + clz + " must be Serializable!");
		}
	}

	@Override
	public JobId getJobId() {
		return jobId;
	}

	@Override
	public boolean wasSuccessful() {
		return true;
	}

	@Override
	public T getResult() {
		return result;
	}

}
