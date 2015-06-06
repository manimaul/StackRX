package com.example.questions.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.main.application.StackRXApp;
import com.example.main.fragment.StackRXBaseFragmemt;
import com.example.main.user.UserSession;
import com.example.questions.adapter.QuestionRecyclerViewAdapter;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.InjectView;
import example.com.stackrx.services.questions.dao.QuestionsDAO;
import example.com.stackrx.services.questions.model.Questions;
import liffft.com.stackrx.R;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;

public class QuestionsFragment extends StackRXBaseFragmemt {


    //region INJECTED CLASSES ----------------------------------------------------------------------
    //endregion

    //region INJECTED VIEWS ------------------------------------------------------------------------

    @InjectView(R.id.question_fragment_question_recycler_view)
    RecyclerView _recyclerView;

    //endregion


    //region LOCAL CONSTANTS -----------------------------------------------------------------------
    //endregion


    //region FIELDS --------------------------------------------------------------------------------

    private Context _context;

    private QuestionRecyclerViewAdapter _questionRecyclerViewAdapter;

    //endregion


    //region CONSTRUCTOR ---------------------------------------------------------------------------
    //endregion


    //region LIFE CYCLE METHODS --------------------------------------------------------------------


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StackRXApp.component().inject(this);
        _context = getActivity().getBaseContext();
        _questionRecyclerViewAdapter = new QuestionRecyclerViewAdapter();
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
        LinearLayoutManager layoutManager = new LinearLayoutManager(_context);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        _recyclerView.setHasFixedSize(true);
        _recyclerView.setLayoutManager(layoutManager);
        _recyclerView.setAdapter(_questionRecyclerViewAdapter);
    }

    @Override
    public void onResume() {
        super.onResume();
        apiGetQuestions();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        _recyclerView.setAdapter(null);
        _recyclerView.setLayoutManager(null);
    }

    private void apiGetQuestions() {
        addSubscription(getQuestionsDAO().getQuestions()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Questions>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Toast.makeText(_context, _context.getString(R.string.service_error),
                                Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onNext(Questions questions) {
                        getUserSession().setQuestions(questions);
                        _questionRecyclerViewAdapter.setItemList(questions.getItems());
                        _questionRecyclerViewAdapter.notifyDataSetChanged();
                    }
                }));
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
    //endregion


    //region ACCESSORS -----------------------------------------------------------------------------
    //endregion


    //region INNER CLASSES -------------------------------------------------------------------------
    //endregion


    //region CLASS METHODS -------------------------------------------------------------------------
    //endregion

}
