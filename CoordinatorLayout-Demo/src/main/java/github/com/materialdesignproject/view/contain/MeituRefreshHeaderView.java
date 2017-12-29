package github.com.materialdesignproject.view.contain;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.AnimationDrawable;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import github.com.materialdesignproject.R;

/**
 * Email  1562363326@qq.com
 * Github https://github.com/skcodestack
 * Created by sk on 2017/12/13
 * Version  1.0
 * Description:
 */

public class MeituRefreshHeaderView extends BaseHeader {

    private AnimationDrawable animationPull;
    private AnimationDrawable animationPullFan;
    private AnimationDrawable animationRefresh;
    private ImageView header_img;
    private int[] pullAnimSrcs = new int[]{R.drawable.mt_pull, R.drawable.mt_pull01, R.drawable.mt_pull02, R.drawable.mt_pull03, R.drawable.mt_pull04, R.drawable.mt_pull05};
    private int[] refreshAnimSrcs = new int[]{R.drawable.mt_refreshing01, R.drawable.mt_refreshing02, R.drawable.mt_refreshing03, R.drawable.mt_refreshing04, R.drawable.mt_refreshing05, R.drawable.mt_refreshing06};



    public MeituRefreshHeaderView(Context context) {
        super(context);
    }

    public MeituRefreshHeaderView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MeituRefreshHeaderView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);


        animationPull = new AnimationDrawable();
        animationPullFan = new AnimationDrawable();
        animationRefresh = new AnimationDrawable();
        for (int i = 1; i < this.pullAnimSrcs.length; i++) {
            animationPull.addFrame(ContextCompat.getDrawable(context, this.pullAnimSrcs[i]), 100);
            animationPull.setOneShot(true);
        }
        for (int i = this.pullAnimSrcs.length - 1; i >= 0; i--) {
            animationPullFan.addFrame(ContextCompat.getDrawable(context, this.pullAnimSrcs[i]), 100);
            animationPullFan.setOneShot(true);
        }
        for (int src : this.refreshAnimSrcs) {
            animationRefresh.addFrame(ContextCompat.getDrawable(context, src), 150);
            animationRefresh.setOneShot(false);
        }
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        header_img = (ImageView) findViewById(R.id.meituan_header_img);
        if (pullAnimSrcs != null && pullAnimSrcs.length > 0)
            header_img.setImageResource(pullAnimSrcs[0]);
    }

    @Override
    public void onPrepare() {
//        ivSpeed.clearAnimation();
//        ivSpeed.setVisibility(GONE);
    }


    @Override
    public void onDrag(int dy, int offset) {
        super.onDrag(dy, offset);
//        int h = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 45, Resources.getSystem().getDisplayMetrics());
//        float w = h * Math.abs(dy) / offset;
        if(dy > 0){
            if(dy > offset){
                header_img.setImageDrawable(animationRefresh);
                animationRefresh.start();
            }else {
                float percent = Math.abs(dy) * 1.0f / offset;
                if(percent > 1){
                    percent = 1;
                }
                int index = (int) ((pullAnimSrcs.length-1) * percent);
                header_img.setImageResource(pullAnimSrcs[index]);


            }
        }

    }
}
