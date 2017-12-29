package github.skcodestack.materialdesignproject.recyclerview.layoutmanager;

import android.graphics.Rect;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

/**
 * Email  1562363326@qq.com
 * Github https://github.com/skcodestack
 * Created by sk on 2017/12/22
 * Version  1.0
 * Description:
 */

public class AlbumLayoutManager extends RecyclerView.LayoutManager{


    public AlbumLayoutManager() {
        setAutoMeasureEnabled(true);
    }


//    @Override
//    public void onMeasure(RecyclerView.Recycler recycler, RecyclerView.State state, int widthSpec, int heightSpec) {
//        super.onMeasure(recycler,state,widthSpec,heightSpec);
//        int width = getWidth();
//        int cellWidth = width / 3-5;
//
//        for (int i = 0; i < getItemCount(); i++) {
//            //找recycler要一个childItemView,我们不管它是从scrap里取，还是从RecyclerViewPool里取，亦或是onCreateViewHolder里拿。
//            View child = recycler.getViewForPosition(i);
//            int widths = 0;
//            int heights = 0;
//            if(i == 0){
//                widths = View.MeasureSpec.makeMeasureSpec(2*cellWidth, View.MeasureSpec.EXACTLY);
//                heights = View.MeasureSpec.makeMeasureSpec(2*cellWidth, View.MeasureSpec.EXACTLY);
//            }else {
//                widths = View.MeasureSpec.makeMeasureSpec(cellWidth, View.MeasureSpec.EXACTLY);
//                heights = View.MeasureSpec.makeMeasureSpec(2*cellWidth, View.MeasureSpec.EXACTLY);
//            }
//            child.measure(widths,heights);
//        }
//
//    }

    @Override
    public RecyclerView.LayoutParams generateDefaultLayoutParams() {
        return new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
    }

    @Override
    public void onLayoutChildren(RecyclerView.Recycler recycler, RecyclerView.State state) {

        if (getItemCount() == 0) {//没有Item，界面空着吧
            detachAndScrapAttachedViews(recycler);
            return;
        }
        if (getChildCount() == 0 && state.isPreLayout()) {//state.isPreLayout()是支持动画的
            return;
        }
        //onLayoutChildren方法在RecyclerView 初始化时 会执行两遍
        detachAndScrapAttachedViews(recycler);

        fill(recycler, state);
    }


    private void fill(RecyclerView.Recycler recycler, RecyclerView.State state) {
        int width = getWidth();
        int cellWidth = width / 3-5;

        for (int i = 0; i < getItemCount(); i++) {
            //找recycler要一个childItemView,我们不管它是从scrap里取，还是从RecyclerViewPool里取，亦或是onCreateViewHolder里拿。
            View child = recycler.getViewForPosition(i);
            addView(child);
//            measureChildWithMargins(child, 0, 0);
            final RecyclerView.LayoutParams lp = (RecyclerView.LayoutParams) child.getLayoutParams();

//            final Rect insets = getItemDecorInsetsForChild(child);
//            widthUsed += insets.left + insets.right;
//            heightUsed += insets.top + insets.bottom;

            int childWidth = 0;
            int childHeight = 0;
//            if(i == 0){
//                 childWidth = cellWidth * 2;
//                 childHeight = cellWidth * 2;
//            }else {
                 childWidth = cellWidth;
                 childHeight = cellWidth;
//            }

            final int widthSpec = getChildMeasureSpec(childWidth, getWidthMode(),
                    getPaddingLeft() + getPaddingRight() +
                            lp.leftMargin + lp.rightMargin , lp.width,
                    canScrollHorizontally());
            final int heightSpec = getChildMeasureSpec(childHeight, getHeightMode(),
                    getPaddingTop() + getPaddingBottom() +
                            lp.topMargin + lp.bottomMargin , lp.height,
                    canScrollVertically());
            child.measure(widthSpec, heightSpec);
            if(i == 0){
                layoutDecoratedWithMargins(child,0,0, 2*cellWidth,2*cellWidth);
            }else if(i == 1 || i == 2){
                layoutDecoratedWithMargins(child,2 * cellWidth,(i-1) * cellWidth,3 * cellWidth,i*cellWidth);
            }else {
                layoutDecoratedWithMargins(child,(i%3) * cellWidth, (i/3+1) * cellWidth,(i%3 + 1) * cellWidth,(i/3+2) * cellWidth);
            }

        }

    }
}
