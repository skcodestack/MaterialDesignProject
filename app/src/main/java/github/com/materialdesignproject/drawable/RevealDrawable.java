package github.com.materialdesignproject.drawable;

import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.PixelFormat;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.view.Gravity;

/**
 * Email  1562363326@qq.com
 * Github https://github.com/skcodestack
 * Created by sk on 2017/10/24
 * Version  1.0
 * Description:
 */

public class RevealDrawable extends Drawable {


    Drawable mUnSelectedDrawable;
    Drawable mSelectedDrawable;
    int mOrientation = 0;
    public RevealDrawable(Drawable unSelectedDrawable,Drawable selectedDrawable,int orientation) {
        this.mUnSelectedDrawable = unSelectedDrawable;
        this.mSelectedDrawable = selectedDrawable;
        this.mOrientation = orientation;

    }

    @Override
    public void draw(Canvas canvas) {

//        mUnSelectedDrawable.getIntrinsicWidth();
        Rect bounds = getBounds();
        int width = bounds.width();
        int height = bounds.height();

        Rect rect1 = new Rect();
        Rect rect2 = new Rect();
        if(mOrientation == 0){
            //横向
            int w = width/2;
            int _w = width - w;
            int h = height;
            Gravity.apply(Gravity.LEFT,w,h,bounds,rect1);
            Gravity.apply(Gravity.LEFT,_w,h,bounds,rect2);
        }else {
            //横向
            int w = width;
            int h = height/2;
            int _h = height - h;
            Gravity.apply(Gravity.LEFT,w,h,bounds,rect1);
            Gravity.apply(Gravity.LEFT,w,_h,bounds,rect2);
        }



        canvas.save();
        canvas.clipRect(rect1);
        mUnSelectedDrawable.draw(canvas);
        canvas.restore();

        canvas.save();
        canvas.clipRect(rect2);
        mSelectedDrawable.draw(canvas);

        canvas.restore();


    }

    @Override
    protected void onBoundsChange(Rect bounds) {
//        super.onBoundsChange(bounds);
        mUnSelectedDrawable.setBounds(bounds);
        mSelectedDrawable.setBounds(bounds);
    }

    @Override
    protected boolean onLevelChange(int level) {



        return super.onLevelChange(level);
    }

    @Override
    public void setAlpha(int alpha) {

    }

    @Override
    public void setColorFilter(ColorFilter colorFilter) {

    }

    @Override
    public int getOpacity() {
        return PixelFormat.TRANSLUCENT;
    }
}
