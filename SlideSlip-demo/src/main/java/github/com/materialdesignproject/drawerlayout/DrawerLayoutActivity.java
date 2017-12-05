package github.com.materialdesignproject.drawerlayout;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import github.com.materialdesignproject.R;
/**
 * Email  1562363326@qq.com
 * Github https://github.com/skcodestack
 * Created by sk on 2017/12/1
 * Version  1.0
 * Description:
 */

public class DrawerLayoutActivity extends AppCompatActivity {

    private TextView tv_content;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.drawerlayout_activity);

        TextView tv_content =  (TextView) findViewById(R.id.tv_content);

        tv_content.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showInfo(v);
            }
        });
    }

    private void showInfo(final View view) {
        Snackbar snackbar = Snackbar.make(view,"是否满意？",Snackbar.LENGTH_INDEFINITE);

        snackbar.setAction("确定", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(view.getContext(),"谢谢",Toast.LENGTH_SHORT).show();
            }
        });

        snackbar.addCallback(new Snackbar.Callback(){
            @Override
            public void onDismissed(Snackbar transientBottomBar, int event) {
                super.onDismissed(transientBottomBar, event);
                Toast.makeText(view.getContext(),"消失",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onShown(Snackbar sb) {
                super.onShown(sb);
                Toast.makeText(view.getContext(),"开始",Toast.LENGTH_SHORT).show();
            }
        });
        snackbar.setActionTextColor(Color.BLUE);
        snackbar.show();

    }

}
