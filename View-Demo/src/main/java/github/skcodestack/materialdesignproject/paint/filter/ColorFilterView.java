package github.skcodestack.materialdesignproject.paint.filter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.LightingColorFilter;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.BitmapDrawable;
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

public class ColorFilterView extends View {

    private Paint mPaint;
    private Bitmap bitmap;

    public ColorFilterView(Context context) {
        this(context,null);
    }

    public ColorFilterView(Context context, AttributeSet attrs) {
        this(context, attrs,-1);
    }

    public ColorFilterView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mPaint = new Paint();
        bitmap = ((BitmapDrawable)getContext().getResources().getDrawable(R.drawable.img_s)).getBitmap();

    }

    enum ColorFilterMode{
        None,
        ReversedPhase,//反相
        ColorAdvance,//颜色增强
        WhiteBlack, //黑白图片
        ExchangeColor,//发色效果
        Retro,        //复古风格

        //
        Scale,
        Saturation,
        Rotate,
        LightingColorFilter,
        PorterDuffColorFilter
    };

    ColorFilterMode mode = ColorFilterMode.None;

    @Override
    protected void onDraw(Canvas canvas) {
        mPaint.reset();
        ColorMatrix matrix = new ColorMatrix();
        switch (mode){
            case None:
                break;
            case ReversedPhase:
                createReversedPhase();
                break;
            case ColorAdvance:
                createColorAdvance();
                break;
            case WhiteBlack:
                createWhiteBlack();
                break;
            case ExchangeColor:
                createExchangeColor();
                break;
            case Retro:
                createRetro();
                break;
            case Scale:
                //亮度调节
                matrix.setScale(1.2f,1.2f,1.2f,1.2f);
                mPaint.setColorFilter(new ColorMatrixColorFilter(matrix));
                break;
            case Saturation:
                //饱和度调节（1，是原来不变；0灰色；>1增加饱和度）
                matrix.setSaturation(0);
                mPaint.setColorFilter(new ColorMatrixColorFilter(matrix));
                break;
            case Rotate:
                /**
                 * 色彩调节
                 * axis,代表绕哪一个轴旋转，0,1,2
                 * 	(0红色轴，1绿色，2蓝色)
                 * degrees：旋转的度数
                 */
                matrix.setRotate(0, 90);
                mPaint.setColorFilter(new ColorMatrixColorFilter(matrix));
                break;

            case LightingColorFilter:
                createLightingColorFilter();
                break;
            case PorterDuffColorFilter:
                createPorterDuffColorFilter();
                break;
        }




        int left = (getWidth() - bitmap.getWidth()) / 2;
        int top = (getHeight() - bitmap.getHeight()) / 2;
        canvas.drawBitmap(bitmap,left,top,mPaint);

    }

    private void createPorterDuffColorFilter() {
        PorterDuffColorFilter porterDuffColorFilter = new PorterDuffColorFilter(Color.RED, PorterDuff.Mode.DARKEN);
        mPaint.setColorFilter(porterDuffColorFilter);
    }

    private void createLightingColorFilter() {
        /**
         * mul  相乘 ---缩放
         * add  相加---平移
         */
        LightingColorFilter lightingColorFilter = new LightingColorFilter(0x00ffff, 0x000000);
        mPaint.setColorFilter(lightingColorFilter);
    }

    private void createExchangeColor() {
        //发色效果（比如红色和绿色交换----把第一行和第二行交换）
        ColorMatrix matrix = new ColorMatrix(new float[]{
            0,1f,0,0,0,
            1f,0,0,0,0,
            0,0,1f,0,0,
            0,0,0,1f,0,
        });
        mPaint.setColorFilter(new ColorMatrixColorFilter(matrix));

    }

    private void createRetro() {
        //复古风格
        ColorMatrix matrix = new ColorMatrix(new float[]{
                1/2f,1/2f,1/2f,0,0,
                1/3f,1/3f,1/3f,0,0,
                1/4f,1/4f,1/4f,0,0,
                0,0,0,1f,0,
        });
        mPaint.setColorFilter(new ColorMatrixColorFilter(matrix));
    }

    private void createWhiteBlack() {
        //处理图片为黑白图片（图像学：如何让图片成为灰色即黑白？R+G+B=1）
        /**
         *
         去色原理：只要把RGB三通道的色彩信息设置成一样；即：R＝G
         ＝B，那么图像就变成了灰色，并且，为了保证图像亮度不变，
         同一个通道中的R+G+B=1:如：0.213+0.715+0.072＝1；
         RGB=0.213, 0.715, 0.072；
         三个数字是根据色彩光波频率及色彩心理学计算出来的.
         */
		ColorMatrix matrix = new ColorMatrix(new float[]{
				0.213f, 0.715f,0.072f,0,0,
				0.213f, 0.715f,0.072f,0,0,
				0.213f, 0.715f,0.072f,0,0,
				0, 		0,		0,	  1f,0,
		});
        mPaint.setColorFilter(new ColorMatrixColorFilter(matrix));
    }

    private void createColorAdvance() {
        //颜色增强（可以起到一个变亮的效果）---矩阵缩放方式
		ColorMatrix matrix = new ColorMatrix(new float[]{
				1.2f,0,0,0,0,
				0,1.2f,0,0,0,
				0,0,1.2f,0,0,
				0,0,0,1.2f,0,
		});
        mPaint.setColorFilter(new ColorMatrixColorFilter(matrix));
    }

    private void createReversedPhase() {
        //反相效果
        ColorMatrix matrix = new ColorMatrix(new float[]{
                -1,0,0,0,255,
                0,-1,0,0,255,
                0,0,-1,0,255,
                0,0,0,1,0,
        });

        mPaint.setColorFilter(new ColorMatrixColorFilter(matrix));
    }


    public void setMode(ColorFilterMode mode) {
        this.mode = mode;
        postInvalidate();
    }
}
