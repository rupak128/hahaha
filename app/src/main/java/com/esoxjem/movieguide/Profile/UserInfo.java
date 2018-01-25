package com.esoxjem.movieguide.Profile;

import java.io.Serializable;

/**
 * Created by rupak on 1/22/18.
 */

public class UserInfo implements Serializable{
    private int ID;
    private String name;
    private String mobile;
    private String location;
    private String email;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public UserInfo() {

    }

    public UserInfo(String name, String mobile, String location, String email) {

        this.name = name;
        this.mobile = mobile;
        this.location = location;
        this.email = email;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }
}
