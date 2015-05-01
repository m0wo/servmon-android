package server_classes;

import android.os.Parcel;
import android.os.Parcelable;

//ram class
public class Ram implements Parcelable {
	
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
		return "Total Ram: " + totalRam + "MB";
	}

    protected Ram(Parcel in) {
        totalRam = in.readString();
        usedRam = in.readString();
        timeStamp = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(totalRam);
        dest.writeString(usedRam);
        dest.writeString(timeStamp);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<Ram> CREATOR = new Parcelable.Creator<Ram>() {
        @Override
        public Ram createFromParcel(Parcel in) {
            return new Ram(in);
        }

        @Override
        public Ram[] newArray(int size) {
            return new Ram[size];
        }
    };
}