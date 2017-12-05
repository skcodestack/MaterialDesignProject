package github.com.materialdesignproject;

import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;

public class AppLayoutActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app_layout);

        final TextInputLayout textInputLayout = (TextInputLayout) findViewById(R.id.textinput);

        textInputLayout.getEditText().addTextChangedListener(new TextWatcher() {



            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String str = s.toString();
                if(str.length() > 6){
                    textInputLayout.setErrorEnabled(true);
                    textInputLayout.setError("输入不能成功6位");
                }else {
                    textInputLayout.setErrorEnabled(false);
                }

            }
        });
    }
}
