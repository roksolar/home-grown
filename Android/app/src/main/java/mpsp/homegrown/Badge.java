package mpsp.homegrown;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by rokso on 28. 12. 2017.
 */

public class Badge implements Parcelable{
    private int id;
    private String name;
    private int progress;


    public Badge(int id, int progress){
        this.id = id;
        this.progress = progress;
    }

    protected Badge(Parcel in) {
        id = in.readInt();
        name = in.readString();
        progress = in.readInt();
    }

    public static final Creator<Badge> CREATOR = new Creator<Badge>() {
        @Override
        public Badge createFromParcel(Parcel in) {
            return new Badge(in);
        }

        @Override
        public Badge[] newArray(int size) {
            return new Badge[size];
        }
    };

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getProgress() {
        return progress;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeString(name);
        parcel.writeInt(progress);

    }
}
