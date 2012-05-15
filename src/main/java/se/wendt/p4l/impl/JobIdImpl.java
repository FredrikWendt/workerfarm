package se.wendt.p4l.impl;

import se.wendt.p4l.JobId;

public class JobIdImpl implements JobId {

	private static final long serialVersionUID = 1L;
	
	private String id;

	public JobIdImpl(long id) {
		this.id = Long.toHexString(id);
	}
	
	@Override
	public String toString() {
		return "id:" + id;
	}
	
	@Override
	public int hashCode() {
		return id.hashCode();
	}

}
