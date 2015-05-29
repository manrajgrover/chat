package com.ap.collegespace;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.os.Bundle;
import android.text.Html;
import android.text.Spanned;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.os.AsyncTask;
import android.app.ProgressDialog;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import  com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.jeremyfeinstein.slidingmenu.lib.app.SlidingActivity;

public class main extends SlidingActivity {

    private int CurrentTab = 0;
    private TextView title_label;
    private LinearLayout screen_aboutUs;
    private ListView MainListView;

    JSONArray user = null;
    private static String url = "http://updates.collegespace.in/feed/json";

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setBehindContentView(R.layout.menu);

        final SlidingMenu DragMenu = getSlidingMenu();
        DragMenu.setMode(SlidingMenu.LEFT);
        DragMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
        DragMenu.setShadowWidthRes(R.dimen.shadow_width);
        DragMenu.setShadowDrawable(R.drawable.shadow);
        DragMenu.setBehindOffsetRes(R.dimen.slidingmenu_offset);
        DragMenu.setFadeDegree(0.35f);

        //Menu Handler
        final LinearLayout mHome = (LinearLayout)findViewById(R.id.btn_home);
        final LinearLayout mNotices = (LinearLayout)findViewById(R.id.btn_notices);
        final LinearLayout mResults = (LinearLayout)findViewById(R.id.btn_results);
        final LinearLayout mAboutUs = (LinearLayout)findViewById(R.id.btn_aboutus);

        final ImageView mHome_Selected = (ImageView)findViewById(R.id.Home_Selected);
        final ImageView mNotices_Selected = (ImageView)findViewById(R.id.Notices_Selected);
        final ImageView mResults_Selected = (ImageView)findViewById(R.id.Results_Selected);
        final ImageView mAboutUs_Selected = (ImageView)findViewById(R.id.AboutUs_Selected);
        title_label = (TextView)findViewById(R.id.screen_title);
        screen_aboutUs = (LinearLayout)findViewById(R.id.scr_about_us);

        ((ImageView)findViewById(R.id.btn_menu_title)).setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                DragMenu.toggle();
            }
        });

        View.OnClickListener event_click = new View.OnClickListener(){
            @Override
            public void onClick(View v)
            {
                int id = v.getId();
                if (id == R.id.btn_home)
                {
                    mNotices.setBackgroundColor(0x1B191E);
                    mResults.setBackgroundColor(0x1B191E);
                    mAboutUs.setBackgroundColor(0x1B191E);

                    mNotices_Selected.setBackgroundColor(0x1B191E);
                    mResults_Selected.setBackgroundColor(0x1B191E);
                    mAboutUs_Selected.setBackgroundColor(0x1B191E);


                    mHome.setBackgroundColor(0xDD110F12);
                    mHome_Selected.setBackgroundColor(0xFFFF0078);
                    CurrentTab = 0;
                }
                else if (id == R.id.btn_notices)
                {
                    mHome.setBackgroundColor(0x1B191E);
                    mResults.setBackgroundColor(0x1B191E);
                    mAboutUs.setBackgroundColor(0x1B191E);

                    mHome_Selected.setBackgroundColor(0x1B191E);
                    mResults_Selected.setBackgroundColor(0x1B191E);
                    mAboutUs_Selected.setBackgroundColor(0x1B191E);

                    mNotices.setBackgroundColor(0xDD110F12);
                    mNotices_Selected.setBackgroundColor(0xFFFF0078);
                    CurrentTab = 1;
                }
                else if (id == R.id.btn_results)
                {
                    mNotices.setBackgroundColor(0x1B191E);
                    mHome.setBackgroundColor(0x1B191E);
                    mAboutUs.setBackgroundColor(0x1B191E);

                    mNotices_Selected.setBackgroundColor(0x1B191E);
                    mHome_Selected.setBackgroundColor(0x1B191E);
                    mAboutUs_Selected.setBackgroundColor(0x1B191E);

                    mResults.setBackgroundColor(0xDD110F12);
                    mResults_Selected.setBackgroundColor(0xFFFF0078);
                    CurrentTab = 2;
                }
                else if (id == R.id.btn_aboutus)
                {
                    mNotices.setBackgroundColor(0x1B191E);
                    mResults.setBackgroundColor(0x1B191E);
                    mHome.setBackgroundColor(0x1B191E);

                    mNotices_Selected.setBackgroundColor(0x1B191E);
                    mResults_Selected.setBackgroundColor(0x1B191E);
                    mHome_Selected.setBackgroundColor(0x1B191E);

                    mAboutUs.setBackgroundColor(0xDD110F12);
                    mAboutUs_Selected.setBackgroundColor(0xFFFF0078);
                    CurrentTab = 3;
                }
                DragMenu.toggle();
                Toggle_Content();
            }
        };

        mHome.setOnClickListener(event_click);
        mNotices.setOnClickListener(event_click);
        mResults.setOnClickListener(event_click);
        mAboutUs.setOnClickListener(event_click);

        MainListView = (ListView)findViewById(R.id.home_posts);
        new JSONParse().execute();
    }

    String[] NAMES = null;
    String[] TIME = null;
    String[] TITLE = null;
    String[] CONTENTS = null;
    public void RefreshHome()
    {
        if (NAMES == null)
            return;

        final CustomList adapter = new CustomList(main.this, NAMES, TIME, CONTENTS, TITLE);
        MainListView.setAdapter(adapter);
    }

    private void Toggle_Content()
    {
        String Title;
        switch (CurrentTab)
        {
            case 0:
                Title = "Home";
                MainListView.setVisibility(View.VISIBLE);
                screen_aboutUs.setVisibility(View.GONE);
                break;
            case 1:
                Title = "Notices";
                MainListView.setVisibility(View.VISIBLE);
                screen_aboutUs.setVisibility(View.GONE);
                break;
            case 2:
                Title = "Results";
                MainListView.setVisibility(View.VISIBLE);
                screen_aboutUs.setVisibility(View.GONE);
                break;
            case 3:
                Title = "About Us";
                MainListView.setVisibility(View.GONE);
                screen_aboutUs.setVisibility(View.VISIBLE);
                break;
            default:
                Title="ERROR";
                break;
        }
        title_label.setText(Title);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private class JSONParse extends AsyncTask<String, String, JSONArray> {
        private ProgressDialog pDialog;
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(main.this);
            pDialog.setMessage("Getting Data ...");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(true);
            pDialog.show();

        }

        @Override
        protected JSONArray doInBackground(String... args) {
            JSONParser jParser = new JSONParser();

            // Getting JSON from URL
            JSONArray json = jParser.getJSONFromUrl(url);
            return json;
        }
        @Override
        protected void onPostExecute(JSONArray json) {
            pDialog.dismiss();
            try {
                //Data is baked up!!
                int len = json.length();
                NAMES = new String[len];
                TIME = new String[len];
                CONTENTS = new String[len];
                TITLE = new String[len];
                for (int i = 0; i < len; i++)
                {
                    JSONObject c = json.getJSONObject(i);
                    NAMES[i] = c.getString("author");
                    TIME[i] = c.getString("date");
                    CONTENTS[i] = c.getString("content");
                    TITLE[i] = c.getString("title");
                }
                RefreshHome();

            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
    }
}
