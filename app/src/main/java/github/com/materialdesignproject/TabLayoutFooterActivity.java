package github.com.materialdesignproject;

import android.graphics.Color;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import github.com.materialdesignproject.tablayout.NewsDetailFragment;
import github.com.materialdesignproject.tablayout.TabLayoutAdapter;

public class TabLayoutFooterActivity extends AppCompatActivity {
    private String[] title = {
            "头条",
            "新闻",
            "娱乐",
            "体育"
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab_layout_footer);

        ViewPager viewpager = (ViewPager) findViewById(R.id.viewpager);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tablayout);
        TabLayoutAdapter adapter = new TabLayoutAdapter(getSupportFragmentManager());

        for (int i = 0; i < title.length; i++) {
            String s = title[i];


            NewsDetailFragment fragment = new NewsDetailFragment();
            Bundle bundle = new Bundle();
            bundle.putString("title",s);
            fragment.setArguments(bundle);

            adapter.addFragment(fragment,s);
        }

        viewpager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewpager);

        int tabCount = tabLayout.getTabCount();
        for (int i = 0; i < tabCount; i++) {
            TabLayout.Tab tab = tabLayout.getTabAt(i);
            CharSequence text = tab.getText();
            View inflate = LayoutInflater.from(this).inflate(R.layout.layout_footer_item, null);
            TextView tv_name = (TextView) inflate.findViewById(R.id.tv_name);
            tv_name.setText(text);
            if(i != 0) {
                tv_name.setTextColor(Color.BLACK);
            }else {
                tv_name.setTextColor(Color.RED);
            }
            tab.setCustomView(inflate);
        }

        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener(){

            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                View customView = tab.getCustomView();
                TextView textView = (TextView) customView.findViewById(R.id.tv_name);
                textView.setTextColor(Color.RED);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                View customView = tab.getCustomView();
                TextView textView = (TextView) customView.findViewById(R.id.tv_name);
                textView.setTextColor(Color.BLACK);
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }
}
