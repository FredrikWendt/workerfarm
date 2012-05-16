package se.wendt.p4l.impl;

import se.wendt.p4l.JobId;

public class JobIdStringImpl implements JobId {

	private static final long serialVersionUID = 1L;
	
	private final String string;

	public JobIdStringImpl(String string) {
		this.string = string;
	}
	
	@Override
	public String toString() {
		return string;
	}
	
	@Override
	public int hashCode() {
		return string.hashCode();
	}

}
