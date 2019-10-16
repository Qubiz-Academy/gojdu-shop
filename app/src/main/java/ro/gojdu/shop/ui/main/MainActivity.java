package ro.gojdu.shop.ui.main;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.backendless.Backendless;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.exceptions.BackendlessFault;
import com.google.android.material.navigation.NavigationView;

import ro.gojdu.shop.R;
import ro.gojdu.shop.ui.auth.LoginActivity;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);

        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_mycart, R.id.nav_logout)
                .setDrawerLayout(drawer)
                .build();

        setupUserLogoutView(navigationView);

        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);
    }

    private void setupUserLogoutView(NavigationView navigationView) {
        MenuItem item = navigationView.getMenu().findItem(R.id.nav_logout);
        item.setOnMenuItemClickListener(item1 -> {
            Backendless.UserService.logout(new AsyncCallback<Void>() {
                @Override
                public void handleResponse(Void response) {
                    startActivity(new Intent(MainActivity.this, LoginActivity.class));
                    MainActivity.this.finish();
                }

                @Override
                public void handleFault(BackendlessFault fault) {
                    startActivity(new Intent(MainActivity.this, LoginActivity.class));
                    MainActivity.this.finish();
                }
            });
            return false;
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }
}
