package fr.smarquis.usi;

import android.app.Application;
import android.content.ComponentName;
import android.content.pm.PackageManager;

public final class Usi {

    private Usi() {
    }

    public static void intercept(Application application) {
        application.getPackageManager().setComponentEnabledSetting(new ComponentName(application, InterceptorActivity.class), PackageManager.COMPONENT_ENABLED_STATE_ENABLED, PackageManager.DONT_KILL_APP);
    }

    public static void ignore(Application application) {
        application.getPackageManager().setComponentEnabledSetting(new ComponentName(application, InterceptorActivity.class), PackageManager.COMPONENT_ENABLED_STATE_DISABLED, PackageManager.DONT_KILL_APP);
    }
}
