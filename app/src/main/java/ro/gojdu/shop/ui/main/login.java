package ro.gojdu.shop.ui.main;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.backendless.Backendless;
import com.backendless.BackendlessUser;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.exceptions.BackendlessFault;
import com.backendless.persistence.local.UserIdStorageFactory;

import ro.gojdu.shop.R;

public class login extends AppCompatActivity {
    private View mProgressView;
    private View mLoginFormView;
    private TextView tvLoad;
    EditText etMail,etPassword;
    Button btnlogin;
    TextView tvreset,tvRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mLoginFormView = findViewById(R.id.login_form);
        mProgressView = findViewById(R.id.login_progress);
        tvLoad = findViewById(R.id.tvLoad);
        etMail=findViewById(R.id.etMail);
        etPassword=findViewById(R.id.etPassword);
        btnlogin=findViewById(R.id.btnlogin);
        tvreset=findViewById(R.id.tvreset);
        tvRegister=findViewById(R.id.tvRegister);
        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(etMail.getText().toString().isEmpty()||etPassword.getText().toString().isEmpty())
                {
                    Toast.makeText(login.this, "Please complete all fields!", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    String email=etMail.getText().toString().trim();
                    String p=etPassword.getText().toString().trim();
                    showProgress(true);
                    tvLoad.setText("Busy logging you in...please wait...");
                    Backendless.UserService.login(email, p, new AsyncCallback<BackendlessUser>() {
                        @Override
                        public void handleResponse(BackendlessUser response) {
                            Toast.makeText(login.this, "Logged in successfully!", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(login.this,MainActivity.class));
                            login.this.finish();
                        }

                        @Override
                        public void handleFault(BackendlessFault fault) {
                            Toast.makeText(login.this, "Error: "+fault.getMessage(), Toast.LENGTH_SHORT).show();
                            showProgress(false);
                        }
                    }, true);
                }

            }
        });
        tvRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(login.this,register.class));
            }
        });
        tvreset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    if(etMail.getText().toString().isEmpty())
                        Toast.makeText(login.this, "Please enter your e-mail in the e-mail field!", Toast.LENGTH_SHORT).show();
                    else
                    {
                        String email=etMail.getText().toString().trim();
                        showProgress(true);
                        tvLoad.setText("Busy sending reset intructions...please wait...");
                        Backendless.UserService.restorePassword(email, new AsyncCallback<Void>() {
                            @Override
                            public void handleResponse(Void response) {
                                Toast.makeText(login.this, "Reset instructions sent to e-mail!", Toast.LENGTH_SHORT).show();
                                showProgress(false);
                            }

                            @Override
                            public void handleFault(BackendlessFault fault) {
                                Toast.makeText(login.this, "Error: "+fault.getMessage(), Toast.LENGTH_SHORT).show();
                                showProgress(false);
                            }
                        });
                    }
            }
        });
        showProgress(true);
        tvLoad.setText("Checking login credentials..please wait...");
        Backendless.UserService.isValidLogin(new AsyncCallback<Boolean>() {
            @Override
            public void handleResponse(Boolean response) {
                if(response)
                {
                    String userObjectId= UserIdStorageFactory.instance().getStorage().get();
                    tvLoad.setText("Logging you in...please wait...");
                    Backendless.Data.of(BackendlessUser.class).findById(userObjectId, new AsyncCallback<BackendlessUser>() {
                        @Override
                        public void handleResponse(BackendlessUser response) {
                            startActivity(new Intent(login.this, MainActivity.class));
                            login.this.finish();
                        }

                        @Override
                        public void handleFault(BackendlessFault fault) {
                            Toast.makeText(login.this, "Error "+fault.getMessage(), Toast.LENGTH_SHORT).show();
                            showProgress(false);
                        }
                    });
                }
                else
                {
                    showProgress(false);
                }
            }

            @Override
            public void handleFault(BackendlessFault fault) {
                Toast.makeText(login.this, "Error+ "+fault.getMessage(), Toast.LENGTH_SHORT).show();
                showProgress(false);
            }
        });

    }
    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
    private void showProgress(final boolean show) {
        // On Honeycomb MR2 we have the ViewPropertyAnimator APIs, which allow
        // for very easy animations. If available, use these APIs to fade-in
        // the progress spinner.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
            int shortAnimTime = getResources().getInteger(android.R.integer.config_shortAnimTime);

            mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
            mLoginFormView.animate().setDuration(shortAnimTime).alpha(
                    show ? 0 : 1).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
                }
            });

            mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
            mProgressView.animate().setDuration(shortAnimTime).alpha(
                    show ? 1 : 0).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
                }
            });

            tvLoad.setVisibility(show ? View.VISIBLE : View.GONE);
            tvLoad.animate().setDuration(shortAnimTime).alpha(
                    show ? 1 : 0).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    tvLoad.setVisibility(show ? View.VISIBLE : View.GONE);
                }
            });
        } else {
            // The ViewPropertyAnimator APIs are not available, so simply show
            // and hide the relevant UI components.
            mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
            tvLoad.setVisibility(show ? View.VISIBLE : View.GONE);
            mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
        }
    }


}
