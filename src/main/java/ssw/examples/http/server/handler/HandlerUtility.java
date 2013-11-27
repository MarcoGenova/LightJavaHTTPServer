/**
 * 
 */
package ssw.examples.http.server.handler;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpRequest;
import org.apache.http.RequestLine;

/**
 * @author m.genova
 * @since 1.0
 */
public class HandlerUtility {

	public static final String URI_SEPARATOR = "/";
	public static final String URI_PARAM_SEPARATOR = "?";

	/**
	 * 
	 * @param request
	 * @param removeParams
	 * @return
	 * 
	 * @since 1.0
	 */
	public static String extractIdentifer(HttpRequest request, Boolean removeParams) {
		String identifier = StringUtils.EMPTY;

		if (request != null) {
			RequestLine requestLine = request.getRequestLine();
			String uri = requestLine.getUri();
			int lastIndexOf = StringUtils.lastIndexOf(uri, URI_SEPARATOR);
			
			if(lastIndexOf > -1) {
				identifier = uri.substring(lastIndexOf + 1, uri.length());
				
				if(removeParams) {
					lastIndexOf = StringUtils.lastIndexOf(identifier, URI_PARAM_SEPARATOR);
					
					if(lastIndexOf > -1) {
						identifier = identifier.substring(0, lastIndexOf);
					}
				}
			}
		}

		return identifier;
	}
}
