package github.skcodestack.materialdesignproject;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.animation.ArgbEvaluator;
import android.animation.Keyframe;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.TypeEvaluator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.graphics.PointF;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.AnticipateInterpolator;
import android.view.animation.BounceInterpolator;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.annotation.Target;

import github.skcodestack.materialdesignproject.view.CustomTextView;

public class PropertyAnimationActivity extends AppCompatActivity {

    private ImageView target;
    Context mContext ;
    private CustomTextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_property_animation);
        mContext = this;
        tv = (CustomTextView) findViewById(R.id.tv);
        target = (ImageView) findViewById(R.id.target);
        target.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext,"我被点击了",Toast.LENGTH_SHORT).show();
            }
        });

    }


    public void onclick_xml(View view){
        Animation animation = AnimationUtils.loadAnimation(mContext, R.anim.translate);
        animation.setInterpolator(new BounceInterpolator());
        target.startAnimation(animation);
    }
    public void onclick_xml2(View view){
        Animator animator = AnimatorInflater.loadAnimator(mContext, R.animator.propery_anim_1);
        animator.setTarget(target);
        animator.start();
    }

    public void onclick_object(View view){
        ObjectAnimator animator = ObjectAnimator.ofFloat(target, "rotationX", 0f, 360f);
        animator.setDuration(2000);
        animator.setInterpolator(new AccelerateDecelerateInterpolator());
        animator.start();

    }

    public void onclick_value(View view){
        ValueAnimator animator = ValueAnimator.ofFloat(0f, 1f);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                Float value = (Float) animation.getAnimatedValue();
                target.animate().alpha(value);
            }
        });
        animator.setDuration(2000);
        animator.setInterpolator(new AccelerateDecelerateInterpolator());
        animator.start();

    }

    public void onclick_propertyvalues(View view){
        PropertyValuesHolder propertyValuesHolder1 = PropertyValuesHolder.ofFloat("rotationX",0f,180f);
        PropertyValuesHolder propertyValuesHolder2 = PropertyValuesHolder.ofObject("BackgroundColor",new ArgbEvaluator(), Color.BLUE,Color.RED);
        ObjectAnimator objectAnimator = ObjectAnimator.ofPropertyValuesHolder(target, propertyValuesHolder1, propertyValuesHolder2);
        objectAnimator.setInterpolator(new AccelerateDecelerateInterpolator());
        objectAnimator.setDuration(3000);
        objectAnimator.start();


    }


    public void onclick_keyframe(View view){
        Keyframe keyframe1 = Keyframe.ofFloat(0f,0f);
        Keyframe keyframe2 = Keyframe.ofFloat(0.3f,90f);
        Keyframe keyframe3 = Keyframe.ofFloat(0.5f,180f);
        Keyframe keyframe4 = Keyframe.ofFloat(1f,360f);

        PropertyValuesHolder propertyValuesHolder = PropertyValuesHolder.ofKeyframe("rotationX", keyframe1, keyframe2, keyframe3, keyframe4);
        ObjectAnimator objectAnimator = ObjectAnimator.ofPropertyValuesHolder(target, propertyValuesHolder);
        objectAnimator.setInterpolator(new AccelerateDecelerateInterpolator());
        objectAnimator.setDuration(3000);
        objectAnimator.start();

    }


    public void onclick_custom_typeevaluator(View view){

        ValueAnimator valueAnimator = new ValueAnimator();
        valueAnimator.setObjectValues(new PointF(0, 0));
        valueAnimator.setDuration(4000);
        valueAnimator.setEvaluator(new PointFEvaluator());
        valueAnimator.setInterpolator(new AccelerateDecelerateInterpolator());
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                PointF pointF = (PointF) animation.getAnimatedValue();
                Log.e("typeevaluator","===>"+pointF.x +"--" + pointF.y);
                target.setX(pointF.x);
                target.setY(pointF.y);
            }
        });
        valueAnimator.start();
    }


    public void onclick_custom_typeevaluator2(View view){
        ObjectAnimator animator = ObjectAnimator.ofObject(tv,"Char",new CharEvaluator(),new Character('A'),new Character('Z'));
        animator.setDuration(5000);
        animator.setInterpolator(new AccelerateDecelerateInterpolator());
        animator.start();
    }


    public void onclick_set(View view){
        ObjectAnimator animator1 = ObjectAnimator.ofFloat(target,"rotationX", 0f, 360f);
        ObjectAnimator animator2 = ObjectAnimator.ofFloat(target, "translationY", 0f, 500f);

        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.setInterpolator(new AccelerateDecelerateInterpolator());
        animatorSet.setDuration(5000);
        animatorSet.playTogether(animator1,animator2);
        animatorSet.start();
    }

    /**
     * pointF
     */
    public class PointFEvaluator implements TypeEvaluator<PointF> {

        @Override
        public PointF evaluate(float fraction, PointF startValue, PointF endValue) {

            PointF pointF = new PointF();
            pointF.x = 100f*(fraction*4);//初始速度*(执行的百分比*4)
            pointF.y = 0.5f*150f*(fraction*4)*(fraction*4);
            return pointF;
        }
    }
    /**
     * 字符   类型评估
     */
    public class CharEvaluator implements TypeEvaluator<Character> {
        public Character evaluate(float fraction, Character startValue, Character endValue) {
            int startInt  = (int)startValue;
            int endInt = (int)endValue;
            int curInt = (int)(startInt + fraction *(endInt - startInt));
            char result = (char)curInt;
            return result;
        }
    }

}
