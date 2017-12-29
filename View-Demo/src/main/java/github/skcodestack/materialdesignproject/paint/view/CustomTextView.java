package github.skcodestack.materialdesignproject.paint.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
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

public class CustomTextView extends View {

    private Paint mPaint;
    private String text = "adcgf";

    public CustomTextView(Context context) {
        this(context,null);
    }

    public CustomTextView(Context context, AttributeSet attrs) {
        this(context, attrs,-1);
    }

    public CustomTextView(Context context, AttributeSet attrs, int defStyleAttr) {
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
        mPaint.setStrokeWidth(1);
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

        //字符行间距
        float fontSpacing = mPaint.getFontSpacing();

        //字符间距
//        float letterSpacing = mPaint.getLetterSpacing();
//        mPaint.setLetterSpacing(10);

        //设置文本删除线
//        mPaint.setStrikeThruText(true);

        //是否设置下划线
//        mPaint.setUnderlineText(true);

        //设置文本大小
        mPaint.setTextSize(200);

        //设置字体类型  Typeface.BOLD_ITALIC
//        Typeface typeface = Typeface.createFromAsset(getContext().getAssets(), "/simff.ttf");
        mPaint.setTypeface(Typeface.SERIF);

        //设置文字倾斜 默认0，官方推荐的-0.25f是斜体
//        mPaint.setTextSkewX(-0.25f);

        //设置文本对齐方式
        mPaint.setTextAlign(Paint.Align.CENTER);


        //计算指定长度的字符串(字符长度、字符个数、显示的时候真实的长度)
//        int breakText = mPaint.breakText(text, true, maxWidth, measuredWidth);

        //获取文本的矩形区域
//        Rect rect = new Rect();
//        mPaint.getTextBounds(text,0,text.length()-1,rect);

        //获取文本的宽度(相比breakText，它只是粗略的结果)
        float measureText  = mPaint.measureText(text);


        //获取文本的宽度,和上面类似，但是是比较精准的。
        //measuredWidth----每个字符的宽度，textWidths---字符的个数
//        float[] measuredWidth = new float[text.length()];
//        int textWidths = mPaint.getTextWidths(text, measuredWidth);

        //=====================基线的问题=====================
//        Paint.FontMetrics fontMetrics = mPaint.getFontMetrics();
//        float top = fontMetrics.top;
//        float ascent = fontMetrics.ascent;
//        float descent = fontMetrics.descent;
//        float bottom = fontMetrics.bottom;
        //指定左上角的顶点坐标 绘制文本
//        float baseLine = Y - top;

        //指定中间位置，绘制文本
//        float baseLine = centerY + (bottom - top)/2 - bottom;




        float baseLineX = 0;
        float baseLineY = 300;
//        float top = 100;

        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setTextAlign(Paint.Align.LEFT);

        Paint.FontMetrics fontMetrics = mPaint.getFontMetrics();

//        baseLineY = top - fontMetrics.top;

        canvas.drawLine(baseLineX,baseLineY,700,baseLineY,mPaint);
        canvas.drawText(text, baseLineX, baseLineY, mPaint);
        //字体的绘制

        float  ascentY=fontMetrics.ascent+baseLineY;
        float  descent=fontMetrics.descent+baseLineY;
        float  top=fontMetrics.top+baseLineY;
        float  bottom=fontMetrics.bottom+baseLineY;

        mPaint.setColor(Color.YELLOW);
        canvas.drawLine(baseLineX,ascentY,700,ascentY,mPaint);
        mPaint.setColor(Color.GREEN);
        canvas.drawLine(baseLineX,descent,700,descent,mPaint);
        mPaint.setColor(Color.GRAY);
        canvas.drawLine(baseLineX,top,700,top,mPaint);
        mPaint.setColor(Color.BLACK);
        canvas.drawLine(baseLineX,bottom,700,bottom,mPaint);



        Rect rect = new Rect();
        mPaint.getTextBounds(text,0,text.length(),rect);

        float actual_top=baseLineY+rect.top;
        float actual_right=baseLineX+rect.right;
        float actual_bottom=baseLineY+rect.bottom;
        float actual_left=baseLineX+rect.left;

        mPaint.setColor(Color.RED);
        mPaint.setStyle(Paint.Style.STROKE);
        canvas.drawRect(actual_left,actual_top,actual_right,actual_bottom,mPaint);

    }


    public void setText(String msg){
        text = msg;
        invalidate();
    }
}
