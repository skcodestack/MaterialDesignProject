package github.skcodestack.recyclerview.divider;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Email  1562363326@qq.com
 * Github https://github.com/skcodestack
 * Created by sk on 2017/5/31
 * Version  1.0
 * Description:
 */

public class LinearLayoutItemDecoration extends RecyclerView.ItemDecoration {

    private Context mContext;
    private int mDrawableResId;
    private final Drawable mDivideDrawale;

    int orientation = LinearLayoutManager.VERTICAL;

    //系统默认分割线资源
    private int[] attrs = new int[]{
            android.R.attr.listDivider
    };

    /**
     *
     * @param context
     * @param drawableResId  分割线资源，
     *                       可以在theme中进行设置
     *                       <item name="android:listDivider">@drawable/linearlayout_item</item>
     */
    public LinearLayoutItemDecoration(Context context,int drawableResId){
        this.mContext = context;
        if(drawableResId < 0){
            drawableResId = -1 ;
            TypedArray typedArray =
                    context.obtainStyledAttributes(attrs);

            mDivideDrawale = typedArray.getDrawable(0);
            typedArray.recycle();
            return;
        }
        this.mDrawableResId = drawableResId;
        mDivideDrawale = ContextCompat.getDrawable(context,drawableResId);
    }

    /**
     * 分割线可以在theme中进行设置<item name="android:listDivider">@drawable/linearlayout_item</item>
     * @param context
     */
    public LinearLayoutItemDecoration(Context context){
        this(context,-1);
    }

    /**
     *
     * @param context
     * @param drawableResId   分割线可以在theme中进行设置<item name="android:listDivider">@drawable/linearlayout_item</item>
     * @param orientation     LinearLayoutManager.VERTICAL
     */
    public LinearLayoutItemDecoration(Context context,int drawableResId,int orientation ){
        this(context,drawableResId);
        setOrientation(orientation);
    }

    /**
     * 设置 LinearLayoutManager.VERTICAL
     * @param orientation
     */
    public void setOrientation(int orientation) {
        this.orientation = orientation;
    }

    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
//        super.onDraw(c, parent, state);


        if(orientation == LinearLayoutManager.VERTICAL){
            drawVertical(c,parent);
        }else if(orientation == LinearLayoutManager.HORIZONTAL){
            drawHorizontal(c,parent);
        }


    }

    private void drawHorizontal(Canvas c, RecyclerView parent) {
        int count = parent.getChildCount();
        Rect rect = new Rect();

        for (int i = 0; i < count-1; i++) {
            View child = parent.getChildAt(i);
            RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child.getLayoutParams();

            rect.top = child.getTop();
            rect.left = child.getRight() + params.rightMargin + Math.round(ViewCompat.getTranslationX(child));
            rect.right = rect.left + mDivideDrawale.getIntrinsicWidth();
            rect.bottom = child.getRight() -  parent.getPaddingBottom();

            mDivideDrawale.setBounds(rect);
            mDivideDrawale.draw(c);

        }
    }


    private void drawVertical(Canvas c, RecyclerView parent){
        int count = parent.getChildCount();
        Rect rect = new Rect();

        for (int i = 1; i < count; i++) {
            View child = parent.getChildAt(i);
            RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child.getLayoutParams();
            rect.left = child.getLeft();
            rect.right = child.getRight() -  parent.getPaddingRight();
            rect.bottom = child.getTop()-params.topMargin;
            rect.top = rect.bottom - mDivideDrawale.getIntrinsicHeight();

            mDivideDrawale.setBounds(rect);
            mDivideDrawale.draw(c);

        }
    }


    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
//        super.getItemOffsets(outRect, view, parent, state);
        int postion = parent.getChildAdapterPosition(view);
        if(orientation == LinearLayoutManager.VERTICAL){
            if(postion > 0){
                outRect.top=mDivideDrawale.getIntrinsicHeight();
            }
        }else if(orientation == LinearLayoutManager.HORIZONTAL){
            if(postion > 0){
                outRect.right=mDivideDrawale.getIntrinsicWidth();
            }
        }

    }


}
