package com.example.injection;

import javax.inject.Singleton;

import dagger.Component;
import com.example.questions.fragment.QuestionsFragment;

@Singleton
@Component(modules = ServicesModule.class)
public interface DeComponent {

    void inject(QuestionsFragment questionsFragment);
}
