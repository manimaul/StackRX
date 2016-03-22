package com.example.main.application;

import android.app.Application;

import com.example.injection.component.DaggerDeComponent;
import com.example.injection.component.DeGraphComponent;
import com.example.injection.module.FragmentModule;
import com.example.injection.module.ServicesModule;

public class StackRXApp extends Application {


    //region INJECTED CLASSES ----------------------------------------------------------------------
    //endregion


    //region INJECTED VIEWS ------------------------------------------------------------------------
    //endregion


    //region LOCAL CONSTANTS -----------------------------------------------------------------------

    private static StackRXApp sStackRXApp;

    //endregion


    //region FIELDS --------------------------------------------------------------------------------

    private DeGraphComponent mComponent = null;

    //endregion


    //region CONSTRUCTOR ---------------------------------------------------------------------------
    //endregion


    //region LIFE CYCLE METHODS --------------------------------------------------------------------

    @Override
    public void onCreate() {
        super.onCreate();
        sStackRXApp = this;
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
     * Create the default dependency injection mComponent
     */
    public void buildComponentAndInject() {
        // only set if not null to allow for injection mComponent implementation interface to be overridden
        if (mComponent == null) {
            setComponent(DaggerDeComponent.builder()
                    .servicesModule(new ServicesModule(sStackRXApp))
                    .fragmentModule(new FragmentModule())
                    .build());
        }
    }

    //endregion


    //region SUBSCRIBERS ---------------------------------------------------------------------------
    //endregion


    //region ACCESSORS -----------------------------------------------------------------------------

    /**
     * Set the application dependency injection implementation mComponent
     * @param component the mComponent to set
     * @see {http://google.github.io/dagger/api/latest/dagger/Component.html}
     */
    public void setComponent(DeGraphComponent component) {
        this.mComponent = component;
    }

    //endregion


    //region INNER CLASSES -------------------------------------------------------------------------
    //endregion


    //region CLASS METHODS -------------------------------------------------------------------------

    /**
     * Get the application dependency injection implementation mComponent
     * @return the mComponent
     */
    public static DeGraphComponent component() {
        return sStackRXApp.mComponent;
    }

    //endregion

}
