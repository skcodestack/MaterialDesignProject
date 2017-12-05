package github.com.materialdesignproject.custom.qq5;

import android.R.anim;
import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.animation.CycleInterpolator;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.nineoldandroids.animation.ObjectAnimator;
import com.nineoldandroids.view.ViewHelper;

import github.com.materialdesignproject.R;
import github.com.materialdesignproject.data.Cheeses;

public class MainFrament extends BaseFrament {

	private ListView main_content;
	private ImageView iv_head;
	public MainFrament(Activity activity) {
		super(activity);
	}

	@Override
	public View initView() {
		View inflate = View.inflate(mActivity, R.layout.main_layout,null);
		main_content = (ListView) inflate.findViewById(R.id.lv_main);
		iv_head = (ImageView) inflate.findViewById(R.id.iv_head);
		
		return inflate;
	}
	@Override
	public void initDate() {
		super.initDate();
		iv_head.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				((QQ5Activity) mActivity).OpenLeftFramet();
			}
		});
		main_content.setAdapter(new ArrayAdapter<String>(mActivity, android.R.layout.simple_list_item_1, Cheeses.NAMES));
	}

	public void setHeaderAlpha(float percent) {
		ViewHelper.setAlpha(iv_head, 1-percent);
		
	}

	public  void  setShakeHead(){
		
		ObjectAnimator anima= ObjectAnimator.ofFloat(iv_head, "TranslationX", 15.0f);
		//差值器
		anima.setInterpolator(new CycleInterpolator(6));
		anima.setDuration(500);
		anima.start();
	}
	
	
}
