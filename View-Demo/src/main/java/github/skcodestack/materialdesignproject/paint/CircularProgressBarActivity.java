package github.skcodestack.materialdesignproject.paint;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import github.skcodestack.materialdesignproject.R;
import github.skcodestack.materialdesignproject.paint.view.circularProgressBar;

public class CircularProgressBarActivity extends AppCompatActivity {

    private circularProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_circular_progress_bar);
        progressBar = (circularProgressBar) findViewById(R.id.progressBar);
        if(savedInstanceState != null){
            int progress = savedInstanceState.getInt("progress");
            if(progress != 0){
                progressBar.setProgress(progress);
            }
        }

    }


    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("progress",progressBar.getProgress());
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

            progressBar.setProgress(progressBar.getProgress()+1);
            if(progressBar.getProgress() < 100){
                mHandler.sendEmptyMessageDelayed(1, 200);
            }

        }
    };

}
