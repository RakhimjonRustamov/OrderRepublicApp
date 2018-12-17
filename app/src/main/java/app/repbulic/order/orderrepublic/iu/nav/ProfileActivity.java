package app.repbulic.order.orderrepublic.iu.nav;

import android.app.ActionBar;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import app.repbulic.order.orderrepublic.authentication.LoginActivity;
import app.repbulic.order.orderrepublic.controllers.UserController;
import app.repbulic.order.orderrepublic.iu.EditProfileActivity;
import butterknife.BindView;
import butterknife.ButterKnife;

import app.repbulic.order.orderrepublic.R;

public class ProfileActivity extends AppCompatActivity {

    @BindView(R.id.toolbar__profile)
    Toolbar toolbar;
    @BindView(R.id.first_name__profile)
    TextView firstName;
    @BindView(R.id.last_name__profile)
    TextView lastName;
    @BindView(R.id.email__profile)
    TextView email;

    private String userId = "-LTq109TnLQL3ID411XL";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        ButterKnife.bind(this);
        setToolbar();
        UserController.readUser(userId, firstName, lastName, email);

    }

    private void setToolbar() {
        toolbar.setTitle("Profile");
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_white_24dp);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    public void edit_profile_button_clicked(View view) {
        Intent editActivity = new Intent(getApplicationContext(), EditProfileActivity.class);
        editActivity.putExtra("fn", firstName.getText().toString());
        editActivity.putExtra("ln", lastName.getText().toString());
        editActivity.putExtra("e", email.getText().toString());
        editActivity.putExtra("ui", userId);
        startActivity(editActivity);
    }

    public void delete_profile_button_clicked(View view) {
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setTitle("Confirm deletion");
        dialog.setMessage("Are you sure?");

        dialog.setPositiveButton("YES", new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialog, int which) {


                UserController.deleteUser(userId);
                dialog.dismiss();
                Toast.makeText(ProfileActivity.this, "Successfully deleted", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(ProfileActivity.this, LoginActivity.class);
                startActivity(intent);

            }
        });

        dialog.setNegativeButton("NO", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {


                dialog.dismiss();
                Toast.makeText(ProfileActivity.this, "Deletion cancelled", Toast.LENGTH_SHORT).show();
            }
        });

        AlertDialog alert = dialog.create();
        alert.show();

    }
}
