package mpsp.homegrown;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class CheckoutComplete extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout_complete);
        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Checkout complete");

        processBadges();
    }

    private void processBadges() {
        Intent intent = getIntent();
        ArrayList<Badge> badges = intent.getParcelableArrayListExtra("badges");

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
                if (progress >= maxProgress){
                    badge.setVisibility(View.VISIBLE);
                    progressView.setText(Integer.toString(progress) + "/" + Integer.toString(maxProgress));
                }
            }
        });

    }
    public void returnHome (View view){
        //Start home activity
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }


}
