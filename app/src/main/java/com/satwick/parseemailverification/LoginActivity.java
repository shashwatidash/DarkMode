package com.satwick.parseemailverification;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;

public class LoginActivity extends AppCompatActivity {

    private EditText edtLoginUsername, edtLoginPwd;
    private Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        edtLoginUsername = findViewById(R.id.edit_login_username);
        edtLoginPwd = findViewById(R.id.edit_login_pwd);
        btnLogin = findViewById(R.id.btnLogin);

    }
    public void loginPressed(View btnLoginView) {
        ParseUser.logInInBackground(edtLoginUsername.getText().toString(), edtLoginPwd.getText().toString(), new LogInCallback() {
            @Override
            public void done(ParseUser user, ParseException e) {
                if (user != null) {
                    // Hooray! The user is logged in.
                } else {
                    // Signup failed. Look at the ParseException to see what happened.
                }
            }
        });
    }
}
