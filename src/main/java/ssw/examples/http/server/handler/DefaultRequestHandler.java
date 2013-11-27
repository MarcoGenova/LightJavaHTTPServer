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
	
	private String identifier;
	
	@Override
	protected void manageRequest(HttpRequest request) {
		identifier = HandlerUtility.extractIdentifer(request, Boolean.TRUE);
	}

	@Override
	protected void prepareResponse(HttpResponse response) {
		response.setStatusCode(HttpStatus.SC_OK);
		ContentType contentType = ContentType.create("text/html", "ISO-8859-1");
		
		StringBuilder textualresponse = new StringBuilder("<b>PONG: ");
		textualresponse.append(identifier);
		textualresponse.append("</b>");
		
		StringEntity entity = new StringEntity(textualresponse.toString(), contentType);

        response.setEntity(entity);
	}

}
