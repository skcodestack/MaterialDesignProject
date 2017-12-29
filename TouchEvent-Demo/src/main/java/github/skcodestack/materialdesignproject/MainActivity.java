package github.skcodestack.materialdesignproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import github.skcodestack.materialdesignproject.test.TestTouchEventActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    public void btn_recyclerview_album(View view){
        startActivity(new Intent(view.getContext(),RecyclerViewAlbumActivity.class));
    }

    public void btn_album(View view){
        startActivity(new Intent(view.getContext(),AlbumActivity.class));
    }

    public void btn_test(View view){
        startActivity(new Intent(view.getContext(),TestTouchEventActivity.class));
    }
}
