package se.wendt.p4l;

import java.io.Serializable;

public interface JobRequest extends Serializable {

	WorkerId getWorkerId();
	
	void offerJob(Job job);
	
}
