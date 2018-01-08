package mpsp.homegrown;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.JsonReader;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class CheckOutActivity extends AppCompatActivity {

    private int tocke;
    private int orderType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_out);

        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Checkout");
        actionBar.setDisplayHomeAsUpEnabled(true);
        //Get points
        getPoints();

        //New badges
        final double price;
        Intent intent = getIntent();
        int id = intent.getIntExtra("idKosarice", 1);
        switch (id) {
            case 0:
                price = 0;
                break;
            case 1:
                //Košarica za variety in eko5x značko
                //Show items
                (findViewById(R.id.one)).setVisibility(View.VISIBLE);
                (findViewById(R.id.two)).setVisibility(View.VISIBLE);
                (findViewById(R.id.three)).setVisibility(View.VISIBLE);
                (findViewById(R.id.four)).setVisibility(View.VISIBLE);
                (findViewById(R.id.five)).setVisibility(View.VISIBLE);
                //Show price
                price = 32.3;
                ((TextView) findViewById(R.id.totalPrice)).setText(Double.toString(price));

                orderType = 0;
                break;
            case 2:
                //Košarica za go big or go home značko
                //Show items
                (findViewById(R.id.one)).setVisibility(View.VISIBLE);
                (findViewById(R.id.two)).setVisibility(View.VISIBLE);
                (findViewById(R.id.four)).setVisibility(View.VISIBLE);
                (findViewById(R.id.five)).setVisibility(View.VISIBLE);
                (findViewById(R.id.six)).setVisibility(View.VISIBLE);
                (findViewById(R.id.seven)).setVisibility(View.VISIBLE);
                //Show price
                price = 166.7;
                ((TextView) findViewById(R.id.totalPrice)).setText(Double.toString(price));

                orderType = 1;
                break;
            case 3:
                //Košarica za go big or go home značko
                //Show items
                //(findViewById(R.id.one)).setVisibility(View.VISIBLE);
                (findViewById(R.id.two)).setVisibility(View.VISIBLE);
                (findViewById(R.id.three)).setVisibility(View.VISIBLE);;
                //(findViewById(R.id.six)).setVisibility(View.VISIBLE);
                //(findViewById(R.id.seven)).setVisibility(View.VISIBLE);
                //Show price
                price = 152.7;
                ((TextView) findViewById(R.id.totalPrice)).setText(Double.toString(price));

                orderType = 2;
                break;
            case 4:
                price = 0;
                orderType = 3;
                break;
            default:
                price = 0;
                orderType = 4;
        }

        //Setup seekbar for loyality points
        SeekBar seekBar = (SeekBar) findViewById(R.id.loyalityPointsSeek);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

                //Show current value and change price
                ((TextView) findViewById(R.id.loyalityPoints)).setText("" + progress);
                double scale = Math.pow(10, 2);
                ((TextView) findViewById(R.id.totalPrice)).setText(Double.toString(Math.round((price-(progress/1000.0))*scale)/scale));
                tocke = progress;

            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    private void getPoints() {
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
                                ((SeekBar)findViewById(R.id.loyalityPointsSeek)).setMax(Integer.parseInt(points));

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

    @Override
    public boolean onSupportNavigateUp () {
        finish();
        return true;
    }


    public void pay(View view){
        //send data
        sendOrder();
    }

    private void sendOrder() {
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
                    //url = new URL("http://demo7168011.mockable.io/getPoints");
                    //url = new URL("http://demo7168011.mockable.io/order");
                    url = new URL("http://192.168.137.1:8081/order/tilenav");
                    // Create connection
                    HttpURLConnection connection =
                            (HttpURLConnection) url.openConnection();
                    connection.setDoOutput(true);
                    connection.setRequestProperty("User-Agent", "HomeGrown");
                    connection.setRequestProperty("Content-Type", "application/json");
                    connection.setRequestMethod("PUT");


                    //Build JSON
                    JSONObject outerJson = new JSONObject();
                    JSONArray json = new JSONArray();
                    for (int i = 0; i < 8; i++){
                        switch (i){
                            //MILK
                            case 0:
                                if ((findViewById(R.id.one)).getVisibility() == View.VISIBLE){
                                    JSONObject item = new JSONObject();
                                    item.put("idProduct", 0);
                                    item.put("quantity", 1);
                                    json.put(item);
                                }
                                break;
                            //APPLES
                            case 1:
                                if ((findViewById(R.id.two)).getVisibility() == View.VISIBLE){
                                    JSONObject item = new JSONObject();
                                    item.put("idProduct", 1);
                                    item.put("quantity", 3);
                                    json.put(item);
                                }

                                break;
                            //BREAD LOAF
                            case 2:
                                if ((findViewById(R.id.three)).getVisibility() == View.VISIBLE){
                                    JSONObject item = new JSONObject();
                                    item.put("idProduct", 2);
                                    item.put("quantity", 3);
                                    json.put(item);
                                }
                                break;
                            //CHICKEN WINGS
                            case 3:
                                if ((findViewById(R.id.four)).getVisibility() == View.VISIBLE){
                                    JSONObject item = new JSONObject();
                                    item.put("idProduct", 3);
                                    item.put("quantity", 3);
                                    json.put(item);
                                }
                                break;
                            //TROUT
                            case 4:
                                if ((findViewById(R.id.five)).getVisibility() == View.VISIBLE){
                                    JSONObject item = new JSONObject();
                                    item.put("idProduct", 4);
                                    item.put("quantity", 1.2);
                                    json.put(item);
                                }
                                break;
                            //PORK
                            case 5:
                                if ((findViewById(R.id.six)).getVisibility() == View.VISIBLE){
                                    JSONObject item = new JSONObject();
                                    item.put("idProduct", 5);
                                    item.put("quantity", 30);
                                    json.put(item);
                                }
                                break;
                            //EGGS
                            case 6:
                                if ((findViewById(R.id.seven)).getVisibility() == View.VISIBLE){
                                    JSONObject item = new JSONObject();
                                    item.put("idProduct", 6);
                                    item.put("quantity", 20);
                                    json.put(item);
                                }
                                break;
                        }
                    }
                    outerJson.put("products", json);
                    outerJson.put("usePoints", tocke);
                    outerJson.put("cart", orderType);

                    Log.i("JSON ", outerJson.toString());
                    //Log.i("DATA", json.toString());

                    OutputStreamWriter out = new OutputStreamWriter(
                            connection.getOutputStream());
                    out.write(outerJson.toString());
                    out.close();

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
                            Log.i("ID", Integer.toString(id));

                            //Badge name
                            jsonReader.nextName();
                            progress = jsonReader.nextInt();
                            jsonReader.endObject();

                            //Add to array
                            badges.add(new Badge(id, progress));

                        }
                        finishCheckout(badges);
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

    private void finishCheckout(ArrayList<Badge> badges) {
        //Send badges to checkoutcomplete activity
        Intent intent = new Intent(this, CheckoutComplete.class);
        intent.putExtra("badges", badges);
        startActivity(intent);
    }

}
