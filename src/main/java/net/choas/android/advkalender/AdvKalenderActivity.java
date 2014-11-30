package net.choas.android.advkalender;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;


public class AdvKalenderActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adv_kalender);

        Calendar c = Calendar.getInstance();
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);
        int urlday = day;
        if (urlday > 24) {
            urlday -= 24;
        }

        SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
        final String name = settings.getString("pref_name", "unknown").toLowerCase().trim();
        final String baseUrl = settings.getString("pref_url", getBaseContext().getString(R.string.base_url)).trim();
        final String url = baseUrl.replaceAll("&amp;", "&").replaceAll("__NAME__", name).replaceAll("__URLDAY__", "" + urlday).replaceAll("__MONTH__", ""+month).replaceAll("__DAY__", "" + day);

        TextView tv = (TextView) findViewById(R.id.textView);
        tv.setText("" + day);

        final WebView webView = (WebView) findViewById(R.id.webView);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setVisibility(View.GONE);

        FrameLayout fl = (FrameLayout) findViewById(R.id.frameLayout);
        fl.setOnTouchListener(new OnSwipeTouchListener(getBaseContext()) {
                                  public void onSwipeTop() {
//                                          Toast.makeText(AdvKalenderActivity.this, "top", Toast.LENGTH_SHORT).show();
                                  }

                                  public void onSwipeRight() {
//                                          Toast.makeText(AdvKalenderActivity.this, "right " + url, Toast.LENGTH_SHORT).show();
                                      webView.loadUrl(url);
                                      webView.setVisibility(View.VISIBLE);
                                  }

                                  public void onSwipeLeft() {
//                                          Toast.makeText(AdvKalenderActivity.this, "left", Toast.LENGTH_SHORT).show();
                                      webView.loadUrl(url);
                                      webView.setVisibility(View.VISIBLE);
                                  }

                                  public void onSwipeBottom() {
//                                          Toast.makeText(AdvKalenderActivity.this, "bottom", Toast.LENGTH_SHORT).show();
                                  }
                              }
        );
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.adv_kalender, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            Intent i = new Intent(this, SettingsActivity.class);
            startActivity(i);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
