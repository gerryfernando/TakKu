package com.motion.takku;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {

    private String EmailInput;
    private String PasswordInput;

    TextInputLayout EmailAddress, Password;
    Button btnLogin;
    TextView tvSignup, errEmail, errPass;
    RelativeLayout progressBar;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        EmailAddress = findViewById(R.id.et_login_email);
        errEmail = findViewById(R.id.err_email);

        Password = findViewById(R.id.et_login_pass);
        errPass = findViewById(R.id.err_pass);

        btnLogin = findViewById(R.id.btn_login);
        tvSignup = findViewById(R.id.tv_signup);

        progressBar = findViewById(R.id.progress_bar);

        mAuth = FirebaseAuth.getInstance();

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EmailInput = EmailAddress.getEditText().getText().toString().trim();
                PasswordInput = Password.getEditText().getText().toString().trim();

                if (!validateEmail(EmailInput) | !validatePassword(PasswordInput)){
                    return;
                } else {
                    progressBar.setVisibility(View.VISIBLE);

                    mAuth.signInWithEmailAndPassword(EmailInput, PasswordInput).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            progressBar.setVisibility(View.GONE);
                            if (task.isSuccessful()) {
                                finish();
                                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                startActivity(intent);
                            } else {
                                EmailAddress.getEditText().setBackground(getResources().getDrawable(R.drawable.bg_input_error));
                                Password.getEditText().setBackground(getResources().getDrawable(R.drawable.bg_input_error));
                                Toast.makeText(getApplicationContext(), task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }
        });

        tvSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, Signup1Activity.class);
                startActivity(intent);
            }
        });
    }

    /*@Override
    protected void onStart() {
        super.onStart();

        if (mAuth.getCurrentUser() != null) {
            finish();
            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
        }
    }*/

    private boolean validateEmail(String EmailInput) {
        if (EmailInput.isEmpty()) {
            EmailAddress.getEditText().setBackground(getResources().getDrawable(R.drawable.bg_input_error));
            errEmail.setVisibility(View.VISIBLE);
            errEmail.setText(R.string.error_text_email_empty);
            return false;
        } else if (!Patterns.EMAIL_ADDRESS.matcher(EmailInput).matches()) {
            EmailAddress.getEditText().setBackground(getResources().getDrawable(R.drawable.bg_input_error));
            errEmail.setVisibility(View.VISIBLE);
            errEmail.setText(R.string.error_text_email_notvalid);
            return false;
        } else {
            errEmail.setVisibility(View.INVISIBLE);
            return true;
        }
    }

    private boolean validatePassword(String PasswordInput) {
        if (PasswordInput.isEmpty()) {
            Password.getEditText().setBackground(getResources().getDrawable(R.drawable.bg_input_error));
            errPass.setVisibility(View.VISIBLE);
            errPass.setText(R.string.error_text_pass_empty);
            return false;
        } else if (PasswordInput.length() < 6) {
            Password.getEditText().setBackground(getResources().getDrawable(R.drawable.bg_input_error));
            errPass.setVisibility(View.VISIBLE);
            errPass.setText(R.string.error_text_pass_minLength);
            return false;
        } else {
            errPass.setVisibility(View.INVISIBLE);
            return true;
        }
    }
}
