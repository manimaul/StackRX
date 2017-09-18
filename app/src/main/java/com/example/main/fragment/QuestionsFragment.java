package com.example.main.fragment;

import android.app.Fragment;
import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.injection.Injector;
import com.example.main.adapter.QuestionRecyclerViewAdapter;

import javax.inject.Inject;

import example.com.stackrx.databinding.QuestionsFragmentBinding;
import example.com.stackrx.services.questions.model.QuestionItem;
import example.com.stackrx.services.questions.service.StackExchangeService;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;

public class QuestionsFragment extends Fragment {


    //region INJECTED CLASSES ----------------------------------------------------------------------

    @Inject Context mContext;
    @Inject Resources mResources;
    @Inject StackExchangeService mStackExchangeService;

    //endregion

    //region INJECTED VIEWS ------------------------------------------------------------------------

    private QuestionsFragmentBinding viewBinding;

    //endregion

    //region LOCAL CONSTANTS -----------------------------------------------------------------------

    public static final String TAG = QuestionsFragment.class.getSimpleName();

    //endregion

    //region FIELDS --------------------------------------------------------------------------------

    private QuestionRecyclerViewAdapter mQuestionRecyclerViewAdapter;
    private CompositeDisposable mCompositeSubscription = new CompositeDisposable();

    //endregion

    //region CONSTRUCTOR ---------------------------------------------------------------------------
    //endregion

    //region LIFE CYCLE METHODS --------------------------------------------------------------------


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Injector.getApplicationScope().inject(this);
        mQuestionRecyclerViewAdapter = new QuestionRecyclerViewAdapter();
    }

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {
        viewBinding = QuestionsFragmentBinding.inflate(inflater, container, false);
        return viewBinding.getRoot();
    }

    @Override
    public void onViewCreated(View view,
                              Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setupAdapter();

        apiGetQuestions();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mCompositeSubscription.clear();
        viewBinding.unbind();
        viewBinding = null;
    }

    //endregion

    //region PRIVATE METHODS -----------------------------------------------------------------------

    private void setupAdapter() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(mContext);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        viewBinding.recyclerView.setHasFixedSize(true);
        viewBinding.recyclerView.setLayoutManager(layoutManager);
        viewBinding.recyclerView.setAdapter(mQuestionRecyclerViewAdapter);

        mCompositeSubscription.add(mQuestionRecyclerViewAdapter.getQuestionItemSelected()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(this::onQuestionItemSelected));
    }


    private void apiGetQuestions() {
        mCompositeSubscription.add(mStackExchangeService.questions()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe( questions -> mQuestionRecyclerViewAdapter.setItemList(questions.getItems()), this::questionError));
    }

    private void questionError(Throwable throwable) {
        Log.e(TAG, "", throwable);
    }

    private void onQuestionItemSelected(QuestionItem item) {
        Log.i(TAG, "question item selected: " + item.getTitle());
        //todo: implement me
    }

    //endregion

    //region ACCESSORS -----------------------------------------------------------------------------
    //endregion

    //region INNER CLASSES -------------------------------------------------------------------------
    //endregion
}
