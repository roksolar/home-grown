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

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

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
        // Get Badges
        getBadges();
    }

    private void getBadges() {
        //Get data

        //Loop through badges
        for (int i = 0; i < 5; i++){
            switch (i) {
                case 0: break;
                case 1: break;
                case 2: break;
                case 3: break;
                case 4: break;

            }
        }
        // Make image black & white (if user doesn't own the badge
        ImageView imageview = (ImageView) findViewById(R.id.badge1_image);
        ColorMatrix matrix = new ColorMatrix();
        matrix.setSaturation(0);

        ColorMatrixColorFilter filter = new ColorMatrixColorFilter(matrix);
        imageview.setColorFilter(filter);
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
                    url = new URL("http://demo7168011.mockable.io/getPoints");
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

}
