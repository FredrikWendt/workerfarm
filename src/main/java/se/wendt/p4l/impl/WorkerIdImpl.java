package se.wendt.p4l.impl;

import se.wendt.p4l.WorkerId;

public class WorkerIdImpl implements WorkerId {

	private static final long serialVersionUID = 1L;

	private String id;

	public WorkerIdImpl(String id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return id;
	}

	@Override
	public int hashCode() {
		return id.hashCode();
	}

}
