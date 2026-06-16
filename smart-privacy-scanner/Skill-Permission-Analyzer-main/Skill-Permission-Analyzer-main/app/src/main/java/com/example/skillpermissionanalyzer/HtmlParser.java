package com.example.skillpermissionanalyzer;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;

public class HtmlParser {

    HashMap<String,String> permissionExplanation = new HashMap<>();

    public HtmlParser(){

        permissionExplanation.put("CAMERA","Allows app to capture photos and videos");
        permissionExplanation.put("LOCATION","Allows app to track your location");
        permissionExplanation.put("CONTACTS","Allows access to your contacts");
        permissionExplanation.put("MICROPHONE","Allows recording audio");
        permissionExplanation.put("STORAGE","Allows access to files and media");
        permissionExplanation.put("PHONE","Allows access to phone calls");
    }

    String getImageUrlFromWebPage(Document document,String imgpath) {
        try {
            Element imgElement = document.select("img[src*='/en/reports/'][alt='"+imgpath+"']").first();

            if (imgElement != null) {
                return imgElement.absUrl("src");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    ArrayList<String> fetchTrackers(Document doc ) {

        ArrayList<String> trackerList = new ArrayList<>();

        Elements trackerElements = doc.select("p a.link.black");

        for (Element trackerElement : trackerElements) {

            String trackerName = trackerElement.text();

            String data = "Tracker: " + trackerName +
                    "\nCollects user behaviour and analytics data";

            trackerList.add(data);

            Log.d("TrackerData", data);
        }

        return trackerList;
    }

    ArrayList<String> fetchPermissions(Document doc) {

        ArrayList<String> permissionList = new ArrayList<>();

        Elements permissionElements = doc.select("p.text-truncate");

        for (Element permissionElement : permissionElements) {

            Element permissionText = permissionElement.select("span[data-toggle='tooltip']").first();

            if(permissionText == null) continue;

            String permissionName = permissionText.text();

            String explanation = "This permission allows access to system resources";

            for(String key : permissionExplanation.keySet()){
                if(permissionName.contains(key)){
                    explanation = permissionExplanation.get(key);
                }
            }

            String data =
                    "Permission: " + permissionName +
                            "\n" + explanation;

            permissionList.add(data);

        }

        return permissionList;
    }
}