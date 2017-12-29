package github.com.materialdesignproject;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    private Activity mActivity;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.mActivity = this;
    }

    public void simple_onclick(View view){
        startActivity(new Intent(mActivity,SimpleCoordinatorLayoutActivity.class));
    }

    public void flexible_onclick(View view){
        startActivity(new Intent(mActivity,FixibleActivity.class));
    }

    public void swipefresh_onclick(View view){
        startActivity(new Intent(mActivity,SwipeRefreshActivity.class));
    }

}
