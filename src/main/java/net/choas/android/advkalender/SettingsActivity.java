package net.choas.android.advkalender;

import android.os.Bundle;
import android.preference.PreferenceActivity;

/**
 * Created by gregoril on 29.11.2014.
 */
public class SettingsActivity extends PreferenceActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Display the fragment as the main content.
        getFragmentManager().beginTransaction()
                .replace(android.R.id.content, new SettingsFragment())
                .commit();
    }

}
