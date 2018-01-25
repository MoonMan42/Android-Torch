package com.spacejanitor.torch;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.hardware.Camera;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

public class MainActivity extends Activity implements View.OnClickListener {

    private Camera camera;
    private Camera.Parameters parameters;

    private TextView switchTxt;

    private LinearLayout mainLayout;

    private boolean isFlashOn = false;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT); // lock screen to vertical

        mainLayout = (LinearLayout)findViewById(R.id.mainLayout);

        switchTxt = (TextView)findViewById(R.id.textSwitch);



        // check if device is supported
        if (isFlashSupported()){
            camera = Camera.open();
            parameters = camera.getParameters();

        } else {
            showFlashAlert();
        }

        switchTxt.setOnClickListener(this);
        mainLayout.setOnClickListener(this);


       /* switchTxt.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                if (isFlashOn){

                    // change text and background color
                    switchTxt.setText(getString(R.string.off_txt));
                    switchTxt.setBackgroundColor(Color.parseColor("#000000"));
                    switchTxt.setTextColor(Color.parseColor("#ffffff"));
                    mainLayout.setBackgroundColor(Color.parseColor("#000000"));

                    // switch light off
                    parameters.setFlashMode(Camera.Parameters.FLASH_MODE_OFF); // turn flash off
                    camera.setParameters(parameters);
                    camera.stopPreview();
                    isFlashOn = false;
                } else if (!isFlashOn){

                    // change text and background color
                    switchTxt.setText(getString(R.string.on_txt));
                    switchTxt.setBackgroundColor(Color.parseColor("#ffffff"));
                    switchTxt.setTextColor(Color.parseColor("#000000"));
                    mainLayout.setBackgroundColor(Color.parseColor("#ffffff"));


                    // turn light on
                    parameters.setFlashMode(Camera.Parameters.FLASH_MODE_TORCH); // turn flash on
                    camera.setParameters(parameters);
                    camera.startPreview();
                    isFlashOn = true;
                }
            }
        });*/


        // ads
        AdView adView = (AdView)findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        adView.loadAd(adRequest);
    }


    public boolean isFlashSupported(){
        PackageManager pm = getPackageManager();
        return pm.hasSystemFeature(PackageManager.FEATURE_CAMERA_FLASH);
    }

    public void showFlashAlert(){

        new AlertDialog.Builder(this)
                .setTitle("Error: No Camera Flash!")
                .setMessage("Camera flashlight not available in this Android device!")
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        finish(); // close the Android app
                    }
                })
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
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

    @Override
    public void onClick(View v) {

        if (isFlashOn){

            // change text and background color
            switchTxt.setText(getString(R.string.off_txt));
            switchTxt.setBackgroundColor(Color.parseColor("#000000"));
            switchTxt.setTextColor(Color.parseColor("#ffffff"));
            mainLayout.setBackgroundColor(Color.parseColor("#000000"));

            // switch light off
            parameters.setFlashMode(Camera.Parameters.FLASH_MODE_OFF); // turn flash off
            camera.setParameters(parameters);
            camera.stopPreview();
            isFlashOn = false;
        } else if (!isFlashOn){

            // change text and background color
            switchTxt.setText(getString(R.string.on_txt));
            switchTxt.setBackgroundColor(Color.parseColor("#ffffff"));
            switchTxt.setTextColor(Color.parseColor("#000000"));
            mainLayout.setBackgroundColor(Color.parseColor("#ffffff"));


            // turn light on
            parameters.setFlashMode(Camera.Parameters.FLASH_MODE_TORCH); // turn flash on
            camera.setParameters(parameters);
            camera.startPreview();
            isFlashOn = true;
        }

    }
}
