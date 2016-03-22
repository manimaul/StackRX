package com.example.injection.component;

import com.example.injection.module.AndroidModule;
import com.example.injection.module.ServicesModule;
import com.example.main.activity.StackRXActivity;
import com.example.main.fragment.QuestionsFragment;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Dependency injection component for the Singleton / Application scope
 */
@Singleton
@Component(modules = {AndroidModule.class, ServicesModule.class})
public interface ApplicationComponent {
    void inject(StackRXActivity stackRXActivity);

    void inject(QuestionsFragment questionsFragment);
}
