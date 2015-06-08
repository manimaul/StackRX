package com.example.main.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;

import com.example.injection.module.FragmentModule;
import com.example.main.application.StackRXApp;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.InjectView;
import example.com.stackrx.R;

public class StackRXActivity extends Activity {


    //region INJECTED CLASSES ----------------------------------------------------------------------

    @Inject
    InitialFragment _initialFragment;

    //endregion


    //region INJECTED VIEWS ------------------------------------------------------------------------

    @InjectView(R.id.drawer_layout)
    DrawerLayout _drawerLayout;

    //endregion


    //region FIELDS --------------------------------------------------------------------------------
    //endregion


    //region CONSTRUCTOR ---------------------------------------------------------------------------
    //endregion


    //region LIFE CYCLE METHODS --------------------------------------------------------------------

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.stack_rx_activity);
        ButterKnife.inject(this);
        StackRXApp.component().inject(this);
    }

    @Override
    protected void onResume() {
        super.onResume();

        /**
         *  _initialFragment is indirect: the purpose is to allow DI to change the initial fragment
         * @see FragmentModule#provideFragmentCreator()
         */
        getFragmentManager().beginTransaction()
                .add(R.id.container, _initialFragment.createInitialFragment())
                .commit();
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
