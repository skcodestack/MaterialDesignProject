package github.skcodestack.materialdesignproject.parallaxsplash;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;

import github.skcodestack.materialdesignproject.R;


/**
 * Email  1562363326@qq.com
 * Github https://github.com/skcodestack
 * Created by sk on 2017/12/21
 * Version  1.0
 * Description:LayoutInflater
 */

public class ParallaxLayoutInflater extends LayoutInflater  {

    private ParallaxFragment mFragment;
    protected ParallaxLayoutInflater(LayoutInflater original, Context newContext ,ParallaxFragment fragment) {
        super(original, newContext);
        this.mFragment = fragment;
        setFactory2(new ParallaxFactory(this));
    }

    @Override
    public LayoutInflater cloneInContext(Context newContext) {
        return new ParallaxLayoutInflater(this,newContext,mFragment);
    }



    class ParallaxFactory implements LayoutInflater.Factory2{

        private  final String[] sClassPrefixList = {
                "android.widget.",
                "android.view.",
                "android.webkit."
        };


        final int[] attrIds = {
                R.attr.a_in,
                R.attr.a_out,
                R.attr.x_in,
                R.attr.x_out,
                R.attr.y_in,
                R.attr.y_out
        };

        private LayoutInflater mLayoutInflater;
        public ParallaxFactory(LayoutInflater layoutInflater){
            this.mLayoutInflater = layoutInflater;
        }

        @Override
        public View onCreateView(View parent, String name, Context context, AttributeSet attrs) {
            View view = createView(name, attrs);
            if(view != null){
                setViewTag(view,context,attrs);
                Log.e("ParallaxLayoutInflater","=====>setViewTag");
                mFragment.getParallaxViews().add(view);
            }
            return view;
        }


        private  View createView(String name, AttributeSet attrs){
            try {
                //自定义控件
                if (name.contains(".")) {
                    return createView(name,null,attrs);
                }else {
                    for (String prefix : sClassPrefixList) {
                        View view = createView(name,prefix,attrs);;
                        if(view != null){
                            return view;
                        }
                    }
                }
            }catch (Exception ex){

            }

            return null;
        }
        private View  createView(String name,String prefix,AttributeSet attrs){
            try {
                return mLayoutInflater.createView(name, prefix, attrs);
            } catch (ClassNotFoundException e) {
                Log.e("layout","====>" + e);
                return null;
            }
        }

        private void  setViewTag(View view, Context context, AttributeSet attrs){


            TypedArray typedArray = context.obtainStyledAttributes(attrs, attrIds);

            if(typedArray != null && typedArray.length() >0){

                //获取自定义属性的值
                ParallaxViewTag tag = new ParallaxViewTag();
                tag.alphaIn = typedArray.getFloat(0, 0f);
                tag.alphaOut = typedArray.getFloat(1, 0f);
                tag.xIn = typedArray.getFloat(2, 0f);
                tag.xOut = typedArray.getFloat(3, 0f);
                tag.yIn = typedArray.getFloat(4, 0f);
                tag.yOut = typedArray.getFloat(5, 0f);

                //index
                view.setTag(R.id.parallax_view_tag,tag);


            }
            typedArray.recycle();
        }


        @Override
        public View onCreateView(String name, Context context, AttributeSet attrs) {
            View view = createView(name, attrs);
            if(view != null){
                setViewTag(view,context,attrs);
                Log.e("ParallaxLayoutInflater","=====>setViewTag");
                mFragment.getParallaxViews().add(view);
            }
            return view;
        }
    }
}
