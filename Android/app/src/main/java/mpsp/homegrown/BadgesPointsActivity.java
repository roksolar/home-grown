package mpsp.homegrown;

import android.app.ActionBar;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.JsonReader;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import javax.net.ssl.HttpsURLConnection;

public class BadgesPointsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_badges_points);
        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Badges & Points");
        actionBar.setDisplayHomeAsUpEnabled(true);
        // Get loyalty points
        getPoints();
        // Reset badges
        resetBadges();
        // Get Badges
        getBadges();

    }

    private void resetBadges() {
        //Loop through badges
        for (int i = 0; i < 15; i++){
            switch (i) {
                case 0:
                    makeBlackWhite((ImageView)findViewById(R.id.badge1_image));
                    break;
                case 1:
                    makeBlackWhite((ImageView)findViewById(R.id.badge2_image));
                    break;
                case 2:
                    makeBlackWhite((ImageView)findViewById(R.id.badge3_image));
                    break;
                case 3:
                    makeBlackWhite((ImageView)findViewById(R.id.badge4_image));
                    break;
                case 4:
                    makeBlackWhite((ImageView)findViewById(R.id.badge5_image));
                    break;
                case 5:
                    makeBlackWhite((ImageView)findViewById(R.id.badge6_image));
                    break;
                case 6:
                    makeBlackWhite((ImageView)findViewById(R.id.badge7_image));
                    break;
                case 7:
                    makeBlackWhite((ImageView)findViewById(R.id.badge8_image));
                    break;
                case 8:
                    makeBlackWhite((ImageView)findViewById(R.id.badge9_image));
                    break;
                case 9:
                    makeBlackWhite((ImageView)findViewById(R.id.badge10_image));
                    break;
                case 10:
                    makeBlackWhite((ImageView)findViewById(R.id.badge11_image));
                    break;
                case 11:
                    makeBlackWhite((ImageView)findViewById(R.id.badge12_image));
                    break;
                case 12:
                    makeBlackWhite((ImageView)findViewById(R.id.badge13_image));
                    break;
                case 13:
                    makeBlackWhite((ImageView)findViewById(R.id.badge14_image));
                    break;
                case 14:
                    makeBlackWhite((ImageView)findViewById(R.id.badge15_image));
                    break;
            }
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        // Get loyalty points
        getPoints();
        // Get Badges
        getBadges();
    }

    private void getBadges() {



        //Get and process data
        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                // Network logic
                // Create URL
                URL url = null;
                int id;
                int progress;
                ArrayList badges = new ArrayList();
                try {
                    url = new URL("http://192.168.137.1:8081/getBadges/tilenav");
                    // Create connection
                    HttpURLConnection connection =
                            (HttpURLConnection) url.openConnection();

                    connection.setRequestProperty("User-Agent", "HomeGrown");
                    connection.setRequestProperty("User", "krispret");

                    if (connection.getResponseCode() == 200) {
                        // Success
                        // Further processing here

                        // Reading response
                        InputStream responseBody = connection.getInputStream();
                        InputStreamReader responseBodyReader =
                                new InputStreamReader(responseBody, "UTF-8");

                        //Parsing JSON
                        JsonReader jsonReader = new JsonReader(responseBodyReader);
                        jsonReader.beginArray();
                        //jsonReader.beginObject(); // Start processing the JSON object
                        while (jsonReader.hasNext()) { // Loop through all keys
                            //badgex
                           // jsonReader.nextName();
                            //Go in each badge
                            jsonReader.beginObject();
                            //Badge id
                            jsonReader.nextName();
                            id = jsonReader.nextInt();
                            //Badge name
                            jsonReader.nextName();
                            progress = jsonReader.nextInt();
                            jsonReader.endObject();


                            //Add to array
                            badges.add(new Badge(id, progress));
                            //Process badges
                            processBadges(badges);
                        }
                    } else {
                        Log.e("Error", "Response code not 200.");
                    }
                } catch (Exception e) {
                    Log.e("Error", "Can't get badges.");
                    e.printStackTrace();
                }


            }
        });
    }

    @Override
    public boolean onSupportNavigateUp(){
        finish();
        return true;
    }

    private void getPoints(){
        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                // Network logic
                // Create URL
                URL url = null;
                try {
                    //url = new URL("http://demo7168011.mockable.io/getPoints");
                    url = new URL("http://192.168.137.1:8081/getPoints/tilenav");
                    // Create connection
                    HttpURLConnection connection =
                            (HttpURLConnection) url.openConnection();

                    connection.setRequestProperty("User-Agent", "HomeGrown");
                    connection.setRequestProperty("User", "krispret");
                    if (connection.getResponseCode() == 200) {
                        // Success
                        // Further processing here

                        // Reading response
                        InputStream responseBody = connection.getInputStream();
                        InputStreamReader responseBodyReader =
                                new InputStreamReader(responseBody, "UTF-8");

                        //Parsing JSON
                        JsonReader jsonReader = new JsonReader(responseBodyReader);
                        jsonReader.beginArray();
                        jsonReader.beginObject(); // Start processing the JSON object
                        while (jsonReader.hasNext()) { // Loop through all keys
                            String key = jsonReader.nextName(); // Fetch the next key
                            if (key.equals("points")) { // Check if desired key
                                // Fetch the value as a String
                                String points = jsonReader.nextString();

                                // Show points
                                ((TextView)findViewById(R.id.pointsTV)).setText(points + " (" + Double.parseDouble(points)/1000 + "€)" );

                                break; // Break out of the loop
                            } else {
                                jsonReader.skipValue(); // Skip values of other keys
                            }
                        }
                    } else {
                        Log.e("Error", "Response code not 200.");
                    }
                } catch (Exception e) {
                    Log.e("Error", "Can't get points.");
                    e.printStackTrace();
                }


            }
        });
    }

    private void processBadges(ArrayList<Badge> badges){
        //Loop through badges
        for (int i = 0; i < badges.size(); i++){
            switch (badges.get(i).getId()) {
                case 0:
                    setProgress((TextView)findViewById(R.id.badge1_data2), badges.get(i).getProgress(), 1);
                    if (badges.get(i).getProgress() >= 1) {
                        makeColor((ImageView)findViewById(R.id.badge1_image));
                    }
                    break;
                case 1:
                    setProgress((TextView)findViewById(R.id.badge2_data2), badges.get(i).getProgress(), 2);
                    if (badges.get(i).getProgress() >= 2) {
                        makeColor((ImageView)findViewById(R.id.badge2_image));
                    }
                    break;
                case 2:
                    setProgress((TextView)findViewById(R.id.badge3_data2), badges.get(i).getProgress(), 3);
                    if (badges.get(i).getProgress() >= 3) {
                        makeColor((ImageView)findViewById(R.id.badge3_image));
                    }
                    break;
                case 3:
                    setProgress((TextView)findViewById(R.id.badge4_data2), badges.get(i).getProgress(), 6);
                    if (badges.get(i).getProgress() >= 6) {
                        makeColor((ImageView)findViewById(R.id.badge4_image));
                    }
                    break;
                case 4:
                    setProgress((TextView)findViewById(R.id.badge5_data2), badges.get(i).getProgress(), 5);
                    if (badges.get(i).getProgress() >= 5) {
                        makeColor((ImageView)findViewById(R.id.badge5_image));
                    }
                    break;
                case 5:
                    setProgress((TextView)findViewById(R.id.badge6_data2), badges.get(i).getProgress(), 10);
                    if (badges.get(i).getProgress() >= 10) {
                        makeColor((ImageView)findViewById(R.id.badge6_image));
                    }
                    break;
                case 6:
                    setProgress((TextView)findViewById(R.id.badge7_data2), badges.get(i).getProgress(), 20);
                    if (badges.get(i).getProgress() >= 20) {
                        makeColor((ImageView)findViewById(R.id.badge7_image));
                    }
                    break;
                case 7:
                    setProgress((TextView)findViewById(R.id.badge8_data2), badges.get(i).getProgress(), 50);
                    if (badges.get(i).getProgress() >= 50) {
                        makeColor((ImageView)findViewById(R.id.badge8_image));
                    }
                    break;
                case 8:
                    setProgress((TextView)findViewById(R.id.badge9_data2), badges.get(i).getProgress(), 100);
                    if (badges.get(i).getProgress() >= 100) {
                        makeColor((ImageView)findViewById(R.id.badge9_image));
                    }
                    break;
                case 9:
                    setProgress((TextView)findViewById(R.id.badge10_data2), badges.get(i).getProgress(), 1);
                    if (badges.get(i).getProgress() >= 1) {
                        makeColor((ImageView)findViewById(R.id.badge10_image));
                    }
                    break;
                case 10:
                    setProgress((TextView)findViewById(R.id.badge11_data2), badges.get(i).getProgress(), 20);
                    if (badges.get(i).getProgress() >= 20) {
                        makeColor((ImageView)findViewById(R.id.badge11_image));
                    }
                    break;
                case 11:
                    setProgress((TextView)findViewById(R.id.badge12_data2), badges.get(i).getProgress(), 7);
                    if (badges.get(i).getProgress() >= 7) {
                        makeColor((ImageView)findViewById(R.id.badge12_image));
                    }
                    break;
                case 12:
                    setProgress((TextView)findViewById(R.id.badge13_data2), badges.get(i).getProgress(), 4);
                    if (badges.get(i).getProgress() >= 4) {
                        makeColor((ImageView)findViewById(R.id.badge13_image));
                    }
                    break;
                case 13:
                    setProgress((TextView)findViewById(R.id.badge14_data2), badges.get(i).getProgress(), 6);
                    if (badges.get(i).getProgress() >= 6) {
                        makeColor((ImageView)findViewById(R.id.badge14_image));
                    }
                    break;
                case 14:
                    setProgress((TextView)findViewById(R.id.badge15_data2), badges.get(i).getProgress(), 20);
                    if (badges.get(i).getProgress() >= 20) {
                        makeColor((ImageView)findViewById(R.id.badge15_image));
                    }
                    break;
            }
        }

    }

    private void makeColor(ImageView badge){
        // Make image color if user has badge
        ImageView imageview = badge;
        ColorMatrix matrix = new ColorMatrix();
        matrix.setSaturation(1);

        ColorMatrixColorFilter filter = new ColorMatrixColorFilter(matrix);
        imageview.setColorFilter(filter);
    }

    private void makeBlackWhite(ImageView badge){
        // Make image black & white (if user doesn't own the badge)
        ImageView imageview = badge;
        ColorMatrix matrix = new ColorMatrix();
        matrix.setSaturation(0);

        ColorMatrixColorFilter filter = new ColorMatrixColorFilter(matrix);
        imageview.setColorFilter(filter);
    }
    private void setProgress(final TextView progressView, final int progress, final int maxProgress){
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                progressView.setText(Integer.toString(progress) + "/" + Integer.toString(maxProgress));
            }
        });

    }
}
