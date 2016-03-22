package com.example.main.adapter;

import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import example.com.stackrx.R;
import example.com.stackrx.services.questions.model.QuestionItem;
import rx.Observable;
import rx.subjects.PublishSubject;

public class QuestionRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    //region INJECTED CLASSES ----------------------------------------------------------------------
    //endregion

    //region INJECTED VIEWS ------------------------------------------------------------------------
    //endregion

    //region LOCAL CONSTANTS -----------------------------------------------------------------------
    //endregion

    //region FIELDS --------------------------------------------------------------------------------

    private List<QuestionItem> mItemList = new ArrayList<>();

    private PublishSubject<QuestionItem> mItemClickPublishSubject = PublishSubject.create();

    //endregion


    //region CONSTRUCTOR ---------------------------------------------------------------------------
    //endregion

    //region LIFE CYCLE METHODS --------------------------------------------------------------------

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {

        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());

        View v = inflater.inflate(R.layout.item_question, viewGroup, false);
        return new ItemHolder(v);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, final int i) {
        ItemHolder itemHolder = (ItemHolder) viewHolder;
        itemHolder.mQuestionText.setText(mItemList.get(i).getTitle());
        final QuestionItem item = mItemList.get(i);
        String answerBtnTxt = String.format(itemHolder.itemView.getContext().getString(R.string.item_question_view_answers),
                item.getAnswerCount());
        itemHolder.mViewAnswersButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mItemClickPublishSubject.onNext(item);
            }
        });
        itemHolder.mViewAnswersButton.setText(answerBtnTxt);
    }

    @Override
    public void onViewRecycled(RecyclerView.ViewHolder viewHolder) {
        super.onViewRecycled(viewHolder);
        ItemHolder itemHolder = (ItemHolder) viewHolder;
        itemHolder.mViewAnswersButton.setOnClickListener(null);
    }

    //endregion

    //region WIDGET --------------------------------------------------------------------------------

    @Override
    public int getItemCount() {
        return mItemList.size();
    }

    //endregion

    //region LISTENERS -----------------------------------------------------------------------------
    //endregion

    //region EVENTS --------------------------------------------------------------------------------
    //endregion

    //region LOCAL METHODS -------------------------------------------------------------------------

    public Observable<QuestionItem> getQuestionItemSelected() {
        return mItemClickPublishSubject.asObservable();
    }

    //endregion


    //region OBSERVERS -----------------------------------------------------------------------------
    //endregion

    //region ACCESSORS -----------------------------------------------------------------------------

    public void setItemList(@Nullable List<QuestionItem> itemList) {
        mItemList.clear();
        if (itemList != null) {
            mItemList.addAll(itemList);
        }
    }
    //endregion

    //region INNER CLASSES -------------------------------------------------------------------------

    static class ItemHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.item_question_question_text_view)
        TextView mQuestionText;

        @Bind(R.id.item_question_view_answers_button)
        Button mViewAnswersButton;

        private ItemHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(itemView);
        }
    }
    //endregion

    //region CLASS METHODS -------------------------------------------------------------------------
    //endregion

}
