package github.com.materialdesignproject.behavior;

import android.content.Context;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;

/**
 * Email  1562363326@qq.com
 * Github https://github.com/skcodestack
 * Created by sk on 2017/12/6
 * Version  1.0
 * Description:
 */

public class RecyclerFlingBehavior extends AppBarLayout.Behavior {

    private static final int TOP_CHILD_FLING_THRESHOLD = 10;
    private boolean isPositive;


    public RecyclerFlingBehavior() {
    }

    public RecyclerFlingBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
    }


//    @Override
//    public boolean layoutDependsOn(CoordinatorLayout parent, AppBarLayout child, View dependency) {
//        return dependency instanceof  RecyclerView;
//    }


//    @Override
//    public boolean onDependentViewChanged(CoordinatorLayout parent, AppBarLayout child, View dependency) {
//        return super.onDependentViewChanged(parent, child, dependency);
//    }
//
//    @Override
//    public void onDependentViewRemoved(CoordinatorLayout parent, AppBarLayout child, View dependency) {
//        super.onDependentViewRemoved(parent, child, dependency);
//    }

    @Override
    public boolean onNestedFling(CoordinatorLayout coordinatorLayout, AppBarLayout child, View target, float velocityX, float velocityY, boolean consumed) {
        if (velocityY > 0 && !isPositive || velocityY < 0 && isPositive) {
            velocityY = velocityY * -1;
        }
        if (target instanceof RecyclerView && velocityY < 0) {
            final RecyclerView recyclerView = (RecyclerView) target;
            final View firstChild = recyclerView.getChildAt(0);
            final int childAdapterPosition = recyclerView.getChildAdapterPosition(firstChild);
            consumed = childAdapterPosition > TOP_CHILD_FLING_THRESHOLD;
//            if(child.get){
//
//            }


        }
        return super.onNestedFling(coordinatorLayout, child, target, velocityX, velocityY, consumed);
    }
    @Override
    public void onNestedPreScroll(CoordinatorLayout coordinatorLayout, AppBarLayout child, View target, int dx, int dy, int[] consumed) {
        super.onNestedPreScroll(coordinatorLayout, child, target, dx, dy, consumed);
        isPositive = dy > 0;
    }
}
