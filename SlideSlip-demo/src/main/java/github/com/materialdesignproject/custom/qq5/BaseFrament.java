package github.com.materialdesignproject.custom.qq5;

import android.app.Activity;
import android.content.Context;
import android.view.View;

public abstract class BaseFrament {

	
	public  View view;
	public  Activity mActivity;
	public BaseFrament(Activity ctx) {
		this.mActivity=ctx;
		 init();
		
	}

	private void  init(){
		 view=initView();
		 initDate();
	}

	public void initDate() {
		
	}

	public abstract  View initView();
}
