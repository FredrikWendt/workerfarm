package se.wendt.p4l.com.xmpp;

public class XmppConfiguration {

	private final String hostName;
	
	public XmppConfiguration() {
		String env = System.getenv("XMPP_HOST_NAME");
		if (env == null) {
			env = "localhost";
		}
		hostName = env;
		System.out.println("Using XMPP_HOST_NAME=" + hostName);
	}

	public String getServerHostname() {
		return hostName;
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
