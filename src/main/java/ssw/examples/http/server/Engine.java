/**
 * 
 */
package ssw.examples.http.server;

import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ssw.examples.http.server.config.ServerConfig;
import ssw.examples.http.server.config.ServerConfigFactory;
import ssw.examples.http.server.service.ServiceThreadFactory;
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
		service = ServiceThreadFactory.createService(serverConfig);
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

	public static void main(String[] args)  {
		
		if(args.length == 0) {
			getUsage();
			System.exit(1);
		}
		
		FileSystem fileSystem = FileSystems.getDefault();
		Path path = fileSystem.getPath(args[0]);
		
		if(Files.notExists(path)) {
			getUsage();
			System.exit(2);
		}
		
		ServerConfigFactory factory = ServerConfigFactory.choosefactory(path);
		ServerConfig config = factory.getServerConfig(path);
		
		Engine engine = new Engine();
		engine.setServerConfig(config);
		engine.start();
	}
	

	private static void getUsage() {
		System.err.println("USAGE: java <optional-jvm> Engine <server-conf-file>");
		System.err.println();
		System.err.println();
		System.err.println("<server-conf-file>: xml file of configuration, see unit tests");
	}
}
