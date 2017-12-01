package github.com.materialdesignproject.middle;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.List;

import github.com.materialdesignproject.DataUtil;
import github.com.materialdesignproject.R;
import github.skcodestack.banner.BannerAdapter;
import github.skcodestack.banner.BannerView;
import github.skcodestack.recyclerview.adapter.MultiItemTypeAdapter;
import github.skcodestack.recyclerview.base.ItemViewDelegate;
import github.skcodestack.recyclerview.base.ViewHolder;
import github.skcodestack.recyclerview.wrapper.WrapperRecyclerView;

/**
 * Email  1562363326@qq.com
 * Github https://github.com/skcodestack
 * Created by sk on 2017/11/28
 * Version  1.0
 * Description:
 */

public class MiddleFragment extends Fragment {

    private View rootView;
    private WrapperRecyclerView recyclerView;

    public static MiddleFragment newInstance() {
        MiddleFragment f = new MiddleFragment();
        return f;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_wrecycle, container, false);
        initBase();
        return rootView;
    }

    private void initBase() {
        List<String> list = DataUtil.obtainRandomData(100);
        recyclerView = (WrapperRecyclerView) rootView.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new GridLayoutManager(recyclerView.getContext(), 2, GridLayoutManager.VERTICAL, false));

        MultiItemTypeAdapter<String> adapter = new MultiItemTypeAdapter<>(recyclerView.getContext(), list);
        adapter.addItemViewDelegate(new ItemViewDelegate<String>() {
            @Override
            public int getItemViewLayoutId() {
                return R.layout.item1_layout;
            }

            @Override
            public boolean isForViewType(String item, int position) {
                return position%2 == 0;
            }

            @Override
            public void convert(ViewHolder holder, String s, int position) {
                holder.setText(R.id.desc,s);
                holder.setImageByUrl(R.id.image,DataUtil.getImageUrl());
            }
        });
        adapter.addItemViewDelegate(new ItemViewDelegate<String>() {
            @Override
            public int getItemViewLayoutId() {
                return R.layout.item2_layout;
            }

            @Override
            public boolean isForViewType(String item, int position) {
                return position%2 == 1;
            }

            @Override
            public void convert(ViewHolder holder, String s, int position) {
                holder.setText(R.id.desc,s);
                holder.setImageByUrl(R.id.image,DataUtil.getImageUrl());
            }
        });

        recyclerView.setAdapter(adapter);

        addHeader(recyclerView);
        addFoot(recyclerView);
    }

    private void addFoot(WrapperRecyclerView recyclerView) {
        final View footerView = LayoutInflater.from(recyclerView.getContext()).inflate(R.layout.foot_layout, recyclerView, false);
        recyclerView.addFooterView(footerView);
    }

    int[] mData=new int[]{R.mipmap.banner_default,R.mipmap.splash_slogan};
    String[] mDesc=new String[]{"哈哈哈哈哈哈哈","呵呵呵呵呵呵呵呵呵呵呵"};

    private void addHeader(final WrapperRecyclerView recyclerView) {
        final View headerView = LayoutInflater.from(recyclerView.getContext()).inflate(R.layout.bannerview_head_layout, recyclerView, false);
        BannerView bannerView = (BannerView) headerView.findViewById(R.id.banner_view);
        bannerView.setAdapter(new BannerAdapter() {
            @Override
            public View getView(int position, View convertView) {
                ImageView mImageView=null;
                if(convertView==null){
                    mImageView=new ImageView(recyclerView.getContext());
                }else {
                    mImageView= (ImageView) convertView;
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
        recyclerView.addHeaderView(headerView);

    }
}
