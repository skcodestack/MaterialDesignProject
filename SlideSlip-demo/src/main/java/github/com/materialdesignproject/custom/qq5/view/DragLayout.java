/***
 *********************************************************** 
 ******************** 凯子出品，必属精品**********************
 *********************************************************** 
 * 创建者：Root 2015
 *********************************************************** 
 * 创建于： 2015-12-4 下午3:05:53
 *********************************************************** 
 ******************** 凯子出品，必属精品**********************
 *********************************************************** 
 ***/
package github.com.materialdesignproject.custom.qq5.view;



import android.R.animator;
import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Color;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.ViewDragHelper;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.ScaleAnimation;
import android.widget.FrameLayout;

import com.nineoldandroids.view.ViewHelper;

public class DragLayout extends FrameLayout {
	
	private ViewDragHelper dragHelper;
	private ViewGroup mLeftContent;
	private ViewGroup mMianContent;
	private int mHeight;
	private int mWidth;
	private int mRangWidth;
	private  String Tag="DragLayout";
	/**
	 * 状态枚举
	 * @author root
	 *
	 */
	public  static  enum  Status{Close,Open,Draging;}
	//当前状态
	private  Status  mStatus=Status.Close;
	
	public Status getmStatus() {
		return mStatus;
	}
	public void setmStatus(Status mStatus) {
		this.mStatus = mStatus;
	}

	private  OnDragStatusChangeListenter  mListenter;
	/**
	 * 滑动界面滑动时调用接口  
	 * @author root
	 *
	 */
	public  interface  OnDragStatusChangeListenter{
		/**
		 * 界面关闭时调用
		 */
		void  close();
		/**
		 * 界面完全打开时调用
		 */
		void  open();
		/**
		 * 界面滑动过程中调用
		 * @param percent   滑动的百分比（0.0f--1.0f）
		 */
		void draging(float percent);
	}
	/**
	 * 对外接口
	 */
	public  void  setDragStatusListenter(OnDragStatusChangeListenter l){
		this.mListenter=l;
	}
	public DragLayout(Context context) {
		super(context);
		init();
	}

	public DragLayout(Context context, AttributeSet attrs) {
		super(context, attrs);
		init();
	}

	public DragLayout(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		init();
		
	}
	private void  init(){
		dragHelper = ViewDragHelper.create(this, callback);
	}
	
	ViewDragHelper.Callback callback =new ViewDragHelper.Callback() {
		
		/**
		 * 尝试抓捕view
		 */
		@Override
		public boolean tryCaptureView(View arg0, int arg1) {
			return true;
		}
		/**
		 * 抓捕到view
		 */
		@Override
		public void onViewCaptured(View capturedChild, int activePointerId) {
			super.onViewCaptured(capturedChild, activePointerId);
		}
		
		/**
		 * 水平位置 
		 */
		@Override
		public int clampViewPositionHorizontal(View child, int left, int dx) {
			if(child==mMianContent){
				left=fixLeft(left);
			}
			return left;
		}
		/**
		 * 垂直方向
		 */
		@Override
		public int clampViewPositionVertical(View child, int top, int dy) {
			return super.clampViewPositionVertical(child, top, dy);
		}
		/**
		 * 得到水平可以拖拉范围
		 */
		@Override
		public int getViewHorizontalDragRange(View child) {
			return mRangWidth;
		}
		/**
		 * 得到垂直可以拖拉范围
		 */
		@Override
		public int getViewVerticalDragRange(View child) {
			return super.getViewVerticalDragRange(child);
		}

		/**
		 * view position  changed  
		 * 
		 * method  invoke   after  position changed 
		 * 1.childView  2. left  3. top ,4. horzial  interval value  4.viertial  interval value
		 * 执行*****************************伴随动画********************************************
		 */
		public void onViewPositionChanged(View changedView, int left, int top, int dx, int dy) {
			
			int newleft=left;
			if(changedView==mLeftContent){
				 newleft=mMianContent.getLeft()+dx;
				newleft=fixLeft(newleft);
				//强制将左边的固定在界面上
				mLeftContent.layout(0, 0, mWidth, mHeight);
				//更新中间的位置
				mMianContent.layout(newleft, 0, newleft+mWidth, mHeight);
			}
			
			
			
			//执行动画  
			//newleft  执行动画过程中距离左边的距离
			 dispatchDragEvent(newleft);
			
			//通过手动重新绘制界面，兼容低版本
			invalidate();
		};
		
		//view释放的时候。要执行的动画
		//1.释放的子view
		//2.水平方向的速度
		//3.垂直方向的速度
		public void onViewReleased(View releasedChild, float xvel, float yvel) {
//			Log.e(Tag, "xuel:"+xvel+";yvel:"+yvel);
			if(xvel==0 && mMianContent.getLeft()>mRangWidth/2){
				open();
			}else if(xvel>0){
				open();
			}else{
				close();
			}
		};
		
	};
	
	
	public boolean onInterceptTouchEvent(android.view.MotionEvent ev) {
		
		return dragHelper.shouldInterceptTouchEvent(ev);
		
		
	};
	
	
	/**
	 * 关闭主面板
	 */
	public   void  close(){
		close(true);
	}
	protected void close(boolean isanimal) {
		int  finalLeft=0;
		int finalTop=0;
		if(isanimal){
			//true：还没有到指定位置，需要继续移动（刷新界面）
			if(dragHelper.smoothSlideViewTo(mMianContent, finalLeft, finalTop)){
				ViewCompat.postInvalidateOnAnimation(this);
			}
		}else{
			mMianContent.layout(finalLeft, 0, finalLeft+mWidth, mHeight);
		}
		
	}
	@Override
	public void computeScroll() {
		super.computeScroll();
		//true  :还需要继续执行
		if(dragHelper.continueSettling(true)){
			ViewCompat.postInvalidateOnAnimation(this);
		}
	}
	/**
	 * 打开主面板
	 */
	public   void open() {
		open(true);
	}
	protected void open(boolean isannimal) {
		int  finalLeft=mRangWidth;
		int finalTop=0;
		if(isannimal){
			if(dragHelper.smoothSlideViewTo(mMianContent, finalLeft, finalTop)){
				ViewCompat.postInvalidateOnAnimation(this);
			}
		}else{
			mMianContent.layout(finalLeft, 0, finalLeft+mWidth, mHeight);
		}
	}

	/**
	 * 执行伴随动画
	 * leftcontent:   缩放，透明，平移
	 * mainContent :  缩放
	 * background:    颜色变化
	 * @param newleft
	 */
	protected void dispatchDragEvent(int newleft) {
			float percent=newleft*1.0f/mRangWidth;//(0.0---1.0)
			
			
			if(mListenter!=null){
				mListenter.draging(percent);
			}
			//更新状态执行回调
			Status perStatus=mStatus;
			mStatus=updateStatus(percent);
			if(mStatus!=perStatus){
				if(mStatus==Status.Close){
					if(mListenter!=null){
						mListenter.close();
					}
				}else if(mStatus==Status.Open){
					if(mListenter!=null){
						mListenter.open();
					}
				}
			}
			
			//leftcontent(不兼容低版本，通过nineoldandroid 解决)
			animationView(percent);
	}
	/**
	 * 更新状态
	 * @param percent
	 * @return
	 */
	private Status updateStatus(float percent) {
		if(percent==0f){
			return  Status.Close;
		}else if(percent==1.0f){
			return  Status.Open;
		}
		return Status.Draging;
	}
	/**
	 * 执行的伴随动画（alt+shift+m）
	 * @param percent   0.0f------>1.0f
	 */
	private void animationView(float percent) {
		ViewHelper.setTranslationX(mLeftContent, evaluate(percent,-mWidth/2f,0.0f));
		ViewHelper.setScaleX(mLeftContent, evaluate(percent, 0.5f, 1.0f));
		ViewHelper.setScaleY(mLeftContent, evaluate(percent, 0.5f, 1.0f));
		ViewHelper.setAlpha(mLeftContent, evaluate(percent, 0.5f, 1.0f));
		//mainContent
		ViewHelper.setScaleX(mMianContent, evaluate(percent, 1.0f, 0.8f));
		ViewHelper.setScaleY(mMianContent, evaluate(percent, 1.0f, 0.8f));
		
		//backGrounp
		getBackground().setColorFilter((Integer)evaluateColor(percent,Color.BLACK,Color.TRANSPARENT),
				android.graphics.PorterDuff.Mode.SRC_OVER);
		
	}

	/**
	 * 评估值
	 * @param fraction
	 * @param startValue
	 * @param endValue
	 * @return
	 */
	 public Float evaluate(float fraction, Number startValue, Number endValue) {
	        float startFloat = startValue.floatValue();
	        return startFloat + fraction * (endValue.floatValue() - startFloat);
	    }
	 /**
	  * 颜色变化过度
	  * @param fraction
	  * @param startValue
	  * @param endValue
	  * @return
	  */
	 public Object evaluateColor(float fraction, Object startValue, Object endValue) {
	        int startInt = (Integer) startValue;
	        int startA = (startInt >> 24) & 0xff;
	        int startR = (startInt >> 16) & 0xff;
	        int startG = (startInt >> 8) & 0xff;
	        int startB = startInt & 0xff;

	        int endInt = (Integer) endValue;
	        int endA = (endInt >> 24) & 0xff;
	        int endR = (endInt >> 16) & 0xff;
	        int endG = (endInt >> 8) & 0xff;
	        int endB = endInt & 0xff;

	        return (int)((startA + (int)(fraction * (endA - startA))) << 24) |
	                (int)((startR + (int)(fraction * (endR - startR))) << 16) |
	                (int)((startG + (int)(fraction * (endG - startG))) << 8) |
	                (int)((startB + (int)(fraction * (endB - startB))));
	    }
	/**
	 * 修正水平移动的值
	 * @param left
	 * @return
	 */
	protected int fixLeft(int left) {
		if(left<0){
			return  0;
		}else if(left>mRangWidth){
			return  mRangWidth;
		}
		return left;
	}

	public boolean onTouchEvent(android.view.MotionEvent event) {
		try {
			dragHelper.processTouchEvent(event);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		return true;
		
	};
	/**
	 * view  添加到this，调用
	 */
	@Override
	protected void onFinishInflate() {
		super.onFinishInflate();
		if(getChildCount()<2){
			throw new IllegalStateException(" least of  two  childern");
		}
		if(!(getChildAt(0) instanceof ViewGroup && getChildAt(1) instanceof  ViewGroup)){
			throw  new  IllegalArgumentException(" Argument  is  not  instanceof  ViewGroup");
		}
		
		mLeftContent = (ViewGroup) getChildAt(0);
		mMianContent = (ViewGroup) getChildAt(1);
	};
	
	/**
	 * 尺寸变化是调用
	 */
	@Override
	protected void onSizeChanged(int w, int h, int oldw, int oldh) {
		super.onSizeChanged(w, h, oldw, oldh);
			mHeight = getMeasuredHeight();
			mWidth = getMeasuredWidth();
			mRangWidth = (int) (mWidth*0.6);
	}

}
