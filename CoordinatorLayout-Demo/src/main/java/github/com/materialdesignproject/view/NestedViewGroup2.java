package github.com.materialdesignproject.view;

import android.content.Context;
import android.support.v4.view.MotionEventCompat;
import android.support.v4.view.NestedScrollingChild;
import android.support.v4.view.NestedScrollingChildHelper;
import android.support.v4.view.NestedScrollingParent;
import android.support.v4.view.NestedScrollingParentHelper;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.animation.DecelerateInterpolator;
import android.widget.AbsListView;
import android.widget.OverScroller;

import github.com.materialdesignproject.R;

/**
 * Email  1562363326@qq.com
 * Github https://github.com/skcodestack
 * Created by sk on 2017/12/8
 * Version  1.0
 * Description:
 */

public class NestedViewGroup2 extends ViewGroup implements NestedScrollingParent, NestedScrollingChild {

    private static final String LOG_TAG = "NestedViewGroup2";

    private int mTouchSlop;
    private OverScroller mScroller;

    private View mHeader;
    private View mFooter;
    private View mTarget;
    private int HEADER_LIMIT_HEIGHT;
    private int HEADER_REFRESH_HEIGHT;
    private int FOOTER_LIMIT_HEIGHT;
    private int FOOTER_REFRESH_HEIGHT;


    private float mTotalUnconsumed;
    private final NestedScrollingParentHelper mNestedScrollingParentHelper;
    private final NestedScrollingChildHelper mNestedScrollingChildHelper;
    private final int[] mParentScrollConsumed = new int[2];
    private final int[] mParentOffsetInWindow = new int[2];

    //移动参数：计算手指移动量的时候会用到这个值，值越大，移动量越小，若值为1则手指移动多少就滑动多少px
    private final double MOVE_PARA = 1;

    public NestedViewGroup2(Context context) {
        this(context,null);
    }

    public NestedViewGroup2(Context context, AttributeSet attrs) {
        this(context, attrs,-1);
    }

    public NestedViewGroup2(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);


        mTouchSlop = ViewConfiguration.get(context).getScaledTouchSlop();
        DecelerateInterpolator mDecelerateInterpolator = new DecelerateInterpolator();
        setWillNotDraw(false);

        mScroller = new OverScroller(context);
        mNestedScrollingParentHelper = new NestedScrollingParentHelper(this);

        mNestedScrollingChildHelper = new NestedScrollingChildHelper(this);
        setNestedScrollingEnabled(true);


        mHeader = LayoutInflater.from(getContext()).inflate(R.layout.custom_header_layout, null);
        addView(mHeader);
        mFooter = LayoutInflater.from(getContext()).inflate(R.layout.custom_header_layout, null);
        addView(mFooter);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        if(mTarget == null) {
            ensureTarget();
        }
        if(mTarget == null){
            return;
        }



        mTarget.bringToFront();


    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {


        mTarget.measure(MeasureSpec.makeMeasureSpec(
                getMeasuredWidth() - getPaddingLeft() - getPaddingRight(),
                MeasureSpec.EXACTLY), MeasureSpec.makeMeasureSpec(
                getMeasuredHeight() - getPaddingTop() - getPaddingBottom(), MeasureSpec.EXACTLY));

        mHeader.measure(MeasureSpec.makeMeasureSpec(getMeasuredWidth() - getPaddingLeft() - getPaddingRight(), MeasureSpec.EXACTLY),
                MeasureSpec.makeMeasureSpec(MeasureSpec.getSize(heightMeasureSpec), MeasureSpec.AT_MOST));
        mFooter.measure(MeasureSpec.makeMeasureSpec(getMeasuredWidth() - getPaddingLeft() - getPaddingRight(), MeasureSpec.EXACTLY),
                MeasureSpec.makeMeasureSpec(MeasureSpec.getSize(heightMeasureSpec), MeasureSpec.AT_MOST));

        if (mHeader != null) {
            HEADER_LIMIT_HEIGHT = mHeader.getMeasuredHeight();
            HEADER_REFRESH_HEIGHT = HEADER_LIMIT_HEIGHT;
        }
        if (mFooter != null) {
            FOOTER_LIMIT_HEIGHT = mFooter.getMeasuredHeight();
            FOOTER_REFRESH_HEIGHT = FOOTER_LIMIT_HEIGHT;
        }
        setMeasuredDimension(widthMeasureSpec, heightMeasureSpec);

    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        final int width = getMeasuredWidth();
        final int height = getMeasuredHeight();
        if (getChildCount() == 0) {
            return;
        }


        if(mTarget == null) {
            ensureTarget();
        }
        if(mTarget == null){
            return;
        }

        View child = mTarget;
        final int childLeft = getPaddingLeft();
        final int childTop = getPaddingTop();
        final int childWidth = width - getPaddingLeft() - getPaddingRight();
        final int childHeight = height - getPaddingTop() - getPaddingBottom();

        if (mHeader != null) {
            mHeader.layout(childLeft, -mHeader.getMeasuredHeight(), getWidth(), 0);
        }
        if (mFooter != null) {
            mFooter.layout(childLeft, getHeight(), getWidth(), getHeight() + mFooter.getMeasuredHeight());
        }

        child.layout(childLeft, childTop, childLeft + childWidth, childTop + childHeight);

    }

    //刷新中
    boolean mRefreshing = false;
    //嵌套滑动中
    boolean mNestedScrollInProgress = false;
    //被拖动
    boolean mIsBeingDragged = false;
    float mInitialMotionY = 0;
    private float mInitialDownY = 0;
    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {

        ensureTarget();
        if(mRefreshing || mNestedScrollInProgress){
            return false;
        }
        dealMulTouchEvent(ev);
        if(mScroller.computeScrollOffset()){
            mScroller.abortAnimation();
        }
        final int action = MotionEventCompat.getActionMasked(ev);
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                mIsBeingDragged = false;

                break;
            case MotionEvent.ACTION_MOVE:

                final float yDiff = mLastY - mInitialDownY;
                if (Math.abs(yDiff) > mTouchSlop && !mIsBeingDragged) {
                    mInitialMotionY = mLastY + mTouchSlop;
                    mIsBeingDragged = true;
                }

                break;
            case MotionEventCompat.ACTION_POINTER_UP:
                break;

            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_CANCEL:
                mIsBeingDragged = false;
                break;

        }
        return mIsBeingDragged;
    }


    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        final int action = MotionEventCompat.getActionMasked(ev);
        if(mRefreshing || mNestedScrollInProgress){
            return false;
        }
        if(mScroller.computeScrollOffset()){
            mScroller.abortAnimation();
        }
        dealMulTouchEvent(ev);

        switch (action) {
            case MotionEvent.ACTION_DOWN:
                mIsBeingDragged = false;
                break;

            case MotionEvent.ACTION_MOVE: {

                final float yDiff = mLastY - mInitialDownY;
                if ( Math.abs(yDiff) > mTouchSlop && !mIsBeingDragged) {
                    mInitialMotionY = mInitialDownY + mTouchSlop;
                    mIsBeingDragged = true;
                }

                if (mIsBeingDragged) {
                    moveSpinner(dy);
                }
                break;
            }
            case MotionEventCompat.ACTION_POINTER_DOWN: {

                break;
            }
            case MotionEventCompat.ACTION_POINTER_UP:
                break;

            case MotionEvent.ACTION_UP: {

                if (mIsBeingDragged) {
                    mIsBeingDragged = false;
                    //结束
                    finishSpinner();
                }
                return false;
            }
            case MotionEvent.ACTION_CANCEL:
                return false;
        }

        return true;
    }


    private int MOVE_TIME = 400;

    private void finishSpinner() {
        if (isTopOverFarm()) {
            mRefreshing = true;
            resetRefreshPosition();
        } else if (isBottomOverFarm()) {
            resetRefreshPosition();
        } else {
            resetPosition();
        }
    }
    /**
     * 判断顶部拉动是否超过临界值
     */
    private boolean isTopOverFarm() {

        return -getScrollY() > HEADER_LIMIT_HEIGHT;

    }

    /**
     * 判断底部拉动是否超过临界值
     */
    private boolean isBottomOverFarm() {

        return getScrollY() > FOOTER_LIMIT_HEIGHT;

    }

    private void resetPosition(){
        mScroller.startScroll(0, getScrollY(), 0, -getScrollY(), MOVE_TIME);
        invalidate();
    }

    private void resetRefreshPosition(){

        if (getScrollY() < 0) {     //下拉
            mScroller.startScroll(0, getScrollY(), 0, -getScrollY() - HEADER_REFRESH_HEIGHT, MOVE_TIME);
            invalidate();
        } else {       //上拉
            mScroller.startScroll(0, getScrollY(), 0, -getScrollY() + FOOTER_REFRESH_HEIGHT, MOVE_TIME);
            invalidate();
        }
    }

    private int MAX_HEADER_PULL_HEIGHT = 600;
    private int MAX_FOOTER_PULL_HEIGHT = 600;

    public void moveSpinner(float deY){
        int movedx;
        if(deY == 0){
            return;
        }
        if (deY > 0) {
            movedx = (int) ((float) ((MAX_HEADER_PULL_HEIGHT + getScrollY()) / (float) MAX_HEADER_PULL_HEIGHT) * deY / MOVE_PARA);

        } else {
            movedx = (int) ((float) ((MAX_FOOTER_PULL_HEIGHT - getScrollY()) / (float) MAX_FOOTER_PULL_HEIGHT) * deY / MOVE_PARA);
        }
        scrollBy(0, (int) (-movedx));
    }

    @Override
    public void computeScroll() {
        if (mScroller.computeScrollOffset()) {
            scrollTo(0, mScroller.getCurrY());
            invalidate();
        }
//        if(mRefreshing && mScroller.isFinished()){
//            mRefreshing = false;
//        }
    }

    /**
     * 处理多点触控的情况，准确地计算Y坐标和移动距离dy
     * 同时兼容单点触控的情况
     */
    private int mActivePointerId = MotionEvent.INVALID_POINTER_ID;
    //储存上次的Y坐标
    private float mLastY;
    private float mLastX;
    //记录单次滚动x,y轴偏移量
    private float dy;
    private float dx;


    public void dealMulTouchEvent(MotionEvent ev) {
        final int action = MotionEventCompat.getActionMasked(ev);
        switch (action) {
            case MotionEvent.ACTION_DOWN: {
                final int pointerIndex = MotionEventCompat.getActionIndex(ev);
                final float x = MotionEventCompat.getX(ev, pointerIndex);
                final float y = MotionEventCompat.getY(ev, pointerIndex);
                mLastX = x;
                mInitialDownY = mLastY = y;
                mActivePointerId = MotionEventCompat.getPointerId(ev, 0);
                break;
            }
            case MotionEvent.ACTION_MOVE: {
                final int pointerIndex = MotionEventCompat.findPointerIndex(ev, mActivePointerId);
                final float x = MotionEventCompat.getX(ev, pointerIndex);
                final float y = MotionEventCompat.getY(ev, pointerIndex);
                dx = x - mLastX;
                dy = y - mLastY;
                mLastY = y;
                mLastX = x;
                break;
            }
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_CANCEL:
                mActivePointerId = MotionEvent.INVALID_POINTER_ID;
                break;
            case MotionEvent.ACTION_POINTER_DOWN: {
                final int pointerIndex = MotionEventCompat.getActionIndex(ev);
                final int pointerId = MotionEventCompat.getPointerId(ev, pointerIndex);
                if (pointerId != mActivePointerId) {
                    mLastX = MotionEventCompat.getX(ev, pointerIndex);
                    mLastY = MotionEventCompat.getY(ev, pointerIndex);
                    mActivePointerId = MotionEventCompat.getPointerId(ev, pointerIndex);
                }
                break;
            }
            case MotionEvent.ACTION_POINTER_UP: {
                final int pointerIndex = MotionEventCompat.getActionIndex(ev);
                final int pointerId = MotionEventCompat.getPointerId(ev, pointerIndex);
                if (pointerId == mActivePointerId) {
                    final int newPointerIndex = pointerIndex == 0 ? 1 : 0;
                    mLastX = MotionEventCompat.getX(ev, newPointerIndex);
                    mLastY = MotionEventCompat.getY(ev, newPointerIndex);
                    mActivePointerId = MotionEventCompat.getPointerId(ev, newPointerIndex);
                }
                break;
            }
        }
    }


    private void ensureTarget() {
        // Don't bother getting the parent height if the parent hasn't been laid
        // out yet.
        if (mTarget == null) {
            for (int i = 0; i < getChildCount(); i++) {
                View child = getChildAt(i);
                if (!child.equals(mHeader) && !child.equals(mFooter)) {
                    mTarget = child;
                    break;
                }
            }
        }
    }

    /**
     * 能否继续向下滑动（到顶部）
     */
    private boolean canChildScrollUp() {
        ensureTarget();
        if (android.os.Build.VERSION.SDK_INT < 14) {
            if (mTarget instanceof AbsListView) {
                final AbsListView absListView = (AbsListView) mTarget;
                return absListView.getChildCount() > 0
                        && (absListView.getFirstVisiblePosition() > 0 || absListView.getChildAt(0)
                        .getTop() < absListView.getPaddingTop());
            } else {
                return ViewCompat.canScrollVertically(mTarget, -1) || mTarget.getScrollY() > 0;
            }
        } else {
            return ViewCompat.canScrollVertically(mTarget, -1);
        }
    }

    /**
     * 能否继续向上滑动（到底部）
     */
    private boolean canChildScrollDown() {
        ensureTarget();
        if (android.os.Build.VERSION.SDK_INT < 14) {
            if (mTarget instanceof AbsListView) {
                final AbsListView absListView = (AbsListView) mTarget;
                return absListView.getChildCount() > 0
                        && (absListView.getLastVisiblePosition() < absListView.getChildCount()-1 || absListView.getChildAt(absListView.getChildCount()-1)
                        .getBottom() > absListView.getPaddingBottom());
            } else {
                return ViewCompat.canScrollVertically(mTarget, 1) || mTarget.getScrollY() < 0;
            }
        } else {
            return ViewCompat.canScrollVertically(mTarget, 1);
        }
    }

// NestedScrollingParent

    @Override
    public boolean onStartNestedScroll(View child, View target, int nestedScrollAxes) {
        return isEnabled()  && !mRefreshing
                && (nestedScrollAxes & ViewCompat.SCROLL_AXIS_VERTICAL) != 0;
    }

    @Override
    public void onNestedScrollAccepted(View child, View target, int axes) {
        // Reset the counter of how much leftover scroll needs to be consumed.
        mNestedScrollingParentHelper.onNestedScrollAccepted(child, target, axes);
        // Dispatch up to the nested parent
        startNestedScroll(axes & ViewCompat.SCROLL_AXIS_VERTICAL);
        mTotalUnconsumed = 0;
        mNestedScrollInProgress = true;
    }


    private void updateScroll(){


    }


    @Override
    public void onNestedPreScroll(View target, int dx, int dy, int[] consumed) {
        // If we are in the middle of consuming, a scroll, then we want to move the spinner back up
        // before allowing the list to scroll
//        Log.e(LOG_TAG,"onNestedPreScroll===>start"+dy+"---"+mTotalUnconsumed);
//        if (dy > 0  &&  getScrollY() <= 0) {
////            if (dy > mTotalUnconsumed) {
////                consumed[1] = dy - (int) mTotalUnconsumed;
////                mTotalUnconsumed = 0;
////            } else {
//                mTotalUnconsumed -= dy;
//                consumed[1] = dy;
////            }
//            Log.e(LOG_TAG,"onNestedPreScroll===>consumed"+consumed[1]);
//            moveSpinner(-consumed[1]);
//        }
//        Log.e(LOG_TAG,"onNestedPreScroll===>end");
//        Log.e(LOG_TAG,"onNestedPreScroll===>start"+dy+"---"+mTotalUnconsumed);
        //dy > 0  手指向下
//        int scrollY = MAX_HEADER_PULL_HEIGHT - getScrollY();
//        getScrollY() <= 0 Log.e(LOG_TAG,"onNestedPreScroll===>start"+MAX_HEADER_PULL_HEIGHT+"==="+getScrollY()+"==="+scrollY);
        //dy > 0  手指向下
        if( dy > 0 && getScrollY() <= 0){

                consumed[1] = dy;
                moveSpinner(-consumed[1]);
        }else if(dy < 0 && getScrollY() > 0){
            consumed[1] = dy;
            moveSpinner(-consumed[1]);
        }


        // If a client layout is using a custom start position for the circle
        // view, they mean to hide it again before scrolling the child view
        // If we get back to mTotalUnconsumed == 0 and there is more to go, hide
        // the circle so it isn't exposed if its blocking content is moved
//        if (mUsingCustomStart && dy > 0 && mTotalUnconsumed == 0
//                && Math.abs(dy - consumed[1]) > 0) {
////            mCircleView.setVisibility(View.GONE);
//        }

        // Now let our nested parent consume the leftovers
        final int[] parentConsumed = mParentScrollConsumed;
        if (dispatchNestedPreScroll(dx - consumed[0], dy - consumed[1], parentConsumed, null)) {
            consumed[0] += parentConsumed[0];
            consumed[1] += parentConsumed[1];
        }
    }

    @Override
    public int getNestedScrollAxes() {
        return mNestedScrollingParentHelper.getNestedScrollAxes();
    }

    @Override
    public void onStopNestedScroll(View target) {
        mNestedScrollingParentHelper.onStopNestedScroll(target);
        mNestedScrollInProgress = false;
        // Finish the spinner for nested scrolling if we ever consumed any
        // unconsumed nested scroll
//        if (mTotalUnconsumed > 0) {
            finishSpinner();
//            mTotalUnconsumed = 0;
//        }
        // Dispatch up our nested parent
        stopNestedScroll();
    }

    @Override
    public void onNestedScroll(final View target, final int dxConsumed, final int dyConsumed,
                               final int dxUnconsumed, final int dyUnconsumed) {
        // Dispatch up to the nested parent first
        dispatchNestedScroll(dxConsumed, dyConsumed, dxUnconsumed, dyUnconsumed,
                mParentOffsetInWindow);

        // This is a bit of a hack. Nested scrolling works from the bottom up, and as we are
        // sometimes between two nested scrolling views, we need a way to be able to know when any
        // nested scrolling parent has stopped handling events. We do that by using the
        // 'offset in window 'functionality to see if we have been moved from the event.
        // This is a decent indication of whether we should take over the event stream or not.
//        Log.e(LOG_TAG,"onNestedScroll===>start"+dyUnconsumed+"---"+mParentOffsetInWindow[1]);
        final int dy = dyUnconsumed + mParentOffsetInWindow[1];
        //向下滑动
//        if (dy < 0 && !canChildScrollUp()) {
//            Log.e(LOG_TAG,"onNestedScroll===>"+dy+"==="+dyUnconsumed+"====="+mParentOffsetInWindow[1]);
//            mTotalUnconsumed += Math.abs(dy);
//            moveSpinner(-dy);
//        }else if(dy > 0 && !canChildScrollDown()){
//            mTotalUnconsumed -= Math.abs(dy);
//            moveSpinner(dy);
//        }
        if(dy < 0 && !canChildScrollUp()) {
            mTotalUnconsumed += Math.abs(dy);
            moveSpinner(-dy);
        }else if(dy > 0 && !canChildScrollDown()){
//            mTotalUnconsumed += Math.abs(dy);
            moveSpinner(-dy);
        }
    }


    // NestedScrollingChild

    @Override
    public void setNestedScrollingEnabled(boolean enabled) {
        mNestedScrollingChildHelper.setNestedScrollingEnabled(enabled);
    }

    @Override
    public boolean isNestedScrollingEnabled() {
        return mNestedScrollingChildHelper.isNestedScrollingEnabled();
    }

    @Override
    public boolean startNestedScroll(int axes) {
        return mNestedScrollingChildHelper.startNestedScroll(axes);
    }

    @Override
    public void stopNestedScroll() {
        mNestedScrollingChildHelper.stopNestedScroll();
    }

    @Override
    public boolean hasNestedScrollingParent() {
        return mNestedScrollingChildHelper.hasNestedScrollingParent();
    }

    @Override
    public boolean dispatchNestedScroll(int dxConsumed, int dyConsumed, int dxUnconsumed,
                                        int dyUnconsumed, int[] offsetInWindow) {
        return mNestedScrollingChildHelper.dispatchNestedScroll(dxConsumed, dyConsumed,
                dxUnconsumed, dyUnconsumed, offsetInWindow);
    }

    @Override
    public boolean dispatchNestedPreScroll(int dx, int dy, int[] consumed, int[] offsetInWindow) {
        return mNestedScrollingChildHelper.dispatchNestedPreScroll(
                dx, dy, consumed, offsetInWindow);
    }

    @Override
    public boolean onNestedPreFling(View target, float velocityX,
                                    float velocityY) {
        return dispatchNestedPreFling(velocityX, velocityY);
    }

    @Override
    public boolean onNestedFling(View target, float velocityX, float velocityY,
                                 boolean consumed) {
        return dispatchNestedFling(velocityX, velocityY, consumed);
    }

    @Override
    public boolean dispatchNestedFling(float velocityX, float velocityY, boolean consumed) {
        return mNestedScrollingChildHelper.dispatchNestedFling(velocityX, velocityY, consumed);
    }

    @Override
    public boolean dispatchNestedPreFling(float velocityX, float velocityY) {
        return mNestedScrollingChildHelper.dispatchNestedPreFling(velocityX, velocityY);
    }
}
