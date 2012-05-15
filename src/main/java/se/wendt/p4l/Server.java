package se.wendt.p4l;

/**
 * This is what you need to implement to serve jobs.
 */
public interface Server {

	void jobRequested(JobRequest request);

	void jobAccepted(JobId job);

	void jobFinished(JobResult<?> result);

}
