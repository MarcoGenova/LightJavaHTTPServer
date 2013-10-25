/**
 * 
 */
package ssw.examples.http.server.config;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Base configuration is contained in xml files.
 * This class contains fields of Server Configuration.
 * fields are populated by Spring Microcontainer (this is a constraint). 
 * 
 * XXX migrate on XML configuration, load configuration via JAXB (use annotation)
 * 
 * @author m.genova
 * @since 1.0
 */
@XmlRootElement
public class ServerConfig {
	
	private String serverName;
	
	private Integer port;
	
	private String context;
	
	private SSLConfig sslConfig;
	
	/**
	 * @return the port
	 */
	public Integer getPort() {
		return port;
	}
	
	/**
	 * @param port the port to set
	 */
	@XmlElement(required=false, defaultValue="8080")
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
	@XmlElement(required=false, defaultValue="sockets")
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
	@XmlElement(required=false, defaultValue="LightHttpServer/0.1")
	public void setServerName(String serverName) {
		this.serverName = serverName;
	}

	/**
	 * @return the socketConfig
	 */
	public final SSLConfig getSslConfig() {
		return sslConfig;
	}

	/**
	 * @param socketConfig the socketConfig to set
	 */
	@XmlElement (required=false)
	public final void setSslConfig(SSLConfig socketConfig) {
		this.sslConfig = socketConfig;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((context == null) ? 0 : context.hashCode());
		result = prime * result + ((port == null) ? 0 : port.hashCode());
		result = prime * result
				+ ((serverName == null) ? 0 : serverName.hashCode());
		result = prime * result
				+ ((sslConfig == null) ? 0 : sslConfig.hashCode());
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ServerConfig other = (ServerConfig) obj;
		if (context == null) {
			if (other.context != null)
				return false;
		} else if (!context.equals(other.context))
			return false;
		if (port == null) {
			if (other.port != null)
				return false;
		} else if (!port.equals(other.port))
			return false;
		if (serverName == null) {
			if (other.serverName != null)
				return false;
		} else if (!serverName.equals(other.serverName))
			return false;
		if (sslConfig == null) {
			if (other.sslConfig != null)
				return false;
		} else if (!sslConfig.equals(other.sslConfig))
			return false;
		return true;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ServerConfig [serverName=");
		builder.append(serverName);
		builder.append(", port=");
		builder.append(port);
		builder.append(", context=");
		builder.append(context);
		builder.append(", socketConfig=");
		builder.append(sslConfig);
		builder.append("]");
		return builder.toString();
	}
	
}
