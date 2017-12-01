package github.com.materialdesignproject.senior;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;

import java.util.List;

import github.com.materialdesignproject.R;
import github.skcodestack.banner.BannerAdapter;
import github.skcodestack.banner.BannerView;
import github.skcodestack.recyclerview.base.ViewHolder;
import github.skcodestack.recyclerview.section.StatelessSection;

/**
 * Email  1562363326@qq.com
 * Github https://github.com/skcodestack
 * Created by sk on 2017/11/28
 * Version  1.0
 * Description:
 */

public class HeaderSection extends StatelessSection {

    Context mContext;
    int[] mData=new int[]{R.mipmap.banner_default,R.mipmap.splash_slogan};
    String[] mDesc=new String[]{"哈哈哈哈哈哈哈","呵呵呵呵呵呵呵呵呵呵呵"};

    public HeaderSection(Context context ) {
        super(R.layout.bannerview_head_layout,  android.R.layout.simple_list_item_1);
        this.mContext = context;
    }

    @Override
    public int getContentItemsTotal() {
        return 0;
    }

    @Override
    public ViewHolder getItemViewHolder(View view, int viewType) {
        return new ViewHolder(mContext, view) ;
    }

    @Override
    public void onBindItemViewHolder(ViewHolder holder, int position) {

    }
    @Override
    public ViewHolder getHeaderViewHolder(Context context, View view) {
        return new ViewHolder(mContext, view);
    }

    @Override
    public void onBindHeaderViewHolder(ViewHolder holder) {
        BannerView bannerView = (BannerView)holder.getView(R.id.banner_view);


            bannerView.setAdapter(new BannerAdapter() {
                @Override
                public View getView(int position, View convertView) {
                    ImageView mImageView = null;
                    if (convertView == null) {
                        mImageView = new ImageView(mContext);
                    } else {
                        mImageView = (ImageView) convertView;
                    }

                    mImageView.setImageResource(mData[position]);
                    return mImageView;
                }

                @Override
                public int getCount() {
                    return mData.length;
                }

                @Override
                public String getBannerDesc(int position) {
                    return mDesc[position];
                }
            });

            bannerView.startRoll();

    }
}
