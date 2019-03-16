package com.example.sarthak.database;

import com.google.firebase.database.Exclude;

public class Upload {
    private String mName;
    private String mImageUrl;
    private String mRoute;
    private String mKey;
    private String mdate;



    public Upload() {
        //empty constructor needed
    }

    public Upload(String name, String imageUrl,String route,String date) {
        if (name.trim().equals("")) {
            name = "No Details";
        }
        if (route.trim().equals("")) {
            route = "No Details";
        }
        mRoute=route;
        mName = name;
        mImageUrl = imageUrl;
        mdate=date;
    }

    public String getRoute()
    {
        return mRoute;
    }

    public String getDate()
    {
        return mdate;
    }

    public void setRoute(String route)
    {

        mRoute=route;
    }

    public void setDate(String date)
    {

        mdate=date;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public String getImageUrl() {
        return mImageUrl;
    }

    public void setImageUrl(String imageUrl) {
        mImageUrl = imageUrl;
    }

    @Exclude
    public String getKey() {
        return mKey;
    }

    @Exclude
    public void setKey(String key) {
        mKey = key;
    }

}
