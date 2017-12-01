package github.com.materialdesignproject.senior;

import android.content.Context;
import android.view.View;

import java.util.List;

import github.com.materialdesignproject.DataUtil;
import github.com.materialdesignproject.R;
import github.skcodestack.recyclerview.base.ViewHolder;
import github.skcodestack.recyclerview.section.StatelessSection;

/**
 * Email  1562363326@qq.com
 * Github https://github.com/skcodestack
 * Created by sk on 2017/11/28
 * Version  1.0
 * Description:
 */

public class ItemSection extends StatelessSection{

    List<String> list = null;
    Context mContext;
    String  head_title = "分组";
    int spansize = 2;
    public ItemSection(Context context ,int headerResourceId, int itemResourceId,String desc, int spansize,List<String> list) {
        super(headerResourceId,  itemResourceId);
        this.list = list;
        this.mContext = context;
        head_title = desc;
        this.spansize = spansize;
    }
    public ItemSection(Context context ,int headerResourceId, int itemResourceId,String desc, List<String> list) {
        super(headerResourceId,  itemResourceId);
        this.list = list;
        this.mContext = context;
        head_title = desc;
    }

    @Override
    public int getContentItemsTotal() {
        return list.size();
    }

    @Override
    public ViewHolder getItemViewHolder(View view, int viewType) {
        return ViewHolder.createViewHolder(mContext,view);
    }

    @Override
    public void onBindItemViewHolder(ViewHolder holder, int position) {
        String s = list.get(position);
        holder.setText(R.id.desc,s);
        holder.setImageByUrl(R.id.image, DataUtil.getImageUrl());
    }

    @Override
    public ViewHolder getHeaderViewHolder(Context context, View view) {
        return ViewHolder.createViewHolder(mContext,view);
    }

    @Override
    public void onBindHeaderViewHolder(ViewHolder holder) {
        holder.setText(R.id.tv_title,head_title);
        holder.setImageByUrl(R.id.iv_head,DataUtil.getImageUrl());
    }

    @Override
    public int getSpanSize(int count) {
        return spansize;
    }
}
