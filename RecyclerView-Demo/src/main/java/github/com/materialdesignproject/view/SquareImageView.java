package github.com.materialdesignproject.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;

import github.com.materialdesignproject.R;

/**
 * Email  1562363326@qq.com
 * Github https://github.com/skcodestack
 * Created by sk on 2017/11/28
 * Version  1.0
 * Description:
 */

public class SquareImageView extends ImageView {

    private int mProportionStandard=0;

    public SquareImageView(Context context) {
        this(context,null);
    }

    public SquareImageView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public SquareImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);


        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.SquareImageView);

        //
        mProportionStandard = array.getInt(R.styleable.SquareImageView_standard, mProportionStandard);

        array.recycle();
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        // 8.自适应高度
        if(mProportionStandard == 0) {
            int width = View.MeasureSpec.getSize(widthMeasureSpec);
            int height = width;
            heightMeasureSpec = View.MeasureSpec.makeMeasureSpec(height, View.MeasureSpec.EXACTLY);
        }else {
            int height = View.MeasureSpec.getSize(heightMeasureSpec);
            int width = height * mProportionStandard;
            widthMeasureSpec = View.MeasureSpec.makeMeasureSpec(width, View.MeasureSpec.EXACTLY);
        }

        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }
}
