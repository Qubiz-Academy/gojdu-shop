package ro.gojdu.shop.ui.auth;

import android.app.Application;

import com.backendless.Backendless;

public class BackendlessApplication extends Application {

    public static final String APPLICATION_ID = "E8BFA3A7-775A-AC87-FF5E-8738A8E8D400";
    public static final String API_KEY = "FFA5F4C3-E0AD-4C6C-AFBB-F8EDE69D5C24";
    public static final String SERVER_URL = "https://api.backendless.com";

    @Override
    public void onCreate() {
        super.onCreate();
        Backendless.setUrl(SERVER_URL);
        Backendless.initApp(getApplicationContext(),
                APPLICATION_ID,
                API_KEY);
    }
}
