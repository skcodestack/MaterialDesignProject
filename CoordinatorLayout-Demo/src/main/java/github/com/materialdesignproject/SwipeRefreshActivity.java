package github.com.materialdesignproject;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import java.util.LinkedList;
import java.util.List;

import github.com.materialdesignproject.fragment.CustomListViewFragment;
import github.com.materialdesignproject.fragment.CustomScrollViewFragment;
import github.com.materialdesignproject.fragment.CustomSwipeRecyclerViewFragment;
import github.com.materialdesignproject.fragment.CustomSwipeRecyclerViewFragment2;
import github.com.materialdesignproject.fragment.CustomNestedScrollViewFragment;
import github.com.materialdesignproject.fragment.RecyclerViewFragment;

public class SwipeRefreshActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_swipe_refresh);


        initView();
    }


    private void initView() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("recycerview");
        setSupportActionBar(toolbar);

        ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);
        MyFragmentPagerAdapter adapter = new MyFragmentPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(RecyclerViewFragment.newInstance(),"初级");
        adapter.addFragment(CustomSwipeRecyclerViewFragment2.newInstance(),"中级");
        adapter.addFragment(CustomSwipeRecyclerViewFragment.newInstance(),"高级");
        adapter.addFragment(CustomNestedScrollViewFragment.newInstance(),"nestedScrollView");
        adapter.addFragment(CustomListViewFragment.newInstance(),"listview");
        adapter.addFragment(CustomScrollViewFragment.newInstance(),"scrollView");
        viewPager.setAdapter(adapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);
    }

    private class MyFragmentPagerAdapter extends FragmentPagerAdapter {
        private List<Fragment> mFragments = new LinkedList<>();
        private List<String> mTitles = new LinkedList<>();
        public MyFragmentPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        public void addFragment(Fragment fragment, String title) {
            mFragments.add(fragment);
            mTitles.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mTitles.get(position);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragments.get(position);
        }

        @Override
        public int getCount() {
            return mFragments.size();
        }
    }
}
