package com.example.kjp.app;

import android.app.Application;

import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by akbar on 08/07/17.
 */

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        FirebaseDatabase.getInstance().setPersistenceEnabled(true);
    }
}
