package countryinfo.rahul.com.countryinfo.infolist;


import java.util.ArrayList;

import countryinfo.rahul.com.countryinfo.base.BasePresenter;
import countryinfo.rahul.com.countryinfo.base.BaseView;
import countryinfo.rahul.com.countryinfo.model.CountryAPIObject;
import countryinfo.rahul.com.countryinfo.net.APIClient;
import countryinfo.rahul.com.countryinfo.net.APIInterface;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import rx.Observable;

/**
 * This presenter manages the splash screen.
 */
public class InfoListPresenter extends BasePresenter {

    private static final String TAG = InfoListPresenter.class.getSimpleName();
    private Observable<ArrayList<CountryAPIObject>> mAPIResponseObservable;
    private final InfoListView mView;

    public InfoListPresenter(InfoListView view) {
        this.mView = view;
    }

    @Override
    public BaseView getView() {
        return mView;
    }

    @Override
    protected void onStart() {
        // Make API Call
        getCountryInfoFromAPI();
    }

    private void getCountryInfoFromAPI()
    {
        APIInterface apiService = APIClient.getClient().create(APIInterface.class);

        Call<ArrayList<CountryAPIObject>> call = apiService.getAllCountries();
        call.enqueue(new Callback<ArrayList<CountryAPIObject>>() {
            @Override
            public void onResponse(Call<ArrayList<CountryAPIObject>>call, Response<ArrayList<CountryAPIObject>> response) {
                //Success Response
                ArrayList<CountryAPIObject> apiResponse = response.body();
                mAPIResponseObservable = Observable.just(apiResponse);
                mView.setUpObservers();
            }

            @Override
            public void onFailure(Call<ArrayList<CountryAPIObject>>call, Throwable t) {
                // Log error here since request failed
                int def = 12;
            }
        });
    }

    public Observable<ArrayList<CountryAPIObject>> getAPIResponseObservable() {
        return mAPIResponseObservable;
    }
}