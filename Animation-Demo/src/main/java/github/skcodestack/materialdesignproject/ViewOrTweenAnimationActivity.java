package github.skcodestack.materialdesignproject;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.view.animation.BounceInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;

public class ViewOrTweenAnimationActivity extends AppCompatActivity {

    private ImageView target;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_or_tween_animation);

        target = (ImageView) findViewById(R.id.target);
    }

    public void btn_xml(View view){
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.tween_animation);
        animation.setInterpolator(new AccelerateInterpolator());
        target.startAnimation(animation);
    }


    public void btn_code(View view){
        AnimationSet animationSet = new AnimationSet(false);
        TranslateAnimation translateAnimation = new TranslateAnimation(0,0,0,200);
        translateAnimation.setDuration(2000);
        translateAnimation.setInterpolator(new AccelerateDecelerateInterpolator());

        AlphaAnimation alphaAnimation = new AlphaAnimation(0.2f,1f);
        alphaAnimation.setDuration(2000);
        alphaAnimation.setInterpolator(new BounceInterpolator());

        animationSet.addAnimation(translateAnimation);
        animationSet.addAnimation(alphaAnimation);

        target.startAnimation(animationSet);

    }
}
