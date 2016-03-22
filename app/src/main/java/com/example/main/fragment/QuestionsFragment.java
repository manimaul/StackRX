package com.example.main.fragment;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.injection.Injector;
import com.example.main.adapter.QuestionRecyclerViewAdapter;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import example.com.stackrx.R;
import example.com.stackrx.services.questions.model.QuestionItem;
import example.com.stackrx.services.questions.model.Questions;
import example.com.stackrx.services.questions.service.StackExchangeService;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.subscriptions.CompositeSubscription;

public class QuestionsFragment extends Fragment {


    //region INJECTED CLASSES ----------------------------------------------------------------------

    @Inject
    Context mContext;

    @Inject
    StackExchangeService mStackExchangeService;

    //endregion

    //region INJECTED VIEWS ------------------------------------------------------------------------

    @Bind(R.id.question_fragment_question_recycler_view)
    RecyclerView mRecyclerView;

    //endregion


    //region LOCAL CONSTANTS -----------------------------------------------------------------------
    //endregion


    //region FIELDS --------------------------------------------------------------------------------

    private QuestionRecyclerViewAdapter mQuestionRecyclerViewAdapter;
    private CompositeSubscription mCompositeSubscription;

    //endregion


    //region CONSTRUCTOR ---------------------------------------------------------------------------
    //endregion


    //region LIFE CYCLE METHODS --------------------------------------------------------------------


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Injector.inject(this);
        mQuestionRecyclerViewAdapter = new QuestionRecyclerViewAdapter();
        mCompositeSubscription = new CompositeSubscription();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.questions_fragment, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Setup recycler view
        LinearLayoutManager layoutManager = new LinearLayoutManager(mContext);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setAdapter(mQuestionRecyclerViewAdapter);

        mCompositeSubscription.add(mQuestionRecyclerViewAdapter.getQuestionItemSelected()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<QuestionItem>() {
                    @Override
                    public void call(QuestionItem item) {
                        onQuestionItemSelected(item);
                    }
                }));

        apiGetQuestions();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
        mCompositeSubscription.unsubscribe();
        mRecyclerView.setAdapter(null);
        mRecyclerView.setLayoutManager(null);
    }

    //endregion


    //region WIDGET --------------------------------------------------------------------------------
    //endregion


    //region LISTENERS -----------------------------------------------------------------------------
    //endregion


    //region EVENTS --------------------------------------------------------------------------------
    //endregion


    //region LOCAL METHODS -------------------------------------------------------------------------

    private void onQuestionItemSelected(QuestionItem item) {
        Log.i("TAG", "question item selected: " + item.getTitle());
        //todo: implement me
    }

    private void apiGetQuestions() {
        mCompositeSubscription.add(mStackExchangeService.getQuestions()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Questions>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Toast.makeText(mContext, mContext.getString(R.string.service_error),
                                Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onNext(Questions questions) {
                        mQuestionRecyclerViewAdapter.setItemList(questions.getItems());
                        mQuestionRecyclerViewAdapter.notifyDataSetChanged();
                    }
                }));
    }

    //endregion


    //region ACCESSORS -----------------------------------------------------------------------------
    //endregion


    //region INNER CLASSES -------------------------------------------------------------------------
    //endregion


    //region CLASS METHODS -------------------------------------------------------------------------
    //endregion

}
