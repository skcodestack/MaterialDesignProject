package github.com.materialdesignproject;

import android.graphics.Color;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.Toast;

public class SearchActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        getWindow().addFlags(Window.FEATURE_NO_TITLE);

        setContentView(R.layout.activity_search);

        DrawerLayout drawerLayout = (DrawerLayout) findViewById(R.id.drawerlayout);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        ActionBarDrawerToggle drawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.app_name, R.string.app_name){

            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
            }
        };
        drawerToggle.syncState();
        drawerLayout.setDrawerListener(drawerToggle);



    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.search_menu,menu);

//        android.support.v7.appcompat.R.layout.abc_search_view.xml
        MenuItem searchItem = menu.findItem(R.id.action_search);
        android.support.v7.widget.SearchView  searchView = (SearchView) MenuItemCompat.getActionView(searchItem);
        ImageView icon = (ImageView) searchView.findViewById(R.id.search_go_btn);
        icon.setImageResource(R.drawable.ic_launcher);
        icon.setVisibility(View.VISIBLE);

        SearchView.SearchAutoComplete et = (SearchView.SearchAutoComplete) searchView.findViewById(R.id.search_src_text);
        et.setHint("输入查询条件");
        et.setHintTextColor(Color.WHITE);

        searchView.setSubmitButtonEnabled(true);

        icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(SearchActivity.this, "提交", Toast.LENGTH_SHORT).show();
            }
        });
//        searchView.setOnSearchClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(SearchActivity.this, "查找", Toast.LENGTH_SHORT).show();
//            }
//        });



        return true;
    }
}
