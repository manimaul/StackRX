package com.example.injection;

import com.example.injection.component.ApplicationComponent;
import com.example.injection.component.DaggerApplicationComponent;
import com.example.injection.module.AndroidModule;
import com.example.injection.module.ServicesModule;
import com.example.main.application.StackRXApp;

/**
 * Provides global access to dependency injection.
 */
public class Injector {

    private static final Injector INJECTOR = new Injector();

    private ApplicationComponent mApplicationComponent;

    private Injector() {
    }

    public static Injector getInstance() {
        return INJECTOR;
    }

    /**
     * Build the Dagger Application (Singleton) Scope injection component.
     *
     * @param application the application.
     */
    public void buildApplicationComponent(StackRXApp application) {
        mApplicationComponent = DaggerApplicationComponent.builder()
                .androidModule(new AndroidModule(application))
                .servicesModule(new ServicesModule())
                .build();
    }

    /**
     * Get the application scope Dagger2 component injector.
     *
     * @return the application scope injector.
     */
    public static ApplicationComponent applicationScope() {
        return INJECTOR.mApplicationComponent;
    }
}
