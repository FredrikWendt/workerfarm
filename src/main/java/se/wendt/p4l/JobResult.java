package se.wendt.p4l;

import java.io.Serializable;

public interface JobResult<T> extends Serializable {
	
	/**
	 * Returns the id of the {@link Job} that generated this {@link JobResult}.
	 */
	JobId getJobId();

	boolean wasSuccessful();
	
	T getResult();
	
}
