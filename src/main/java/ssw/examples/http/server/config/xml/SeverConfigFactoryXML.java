/**
 * 
 */
package ssw.examples.http.server.config.xml;

import java.io.File;
import java.nio.file.Path;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ssw.examples.http.server.config.ServerConfig;
import ssw.examples.http.server.config.ServerConfigFactory;

/**
 * Factory to load server config from XML file
 * 
 * @author m.genova
 * @since 1.0.0
 */
public class SeverConfigFactoryXML extends ServerConfigFactory {
	
	private static Logger logger = LoggerFactory.getLogger(SeverConfigFactoryXML.class);
	
	@Override
	public void constructServerConfig(Path path) {
		
		if(path == null) {
			throw new RuntimeException("Path to read server configution is null");
		}
		
		try {
			File file = path.toFile();
			JAXBContext jaxbContext = JAXBContext.newInstance(ServerConfig.class);
			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			instance = (ServerConfig) jaxbUnmarshaller.unmarshal(file);
		} catch (JAXBException e) {
			logger.error("error in read server configution file", e);
			throw new RuntimeException("Error in read server configution filel", e);
		}
	}

}
