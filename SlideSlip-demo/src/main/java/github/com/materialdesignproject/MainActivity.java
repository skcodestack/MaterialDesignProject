package github.com.materialdesignproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import github.com.materialdesignproject.custom.qq5.QQ5Activity;
import github.com.materialdesignproject.custom.qq6.QQ6Activity;
import github.com.materialdesignproject.drawerlayout.DrawerLayoutActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    public void drawerlayout_click(View view){
        startActivity(new Intent(MainActivity.this, DrawerLayoutActivity.class));
    }
    public void qq5_click(View view){
        startActivity(new Intent(MainActivity.this, QQ5Activity.class));
    }
    public void qq6_click(View view){
        startActivity(new Intent(MainActivity.this, QQ6Activity.class));
    }


}
