package github.skcodestack.materialdesignproject.paint.filter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import github.skcodestack.materialdesignproject.R;

public class ColorFilterActivity extends AppCompatActivity {

    private ColorFilterView target;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_color_filter);

        target = (ColorFilterView) findViewById(R.id.target);
    }

    public void btn_non(View view){
        target.setMode(ColorFilterView.ColorFilterMode.None);
    }


    public void btn_fx(View view){
        target.setMode(ColorFilterView.ColorFilterMode.ReversedPhase);
    }

    public void btn_coloradvance(View view){
        target.setMode(ColorFilterView.ColorFilterMode.ColorAdvance);
    }

    public void btn_whiteblack(View view){
        target.setMode(ColorFilterView.ColorFilterMode.WhiteBlack);
    }
    public void btn_exchangecolor(View view){
        target.setMode(ColorFilterView.ColorFilterMode.ExchangeColor);
    }
    public void btn_retro(View view){
        target.setMode(ColorFilterView.ColorFilterMode.Retro);
    }

    public void btn_scale(View view){target.setMode(ColorFilterView.ColorFilterMode.Scale);}
    public void btn_saturation(View view){target.setMode(ColorFilterView.ColorFilterMode.Saturation);}
    public void btn_rotate(View view){target.setMode(ColorFilterView.ColorFilterMode.Rotate);}

    public void btn_lightingcolorfilter(View  view){
        target.setMode(ColorFilterView.ColorFilterMode.LightingColorFilter);
    }

    public void btn_porterduffcolorfilter(View view){
        target.setMode(ColorFilterView.ColorFilterMode.PorterDuffColorFilter);
    }
}
