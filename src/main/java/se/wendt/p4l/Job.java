package se.wendt.p4l;

import java.io.Serializable;

public interface Job extends Serializable {
	
	/**
	 * ID for this job.
	 */
	JobId getJobId();
	
	/**
	 * Runs the job, returning a JobResult.
	 * <p>
	 * This should never terminate unexpectedly.
	 */
	void execute(ExecutionContext executionContext);

}
