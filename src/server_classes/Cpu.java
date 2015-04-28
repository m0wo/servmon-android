package server_classes;

public class Cpu {
	//[{"id":1,"vendor":"Intel","model":"i5","clock_speed":2000,"cpu_usage_percentage":62},{"id":2,"vendor":"Intel","model":"i5","clock_speed":2000,"cpu_usage_percentage":54}]
	
	private String id;
	private String vendor;
	private String model;
	private String clock_speed;
	private String cpu_usage_percentage;
	
	private Cpu(){
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
	
	
}
