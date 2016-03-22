package com.example.injection.module;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import example.com.stackrx.services.questions.service.StackExchangeService;

@Module
public class ServicesModule {

    @Singleton
    @Provides
    StackExchangeService provideQuestionsDAO() {
        return new StackExchangeService();
    }

}
