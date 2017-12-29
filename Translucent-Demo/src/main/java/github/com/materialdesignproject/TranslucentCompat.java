package github.com.materialdesignproject;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

/**
 * Email  1562363326@qq.com
 * Github https://github.com/skcodestack
 * Created by sk on 2017/12/5
 * Version  1.0
 * Description:沉浸式效果工具栏
 */

public class TranslucentCompat {

    /**
     * 初始化，在setcontentview前调用--兼容4.4-5.0
     * @param activity
     */
    public static void initStatusBar(Activity activity){
        //判断版本,如果[4.4,5.0)就设置状态栏
        if(android.os.Build.VERSION.SDK_INT>=android.os.Build.VERSION_CODES.KITKAT
                &&android.os.Build.VERSION.SDK_INT<android.os.Build.VERSION_CODES.LOLLIPOP){
            activity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
    }
    /**
     * 初始化，在setcontentview前调用--兼容4.4-5.0
     * @param activity
     */
    public static void initNavigationBar(Activity activity){
        //判断版本,如果[4.4,5.0)就设置导航栏为透明
        if(android.os.Build.VERSION.SDK_INT>=android.os.Build.VERSION_CODES.KITKAT
                &&android.os.Build.VERSION.SDK_INT<android.os.Build.VERSION_CODES.LOLLIPOP){
            //设置虚拟导航栏为透明
            activity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        }
    }

    /**
     * 设置状态栏颜色-----toolbar 设置paddingTop 的方式
     * @param activity
     * @param statusBarColor
     */
    public static void setStatusBarColor(Activity activity,View view,int statusBarColor){
        if(android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            //SDK >= 5.0
            activity.getWindow().setStatusBarColor(statusBarColor);

        } else if(android.os.Build.VERSION.SDK_INT>=android.os.Build.VERSION_CODES.KITKAT
                &&android.os.Build.VERSION.SDK_INT<android.os.Build.VERSION_CODES.LOLLIPOP) {
            // 5.0 > SDK >= 4.4
            ViewGroup contentView = (ViewGroup) activity.findViewById(android.R.id.content);
//            if (contentView.getChildCount() <= 0) {
//                throw new NullPointerException("please  invoke  after  setContentView");
//            }
//            ViewGroup customContentView = (ViewGroup) contentView.getChildAt(0);
//            if (!customContentView.getFitsSystemWindows()) {
//                customContentView.setFitsSystemWindows(true);
//            }
            if(view == null){
                throw new NullPointerException("view is null pinter");
            }
            if(view instanceof Toolbar){
                Toolbar toolbar = (Toolbar) view;
                //1.先设置toolbar的高度
                int statusBarHeight =HelperUtil.getStatusBarHeight(activity);
                ViewGroup.LayoutParams params = toolbar.getLayoutParams();
                params.height += statusBarHeight ;
                toolbar.setLayoutParams(params );
                //2.设置paddingTop，以达到状态栏不遮挡toolbar的内容。
                toolbar.setPadding(
                        toolbar.getPaddingLeft(),
                        toolbar.getPaddingTop()+statusBarHeight,
                        toolbar.getPaddingRight(),
                        toolbar.getPaddingBottom());
                //设置顶部的颜色
                toolbar.setBackgroundColor(statusBarColor);
            }else {
                int statusBarHeight = HelperUtil.getStatusBarHeight(activity);
                ViewGroup.LayoutParams params = view.getLayoutParams();
                params.height += statusBarHeight ;
                view.setLayoutParams(params );
                //2.设置paddingTop，以达到状态栏不遮挡toolbar的内容。
                view.setPadding(
                        view.getPaddingLeft(),
                        view.getPaddingTop()+statusBarHeight,
                        view.getPaddingRight(),
                        view.getPaddingBottom());
                //设置顶部的颜色
                view.setBackgroundColor(statusBarColor);
            }
        }else {
            //SDK < 4.4

        }
    }

    /**
     * 设置状态栏颜色----添加view的方式
     * @param activity
     * @param statusBarColor
     */
    public static void setStatusBarColor(Activity activity,int statusBarColor){
        if(android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            //SDK >= 5.0
            activity.getWindow().setStatusBarColor(statusBarColor);

        } else if(android.os.Build.VERSION.SDK_INT>=android.os.Build.VERSION_CODES.KITKAT
                &&android.os.Build.VERSION.SDK_INT<android.os.Build.VERSION_CODES.LOLLIPOP) {
            // 5.0 > SDK >= 4.4
            ViewGroup contentView = (ViewGroup) activity.findViewById(android.R.id.content);
            if (contentView.getChildCount() <= 0) {
                throw new NullPointerException("please  invoke  after  setContentView");
            }
            ViewGroup customContentView = (ViewGroup) contentView.getChildAt(0);
            if (!customContentView.getFitsSystemWindows()) {
                customContentView.setFitsSystemWindows(true);
            }

            View mStatusBarTintView = new View(activity);
            android.widget.FrameLayout.LayoutParams params = new android.widget.FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, HelperUtil.getStatusBarHeight(activity));
            params.gravity = Gravity.TOP;
            //如果横屏，
//	        if(this.mNavBarAvailable && !this.mConfig.isNavigationAtBottom()) {
//	            params.rightMargin = this.mConfig.getNavigationBarWidth();
//	        }

            mStatusBarTintView.setLayoutParams(params);
            mStatusBarTintView.setBackgroundColor(statusBarColor);
            mStatusBarTintView.setVisibility(View.VISIBLE);
            ((ViewGroup)activity.getWindow().getDecorView()).addView(mStatusBarTintView);


        }else {
            //SDK < 4.4

        }
    }



    /**
     * 设置导航栏颜色--------自定义
     * @param activity
     * @param bottomNavigationBar
     * @param navigationBarColor
     */
    public static void setNavigationBarColor(Activity activity,View bottomNavigationBar,int navigationBarColor){
        if(android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            //SDK >= 5.0

            activity.getWindow().setNavigationBarColor(navigationBarColor);

        } else if(android.os.Build.VERSION.SDK_INT>=android.os.Build.VERSION_CODES.KITKAT
                &&android.os.Build.VERSION.SDK_INT<android.os.Build.VERSION_CODES.LOLLIPOP) {
            // 5.0 > SDK >= 4.4
            if(bottomNavigationBar!=null){
                //解决低版本4.4+的虚拟导航栏的
                if(HelperUtil.hasNavigationBarShow(activity.getWindowManager())){
                    ViewGroup.LayoutParams p = bottomNavigationBar.getLayoutParams();
                    p.height += HelperUtil.getNavBarHeight(activity);
                    bottomNavigationBar.setLayoutParams(p);
                    //设置底部导航栏的颜色
                    bottomNavigationBar.setBackgroundColor(navigationBarColor);
                }
            }else {
                setNavigationBarColor(activity,navigationBarColor);
            }
        }else {
            //SDK < 4.4
        }

    }


    /**
     * 设置导航栏颜色-----添加view的方式
     * @param activity
     * @param navigationBarColor
     */
    public static void setNavigationBarColor(Activity activity, int navigationBarColor) {
        if(android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            //SDK >= 5.0

            activity.getWindow().setNavigationBarColor(navigationBarColor);

        } else if(android.os.Build.VERSION.SDK_INT>=android.os.Build.VERSION_CODES.KITKAT
                &&android.os.Build.VERSION.SDK_INT<android.os.Build.VERSION_CODES.LOLLIPOP) {
            // 5.0 > SDK >= 4.4

            View bottomNavigationBar = new View(activity);
            if(bottomNavigationBar!=null){
                //解决低版本4.4+的虚拟导航栏的
                if(HelperUtil.hasNavigationBarShow(activity.getWindowManager())){
                    android.widget.FrameLayout.LayoutParams params = new android.widget.FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, HelperUtil.getNavBarHeight(activity));
                    params.gravity = Gravity.BOTTOM;
                    //如果横屏，
//                    if(this.mNavBarAvailable && !this.mConfig.isNavigationAtBottom()) {
//        	            params.rightMargin = this.mConfig.getNavigationBarWidth();
//        	        }
                    bottomNavigationBar.setLayoutParams(params);
                    //设置底部导航栏的颜色
                    bottomNavigationBar.setBackgroundColor(navigationBarColor);
                    bottomNavigationBar.setVisibility(View.VISIBLE);
                    ((ViewGroup)activity.getWindow().getDecorView()).addView(bottomNavigationBar);
                }
            }
        }else {
            //SDK < 4.4
        }
    }



}
