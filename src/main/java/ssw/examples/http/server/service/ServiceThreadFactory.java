/**
 * 
 */
package ssw.examples.http.server.service;

import java.io.IOException;
import java.net.URL;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;

import javax.net.ssl.KeyManager;
import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLServerSocketFactory;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.protocol.HttpProcessor;
import org.apache.http.protocol.HttpProcessorBuilder;
import org.apache.http.protocol.HttpService;
import org.apache.http.protocol.ResponseConnControl;
import org.apache.http.protocol.ResponseContent;
import org.apache.http.protocol.ResponseDate;
import org.apache.http.protocol.ResponseServer;
import org.apache.http.protocol.UriHttpRequestHandlerMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ssw.examples.http.server.config.ServerConfig;
import ssw.examples.http.server.handler.InternalRequestHandler;
import ssw.examples.http.server.handler.RequestHandlerFactory;

/**
 * @author m.genova
 * @since 1.0
 */
public class ServiceThreadFactory {
	private static Logger logger = LoggerFactory.getLogger(ServiceThreadFactory.class);
	
	/**
	 * 
	 * @param serverConfig
	 * @return
	 */
	public static ServiceThread createService(ServerConfig serverConfig) {

		Integer port = serverConfig.getPort();
		String context = serverConfig.getContext();

		if (StringUtils.isBlank(context)) {
			context = "*";
		}

		String serverName = serverConfig.getServerName();

		if (StringUtils.isBlank(serverName)) {
			serverName = "LightHttpServer/0.1";
		}

		HttpProcessor processor = getHttpProcessor(serverName);
		InternalRequestHandler requestHandler = RequestHandlerFactory
				.getRequestHandler("parameters fake");
		UriHttpRequestHandlerMapper registry = new UriHttpRequestHandlerMapper();
		registry.register(context, requestHandler);

		HttpService httpService = new HttpService(processor, registry);
		ServiceThread service = null;

		try {

			if (serverConfig.getSslConfig().getEnableSSL()) {
				SSLServerSocketFactory sslServerSocketFactory = createSSLContext(serverConfig);
				service = new SecuredServiceThread(port, httpService,
						sslServerSocketFactory);
			} else {
				service = new ServiceThread(port, httpService);
			}
			
			service.initialize();
		} catch (IOException | UnrecoverableKeyException | KeyStoreException
				| NoSuchAlgorithmException | CertificateException
				| KeyManagementException e) {
			logger.error("error in thear service initialization",
					e.getMessage());
			System.exit(1);
		}

		return service;
	}

	/**
	 * 
	 * @param serverConfig
	 * @return
	 * @throws KeyStoreException
	 * @throws NoSuchAlgorithmException
	 * @throws CertificateException
	 * @throws IOException
	 * @throws UnrecoverableKeyException
	 * @throws KeyManagementException
	 */
	private static SSLServerSocketFactory createSSLContext(
			ServerConfig serverConfig) throws KeyStoreException,
			NoSuchAlgorithmException, CertificateException, IOException,
			UnrecoverableKeyException, KeyManagementException {
		SSLServerSocketFactory sf = null;

		// Initialize SSL context
		ClassLoader cl = ClassLoader.getSystemClassLoader();
		URL url = cl.getResource(serverConfig.getSslConfig().getKeyStoreId());

		if (url == null) {
			System.out.println("Keystore not found");
			System.exit(1);
		}

		KeyStore keystore = KeyStore
				.getInstance(serverConfig.getSslConfig().getKeyStoreType());
		String keyStorePasswd = serverConfig.getSslConfig().getKeyStorePasswd();
		keystore.load(url.openStream(), keyStorePasswd.toCharArray());
		String defaultAlgorithm = KeyManagerFactory.getDefaultAlgorithm();
		KeyManagerFactory kmfactory = KeyManagerFactory
				.getInstance(defaultAlgorithm);
		kmfactory.init(keystore, keyStorePasswd.toCharArray());
		KeyManager[] keymanagers = kmfactory.getKeyManagers();
		SSLContext sslcontext = SSLContext.getInstance(serverConfig
				.getSslConfig().getSslContextType());
		sslcontext.init(keymanagers, null, null);
		sf = sslcontext.getServerSocketFactory();

		return sf;
	}

	/**
	 * Parameters managed:
	 * 
	 * <ol>
	 * <li>server name</li>
	 * </ol>
	 * 
	 * 
	 * @param params
	 * @return
	 */
	private static HttpProcessor getHttpProcessor(String... params) {
		HttpProcessorBuilder builder = HttpProcessorBuilder.create();
		builder = builder.add(new ResponseDate());
		builder = builder.add(new ResponseServer(params[0]));
		builder = builder.add(new ResponseContent());
		builder = builder.add(new ResponseConnControl());

		return builder.build();
	}
}
