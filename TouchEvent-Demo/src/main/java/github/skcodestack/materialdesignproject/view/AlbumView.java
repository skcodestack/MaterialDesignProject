package github.skcodestack.materialdesignproject.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

/**
 * Email  1562363326@qq.com
 * Github https://github.com/skcodestack
 * Created by sk on 2017/12/23
 * Version  1.0
 * Description:
 */

public class AlbumView extends ViewGroup {

    public AlbumView(Context context) {
        super(context);
    }

    public AlbumView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public AlbumView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {

        int width = getWidth();
        int cellWidth  = width / 3;

        int count = getChildCount();

        for (int i = 0; i < count; i++) {
            View child =
                    getChildAt(i);
            if(i == 0){
                child.layout(0,0, 2*cellWidth,2*cellWidth);
            }else if(i == 1 || i == 2){
                child.layout(2 * cellWidth,(i-1) * cellWidth,3 * cellWidth,i*cellWidth);
            }else {
                child.layout((i%3) * cellWidth, (i/3+1) * cellWidth,(i%3 + 1) * cellWidth,(i/3+2) * cellWidth);
            }
        }

    }


    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return super.onInterceptTouchEvent(ev);
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return super.onTouchEvent(event);
    }
}
