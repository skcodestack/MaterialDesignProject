package github.skcodestack.materialdesignproject;

import android.animation.Animator;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.animation.AccelerateInterpolator;
import android.widget.ImageView;

public class MaterialDesignAnimationActivity extends AppCompatActivity {

    private ImageView target;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_material_design_animation);

        target = (ImageView) findViewById(R.id.target);
    }

    public void btn_reveal(View view){

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {

            int x = target.getWidth() ;
            int y = target.getHeight();
            double z = Math.pow (Math.pow(x, 2) + Math.pow(y, 2),0.5);

            Animator circularReveal = ViewAnimationUtils.createCircularReveal(target, target.getWidth() / 2, target.getHeight() / 2, 0, (float) (z/2));
            circularReveal.setDuration(2000);
            circularReveal.setInterpolator(new AccelerateInterpolator());
            circularReveal.addListener(new Animator.AnimatorListener() {
                   @Override
                   public void onAnimationStart(Animator animation) {
                       target.setVisibility(View.VISIBLE);
                   }

                   @Override
                   public void onAnimationEnd(Animator animation) {

                   }

                   @Override
                   public void onAnimationCancel(Animator animation) {

                   }

                   @Override
                   public void onAnimationRepeat(Animator animation) {

                   }
               });
            circularReveal.start();

        }


    }

    public void btn_reveal2(View view){

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {

            int x = target.getWidth() ;
            int y = target.getHeight();
            double z = Math.pow (Math.pow(x, 2) + Math.pow(y, 2),0.5);

            Animator circularReveal = ViewAnimationUtils.createCircularReveal(target,0,0, 0, (float) z);
            circularReveal.setDuration(2000);
            circularReveal.setInterpolator(new AccelerateInterpolator());
            circularReveal.addListener(new Animator.AnimatorListener() {
                @Override
                public void onAnimationStart(Animator animation) {
                    target.setVisibility(View.VISIBLE);
                }

                @Override
                public void onAnimationEnd(Animator animation) {

                }

                @Override
                public void onAnimationCancel(Animator animation) {

                }

                @Override
                public void onAnimationRepeat(Animator animation) {

                }
            });
            circularReveal.start();

        }


    }

}
