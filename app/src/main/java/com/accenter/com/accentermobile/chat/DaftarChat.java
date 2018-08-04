package com.accenter.com.accentermobile.chat;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.accenter.com.accentermobile.MainChatroomActivity;
import com.accenter.com.accentermobile.data.SharedPreferenceHelper;
import com.accenter.com.accentermobile.data.StaticConfig;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import com.accenter.com.accentermobile.R;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DaftarChat extends AppCompatActivity {

    private EditText inputEmail, inputPassword, inputUsername;
    private Button btnSignIn, btnSignUp, btnResetPassword;
    private ProgressBar progressBar;
    private FirebaseAuth auth;
    private SharedPreferences sharedPreferences;
    public static final String STR_USERNAME = "userKey";
    public static final String STR_EMAIL = "emailKey";
    public static final String STR_PASSWORD = "emailKey";
    public static final String MY_PREF = "chat";
    //parameter regex daftar
    private final Pattern VALID_EMAIL_ADDRESS_REGEX =
            Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
    public static String STR_EXTRA_ACTION_REGISTER = "daftarchat";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daftar_chat);
        //Get Firebase auth instance
        auth = FirebaseAuth.getInstance();

        btnSignIn = (Button) findViewById(R.id.sign_in_button);
        btnSignUp = (Button) findViewById(R.id.sign_up_button);
        inputEmail = (EditText) findViewById(R.id.email);
        inputPassword = (EditText) findViewById(R.id.password);
        inputUsername = (EditText) findViewById(R.id.username);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        btnResetPassword = (Button) findViewById(R.id.btn_reset_password);
        btnResetPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DaftarChat.this, GantiPassword.class));
            }
        });

        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String email = inputEmail.getText().toString().trim();
                String password = inputPassword.getText().toString().trim();
                String user = inputUsername.getText().toString().trim();

                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString(SharedPreferenceHelper.STR_USERNAME,user);
                editor.putString(SharedPreferenceHelper.STR_EMAIL,email);
                editor.apply();

                //cek parameter input
                if (validasi(email,password,user)) {
                    Intent data = new Intent();
                    data.putExtra(SharedPreferenceHelper.STR_EMAIL,email);
                    data.putExtra(SharedPreferenceHelper.STR_PASSWORD,password);
                    data.putExtra(SharedPreferenceHelper.STR_USERNAME,user);
                    setResult(RESULT_OK);


                    finish();
                }else{
                    Toast.makeText(DaftarChat.this,"Username atau Password Salah",Toast.LENGTH_SHORT).show();
                }

                progressBar.setVisibility(View.VISIBLE);
                //create user
                auth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(DaftarChat.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                Toast.makeText(DaftarChat.this, "createUserWithEmail:onComplete:" + task.isSuccessful(), Toast.LENGTH_SHORT).show();
                                progressBar.setVisibility(View.GONE);
                                // If sign in fails, display a message to the user. If sign in succeeds
                                // the auth state listener will be notified and logic to handle the
                                // signed in user can be handled in the listener.
                                if (!task.isSuccessful()) {
                                    Toast.makeText(DaftarChat.this, "Authentication failed." + task.getException(),
                                            Toast.LENGTH_SHORT).show();
                                } else {
                                    startActivity(new Intent(DaftarChat.this, MainChatroomActivity.class));
                                    finish();
                                }
                            }
                        });

            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        progressBar.setVisibility(View.GONE);
    }
    private boolean validasi(String emailStr, String password, String user){
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(emailStr);
        return password.length()> 0 && user.equals(password)&& matcher.find();
    }
}
