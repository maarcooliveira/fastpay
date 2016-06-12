package com.mastercard.mymerchant.activity.fastpay;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.accountkit.AccountKit;
import com.facebook.accountkit.AccountKitLoginResult;
import com.facebook.accountkit.ui.AccountKitActivity;
import com.facebook.accountkit.ui.AccountKitConfiguration;
import com.facebook.accountkit.ui.LoginType;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.mastercard.mymerchant.R;

public class LoginActivity extends BaseActivity {

    private CallbackManager callbackManager;
    private LoginButton facebookLoginButton;
    private Button newFacebook;
    private final String TAG = "LoginActivity";
    public static int APP_REQUEST_CODE = 99;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fp_activity_login);

        getSupportActionBar().hide();

        facebookLoginButton = (LoginButton) findViewById(R.id.facebook_login_button);
        newFacebook = (Button) findViewById(R.id.facebook_login_button2);
        newFacebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                facebookLoginButton.performClick();
            }
        });
        checkLogin();
        initFbLogin();
    }

    private void checkLogin() {
        if (com.facebook.AccessToken.getCurrentAccessToken() != null) {
            nextStep();
        }
        if (AccountKit.getCurrentAccessToken() != null) {
            nextStep();
        }
    }

    private void nextStep() {
        Intent intent = new Intent(getApplicationContext(), EventsActivity.class);
        startActivity(intent);
    }

    private void initFbLogin() {
        FacebookSdk.sdkInitialize(getApplicationContext());
        callbackManager = CallbackManager.Factory.create();
        facebookLoginButton.setReadPermissions("email", "user_events");

        facebookLoginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {

            @Override
            public void onSuccess(LoginResult loginResult) {
//                String name = Profile.getCurrentProfile().getFirstName();
//                prefs.edit().putString("userId", Profile.getCurrentProfile().getId());
//                ((SmartPayApp) getApplication()).setLoginResult(loginResult);
                nextStep();
            }

            @Override
            public void onCancel() {
                Toast.makeText(getApplicationContext(), "CANCEL", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onError(FacebookException error) {
                Toast.makeText(getApplicationContext(), "ERROR", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == APP_REQUEST_CODE) { // confirm that this response matches your request
            AccountKitLoginResult loginResult = data.getParcelableExtra(AccountKitLoginResult.RESULT_KEY);
            if (loginResult.getError() != null) {
                String toastMessage = loginResult.getError().getErrorType().getMessage();
                Toast.makeText(this, toastMessage, Toast.LENGTH_LONG).show();
            } else if (loginResult.wasCancelled()) {
                String toastMessage = "Login Cancelled";
                Toast.makeText(this, toastMessage, Toast.LENGTH_LONG).show();
            } else {
                if (loginResult.getAccessToken() == null) {
                    loginResult.getAuthorizationCode().substring(0,10);
                }
                nextStep();
            }
            return;
        }

        callbackManager.onActivityResult(requestCode, resultCode, data);
    }

    public void onLoginPhone(final View view) {
        final Intent intent = new Intent(this, AccountKitActivity.class);
        AccountKitConfiguration.AccountKitConfigurationBuilder configurationBuilder =
                new AccountKitConfiguration.AccountKitConfigurationBuilder(
                        LoginType.PHONE,
                        AccountKitActivity.ResponseType.CODE); // or .ResponseType.TOKEN
        // ... perform additional configuration ...
        intent.putExtra(
                AccountKitActivity.ACCOUNT_KIT_ACTIVITY_CONFIGURATION,
                configurationBuilder.build());
        startActivityForResult(intent, APP_REQUEST_CODE);
    }

    public void onLoginEmail(final View view) {
        final Intent intent = new Intent(this, AccountKitActivity.class);
        AccountKitConfiguration.AccountKitConfigurationBuilder configurationBuilder =
                new AccountKitConfiguration.AccountKitConfigurationBuilder(
                        LoginType.EMAIL,
                        AccountKitActivity.ResponseType.CODE); // or .ResponseType.TOKEN
        // ... perform additional configuration ...
        intent.putExtra(
                AccountKitActivity.ACCOUNT_KIT_ACTIVITY_CONFIGURATION,
                configurationBuilder.build());
        startActivityForResult(intent, APP_REQUEST_CODE);
    }
}
