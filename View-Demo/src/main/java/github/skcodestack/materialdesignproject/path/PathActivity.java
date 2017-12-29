package github.skcodestack.materialdesignproject.path;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import github.skcodestack.materialdesignproject.R;

public class PathActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_path);
    }

    public void btn_wave(View view){
        startActivity(new Intent(view.getContext(),WaveActivity.class));
    }

    public void btn_pathmesure(View view){
        startActivity(new Intent(view.getContext(),PathMeasureActivity.class));
    }
}
