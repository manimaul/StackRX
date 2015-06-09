package com.example.questions.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.main.fragment.StackRXBaseFragment;
import com.example.questions.adapter.QuestionRecyclerViewAdapter;

import butterknife.ButterKnife;
import butterknife.InjectView;
import example.com.stackrx.R;
import example.com.stackrx.services.questions.model.QuestionItem;
import example.com.stackrx.services.questions.model.Questions;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;

public class QuestionsFragment extends StackRXBaseFragment {


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

        addSubscription(_questionRecyclerViewAdapter.getQuestionItemSelected()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<QuestionItem>() {
                    @Override
                    public void call(QuestionItem item) {
                        onQuestionItemSelected(item);
                    }
                }));
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


    //region ACCESSORS -----------------------------------------------------------------------------
    //endregion


    //region INNER CLASSES -------------------------------------------------------------------------
    //endregion


    //region CLASS METHODS -------------------------------------------------------------------------
    //endregion

}
