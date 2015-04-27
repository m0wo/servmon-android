package server_classes;

public class Ram {
	
	private String totalRam;
	private String usedRam;
	private String timeStamp;
	
	public Ram(String totalRam, String usedRam, String timeStamp) {
		super();
		this.totalRam = totalRam;
		this.usedRam = usedRam;
		this.timeStamp = timeStamp;
	}

	public String getTotalRam() {
		return totalRam;
	}

	public void setTotalRam(String totalRam) {
		this.totalRam = totalRam;
	}

	public String getUsedRam() {
		return usedRam;
	}

	public void setUsedRam(String usedRam) {
		this.usedRam = usedRam;
	}

	public String getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(String timeStamp) {
		this.timeStamp = timeStamp;
	}
}
