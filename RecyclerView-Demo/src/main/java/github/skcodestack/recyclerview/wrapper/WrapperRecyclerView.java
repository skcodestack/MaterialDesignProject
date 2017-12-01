package github.skcodestack.recyclerview.wrapper;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 * Email  1562363326@qq.com
 * Github https://github.com/skcodestack
 * Created by sk on 2017/6/6
 * Version  1.0
 * Description:
 */

public class WrapperRecyclerView extends RecyclerView {



    private  HeaderAndFooterWrapper mAdapter;
    private AdapterDataObserver mObserver = new AdapterDataObserver() {
        @Override
        public void onChanged() {
            super.onChanged();
            mAdapter.notifyDataSetChanged();
        }

        @Override
        public void onItemRangeInserted(int positionStart, int itemCount) {
            super.onItemRangeInserted(positionStart, itemCount);
            mAdapter.notifyItemRangeInserted(positionStart,itemCount);
        }

        @Override
        public void onItemRangeRemoved(int positionStart, int itemCount) {
            super.onItemRangeRemoved(positionStart, itemCount);
            mAdapter.notifyItemRangeRemoved(positionStart,itemCount);
        }

        @Override
        public void onItemRangeMoved(int fromPosition, int toPosition, int itemCount) {
            super.onItemRangeMoved(fromPosition, toPosition, itemCount);
            Log.e("WrapperRecyclerView","fromPosition===>"+fromPosition+"----"+toPosition+"---->"+itemCount);
            mAdapter.notifyItemMoved(fromPosition,toPosition);
        }
    };


    public WrapperRecyclerView(Context context) {
        this(context,null);
    }

    public WrapperRecyclerView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public WrapperRecyclerView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);


    }


    @Override
    public void setAdapter(Adapter adapter) {

        if(adapter instanceof HeaderAndFooterWrapper){
            mAdapter = (HeaderAndFooterWrapper) adapter;
        }else {
            mAdapter = new HeaderAndFooterWrapper(adapter);
            adapter.registerAdapterDataObserver(mObserver);
        }

        super.setAdapter(mAdapter);
    }


    public void addHeaderView(View v){
       if(v != null ){
           mAdapter.addHeaderView(v);
       }
    }
    public void addFooterView(View v){
        if(v != null){
            mAdapter.addFootView(v);
        }

    }
    public void removeHeaderView(View v){
        if(v != null){
            mAdapter.removeHeaderView(v);
        }

    }
    public void removeFooterView(View v){
        if(v != null){
            mAdapter.removeFootView(v);
        }

    }


}
