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

    private List<QuestionItem> _itemList = new ArrayList<>();

    private PublishSubject<QuestionItem> _itemClickPublishSubject = PublishSubject.create();

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
        itemHolder._questionText.setText(_itemList.get(i).getTitle());
        final QuestionItem item = _itemList.get(i);
        String answerBtnTxt = String.format(itemHolder.itemView.getContext().getString(R.string.item_question_view_answers),
                item.getAnswerCount());
        itemHolder._viewAnswersButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _itemClickPublishSubject.onNext(item);
            }
        });
        itemHolder._viewAnswersButton.setText(answerBtnTxt);
    }

    @Override
    public void onViewRecycled(RecyclerView.ViewHolder viewHolder) {
        super.onViewRecycled(viewHolder);
        ItemHolder itemHolder = (ItemHolder) viewHolder;
        itemHolder._viewAnswersButton.setOnClickListener(null);
    }

    //endregion


    //region WIDGET --------------------------------------------------------------------------------

    @Override
    public int getItemCount() {
        return _itemList.size();
    }

    //endregion


    //region LISTENERS -----------------------------------------------------------------------------
    //endregion


    //region EVENTS --------------------------------------------------------------------------------
    //endregion


    //region LOCAL METHODS -------------------------------------------------------------------------

    public Observable<QuestionItem> getQuestionItemSelected() {
        return _itemClickPublishSubject.asObservable();
    }

    //endregion


    //region SUBSCRIBERS ---------------------------------------------------------------------------
    //endregion


    //region ACCESSORS -----------------------------------------------------------------------------

    public void setItemList(@Nullable List<QuestionItem> itemList) {
        _itemList.clear();
        if (itemList != null) {
            _itemList.addAll(itemList);
        }
    }
    //endregion


    //region INNER CLASSES -------------------------------------------------------------------------

    private static class ItemHolder extends RecyclerView.ViewHolder {

        public TextView _questionText;
        public Button _viewAnswersButton;

        private ItemHolder(View itemView) {
            super(itemView);
            _questionText = (TextView) itemView.findViewById(R.id.item_question_question_text_view);
            _viewAnswersButton = (Button) itemView.findViewById(R.id.item_question_view_answers_button);
        }
    }
    //endregion


    //region CLASS METHODS -------------------------------------------------------------------------
    //endregion


}
