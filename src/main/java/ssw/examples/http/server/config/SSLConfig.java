/**
 * 
 */
package ssw.examples.http.server.config;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 * @author m.genova
 * @since 1.0
 */
@XmlType
public class SSLConfig {
	
	private Boolean enableSSL;

	private String keyStoreType;

	private String keyStoreId;
	
	//XXX find method to protect password on properties file
	private String keyStorePasswd;

	private String sslContextType;
	
	/**
	 * @return the enableSSL
	 */
	public final Boolean getEnableSSL() {
		return enableSSL != null ? enableSSL : Boolean.FALSE;
	}

	/**
	 * @param enableSSL the enableSSL to set
	 */
	@XmlAttribute(required=true)
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
	@XmlElement(required=false, defaultValue="")
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
	@XmlElement(required=false, defaultValue="")
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
	@XmlElement(required=false)
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
	@XmlElement(required=false, defaultValue="")
	public final void setSslContextType(String sslContextType) {
		this.sslContextType = sslContextType;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((enableSSL == null) ? 0 : enableSSL.hashCode());
		result = prime * result
				+ ((keyStoreId == null) ? 0 : keyStoreId.hashCode());
		result = prime * result
				+ ((keyStorePasswd == null) ? 0 : keyStorePasswd.hashCode());
		result = prime * result
				+ ((keyStoreType == null) ? 0 : keyStoreType.hashCode());
		result = prime * result
				+ ((sslContextType == null) ? 0 : sslContextType.hashCode());
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
		SSLConfig other = (SSLConfig) obj;
		if (enableSSL == null) {
			if (other.enableSSL != null)
				return false;
		} else if (!enableSSL.equals(other.enableSSL))
			return false;
		if (keyStoreId == null) {
			if (other.keyStoreId != null)
				return false;
		} else if (!keyStoreId.equals(other.keyStoreId))
			return false;
		if (keyStorePasswd == null) {
			if (other.keyStorePasswd != null)
				return false;
		} else if (!keyStorePasswd.equals(other.keyStorePasswd))
			return false;
		if (keyStoreType == null) {
			if (other.keyStoreType != null)
				return false;
		} else if (!keyStoreType.equals(other.keyStoreType))
			return false;
		if (sslContextType == null) {
			if (other.sslContextType != null)
				return false;
		} else if (!sslContextType.equals(other.sslContextType))
			return false;
		return true;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("SocketConfig [enableSSL=");
		builder.append(enableSSL);
		builder.append(", keyStoreType=");
		builder.append(keyStoreType);
		builder.append(", keyStoreId=");
		builder.append(keyStoreId);
		builder.append(", keyStorePasswd=");
		builder.append(keyStorePasswd);
		builder.append(", sslContextType=");
		builder.append(sslContextType);
		builder.append("]");
		return builder.toString();
	}
	
	
}
