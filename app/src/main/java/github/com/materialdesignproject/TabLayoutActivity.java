package github.com.materialdesignproject;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;

import github.com.materialdesignproject.tablayout.NewsDetailFragment;
import github.com.materialdesignproject.tablayout.TabLayoutAdapter;

public class TabLayoutActivity extends AppCompatActivity {
    private String[] title = {
            "头条",
            "新闻",
            "娱乐",
            "体育",
            "科技",
            "美女",
            "财经",
            "汽车",
            "房子",
            "头条"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab_layout);

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
    }



}
