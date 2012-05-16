package se.wendt.p4l;

public interface ExecutionContext {

	Worker getExecutingWorker();
	
	void publishJobResult(JobResult<?> jobResult);
	
	void addJobResultPublishedListener(JobResultPublishedListener listener);
	
}
