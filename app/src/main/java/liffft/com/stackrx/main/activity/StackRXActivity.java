package liffft.com.stackrx.main.activity;

import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;

import butterknife.ButterKnife;
import butterknife.InjectView;
import liffft.com.stackrx.R;
import liffft.com.stackrx.questions.fragment.QuestionsFragment;

public class StackRXActivity extends AppCompatActivity {


    //region INJECTED CLASSES ----------------------------------------------------------------------
    //endregion

    //region INJECTED VIEWS ------------------------------------------------------------------------

    @InjectView(R.id.drawer_layout)
    DrawerLayout mDrawerLayout;

    //endregion


    //region FIELDS --------------------------------------------------------------------------------
    //endregion


    //region CONSTRUCTOR ---------------------------------------------------------------------------

    public StackRXActivity() {
    }

    //endregion


    //region LIFE CYCLE METHODS --------------------------------------------------------------------

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.stack_rx_activity);
        ButterKnife.inject(this);
    }

    @Override
    protected void onResume() {
        super.onResume();

        getSupportFragmentManager().beginTransaction()
                .add(R.id.container, new QuestionsFragment())
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
