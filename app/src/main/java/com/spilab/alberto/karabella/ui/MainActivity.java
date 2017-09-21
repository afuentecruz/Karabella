package com.spilab.alberto.karabella.ui;

import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.provider.Settings;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.spilab.alberto.karabella.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    static final String TAG = "MainActivity";

    SensorManager sensorManager;
    List<Sensor> sensorList;
    ListView mListView;

    @BindView(R.id.fab) FloatingActionButton mFab;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Checks if accessibility service 'MonitorService' is enabled
        if (!isAccessibilitySettingsOn(getApplicationContext())) {
            startActivity(new Intent(Settings.ACTION_ACCESSIBILITY_SETTINGS));
        }
    }

    @OnClick(R.id.fab)
    public void sayHelloButton(View view){
        Snackbar.make(view, "Displaying all available sensors", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show();

        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        mListView = (ListView) findViewById (R.id.ListView);
        sensorList = sensorManager.getSensorList(Sensor.TYPE_ALL);
        mListView.setAdapter(new ArrayAdapter<Sensor>(this, android.R.layout.simple_list_item_1,  sensorList));
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

    /**
     * Checks if the accessibility service 'MonitorService' is enabled in the
     * user accessibility settings
     * @param mContext application context
     * @return true is enabled, false in other case
     */
    private boolean isAccessibilitySettingsOn(Context mContext) {
        int accessibilityEnabled = 0;

        try {
            accessibilityEnabled = Settings.Secure.getInt(
                    mContext.getApplicationContext().getContentResolver(),
                    android.provider.Settings.Secure.ACCESSIBILITY_ENABLED);

        } catch (Settings.SettingNotFoundException e) {
            Log.e(TAG, "Error finding setting, default accessibility to not found: "
                    + e.getMessage());
        }

        if (accessibilityEnabled == 1) {
            Log.v(TAG, "Accessibility service is enabled");

            return true;
        } else {
            Log.v(TAG, "Accessibility service is DISABLED");
        }

        return false;
    }
}
