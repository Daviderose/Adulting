package org.launchcode.Adulting.models;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class User {


    // fields
    @Id
    @GeneratedValue
    private int id;

    @NotNull(message="invalid user id or password")
    @Size(min=3, max=20)
    private String username;

    @NotNull(message="invalid user id or password")
    @Size(min=3, max=20)
    private String password;

    @NotNull(message="does not match password")
    @Size(min=3, max=20)
    private String verify;

    private int experience;

    private int level;



    // constructors

    public User(String username, String password, String verify) {
        this.username = username;
        this.password = password;
        this.verify = verify;
    }

    public User() { }

    // getters and setters
    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
        checkPassword();
    }

    public String getVerify() {
        return verify;
    }

    public void setVerify(String verify) {
        this.verify = verify;
        checkPassword();
    }

    public int getExperience() {
        return experience;
    }

    public void setExperience(int experience) {
        this.experience = experience;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    private void checkPassword() {
        if(this.password == null || this.verify == null){
            return;
        }else if(!this.password.equals(verify)){
            this.verify = null;
        }
    }

}
