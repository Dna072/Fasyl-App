package com.dnastudios.fasylgh.activities;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;
import com.dnastudios.fasylgh.Constants;
import com.dnastudios.fasylgh.R;
import com.dnastudios.fasylgh.utils.LoginRequest;

import org.json.JSONException;
import org.json.JSONObject;

public class LoginActivity extends AppCompatActivity implements Response.Listener<String>, Response.ErrorListener{

    private EditText mAccountNumberView;

    private View mLoginFormView;
    private View mProgressView;

    @Override
    public void onErrorResponse(VolleyError error) {
        showProgress(false);

        Log.e("Login", "", error.getCause());
        LinearLayout layout = (LinearLayout) findViewById(R.id.activity_login);
        Snackbar.make(layout, "Couldn\'t login, an error occurred",Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void onResponse(String response) {
        showProgress(false);
        Log.e("Login", response);

        //TODO verify response and open main activity
        try {
            JSONObject obj = new JSONObject(response);
            int respCode = obj.getInt("code");

            if(respCode == 0) {
                JSONObject jsonObject = obj.getJSONObject("data");
                String branch = jsonObject.getString("customerBranch");
                String custFullName = jsonObject.getString("customerFullName");
                String customerId = jsonObject.getString("customerId");
                String accountNo = String.valueOf(mAccountNumberView.getText());

                SharedPreferences sharedPreferences = getSharedPreferences(Constants.USER_PREFS, MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();

                editor.putString(Constants.CUSTOMER_BRANCH, branch);
                editor.putString(Constants.CUSTOMER_FULL_NAME, custFullName);
                editor.putString(Constants.ACCOUNT_NUMBER, accountNo);
                editor.putString(Constants.CUSTOMER_ID, customerId);

                editor.apply();

                openMainPage();
            }

            LinearLayout layout = (LinearLayout) findViewById(R.id.activity_login);
            Snackbar.make(layout, "Couldn\'t login, an error occurred",Snackbar.LENGTH_SHORT).show();

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    private void openMainPage() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mAccountNumberView = (EditText) findViewById(R.id.account_number);
        Button loginBtn = (Button) findViewById(R.id.btnLogin);

        mLoginFormView = findViewById(R.id.login_form);
        mProgressView = findViewById(R.id.login_progress);

        if (loginBtn != null) {
            loginBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                     attemptLogin();

                }
            });
        }

        SharedPreferences sharedPreferences = getSharedPreferences(Constants.USER_PREFS, MODE_PRIVATE);
        String userID = sharedPreferences.getString(Constants.CUSTOMER_ID, "0");

        if(!userID.equals("0")){
            openMainPage();
        }
    }

    /**
     * Shows the progress UI and hides the login form.
     */
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
        } else {
            // The ViewPropertyAnimator APIs are not available, so simply show
            // and hide the relevant UI components.
            mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
            mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
        }
    }


    private void attemptLogin() {
        String accountNo = String.valueOf(mAccountNumberView.getText());
        Log.d("Login", accountNo);

        // TODO Check for null
        LoginRequest loginRequest = new LoginRequest(accountNo, this, this);
        RequestQueue queue = Volley.newRequestQueue(this);

        queue.add(loginRequest);

        showProgress(true);
    }

}
