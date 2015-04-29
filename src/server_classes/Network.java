package server_classes;

public class Network {
	
	private String hostname;
	private String ipAddress;
	private String gateway;
	private String publicIp;
	private String uploadTotal;
	private String downloadTotal;

	public Network(){

	}

	public Network(String hostname, String ipAddress, String gateway,
			String publicIp, String uploadTotal, String downloadTotal) {
		super();
		this.hostname = hostname;
		this.ipAddress = ipAddress;
		this.gateway = gateway;
		this.publicIp = publicIp;
		this.uploadTotal = uploadTotal;
		this.downloadTotal = downloadTotal;
	}

	public String getHostname() {
		return hostname;
	}

	public void setHostname(String hostname) {
		this.hostname = hostname;
	}

	public String getIpAddress() {
		return ipAddress;
	}

	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}

	public String getGateway() {
		return gateway;
	}

	public void setGateway(String gateway) {
		this.gateway = gateway;
	}

	public String getPublicIp() {
		return publicIp;
	}

	public void setPublicIp(String publicIp) {
		this.publicIp = publicIp;
	}

	public String getUploadTotal() {
		return uploadTotal;
	}

	public void setUploadTotal(String uploadTotal) {
		this.uploadTotal = uploadTotal;
	}

	public String getDownloadTotal() {
		return downloadTotal;
	}

	public void setDownloadTotal(String downloadTotal) {
		this.downloadTotal = downloadTotal;
	}

	@Override
	public String toString() {
		return "Network [hostname=" + hostname + ", ipAddress=" + ipAddress
				+ ", gateway=" + gateway + ", publicIp=" + publicIp
				+ ", uploadTotal=" + uploadTotal + ", downloadTotal="
				+ downloadTotal + "]";
	}	
}
