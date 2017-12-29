package github.skcodestack.materialdesignproject;

import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.RotateDrawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.widget.ImageView;

public class FrameOrDrawableAnimationActivity extends AppCompatActivity {

    private ImageView target;
    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_frame_or_drawable_animation);
        mContext = this;
        target = (ImageView) findViewById(R.id.target);
    }


    public void btn_xml(View view){
        target.setImageResource(R.drawable.frame_animation2);
    }

    public void btn_xml2(View view){
        target.setImageResource(R.drawable.frame_animation);
        AnimationDrawable animationDrawable = (AnimationDrawable) target.getDrawable();
        animationDrawable.start();
    }

    public void btn_code(View view){
        int[] refreshAnimSrcs = new int[]{R.drawable.mt_refreshing01, R.drawable.mt_refreshing02, R.drawable.mt_refreshing03, R.drawable.mt_refreshing04, R.drawable.mt_refreshing05, R.drawable.mt_refreshing06};

        AnimationDrawable animationDrawable = new AnimationDrawable();
        for (int animSrc : refreshAnimSrcs) {
            animationDrawable.addFrame(ContextCompat.getDrawable(mContext, animSrc),200);
        }
        target.setImageDrawable(animationDrawable);
        animationDrawable.start();

    }

}
