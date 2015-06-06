package com.example.main.application;

import android.app.Application;

import com.example.injection.DaggerDeComponent;
import com.example.injection.DeComponent;
import com.example.injection.ServicesModule;

public class StackRXApp extends Application {


    //region INJECTED CLASSES ----------------------------------------------------------------------
    //endregion


    //region INJECTED VIEWS ------------------------------------------------------------------------
    //endregion


    //region LOCAL CONSTANTS -----------------------------------------------------------------------

    private static StackRXApp app;

    //endregion


    //region FIELDS --------------------------------------------------------------------------------

    private DeComponent component = null;

    //endregion


    //region CONSTRUCTOR ---------------------------------------------------------------------------
    //endregion


    //region LIFE CYCLE METHODS --------------------------------------------------------------------

    @Override
    public void onCreate() {
        super.onCreate();
        buildComponentAndInject();
    }

    //endregion


    //region WIDGET --------------------------------------------------------------------------------
    //endregion


    //region LISTENERS -----------------------------------------------------------------------------
    //endregion


    //region EVENTS --------------------------------------------------------------------------------
    //endregion


    //region LOCAL METHODS -------------------------------------------------------------------------

    /**
     * Create dependency injection component
     */
    public void buildComponentAndInject() {
        if (component == null || app == null) {
            app = this;
            component = DaggerDeComponent.builder()
                    .servicesModule(new ServicesModule(app))
                    .build();
        }
    }

    //endregion


    //region SUBSCRIBERS ---------------------------------------------------------------------------
    //endregion


    //region ACCESSORS -----------------------------------------------------------------------------
    //endregion


    //region INNER CLASSES -------------------------------------------------------------------------
    //endregion


    //region CLASS METHODS -------------------------------------------------------------------------

    /**
     *
     * @return the dependency injection component
     */
    public static DeComponent component() {
        return app.component;
    }

    //endregion

}
