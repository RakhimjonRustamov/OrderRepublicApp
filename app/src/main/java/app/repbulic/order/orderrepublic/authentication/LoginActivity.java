package app.repbulic.order.orderrepublic.authentication;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import app.repbulic.order.orderrepublic.MainActivity;
import app.repbulic.order.orderrepublic.R;
import app.repbulic.order.orderrepublic.controllers.UserController;
import app.repbulic.order.orderrepublic.iu.owner.OwnerActivity;
import app.repbulic.order.orderrepublic.models.User;
import butterknife.BindView;
import butterknife.ButterKnife;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    @BindView(R.id.login_email)
    EditText editTextEmail;
    @BindView(R.id.login_password)
    EditText editTextPassword;
    @BindView(R.id.open_reg)
    Button openRegistration;
    @BindView(R.id.login_button)
    Button loginButton;
    @BindView(R.id.progress_bar)
    ProgressBar progressBar;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        setUpListeners();
        firebaseAuth = FirebaseAuth.getInstance();
    }

    private void setUpListeners() {
        openRegistration.setOnClickListener(this);
        loginButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.open_reg:
                startActivity(new Intent(this, RegisterActivity.class));
                break;
            case R.id.login_button:
                signInUser();
                break;
        }

    }

    private void signInUser() {
        final String email = this.editTextEmail.getText().toString();
        String password = this.editTextPassword.getText().toString();

        if (TextUtils.isEmpty(email)) {
            Toast.makeText(getApplicationContext(), "Please fill in the required fields", Toast.LENGTH_SHORT).show();
            editTextEmail.requestFocus();
            return;
        }
        if (TextUtils.isEmpty(password)) {
            Toast.makeText(getApplicationContext(), "Please fill in the required fields", Toast.LENGTH_SHORT).show();
            editTextPassword.requestFocus();
        }
        if (password.length() < 6) {
            Toast.makeText(getApplicationContext(), "Password must be at least 6 characters", Toast.LENGTH_SHORT).show();
        }
        progressBar.setVisibility(View.VISIBLE);

        firebaseAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    UserController.readUserByEmail(email, progressBar, getApplicationContext());
                } else {
                    progressBar.setVisibility(View.GONE);
                    Toast.makeText(getApplicationContext(), task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}
