package github.skcodestack.materialdesignproject.paint;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import github.skcodestack.materialdesignproject.R;

public class BaseUserActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base_user);
    }
}
