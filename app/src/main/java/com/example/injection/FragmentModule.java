package com.example.injection;

import com.example.main.fragment.InitialFragment;
import com.example.main.fragment.StackRXBaseFragmemt;
import com.example.questions.fragment.QuestionsFragment;

import dagger.Module;
import dagger.Provides;

@Module
public class FragmentModule {

    @Provides
    InitialFragment provideFragmentCreator() {

        return new InitialFragment() {
            @Override
            public StackRXBaseFragmemt createInitialFragment() {
                return new QuestionsFragment();
            }
        };
    }


}
