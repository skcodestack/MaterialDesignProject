package github.skcodestack.materialdesignproject.transfer;

import android.app.Activity;
import android.content.Intent;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.transition.Explode;
import android.transition.Fade;
import android.transition.Slide;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;

import github.skcodestack.materialdesignproject.R;

public class CommonTransferActivity extends AppCompatActivity {
    Activity mActivity;
    private ImageView target;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);
        setContentView(R.layout.activity_common_transfer);

        mActivity = this;
        target = (ImageView) findViewById(R.id.target);
        target.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ActivityOptionsCompat optionsCompat = ActivityOptionsCompat.makeSceneTransitionAnimation(mActivity, target, "meinv3");
                Intent intent = new Intent(mActivity, TransferDetailActivity.class);
                mActivity.startActivity(intent,optionsCompat.toBundle());
            }
        });
    }

    public  void btn_slide(View view){

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            Slide slide = new Slide();
            slide.setDuration(1000);
            getWindow().setExitTransition(slide);//出去的动画
            getWindow().setEnterTransition(slide);//进来的动画
            ActivityOptionsCompat optionsCompat = ActivityOptionsCompat.makeSceneTransitionAnimation(mActivity);
            Intent intent = new Intent(mActivity, TransferDetailActivity.class);
            mActivity.startActivity(intent,optionsCompat.toBundle());

        }

    }
    public  void btn_explode(View view){

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            Explode explode = new Explode();
            explode.setDuration(1000);
            getWindow().setExitTransition(explode);//出去的动画
            getWindow().setEnterTransition(explode);//进来的动画
            ActivityOptionsCompat optionsCompat = ActivityOptionsCompat.makeSceneTransitionAnimation(mActivity);
            Intent intent = new Intent(mActivity, TransferDetailActivity.class);
            mActivity.startActivity(intent,optionsCompat.toBundle());

        }

    }

    public  void btn_fade(View view){

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            Fade fade = new Fade();
            fade.setDuration(1000);
            getWindow().setExitTransition(fade);//出去的动画
            getWindow().setEnterTransition(fade);//进来的动画
            ActivityOptionsCompat optionsCompat = ActivityOptionsCompat.makeSceneTransitionAnimation(mActivity);
            Intent intent = new Intent(mActivity, TransferDetailActivity.class);
            mActivity.startActivity(intent,optionsCompat.toBundle());

        }

    }
}
