package app.repbulic.order.orderrepublic.authentication;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.regex.Pattern;

import app.repbulic.order.orderrepublic.MainActivity;
import app.repbulic.order.orderrepublic.R;
import app.repbulic.order.orderrepublic.models.User;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {
    @BindView(R.id.reg_email)
    EditText editTextEmail;
    @BindView(R.id.reg_password)
    EditText editTextPassword;
    @BindView(R.id.reg_button)
    Button registerButton;
    @BindView(R.id.login_redirect)
    Button redirectLogin;
    @BindView(R.id.progress_bar)
    ProgressBar progressBar;
    private FirebaseAuth firebaseAuth;
    private FirebaseUser firebaseUser;
    private FirebaseDatabase firebaseDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);
        setUpListeners();
        firebaseAuth = FirebaseAuth.getInstance();
    }

    private void setUpListeners() {
        registerButton.setOnClickListener(this);
        redirectLogin.setOnClickListener(this);
    }

    private void registerUser() {
        String email = this.editTextEmail.getText().toString();
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
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            editTextEmail.setError("Please enter valid an email");
            editTextEmail.requestFocus();
            return;
        }
        if (password.length() < 6) {
            Toast.makeText(getApplicationContext(), "Password must be at least 6 characters", Toast.LENGTH_SHORT).show();
        }
        progressBar.setVisibility(View.VISIBLE);
        firebaseAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            progressBar.setVisibility(View.GONE);
                            insertUser(task.getResult().getUser());
                            Toast.makeText(getApplicationContext(), "User Register Successfull", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
                            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                            startActivity(intent);

                        } else {
                            if (task.getException() instanceof FirebaseAuthUserCollisionException) {
                                progressBar.setVisibility(View.GONE);
                                Toast.makeText(getApplicationContext(), "You are already registered", Toast.LENGTH_SHORT).show();
                            } else {
                                progressBar.setVisibility(View.GONE);
                                Toast.makeText(getApplicationContext(), task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            }

                        }
                    }
                });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.reg_button:
                registerUser();
                break;
            case R.id.login_redirect:
                startActivity(new Intent(this, LoginActivity.class));
                break;

        }
    }


    private void insertUser(FirebaseUser firebaseUser ){
        String firstName ="firstname";
        String lastName="lastname";
        boolean isOwner=false;
        String email=firebaseUser.getEmail();
        String userId=firebaseUser.getUid();
        User user=new User(firstName, lastName, isOwner, email);
        firebaseDatabase= FirebaseDatabase.getInstance();
        Toast.makeText(this, "INSERT USER FUNCTION", Toast.LENGTH_LONG).show();
        DatabaseReference userReference=firebaseDatabase.getReference();
        userReference.child("users").child(userId).setValue(user);
    }





}
