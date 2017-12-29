package github.skcodestack.materialdesignproject.test;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import github.skcodestack.materialdesignproject.R;

public class TestTouchEventActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_touch_event);
    }

    public  void btn_sviewgroup(View view){
        startActivity(new Intent(view.getContext(),SingalViewGroupActivity.class));
    }

    public  void btn_dviewgroup(View view){
        startActivity(new Intent(view.getContext(),DoubleViewGroupActivity.class));
    }
}
