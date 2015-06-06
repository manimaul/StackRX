package com.example.main.user;


import example.com.stackrx.services.questions.model.Item;
import example.com.stackrx.services.questions.model.Questions;

public class UserSession {

    private Questions _questions;
    private Item _selectedQuestion;

    public Questions getQuestions() {
        return _questions;
    }

    public void setQuestions(Questions questions) {
        _questions = questions;
    }

    public Item getSelectedQuestion() {
        return _selectedQuestion;
    }

    public void setSelectedQuestion(Item selectedQuestion) {
        _selectedQuestion = selectedQuestion;
    }

}
