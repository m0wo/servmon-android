package server_classes;

public class Cpu {
	//[{"id":1,"vendor":"Intel","model":"i5","clock_speed":2000,"cpu_usage_percentage":62},{"id":2,"vendor":"Intel","model":"i5","clock_speed":2000,"cpu_usage_percentage":54}]
	
	private String id;
	private String vendor;
	private String model;
	private String clock_speed;
	private String cpu_usage_percentage;
	
	public Cpu(){
		super();
	}
	
	public Cpu(String id, String vendor, String model, String clock_speed,
			String cpu_usage_percentage) {
		super();
		this.id = id;
		this.vendor = vendor;
		this.model = model;
		this.clock_speed = clock_speed;
		this.cpu_usage_percentage = cpu_usage_percentage;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getVendor() {
		return vendor;
	}

	public void setVendor(String vendor) {
		this.vendor = vendor;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getClock_speed() {
		return clock_speed;
	}

	public void setClock_speed(String clock_speed) {
		this.clock_speed = clock_speed;
	}

	public String getCpu_usage_percentage() {
		return cpu_usage_percentage;
	}

	public void setCpu_usage_percentage(String cpu_usage_percentage) {
		this.cpu_usage_percentage = cpu_usage_percentage;
	}

	@Override
	public String toString() {
		return "Cpu [id=" + id + ", vendor=" + vendor + ", model=" + model
				+ ", clock_speed=" + clock_speed + ", cpu_usage_percentage="
				+ cpu_usage_percentage + "]";
	}
	
	
}
