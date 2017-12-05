package github.com.materialdesignproject.custom.qq5.view;


import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.FrameLayout;

public class MyFrameLayout extends FrameLayout {

	private DragLayout  mDragLayout;
	public MyFrameLayout(Context context) {
		super(context);
	}

	public MyFrameLayout(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public MyFrameLayout(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}
	public  void  setDragLayout(DragLayout dl){
		this.mDragLayout=dl;
	}
	@Override
	public boolean onInterceptTouchEvent(MotionEvent ev) {
		if(mDragLayout.getmStatus()== DragLayout.Status.Close){
			return super.onInterceptTouchEvent(ev);
		}else{
			return  true;
		}
		
		
	}
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		if(mDragLayout.getmStatus()== DragLayout.Status.Close){
			return super.onTouchEvent(event);
		}else{
			if(event.getAction()==MotionEvent.ACTION_UP){
				mDragLayout.close();
			}
			return  true;
		}
		
	}

}
