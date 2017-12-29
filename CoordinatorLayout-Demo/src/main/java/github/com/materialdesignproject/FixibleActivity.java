package github.com.materialdesignproject;

import android.support.design.widget.AppBarLayout;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class FixibleActivity extends AppCompatActivity {
    private static final int PERCENTAGE_TO_SHOW_IMAGE = 100;
    private int mMaxScrollSize;
    private boolean mIsImageHidden;
    private View mFab;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.flexible_activity);

        mFab = findViewById(R.id.fab);
        AppBarLayout appBarLayout = (AppBarLayout) findViewById(R.id.main_appbar);

        appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if (mMaxScrollSize == 0)
                    mMaxScrollSize = appBarLayout.getTotalScrollRange();
                int currentScrollPercentage = (Math.abs(verticalOffset)) * 100
                        / mMaxScrollSize;
                if(currentScrollPercentage>=PERCENTAGE_TO_SHOW_IMAGE)

                {
                    if (!mIsImageHidden) {
                        mIsImageHidden = true;

                        ViewCompat.animate(mFab).scaleY(0).scaleX(0).start();
                    }
                }

                if(currentScrollPercentage<PERCENTAGE_TO_SHOW_IMAGE)

                {
                    if (mIsImageHidden) {
                        mIsImageHidden = false;
                        ViewCompat.animate(mFab).scaleY(1).scaleX(1).start();
                    }
                }
            }
        });
    }
}
