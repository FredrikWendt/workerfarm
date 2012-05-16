package se.wendt.p4l.com.xmpp;

import org.apache.vysper.mina.TCPEndpoint;
import org.apache.vysper.storage.OpenStorageProviderRegistry;
import org.apache.vysper.storage.StorageProviderRegistry;
import org.apache.vysper.xmpp.addressing.Entity;
import org.apache.vysper.xmpp.authorization.UserAuthorization;
import org.apache.vysper.xmpp.modules.roster.persistence.MemoryRosterManager;
import org.apache.vysper.xmpp.server.XMPPServer;


public class VysperServer {

	public static void main(String... strings) throws Exception {
		XmppConfiguration conf = new XmppConfiguration();
		new VysperServer().start(conf);
	}

	public void start(XmppConfiguration conf) throws Exception {
		StorageProviderRegistry registry = new OpenStorageProviderRegistry();
		UserAuthorization reg = new UserAuthorization() {
			public boolean verifyCredentials(String arg0, String arg1, Object arg2) {
				System.out.printf("All string credentials are valid: %s %s %s\n", arg0, arg1, arg2);
				return true;
			}
			
			public boolean verifyCredentials(Entity arg0, String arg1, Object arg2) {
				System.out.println("All entity credentials are valid");
				return true;
			}
		};
		registry.add(reg);
        
		registry.add(new MemoryRosterManager());

		XMPPServer server = new XMPPServer(conf.getServerHostname());
        server.setStorageProviderRegistry(registry);

        server.addEndpoint(new TCPEndpoint());

        server.setTLSCertificateInfo(this.getClass().getResourceAsStream("/bogus_mina_tls.cert"), "boguspw");
		server.setStorageProviderRegistry(registry);
		server.start();
	}
}
