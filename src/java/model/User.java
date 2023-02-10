package model;

import java.io.Serializable;

/**
 *
 * @author Majid
 */
public class User implements Serializable {

    private String username;
    private String password;

    public User() {
        username = "";
        password = "";
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }
    
    public boolean login(User user){
        if(this.username.equals(user.username) && this.password.equals(user.password))
            return true;
        else return false;
    }

    @Override
    public String toString() {
        return username + " " + password;
    }

}
