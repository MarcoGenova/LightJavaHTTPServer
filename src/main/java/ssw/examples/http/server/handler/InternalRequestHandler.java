/**
 * 
 */
package ssw.examples.http.server.handler;

import java.io.IOException;

import org.apache.http.HttpException;
import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.http.protocol.HttpContext;
import org.apache.http.protocol.HttpRequestHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This components manages the communication with client
 *  
 * @author m.genova
 * @since 1.0
 */
public abstract class InternalRequestHandler implements HttpRequestHandler {
	
	private static Logger logger = LoggerFactory.getLogger(InternalRequestHandler.class);
			
	public void handle(HttpRequest arg0, HttpResponse arg1, HttpContext arg2)
			throws HttpException, IOException {
		logger.debug("handle({},{},{})", arg0, arg1, arg2);
		
		manageRequest(arg0);
		prepareResponse(arg1);
	}

	protected abstract void manageRequest(HttpRequest request);
	
	protected abstract void prepareResponse(HttpResponse response);

}
