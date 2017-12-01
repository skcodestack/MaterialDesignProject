package github.com.materialdesignproject;

import android.content.Context;
import android.util.TypedValue;

/**
 * Email  1562363326@qq.com
 * Github https://github.com/skcodestack
 * Created by sk on 2017/10/11
 * Version  1.0
 * Description:
 */

public class HelperUtil {

    /**
     * 获取actionBar高度
     * @param context
     * @return
     */
    public static  int  getActionBarHeight(Context context){
        TypedValue  tv = new TypedValue();
        int actionBarHeight = 0;

        if (context.getTheme().resolveAttribute(android.R.attr.actionBarSize, tv, true)) {
            actionBarHeight = TypedValue.complexToDimensionPixelSize(tv.data, context.getResources().getDisplayMetrics());
        }
        return actionBarHeight;
    }

    /*
     * Returns the status bar height for the current layout configuration.
     */
    public static int getStatusBarHeight(Context context) {
        int result = 0;

        int resourceId = context.getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = context.getResources().getDimensionPixelSize(resourceId);
        }

        return result;
    }

    /**
     * 获取NavBar高度
     * @param context
     * @return
     */
    public static int getNavBarHeight(Context context) {
        int result = 0;
        int resourceId = context.getResources().getIdentifier("navigation_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = context.getResources().getDimensionPixelSize(resourceId);
        }

        return result;
    }

}
