package se.wendt.p4l;


/**
 * <ol>
 * <li>client: requests job(s)
 * <li>server: sends job(s)
 * <li>client: accepts job (and executes)
 * <li>client: finishes job - send results
 * </ol>
 */
public interface Client {

	void jobOffered(JobOffer job);
	
	void setServerHandle(ServerHandle serverHandle);

}
