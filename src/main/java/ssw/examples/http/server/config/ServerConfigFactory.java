/**
 * 
 */
package ssw.examples.http.server.config;

import java.nio.file.Path;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ssw.examples.http.server.config.xml.SeverConfigFactoryXML;

/**
 * Factory to load server config from file
 * 
 * @author m.genova
 * @since 1.0.0
 */
public abstract class ServerConfigFactory {
	
	/**
	 * XXX change with new version of Singleton
	 * 
	 * Instance for Singleton.
	 * 
	 * Only one instance for RunTime context.
	 */
	protected static ServerConfig instance;
	private static Logger logger = LoggerFactory.getLogger(ServerConfigFactory.class);
	
	/**
	 * Gets a singleton instance of server config
	 * 
	 * @return
	 */
	public ServerConfig getServerConfig(Path path) {
		
		if(logger.isDebugEnabled()) {
			logger.debug("getServerConfig()");
			logger.debug("actual instance {}", instance);
		}
		
		if(instance == null) {
			constructServerConfig(path);
			
			if(logger.isDebugEnabled()) {
				logger.debug("created instance: {}", instance);
			}
		}
		
		return instance;
	}
	
	/**
	 * Loads from file
	 * 
	 * @param path
	 * @return
	 */
	protected abstract void constructServerConfig(Path path);
	
	/**
	 * Gets correct Factory for read file format
	 * 
	 * TODO implement mechanism to choose correct factory
	 * 
	 * @param path file to read
	 * @return Factory for read file format
	 */
	public static ServerConfigFactory choosefactory(Path path) {
		ServerConfigFactory factory = new SeverConfigFactoryXML();
		
		return factory;
	}
}
