package github.skcodestack.materialdesignproject.test.view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.FrameLayout;

/**
 * Email  1562363326@qq.com
 * Github https://github.com/skcodestack
 * Created by sk on 2017/12/24
 * Version  1.0
 * Description:
 */

public class NoInterceptonViewGroup2 extends FrameLayout {

    private static final String TAG = "NoInterceptonViewGroup2";

    public NoInterceptonViewGroup2(Context context) {
        super(context);
    }

    public NoInterceptonViewGroup2(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public NoInterceptonViewGroup2(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        int action = ev.getAction();
        switch (action){
            case MotionEvent.ACTION_DOWN:
                Log.e(TAG,TAG+"----onInterceptTouchEvent===>ACTION_DOWN");
                break;
            case MotionEvent.ACTION_MOVE:
                Log.e(TAG,TAG+"----onInterceptTouchEvent===>ACTION_MOVE");
                break;
            case MotionEvent.ACTION_UP:
                Log.e(TAG,TAG+"----onInterceptTouchEvent===>ACTION_UP");
                break;
            case MotionEvent.ACTION_CANCEL:
                Log.e(TAG,TAG+"----onInterceptTouchEvent===>ACTION_CANCEL");
                break;
        }

        return super.onInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int action = event.getAction();
        switch (action){
            case MotionEvent.ACTION_DOWN:
                Log.e(TAG,TAG+"----onTouchEvent===>ACTION_DOWN");
                break;
            case MotionEvent.ACTION_MOVE:
                Log.e(TAG,TAG+"----onTouchEvent===>ACTION_MOVE");
                break;
            case MotionEvent.ACTION_UP:
                Log.e(TAG,TAG+"----onTouchEvent===>ACTION_UP");
                break;
            case MotionEvent.ACTION_CANCEL:
                Log.e(TAG,TAG+"----onTouchEvent===>ACTION_CANCEL");
                break;
        }



        return super.onTouchEvent(event);
    }
}
