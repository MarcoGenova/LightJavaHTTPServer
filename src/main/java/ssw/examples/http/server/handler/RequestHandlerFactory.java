/**
 * 
 */
package ssw.examples.http.server.handler;

/**
 * Factory for Request Handler, all implementation are based on {@link InternalRequestHandler}
 * 
 * @author m.genova
 * @since 1.0
 */
public class RequestHandlerFactory {
	
	/**
	 * Gets request handler implementation
	 * 
	 * @param params request handler parameters
	 * @return request handler
	 */
	public static InternalRequestHandler getRequestHandler(String...params) {
		return new DefaultRequestHandler();
	}
}
