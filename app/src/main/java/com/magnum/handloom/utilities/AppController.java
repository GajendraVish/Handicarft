package com.magnum.handloom.utilities;

/**
 * Created by cnet on 1/18/2016.
 */

import android.app.Application;

import com.reactiveandroid.ReActiveAndroid;
import com.reactiveandroid.ReActiveConfig;
import com.reactiveandroid.internal.database.DatabaseConfig;

public class AppController extends Application {

    private static AppController mInstance;
    public static synchronized AppController getInstance() {
        return mInstance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;

        DatabaseConfig appDatabase = new DatabaseConfig.Builder(AppDatabase.class)
                .build();
        ReActiveAndroid.init(new ReActiveConfig.Builder(this)
                .addDatabaseConfigs(appDatabase)
                .build());

    }
}

