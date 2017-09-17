package com.example.main.activity;

import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;

import com.example.injection.Injector;
import com.example.main.fragment.QuestionsFragment;

import example.com.stackrx.R;

public class StackRXActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Injector.applicationScope().inject(this);
        setContentView(R.layout.stack_rx_activity);
        getFragmentManager().beginTransaction()
                .replace(R.id.container, new QuestionsFragment())
                .commit();
    }

}
