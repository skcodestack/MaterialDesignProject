package github.skcodestack.materialdesignproject.paint;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import github.skcodestack.materialdesignproject.R;
import github.skcodestack.materialdesignproject.paint.filter.ColorFilterActivity;
import github.skcodestack.materialdesignproject.paint.filter.FilterActivity;

public class PaintActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paint);
    }

    public void btn_baseuser(View view){
        startActivity(new Intent(view.getContext(),BaseUserActivity.class));
    }

    public void btn_text(View view){
        startActivity(new Intent(view.getContext(),TextActivity.class));
    }

    public void btn_circular(View view){
        startActivity(new Intent(view.getContext(),CircularProgressBarActivity.class));
    }

    public void btn_pricebar(View view){
        startActivity(new Intent(view.getContext(),SlidingPriceBarActivity.class));
    }

    public void btn_shader(View view){
        startActivity(new Intent(view.getContext(),ShaderActivity.class));
    }

    public void btn_filter(View view){
        startActivity(new Intent(view.getContext(),FilterActivity.class));
    }

    public void btn_colorfilter(View view){
        startActivity(new Intent(view.getContext(),ColorFilterActivity.class));
    }
}
