package mpsp.homegrown;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

public class CheckOutActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_out);

        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Checkout");
        actionBar.setDisplayHomeAsUpEnabled(true);
        //New badges



    }
    @Override
    public boolean onSupportNavigateUp(){
        finish();
        return true;
    }

    public void pay(View view){
        //Start checkout complete activity
        Intent intent = new Intent(this, CheckoutComplete.class);
        startActivity(intent);
    }
}
