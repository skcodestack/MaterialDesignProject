package github.skcodestack.materialdesignproject.paint.shader;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ComposeShader;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.RadialGradient;
import android.graphics.Shader;
import android.graphics.SweepGradient;
import android.graphics.Xfermode;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;

import github.skcodestack.materialdesignproject.R;

/**
 * Email  1562363326@qq.com
 * Github https://github.com/skcodestack
 * Created by sk on 2017/12/26
 * Version  1.0
 * Description:
 */

public class CustomShaderView extends View {


    enum Type{
        BimapShader,
        LinearGradient,
        RadialGradient,
        SweepGradient,
        ComposeShader
    };

    Type mType = Type.ComposeShader;

    private Bitmap bitmap;
    private Paint mPaint;

    public CustomShaderView(Context context) {
        this(context,null);
    }

    public CustomShaderView(Context context, AttributeSet attrs) {
        this(context, attrs,-1);
    }

    public CustomShaderView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init(){
        BitmapDrawable bitmapDrawable = (BitmapDrawable) getContext().getResources().getDrawable(R.drawable.yez);
        bitmap = bitmapDrawable.getBitmap();
        mPaint = new Paint();
    }


    @Override
    protected void onDraw(Canvas canvas) {
//        super.onDraw(canvas);


        switch (mType){
            case BimapShader:
                createBitmapShader(canvas);
                break;
            case LinearGradient:
                createLinearGradient(canvas);
                break;
            case RadialGradient:
                createRadialGradient(canvas);
                break;
            case SweepGradient:
                createSweepGradient(canvas);
                break;
            case ComposeShader:
                createComposeShader(canvas);
                break;
        }

    }

    private void createComposeShader(Canvas canvas) {
        mPaint.reset();
        BitmapShader bitmapShader = new BitmapShader(bitmap,Shader.TileMode.CLAMP,Shader.TileMode.MIRROR );
        int[] colors = new int[]{Color.BLUE,Color.YELLOW,Color.GRAY,Color.RED};
        float[] postion = new float[]{0.3f,0.6f,0.8f,1f};
        SweepGradient sweepGradient = new SweepGradient(500, 500, colors, postion);
        ComposeShader composeShader = new ComposeShader(sweepGradient,bitmapShader, PorterDuff.Mode.DARKEN);
        mPaint.setShader(composeShader);
        canvas.drawRect(0,0,1000,1000,mPaint);

        mPaint.reset();
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(2);
        canvas.drawRect(0,0,1000,1000,mPaint);
    }

    private void createSweepGradient(Canvas canvas) {
        mPaint.reset();
        int[] colors = new int[]{Color.BLUE,Color.YELLOW,Color.GRAY,Color.RED};
        float[] postion = new float[]{0.3f,0.6f,0.8f,1f};
        SweepGradient sweepGradient = new SweepGradient(500, 500, colors, postion);
        mPaint.setShader(sweepGradient);
        canvas.drawCircle(500,500,500,mPaint);

        mPaint.reset();
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(2);
        canvas.drawRect(0,0,10000,1000,mPaint);
    }

    private void createRadialGradient(Canvas canvas) {

        mPaint.reset();
        RadialGradient radialGradient = new RadialGradient(500, 500, 100, Color.BLUE, Color.YELLOW, Shader.TileMode.REPEAT);
        mPaint.setShader(radialGradient);
        canvas.drawCircle(500,500,500,mPaint);

        mPaint.reset();
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(2);
        canvas.drawRect(0,0,10000,1000,mPaint);
    }

    private void createLinearGradient(Canvas canvas) {

        mPaint.reset();
        int[] colors = new int[]{Color.BLUE,Color.YELLOW,Color.RED};
        float[] postion = new float[]{0.3f,0.6f,1f};
        LinearGradient linearGradient = new LinearGradient(0, 0, 500, 500, colors, postion, Shader.TileMode.REPEAT);
        mPaint.setShader(linearGradient);
        canvas.drawRect(0,0,10000,1000,mPaint);

        mPaint.reset();
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(2);
        canvas.drawRect(0,0,10000,1000,mPaint);
    }


    private void createBitmapShader(Canvas canvas){
        mPaint.reset();
        /**
         * Shader.TileMode.CLAMP   拉伸
         * Shader.TileMode.MIRROR  镜像
         * Shader.TileMode.REPEAT  平铺
         */
        BitmapShader bitmapShader = new BitmapShader(bitmap,Shader.TileMode.MIRROR,Shader.TileMode.MIRROR );

        mPaint.setShader(bitmapShader);
        canvas.drawRect(0,0,1000,1000,mPaint);

        mPaint.reset();
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(2);
        canvas.drawRect(0,0,10000,1000,mPaint);
    }

}
