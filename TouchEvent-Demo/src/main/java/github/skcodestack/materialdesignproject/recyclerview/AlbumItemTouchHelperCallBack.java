package github.skcodestack.materialdesignproject.recyclerview;

import android.graphics.Canvas;
import android.graphics.Color;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.AlertDialogLayout;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.ViewGroup;

import java.util.Collections;
import java.util.List;

import github.skcodestack.materialdesignproject.R;

/**
 * Email  1562363326@qq.com
 * Github https://github.com/skcodestack
 * Created by sk on 2017/12/22
 * Version  1.0
 * Description:
 */

public class AlbumItemTouchHelperCallBack<T> extends ItemTouchHelper.Callback {

    private static final String TAG = "AlbumItemTouchHelper";

    RecyclerView.Adapter mAdapter = null;
    List<T> mList;
    public AlbumItemTouchHelperCallBack(RecyclerView.Adapter adapter, List<T> list) {
        this.mAdapter = adapter;
        this.mList = list;
    }

    @Override
    public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
        int dragFlags = 0;
        int swipeFlags = 0;
        dragFlags = ItemTouchHelper.LEFT|ItemTouchHelper.UP|ItemTouchHelper.RIGHT|ItemTouchHelper.DOWN;
        return makeMovementFlags(dragFlags,swipeFlags);
    }

    @Override
    public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
        if(viewHolder.getItemViewType() != target.getItemViewType()){
            return false;
        }

        int fromPostion = viewHolder.getAdapterPosition();
        int targetPostion = target.getAdapterPosition();
        if(fromPostion == 0){
           viewHolder.itemView.setScaleX(1f);
           viewHolder.itemView.setScaleY(1f);
//           viewHolder.itemView.offsetTopAndBottom(viewHolder.itemView.getHeight());
//           viewHolder.itemView.offsetLeftAndRight(viewHolder.itemView.getWidth());
        }
        if(targetPostion == 0){
            viewHolder.itemView.setScaleX(0.5f);
            viewHolder.itemView.setScaleY(0.5f);
        }
        mAdapter.notifyItemMoved(fromPostion,targetPostion);
        return true;
    }

    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {

    }

    @Override
    public void onMoved(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, int fromPos, RecyclerView.ViewHolder target, int toPos, int x, int y) {
        super.onMoved(recyclerView, viewHolder, fromPos, target, toPos, x, y);

        Collections.swap(mList,fromPos,toPos);
    }

    @Override
    public void onSelectedChanged(RecyclerView.ViewHolder viewHolder, int actionState) {
        //状态改变
        if(actionState != ItemTouchHelper.ACTION_STATE_IDLE) {
            viewHolder.itemView.setBackgroundColor(Color.GRAY);
            int position = viewHolder.getAdapterPosition();
            Log.e(TAG,"onSelectedChanged == >" + position);
            if(position == 0){
               viewHolder.itemView.setScaleX(0.5f);
               viewHolder.itemView.setScaleY(0.5f);
//                viewHolder.itemView.offsetTopAndBottom(viewHolder.itemView.getHeight());
//                viewHolder.itemView.offsetLeftAndRight(viewHolder.itemView.getWidth());
//                ViewGroup.LayoutParams layoutParams = viewHolder.itemView.getLayoutParams();
//                layoutParams.height = 50;
//                layoutParams.width = 50;
//                viewHolder.itemView.setLayoutParams(layoutParams);

            }
        }
    }

    @Override
    public void clearView(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
        super.clearView(recyclerView, viewHolder);
        viewHolder.itemView.setBackgroundColor(Color.WHITE);
//        ViewCompat.setTranslationX(viewHolder.itemView,0);
        int position = viewHolder.getAdapterPosition();
        Log.e(TAG,"clearView == >" + position);
//        if(position == 0){
            viewHolder.itemView.setScaleX(1f);
            viewHolder.itemView.setScaleY(1f);
//        }
    }


    @Override
    public void onChildDraw(Canvas c, RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {
        super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
        if(actionState == ItemTouchHelper.ACTION_STATE_DRAG){

        }
    }


    public BaseRecyclerAdapter.ViewHolder obtainViewHolder(RecyclerView.ViewHolder viewHolder) throws Exception {

        if(viewHolder != null && viewHolder instanceof BaseRecyclerAdapter.ViewHolder){
            return (BaseRecyclerAdapter.ViewHolder) viewHolder;
        }else {
            throw new ParseViewHolderExecption("ViewHolder parse BaseRecyclerAdapter.ViewHolder is Faild");
        }
    }
}
