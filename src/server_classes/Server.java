package server_classes;

public class Server {
	
	private String serverId;
	private String serverName;
	private String operatingSystem;
	private String uptime;
	
	//TODO: RAM, someone tell me how it works pls
	
	public Server(String id, String name, String os, String uptime){
		this.serverId = id;
		this.serverName = name;
		this.operatingSystem = os;
		this.uptime = uptime;
	}

	public String getServerId() {
		return serverId;
	}

	public void setServerId(String serverId) {
		this.serverId = serverId;
	}

	public String getServerName() {
		return serverName;
	}

	public void setServerName(String serverName) {
		this.serverName = serverName;
	}

	public String getOperatingSystem() {
		return operatingSystem;
	}

	public void setOperatingSystem(String operatingSystem) {
		this.operatingSystem = operatingSystem;
	}

	public String getUptime() {
		return uptime;
	}

	public void setUptime(String uptime) {
		this.uptime = uptime;
	}

	@Override
	public String toString() {
		return "Server [serverId=" + serverId + ", serverName=" + serverName
				+ ", operatingSystem=" + operatingSystem + ", uptime=" + uptime
				+ "]";
	}
	
}
