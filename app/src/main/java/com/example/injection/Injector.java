package com.example.injection;

import com.example.injection.component.ApplicationComponent;
import com.example.injection.component.DaggerApplicationComponent;
import com.example.injection.module.AndroidModule;
import com.example.injection.module.ServicesModule;
import com.example.main.activity.StackRXActivity;
import com.example.main.application.StackRXApp;
import com.example.main.fragment.QuestionsFragment;

/**
 * Provides global access to dependency injection.
 */
public class Injector {

    //region LOCAL CONSTANTS -----------------------------------------------------------------------

    private static final Injector INJECTOR = new Injector();

    //endregion

    //region FIELDS --------------------------------------------------------------------------------

    private ApplicationComponent mApplicationComponent;

    //endregion

    //region CONSTRUCTOR ---------------------------------------------------------------------------

    private Injector() {
    }

    //endregion

    //region LOCAL METHODS -------------------------------------------------------------------------
    //endregion

    //region ACCESSORS -----------------------------------------------------------------------------

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

    //endregion

    //region INNER CLASSES -------------------------------------------------------------------------
    //endregion

    //region CLASS METHODS -------------------------------------------------------------------------

    /**
     * Inject dependencies.
     */
    public static void inject(StackRXActivity activity) {
        INJECTOR.mApplicationComponent.inject(activity);
    }

    /**
     * Inject dependencies.
     */
    public static void inject(QuestionsFragment fragment) {
        INJECTOR.mApplicationComponent.inject(fragment);
    }

    //endregion

}
