package com.example.kjp.view.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.EditText;

import com.example.kjp.MainActivity;
import com.akbar.dev.kjp.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends AppCompatActivity {

    @BindView(R.id.email)EditText inputEmail;
    @BindView(R.id.password)EditText inputPassword;

    private FirebaseAuth auth;

    private ProgressDialog progressDialog;

    private String email, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Get Firebase auth instance
        auth = FirebaseAuth.getInstance();

        if (auth.getCurrentUser() != null) {
            startActivity(new Intent(LoginActivity.this, MainActivity.class));
            finish();
        }

        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        initComponents();
    }

    private void initComponents(){
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Log In. Please wait...");
    }

    private void hideProgress() {
        if (progressDialog != null){
            progressDialog.dismiss();
        }
    }

    private void showProgress() {
        progressDialog.show();
    }

    private void getData(){
        email = inputEmail.getText().toString().trim();
        password = inputPassword.getText().toString().trim();
    }

    private boolean isValidateForm(){
        boolean result = true;

        getData();

        if (TextUtils.isEmpty(email)) {
            inputEmail.setError("Enter email address!");
            result = false;
        } else if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            inputEmail.setError("Invalid email!");
            result = false;
        } else {
            inputEmail.setError(null);
        }

        if (TextUtils.isEmpty(password)) {
            inputPassword.setError("Enter password!");
            result = false;
        } else if (password.length() < 8){
            inputPassword.setError(getString(R.string.minimum_password));
            result = false;
        } else {
            inputPassword.setError(null);
        }

        return result;
    }

    @OnClick(R.id.btn_login)
    public void doSignIn(){
        //Get Firebase auth instance
        auth = FirebaseAuth.getInstance();

        if (!isValidateForm()){
            return;
        }

        getData();

        showProgress();

        //authenticate user
        auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        // If sign in fails, display a message to the user. If sign in succeeds
                        // the auth state listener will be notified and logic to handle the
                        // signed in user can be handled in the listener.
                        hideProgress();
                        if (!task.isSuccessful()) {
                            showMessageSnackbar(getString(R.string.auth_failed));
                        } else {
                            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                            startActivity(intent);
                            finish();
                        }
                    }
                });
    }

    private void showMessageSnackbar(String message) {
        Snackbar.make(findViewById(android.R.id.content), message, Snackbar.LENGTH_LONG)
                .show();
    }

    @OnClick(R.id.btn_signup)
    public void doSignUp(){
        startActivity(new Intent(LoginActivity.this, SignupActivity.class));
    }
}