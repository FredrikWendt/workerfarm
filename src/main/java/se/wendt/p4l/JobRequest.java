package se.wendt.p4l;

public interface JobRequest {

	ClientId getClientId();
	
	void offerJob(Job job);
	
}
