package server_classes;

import android.os.Parcel;
import android.os.Parcelable;

public class Network implements Parcelable {
	
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

    protected Network(Parcel in) {
        hostname = in.readString();
        ipAddress = in.readString();
        gateway = in.readString();
        publicIp = in.readString();
        uploadTotal = in.readString();
        downloadTotal = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(hostname);
        dest.writeString(ipAddress);
        dest.writeString(gateway);
        dest.writeString(publicIp);
        dest.writeString(uploadTotal);
        dest.writeString(downloadTotal);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<Network> CREATOR = new Parcelable.Creator<Network>() {
        @Override
        public Network createFromParcel(Parcel in) {
            return new Network(in);
        }

        @Override
        public Network[] newArray(int size) {
            return new Network[size];
        }
    };
}