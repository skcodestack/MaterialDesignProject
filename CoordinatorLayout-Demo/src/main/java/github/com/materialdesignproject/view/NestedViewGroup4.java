//package github.com.materialdesignproject.view;
//
//import android.content.Context;
//import android.support.v4.view.MotionEventCompat;
//import android.support.v4.view.NestedScrollingChild;
//import android.support.v4.view.NestedScrollingChildHelper;
//import android.support.v4.view.NestedScrollingParent;
//import android.support.v4.view.NestedScrollingParentHelper;
//import android.support.v4.view.ViewCompat;
//import android.util.AttributeSet;
//import android.util.Log;
//import android.view.MotionEvent;
//import android.view.View;
//import android.view.ViewConfiguration;
//import android.view.ViewGroup;
//import android.widget.AbsListView;
//import android.widget.Scroller;
//
//import github.com.materialdesignproject.view.contain.DragListener;
//import github.com.materialdesignproject.view.contain.onLoadMoreListener;
//import github.com.materialdesignproject.view.contain.onRefreshListener;
//
///**
// * Email  1562363326@qq.com
// * Github https://github.com/skcodestack
// * Created by sk on 2017/12/11
// * Version  1.0
// * Description:
// */
//
//public class NestedViewGroup4 extends ViewGroup implements NestedScrollingParent, NestedScrollingChild {
//
//
//    private static final String LOG_TAG = "NestedViewGroup3";
//    private static final int DEFAULT_SWIPING_TO_REFRESH_TO_DEFAULT_SCROLLING_DURATION = 200;
//
//    private static final int DEFAULT_RELEASE_TO_REFRESHING_SCROLLING_DURATION = 200;
//
//    private static final int DEFAULT_REFRESH_COMPLETE_DELAY_DURATION = 300;
//
//    private static final int DEFAULT_REFRESH_COMPLETE_TO_DEFAULT_SCROLLING_DURATION = 500;
//
//    private static final int DEFAULT_DEFAULT_TO_REFRESHING_SCROLLING_DURATION = 500;
//
//    private static final int DEFAULT_SWIPING_TO_LOAD_MORE_TO_DEFAULT_SCROLLING_DURATION = 200;
//
//    private static final int DEFAULT_RELEASE_TO_LOADING_MORE_SCROLLING_DURATION = 200;
//
//    private static final int DEFAULT_LOAD_MORE_COMPLETE_DELAY_DURATION = 300;
//
//    private static final int DEFAULT_LOAD_MORE_COMPLETE_TO_DEFAULT_SCROLLING_DURATION = 300;
//
//    private static final int DEFAULT_DEFAULT_TO_LOADING_MORE_SCROLLING_DURATION = 300;
//
//
//
//    private int mSwipingToRefreshToDefaultScrollingDuration = DEFAULT_SWIPING_TO_REFRESH_TO_DEFAULT_SCROLLING_DURATION;
//
//    private int mReleaseToRefreshToRefreshingScrollingDuration = DEFAULT_RELEASE_TO_REFRESHING_SCROLLING_DURATION;
//
//    private int mRefreshCompleteDelayDuration = DEFAULT_REFRESH_COMPLETE_DELAY_DURATION;
//
//    private int mRefreshCompleteToDefaultScrollingDuration = DEFAULT_REFRESH_COMPLETE_TO_DEFAULT_SCROLLING_DURATION;
//
//    private int mDefaultToRefreshingScrollingDuration = DEFAULT_DEFAULT_TO_REFRESHING_SCROLLING_DURATION;
//
//    private int mReleaseToLoadMoreToLoadingMoreScrollingDuration = DEFAULT_RELEASE_TO_LOADING_MORE_SCROLLING_DURATION;
//
//
//    private int mLoadMoreCompleteDelayDuration = DEFAULT_LOAD_MORE_COMPLETE_DELAY_DURATION;
//
//
//    private int mLoadMoreCompleteToDefaultScrollingDuration = DEFAULT_LOAD_MORE_COMPLETE_TO_DEFAULT_SCROLLING_DURATION;
//
//
//    private int mSwipingToLoadMoreToDefaultScrollingDuration = DEFAULT_SWIPING_TO_LOAD_MORE_TO_DEFAULT_SCROLLING_DURATION;
//
//
//    private int mDefaultToLoadingMoreScrollingDuration = DEFAULT_DEFAULT_TO_LOADING_MORE_SCROLLING_DURATION;
//
//
//
//    private View mHeaderView;
//    private View mFooterView;
//    private int mRefreshTriggerOffset = 0;
//    private int mHeaderHeight = 0;
//
//    private View mTargetView;
//    private int mLoadMoreTriggerOffset = 0;
//    private int mFooterHeight = 0;
//    private boolean mHasHeaderView;
//    private boolean mHasFooterView;
//
//    int mHeaderOffset = 0;
//    int mTargetOffset = 0;
//    int mFooterOffset = 0;
//
//
//    private int mStatus = STATUS.STATUS_DEFAULT;
//    //滑动系数
//    private static final float DRAG_RATIO = 2;
//    //嵌套滑动
//    boolean mNestedScrollInProgress = false;
//    //最大下拉
//    private int mRefreshMaxDragOffset = 0;
//    private int mLoadMoreMaxDragOffset = 0;
//    private AutoScroller mAutoScroller;
//
//    public NestedViewGroup4(Context context) {
//        this(context,null);
//    }
//
//    public NestedViewGroup4(Context context, AttributeSet attrs) {
//        this(context, attrs,-1);
//    }
//
//    public NestedViewGroup4(Context context, AttributeSet attrs, int defStyleAttr) {
//        super(context, attrs, defStyleAttr);
//        init(context);
//    }
//
//    private int mTouchSlop;
//    private  NestedScrollingParentHelper mNestedScrollingParentHelper;
//    private  NestedScrollingChildHelper mNestedScrollingChildHelper;
//
//    private void init(Context context){
//
//        mTouchSlop = ViewConfiguration.get(context).getScaledTouchSlop();
//        mAutoScroller = new AutoScroller();
//        setWillNotDraw(false);
//
//        mNestedScrollingParentHelper = new NestedScrollingParentHelper(this);
//
//        mNestedScrollingChildHelper = new NestedScrollingChildHelper(this);
//        setNestedScrollingEnabled(true);
//
//
////        mHeaderView = LayoutInflater.from(getContext()).inflate(R.layout.custom_header_layout, null);
////        addView(mHeaderView);
////        mFooterView = LayoutInflater.from(getContext()).inflate(R.layout.custom_header_layout, null);
////        addView(mFooterView);
//
//    }
//
//    @Override
//    protected void onFinishInflate() {
//        super.onFinishInflate();
//        if(mTargetView == null) {
//            ensureTarget();
//        }
//        if(mTargetView == null){
//            return;
//        }
//        if(mHeaderView != null){
//            mHeaderView.setVisibility(View.GONE);
//        }
//
//        if(mFooterView != null){
//            mFooterView.setVisibility(View.GONE);
//        }
//
//    }
//
//    public void setStatus(int status) {
//        this.mStatus = status;
//    }
//
//    /**
//     * TODO add gravity
//     * LayoutParams of RefreshLoadMoreLayout
//     */
//    public static class LayoutParams extends MarginLayoutParams {
//
//        public LayoutParams(Context c, AttributeSet attrs) {
//            super(c, attrs);
//        }
//
//        public LayoutParams(int width, int height) {
//            super(width, height);
//        }
//
//        public LayoutParams(MarginLayoutParams source) {
//            super(source);
//        }
//
//        public LayoutParams(ViewGroup.LayoutParams source) {
//            super(source);
//        }
//    }
//    /**
//     * {@inheritDoc}
//     */
//    @Override
//    protected ViewGroup.LayoutParams generateDefaultLayoutParams() {
//        return new NestedViewGroup4.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
//    }
//    /**
//     * {@inheritDoc}
//     */
//    @Override
//    protected ViewGroup.LayoutParams generateLayoutParams(ViewGroup.LayoutParams p) {
//        return new NestedViewGroup4.LayoutParams(p);
//    }
//    /**
//     * {@inheritDoc}
//     */
//    @Override
//    public ViewGroup.LayoutParams generateLayoutParams(AttributeSet attrs) {
//        return new NestedViewGroup4.LayoutParams(getContext(), attrs);
//    }
//
//    @Override
//    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
////        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
//
//        // header
//        if (mHeaderView != null) {
//            final View headerView = mHeaderView;
//            measureChildWithMargins(headerView, widthMeasureSpec, 0, heightMeasureSpec, 0);
//            MarginLayoutParams lp = ((MarginLayoutParams) headerView.getLayoutParams());
//            mHeaderHeight = headerView.getMeasuredHeight() + lp.topMargin + lp.bottomMargin;
//
//            mRefreshTriggerOffset = ((DragListener) mHeaderView).getDragTriggerOffset(this);
//            mRefreshMaxDragOffset = ((DragListener) mHeaderView).getDragMaxOffset(this);
//
//            if (mRefreshTriggerOffset <= 0) {
//                mRefreshTriggerOffset = mHeaderHeight;
//            }
//            if(mRefreshMaxDragOffset < mHeaderHeight){
//               mRefreshMaxDragOffset = mHeaderHeight * 2;
//            }
//        }
//        // target
//        if (mTargetView != null) {
//            final View targetView = mTargetView;
//            measureChildWithMargins(targetView, widthMeasureSpec, 0, heightMeasureSpec, 0);
//        }
//        // footer
//        if (mFooterView != null) {
//            final View footerView = mFooterView;
//            measureChildWithMargins(footerView, widthMeasureSpec, 0, heightMeasureSpec, 0);
//            MarginLayoutParams lp = ((MarginLayoutParams) footerView.getLayoutParams());
//            mFooterHeight = footerView.getMeasuredHeight() + lp.topMargin + lp.bottomMargin;
//
//            mLoadMoreTriggerOffset = ((DragListener) mFooterView).getDragTriggerOffset(this);
//            mLoadMoreMaxDragOffset = ((DragListener) mFooterView).getDragMaxOffset(this);
//
//            if (mLoadMoreTriggerOffset <= 0) {
//                mLoadMoreTriggerOffset = mFooterHeight;
//            }
//
//            if(mLoadMoreMaxDragOffset < mFooterHeight){
//                mLoadMoreMaxDragOffset = mFooterHeight * 2;
//            }
//        }
//
//        setMeasuredDimension(widthMeasureSpec, heightMeasureSpec);
//    }
//
//    @Override
//    protected void onLayout(boolean changed, int l, int t, int r, int b) {
//        layoutChildren();
//
//        mHasHeaderView = (mHeaderView != null);
//        mHasFooterView =  (mFooterView != null);
//    }
//
//
//    private void layoutChildren() {
//
//        final int width = getMeasuredWidth();
//        final int height = getMeasuredHeight();
//
//        final int paddingLeft = getPaddingLeft();
//        final int paddingTop = getPaddingTop();
//        final int paddingRight = getPaddingRight();
//        final int paddingBottom = getPaddingBottom();
//
//        if (mTargetView == null) {
//            return;
//        }
//
//
//        if (mHeaderView != null) {
//            final View headerView = mHeaderView;
//            MarginLayoutParams lp = (MarginLayoutParams) headerView.getLayoutParams();
//            final int headerLeft = paddingLeft + lp.leftMargin;
//            final int headerTop;
//            headerTop = paddingTop + lp.topMargin - mHeaderHeight + mHeaderOffset;
//
//            final int headerRight = headerLeft + headerView.getMeasuredWidth();
//            final int headerBottom = headerTop + headerView.getMeasuredHeight();
//            headerView.layout(headerLeft, headerTop, headerRight, headerBottom);
//        }
//
//        // layout target
//        if (mTargetView != null) {
//            final View targetView = mTargetView;
//            MarginLayoutParams lp = (MarginLayoutParams) targetView.getLayoutParams();
//            final int targetLeft = paddingLeft + lp.leftMargin;
//            final int targetTop;
//
//            targetTop = paddingTop + lp.topMargin + mTargetOffset;
//
//            final int targetRight = targetLeft + targetView.getMeasuredWidth();
//            final int targetBottom = targetTop + targetView.getMeasuredHeight();
//            targetView.layout(targetLeft, targetTop, targetRight, targetBottom);
//        }
//
//
//        // layout footer
//        if (mFooterView != null) {
//            final View footerView = mFooterView;
//            MarginLayoutParams lp = (MarginLayoutParams) footerView.getLayoutParams();
//            final int footerLeft = paddingLeft + lp.leftMargin;
//            final int footerBottom;
//
//            footerBottom = height - paddingBottom - lp.bottomMargin + mFooterHeight + mFooterOffset;
//
//
//            final int footerTop = footerBottom - footerView.getMeasuredHeight();
//            final int footerRight = footerLeft + footerView.getMeasuredWidth();
//
//            footerView.layout(footerLeft, footerTop, footerRight, footerBottom);
//        }
//
//
//        if (mHeaderView != null) {
//            mHeaderView.bringToFront();
//        }
//        if (mFooterView != null) {
//            mFooterView.bringToFront();
//        }
//    }
//
//    /**
//     * 更新滑动状态
//     */
//    private void updateScrollStatus() {
//
//        if(mTargetOffset >mTouchSlop && onCheckCanRefresh()){
//            //下拉
//            if (mTargetOffset >= mRefreshTriggerOffset) {
//                setStatus(STATUS.STATUS_RELEASE_TO_REFRESH);
//            }else {
//                setStatus(STATUS.STATUS_SWIPING_TO_REFRESH);
//            }
//            mRefreshCallback.onPrepare();
//        }else if(mTargetOffset < -mTouchSlop && onCheckCanLoadMore()){
//            //上拉
//            if (-mTargetOffset >= mLoadMoreTriggerOffset) {
//                setStatus(STATUS.STATUS_RELEASE_TO_LOAD_MORE);
//            } else {
//                setStatus(STATUS.STATUS_SWIPING_TO_LOAD_MORE);
//            }
//            mLoadMoreCallback.onPrepare();
//        } else if(Math.abs(mTargetOffset) < mTouchSlop){
//            setStatus(STATUS.STATUS_DEFAULT);
//        }
//    }
//
//    private boolean onCheckCanLoadMore() {
//        return mLoadMoreEnabled && !canChildScrollDown() && mHasFooterView && mLoadMoreTriggerOffset > 0;
//    }
//
//    private boolean onCheckCanRefresh() {
//        return mRefreshEnabled && !canChildScrollUp() && mHasHeaderView && mRefreshTriggerOffset > 0;
//    }
//
//    /**
//     * on active finger up
//     */
//    private void finishSpinner() {
//
//        updateScrollStatus();
//
//        if (STATUS.isSwipingToRefresh(mStatus)) {
//            // simply return
//            scrollSwipingToRefreshToDefault();
//
//        } else if (STATUS.isSwipingToLoadMore(mStatus)) {
//            // simply return
//            scrollSwipingToLoadMoreToDefault();
//
//        } else if (STATUS.isReleaseToRefresh(mStatus)) {
//            // return to header height and perform refresh
//            mRefreshCallback.onRelease();
//            scrollReleaseToRefreshToRefreshing();
//
//        } else if (STATUS.isReleaseToLoadMore(mStatus)) {
//            // return to footer height and perform loadMore
//            mLoadMoreCallback.onRelease();
//            scrollReleaseToLoadMoreToLoadingMore();
//
//        }
//    }
//
//    /**
//     * finger Scroll 滑动
//     * @param yDiff
//     */
//    private void fingerScroll(final float yDiff) {
//
//        updateScrollStatus();
//
//        float ratio = DRAG_RATIO;
//        float yScrolled = yDiff / ratio;
//
//
//        float tmpTargetOffset = yScrolled + mTargetOffset;
//        if ((tmpTargetOffset > 0 && mTargetOffset < 0)
//                || (tmpTargetOffset < 0 && mTargetOffset > 0)) {
//            yScrolled = -mTargetOffset;
//        }
//
//
//        if (mRefreshMaxDragOffset >= mRefreshTriggerOffset && tmpTargetOffset > mRefreshMaxDragOffset) {
//            yScrolled = mRefreshMaxDragOffset - mTargetOffset;
//        } else if (mLoadMoreMaxDragOffset >= mLoadMoreTriggerOffset && -tmpTargetOffset > mLoadMoreMaxDragOffset) {
//            yScrolled = -mLoadMoreMaxDragOffset - mTargetOffset;
//        }
//        if (STATUS.isRefreshStatus(mStatus)) {
//            mRefreshCallback.onDrag(mTargetOffset,mRefreshTriggerOffset);
//        } else if (STATUS.isLoadMoreStatus(mStatus)) {
//            mLoadMoreCallback.onDrag(mTargetOffset,mLoadMoreTriggerOffset);
//        }
//        updateScroll(yScrolled);
//    }
//
//    private void updateScroll(final float yScrolled) {
//        if (yScrolled == 0) {
//            return;
//        }
//        mTargetOffset += yScrolled;
//
//        if (STATUS.isRefreshStatus(mStatus)) {
//            mHeaderOffset = mTargetOffset;
//            mFooterOffset = 0;
//        } else if (STATUS.isLoadMoreStatus(mStatus)) {
//            mFooterOffset = mTargetOffset;
//            mHeaderOffset = 0;
//        }
//
//
//        layoutChildren();
//        invalidate();
//    }
//
//
//    private void autoScroll(final float yScrolled) {
//
//
//        if (STATUS.isRefreshStatus(mStatus)) {
//            mRefreshCallback.onDrag(mTargetOffset,mRefreshTriggerOffset);
//        } else if (STATUS.isLoadMoreStatus(mStatus)) {
//            mLoadMoreCallback.onDrag(mTargetOffset,mLoadMoreTriggerOffset);
//        }
//
//        updateScroll(yScrolled);
//    }
//
//    private void fixCurrentStatusLayout() {
//        mIsMyControl = false;
//        if (STATUS.isRefreshing(mStatus)) {
//            mTargetOffset = (int) (mRefreshTriggerOffset + 0.5f);
//            mHeaderOffset = mTargetOffset;
//            mFooterOffset = 0;
//            layoutChildren();
//            invalidate();
//        } else if (STATUS.isStatusDefault(mStatus)) {
//            mTargetOffset = 0;
//            mHeaderOffset = 0;
//            mFooterOffset = 0;
//            layoutChildren();
//            invalidate();
//        } else if (STATUS.isLoadingMore(mStatus)) {
//            mTargetOffset = -(int) (mLoadMoreTriggerOffset + 0.5f);
//            mHeaderOffset = 0;
//            mFooterOffset = mTargetOffset;
//            layoutChildren();
//            invalidate();
//        }
//    }
//
//
//    private void scrollDefaultToRefreshing() {
//        mAutoScroller.autoScroll((int) (mRefreshTriggerOffset + 0.5f), mDefaultToRefreshingScrollingDuration);
//    }
//
//    private void scrollDefaultToLoadingMore() {
//        mAutoScroller.autoScroll(-(int) (mLoadMoreTriggerOffset + 0.5f), mDefaultToLoadingMoreScrollingDuration);
//    }
//
//    private void scrollSwipingToRefreshToDefault() {
//        mAutoScroller.autoScroll(-mHeaderOffset, mSwipingToRefreshToDefaultScrollingDuration);
//    }
//
//    private void scrollSwipingToLoadMoreToDefault() {
//        mAutoScroller.autoScroll(-mFooterOffset, mSwipingToLoadMoreToDefaultScrollingDuration);
//    }
//
//    private void scrollReleaseToRefreshToRefreshing() {
//        mAutoScroller.autoScroll(mHeaderHeight - mHeaderOffset, mReleaseToRefreshToRefreshingScrollingDuration);
//    }
//
//    private void scrollReleaseToLoadMoreToLoadingMore() {
//        mAutoScroller.autoScroll(-mFooterOffset - mFooterHeight, mReleaseToLoadMoreToLoadingMoreScrollingDuration);
//    }
//
//    private void scrollRefreshingToDefault() {
//        mAutoScroller.autoScroll(-mHeaderOffset, mRefreshCompleteToDefaultScrollingDuration);
//    }
//
//    private void scrollLoadingMoreToDefault() {
//        mAutoScroller.autoScroll(-mFooterOffset, mLoadMoreCompleteToDefaultScrollingDuration);
//    }
//
//    private void autoScrollFinished() {
//        int mLastStatus = mStatus;
//        mIsMyControl = false;
//        if (STATUS.isReleaseToRefresh(mStatus)) {
//            setStatus(STATUS.STATUS_REFRESHING);
//            fixCurrentStatusLayout();
//            mRefreshCallback.onRefresh();
//
//        } else if (STATUS.isRefreshing(mStatus)) {
//            setStatus(STATUS.STATUS_DEFAULT);
//            fixCurrentStatusLayout();
//            mRefreshCallback.onReset();
//
//        } else if (STATUS.isStatusDefault(mStatus)) {
//            fixCurrentStatusLayout();
//            mRefreshCallback.onReset();
//
//        } else if (STATUS.isLoadingMore(mStatus)) {
//            setStatus(STATUS.STATUS_DEFAULT);
//            fixCurrentStatusLayout();
//            mLoadMoreCallback.onReset();
//        } else if (STATUS.isReleaseToLoadMore(mStatus)) {
//            setStatus(STATUS.STATUS_LOADING_MORE);
//            fixCurrentStatusLayout();
//            mLoadMoreCallback.onLoadMore();
//        } else {
//            //throw new IllegalStateException("illegal state: " + (mStatus));
//        }
//
//    }
//
//    private class AutoScroller implements Runnable {
//
//        private Scroller mScroller;
//
//        private int mmLastY;
//
//        private boolean mRunning = false;
//
//        private boolean mAbort = false;
//
//        public AutoScroller() {
//            mScroller = new Scroller(getContext());
//        }
//
//        @Override
//        public void run() {
//            boolean finish = !mScroller.computeScrollOffset() || mScroller.isFinished();
//            int currY = mScroller.getCurrY();
//            int yDiff = currY - mmLastY;
//            if (finish) {
//                finish();
//            } else {
//                mmLastY = currY;
//                NestedViewGroup4.this.autoScroll(yDiff);
//                post(this);
//            }
//        }
//
//        /**
//         * remove the post callbacks and reset default values
//         */
//        private void finish() {
//            mmLastY = 0;
//            mRunning = false;
//            removeCallbacks(this);
//            // if abort by user, don't call
//            if (!mAbort) {
//                NestedViewGroup4.this.autoScrollFinished();
//            }
//        }
//
//        /**
//         * abort scroll if it is scrolling
//         */
//        public void abortIfRunning() {
//            if (mRunning) {
//                if (!mScroller.isFinished()) {
//                    mAbort = true;
//                    mScroller.forceFinished(true);
//                }
//                finish();
//                mAbort = false;
//            }
//        }
//
//        /**
//         * The param yScrolled here isn't final pos of y.
//         * It's just like the yScrolled param in the
//         * {@link #updateScroll(float yScrolled)}
//         *
//         * @param yScrolled
//         * @param duration
//         */
//        private void autoScroll(int yScrolled, int duration) {
//            removeCallbacks(this);
//            mmLastY = 0;
//            if (!mScroller.isFinished()) {
//                mScroller.forceFinished(true);
//            }
//            mScroller.startScroll(0, 0, 0, yScrolled, duration);
//            post(this);
//            mRunning = true;
//        }
//    }
//
//
//    @Override
//    public boolean onTouchEvent(MotionEvent ev) {
//        ensureTarget();
//        if(STATUS.isRefreshing(mStatus) ||  STATUS.isLoadingMore(mStatus) || mNestedScrollInProgress){
//            return false;
//        }
//
//        dealMulTouchEvent(ev);
//        mAutoScroller.abortIfRunning();
//
//
//        final int action = MotionEventCompat.getActionMasked(ev);
//        switch (action) {
//            case MotionEvent.ACTION_DOWN:
//                mIsBeingDragged = false;
//                break;
//            case MotionEvent.ACTION_MOVE: {
//
//                mIsBeingDragged = isMyControlScroll();
//                if (mIsBeingDragged) {
//                    fingerScroll(-Overdy);
//                }else {
//                    if(Overdy != 0) {
//                        fixCurrentStatusLayout();
////                    //把滚动事件交给内部控件处理
//                        ev.setAction(MotionEvent.ACTION_DOWN);
//                        dispatchTouchEvent(ev);
//                        mIsMyControl = false;
//                    }
//                }
//                break;
//            }
//            case MotionEventCompat.ACTION_POINTER_DOWN: {
//
//                break;
//            }
//            case MotionEventCompat.ACTION_POINTER_UP:
//                break;
//
//            case MotionEvent.ACTION_UP: {
//
//                if (mIsBeingDragged) {
//                    mIsBeingDragged = false;
//                    //结束
//                    finishSpinner();
//                }
//                return false;
//            }
//            case MotionEvent.ACTION_CANCEL:
//                return false;
//        }
//
//        return mIsBeingDragged;
//    }
//
//
//    boolean mIsBeingDragged = false;
//    //是否由本控件控制活动操作
//    boolean mIsMyControl = false;
//    @Override
//    public boolean onInterceptTouchEvent(MotionEvent ev) {
//
//        ensureTarget();
//        if(STATUS.isRefreshing(mStatus) ||  STATUS.isLoadingMore(mStatus) || mNestedScrollInProgress){
//            return false;
//        }
//        dealMulTouchEvent(ev);
//
//        mAutoScroller.abortIfRunning();
//
//        final int action = MotionEventCompat.getActionMasked(ev);
//        switch (action) {
//            case MotionEvent.ACTION_DOWN:
//                mIsBeingDragged = false;
//                break;
//            case MotionEvent.ACTION_MOVE:
//
//                mIsBeingDragged = isMyControlScroll();
//                break;
//            case MotionEventCompat.ACTION_POINTER_UP:
//                break;
//
//            case MotionEvent.ACTION_UP:
//            case MotionEvent.ACTION_CANCEL:
//                mIsBeingDragged = false;
//                break;
//        }
//        return mIsBeingDragged;
//    }
//
//
//
//
////    @Override
////    public boolean dispatchTouchEvent(MotionEvent ev) {
////        ensureTarget();
////        if(STATUS.isRefreshing(mStatus) ||  STATUS.isLoadingMore(mStatus) || mNestedScrollInProgress){
////            return super.dispatchTouchEvent(ev);
////        }
////        dealMulTouchEvent(ev);
////        mAutoScroller.abortIfRunning();
////
////        final int action = MotionEventCompat.getActionMasked(ev);
////        switch (action) {
////            case MotionEvent.ACTION_MOVE:
////
////                mIsBeingDragged = isMyControlScroll();
//////                Log.e(LOG_TAG,"onTouchEvent===>mIsBeingDragged:"+mIsBeingDragged+",mIsMyControl:"+mIsMyControl);
//////                if(mIsBeingDragged && !mIsMyControl){
////////                    //把内部控件的事件转发给本控件处理
//////                    mIsMyControl = true;
//////                    ev.setAction(MotionEvent.ACTION_CANCEL);
//////                    MotionEvent ev2 = MotionEvent.obtain(ev);
//////                    dispatchTouchEvent(ev);
//////                    ev2.setAction(MotionEvent.ACTION_DOWN);
//////                    return dispatchTouchEvent(ev2);
//////
//////                }
////
////                break;
////        }
////        return super.dispatchTouchEvent(ev);
////    }
//
//    private boolean  isMyControlScroll(){
//        if(Math.abs(Overdy) < Math.abs(Overdx) ){
//            return mIsBeingDragged;
//        }
//        if((-Overdy > 0 && onCheckCanRefresh()) || (onCheckCanRefresh() && mTargetOffset-Overdy > 0)){
//            return true;
//
//        }else if((-Overdy < 0 && onCheckCanLoadMore()) || (onCheckCanLoadMore() && mTargetOffset-Overdy < 0)){
//            return true;
//        }
//
//        return false;
//    }
//
//
//    /**
//     * 处理多点触控的情况，准确地计算Y坐标和移动距离dy
//     * 同时兼容单点触控的情况
//     */
//    private int mActivePointerId = MotionEvent.INVALID_POINTER_ID;
//    //储存上次的Y坐标
//    private float mLastY;
//    private float mLastX;
//    //记录单次滚动x,y轴偏移量
//    private float Overdy;
//    private float Overdx;
//
//
//    public void dealMulTouchEvent(MotionEvent ev) {
//        final int action = MotionEventCompat.getActionMasked(ev);
//        switch (action) {
//            case MotionEvent.ACTION_DOWN: {
//                final int pointerIndex = MotionEventCompat.getActionIndex(ev);
//                final float x = MotionEventCompat.getX(ev, pointerIndex);
//                final float y = MotionEventCompat.getY(ev, pointerIndex);
//                mLastX = x;
//                mLastY = y;
//                mActivePointerId = MotionEventCompat.getPointerId(ev, 0);
//                break;
//            }
//            case MotionEvent.ACTION_MOVE: {
//                final int pointerIndex = MotionEventCompat.findPointerIndex(ev, mActivePointerId);
//                final float x = MotionEventCompat.getX(ev, pointerIndex);
//                final float y = MotionEventCompat.getY(ev, pointerIndex);
//                Overdx = mLastX -x ;
//                Overdy = mLastY - y;
//                mLastY = y;
//                mLastX = x;
//                break;
//            }
//            case MotionEvent.ACTION_UP:
//            case MotionEvent.ACTION_CANCEL:
//                mActivePointerId = MotionEvent.INVALID_POINTER_ID;
//                break;
//            case MotionEvent.ACTION_POINTER_DOWN: {
//                final int pointerIndex = MotionEventCompat.getActionIndex(ev);
//                final int pointerId = MotionEventCompat.getPointerId(ev, pointerIndex);
//                if (pointerId != mActivePointerId) {
//                    mLastX = MotionEventCompat.getX(ev, pointerIndex);
//                    mLastY = MotionEventCompat.getY(ev, pointerIndex);
//                    mActivePointerId = MotionEventCompat.getPointerId(ev, pointerIndex);
//                }
//                break;
//            }
//            case MotionEvent.ACTION_POINTER_UP: {
//                final int pointerIndex = MotionEventCompat.getActionIndex(ev);
//                final int pointerId = MotionEventCompat.getPointerId(ev, pointerIndex);
//                if (pointerId == mActivePointerId) {
//                    final int newPointerIndex = pointerIndex == 0 ? 1 : 0;
//                    mLastX = MotionEventCompat.getX(ev, newPointerIndex);
//                    mLastY = MotionEventCompat.getY(ev, newPointerIndex);
//                    mActivePointerId = MotionEventCompat.getPointerId(ev, newPointerIndex);
//                }
//                break;
//            }
//        }
//    }
//
//
//
//
//    private void ensureTarget() {
//        // Don't bother getting the parent height if the parent hasn't been laid
//        // out yet.
//        if (mTargetView == null) {
//            for (int i = 0; i < getChildCount(); i++) {
//                View child = getChildAt(i);
//                if (!child.equals(mHeaderView) && !child.equals(mFooterView)) {
//                    mTargetView = child;
//                    break;
//                }
//            }
//        }
//    }
//
//
//    /**
//     * copy from {@link android.support.v4.widget.SwipeRefreshLayout#canChildScrollUp()}
//     *
//     * @return Whether it is possible for the child view of this layout to
//     * scroll up. Override this if the child view is a custom view.
//     */
//    protected boolean canChildScrollUp() {
//        if (android.os.Build.VERSION.SDK_INT < 14) {
//            if (mTargetView instanceof AbsListView) {
//                final AbsListView absListView = (AbsListView) mTargetView;
//                return absListView.getChildCount() > 0
//                        && (absListView.getFirstVisiblePosition() > 0 || absListView.getChildAt(0)
//                        .getTop() < absListView.getPaddingTop());
//            } else {
//                return ViewCompat.canScrollVertically(mTargetView, -1) || mTargetView.getScrollY() > 0;
//            }
//        } else {
//            return ViewCompat.canScrollVertically(mTargetView, -1);
//        }
//    }
//
//    /**
//     * Whether it is possible for the child view of this layout to
//     * scroll down. Override this if the child view is a custom view.
//     *
//     * @return
//     */
//    protected boolean canChildScrollDown() {
//        if (android.os.Build.VERSION.SDK_INT < 14) {
//            if (mTargetView instanceof AbsListView) {
//                final AbsListView absListView = (AbsListView) mTargetView;
//                return absListView.getChildCount() > 0
//                        && (absListView.getLastVisiblePosition() < absListView.getChildCount() - 1
//                        || absListView.getChildAt(absListView.getChildCount() - 1).getBottom() > absListView.getPaddingBottom());
//            } else {
//                return ViewCompat.canScrollVertically(mTargetView, 1) || mTargetView.getScrollY() < 0;
//            }
//        } else {
//            return ViewCompat.canScrollVertically(mTargetView, 1);
//        }
//    }
//
//
//    public void setRefreshHeaderView(View view) {
//        if (view instanceof DragListener) {
//            if (mHeaderView != null && mHeaderView != view) {
//                removeView(mHeaderView);
//            }
//            if (mHeaderView != view) {
//                this.mHeaderView = view;
//                addView(view);
//            }
//        } else {
//            Log.e(LOG_TAG, "Refresh header view must be an implement of SwipeRefreshTrigger");
//        }
//    }
//
//    /**
//     * set load more footer view, the view must at least be an implement of SwipeLoadTrigger
//     * the view can also implement {@code SwipeTrigger} for more extension functions
//     *
//     * @param view
//     */
//    public void setLoadMoreFooterView(View view) {
//        if (view instanceof DragListener) {
//            if (mFooterView != null && mFooterView != view) {
//                removeView(mFooterView);
//            }
//            if (mFooterView != view) {
//                this.mFooterView = view;
//                addView(mFooterView);
//            }
//        } else {
//            Log.e(LOG_TAG, "Load more footer view must be an implement of SwipeLoadTrigger");
//        }
//    }
//
//    boolean mRefreshEnabled = true;
//    boolean mLoadMoreEnabled = true;
//
//
//    public boolean isRefreshEnabled() {
//        return mRefreshEnabled;
//    }
//
//    /**
//     * switch refresh function on or off
//     *
//     * @param enable
//     */
//    public void setRefreshEnabled(boolean enable) {
//        this.mRefreshEnabled = enable;
//    }
//
//    /**
//     * is load more function is enabled
//     *
//     * @return
//     */
//    public boolean isLoadMoreEnabled() {
//        return mLoadMoreEnabled;
//    }
//
//    /**
//     * switch load more function on or off
//     *
//     * @param enable
//     */
//    public void setLoadMoreEnabled(boolean enable) {
//        this.mLoadMoreEnabled = enable;
//    }
//
//
//    public void setRefreshing(boolean refreshing) {
//        if (!isRefreshEnabled() || mHeaderView == null) {
//            return;
//        }
//        if (refreshing) {
//            if (STATUS.isStatusDefault(mStatus)) {
//                setStatus(STATUS.STATUS_RELEASE_TO_REFRESH);
//                scrollDefaultToRefreshing();
//            }
//        } else {
//            if (STATUS.isRefreshing(mStatus)) {
//                mRefreshCallback.onComplete();
//                postDelayed(new Runnable() {
//                    @Override
//                    public void run() {
//                        scrollRefreshingToDefault();
//                    }
//                }, mRefreshCompleteDelayDuration);
//            }
//        }
//    }
//
//    /**
//     * auto loading more or cancel
//     *
//     * @param loadingMore
//     */
//    public void setLoadingMore(boolean loadingMore) {
//        if (!isLoadMoreEnabled() || mFooterView == null) {
//            return;
//        }
//        if (loadingMore) {
//            if (STATUS.isStatusDefault(mStatus)) {
//                setStatus(STATUS.STATUS_SWIPING_TO_LOAD_MORE);
//                scrollDefaultToLoadingMore();
//            }
//        } else {
//            if (STATUS.isLoadingMore(mStatus)) {
//                mLoadMoreCallback.onComplete();
//                postDelayed(new Runnable() {
//                    @Override
//                    public void run() {
//                        scrollLoadingMoreToDefault();
//                    }
//                }, mLoadMoreCompleteDelayDuration);
//            }
//        }
//    }
//
//
//
//
//
//    // NestedScrollingParent
//
//    @Override
//    public boolean onStartNestedScroll(View child, View target, int nestedScrollAxes) {
//        return isEnabled() && !STATUS.isRefreshing(mStatus) &&  !STATUS.isLoadingMore(mStatus)
//                && (nestedScrollAxes & ViewCompat.SCROLL_AXIS_VERTICAL) != 0;
//    }
//
//    @Override
//    public void onNestedScrollAccepted(View child, View target, int axes) {
//        // Reset the counter of how much leftover scroll needs to be consumed.
//        mNestedScrollingParentHelper.onNestedScrollAccepted(child, target, axes);
////        // Dispatch up to the nested parent
//        startNestedScroll(axes & ViewCompat.SCROLL_AXIS_VERTICAL);
////        mTotalUnconsumed = 0;
//        mNestedScrollInProgress = true;
//    }
//
//
//
//
//    private final int[] mParentScrollConsumed = new int[2];
//    private final int[] mParentOffsetInWindow = new int[2];
//    @Override
//    public void onNestedPreScroll(View target, int dx, int dy, int[] consumed) {
//
//        //dy > 0  手指向上滑动
//        if(dy > 0 && mTargetOffset > 0){
//            if(dy > mTargetOffset){
//                consumed[1] = mTargetOffset;
//            }else {
//                consumed[1] = dy;
//            }
//            Log.e(LOG_TAG,"DY > 0");
//            fingerScroll(-consumed[1]);
//        }else if(dy < 0 &&  mTargetOffset < 0){
//            //dy < 0  手指向下滑动
//            Log.e(LOG_TAG,"DY < 0");
//            if(dy < mTargetOffset){
//                consumed[1] = mTargetOffset;
//            }else {
//                consumed[1] = dy;
//            }
//            fingerScroll(-consumed[1]);
//        }
//
//
//
//        final int[] parentConsumed = mParentScrollConsumed;
//        if (dispatchNestedPreScroll(dx - consumed[0], dy - consumed[1], parentConsumed, null)) {
//            consumed[0] += parentConsumed[0];
//            consumed[1] += parentConsumed[1];
//        }
//    }
//
//    @Override
//    public int getNestedScrollAxes() {
//        return mNestedScrollingParentHelper.getNestedScrollAxes();
//    }
//
//    @Override
//    public void onStopNestedScroll(View target) {
//        mNestedScrollingParentHelper.onStopNestedScroll(target);
//        mNestedScrollInProgress = false;
//
//
//        finishSpinner();
////        // Finish the spinner for nested scrolling if we ever consumed any
////        // Dispatch up our nested parent
//        stopNestedScroll();
//    }
//
//    @Override
//    public void onNestedScroll(final View target, final int dxConsumed, final int dyConsumed,
//                               final int dxUnconsumed, final int dyUnconsumed) {
//        // Dispatch up to the nested parent first
//        dispatchNestedScroll(dxConsumed, dyConsumed, dxUnconsumed, dyUnconsumed,
//                mParentOffsetInWindow);
//
//        final int dy = dyUnconsumed + mParentOffsetInWindow[1];
//
//        if(dy < 0 && onCheckCanRefresh()) {
//            fingerScroll(-dy);
//        }else if(dy > 0 && onCheckCanLoadMore()){
//           fingerScroll(-dy);
//        }
//    }
//
//
////    public boolean onNestedPreFling(View target, float velocityX, float velocityY){
////        //dy > 0  手指向上滑动
////        if(velocityY > 0 && mTargetOffset > 0){
////
////            return true;
////        }else if(velocityY < 0 &&  mTargetOffset < 0){
////            //dy < 0  手指向下滑动
////           return true;
////
////        }
////        return false;
////    }
//
//    // NestedScrollingChild
//
//    @Override
//    public void setNestedScrollingEnabled(boolean enabled) {
//        mNestedScrollingChildHelper.setNestedScrollingEnabled(enabled);
//    }
//
//    @Override
//    public boolean isNestedScrollingEnabled() {
//        return mNestedScrollingChildHelper.isNestedScrollingEnabled();
//    }
//
//    @Override
//    public boolean startNestedScroll(int axes) {
//        return mNestedScrollingChildHelper.startNestedScroll(axes);
//    }
//
//    @Override
//    public void stopNestedScroll() {
//        mNestedScrollingChildHelper.stopNestedScroll();
//    }
//
//    @Override
//    public boolean hasNestedScrollingParent() {
//        return mNestedScrollingChildHelper.hasNestedScrollingParent();
//    }
//
//    @Override
//    public boolean dispatchNestedScroll(int dxConsumed, int dyConsumed, int dxUnconsumed,
//                                        int dyUnconsumed, int[] offsetInWindow) {
//        return mNestedScrollingChildHelper.dispatchNestedScroll(dxConsumed, dyConsumed,
//                dxUnconsumed, dyUnconsumed, offsetInWindow);
//    }
//
//    @Override
//    public boolean dispatchNestedPreScroll(int dx, int dy, int[] consumed, int[] offsetInWindow) {
//
//        return mNestedScrollingChildHelper.dispatchNestedPreScroll(
//                dx, dy, consumed, offsetInWindow);
//    }
//
//    @Override
//    public boolean onNestedPreFling(View target, float velocityX,
//                                    float velocityY) {
//
//        return dispatchNestedPreFling(velocityX, velocityY);
//    }
//
//    @Override
//    public boolean onNestedFling(View target, float velocityX, float velocityY,
//                                 boolean consumed) {
//        return dispatchNestedFling(velocityX, velocityY, consumed);
//    }
//
//    @Override
//    public boolean dispatchNestedFling(float velocityX, float velocityY, boolean consumed) {
//        return mNestedScrollingChildHelper.dispatchNestedFling(velocityX, velocityY, consumed);
//    }
//
//    @Override
//    public boolean dispatchNestedPreFling(float velocityX, float velocityY) {
//        return mNestedScrollingChildHelper.dispatchNestedPreFling(velocityX, velocityY);
//    }
//
//
//    abstract   class onRefreshCallback implements DragListener,onRefreshListener {
//
//    }
//    abstract   class onLoadMoreCallback implements DragListener,onLoadMoreListener {
//
//    }
//
//    onRefreshCallback mRefreshCallback = new onRefreshCallback() {
//        @Override
//        public void onPrepare() {
//            if (mHeaderView != null && mHeaderView instanceof DragListener && STATUS.isStatusDefault(mStatus)) {
//                mHeaderView.setVisibility(VISIBLE);
//                ((DragListener) mHeaderView).onPrepare();
//            }
//        }
//
//        @Override
//        public void onDrag(int y,int offset) {
//            if (mHeaderView != null && mHeaderView instanceof DragListener && STATUS.isRefreshStatus(mStatus)) {
//                if (mHeaderView.getVisibility() != VISIBLE) {
//                    mHeaderView.setVisibility(VISIBLE);
//                }
//                ((DragListener) mHeaderView).onDrag(y,offset);
//            }
//        }
//
//        @Override
//        public void onRelease() {
//            if (mHeaderView != null && mHeaderView instanceof DragListener && STATUS.isReleaseToRefresh(mStatus)) {
//                ((DragListener) mHeaderView).onRelease();
//            }
//        }
//
//        @Override
//        public void onRefresh() {
//            if (mHeaderView != null && STATUS.isRefreshing(mStatus)) {
//                if(mHeaderView instanceof onRefreshListener){
//                    ((onRefreshListener)mHeaderView).onRefresh();
//                }
//                if (mRefreshListener != null) {
//                    mRefreshListener.onRefresh();
//                }
//            }
//        }
//
//        @Override
//        public void onComplete() {
//            if (mHeaderView != null && mHeaderView instanceof DragListener) {
//                ((DragListener) mHeaderView).onComplete();
//            }
//        }
//
//        @Override
//        public void onReset() {
//            if (mHeaderView != null && mHeaderView instanceof DragListener && STATUS.isStatusDefault(mStatus)) {
//                ((DragListener) mHeaderView).onReset();
//                mHeaderView.setVisibility(GONE);
//            }
//        }
//
//        @Override
//        public int getDragMaxOffset(View rootView) {
//            if (mHeaderView != null && mHeaderView instanceof DragListener ) {
//                return ((DragListener) mHeaderView).getDragMaxOffset(rootView);
//            }
//            return 0;
//        }
//
//        @Override
//        public int getDragTriggerOffset(View rootView) {
//            if (mHeaderView != null && mHeaderView instanceof DragListener ) {
//                return ((DragListener) mHeaderView).getDragTriggerOffset(rootView);
//            }
//            return 0;
//        }
//
//    };
//
//    onLoadMoreCallback mLoadMoreCallback = new onLoadMoreCallback() {
//
//        @Override
//        public void onPrepare() {
//            if (mFooterView != null && mFooterView instanceof DragListener && STATUS.isStatusDefault(mStatus)) {
//                mFooterView.setVisibility(VISIBLE);
//                ((DragListener) mFooterView).onPrepare();
//            }
//        }
//
//        @Override
//        public void onDrag(int y,int offset) {
//            if (mFooterView != null && mFooterView instanceof DragListener && STATUS.isLoadMoreStatus(mStatus)) {
//                if (mFooterView.getVisibility() != VISIBLE) {
//                    mFooterView.setVisibility(VISIBLE);
//                }
//                ((DragListener) mFooterView).onDrag(y,offset);
//            }
//        }
//
//        @Override
//        public void onRelease() {
//            if (mFooterView != null && mFooterView instanceof DragListener && STATUS.isReleaseToLoadMore(mStatus)) {
//                ((DragListener) mFooterView).onRelease();
//            }
//        }
//
//        @Override
//        public void onLoadMore() {
//            if (mFooterView != null && STATUS.isLoadingMore(mStatus)) {
//                if(mFooterView instanceof onLoadMoreListener){
//                    ((onLoadMoreListener)mFooterView).onLoadMore();
//                }
//                if (mLoadMoreListener != null) {
//                    mLoadMoreListener.onLoadMore();
//                }
//            }
//        }
//
//        @Override
//        public void onComplete() {
//            if (mFooterView != null && mFooterView instanceof DragListener) {
//                ((DragListener) mFooterView).onComplete();
//            }
//        }
//
//        @Override
//        public void  onReset(){
//            if (mFooterView != null && mFooterView instanceof DragListener && STATUS.isStatusDefault(mStatus)) {
//                ((DragListener) mFooterView).onReset();
//                mFooterView.setVisibility(GONE);
//            }
//        }
//
//
//        @Override
//        public int getDragMaxOffset(View rootView) {
//            if (mFooterView != null && mFooterView instanceof DragListener) {
//                return  ((DragListener) mFooterView).getDragMaxOffset(rootView);
//            }
//            return 0;
//        }
//
//        @Override
//        public int getDragTriggerOffset(View rootView) {
//            if (mFooterView != null && mFooterView instanceof DragListener) {
//                return  ((DragListener) mFooterView).getDragTriggerOffset(rootView);
//            }
//            return 0;
//        }
//
//    };
//
//    private onLoadMoreListener mLoadMoreListener;
//    public void setonLoadMoreListener(onLoadMoreListener listener){
//        this.mLoadMoreListener = listener;
//    }
//
//    private onRefreshListener mRefreshListener;
//    public void setonRefreshListener(onRefreshListener listener){
//        this.mRefreshListener = listener;
//    }
//
//
//    private final static class STATUS {
//        private static final int STATUS_REFRESH_RETURNING = -4;
//        private static final int STATUS_REFRESHING = -3;
//        private static final int STATUS_RELEASE_TO_REFRESH = -2;
//        private static final int STATUS_SWIPING_TO_REFRESH = -1;
//        private static final int STATUS_DEFAULT = 0;
//        private static final int STATUS_SWIPING_TO_LOAD_MORE = 1;
//        private static final int STATUS_RELEASE_TO_LOAD_MORE = 2;
//        private static final int STATUS_LOADING_MORE = 3;
//        private static final int STATUS_LOAD_MORE_RETURNING = 4;
//        private static boolean isRefreshing(final int status) {
//            return status == STATUS.STATUS_REFRESHING;
//        }
//
//        private static boolean isLoadingMore(final int status) {
//            return status == STATUS.STATUS_LOADING_MORE;
//        }
//
//        private static boolean isStatusDefault(final int status) {
//            return status == STATUS.STATUS_DEFAULT;
//        }
//
//        private static boolean isReleaseToRefresh(final int status) {
//            return status == STATUS.STATUS_RELEASE_TO_REFRESH;
//        }
//
//        private static boolean isReleaseToLoadMore(final int status) {
//            return status == STATUS.STATUS_RELEASE_TO_LOAD_MORE;
//        }
//
//        private static boolean isSwipingToRefresh(final int status) {
//            return status == STATUS.STATUS_SWIPING_TO_REFRESH;
//        }
//
//        private static boolean isSwipingToLoadMore(final int status) {
//            return status == STATUS.STATUS_SWIPING_TO_LOAD_MORE;
//        }
//
//        private static boolean isRefreshStatus(final int status) {
//            return status < STATUS.STATUS_DEFAULT;
//        }
//
//        public static boolean isLoadMoreStatus(final int status) {
//            return status > STATUS.STATUS_DEFAULT;
//        }
//
//    }
//}