package github.skcodestack.materialdesignproject.path;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import github.skcodestack.materialdesignproject.R;
import github.skcodestack.materialdesignproject.path.view.WaveView;

public class WaveActivity extends AppCompatActivity {

    private WaveView waveView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wave);

        waveView = (WaveView) findViewById(R.id.target);
        waveView.startAnimation();

        if(savedInstanceState != null){
            float progress = savedInstanceState.getFloat("progress");
            if(progress != 0){
                waveView.setProgress(progress);
            }
        }
    }


    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putFloat("progress",waveView.getProgress());
    }

    @Override
    protected void onStart() {
        super.onStart();
        mHandler.removeCallbacksAndMessages(null);
        mHandler.sendEmptyMessageDelayed(1, 1000);
    }

    @Override
    protected void onStop() {
        super.onStop();
        mHandler.removeCallbacksAndMessages(null);
    }

    Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {

            waveView.setProgress(waveView.getProgress()+0.1f);
            if(waveView.getProgress() <= 1){
                mHandler.sendEmptyMessageDelayed(1, 1000);
            }

        }
    };
}
