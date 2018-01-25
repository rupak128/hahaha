package com.esoxjem.movieguide.Profile;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.esoxjem.movieguide.R;

/**
 * Created by rupak on 1/22/18.
 */

public class ProfileActivity extends AppCompatActivity {
    TextView nameText,emailText,locationText,mobileText;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile_page);
        sharedPreferences = getSharedPreferences("MyPref",Context.MODE_PRIVATE);
        //UserInfo userInfo = (UserInfo) getIntent().getExtras().get("user");
        UserInfo userInfo = new UserInfo();
        //sharedPreferences.getString("name",null);

        userInfo.setName(sharedPreferences.getString("name",null));
        userInfo.setMobile(sharedPreferences.getString("mobile",null));
        userInfo.setLocation(sharedPreferences.getString("location",null));
        userInfo.setEmail(sharedPreferences.getString("email",null));

        init(userInfo);

        sharedPreferences = getSharedPreferences("MyRef", Context.MODE_PRIVATE);



    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
    }

    public void init(UserInfo userInfo)
    {
        nameText = (TextView) findViewById(R.id.name);
        nameText.setText("Name : " + userInfo.getName());
        emailText = (TextView) findViewById(R.id.email);
        emailText.setText("Email : "+ userInfo.getEmail());
        locationText = (TextView) findViewById(R.id.location);
        locationText.setText("Location : " + userInfo.getLocation());
        mobileText = (TextView) findViewById(R.id.mobile);
        mobileText.setText("Mobile No. : "+userInfo.getMobile());
    }
}
