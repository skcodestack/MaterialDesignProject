package github.skcodestack.materialdesignproject.paint.filter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BlurMaskFilter;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.EmbossMaskFilter;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import github.skcodestack.materialdesignproject.R;

/**
 * Email  1562363326@qq.com
 * Github https://github.com/skcodestack
 * Created by sk on 2017/12/26
 * Version  1.0
 * Description:
 */

public class FilterView extends View {

    private Paint mPaint;
    private Bitmap bitmap;

    public FilterView(Context context) {
        this(context,null);
    }

    public FilterView(Context context, AttributeSet attrs) {
        this(context, attrs,-1);
    }

    public FilterView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mPaint = new Paint();
        //关闭硬件加速
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB){
            setLayerType(View.LAYER_TYPE_SOFTWARE, mPaint);
        }
        bitmap = ((BitmapDrawable)getContext().getResources().getDrawable(R.drawable.meinv)).getBitmap();
    }



    enum MaskFilterMode{
        NONE,
        BlurMaskFilter,
        EmbossMaskFilter
    };
    MaskFilterMode mode = MaskFilterMode.BlurMaskFilter;
    private float  radius = 10;
    BlurMaskFilter.Blur currentStyle =  BlurMaskFilter.Blur.NORMAL;
    //
    private float directionX = 0;
    private float directionY = 0;
    private float directionZ = 0;
    private float ambient = 0;
    private float specular = 0;

    @Override
    protected void onDraw(Canvas canvas) {

        /**
         *  radius阴影的模糊半径 其值越大表示图形绘制出来越模糊，其值越小图形越清晰
         *  style 阴影按细节来分主要分为内阴影和外阴影，内阴影指的是阴影从图形轮廓向内侧扩张，外阴影指的是阴影从图形轮廓向外侧扩张
         *
         *  BlurMaskFilter.Blur.NORMAL 同时绘制图形本身内容+内阴影+外阴影
         *  BlurMaskFilter.Blur.INNER  绘制图形内容本身+内阴影,不绘制外阴影。
         *  BlurMaskFilter.Blur.OUTER  不绘制图形内容以及内阴影，只绘制外阴影
         *  BlurMaskFilter.Blur.SOLID  只绘制外阴影和图形内容本身，不绘制内阴影
         */
//        BlurMaskFilter blurMaskFilter = new BlurMaskFilter(radius, currentStyle);

//        float[] direction = new float[]{directionX,directionY,directionZ};
        /**
         * direction 光线的方向,xyz
         * ambient 环境光因子，float类型，取值是0到1，值越接近于0，环境光越暗，值越接近于1，环境光越亮
         * specular 镜面反射因子，float类型，取值也是0到1 , 值越接近于0，镜面反射越强，被光照照射到的地方更容易出现很白很亮的状态，即高光效果
         * blurRadius 模糊半径,值越大，模糊效果越明显
         *
         */
//        EmbossMaskFilter embossMaskFilter = new EmbossMaskFilter(direction,ambient,specular,radius);


        mPaint.reset();
        Log.e("xxx","---->"+toString());
        switch (mode){
            case NONE:
                break;
            case BlurMaskFilter:
                BlurMaskFilter blurMaskFilter = new BlurMaskFilter(radius, currentStyle);
                mPaint.setMaskFilter(blurMaskFilter);
                break;
            case EmbossMaskFilter:
                float[] direction = new float[]{directionX,directionY,directionZ};
                EmbossMaskFilter embossMaskFilter = new EmbossMaskFilter(direction,ambient,specular,radius);
                mPaint.setMaskFilter(embossMaskFilter);
                break;
        }



       // canvas.drawBitmap(bitmap, 50,100,mPaint);
        mPaint.setColor(Color.GRAY);
        mPaint.setStyle(Paint.Style.FILL);
        canvas.drawRect(100,100,500,500,mPaint);
    }


    public void setMode(MaskFilterMode mode) {
        this.mode = mode;
    }

    public void setRadius(float radius) {
        if(radius < 1){
            radius = 1;
        }
        this.radius = radius;
    }

    public void setCurrentStyle(BlurMaskFilter.Blur currentStyle) {
        this.currentStyle = currentStyle;
    }

    public void setDirectionX(float directionX) {
        this.directionX = directionX;
    }

    public void setDirectionY(float directionY) {
        this.directionY = directionY;
    }

    public void setDirectionZ(float directionZ) {
        this.directionZ = directionZ;
    }

    public void setAmbient(float ambient) {
        this.ambient = ambient;
    }

    public void setSpecular(float specular) {
        this.specular = specular;
    }



    public void invokeInvalidate(){
        postInvalidate();
    }


    @Override
    public String toString() {
        return "FilterView{" +
                "specular=" + specular +
                ", ambient=" + ambient +
                ", directionZ=" + directionZ +
                ", directionY=" + directionY +
                ", directionX=" + directionX +
                ", currentStyle=" + currentStyle +
                ", radius=" + radius +
                ", mode=" + mode +
                '}';
    }
}
