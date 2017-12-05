package github.com.materialdesignproject.util;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.support.annotation.ColorInt;
import android.support.annotation.NonNull;
import android.support.v7.graphics.Palette;

/**
 * Email  1562363326@qq.com
 * Github https://github.com/skcodestack
 * Created by sk on 2017/12/4
 * Version  1.0
 * Description:调色板
 */

public class PaletteUtil {


    public  enum PaletteColor{
        //暗 柔和
        DarkMutedColor,
        //暗 鲜艳
        DarkVibrantColor,
        //亮 鲜艳
        LightVibrantColor,
        //柔和
        MutedColor,
        //鲜艳
        VibrantColor,

    };


    private final Palette.Builder builder;
    PaletteUtil paletteUtil ;
    public PaletteUtil(@NonNull BitmapDrawable bitmapDrawable){
        Bitmap bitmap = bitmapDrawable.getBitmap();
        builder = Palette.from(bitmap);
        paletteUtil = this;
    }




    /**
     * //暗、柔和
     //                int lightMutedColor = palette.getLightMutedColor(Color.BLUE);
     //                //暗、鲜艳
     //                int darkVibrantColor = palette.getDarkVibrantColor(Color.BLUE);
     //                //亮、鲜艳
     //                int lightVibrantColor = palette.getLightVibrantColor(Color.BLUE);
     //                //柔和
     //                int mutedColor = palette.getMutedColor(Color.BLUE);
     //                //柔和
     //                int vibrantColor = palette.getVibrantColor(Color.BLUE);
     //                //获取某种特性颜色的样品
     ////				Swatch lightVibrantSwatch = palette.getLightVibrantSwatch();
     //                Palette.Swatch lightVibrantSwatch = palette.getVibrantSwatch();
     //                //谷歌推荐的：图片的整体的颜色rgb的混合值---主色调
     //                int rgb = lightVibrantSwatch.getRgb();
     //                //谷歌推荐：图片中间的文字颜色
     //                int bodyTextColor = lightVibrantSwatch.getBodyTextColor();
     //                //谷歌推荐：作为标题的颜色（有一定的和图片的对比度的颜色值）
     //                int titleTextColor = lightVibrantSwatch.getTitleTextColor();
     //                //颜色向量
     //                float[] hsl = lightVibrantSwatch.getHsl();
     //                //分析该颜色在图片中所占的像素多少值
     //                int population = lightVibrantSwatch.getPopulation();
     * @param paletteColor
     * @param defaultColor
     */
    public  void setBackgroudColor(@NonNull final PaletteColor paletteColor, @ColorInt final int defaultColor, final BackgroudColorCallBack callBack){

        builder.generate(new Palette.PaletteAsyncListener() {
            @Override
            public void onGenerated(Palette palette) {

                int  color = defaultColor;
                switch (paletteColor){
                    case DarkMutedColor:
                        color = palette.getDarkMutedColor(defaultColor);
                        break;
                    case DarkVibrantColor:
                        color = palette.getDarkVibrantColor(defaultColor);
                        break;
                    case LightVibrantColor:
                        color = palette.getLightVibrantColor(defaultColor);
                        break;
                    case MutedColor:
                        color = palette.getMutedColor(defaultColor);
                        break;
                    case VibrantColor:
                        color = palette.getVibrantColor(defaultColor);
                        break;
                    default:
                        break;

                }
                if(callBack != null) {
                    callBack.obtainColors(paletteUtil,color);
                }

            }
        });


    }

    /**
     * 设置在调色板颜色
     *
     * @param paletteColor
     * @param defaultColor
     * @param callBack
     */
    public  void setPaletteColor(@NonNull final PaletteColor paletteColor, @ColorInt final int defaultColor, final PaletteColorCallBack callBack) {

        builder.generate(new Palette.PaletteAsyncListener() {
            @Override
            public void onGenerated(Palette palette) {

                int color = defaultColor;
                int bodytextColor = defaultColor;
                int titleTextColor = defaultColor;
                Palette.Swatch swatch = null;
                switch (paletteColor) {
                    case DarkMutedColor:
                        swatch = palette.getDarkMutedSwatch();
                        break;
                    case DarkVibrantColor:
                        swatch = palette.getDarkVibrantSwatch();
                        break;
                    case LightVibrantColor:
                        swatch = palette.getLightVibrantSwatch();
                        break;
                    case MutedColor:
                        swatch = palette.getMutedSwatch();
                        break;
                    case VibrantColor:
                        swatch = palette.getVibrantSwatch();
                        break;
                    default:
                        swatch = palette.getLightVibrantSwatch();
                        break;

                }
                if (swatch != null) {
                    color = swatch.getRgb();
                    bodytextColor = swatch.getBodyTextColor();
                    titleTextColor = swatch.getTitleTextColor();
                }

                if (callBack != null) {
                    callBack.obtainColors(paletteUtil, color,titleTextColor,bodytextColor);
                }
            }
        });
    }


    /**
     * 获取当前颜色的透明度颜色
     * @param percent   透明度
     * @param rgb
     * @return
     */
    public  int obtainTranslucentColor(float percent, int rgb){
        int blue = Color.blue(rgb);
        int green = Color.green(rgb);
        int red = Color.red(rgb);
        int alpha = Color.alpha(rgb);
//		int blue = rgb & 0xff;
//		int green = rgb>>8 & 0xff;
//		int red = rgb>>16 & 0xff;
//		int alpha = rgb>>>24;

        alpha = Math.round(alpha*percent);
        return Color.argb(alpha, red, green, blue);
    }


    public  interface  PaletteColorCallBack{
        void obtainColors(PaletteUtil palette, int mainColor, int titleTextColor, int bodyTextColor);
    }

    public  interface  BackgroudColorCallBack{
        void obtainColors(PaletteUtil palette, int backgroudColor);
    }

}
