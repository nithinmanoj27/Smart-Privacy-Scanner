package com.example.skillpermissionanalyzer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.app.ProgressDialog;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.util.ArrayList;

public class InstagramActivity extends AppCompatActivity {

    private ProgressDialog progressDialog;
    private ArrayList<String> trackerList;
    private ArrayList<String> permissionList;

    private LinearLayout trackerListLinearLayout, permissionListLinearLayout;

    private String appname;
    private String apppackagename;
    private String Appurl;

    private String imgpath;
    private String imageurl;

    private int trackercount = 0;
    private int dangerouspermissioncount = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_moodle);

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Analyzing App...");
        progressDialog.setCancelable(false);

        appname = getIntent().getStringExtra("APP_NAME");
        apppackagename = getIntent().getStringExtra("PACKAGE_NAME");

        imgpath = apppackagename + " logo";

        Appurl = "https://reports.exodus-privacy.eu.org/en/reports/"
                + apppackagename + "/latest/";

        new FetchHtmlData().execute();
    }

    private class FetchHtmlData extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            progressDialog.show();
        }

        @Override
        protected Void doInBackground(Void... voids) {

            HtmlParser htmlParser = new HtmlParser();

            Document document = null;

            try {
                document = Jsoup.connect(Appurl).get();
            } catch (IOException e) {
                e.printStackTrace();
            }

            if (document != null) {

                trackerList = htmlParser.fetchTrackers(document);
                permissionList = htmlParser.fetchPermissions(document);
                imageurl = htmlParser.getImageUrlFromWebPage(document, imgpath);

            }

            return null;
        }

        @Override
        protected void onPostExecute(Void result) {

            ImageView imageView = findViewById(R.id.imageView);
            TextView titlepageheader = findViewById(R.id.apptitle);

            trackerListLinearLayout = findViewById(R.id.trackerListLinearLayout);
            permissionListLinearLayout = findViewById(R.id.permissionListLinearLayout);

            titlepageheader.setText(appname);

            Picasso.get()
                    .load(imageurl)
                    .resize(150,150)
                    .centerCrop()
                    .into(imageView);

            for (String trackerData : trackerList) {
                addTrackerTextView(trackerData);
            }

            for (String permissionData : permissionList) {
                addPermissionTextView(permissionData);
            }

            TextView trackersTextView = findViewById(R.id.trackerscountsTextViewtoatalCounts);
            TextView permissionsTextView = findViewById(R.id.permissionsscountsTextViewtoatalCounts);

            trackersTextView.setText("Trackers Found : " + trackerList.size());
            permissionsTextView.setText("Permissions Found : " + permissionList.size());

            progressDialog.dismiss();

            calculateRiskScore();
        }
    }

    private void calculateRiskScore() {

        int trackerScore = (trackercount * 40) / 10;
        int dangerousScore = (dangerouspermissioncount * 40) / 20;
        int permissionScore = (permissionList.size() * 20) / 100;

        int riskScore = trackerScore + dangerousScore + permissionScore;

        if(riskScore > 100){
            riskScore = 100;
        }

        TextView textViewresult = findViewById(R.id.privacyscore);

        String riskLevel;

        if (riskScore < 30){
            riskLevel = "LOW";
        }
        else if (riskScore < 70){
            riskLevel = "MODERATE";
        }
        else{
            riskLevel = "HIGH";
        }

        textViewresult.setText("Privacy Risk: " + riskScore + "%\nRisk Level: " + riskLevel);
    }

    private void addTrackerTextView(String trackerData){

        TextView textView = new TextView(this);
        textView.setText(trackerData);
        textView.setTextColor(Color.BLACK);
        textView.setPadding(20,20,20,20);

        CardView cardView = new CardView(this);
        cardView.setCardBackgroundColor(Color.parseColor("#D6EAF8"));
        cardView.setRadius(20);

        cardView.addView(textView);

        trackerListLinearLayout.addView(cardView);

        trackercount++;
    }

    private void addPermissionTextView(String permissionData){

        TextView textView = new TextView(this);
        textView.setText(permissionData);
        textView.setPadding(20,20,20,20);

        if(permissionData.contains("CAMERA") ||
                permissionData.contains("LOCATION") ||
                permissionData.contains("CONTACTS") ||
                permissionData.contains("MICROPHONE") ||
                permissionData.contains("PHONE") ||
                permissionData.contains("CALL")){

            textView.setTextColor(Color.RED);
            dangerouspermissioncount++;
        }
        else{
            textView.setTextColor(Color.BLACK);
        }

        CardView cardView = new CardView(this);
        cardView.setCardBackgroundColor(Color.parseColor("#D6EAF8"));
        cardView.setRadius(20);

        cardView.addView(textView);

        permissionListLinearLayout.addView(cardView);
    }
}