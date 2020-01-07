package com.oneonezz.mvvm;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

/**
 * Created by ${Justin} on 2019/9/19.
 */
public class User  extends BaseObservable{
    private  String firstName;
    @Bindable
    public  String lastName;
    @Bindable
    public String publicTest;

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public User(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }
    @Bindable
    public String getFirstName() {
        return firstName;
    }
    @Bindable
    public String getLastName() {
        return lastName;
    }
}
