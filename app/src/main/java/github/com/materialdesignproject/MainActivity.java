package github.com.materialdesignproject;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.MainThread;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.view.ViewConfigurationCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ScrollView;

public class MainActivity extends AppCompatActivity {


    private Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.context = this;

    }


    public void applayout(View view){
        startActivity(new Intent(context,AppLayoutActivity.class));
    }


    public void floating(View view){
        Intent intent = new Intent(context, FloatingActivity.class);
        startActivity(intent);
    }

    public void search_toolbar(View view){
        startActivity(new Intent(context,SearchActivity.class));
    }

    public void translucent_toolbar(View view){
        startActivity(new Intent(context,TranslucentToolBarActivity.class));
    }

    public void palette_click(View view){
        startActivity(new Intent(context,PaletteActivity.class));
    }

    public void tablayout_click(View view){
        startActivity(new Intent(context,TabLayoutActivity.class));
    }

    public void tablayout_footer_click(View view){
        startActivity(new Intent(context,TabLayoutFooterActivity.class));
    }
}
