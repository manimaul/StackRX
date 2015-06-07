package com.example.main.application;

import android.app.Application;

import com.example.injection.DaggerDeComponent;
import com.example.injection.DeGraphComponent;
import com.example.injection.FragmentModule;
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

    private DeGraphComponent component = null;

    //endregion


    //region CONSTRUCTOR ---------------------------------------------------------------------------
    //endregion


    //region LIFE CYCLE METHODS --------------------------------------------------------------------

    @Override
    public void onCreate() {
        super.onCreate();
        app = this;
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
     * Create the default dependency injection component
     */
    public void buildComponentAndInject() {
        // only set if not null to allow for injection component implementation interface to be overridden
        if (component == null) {
            setComponent(DaggerDeComponent.builder()
                    .servicesModule(new ServicesModule(app))
                    .fragmentModule(new FragmentModule())
                    .build());
        }
    }

    //endregion


    //region SUBSCRIBERS ---------------------------------------------------------------------------
    //endregion


    //region ACCESSORS -----------------------------------------------------------------------------

    /**
     * Set the application dependency injection implementation component
     * @param component the component to set
     * @see {http://google.github.io/dagger/api/latest/dagger/Component.html}
     */
    public void setComponent(DeGraphComponent component) {
        this.component = component;
    }

    //endregion


    //region INNER CLASSES -------------------------------------------------------------------------
    //endregion


    //region CLASS METHODS -------------------------------------------------------------------------

    /**
     * Get the application dependency injection implementation component
     * @return the component
     */
    public static DeGraphComponent component() {
        return app.component;
    }

    //endregion

}
