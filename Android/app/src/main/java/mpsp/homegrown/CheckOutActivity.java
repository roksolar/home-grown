package mpsp.homegrown;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class CheckOutActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_out);

        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Checkout");
        actionBar.setDisplayHomeAsUpEnabled(true);
    }
    @Override
    public boolean onSupportNavigateUp(){
        finish();
        return true;
    }
}
