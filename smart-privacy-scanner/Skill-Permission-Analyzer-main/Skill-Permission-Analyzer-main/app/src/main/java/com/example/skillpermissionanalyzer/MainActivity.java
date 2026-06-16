package com.example.skillpermissionanalyzer;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

class CustomAdapter extends ArrayAdapter<String> {

    private Context mContext;
    private ArrayList<String> mItems;

    public CustomAdapter(Context context, ArrayList<String> items) {
        super(context, 0, items);
        mContext = context;
        mItems = items;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View listItemView = convertView;

        if (listItemView == null) {
            listItemView = LayoutInflater.from(mContext)
                    .inflate(R.layout.list_item_layout, parent, false);
        }

        String currentItem = mItems.get(position);

        TextView textViewItem = listItemView.findViewById(R.id.text_view_item);
        ImageView appIcon = listItemView.findViewById(R.id.app_icon);

        textViewItem.setText(currentItem);

        PackageManager pm = mContext.getPackageManager();
        List<ApplicationInfo> packages = pm.getInstalledApplications(0);

        for (ApplicationInfo packageInfo : packages) {
            String appName = pm.getApplicationLabel(packageInfo).toString();

            if(appName.equals(currentItem)){
                appIcon.setImageDrawable(pm.getApplicationIcon(packageInfo));
                break;
            }
        }

        return listItemView;
    }
}

public class MainActivity extends Activity {

    ListView itemList;
    ArrayList<String> items = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        itemList = findViewById(R.id.item_list);

        CustomAdapter adapter = new CustomAdapter(this, items);

        itemList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                String selectedApp = items.get(position);

                PackageManager pm = getPackageManager();
                List<ApplicationInfo> packages = pm.getInstalledApplications(0);

                String packageName = "";

                for (ApplicationInfo packageInfo : packages) {
                    String appName = pm.getApplicationLabel(packageInfo).toString();
                    if(appName.equals(selectedApp)){
                        packageName = packageInfo.packageName;
                        break;
                    }
                }

                Intent intent = new Intent(MainActivity.this, InstagramActivity.class);
                intent.putExtra("APP_NAME", selectedApp);
                intent.putExtra("PACKAGE_NAME", packageName);

                startActivity(intent);
            }
        });

        final PackageManager pm = getPackageManager();

        List<ApplicationInfo> packages = pm.getInstalledApplications(PackageManager.GET_META_DATA);

        for (ApplicationInfo packageInfo : packages) {

            if ((packageInfo.flags & ApplicationInfo.FLAG_SYSTEM) == 0) {

                String appName = pm.getApplicationLabel(packageInfo).toString();

                if(appName.contains(".") || appName.contains("$") ||
                        appName.contains("%") || appName.contains("-"))
                    continue;

                items.add(appName);
            }
        }

        itemList.setVerticalScrollBarEnabled(true);
        itemList.setAdapter(adapter);
    }
}