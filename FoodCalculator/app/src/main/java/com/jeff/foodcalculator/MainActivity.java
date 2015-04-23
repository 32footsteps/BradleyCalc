package com.jeff.foodcalculator;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;


public class MainActivity extends Activity {

    private Button buttonDaily;
    private Button buttonWeekly;

    // keys for storing row ID in Bundle passed to a fragment
    public static final String ROW_ID = "row_id";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // Set values for buttons on main screen
        buttonDaily = (Button) findViewById(R.id.buttonDaily);
        buttonDaily.setOnClickListener(dailyListener);

        buttonWeekly = (Button) findViewById(R.id.buttonWeekly);
        buttonWeekly.setOnClickListener(weeklyListener);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    // OnClick Listeners for switching screens
    View.OnClickListener dailyListener = new View.OnClickListener(){
        public void onClick(View v){
            setContentView(R.layout.activity_daily);
        };
    };

    View.OnClickListener weeklyListener = new View.OnClickListener(){
        public void onClick(View v){
            setContentView(R.layout.activity_weekly);
        };
    };





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
}
