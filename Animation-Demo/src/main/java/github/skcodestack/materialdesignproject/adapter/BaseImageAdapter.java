package github.skcodestack.materialdesignproject.adapter;

import android.animation.Animator;
import android.content.Context;
import android.os.Build;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;

import java.util.List;

/**
 * Email  1562363326@qq.com
 * Github https://github.com/skcodestack
 * Created by sk on 2017/12/20
 * Version  1.0
 * Description:
 */

public abstract class BaseImageAdapter extends RecyclerView.Adapter<ViewHolder> {

    List<String > list ;
    Context mContent;
    private final LayoutInflater layoutInflater;
    int resId;
    public BaseImageAdapter(Context context, List<String> list,int resId){
        this.list =list;
        this.mContent = context;
        layoutInflater = LayoutInflater.from(mContent);
        this.resId = resId;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(resId, null);
//        view.addOnAttachStateChangeListener(new View.OnAttachStateChangeListener() {
//            @Override
//            public void onViewAttachedToWindow(final View v) {
//                if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//
//                    int x = v.getWidth() ;
//                    int y = v.getHeight();
//                    double z = Math.pow (Math.pow(x, 2) + Math.pow(y, 2),0.5);
//
//                    Animator circularReveal = ViewAnimationUtils.createCircularReveal(v, v.getWidth() / 2, v.getHeight() / 2, 0, (float) (z/2));
//                    circularReveal.setDuration(500);
//                    circularReveal.setInterpolator(new AccelerateInterpolator());
//                    circularReveal.addListener(new Animator.AnimatorListener() {
//                        @Override
//                        public void onAnimationStart(Animator animation) {
//                            v.setVisibility(View.VISIBLE);
//                        }
//
//                        @Override
//                        public void onAnimationEnd(Animator animation) {
//
//                        }
//
//                        @Override
//                        public void onAnimationCancel(Animator animation) {
//
//                        }
//
//                        @Override
//                        public void onAnimationRepeat(Animator animation) {
//
//                        }
//                    });
//                    circularReveal.start();
//
//                }
//            }
//
//            @Override
//            public void onViewDetachedFromWindow(View v) {
//
//            }
//        });
        return new ViewHolder(mContent,view);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
