package com.example.main.adapter;

import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import example.com.stackrx.R;
import example.com.stackrx.databinding.ItemQuestionBinding;
import example.com.stackrx.services.questions.model.QuestionItem;
import io.reactivex.Observable;
import io.reactivex.subjects.PublishSubject;

public class QuestionRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<QuestionItem> mItemList = new ArrayList<>();
    private PublishSubject<QuestionItem> mItemClickPublishSubject = PublishSubject.create();

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        return new ItemHolder(ItemQuestionBinding.inflate(inflater, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, final int i) {
        ItemHolder itemHolder = (ItemHolder) viewHolder;
        final QuestionItem item = mItemList.get(i);
        itemHolder.bind(item, mItemClickPublishSubject);
    }

    @Override
    public void onViewRecycled(RecyclerView.ViewHolder viewHolder) {
        super.onViewRecycled(viewHolder);
        ItemHolder itemHolder = (ItemHolder) viewHolder;
        itemHolder.unBind();
    }

    @Override
    public int getItemCount() {
        return mItemList.size();
    }

    public Observable<QuestionItem> getQuestionItemSelected() {
        return mItemClickPublishSubject;
    }

    public void setItemList(@Nullable List<QuestionItem> itemList) {
        mItemList.clear();
        if (itemList != null) {
            mItemList.addAll(itemList);
        }
        notifyDataSetChanged();
    }

    private static class ItemHolder extends RecyclerView.ViewHolder {

        private ItemQuestionBinding viewBinding;

        private ItemHolder(ItemQuestionBinding viewBinding) {
            super(viewBinding.getRoot());
            this.viewBinding = viewBinding;
        }

        void bind(QuestionItem questionItem, PublishSubject<QuestionItem> publishSubject) {
            viewBinding.questionTextView.setText(Html.fromHtml(questionItem.getTitle()));
            String answerBtnTxt = String.format(itemView.getContext().getString(R.string.item_question_view_answers),
                questionItem.getAnswerCount());
            viewBinding.viewAnswersButton.setText(answerBtnTxt);
            viewBinding.viewAnswersButton.setOnClickListener(view -> publishSubject.onNext(questionItem));
            viewBinding.executePendingBindings();
        }

        void unBind() {
            viewBinding.questionTextView.setOnClickListener(null);
        }
    }
}
