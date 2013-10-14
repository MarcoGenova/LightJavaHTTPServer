/**
 * 
 */
package ssw.examples.http.server.service;

import java.io.IOException;

import javax.net.ssl.SSLServerSocketFactory;

import org.apache.http.impl.DefaultBHttpServerConnectionFactory;
import org.apache.http.protocol.HttpService;

/**
 * @author m.genova
 * @since 1.0
 */
public class SecuredServiceThread extends ServiceThread {

	/**
	 * 
	 * @param port
	 * @param httpService
	 * @param sslServerSocketFactory
	 * @throws IOException
	 */
	public SecuredServiceThread(final int port, final HttpService httpService,
			final SSLServerSocketFactory sslServerSocketFactory)
			throws IOException {
		super(port, httpService);
		this.serversocket = sslServerSocketFactory.createServerSocket(this.port);
	}
	
	@Override
	public void initialize() throws IOException {
		this.connFactory = DefaultBHttpServerConnectionFactory.INSTANCE;
	}
}
