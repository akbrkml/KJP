package com.example.kjp.view.activity;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.EditText;

import com.akbar.dev.kjp.R;
import com.example.kjp.model.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SignupActivity extends AppCompatActivity {

    private static final String TAG = SignupActivity.class.getSimpleName();

    @BindView(R.id.name)EditText inputName;
    @BindView(R.id.email)EditText inputEmail;
    @BindView(R.id.password)EditText inputPassword;

    private FirebaseAuth auth;
    private DatabaseReference mDatabase;

    private ProgressDialog progressDialog;

    private String name, email, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        ButterKnife.bind(this);

        initFirebase();

        initComponents();
    }

    private void initFirebase(){
        //Get Firebase auth instance
        auth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference();
    }

    private void initComponents(){
        progressDialog = new ProgressDialog(this);
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
        } else if (password.length() < 8) {
            inputPassword.setError(getString(R.string.minimum_password));
            result = false;
        } else {
            inputPassword.setError(null);
        }

        if (TextUtils.isEmpty(name)) {
            inputName.setError("Enter name!");
            result = false;
        } else if (name.length() < 5){
            inputName.setError("Name too short, enter minimum 8 characters!");
            result = false;
        } else {
            inputName.setError(null);
        }

        return result;
    }

    private void getData(){
        email = inputEmail.getText().toString().trim();
        password = inputPassword.getText().toString().trim();
        name = inputName.getText().toString().trim();
    }

    @OnClick(R.id.sign_in_button)
    public void doSignIn(){
        finish();
    }

    @OnClick(R.id.sign_up_button)
    public void doSignUp(){
        if (!isValidateForm()){
            return;
        }

        getData();

        showProgress();

        //create user
        auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        // If sign in fails, display a message to the user. If sign in succeeds
                        // the auth state listener will be notified and logic to handle the
                        // signed in user can be handled in the listener.
                        if (!task.isSuccessful()) {
                            showMessageSnackbar("Registration has been failed! Please try again.");
                        } else {
                            onAuthSuccess(task.getResult().getUser());
                            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(SignupActivity.this);
                            alertDialogBuilder.setTitle("Register new account");
                            alertDialogBuilder.setMessage("Your account has been registered. Please sign in with email and password.");
                            alertDialogBuilder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    dialogInterface.dismiss();
                                    finish();
                                }
                            });
                            alertDialogBuilder.show();
                        }
                        hideProgress();
                    }
                });
    }

    private void showMessageSnackbar(String message) {
        Snackbar.make(findViewById(android.R.id.content), message, Snackbar.LENGTH_LONG)
                .show();
    }

    private void hideProgress() {
        if (progressDialog != null){
            progressDialog.dismiss();
        }
    }

    private void showProgress() {
        progressDialog.setMessage("Sign Up. Please wait...");
        progressDialog.show();
    }

    private void onAuthSuccess(FirebaseUser user) {
        name = inputName.getText().toString();

        // Write new user
        createNewUser(user.getUid(), name, user.getEmail());
    }

    private void createNewUser(String userId, String name, String email) {
        User user = new User(name, email);

        mDatabase.child("users").child(userId).setValue(user);
    }
}