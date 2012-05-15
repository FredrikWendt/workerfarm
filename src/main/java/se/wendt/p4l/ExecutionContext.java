package se.wendt.p4l;

public interface ExecutionContext {

	Client getExecutingClient();
	
	void publishJobResult(JobResult<?> jobResult);
	
	void addJobResultPublishedListener(JobResultPublishedListener listener);
	
}
