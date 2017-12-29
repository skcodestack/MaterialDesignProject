package github.com.materialdesignproject.view.contain;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import github.com.materialdesignproject.R;

/**
 * Email  1562363326@qq.com
 * Github https://github.com/skcodestack
 * Created by sk on 2017/12/12
 * Version  1.0
 * Description:
 */

public class TwitterOnRefreshHeaderView extends BaseHeader{


    private ImageView ivArrow;

    private ImageView ivSuccess;

    private TextView tvRefresh;

    private ProgressBar progressBar;


    private Animation rotateUp;

    private Animation rotateDown;

    private boolean rotated = false;

    public TwitterOnRefreshHeaderView(Context context) {
        this(context, null);
    }

    public TwitterOnRefreshHeaderView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TwitterOnRefreshHeaderView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        rotateUp = AnimationUtils.loadAnimation(context, R.anim.rotate_up);
        rotateDown = AnimationUtils.loadAnimation(context, R.anim.rotate_down);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();

        tvRefresh = (TextView) findViewById(R.id.tvRefresh);
        ivArrow = (ImageView) findViewById(R.id.ivArrow);
        ivSuccess = (ImageView) findViewById(R.id.ivSuccess);
        progressBar = (ProgressBar) findViewById(R.id.progressbar);
    }

    @Override
    public void onRefresh() {
        ivSuccess.setVisibility(GONE);
        ivArrow.clearAnimation();
        ivArrow.setVisibility(GONE);
        progressBar.setVisibility(VISIBLE);
        tvRefresh.setText("REFRESHING");
    }

    @Override
    public void onPrepare() {
        Log.d("TwitterRefreshHeader", "onPrepare()");
    }

    @Override
    public void onDrag(int y, int offset) {



        if(y > 0){
            ivArrow.setVisibility(VISIBLE);
            progressBar.setVisibility(GONE);
            ivSuccess.setVisibility(GONE);
            if(y > offset){
                tvRefresh.setText("RELEASE TO REFRESH");
                if (!rotated) {
                    ivArrow.clearAnimation();
                    ivArrow.startAnimation(rotateUp);
                    rotated = true;
                }
            }else {
                if (rotated) {
                    ivArrow.clearAnimation();
                    ivArrow.startAnimation(rotateDown);
                    rotated = false;
                }

                tvRefresh.setText("SWIPE TO REFRESH");
            }
        }


    }

    @Override
    public void onRelease() {
        Log.d("TwitterRefreshHeader", "onRelease()");
    }

    @Override
    public void onComplete() {
        rotated = false;
        ivSuccess.setVisibility(VISIBLE);
        ivArrow.clearAnimation();
        ivArrow.setVisibility(GONE);
        progressBar.setVisibility(GONE);
        tvRefresh.setText("COMPLETE");
    }

    @Override
    public void onReset() {
        rotated = false;
        ivSuccess.setVisibility(GONE);
        ivArrow.clearAnimation();
        ivArrow.setVisibility(GONE);
        progressBar.setVisibility(GONE);
    }


    @Override
    public int getDragMaxOffset(View rootView, View target, int targetHeight) {
        return targetHeight*2;
    }

    @Override
    public int getDragTriggerOffset(View rootView, View target, int targetHeight) {
        RelativeLayout relativeLayout = (RelativeLayout) target.findViewById(R.id.rl_refresh_height);
        return relativeLayout.getHeight();
    }

    @Override
    public int getRefreshOrLoadMoreHeight(View rootView, View target, int targetHeight) {
        RelativeLayout relativeLayout = (RelativeLayout) target.findViewById(R.id.rl_refresh_height);
        return relativeLayout.getHeight();
    }
}