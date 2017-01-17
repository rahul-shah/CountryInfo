package countryinfo.rahul.com.countryinfo.net;

import java.util.ArrayList;

import countryinfo.rahul.com.countryinfo.model.CountryAPIObject;
import retrofit2.Call;
import retrofit2.http.GET;


public interface APIInterface {
    @GET("all")
    Call<ArrayList<CountryAPIObject>> getAllCountries();
}