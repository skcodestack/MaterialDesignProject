package github.skcodestack.materialdesignproject;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import github.skcodestack.materialdesignproject.parallaxsplash.SplashActivity;

public class MainActivity extends AppCompatActivity {


    Activity mActivity;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivity = this;
        setContentView(R.layout.activity_main);

    }

    /**
     * 属性动画
     * @param view
     */
    public void btn_property_animation(View view){
        startActivity(new Intent(mActivity,PropertyAnimationActivity.class));
    }

    /**
     * 补间动画(View 动画)
     * @param view
     */
    public void btn_tween_animation(View view){
        startActivity(new Intent(mActivity,ViewOrTweenAnimationActivity.class));
    }

    /**
     * 帧动画(Drawable 动画)
     * @param view
     */
    public void btn_frame_animation(View view){
        startActivity(new Intent(mActivity,FrameOrDrawableAnimationActivity.class));
    }
    /**
     * MaterialDesign动画
     * @param view
     */
    public void btn_material_design_animation(View view){
        startActivity(new Intent(mActivity,MaterialDesignAnimationActivity.class));
    }

    /**
     * MaterialDesign转场动画
     * @param view
     */
    public void btn_transfer_animation(View view){
        startActivity(new Intent(mActivity,MaterialDesignTransferActivity.class));
    }

    /**
     * SVG
     */
    public void btn_svg(View view){
        startActivity(new Intent(mActivity,SVGActivity.class));
    }


    public void btn_splash(View view){
        startActivity(new Intent(mActivity,SplashActivity.class));
    }
}
