package github.skcodestack.materialdesignproject.paint.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import github.skcodestack.materialdesignproject.R;

public class SlidingPriceBar extends View {
	
	private Bitmap gray_bg;
	private Bitmap green_bg;
	private Bitmap btn;
	private Bitmap num_price;
	private Paint paint;
	private int bg_height;
	private int bg_width;
	private float scale_h;

	private final int FIRST_STAGE = 0;
	private final int SECEND_STAGE = 200;
	private final int THIRD_STAGE = 500;
	private final int FOURTH_STAGE = 1000;
	private final int FIFTH_STAGE = 10000;
	private int span;
	private int price_u;
	private int price_d;
	private float btn_x;
	private float y_u;
	private final float REAL_PER = 0.95F;
	private float half_round;
	private String TAG = "SlidingPriceBar";
	private float y_d;
	private int PRICE_PADDING = 15;
	

	public SlidingPriceBar(Context context, AttributeSet attrs) {
		super(context, attrs);

		gray_bg = BitmapFactory.decodeResource(getResources(), R.drawable.axis_before);
		green_bg = BitmapFactory.decodeResource(getResources(), R.drawable.axis_after);
		btn = BitmapFactory.decodeResource(getResources(), R.drawable.btn);
		num_price = BitmapFactory.decodeResource(getResources(), R.drawable.bg_number);
		paint = new Paint();
		paint.setColor(Color.GRAY);

		TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.slidingpricebar);
		price_u = array.getInt(R.styleable.slidingpricebar_price_u, 1000);
		price_d = array.getInt(R.styleable.slidingpricebar_price_d, 200);
		
	}
	
	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
//		super.onMeasure(widthMeasureSpec, heightMeasureSpec);

//		MeasureSpec.AT_MOST match_parent/fill_parent
//		MeasureSpec.UNSPECIFIED Сwrap_content
		int modeWidth = MeasureSpec.getMode(widthMeasureSpec);
		int sizeWidth = MeasureSpec.getSize(widthMeasureSpec);
		
		int modeHeight = MeasureSpec.getMode(heightMeasureSpec);
		int sizeHeight = MeasureSpec.getSize(heightMeasureSpec);
		
		bg_height = gray_bg.getHeight();
		bg_width = gray_bg.getWidth();
		//
		int measuredHeight = (modeHeight==MeasureSpec.EXACTLY)?sizeHeight:bg_height;
		//
		measuredHeight = Math.min(measuredHeight, sizeHeight);
		int measuredWidth = measuredHeight*2/3;
		//
		scale_h = (float)measuredHeight/bg_height;
		//
		span = (bg_height-bg_width)/4;
		//
		half_round = bg_height*(1-REAL_PER)/2;
		//
		setMeasuredDimension(measuredWidth, measuredHeight);
	}
	
	@Override
	protected void onDraw(Canvas canvas) {

		canvas.save();
		canvas.scale(scale_h, scale_h);
		

		float bg_x = (this.getWidth()/scale_h-gray_bg.getWidth())/2;
		canvas.drawBitmap(gray_bg, bg_x, 0, paint);
		

		String[] numbers = new String[]{
			"不限",
			String.valueOf(FOURTH_STAGE),	
			String.valueOf(THIRD_STAGE),	
			String.valueOf(SECEND_STAGE),	
			String.valueOf(FIRST_STAGE)
		};
		paint.setTextSize(20/scale_h);
		for (int i = 0; i < numbers.length; i++) {
			int text_x = (int) (bg_x*5/4);

			//(paint.descent()-paint.ascent())/
//			float text_y = i*span+bg_width/2+(paint.descent()-paint.ascent())/2-paint.descent();
			float text_y = i*span+bg_width/2+(-paint.ascent()-paint.descent())/2;
			canvas.drawText(numbers[i], text_x, text_y, paint);
		}

		btn_x = (this.getWidth()/scale_h - btn.getWidth())/2;
		y_u = getBtnYLocationByPrice(price_u);
		canvas.drawBitmap(btn, btn_x, y_u-btn.getHeight()/2, paint);
		y_d = getBtnYLocationByPrice(price_d);
		canvas.drawBitmap(btn, btn_x, y_d-btn.getHeight()/2, paint);

		Rect src = new Rect(0, (int)(y_u+btn.getHeight()/2), green_bg.getWidth(), (int)(y_d-btn.getHeight()/2));
		Rect dst = new Rect((int)bg_x, (int)(y_u+btn.getHeight()/2), (int)(bg_x+green_bg.getWidth()), (int)(y_d-btn.getHeight()/2));
		canvas.drawBitmap(green_bg, src, dst, paint);

		Rect rect_u = getRectByMidLine(y_u);
		canvas.drawBitmap(num_price, null, rect_u, paint);
		Rect rect_d = getRectByMidLine(y_d);
		canvas.drawBitmap(num_price, null, rect_d, paint);

		float text_u_x = (3*rect_u.width()/4 - paint.measureText(String.valueOf(price_u)))/2;
		float text_u_y = rect_u.top - paint.ascent() + PRICE_PADDING;

		float text_d_x = (3*rect_d.width()/4 - paint.measureText(String.valueOf(price_d)))/2;
		float text_d_y = rect_d.top - paint.ascent() + PRICE_PADDING;
		canvas.drawText(String.valueOf(price_u), text_u_x, text_u_y, paint);
		canvas.drawText(String.valueOf(price_d), text_d_x, text_d_y, paint);
		canvas.restore();
		super.onDraw(canvas);
	}

	/**
	 *
	 * @param y
	 * @return
	 */
	private Rect getRectByMidLine(float y) {
		Rect rect = new Rect();
		rect.left=0;
		rect.right = (int) btn_x;
		float text_h = paint.descent() - paint.ascent();
		rect.top = (int)(y-text_h/2) - PRICE_PADDING ;
		rect.bottom = (int)(y+text_h/2) + PRICE_PADDING ;
		return rect;
	}

	/**
	 *
	 * @param price
	 * @return
	 */
	private float getBtnYLocationByPrice(int price) {
		float y = 0;
		if(price<FIRST_STAGE){
			price = FIRST_STAGE;
		}
		if(price > FIFTH_STAGE){
			price = FIFTH_STAGE;
		}
		if(price>=FIRST_STAGE&&price<SECEND_STAGE){
			//0~200
			y =  bg_height - span*price/(SECEND_STAGE-FIRST_STAGE) -half_round;
		}else if(price>=SECEND_STAGE&&price<THIRD_STAGE){//200~500
			y =  bg_height - span*(price-SECEND_STAGE)/(THIRD_STAGE-SECEND_STAGE)-span -half_round;
		}else if(price>=THIRD_STAGE&&price<FOURTH_STAGE){//500~1000
			y = bg_height - span*(price-THIRD_STAGE)/(FOURTH_STAGE-THIRD_STAGE)-2*span -half_round;
		}else {//1000~10000
			y = span*(FIFTH_STAGE- price)/(FIFTH_STAGE-FOURTH_STAGE)+half_round;
		}
		Log.i(TAG , "price:"+price+"y坐标："+y);
		return y;
	}
	
	private boolean isDownTouched;
	private boolean isUpTouched;
	@Override
	public boolean onTouchEvent(MotionEvent event) {

		int action = event.getAction();
		switch (action) {
		case MotionEvent.ACTION_DOWN:

			float x = event.getX()/scale_h;
			float y = event.getY()/scale_h;

			if(x>btn_x&&x<=btn_x+btn.getWidth()){

				if(y>=y_u-btn.getHeight()/2&&y<=y_u+btn.getHeight()/2){

					isUpTouched = true;
					isDownTouched = false;
				}

				if(y>=y_d-btn.getHeight()/2&&y<=y_d+btn.getHeight()/2){

					isUpTouched = false;
					isDownTouched = true;
				}
			}
			break;
		case MotionEvent.ACTION_MOVE:
			float y2 = event.getY();
			y2 = y2/scale_h;
			if(isUpTouched){

				price_u = getPriceByPosition(y2);
				if(price_u<price_d){
					price_u = price_d;
				}
			}
			if(isDownTouched){
				price_d =getPriceByPosition(y2);
				if(price_u<price_d){
					price_d = price_u;
				}
			}

			invalidate();
			break;
		case MotionEvent.ACTION_UP:
			isUpTouched = false;
			isDownTouched = false;
			break;

		default:
			break;
		}
		
		return true;
	}

	/**
	 *
	 * @param y
	 * @return
	 */
	private int getPriceByPosition(float y) {
		
		float half_height = this.getHeight()*(1-REAL_PER)/2;
		int price;
		if(y<half_height){
			//y>10000
			price = FIFTH_STAGE;
		}else if(y>=half_height&&y<half_height+span){
			//1000~10000
			price = (int) (FIFTH_STAGE - (FIFTH_STAGE-FOURTH_STAGE)*(y-half_height)/span);
		}else if(y>half_height+span&&y<half_height+2*span){
			//500~1000
			price = (int) (FOURTH_STAGE - (FOURTH_STAGE-THIRD_STAGE)*(y-half_height-span)/span);
		}else if(y>half_height+2*span&&y<half_height+3*span){
			//200~500
			price = (int) (THIRD_STAGE - (THIRD_STAGE-SECEND_STAGE)*(y-half_height-2*span)/span);
		}else{
			//0~200
			price = (int) (SECEND_STAGE - (SECEND_STAGE-FIRST_STAGE)*(y-half_height-3*span)/span);
//			price = (int) ((SECEND_STAGE - FIRST_STAGE)*((this.getHeight()-y-half_height)/span));
		}
		if(price<FIRST_STAGE){
			price = FIRST_STAGE;
		}
		return price;
	}
	
	
	
}
