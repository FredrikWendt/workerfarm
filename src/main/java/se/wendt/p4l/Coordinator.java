package se.wendt.p4l;

/**
 * This is what you need to implement to serve jobs.
 */
public interface Coordinator<R> {

	void jobRequested(JobRequest request);

	void jobAccepted(JobId job);

	void jobFinished(JobResult<R> result);

}
