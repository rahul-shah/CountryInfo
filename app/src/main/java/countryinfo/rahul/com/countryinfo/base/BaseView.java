package countryinfo.rahul.com.countryinfo.base;

import android.content.Context;

/**
 * Base view.
 */
public interface BaseView {

    Context getContext();

    void setUpObservers();

    void close();

}