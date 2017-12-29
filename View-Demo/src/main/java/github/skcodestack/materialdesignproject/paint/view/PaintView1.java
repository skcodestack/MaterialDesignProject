package github.skcodestack.materialdesignproject.paint.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

/**
 * Email  1562363326@qq.com
 * Github https://github.com/skcodestack
 * Created by sk on 2017/12/25
 * Version  1.0
 * Description:
 */

public class PaintView1 extends View {

    private Paint mPaint;

    public PaintView1(Context context) {
        this(context,null);
    }

    public PaintView1(Context context, AttributeSet attrs) {
        this(context, attrs,-1);
    }

    public PaintView1(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    void init(){
        mPaint = new Paint();
        mPaint.setColor(Color.BLUE);
        //样式
        mPaint.setStyle(Paint.Style.STROKE);//描边
//        mPaint.setStyle(Paint.Style.FILL);//填充
//        mPaint.setStyle(Paint.Style.FILL_AND_STROKE);//描边加填充
        //宽度
        mPaint.setStrokeWidth(10);
        //线帽
        mPaint.setStrokeCap(Paint.Cap.ROUND);//圆形
//        mPaint.setStrokeCap(Paint.Cap.SQUARE);//方形
//        mPaint.setStrokeCap(Paint.Cap.BUTT); //没有
        //连接处样式
        mPaint.setStrokeJoin(Paint.Join.ROUND);//圆弧
//        mPaint.setStrokeJoin(Paint.Join.MITER);//锐角
//        mPaint.setStrokeJoin(Paint.Join.BEVEL);//直线
        //抗锯齿
        mPaint.setAntiAlias(true);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        //画圆
        canvas.drawCircle(100,100,50,mPaint);

        //线
        canvas.drawLine(0,0,200,200,mPaint);

        //方形
        canvas.drawRect(200,20,300,70,mPaint);
    }
}
