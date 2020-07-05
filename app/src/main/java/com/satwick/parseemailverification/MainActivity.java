package com.satwick.parseemailverification;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseInstallation;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

public class MainActivity extends AppCompatActivity {

    private EditText editEmail, editUsername, editPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editEmail = findViewById(R.id.editEmail);
        editUsername = findViewById(R.id.editUsername);
        editPassword = findViewById(R.id.editPassword);

        ParseInstallation.getCurrentInstallation().saveInBackground();
    }


    public void btnSignUpPressed(View btnView){



            Toast.makeText(this, "Signed Up", Toast.LENGTH_SHORT).show();
            try {

                ParseUser user = new ParseUser();
                user.setUsername(editUsername.getText().toString());
                user.setPassword(editPassword.getText().toString());
                user.setEmail(editEmail.getText().toString());


                user.signUpInBackground(new SignUpCallback() {
                    @Override
                    public void done(ParseException e) {
                        if (e == null) {

                            ParseUser.logOut();
                            alertDisplayer("Account created Successfully!", "Please verify email and log in", false);
                        } else {
                            // Sign up didn't succeed. Look at the ParseException
                            // to figure out what went wrong
                            ParseUser.logOut();
                        }
                    }
                });
            }
            catch (Exception e) {
                e.printStackTrace();
            }
    }

    public void alertDisplayer(String title, String msg, final boolean error) {

        AlertDialog.Builder alertDialog = new AlertDialog.Builder(MainActivity.this)
                .setTitle(title)
                .setMessage(msg)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        dialog.cancel();
                        if(!error){
                            Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                            startActivity(intent);
                        }
                    }
                });
        AlertDialog ok = alertDialog.create();
        ok.show();

    }
}
