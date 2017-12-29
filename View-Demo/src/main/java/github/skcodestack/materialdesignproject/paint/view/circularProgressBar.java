package github.skcodestack.materialdesignproject.paint.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.view.View;

/**
 * Email  1562363326@qq.com
 * Github https://github.com/skcodestack
 * Created by sk on 2017/12/25
 * Version  1.0
 * Description:
 */

public class circularProgressBar  extends View {


    private int max = 100;
    private int roundColor = Color.RED;
    private int roundProgressColor = Color.BLUE;
    private int textColor = Color.GREEN;
    private float textSize = 55;
    private float roundWidth = 20;
    private int progress = 0;
    private Paint mPaint;


    public circularProgressBar(Context context) {
        this(context,null);
    }

    public circularProgressBar(Context context, AttributeSet attrs) {
        this(context, attrs,-1);
    }

    public circularProgressBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    /**
     * paint 初始化
     */
    private void init(){
        mPaint = new Paint();
        mPaint.setStyle(Paint.Style.FILL);//描边
        mPaint.setStrokeCap(Paint.Cap.ROUND);//圆形
        mPaint.setStrokeJoin(Paint.Join.ROUND);//圆弧
        mPaint.setAntiAlias(true);
    }


    @Override
    protected void onDraw(Canvas canvas) {

        int center = getWidth()/2;//中心坐标点
        float radius =  center-roundWidth/2;//半径
        mPaint.setColor(roundColor);
        mPaint.setStyle(Paint.Style.STROKE);//设置空心(描边)
        mPaint.setStrokeWidth(roundWidth);//圆环的宽度
        mPaint.setAntiAlias(true);
        //加载背景
        canvas.drawCircle(center, center, radius , mPaint);


        mPaint.setColor(textColor);
        mPaint.setStrokeWidth(0);//圆环的宽度
        mPaint.setTextSize(textSize);
        mPaint.setTypeface(Typeface.DEFAULT_BOLD);
        int percent = (int) (progress/(float)max * 100);
        //加载百分比
        if( percent!=0 ) {
            String showText = percent + "%";
            float x = center - mPaint.measureText(showText)/2;
            Paint.FontMetrics fontMetrics = mPaint.getFontMetrics();
            float y = center + (fontMetrics.bottom-fontMetrics.top)/2 - fontMetrics.bottom;
            canvas.drawText(showText,x,y,mPaint);
        }
        //加载进度的圆弧
        RectF oval = new RectF(center-radius, center-radius, center+radius, center+radius);
        mPaint.setColor(roundProgressColor);
        mPaint.setStrokeWidth(roundWidth);
        mPaint.setStyle(Paint.Style.STROKE);
        canvas.drawArc(oval,0,360* percent /100,false,mPaint);

    }

    public synchronized int getProgress(){
        return progress;
    }

    public synchronized void setProgress(int progress){
        if(progress<0){
            throw new IllegalArgumentException("progress不能小于0");
        }
        if(progress>max){
            progress = max;
        }
        if(progress <=max){
            this.progress = progress;
            postInvalidate();
        }
    }
}
