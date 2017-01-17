package countryinfo.rahul.com.countryinfo.infolist;

import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

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
public class InfoListActivity extends BaseActivity<InfoListPresenter> implements InfoListView {

    @BindView(R.id.info_list_rv) protected RecyclerView mInfoListRV;
    private InfoListAdapter mViewAdapter;
    private ArrayList<CountryAPIObject> mAPIResponseObject;
    private Subscription mAPIResponseSubscription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_list);
        ButterKnife.bind(this);
    }

    @Override
    protected void setupPresenter() {
        mPresenter = new InfoListPresenter(this);
    }

    private void setUpCountryList()
    {
        mViewAdapter = new InfoListAdapter(mAPIResponseObject,this);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        mInfoListRV.setLayoutManager(mLayoutManager);
        mInfoListRV.setItemAnimator(new DefaultItemAnimator());
        mInfoListRV.setAdapter(mViewAdapter);
        mViewAdapter.notifyDataSetChanged();
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
            mAPIResponseObject = apiObject;
            setUpCountryList();
        }
    };

    @Override
    public void setUpObservers() {
        mAPIResponseSubscription = mPresenter.getAPIResponseObservable().subscribe(mAPIResponseObserver);
    }
}