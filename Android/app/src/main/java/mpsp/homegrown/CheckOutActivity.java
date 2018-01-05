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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_out);

        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Checkout");
        actionBar.setDisplayHomeAsUpEnabled(true);
        //New badges

        Intent intent = getIntent();
        int id = intent.getIntExtra("idKosarice",1);
        switch (id) {
            case 0:

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
                ((Button)findViewById(R.id.checkoutPayB)).setText("PAY (32.3€)");
                ((TextView)findViewById(R.id.totalPrice)).setText("32.3€");

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
                ((Button)findViewById(R.id.checkoutPayB)).setText("PAY (166.7€)");
                ((TextView)findViewById(R.id.totalPrice)).setText("166.7€");
                break;
            case 3:
                break;
            case 4:
                break;
        }


    }
    @Override
    public boolean onSupportNavigateUp(){
        finish();
        return true;
    }

    public void pay(View view){
        //send data
        sendOrder();
        //Start checkout complete activity
        Intent intent = new Intent(this, CheckoutComplete.class);
        startActivity(intent);
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
                    url = new URL("http://demo7168011.mockable.io/order");
                    //url = new URL("http://192.168.137.1:8081/order");
                    // Create connection
                    HttpURLConnection connection =
                            (HttpURLConnection) url.openConnection();
                    connection.setDoOutput(true);
                    connection.setRequestProperty("User-Agent", "HomeGrown");
                    connection.setRequestMethod("PUT");

                    //Build JSON
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

                    //Log.i("DATA", json.toString());

                    OutputStreamWriter out = new OutputStreamWriter(
                            connection.getOutputStream());
                    out.write(json.toString());
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
                            //Badge name
                            jsonReader.nextName();
                            progress = jsonReader.nextInt();
                            jsonReader.endObject();

                            //Add to array
                            badges.add(new Badge(id, progress));
                            //Process badges
                            //TODO: SHOW BADGES IN CHECHOUT COMPLETE ACTIVITY
                            //processBadges(badges);
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

    private void processBadges(ArrayList<Badge> badges) {
        //Loop through badges
        for (int i = 0; i < badges.size(); i++){
            switch (badges.get(i).getId()) {
                case 0:
                    showBadge((LinearLayout)findViewById(R.id.badge1), (TextView)findViewById(R.id.badge11_data2), badges.get(i).getProgress(), 1);
                    break;
                case 1:
                    showBadge((LinearLayout)findViewById(R.id.badge2), (TextView)findViewById(R.id.badge21_data2), badges.get(i).getProgress(), 2);
                    break;
                case 2:
                    showBadge((LinearLayout)findViewById(R.id.badge3), (TextView)findViewById(R.id.badge31_data2), badges.get(i).getProgress(), 3);
                    break;
                case 3:
                    showBadge((LinearLayout)findViewById(R.id.badge4), (TextView)findViewById(R.id.badge41_data2), badges.get(i).getProgress(), 6);
                    break;
                case 4:
                    showBadge((LinearLayout)findViewById(R.id.badge5), (TextView)findViewById(R.id.badge51_data2), badges.get(i).getProgress(), 5);
                    break;
                case 5:
                    showBadge((LinearLayout)findViewById(R.id.badge6), (TextView)findViewById(R.id.badge61_data2), badges.get(i).getProgress(), 10);
                    break;
                case 6:
                    showBadge((LinearLayout)findViewById(R.id.badge7), (TextView)findViewById(R.id.badge71_data2), badges.get(i).getProgress(), 20);
                    break;
                case 7:
                    showBadge((LinearLayout)findViewById(R.id.badge8), (TextView)findViewById(R.id.badge81_data2), badges.get(i).getProgress(), 50);
                    break;
                case 8:
                    showBadge((LinearLayout)findViewById(R.id.badge9), (TextView)findViewById(R.id.badge91_data2), badges.get(i).getProgress(), 100);
                    break;
                case 9:
                    showBadge((LinearLayout)findViewById(R.id.badge10), (TextView)findViewById(R.id.badge101_data2), badges.get(i).getProgress(), 1);
                    break;
                case 10:
                    showBadge((LinearLayout)findViewById(R.id.badge11), (TextView)findViewById(R.id.badge111_data2), badges.get(i).getProgress(), 20);
                    break;
                case 11:
                    showBadge((LinearLayout)findViewById(R.id.badge12), (TextView)findViewById(R.id.badge121_data2), badges.get(i).getProgress(), 7);
                    break;
                case 12:
                    showBadge((LinearLayout)findViewById(R.id.badge13), (TextView)findViewById(R.id.badge131_data2), badges.get(i).getProgress(), 4);
                    break;
                case 13:
                    showBadge((LinearLayout)findViewById(R.id.badge14), (TextView)findViewById(R.id.badge141_data2), badges.get(i).getProgress(), 6);
                    break;
                case 14:
                    showBadge((LinearLayout)findViewById(R.id.badge15), (TextView)findViewById(R.id.badge151_data2), badges.get(i).getProgress(), 20);
                    break;
            }
        }
    }
    private void showBadge(final LinearLayout badge, final TextView progressView, final int progress, final int maxProgress){
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                badge.setVisibility(View.VISIBLE);
                progressView.setText(Integer.toString(progress) + "/" + Integer.toString(maxProgress));

            }
        });

    }

}
