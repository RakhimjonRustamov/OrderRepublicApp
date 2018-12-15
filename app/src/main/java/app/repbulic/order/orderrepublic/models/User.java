package app.repbulic.order.orderrepublic.models;

public class User {
    private String userId;
    private String firstName;
    private String lastName;
    private boolean isOwner;
    private String email;

    public User(String userId, String firstName, String lastName, boolean isOwner, String email) {
        this.userId = userId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.isOwner = isOwner;
        this.email = email;
    }

    public String getUserId() {

        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public User(){}

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

}
