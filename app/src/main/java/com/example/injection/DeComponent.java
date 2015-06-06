package com.example.injection;

import com.example.questions.fragment.QuestionsFragment;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = ServicesModule.class)
public interface DeComponent {

    void inject(QuestionsFragment questionsFragment);
}
