package com.motion.takku;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;

public class Signup1Activity extends AppCompatActivity {
    private String UsernameInput;
    private String EmailInput;
    private String PasswordInput;

    TextInputLayout Username, EmailAddress, Password, ConfirmPassword;
    TextView errUsername, errEmail, errPass, errConfpass;
    Button btnNext;

    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup1);

        btnNext = findViewById(R.id.btn_signup_next);

        Username = findViewById(R.id.et_signup_username);
        errUsername = findViewById(R.id.err_username);

        EmailAddress = findViewById(R.id.et_signup_email);
        errEmail = findViewById(R.id.err_email);

        Password = findViewById(R.id.et_signup_pass);
        errPass = findViewById(R.id.err_pass);

        ConfirmPassword = findViewById(R.id.et_signup_confpass);
        errConfpass = findViewById(R.id.err_confpass);

        mAuth = FirebaseAuth.getInstance();

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UsernameInput = Username.getEditText().getText().toString().trim();
                EmailInput = EmailAddress.getEditText().getText().toString().trim();
                PasswordInput = Password.getEditText().getText().toString().trim();

                if (!validateUsername(UsernameInput) | !validateEmail(EmailInput) | !validatePassword(PasswordInput) | !validateConfirmPassword()) {
                    return;
                } else {
                    Intent intent = new Intent(Signup1Activity.this, Signup2Activity.class);
                    intent.putExtra(Signup2Activity.EXTRA_USERNAME, UsernameInput);
                    intent.putExtra(Signup2Activity.EXTRA_EMAIL, EmailInput);
                    intent.putExtra(Signup2Activity.EXTRA_PASSWORD, PasswordInput);
                    startActivity(intent);
                }
            }
        });
    }

    /*protected void onStart() {
        super.onStart();

        if (mAuth.getCurrentUser() != null) {
            finish();
            Intent intent = new Intent(Signup1Activity.this, MainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
        }
    }*/

    private boolean validateUsername(String UsernameInput) {
        if (UsernameInput.isEmpty()) {
            Username.getEditText().setBackground(getResources().getDrawable(R.drawable.bg_input_error));
            errUsername.setVisibility(View.VISIBLE);
            errUsername.setText(R.string.error_text_username_empty);
            return false;
        } else {
            errUsername.setVisibility(View.INVISIBLE);
            return true;
        }
    }

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

    private boolean validateConfirmPassword() {
        String PasswordInput = Password.getEditText().getText().toString().trim();
        String ConfirmPasswordInput = ConfirmPassword.getEditText().getText().toString().trim();

        if (ConfirmPasswordInput.isEmpty() || !ConfirmPasswordInput.equals(PasswordInput)) {
            ConfirmPassword.getEditText().setBackground(getResources().getDrawable(R.drawable.bg_input_error));
            errConfpass.setVisibility(View.VISIBLE);
            errConfpass.setText(R.string.error_text_confpass_notmatch);
            return false;
        } else {
            errConfpass.setVisibility(View.INVISIBLE);
            return true;
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
