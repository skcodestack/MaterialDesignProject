package github.skcodestack.materialdesignproject.path.view;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.LinearInterpolator;

/**
 * Email  1562363326@qq.com
 * Github https://github.com/skcodestack
 * Created by sk on 2017/12/27
 * Version  1.0
 * Description: 水波纹
 */

public class WaveView extends View {

    private Paint mPaint;
    private Path mPath;

    public WaveView(Context context) {
        this(context,null);
    }

    public WaveView(Context context, AttributeSet attrs) {
        this(context, attrs,-1);
    }

    public WaveView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {

        mPaint = new Paint();
        mPaint.setColor(Color.BLUE);
        mPaint.setStrokeWidth(10);
        mPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        mPaint.setAntiAlias(true);
        mPaint.setDither(true);

        mPath = new Path();

    }
    //初始化高度
    private  int initHeight = 100;
    //一个波浪的长度
    private int waveLength = 600;
    //波峰高度
    private int peak = 150;

    private  int dx = 0;

    int dY = 0;
    @Override
    protected void onDraw(Canvas canvas) {

        mPath.reset();

        //int currentY = (int) ((getWidth() - initHeight) * (1 - progress));

        int currentY =  0;
        if(dY < (getWidth() - initHeight) + 300){
            dY+=3;
        }
        currentY = (getWidth() - initHeight) - dY;

        int half = waveLength / 2;
        mPath.moveTo(-waveLength + dx, currentY);
        for (int i = -waveLength; i < getWidth() + waveLength; i += waveLength) {
            //相对绘制二阶贝塞尔曲线(相对于自己的起始点--也即是上一个曲线的终点  的距离dx1)
            mPath.rQuadTo(half/2,peak,half,0);
            mPath.rQuadTo(half/2,-peak,half,0);
        }
        mPath.lineTo(getWidth(),getHeight());
        mPath.lineTo(0,getHeight());
        mPath.close();

        canvas.drawPath(mPath,mPaint);
    }


    float progress = 0;

    public synchronized float getProgress(){
        return progress;
    }

    public synchronized void setProgress(float progress){
        if(progress<0){
            throw new IllegalArgumentException("progress不能小于0");
        }
        if(progress>1){
            progress = 1;
        }
        if(progress <=1){
            this.progress = progress;
            postInvalidate();
        }
    }


    public void startAnimation(){
        ValueAnimator animator = ValueAnimator.ofInt(0,waveLength);
        animator.setDuration(1000);
        animator.setInterpolator(new LinearInterpolator());
        //无限循环
        animator.setRepeatCount(ValueAnimator.INFINITE);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {

            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                dx = (int) animation.getAnimatedValue();
                postInvalidate();
            }
        });
        animator.start();
    }
}
