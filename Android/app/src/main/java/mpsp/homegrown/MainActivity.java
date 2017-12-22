package mpsp.homegrown;

import android.graphics.PointF;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.WindowManager;
import android.view.animation.Transformation;
import android.widget.TextView;

import static android.view.Window.FEATURE_NO_TITLE;

public class MainActivity extends AppCompatActivity {

    private TextView mTextMessage;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment selectedFragment = null;
            FragmentTransaction transaction = null;
            switch (item.getItemId()) {
                case R.id.navigation_explore:
                    selectedFragment = ExploreFragment.newInstance();
                    transaction = getSupportFragmentManager().beginTransaction();
                    transaction.replace(R.id.fragment_frame, selectedFragment);
                    transaction.commit();
                    return true;
                case R.id.navigation_producers:
                    return true;
                case R.id.navigation_profile:
                    selectedFragment = ProfileFragment.newInstance();
                    transaction = getSupportFragmentManager().beginTransaction();
                    transaction.replace(R.id.fragment_frame, selectedFragment);
                    transaction.commit();
                    return true;
            }

            return false;
        }

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        requestWindowFeature(FEATURE_NO_TITLE);

        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        setContentView(R.layout.activity_main);

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        Fragment selectedFragment = ExploreFragment.newInstance();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_frame, selectedFragment);
        transaction.commit();
    }

}
