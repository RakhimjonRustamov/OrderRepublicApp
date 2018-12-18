package app.repbulic.order.orderrepublic.iu;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import app.repbulic.order.orderrepublic.R;
import app.repbulic.order.orderrepublic.controllers.UserController;
import butterknife.BindView;
import butterknife.ButterKnife;

public class EditProfileActivity extends AppCompatActivity {

    @BindView(R.id.first_name__edit_profile)
    EditText firstName;
    @BindView(R.id.last_name__edit_profile)
    EditText lastName;
    @BindView(R.id.email__edit_profile)
    EditText email;

    private String userId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);
        ButterKnife.bind(this);
        Intent intent = getIntent();
        firstName.setText(intent.getStringExtra("fn"));
        lastName.setText(intent.getStringExtra("ln"));
        email.setText(intent.getStringExtra("e"));
        userId=intent.getStringExtra("ui");
    }

    public void cancel_button_clicked(View view) {
        finish();
    }

    public void confirm_button_clicked(View view) {
        String newfn, newln, newe;
        newfn=firstName.getText().toString();
        newln=lastName.getText().toString();
        newe=email.getText().toString();
        //TODO: here email change should be handled properly
        UserController.updateUser(userId, newfn, newln, newe);

        Toast.makeText(this, "Successfully updated", Toast.LENGTH_SHORT).show();

        finish();

    }
}
