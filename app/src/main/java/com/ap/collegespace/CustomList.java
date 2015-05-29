package com.ap.collegespace;

import android.app.Activity;
import android.text.Spanned;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class CustomList extends ArrayAdapter<String>
{
    private final Activity context;
    private final String[] users_name;
    private final String[] posts_time;
    private final String[] posts_title;
    private final String[] posts_content;
    //private final Integer[] users_imageid;


    public CustomList(Activity context, String[] names, String[] time, String[] contents, String[] title/*, Integer[] imageid*/) {
        super(context, R.layout.list_post, names);
        this.context = context;

        this.users_name = names;
        this.posts_time = time;
        this.posts_content = contents;
        this.posts_title = title;
        //this.users_imageid = imageid;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View rowView= inflater.inflate(R.layout.list_post, null, true);
        TextView txtuser = (TextView) rowView.findViewById(R.id.user_name);
        TextView txttime = (TextView) rowView.findViewById(R.id.post_date);
        WebView txtpost = (WebView) rowView.findViewById(R.id.post_content);
        TextView txttitle = (TextView) rowView.findViewById(R.id.post_title);
        //ImageView imageView = (ImageView) rowView.findViewById(R.id.user_pic);

        txtuser.setText(users_name[position]);
        txttime.setText(posts_time[position]);
        txtpost.loadData(posts_content[position], "text/html", "UTF-8");
        txttitle.setText(posts_title[position]);
        //imageView.setImageResource(users_imageid[position]);

        txtpost.getSettings().setLoadsImagesAutomatically(false);
        txtpost.getSettings().setAppCacheEnabled(true);
        return rowView;
    }
}
