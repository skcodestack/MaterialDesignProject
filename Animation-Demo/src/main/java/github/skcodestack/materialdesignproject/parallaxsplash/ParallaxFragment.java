package github.skcodestack.materialdesignproject.parallaxsplash;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Email  1562363326@qq.com
 * Github https://github.com/skcodestack
 * Created by sk on 2017/12/21
 * Version  1.0
 * Description:
 */

public class ParallaxFragment extends Fragment {


    List<View> parallaxViews =   new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater original, ViewGroup container,
                             Bundle savedInstanceState) {
        Bundle args = getArguments();
        int layoutId = args.getInt("layoutId");
        int index = args.getInt("index");
//        Log.d("jason", "fragment:"+index);
        //1.布局加载器将布局加载进来了
        //2.解析创建布局上所有的视图
        //3.自己搞定创建视图的过程
        //4.获取视图相关的自定义属性的值
        ParallaxLayoutInflater inflater = new ParallaxLayoutInflater(original, getActivity(),this);
//        TextView
//        ImageView
        return inflater.inflate(layoutId, null);
    }



    public List<View> getParallaxViews() {
        Log.e("ParallaxFragment","list===>"+parallaxViews.size());
        return parallaxViews;
    }
}
