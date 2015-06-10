package com.example.injection.module;

import com.example.main.activity.InitialFragment;
import com.example.main.fragment.QuestionsFragment;
import com.example.main.fragment.StackRXBaseFragment;

import dagger.Module;
import dagger.Provides;

@Module
public class FragmentModule {

    @Provides
    InitialFragment provideFragmentCreator() {

        return new InitialFragment() {
            @Override
            public StackRXBaseFragment createInitialFragment() {
                return new QuestionsFragment();
            }
        };
    }


}
