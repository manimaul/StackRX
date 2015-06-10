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

    private static StackRXApp APP;

    //endregion


    //region FIELDS --------------------------------------------------------------------------------

    private DeGraphComponent _component = null;

    //endregion


    //region CONSTRUCTOR ---------------------------------------------------------------------------
    //endregion


    //region LIFE CYCLE METHODS --------------------------------------------------------------------

    @Override
    public void onCreate() {
        super.onCreate();
        APP = this;
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
     * Create the default dependency injection _component
     */
    public void buildComponentAndInject() {
        // only set if not null to allow for injection _component implementation interface to be overridden
        if (_component == null) {
            setComponent(DaggerDeComponent.builder()
                    .servicesModule(new ServicesModule(APP))
                    .fragmentModule(new FragmentModule())
                    .build());
        }
    }

    //endregion


    //region SUBSCRIBERS ---------------------------------------------------------------------------
    //endregion


    //region ACCESSORS -----------------------------------------------------------------------------

    /**
     * Set the application dependency injection implementation _component
     * @param component the _component to set
     * @see {http://google.github.io/dagger/api/latest/dagger/Component.html}
     */
    public void setComponent(DeGraphComponent component) {
        this._component = component;
    }

    //endregion


    //region INNER CLASSES -------------------------------------------------------------------------
    //endregion


    //region CLASS METHODS -------------------------------------------------------------------------

    /**
     * Get the application dependency injection implementation _component
     * @return the _component
     */
    public static DeGraphComponent component() {
        return APP._component;
    }

    //endregion

}
