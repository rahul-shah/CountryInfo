package countryinfo.rahul.com.countryinfo.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

/**
 * Base activity class.
 */
public abstract class BaseActivity<P extends BasePresenter> extends AppCompatActivity implements BaseView
{

    protected P mPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setupPresenter();
    }

    protected abstract void setupPresenter();

    @Override
    protected void onStart() {
        super.onStart();
        mPresenter.onStart();
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.onResume();
    }

    @Override
    public void onPause() {
        mPresenter.onPause();
        super.onPause();
    }

    @Override
    public void onStop() {
        mPresenter.onStop();
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.onDestroy();
    }

    @Override
    public Context getContext() {
        return this;
    }

    /*@Override
    public void showNoConnectivityMessage() {
        new AlertDialog.Builder(this)
                .setTitle(R.string.error_no_network_title)
                .setMessage(R.string.error_no_network_message)
                .setCancelable(false)
                .setPositiveButton(R.string.close, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        close();
                    }
                })
                .show();
    }*/

    @Override
    public void close() {
        finish();
    }

}