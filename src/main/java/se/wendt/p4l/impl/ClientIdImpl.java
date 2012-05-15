package se.wendt.p4l.impl;

import se.wendt.p4l.ClientId;

public class ClientIdImpl implements ClientId {

	private static final long serialVersionUID = 1L;

	private String id;

	public ClientIdImpl(String id) {
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
