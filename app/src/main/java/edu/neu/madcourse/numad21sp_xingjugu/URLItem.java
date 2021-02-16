package edu.neu.madcourse.numad21sp_xingjugu;

import android.content.Intent;
import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;

public class URLItem implements ItemClickListener {

    private final String URL_name;
    private final String URL_link;

    //Constructor
    public URLItem(String URL_name, String URL_link) {
        this.URL_link = URL_link;
        this.URL_name = URL_name;
    }

    public String getURLName(){
        return this.URL_name;
    }

    public String getURLLink(){
        return this.URL_link;
    }


    @Override
    public Intent onItemClick(int position) {
        Intent viewIntent =
                new Intent("android.intent.action.VIEW",
                        Uri.parse(this.URL_link));
        return viewIntent;

    }

}