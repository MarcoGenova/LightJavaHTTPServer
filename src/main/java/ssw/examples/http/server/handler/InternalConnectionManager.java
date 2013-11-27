/**
 * 
 */
package ssw.examples.http.server.handler;

import java.io.IOException;

import org.apache.http.ConnectionClosedException;
import org.apache.http.HttpException;
import org.apache.http.HttpServerConnection;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;
import org.apache.http.protocol.HttpService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author m.genova
 * @since 1.0
 */
public class InternalConnectionManager extends Thread {
	private Logger logger = LoggerFactory.getLogger(InternalConnectionManager.class);
	private final HttpService httpservice;
    private final HttpServerConnection conn;
	
    public InternalConnectionManager(
            final HttpService httpservice,
            final HttpServerConnection conn) {
        super();
        this.httpservice = httpservice;
        this.conn = conn;
    }

    
	@Override
	public void run() {
		logger.trace("New connection thread");
        HttpContext context = new BasicHttpContext();
        
        try {
            while (!Thread.interrupted() && this.conn.isOpen()) {
                this.httpservice.handleRequest(this.conn, context);
            }
        } catch (ConnectionClosedException ex) {
        	logger.error ("Client closed connection");
        } catch (IOException ex) {
        	logger.error ("I/O error: {}", ex.getMessage());
        } catch (HttpException ex) {
        	logger.error ("Unrecoverable HTTP protocol violation: {}", ex.getMessage());
        } finally {
            try {
                this.conn.shutdown();
            } catch (IOException ignore) {
            	//NOP
            }
        }
	}
}
