package liffft.com.stackrx.main.application;

import android.app.Application;

import liffft.com.stackrx.dependency.injection.DaggerDeComponent;
import liffft.com.stackrx.dependency.injection.DeComponent;
import liffft.com.stackrx.dependency.injection.ServicesModule;

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

    public void buildComponentAndInject() {
        if (component == null) {
            app = this;
            component = DaggerDeComponent.builder()
                    .servicesModule(new ServicesModule(app))
                    .build();
        }
    }

    public static DeComponent component() {
        return app.component;
    }

    //endregion


    //region WIDGET --------------------------------------------------------------------------------
    //endregion


    //region LISTENERS -----------------------------------------------------------------------------
    //endregion


    //region EVENTS --------------------------------------------------------------------------------
    //endregion


    //region LOCAL METHODS -------------------------------------------------------------------------
    //endregion


    //region SUBSCRIBERS ---------------------------------------------------------------------------
    //endregion


    //region ACCESSORS -----------------------------------------------------------------------------
    //endregion


    //region INNER CLASSES -------------------------------------------------------------------------
    //endregion


    //region CLASS METHODS -------------------------------------------------------------------------
    //endregion

}
