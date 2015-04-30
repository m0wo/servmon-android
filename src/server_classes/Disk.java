package server_classes;

import android.os.Parcel;
import android.os.Parcelable;

public class Disk implements Parcelable {

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
	
	

    protected Disk(Parcel in) {
        id = in.readString();
        totalSpace = in.readString();
        remainingSpace = in.readString();
        readSpeed = in.readString();
        writeSpeed = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(totalSpace);
        dest.writeString(remainingSpace);
        dest.writeString(readSpeed);
        dest.writeString(writeSpeed);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<Disk> CREATOR = new Parcelable.Creator<Disk>() {
        @Override
        public Disk createFromParcel(Parcel in) {
            return new Disk(in);
        }

        @Override
        public Disk[] newArray(int size) {
            return new Disk[size];
        }
    };
}