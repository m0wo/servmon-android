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
	
	public Ram(String totalRam, String usedRam) {
		super();
		this.totalRam = totalRam;
		this.usedRam = usedRam;
	}
	
	public Ram(){
		super();
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

	@Override
	public String toString() {
		return "Ram [totalRam=" + totalRam + ", usedRam=" + usedRam
				+ ", timeStamp=" + timeStamp + "]";
	}
}
