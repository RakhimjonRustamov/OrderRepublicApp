package app.repbulic.order.orderrepublic.models;

import android.util.Log;

import java.io.Serializable;
import java.util.ArrayList;

public class User implements Serializable{
    private String userId;
    private String firstName;
    private String lastName;
    private boolean isOwner;
    private String email;
    private String restName;
    private ArrayList<String> favoritesList;


    public User() {
    }

    public User(String userId, String firstName, String lastName, boolean isOwner, String email, ArrayList<String> favoritesList) {
        this.userId = userId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.isOwner = isOwner;
        this.email = email;
        this.favoritesList = favoritesList;
        restName="";
    }

    public String getRestName() {
        return restName;
    }

    public void setRestName(String restName) {
        this.restName = restName;
    }

    public String getUserId() {

        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public boolean isOwner() {
        return isOwner;
    }

    public void setOwner(boolean owner) {
        isOwner = owner;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public ArrayList<String> getFavoritesList() {
        return favoritesList;
    }

    public void setFavoritesList(ArrayList<String> favoritesList) {
        this.favoritesList = favoritesList;
    }


    public void logger() {
        Log.d("user", this.getFirstName());
        Log.d("user", this.getLastName());
        Log.d("user", this.getEmail());
        Log.d("user", this.getFavoritesList().toString());
        Log.d("user", String.valueOf(this.isOwner()));
    }

}
