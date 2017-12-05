package github.com.materialdesignproject.custom.qq5;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.widget.FrameLayout;

import github.com.materialdesignproject.R;
import github.com.materialdesignproject.custom.qq5.view.DragLayout;
import github.com.materialdesignproject.custom.qq5.view.MyFrameLayout;

/**
 * Email  1562363326@qq.com
 * Github https://github.com/skcodestack
 * Created by sk on 2017/12/1
 * Version  1.0
 * Description:
 */

public class QQ5Activity extends AppCompatActivity {

    private DragLayout dl_main;
    private FrameLayout left_content;
    private MyFrameLayout main_content;
    private LeftFrament leftframent;
    private MainFrament mainframent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.qq5_activity);
        init();
    }

    private void init() {
        initView();
        initListener();
    }



    private void initView() {
        dl_main = (DragLayout) findViewById(R.id.dl_mian);
        left_content = (FrameLayout) findViewById(R.id.leftcontent);
        main_content = (MyFrameLayout) findViewById(R.id.maincontent);
        main_content.setDragLayout(dl_main);

        leftframent = new LeftFrament(QQ5Activity.this);
        mainframent = new MainFrament(QQ5Activity.this);
        left_content.addView(leftframent.view);
        main_content.addView(mainframent.view);

    }
    private void initListener() {
        dl_main.setDragStatusListenter(new DragLayout.OnDragStatusChangeListenter() {

            @Override
            public void open() {
                //				Utiles.showToast(MainActivity.this, "open");
                leftframent.scrolListView();
            }

            @Override
            public void draging(float percent) {
                //				Utiles.showToast(MainActivity.this, "draging");
                mainframent.setHeaderAlpha(percent);
            }

            @Override
            public void close() {
                //				Utiles.showToast(MainActivity.this, "close");
                mainframent.setShakeHead();
            }
        });

    }
    public  void  OpenLeftFramet(){
        dl_main.open();
    }

}
