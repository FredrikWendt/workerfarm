package se.wendt.p4l;

import java.io.Serializable;

public interface JobOffer extends Serializable {

	Job getJob();
	
	void acceptJob();
	
}
