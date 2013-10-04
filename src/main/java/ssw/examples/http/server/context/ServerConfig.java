/**
 * 
 */
package ssw.examples.http.server.context;

/**
 * Base configuration is contained in xml files.
 * This class contains fields of Server Configuration.
 * fields are populated by Spring Microcontainer (this is a constraint). 
 * 
 * @author m.genova
 * @since 1.0
 */
public class ServerConfig {
	
	private Integer port;
	private String context;
	private String serverName;
	
	/**
	 * @return the port
	 */
	public Integer getPort() {
		return port;
	}
	
	/**
	 * @param port the port to set
	 */
	public void setPort(Integer port) {
		this.port = port;
	}
	
	/**
	 * @return the context
	 */
	public String getContext() {
		return context;
	}
	
	/**
	 * @param context the context to set
	 */
	public void setContext(String context) {
		this.context = context;
	}

	/**
	 * @return the serverName
	 */
	public String getServerName() {
		return serverName;
	}

	/**
	 * @param serverName the serverName to set
	 */
	public void setServerName(String serverName) {
		this.serverName = serverName;
	}
	
	
}
