package se.wendt.p4l;


public interface CoordinatorHandle {

	void requestJob(WorkerId workerId);
	
	void jobAccepted(JobId jobId);
	
	void jobFinished(JobResult<?> jobResult);

}
