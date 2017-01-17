package countryinfo.rahul.com.countryinfo.detailinfo;

import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import countryinfo.rahul.com.countryinfo.R;
import countryinfo.rahul.com.countryinfo.base.BaseActivity;
import countryinfo.rahul.com.countryinfo.model.CountryAPIObject;
import rx.Observer;
import rx.Subscription;


/**
 * This activity shows the Santander logo and moves on after a certain delay.
 */
public class DetailInfoActivity extends BaseActivity<DetailInfoPresenter> implements DetailInfoView {

    @BindView(R.id.country_flag) ImageView mCountryFlagIV;
    @BindView(R.id.capital_title) TextView mCapitalTitleTV;
    @BindView(R.id.region_title) TextView mRegionTitleTV;
    @BindView(R.id.area_title) TextView mAreaTitleTV;


    private CountryAPIObject mAPIResponseObject;
    private Subscription mAPIResponseSubscription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_info);
        ButterKnife.bind(this);

        mAPIResponseObject = getIntent().getParcelableExtra("DATA");

        CollapsingToolbarLayout collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.main_collapsing);
        collapsingToolbarLayout.setTitle(mAPIResponseObject.getName());

        setUpViews();
    }

    @Override
    protected void setupPresenter() {
        mPresenter = new DetailInfoPresenter(this);
    }

    private void setUpViews()
    {
        String url = "http://www.geonames.org/flags/x/" + mAPIResponseObject.getAlpha2Code().toLowerCase() + ".gif";
        Picasso.with(this)
                .load(url)
                .into(mCountryFlagIV);

        mCapitalTitleTV.setText(mAPIResponseObject.getCapital());
        mRegionTitleTV.setText(mAPIResponseObject.getRegion());
        mAreaTitleTV.setText(mAPIResponseObject.getArea());
    }

    private void setUpCountryList()
    {

    }

    Observer<ArrayList<CountryAPIObject>> mAPIResponseObserver = new Observer<ArrayList<CountryAPIObject>>() {
        @Override
        public void onCompleted() {
            // Called when the observable has no more data to emit
        }

        @Override
        public void onError(Throwable e) {
            // Called when the observable encounters an error
        }

        @Override
        public void onNext(ArrayList<CountryAPIObject> apiObject) {
            // Called each time the observable emits data
            setUpCountryList();
        }
    };

    @Override
    public void setUpObservers() {
        mAPIResponseSubscription = mPresenter.getAPIResponseObservable().subscribe(mAPIResponseObserver);
    }
}