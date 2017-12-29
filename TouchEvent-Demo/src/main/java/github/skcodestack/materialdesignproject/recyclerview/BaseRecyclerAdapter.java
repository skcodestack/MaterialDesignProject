package github.skcodestack.materialdesignproject.recyclerview;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import github.skcodestack.materialdesignproject.R;

/**
 * Email  1562363326@qq.com
 * Github https://github.com/skcodestack
 * Created by sk on 2017/12/22
 * Version  1.0
 * Description:
 */

public class BaseRecyclerAdapter extends RecyclerView.Adapter<BaseRecyclerAdapter.ViewHolder>{

    Context mContext;
    List<String> list;
    LayoutInflater layoutInflater;

    public BaseRecyclerAdapter(Context context , List<String> list){
        this.mContext=context;
        this.list = list;
        layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public BaseRecyclerAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.layout_item, parent, false);
        return new BaseRecyclerAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(BaseRecyclerAdapter.ViewHolder holder, int position) {
//        TextView item
//                = (TextView) holder.itemView.findViewById(R.id.tv_item);
//        item.setText(list.get(position));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        public ViewHolder(View itemView) {
            super(itemView);
        }


        public void  setTag(int prePosition){
            itemView.setTag(R.id.selected_item,prePosition);
        }

        public int  getTag(){
            Object tag = itemView.getTag(R.id.selected_item);
            if(tag != null && tag instanceof Integer){
                return (int) tag;
            }
            return -1;
        }

        public boolean isChangedView(){
            int position = this.getAdapterPosition();
            int tag = getTag();

            if(position == 0){
                return true;
            }
            if(tag == 0){
                return true;
            }
            return false;
        }

    }
}
