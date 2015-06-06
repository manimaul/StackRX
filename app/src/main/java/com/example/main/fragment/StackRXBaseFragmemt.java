package com.example.main.fragment;

import android.support.v4.app.Fragment;

import com.example.main.user.UserSession;

import javax.inject.Inject;

import dagger.Lazy;
import example.com.stackrx.services.questions.dao.QuestionsDAO;
import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

public class StackRXBaseFragmemt extends Fragment {


    //region INJECTED CLASSES ----------------------------------------------------------------------

    @Inject
    Lazy<QuestionsDAO> _lazyQuestionsDAO;

    @Inject
    Lazy<UserSession> _lazyUserSession;

    //endregion


    //region INJECTED VIEWS ------------------------------------------------------------------------
    //endregion


    //region LOCAL CONSTANTS -----------------------------------------------------------------------
    //endregion


    //region FIELDS --------------------------------------------------------------------------------

    private CompositeSubscription _compositeSubscription = new CompositeSubscription();

    //endregion


    //region CONSTRUCTOR ---------------------------------------------------------------------------
    //endregion


    //region LIFE CYCLE METHODS --------------------------------------------------------------------

    @Override
    public void onDestroy() {
        super.onDestroy();
        _compositeSubscription.unsubscribe();
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
     * Adds a subscription to the internal CompositeSubscription (http://reactivex.io/RxJava/javadoc/rx/subscriptions/CompositeSubscription.html)
     * which will be un-subscribed from onDestroy()
     *
     * @param subscription the Subscription to add
     */
    public void addSubscription(Subscription subscription) {
        _compositeSubscription.add(subscription);
    }

    //endregion


    //region SUBSCRIBERS ---------------------------------------------------------------------------
    //endregion


    //region ACCESSORS -----------------------------------------------------------------------------

    public UserSession getUserSession() {
        return _lazyUserSession.get();
    }

    public QuestionsDAO getQuestionsDAO() {
        return _lazyQuestionsDAO.get();
    }

    //endregion


    //region INNER CLASSES -------------------------------------------------------------------------
    //endregion


    //region CLASS METHODS -------------------------------------------------------------------------
    //endregion

}
