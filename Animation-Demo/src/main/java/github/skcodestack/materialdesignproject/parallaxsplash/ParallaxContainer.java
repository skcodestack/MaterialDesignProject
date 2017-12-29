package github.skcodestack.materialdesignproject.parallaxsplash;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.nineoldandroids.view.ViewHelper;

import java.util.ArrayList;
import java.util.List;

import github.skcodestack.materialdesignproject.R;

/**
 * Email  1562363326@qq.com
 * Github https://github.com/skcodestack
 * Created by sk on 2017/12/21
 * Version  1.0
 * Description:
 */

public class ParallaxContainer extends FrameLayout implements ViewPager.OnPageChangeListener {

    private String TAG = "ParallaxContainer";

    private ParallaxPagerAdapter adapter;
    private ImageView iv_man;
    public ParallaxContainer(Context context) {
        super(context);
    }

    public ParallaxContainer(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ParallaxContainer(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    List<ParallaxFragment> fragments;

    public void setUp(int... childIds) {

        fragments = new ArrayList<ParallaxFragment>();

        for (int i = 0; i < childIds.length; i++) {
            int id = childIds[i];
            ParallaxFragment f = new ParallaxFragment();
            Bundle bundle = new Bundle();
            //页面索引
            bundle.putInt("index", i);
            //Fragment中需要加载的布局文件id
            bundle.putInt("layoutId", childIds[i]);
            f.setArguments(bundle);
            fragments.add(f);
        }


        //实例化适配器
        FragmentActivity activity = (FragmentActivity)getContext();

        adapter = new ParallaxPagerAdapter(activity.getSupportFragmentManager(), fragments);


        //实例化ViewPager
        ViewPager vp = new ViewPager(getContext());
        vp.setId(R.id.parallax_pager);
        vp.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.MATCH_PARENT));

        //绑定
        vp.setAdapter(adapter);
        addView(vp,0);

        vp.setOnPageChangeListener(this);

    }
    int containerWidth;
    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        this.containerWidth = getWidth();
        //在翻页的过程中，不断根据视图的标签中对应的动画参数，改变视图的位置或者透明度
        //获取到进入的页面
        ParallaxFragment inFragment = null;
        try {
            inFragment = fragments.get(position - 1);
        } catch (Exception e) {}

        //获取到退出的页面
        ParallaxFragment outFragment = null;
        try {
            outFragment = fragments.get(position);
        } catch (Exception e) {}

        if (inFragment != null) {
            Log.e(TAG,"===>inFragment"  );
            //获取Fragment上所有的视图，实现动画效果
            List<View> inViews = inFragment.getParallaxViews();
            if (inViews != null) {
                Log.e(TAG,"===>inFragment --- inViews"  );
                for (View view : inViews) {
                    Log.e(TAG,"===>inFragment --- inViews --- view"  );
                    //获取标签，从标签上获取所有的动画参数
                    ParallaxViewTag tag = (ParallaxViewTag) view.getTag(R.id.parallax_view_tag);
                    if (tag == null) {
                        continue;
                    }
                    //translationY改变view的偏移位置，translationY=100，代表view在其原始位置向下移动100
                    //仔细观察进入的fragment中view从远处过来，不断向下移动，最终停在原始位置
                    ViewHelper.setTranslationY(view, (containerWidth - positionOffsetPixels) * tag.yIn);
                    ViewHelper.setTranslationX(view, (containerWidth - positionOffsetPixels) * tag.xIn);
                }
            }
        }

        if(outFragment != null){
            Log.e(TAG,"===>outFragment"  );
            List<View> outViews = outFragment.getParallaxViews();
            if (outViews != null) {
                for (View view : outViews) {
                    ParallaxViewTag tag = (ParallaxViewTag) view.getTag(R.id.parallax_view_tag);
                    if (tag == null) {
                        continue;
                    }
                    //仔细观察退出的fragment中view从原始位置开始向上移动，translationY应为负数
                    ViewHelper.setTranslationY(view, 0 - positionOffsetPixels * tag.yOut);
                    ViewHelper.setTranslationX(view, 0 - positionOffsetPixels * tag.xOut);
                }
            }
        }
    }

    @Override
    public void onPageSelected(int position) {
        if(iv_man == null){
            return;
        }
        if (position == adapter.getCount() - 1) {
            iv_man.setVisibility(INVISIBLE);
        }else{
            iv_man.setVisibility(VISIBLE);
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {
        if(iv_man == null){
            return;
        }
        AnimationDrawable animation = (AnimationDrawable) iv_man.getBackground();
        switch (state) {
            case ViewPager.SCROLL_STATE_DRAGGING:
                animation.start();
                break;

            case ViewPager.SCROLL_STATE_IDLE:
                animation.stop();
                break;

            default:
                break;
        }
    }

    public void setIv_man(ImageView iv_man) {
        this.iv_man = iv_man;
    }

}
