package github.skcodestack.materialdesignproject.paint.filter;

import android.graphics.BlurMaskFilter;
import android.graphics.LinearGradient;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.TextView;

import github.skcodestack.materialdesignproject.R;

public class FilterActivity extends AppCompatActivity {

    private FilterView target;
    private RadioGroup rg_maskfilter;


    FilterView.MaskFilterMode mode = FilterView.MaskFilterMode.NONE;
    private float  radius = 10;
    BlurMaskFilter.Blur currentStyle =  BlurMaskFilter.Blur.NORMAL;
    //
    private float directionX = 0;
    private float directionY = 0;
    private float directionZ = 0;
    private float ambient = 0;
    private float specular = 0;
    private RadioGroup rg_filterstype;
    private SeekBar seekBar;
    private TextView tv_progress;
    private LinearLayout ll_emoss;
    private SeekBar seekbar_directionX;
    private SeekBar seekbar_directionY;
    private SeekBar seekbar_directionZ;
    private SeekBar seekbar_ambient;
    private SeekBar seekbar_specular;
    private TextView tv_directionX;
    private TextView tv_directionY;
    private TextView tv_directionZ;
    private TextView tv_ambient;
    private TextView tv_specular;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter);

        ll_emoss = (LinearLayout) findViewById(R.id.ll_emoss);
        findViewById(R.id.ll_emoss);
        target = (FilterView) findViewById(R.id.target);
        rg_maskfilter = (RadioGroup) findViewById(R.id.rg_maskfilter);
        rg_filterstype = (RadioGroup) findViewById(R.id.rg_filterstype);
        seekBar = (SeekBar) findViewById(R.id.seekbar);
        tv_progress = (TextView) findViewById(R.id.tv_progress);

        seekbar_directionX = (SeekBar) findViewById(R.id.seekbar_directionX);
        seekbar_directionY = (SeekBar) findViewById(R.id.seekbar_directionY);
        seekbar_directionZ = (SeekBar) findViewById(R.id.seekbar_directionZ);
        seekbar_ambient = (SeekBar) findViewById(R.id.seekbar_ambient);
        seekbar_specular = (SeekBar) findViewById(R.id.seekbar_specular);

        tv_directionX = (TextView) findViewById(R.id.tv_directionX);
        tv_directionY = (TextView) findViewById(R.id.tv_directionY);
        tv_directionZ = (TextView) findViewById(R.id.tv_directionZ);
        tv_ambient = (TextView) findViewById(R.id.tv_ambient);
        tv_specular = (TextView) findViewById(R.id.tv_specular);



        initListener();

        seekBar.setMax(200);
        seekBar.setProgress(20);
        seekbar_directionX.setProgress(1);
        seekbar_directionY.setProgress(1);
        seekbar_directionZ.setProgress(1);
        seekbar_ambient.setProgress(1);
        seekbar_specular.setProgress(1);
        updaeMaskFilter();
    }

    private void initListener() {

        rg_maskfilter.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.rb_nomask:
                        mode = FilterView.MaskFilterMode.NONE;
                        ll_emoss.setVisibility(View.GONE);
                        rg_filterstype.setVisibility(View.GONE);
                        break;
                    case R.id.rb_blurmask:
                        Log.e("xxx","===>rb_blurmask");
                        mode = FilterView.MaskFilterMode.BlurMaskFilter;
                        ll_emoss.setVisibility(View.GONE);
                        rg_filterstype.setVisibility(View.VISIBLE);
                        break;
                    case R.id.rb_embossmask:
                        mode =  FilterView.MaskFilterMode.EmbossMaskFilter;
                        ll_emoss.setVisibility(View.VISIBLE);
                        rg_filterstype.setVisibility(View.GONE);
                        break;
                }
                updaeMaskFilter();
            }
        });

        rg_filterstype.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.rb_normal:
                        currentStyle =  BlurMaskFilter.Blur.NORMAL;
                        break;
                    case R.id.rb_inner:
                        currentStyle =  BlurMaskFilter.Blur.INNER;
                        break;
                    case R.id.rb_outer:
                        currentStyle =  BlurMaskFilter.Blur.OUTER;
                        break;
                    case R.id.rb_solid:
                        currentStyle =  BlurMaskFilter.Blur.SOLID;
                        break;
                }
                updaeMaskFilter();
            }
        });

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                radius = progress;
                tv_progress.setText(progress+"");

                updaeMaskFilter();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        seekbar_directionX.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                directionX = progress;
                tv_directionX.setText(progress+"");
                updaeMaskFilter();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        seekbar_directionY.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                directionY = progress;
                tv_directionY.setText(progress+"");
                updaeMaskFilter();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        seekbar_directionZ.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                directionZ = progress;
                tv_directionZ.setText(progress+"");
                updaeMaskFilter();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        seekbar_ambient.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                ambient = progress;
                tv_ambient.setText(progress+"");
                updaeMaskFilter();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        seekbar_specular.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                specular = progress;
                tv_specular.setText(progress+"");
                updaeMaskFilter();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }


    public void updaeMaskFilter(){

//        tv_progress
        target.setMode(mode);
        target.setCurrentStyle(currentStyle);
        target.setRadius(radius);
        target.setAmbient(ambient);
        target.setDirectionX(directionX);
        target.setDirectionY(directionY);
        target.setDirectionZ(directionZ);
        target.setSpecular(specular);
        target.invokeInvalidate();
    }



}
