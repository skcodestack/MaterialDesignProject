package github.com.materialdesignproject.util;

import android.content.Context;
import android.widget.Toast;

public class Utiles {

	private  static   Toast toast;
	/**
	 * 显示单列Toast
	 * @param ctx
	 * @param msg
	 */
	public  static  void  showToast(Context ctx,String msg){
		if(toast==null){
			toast=Toast.makeText(ctx, "", Toast.LENGTH_SHORT);
		}
		toast.setText(msg);
		toast.show();
	}

	/**
	 * 将dip转成px
	 * @param dip
	 */
	public static  int   dip2px(Context ctx,int dip){
		float density = ctx.getResources().getDisplayMetrics().density;
		return   (int)(density*dip+0.5f);
	}
	/**
	 * 将px转成dip
	 * @param dip
	 */
	public static  int   px2dip(Context ctx,int px){
		float density = ctx.getResources().getDisplayMetrics().density;
		return   (int)(px/density+0.5f);
	}
}
