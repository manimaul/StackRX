package com.example.questions.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;


import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.InjectView;
import liffft.com.stackrx.R;
import com.example.main.application.StackRXApp;
import com.example.main.fragment.StackRXBaseFragmemt;
import com.example.main.user.UserSession;
import com.example.questions.adapter.QuestionRecyclerViewAdapter;
import example.com.stackrx.services.questions.dao.QuestionsDAO;
import example.com.stackrx.services.questions.model.Questions;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;

public class QuestionsFragment extends StackRXBaseFragmemt {


    //region INJECTED CLASSES ----------------------------------------------------------------------

    @Inject
    QuestionsDAO _questionsDAO;

    @Inject
    UserSession _userSession;

    //endregion

    //region INJECTED VIEWS ------------------------------------------------------------------------

    @InjectView(R.id.question_fragment_question_recycler_view)
    RecyclerView _recyclerView;

    //endregion


    //region LOCAL CONSTANTS -----------------------------------------------------------------------
    //endregion


    //region FIELDS --------------------------------------------------------------------------------

    private Activity _activity;

    private QuestionRecyclerViewAdapter _questionRecyclerViewAdapter;

    private GetQuestionSubscriber _getQuestionSubscriber;

    //endregion

    //region CONSTRUCTOR ---------------------------------------------------------------------------

    public QuestionsFragment() {
    }

    //endregion

    //region LIFE CYCLE METHODS --------------------------------------------------------------------


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StackRXApp.component().inject(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.questions_fragment, container, false);
        ButterKnife.inject(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Setup recycler view
        _recyclerView.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        _recyclerView.setLayoutManager(layoutManager);

        // Subscribe and call the observable
        _getQuestionSubscriber = new GetQuestionSubscriber();
        _compositeSubscription.add(_questionsDAO.getQuestions()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(_getQuestionSubscriber));

        _questionRecyclerViewAdapter = new QuestionRecyclerViewAdapter(getActivity());
        _recyclerView.setAdapter(_questionRecyclerViewAdapter);

        _activity = getActivity();
    }

    //endregion


    //region WIDGET --------------------------------------------------------------------------------
    //endregion


    //region LISTENERS -----------------------------------------------------------------------------
    //endregion


    //region EVENTS --------------------------------------------------------------------------------
    //endregion


    //region LOCAL METHODS -------------------------------------------------------------------------
    //endregion


    //region SUBSCRIBERS ---------------------------------------------------------------------------

    private class GetQuestionSubscriber implements Observer<Questions> {

        @Override
        public void onCompleted() {

        }

        @Override
        public void onError(Throwable e) {
            Toast.makeText(_activity, _activity.getString(R.string.service_error), Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onNext(Questions questions) {
            _userSession.setQuestions(questions);
            _questionRecyclerViewAdapter.setItemList(questions.getItems());
            _questionRecyclerViewAdapter.notifyDataSetChanged();
        }
    }

    //endregion


    //region ACCESSORS -----------------------------------------------------------------------------
    //endregion


    //region INNER CLASSES -------------------------------------------------------------------------
    //endregion


    //region CLASS METHODS -------------------------------------------------------------------------
    //endregion

}
