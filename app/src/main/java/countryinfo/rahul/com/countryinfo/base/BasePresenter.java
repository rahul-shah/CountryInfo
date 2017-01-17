package countryinfo.rahul.com.countryinfo.base;

/**
 * Base presenter class.
 */
public abstract class BasePresenter {

    public abstract BaseView getView();

    protected void onStart() {
        // not req'd in base class
    }

    protected void onResume() {
        // not req'd in base class
    }

    protected void onPause() {
        // not req'd in base class
    }

    protected void onStop() {
        // not req'd in base class
    }

    protected void onDestroy() {
        // not req'd in base class
    }

}