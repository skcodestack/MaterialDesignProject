package github.skcodestack.materialdesignproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.ScrollView;

import github.skcodestack.materialdesignproject.canvas.CanvasActivity;
import github.skcodestack.materialdesignproject.paint.PaintActivity;
import github.skcodestack.materialdesignproject.path.PathActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void btn_paint(View view){
        startActivity(new Intent(view.getContext(), PaintActivity.class));
    }

    public void btn_canvas(View view){
        startActivity(new Intent(view.getContext(), CanvasActivity.class));
    }

    public void btn_path(View view){
        startActivity(new Intent(view.getContext(), PathActivity.class));
    }
}
