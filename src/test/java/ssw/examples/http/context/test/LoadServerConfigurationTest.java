/**
 * 
 */
package ssw.examples.http.context.test;

import static org.junit.Assert.fail;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.FileVisitOption;
import java.nio.file.FileVisitor;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashSet;
import java.util.Set;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import ssw.examples.http.context.test.support.DeleteFileVisitor;
import ssw.examples.http.server.config.SSLConfig;
import ssw.examples.http.server.config.ServerConfig;

/**
 * @author m.genova
 * @since 1.0
 */
public class LoadServerConfigurationTest {
	
	public static final String TEST_DIRECTORY = "./testDirectory";
	
	@BeforeClass
	public static void setupDirectory() throws IOException {
		FileSystem fileSystem = FileSystems.getDefault();
		Path path = fileSystem.getPath(TEST_DIRECTORY);
		
		if(!Files.exists(path)) {
			Files.createDirectories(path);
		}
	}
	
	@AfterClass
	public static void removeDirectory() throws IOException {
		FileSystem fileSystem = FileSystems.getDefault();
		Path path = fileSystem.getPath(TEST_DIRECTORY);

		Set<FileVisitOption> opts = new HashSet<>();
		opts.add(FileVisitOption.FOLLOW_LINKS);
		
//		FileVisitor<Path> delete = new DeleteFileVisitor<Path>();
//		Files.walkFileTree(path, opts, Integer.MAX_VALUE, delete);
	}
	
	@Test
	public void test() {
		
		ServerConfig serverConfig = new ServerConfig();
		serverConfig.setContext("context value");
		serverConfig.setPort(1000);
		serverConfig.setServerName("test name");
		
		SSLConfig sslConfig = new SSLConfig();
		sslConfig.setEnableSSL(true);
		sslConfig.setKeyStoreId("my.value");
		sslConfig.setKeyStorePasswd("myPsswd");
		sslConfig.setKeyStoreType("TLS");
		sslConfig.setSslContextType("Context type");
		
		serverConfig.setSslConfig(sslConfig);
		
		FileSystem fileSystem = FileSystems.getDefault();
		Path path = fileSystem.getPath(TEST_DIRECTORY, "loadSeverConfigurationTest.xml");
		try {
			Files.deleteIfExists(path);
		} catch (IOException e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
		
		File output = path.toFile();
		
		try {
			JAXBContext jaxbContext = JAXBContext.newInstance(ServerConfig.class);
			Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
			jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
			jaxbMarshaller.marshal(serverConfig, output);
		} catch (JAXBException e1) {
			e1.printStackTrace();
			fail(e1.getMessage());
		}
		
		ServerConfig serverConfigRead = null;
		
		try {
			JAXBContext jaxbContext = JAXBContext.newInstance(ServerConfig.class);
			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			serverConfigRead = (ServerConfig) jaxbUnmarshaller.unmarshal(output);
		} catch (JAXBException e2) {
			e2.printStackTrace();
			fail(e2.getMessage());
		}
		
		Assert.assertNotNull(serverConfigRead);
		Assert.assertEquals(serverConfig, serverConfigRead);
	}

}
