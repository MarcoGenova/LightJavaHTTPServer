/**
 * 
 */
package ssw.examples.http.server.handler;

import org.apache.http.HttpServerConnection;
import org.apache.http.protocol.HttpService;

/**
 * Create Terminal for thread
 * 
 * @author m.genova
 * @since 1.0
 */
public class InternalConnectionManagerFactory {
	
	/**
	 * Gets Terminal Thread to manage communications
	 * 
	 * @param httpservice
	 * @param conn
	 * @return
	 */
	static public InternalConnectionManager getThread(final HttpService httpservice,
            final HttpServerConnection conn) {
		return new InternalConnectionManager(httpservice, conn);
	} 
	
	
}
