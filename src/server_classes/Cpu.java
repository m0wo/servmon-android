package server_classes;

import android.os.Parcel;
import android.os.Parcelable;

public class Cpu implements Parcelable {
	
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
		
		StringBuilder sb = new StringBuilder();
		sb.append("Model: " + model + "\n");
		sb.append("Usage: " + cpu_usage_percentage + "%");
		
		/*return "Cpu [id=" + id + ", vendor=" + vendor + ", model=" + model
				+ ", clock_speed=" + clock_speed + ", cpu_usage_percentage="
				+ cpu_usage_percentage + "]";*/
		
		return sb.toString();
		
		
	}
	
	

    protected Cpu(Parcel in) {
        id = in.readString();
        vendor = in.readString();
        model = in.readString();
        clock_speed = in.readString();
        cpu_usage_percentage = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(vendor);
        dest.writeString(model);
        dest.writeString(clock_speed);
        dest.writeString(cpu_usage_percentage);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<Cpu> CREATOR = new Parcelable.Creator<Cpu>() {
        @Override
        public Cpu createFromParcel(Parcel in) {
            return new Cpu(in);
        }

        @Override
        public Cpu[] newArray(int size) {
            return new Cpu[size];
        }
    };
}