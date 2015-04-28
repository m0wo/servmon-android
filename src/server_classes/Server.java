package server_classes;

import android.os.Parcel;
import android.os.Parcelable;

public class Server implements Parcelable {
	
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
	

    protected Server(Parcel in) {
        serverId = in.readString();
        serverName = in.readString();
        operatingSystem = in.readString();
        uptime = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(serverId);
        dest.writeString(serverName);
        dest.writeString(operatingSystem);
        dest.writeString(uptime);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<Server> CREATOR = new Parcelable.Creator<Server>() {
        @Override
        public Server createFromParcel(Parcel in) {
            return new Server(in);
        }

        @Override
        public Server[] newArray(int size) {
            return new Server[size];
        }
    };
}