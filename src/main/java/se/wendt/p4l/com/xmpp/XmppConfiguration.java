package se.wendt.p4l.com.xmpp;

public class XmppConfiguration {

	public String getServerHostname() {
		String env = System.getenv("XMPP_HOST_NAME");
		if (env == null) {
			env = "localhost";
		}
		return env;
	}

	public String getCoordinatorUsername() {
		return "server";
	}

	public String getCoordinatorPassword() {
		return "server";
	}

	public String getServerJabberId() {
		return getCoordinatorUsername() + "@" + getServerHostname();
	}

}
