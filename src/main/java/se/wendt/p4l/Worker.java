package se.wendt.p4l;

public interface Worker {

	WorkerId getWorkerId();
	
	void jobOffered(JobOffer jobOffer);
	
	void setCoordinatorHandle(CoordinatorHandle serverHandle);

}
