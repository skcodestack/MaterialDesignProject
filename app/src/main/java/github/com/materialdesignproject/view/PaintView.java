package github.com.materialdesignproject.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.ColorMatrix;
import android.graphics.ComposeShader;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.graphics.PorterDuff;
import android.graphics.RadialGradient;
import android.graphics.Shader;
import android.graphics.SweepGradient;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.ArcShape;
import android.graphics.drawable.shapes.OvalShape;
import android.util.AttributeSet;
import android.view.View;

import github.com.materialdesignproject.R;

/**
 * Email  1562363326@qq.com
 * Github https://github.com/skcodestack
 * Created by sk on 2017/10/20
 * Version  1.0
 * Description:
 */

public class PaintView extends View {


    private Paint paint;

    public PaintView(Context context) {
        super(context);

        init();
    }


    public PaintView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public PaintView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public void init(){

        paint = new Paint();
        paint.setColor(Color.RED);
        paint.setAntiAlias(true);
//        paint.setAlpha(0);
//        paint.setStrokeWidth(2);
//        Paint.Style.FILL_AND_STROKE
//        Paint.Style.STROKE
        paint.setStyle(Paint.Style.FILL);

        //线帽
//        Paint.Cap.BUTT  没有
//        Paint.Cap.ROUND 圆
//        Paint.Cap.SQUARE 方

        paint.setStrokeCap(Paint.Cap.ROUND);

        //线条交汇处
//        Paint.Join.MITER  锐角
//        Paint.Join.ROUND  圆角
//        Paint.Join.BEVEL  直线
        paint.setStrokeJoin(Paint.Join.MITER);


//        paint.setFontSpacing()
//        paint.setLetterSpacing()
//        paint.setDither();


//        paint.breakText()
//        paint.measureText()








    }

    String  text = "dfdgdfdfd";

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        BitmapDrawable drawable = (BitmapDrawable) getResources().getDrawable(R.drawable.ic_launcher);
        Bitmap bitmap = drawable.getBitmap();
//        Shader.TileMode.MIRROR  镜像翻转
//        Shader.TileMode.CLAMP   拉伸 最后一个像素
//        Shader.TileMode.REPEAT  重复填充
//        图像渲染
        BitmapShader bitmapShader = new BitmapShader(bitmap, Shader.TileMode.MIRROR,Shader.TileMode.MIRROR);
        Matrix matrix = new Matrix();
        matrix.setScale(10,10);
        bitmapShader.setLocalMatrix(matrix);
        paint.setShader(bitmapShader);

        ShapeDrawable shapeDrawable = new ShapeDrawable(new OvalShape());
        shapeDrawable.getPaint().setShader(bitmapShader);
        shapeDrawable.setBounds(0,0,100,100);
        shapeDrawable.draw(canvas);


        canvas.drawCircle(bitmap.getWidth()/2,bitmap.getWidth()/2,bitmap.getWidth()/2,paint);

        //线性渲染
        LinearGradient linearGradient = new LinearGradient(0,100,100,100,new int[]{Color.RED,Color.BLUE,Color.GRAY}, new float[]{0f,0.5f,1f},Shader.TileMode.CLAMP);


//        环形渲染
        RadialGradient radialGradient = new RadialGradient(100,100,50,new int[]{Color.RED,Color.BLUE,Color.GRAY},null,Shader.TileMode.CLAMP);


//        梯度渲染
        SweepGradient sweepGradient = new SweepGradient(100,100,new int[]{Color.RED,Color.BLUE,Color.GRAY},null);


//        组合渲染
        ComposeShader composeShader = new ComposeShader(linearGradient,radialGradient, PorterDuff.Mode.SRC_IN);



        int text_top = 200;

        paint.setTextSize(200);
        paint.setTextAlign(Paint.Align.LEFT);

        Paint.FontMetrics fontMetrics = paint.getFontMetrics();
        float top = fontMetrics.top;
        float ascent = fontMetrics.ascent;
        float descent = fontMetrics.descent;
        float bottom = fontMetrics.bottom;

        //text_top + Math.abs(top)
        float baseLineY = text_top - top;






//        canvas.drawLine();

        canvas.drawText(text,0,0,paint);



        //
        int centerY = 200;
//        float baseLineY = centerY + ((bottom - top)/2 - bottom)
//                        = heigth/2  - (bottom + top)/2
//                        = centerY + ((descent - ascent)/2 - descent)
//                        = heigth/2  - (descent + ascent)/2


//

        ColorMatrix colorFilter = new ColorMatrix(new float[]{
            1f,0,0,0,0,
            0,1f,0,0,0,
            0,0,1f,0,0,
            0,0,0,1f,0

        });


        Path path  =new Path();
        path.moveTo(100,100);




        //保存当前画布的位置
        canvas.save();

        canvas.translate(10,10);
        paint.setColor(Color.BLUE);
        canvas.drawRect(10,10,100,100,paint);

        //画布恢复到之前保存的位置
        canvas.restore();


//        canvas.skew();

//        Drawable drawable1 = new ColorDrawable()

//        ShapeDrawable
//        BitmapDrawable
//        LayerDrawable

        PathMeasure pathMeasure = new PathMeasure();
        pathMeasure.setPath(path,false);
        //下一个path
        boolean b = pathMeasure.nextContour();

        float length = pathMeasure.getLength();

        Path pathDst = new Path();
        //获取下一个segment片段
        pathMeasure.getSegment(0,length/2,pathDst,true);

        View
    }
}
