package countryinfo.rahul.com.countryinfo.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CountryAPIObject implements Parcelable {
    @SerializedName("name")
    @Expose
    private String name;

    @SerializedName("capital")
    @Expose
    private String capital;

    @SerializedName("region")
    @Expose
    private String region;

    @SerializedName("area")
    @Expose
    private String area;

    @SerializedName("alpha2Code")
    @Expose
    private String alpha2Code;


    public String getName() {
        return name;
    }

    public String getAlpha2Code() {
        return alpha2Code;
    }

    public String getArea() {
        return area;
    }

    public String getCapital() {
        return capital;
    }

    public String getRegion() {
        return region;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(name);
        parcel.writeString(region);
        parcel.writeString(area);
        parcel.writeString(alpha2Code);
        parcel.writeString(capital);
    }

    protected CountryAPIObject(Parcel in) {
        name = in.readString();
        region = in.readString();
        area = in.readString();
        alpha2Code = in.readString();
        capital = in.readString();
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<CountryAPIObject> CREATOR = new Parcelable.Creator<CountryAPIObject>() {
        @Override
        public CountryAPIObject createFromParcel(Parcel in) {
            return new CountryAPIObject(in);
        }

        @Override
        public CountryAPIObject[] newArray(int size) {
            return new CountryAPIObject[size];
        }
    };
}
