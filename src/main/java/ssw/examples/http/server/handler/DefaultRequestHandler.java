/**
 * 
 */
package ssw.examples.http.server.handler;

import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;

/**
 * Default simple request handler
 * 
 * @author m.genova
 * @since 1.0
 */
public class DefaultRequestHandler extends InternalRequestHandler {

	@Override
	protected void manageRequest(HttpRequest request) {
		// FAKE
	}

	@Override
	protected void prepareResponse(HttpResponse response) {
		// TODO Auto-generated method stub
		response.setStatusCode(HttpStatus.SC_OK);
        StringEntity entity = new StringEntity(
                "LOGOFFALL",
        ContentType.create("plain/text", "ISO-8859-1"));
        response.setEntity(entity);
		
	}

}
