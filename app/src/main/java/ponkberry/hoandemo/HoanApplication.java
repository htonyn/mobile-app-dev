package ponkberry.hoandemo;

import android.app.Application;

import ponkberry.hoandemo.util.UtilLog;

/**
 * Created by Ponk on 2/6/2017.
 */

public class HoanApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        UtilLog.setDebug(true);
    }

}
