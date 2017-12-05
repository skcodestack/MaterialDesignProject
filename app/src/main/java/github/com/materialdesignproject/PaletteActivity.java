package github.com.materialdesignproject;

import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import github.com.materialdesignproject.util.PaletteUtil;

public class PaletteActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_palette);

        final TextView tv = (TextView) findViewById(R.id.tv);

        ImageView imageView = (ImageView) findViewById(R.id.iv);

        BitmapDrawable drawable = (BitmapDrawable) imageView.getDrawable();

        PaletteUtil paletteUtil = new PaletteUtil( drawable);
        paletteUtil.setPaletteColor(PaletteUtil.PaletteColor.LightVibrantColor, Color.WHITE, new PaletteUtil.PaletteColorCallBack() {
            @Override
            public void obtainColors(PaletteUtil palette, int mainColor, int titleTextColor, int bodyTextColor) {
                tv.setBackgroundColor(palette.obtainTranslucentColor(0.7f,mainColor));
                tv.setTextColor(titleTextColor);

            }
        });



    }
}
