package mpsp.homegrown;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class CheckoutComplete extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout_complete);
        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Checkout complete");
    }


    public void returnHome (View view){
        //Start home activity
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
