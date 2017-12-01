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
        LayoutInflater.from(this).inflate()

        //添加View   android.view.LayoutInflater
//        final View view = createViewFromTag(parent, name, context, attrs);
//        final ViewGroup viewGroup = (ViewGroup) parent;
//        final ViewGroup.LayoutParams params = viewGroup.generateLayoutParams(attrs);
//        rInflateChildren(parser, view, attrs, true);
//        viewGroup.addView(view, params);
    }

    public void recycleractivity(View view){
        Intent intent = new Intent(context, RecyclerActivity.class);
        startActivity(intent);
//        int scaledTouchSlop = ViewConfiguration.get(this).getScaledTouchSlop();
//        float density = getResources().getDisplayMetrics().density;



    }

    public void floating(View view){
        Intent intent = new Intent(context, FloatingActivity.class);
        startActivity(intent);
    }


}
