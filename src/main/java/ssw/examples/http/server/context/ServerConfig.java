/**
 * 
 */
package ssw.examples.http.server.context;

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
public class ServerConfig {
	
	private Integer port;
	private String context;
	private String serverName;
	private Boolean enableSSL;
	private String keyStoreType;
	private String keyStoreId;
	
	//XXX find method to protect password on properties file
	private String keyStorePasswd;
	private String sslContextType;
	
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
	
	/**
	 * @return the enableSSL
	 */
	public final Boolean getEnableSSL() {
		return enableSSL != null ? enableSSL : Boolean.FALSE;
	}

	/**
	 * @param enableSSL the enableSSL to set
	 */
	public final void setEnableSSL(Boolean enableSSL) {
		this.enableSSL = enableSSL;
	}

	/**
	 * @return the keyStoreType
	 */
	public final String getKeyStoreType() {
		return keyStoreType;
	}

	/**
	 * @param keyStoreType the keyStoreType to set
	 */
	public final void setKeyStoreType(String keyStoreType) {
		this.keyStoreType = keyStoreType;
	}

	/**
	 * @return the keyStoreId
	 */
	public final String getKeyStoreId() {
		return keyStoreId;
	}

	/**
	 * @param keyStoreId the keyStoreId to set
	 */
	public final void setKeyStoreId(String keyStoreId) {
		this.keyStoreId = keyStoreId;
	}

	/**
	 * @return the keyStorePasswd
	 */
	public final String getKeyStorePasswd() {
		return keyStorePasswd;
	}

	/**
	 * @param keyStorePasswd the keyStorePasswd to set
	 */
	public final void setKeyStorePasswd(String keyStorePasswd) {
		this.keyStorePasswd = keyStorePasswd;
	}

	/**
	 * @return the sslContextType
	 */
	public final String getSslContextType() {
		return sslContextType;
	}

	/**
	 * @param sslContextType the sslContextType to set
	 */
	public final void setSslContextType(String sslContextType) {
		this.sslContextType = sslContextType;
	}
	
	
	
}
