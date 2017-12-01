package github.com.materialdesignproject.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import github.com.materialdesignproject.R;

/**
 * Email  1562363326@qq.com
 * Github https://github.com/skcodestack
 * Created by sk on 2017/10/12
 * Version  1.0
 * Description:
 */

public class CustomRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<String> lisData;
    private Context context;
    private final LayoutInflater layoutInflater;

    public CustomRecyclerAdapter(Context context, List<String> lisData) {
        this.lisData = lisData;
        this.context = context;
        layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        CommonViewHolder viewHolder = new CommonViewHolder(layoutInflater.inflate(R.layout.list_item_layout,parent,false));

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        CommonViewHolder commonViewHolder = (CommonViewHolder) holder;

        commonViewHolder.textView.setText(lisData.get(position));

    }

    @Override
    public int getItemCount() {
        return lisData.size();
    }


    public static class CommonViewHolder extends RecyclerView.ViewHolder{

        public TextView textView;

        public CommonViewHolder(View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.tv);
        }
    }

}
