package server_classes;

public class Network {
<<<<<<< HEAD
	
=======

	private String id;
	private String hostname;
	private String ip;
	private String gateway;
	private String publicIp;
	private String uploadTotal;
	private String downloadTotal;

	public Network(){

	}

	public Network(String id, String hostname, String ip, String gateway, String publicIp,
			String uploadTotal, String downloadTotal) {
		super();
		this.id = id;
		this.hostname = hostname;
		this.ip = ip;
		this.gateway = gateway;
		this.publicIp = publicIp;
		this.uploadTotal = uploadTotal;
		this.downloadTotal = downloadTotal;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getHostname() {
		return hostname;
	}

	public void setHostname(String hostname) {
		this.hostname = hostname;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
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
		return "Network [id=" + id + ", hostname=" + hostname + ", ip=" + ip
				+ ", gateway=" + gateway + ", publicIp=" + publicIp
				+ ", uploadTotal=" + uploadTotal + ", downloadTotal="
				+ downloadTotal + "]";
	}
>>>>>>> 9f8727f9af987b6bbe80d0cd75f685f4fd52d976
}
