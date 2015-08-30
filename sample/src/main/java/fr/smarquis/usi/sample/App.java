package fr.smarquis.usi.sample;

import android.app.Application;

import fr.smarquis.usi.Usi;

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Usi.intercept(this);
    }

}
