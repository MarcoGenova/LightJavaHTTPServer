/**
 * 
 */
package ssw.examples.http.server;

import java.io.IOException;

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

import ssw.examples.http.server.context.ServerConfig;
import ssw.examples.http.server.handler.InternalRequestHandler;
import ssw.examples.http.server.handler.RequestHandlerFactory;

/**
 * Http Interceptor Engine
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
		Integer port = this.serverConfig.getPort();
		String context = this.serverConfig.getContext();
		
		if(StringUtils.isBlank(context)) {
			context = "*";
		}
		
		String serverName = this.serverConfig.getServerName();
		
		if(StringUtils.isBlank(serverName)) {
			serverName = "LightHttpServer/0.1";
		}
		
		HttpProcessorBuilder builder = HttpProcessorBuilder.create();
		HttpProcessor processor = builder.add(new ResponseDate())
                .add(new ResponseServer(serverName))
                .add(new ResponseContent())
                .add(new ResponseConnControl()).build();
		InternalRequestHandler requestHandler = RequestHandlerFactory.getRequestHandler("parameters fake");
		UriHttpRequestHandlerMapper registry = new UriHttpRequestHandlerMapper();
        registry.register(context, requestHandler);
		
        HttpService httpService = new HttpService(processor, registry);
       
        try {
			service = new ServiceThread(port, httpService);
		} catch (IOException e) {
			logger.error("error in thear service initialization", e.getMessage());
			System.exit(1);
		}
        
        service.start();
	}
	
	public void stop() {
		service.interrupt();
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
