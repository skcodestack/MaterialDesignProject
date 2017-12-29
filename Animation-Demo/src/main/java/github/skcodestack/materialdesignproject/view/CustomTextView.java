package github.skcodestack.materialdesignproject.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Email  1562363326@qq.com
 * Github https://github.com/skcodestack
 * Created by sk on 2017/12/20
 * Version  1.0
 * Description:
 */

public class CustomTextView extends TextView {
    public CustomTextView(Context context) {
        super(context);
    }

    public CustomTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void setChar(Character character){
        this.setText("value:"+character);
    }
}
