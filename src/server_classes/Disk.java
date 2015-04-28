package server_classes;

public class Disk {
//[{"id":1,"total_space":52000,"remaining_space":35543,"read_speed":25000,"write_speed":35000},{"id":2,"total_space":320000,"remaining_space":44300,"read_speed":28000,"write_speed":34000}]

	private String id;
	private String totalSpace;
	private String remainingSpace;
	private String readSpeed;
	private String writeSpeed;
	
	public Disk(){

	}

	public Disk(String id, String totalSpace, String remainingSpace,
			String readSpeed, String writeSpeed) {
		super();
		this.id = id;
		this.totalSpace = totalSpace;
		this.remainingSpace = remainingSpace;
		this.readSpeed = readSpeed;
		this.writeSpeed = writeSpeed;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTotalSpace() {
		return totalSpace;
	}

	public void setTotalSpace(String totalSpace) {
		this.totalSpace = totalSpace;
	}

	public String getRemainingSpace() {
		return remainingSpace;
	}

	public void setRemainingSpace(String remainingSpace) {
		this.remainingSpace = remainingSpace;
	}

	public String getReadSpeed() {
		return readSpeed;
	}

	public void setReadSpeed(String readSpeed) {
		this.readSpeed = readSpeed;
	}

	public String getWriteSpeed() {
		return writeSpeed;
	}

	public void setWriteSpeed(String writeSpeed) {
		this.writeSpeed = writeSpeed;
	}

	@Override
	public String toString() {
		return "Disk [id=" + id + ", totalSpace=" + totalSpace
				+ ", remainingSpace=" + remainingSpace + ", readSpeed="
				+ readSpeed + ", writeSpeed=" + writeSpeed + "]";
	}
	
	
}
