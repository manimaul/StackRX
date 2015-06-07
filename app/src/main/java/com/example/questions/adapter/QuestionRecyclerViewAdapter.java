package com.example.questions.adapter;

import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.main.application.AppConstants;
import com.example.main.event.NavigationEvent;

import java.util.ArrayList;
import java.util.List;

import example.com.stackrx.R;
import example.com.stackrx.services.questions.model.Item;

public class QuestionRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    //region INJECTED CLASSES ----------------------------------------------------------------------
    //endregion


    //region INJECTED VIEWS ------------------------------------------------------------------------
    //endregion


    //region LOCAL CONSTANTS -----------------------------------------------------------------------
    //endregion


    //region FIELDS --------------------------------------------------------------------------------

    private List<Item> _itemList = new ArrayList<>();

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
        String answerBtnTxt = String.format(itemHolder.itemView.getContext().getString(R.string.item_question_view_answers),
                _itemList.get(i).getAnswerCount());
        itemHolder._viewAnswersButton.setText(answerBtnTxt);

        itemHolder._viewAnswersButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavigationEvent navigationEvent = new NavigationEvent();
                navigationEvent.setDrawerItem(AppConstants.NAVIGATION.DRAWER_IDENTIFIER.QUESTION_DRAWER);
                navigationEvent.setFragmentName(AppConstants.NAVIGATION.NAVIGATION_ROUTES.ANSWER_FRAGMENT);
            }
        });

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
    //endregion


    //region SUBSCRIBERS ---------------------------------------------------------------------------
    //endregion


    //region ACCESSORS -----------------------------------------------------------------------------

    public void setItemList(@Nullable List<Item> itemList) {
        _itemList.clear();
        if (itemList != null) {
            _itemList.addAll(itemList);
        }
    }
    //endregion


    //region INNER CLASSES -------------------------------------------------------------------------

    private class ItemHolder extends RecyclerView.ViewHolder {

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
