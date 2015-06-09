package com.example.main.user;


import example.com.stackrx.services.questions.model.QuestionItem;
import example.com.stackrx.services.questions.model.Questions;

public class UserSession {

    private Questions _questions;
    private QuestionItem _selectedQuestion;

    public Questions getQuestions() {
        return _questions;
    }

    public void setQuestions(Questions questions) {
        _questions = questions;
    }

    public QuestionItem getSelectedQuestion() {
        return _selectedQuestion;
    }

    public void setSelectedQuestion(QuestionItem selectedQuestion) {
        _selectedQuestion = selectedQuestion;
    }

}
