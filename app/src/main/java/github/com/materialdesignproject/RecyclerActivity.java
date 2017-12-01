package github.com.materialdesignproject;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import github.com.materialdesignproject.adapter.CustomRecyclerAdapter;

/**
 * Email  1562363326@qq.com
 * Github https://github.com/skcodestack
 * Created by sk on 2017/10/12
 * Version  1.0
 * Description:
 */

public class RecyclerActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //判断版本,如果[4.4,5.0)就设置状态栏和导航栏为透明
//        if(android.os.Build.VERSION.SDK_INT>=android.os.Build.VERSION_CODES.KITKAT
//                &&android.os.Build.VERSION.SDK_INT<android.os.Build.VERSION_CODES.LOLLIPOP){
//            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
//            //设置虚拟导航栏为透明
//            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
//        }
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

        setContentView(R.layout.activity_recycler);

        toolbar = (Toolbar) findViewById(R.id.toolbar);

//        setOrChangeTranslucentColor(toolbar,null, getResources().getColor(android.R.color.transparent));

        if(toolbar!=null){
            //1.先设置toolbar的高度
            ViewGroup.LayoutParams params = toolbar.getLayoutParams();
            int statusBarHeight = getStatusBarHeight(this);
            params.height += statusBarHeight ;
            toolbar.setLayoutParams(params );
            //2.设置paddingTop，以达到状态栏不遮挡toolbar的内容。
            toolbar.setPadding(
                    toolbar.getPaddingLeft(),
                    toolbar.getPaddingTop()+getStatusBarHeight(this),
                    toolbar.getPaddingRight(),
                    toolbar.getPaddingBottom());
            //设置顶部的颜色
//                toolbar.setBackgroundColor(getResources().getColor(android.R.color.transparent));
            toolbar.getBackground().setAlpha(0);
        }

        recyclerView = (RecyclerView) findViewById(R.id.recyclerview);

        recyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));


        CustomRecyclerAdapter  recyclerAdapter = new CustomRecyclerAdapter(this.getBaseContext(),getListDate());
        recyclerView.setAdapter(recyclerAdapter);


        Display display = getWindowManager().getDefaultDisplay();
        DisplayMetrics outMetrics = new DisplayMetrics();
        //获取整个屏幕的高度
        display.getMetrics(outMetrics);
        final int heightPixels = outMetrics.heightPixels;
        int widthPixels = outMetrics.widthPixels;


        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {

                                             @Override
                                             public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                                                 super.onScrollStateChanged(recyclerView, newState);
                                             }

                                             @Override
                                             public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                                                 super.onScrolled(recyclerView, dx, dy);



                                                 LinearLayoutManager layoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
                                                 //int itemPosition = layoutManager.findFirstVisibleItemPosition();

                                                 View viewByPosition = layoutManager.findViewByPosition(2);
                                                 if(viewByPosition != null ) {
                                                     //Log.e("ttttt", "viewByPosition=====>" + viewByPosition.getTop());
                                                     int top = viewByPosition.getTop();


                                                 }
                                             }
                                         }
        );

//        if(Math.abs(scrollY) <= heightPixels / 4) {
//            int percent = (int) (((Math.abs(scrollY) * 1.0f / (heightPixels / 4))) * 255);
//            Log.e("ttttt", "percent=====>" + percent + "---dy===>" + dy + "----scrollY====>" + scrollY);
//            toolbar.getBackground().setAlpha(percent);
//        }
    }







    private List<String> getListDate(){
        List<String> listData = new ArrayList<>();
        int count = 30;
        for (int i = 1; i < count; i++) {
            listData.add("元素"+i);
        }

        return listData;
    }


    @SuppressLint("NewApi")
    public void setOrChangeTranslucentColor(Toolbar toolbar, View bottomNavigationBar, int translucentPrimaryColor){
        //判断版本,如果[4.4,5.0)就设置状态栏和导航栏为透明
        if(android.os.Build.VERSION.SDK_INT>=android.os.Build.VERSION_CODES.KITKAT
                &&android.os.Build.VERSION.SDK_INT<android.os.Build.VERSION_CODES.LOLLIPOP){
            if(toolbar!=null){
                //1.先设置toolbar的高度
                ViewGroup.LayoutParams params = toolbar.getLayoutParams();
                int statusBarHeight = getStatusBarHeight(this);
                params.height += statusBarHeight ;
                toolbar.setLayoutParams(params );
                //2.设置paddingTop，以达到状态栏不遮挡toolbar的内容。
                toolbar.setPadding(
                        toolbar.getPaddingLeft(),
                        toolbar.getPaddingTop()+getStatusBarHeight(this),
                        toolbar.getPaddingRight(),
                        toolbar.getPaddingBottom());
                //设置顶部的颜色
//                toolbar.setBackgroundColor(translucentPrimaryColor);
            }
            if(bottomNavigationBar!=null){
                //解决低版本4.4+的虚拟导航栏的
                if(hasNavigationBarShow(getWindowManager())){
                    ViewGroup.LayoutParams p = bottomNavigationBar.getLayoutParams();
                    p.height += getNavigationBarHeight(this);
                    bottomNavigationBar.setLayoutParams(p);
                    //设置底部导航栏的颜色
                    bottomNavigationBar.setBackgroundColor(translucentPrimaryColor);
                }
            }
        }else if(android.os.Build.VERSION.SDK_INT>=android.os.Build.VERSION_CODES.LOLLIPOP){
//            getWindow().setNavigationBarColor(translucentPrimaryColor);
//            getWindow().setStatusBarColor(translucentPrimaryColor);
        }else{
            //<4.4的，不做处理
        }
    }


    private int getNavigationBarHeight(Context context) {
        return getSystemComponentDimen(this, "navigation_bar_height");
    }

    /**
     * 获取状态栏的高度
     * @param context
     * @return
     */
    private int getStatusBarHeight(Context context) {
        // 反射手机运行的类：android.R.dimen.status_bar_height.
        return getSystemComponentDimen(this, "status_bar_height");
    }

    private static int getSystemComponentDimen(Context context, String dimenName){
        // 反射手机运行的类：android.R.dimen.status_bar_height.
        int statusHeight = -1;
        try {
            Class<?> clazz = Class.forName("com.android.internal.R$dimen");
            Object object = clazz.newInstance();
            String heightStr = clazz.getField(dimenName).get(object).toString();
            int height = Integer.parseInt(heightStr);
            //dp--->px
            statusHeight = context.getResources().getDimensionPixelSize(height);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return statusHeight;
    }

    @SuppressLint("NewApi")
    private static boolean hasNavigationBarShow(WindowManager wm){
        Display display = wm.getDefaultDisplay();
        DisplayMetrics outMetrics = new DisplayMetrics();
        //获取整个屏幕的高度
        display.getRealMetrics(outMetrics);
        int heightPixels = outMetrics.heightPixels;
        int widthPixels = outMetrics.widthPixels;
        //获取内容展示部分的高度
        outMetrics = new DisplayMetrics();
        display.getMetrics(outMetrics);
        int heightPixels2 = outMetrics.heightPixels;
        int widthPixels2 = outMetrics.widthPixels;
        int w = widthPixels-widthPixels2;
        int h = heightPixels-heightPixels2;
        System.out.println("~~~~~~~~~~~~~~~~h:"+h);
        return  w>0||h>0;//竖屏和横屏两种情况。
    }




}
