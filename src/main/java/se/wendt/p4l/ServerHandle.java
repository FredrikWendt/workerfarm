package se.wendt.p4l;


public interface ServerHandle {

	void requestJob(ClientId clientId);
	
	void jobAccepted(JobId jobId);
	
	void jobFinished(JobResult<?> jobResult);

}
