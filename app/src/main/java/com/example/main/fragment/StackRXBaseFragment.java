package com.example.main.fragment;

import android.app.Fragment;
import android.net.ConnectivityManager;
import android.os.Bundle;

import com.example.main.application.StackRXApp;

import javax.inject.Inject;

import dagger.Lazy;
import example.com.stackrx.services.questions.service.StackExchangeService;
import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

public abstract class StackRXBaseFragment extends Fragment {


    //region INJECTED CLASSES ----------------------------------------------------------------------

    @Inject
    Lazy<StackExchangeService> mLazyQuestionsDAO;

    @Inject
    Lazy<ConnectivityManager> mLazyConnectivityManager;

    //endregion


    //region INJECTED VIEWS ------------------------------------------------------------------------
    //endregion


    //region LOCAL CONSTANTS -----------------------------------------------------------------------
    //endregion


    //region FIELDS --------------------------------------------------------------------------------

    private CompositeSubscription mCompositeSubscription = new CompositeSubscription();

    //endregion


    //region CONSTRUCTOR ---------------------------------------------------------------------------
    //endregion


    //region LIFE CYCLE METHODS --------------------------------------------------------------------


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StackRXApp.component().inject(this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mCompositeSubscription.unsubscribe();
    }

    //endregion


    //region WIDGET --------------------------------------------------------------------------------
    //endregion


    //region LISTENERS -----------------------------------------------------------------------------
    //endregion


    //region EVENTS --------------------------------------------------------------------------------
    //endregion


    //region LOCAL METHODS -------------------------------------------------------------------------

    /**
     * Adds a subscription to the internal CompositeSubscription
     *      (http://reactivex.io/RxJava/javadoc/rx/subscriptions/CompositeSubscription.html)
     *      Subscription that represents a group of Subscriptions that are unsubscribed together.
     *
     * which will be un-subscribed from onDestroy()
     *
     * @param subscription the Subscription to add
     */
    public void addSubscription(Subscription subscription) {
        mCompositeSubscription.add(subscription);
    }

    //endregion


    //region SUBSCRIBERS ---------------------------------------------------------------------------
    //endregion


    //region ACCESSORS -----------------------------------------------------------------------------

    public StackExchangeService getQuestionsDAO() {
        return mLazyQuestionsDAO.get();
    }

    public ConnectivityManager getConnectivityManager() {
        return mLazyConnectivityManager.get();
    }

    //endregion


    //region INNER CLASSES -------------------------------------------------------------------------
    //endregion


    //region CLASS METHODS -------------------------------------------------------------------------
    //endregion

}
