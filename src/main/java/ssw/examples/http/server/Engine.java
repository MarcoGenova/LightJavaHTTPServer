/**
 * 
 */
package ssw.examples.http.server;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ssw.examples.http.server.context.ServerConfig;
import ssw.examples.http.server.service.ServiceFacory;
import ssw.examples.http.server.service.ServiceThread;

/**
 * Http Interceptor Engine
 *  
 * documentation: http://hc.apache.org/httpcomponents-core-4.3.x/tutorial/html/index.html
 * 
 * @author m.genova
 * @since 1.0
 */
public class Engine {
	private static Logger logger = LoggerFactory.getLogger(Engine.class);
	private ServerConfig serverConfig;
	private ServiceThread service;
	
	/**
	 * 
	 */
	public void start() {
		logger.info("Engine starting...");
		service = ServiceFacory.createService(serverConfig);
        service.start();
        logger.info("Engine started!");
	}
	
	public void stop() {
		logger.info("Engine stopping...");
		service.interrupt();
		logger.info("Engine stopped!");
	}

	/**
	 * @return the serverConfig
	 */
	public ServerConfig getServerConfig() {
		return serverConfig;
	}

	/**
	 * @param serverConfig the serverConfig to set
	 */
	public void setServerConfig(ServerConfig serverConfig) {
		this.serverConfig = serverConfig;
	}

	public static void main(String[] args) {
		ServerConfig config = new ServerConfig();
		config.setPort(9092);
		
		Engine engine = new Engine();
		engine.setServerConfig(config);
		
		engine.start();
	}
	
}
