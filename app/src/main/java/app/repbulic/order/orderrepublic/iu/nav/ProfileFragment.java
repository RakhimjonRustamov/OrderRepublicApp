package app.repbulic.order.orderrepublic.iu.nav;


import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import app.repbulic.order.orderrepublic.R;
import app.repbulic.order.orderrepublic.authentication.LoginActivity;
import app.repbulic.order.orderrepublic.controllers.UserController;
import butterknife.BindView;
import butterknife.ButterKnife;

public class ProfileFragment extends Fragment {


  private String userId;

  @BindView(R.id.first_name__profile)
  TextView firstName;
  @BindView(R.id.last_name__profile)
  TextView lastName;
  @BindView(R.id.email__profile)
  TextView email;
  @BindView(R.id.edit_details_button__profile)
  Button editButton;
  @BindView(R.id.delete_account_button__profile)
  Button deleteButton;

  public ProfileFragment() {
    // Required empty public constructor
  }

  // TODO: Rename and change types and number of parameters
  public static ProfileFragment newInstance() {
    ProfileFragment fragment = new ProfileFragment();
    return fragment;
  }


  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
                           Bundle savedInstanceState) {

    //sent userId from main activity
    userId = getArguments().getString("userId");
    // Inflate the layout for this fragment
    View view = inflater.inflate(R.layout.fragment_profile, container, false);
    ButterKnife.bind(this, view);
    //Button editButton = view.findViewById(R.id.edit_details_button__profile);
    editButton.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        Intent editActivity = new Intent(getContext(), EditProfileActivity.class);
        editActivity.putExtra("fn", firstName.getText().toString());
        editActivity.putExtra("ln", lastName.getText().toString());
        editActivity.putExtra("e", email.getText().toString());
        editActivity.putExtra("ui", userId);
        startActivity(editActivity);
      }
    });

    //Button deleteAccountButton = view.findViewById(R.id.delete_account_button__profile);
    deleteButton.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        AlertDialog.Builder dialog = new AlertDialog.Builder(getContext());
        dialog.setTitle("Confirm deletion");
        dialog.setMessage("Are you sure?");

        dialog.setPositiveButton("YES", new DialogInterface.OnClickListener() {

          public void onClick(DialogInterface dialog, int which) {
            UserController.deleteUser(userId);
            dialog.dismiss();
            Toast.makeText(getContext(), "Successfully deleted", Toast.LENGTH_LONG).show();
            Intent intent = new Intent(getContext(), LoginActivity.class);
            startActivity(intent);

          }
        });

        dialog.setNegativeButton("NO", new DialogInterface.OnClickListener() {
          @Override
          public void onClick(DialogInterface dialog, int which) {
            dialog.dismiss();
            Toast.makeText(getContext(), "Deletion cancelled", Toast.LENGTH_SHORT).show();
          }
        });

        AlertDialog alert = dialog.create();
        alert.show();
      }
    });
    return view;

  }

  @Override
  public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);

    UserController.readUser(userId, firstName, lastName, email);
  }
}
