/**
 * 
 */
package ssw.examples.http.server.service;

import java.io.IOException;
import java.io.InterruptedIOException;
import java.net.ServerSocket;
import java.net.Socket;

import org.apache.http.HttpConnectionFactory;
import org.apache.http.HttpServerConnection;
import org.apache.http.impl.DefaultBHttpServerConnection;
import org.apache.http.impl.DefaultBHttpServerConnectionFactory;
import org.apache.http.protocol.HttpService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ssw.examples.http.server.handler.InternalConnectionManagerFactory;

/**
 * Custom Service Thread
 * 
 * @author m.genova
 * @since 1.0
 */
public class ServiceThread extends Thread {
	 private static Logger logger = LoggerFactory.getLogger(ServiceThread.class);
	 protected HttpConnectionFactory<DefaultBHttpServerConnection> connFactory;
	 protected int port;
	 
	 /*
	  * XXX Add socket server properties management (the backlog's important)
	  */
     protected ServerSocket serversocket;
     protected HttpService httpService;
     
     /**
      * 
      * 
      * @param port
      * @param httpService
      * @param sf
      * @throws IOException
      */
     public ServiceThread(final int port, final HttpService httpService) {
         this.port = port;
         this.httpService = httpService;
     }
     
     /**
      * 
      * @throws IOException
      */
     public void initialize() throws IOException {
    	 this.connFactory = DefaultBHttpServerConnectionFactory.INSTANCE;
         this.serversocket = new ServerSocket(port);
     }

     @Override
     public void run() {
    	 StringBuilder builder = new StringBuilder("Service-");
    	 builder.append(this.serversocket.getLocalPort());
    	 this.setName(builder.toString());
    	 
    	 logger.debug("Listening on port {} ", this.serversocket.getLocalPort());
         
    	 while (!Thread.interrupted()) {
             try {
                 // Set up HTTP connection
                 Socket socket = this.serversocket.accept();
                 logger.debug("Incoming connection from {}", socket.getInetAddress());
                 HttpServerConnection conn = this.connFactory.createConnection(socket);

                 // Start worker thread
                 Thread t = InternalConnectionManagerFactory.getThread(this.httpService, conn);
                 t.setDaemon(true);
                 t.start();
             } catch (InterruptedIOException ex) {
                 break;
             } catch (IOException e) {
            	 logger.error("I/O error initialising connection thread: {}",e.getMessage());
                 break;
             }
         }
     }
}
