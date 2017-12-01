package github.com.materialdesignproject.primary;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;

import java.util.List;

import github.com.materialdesignproject.DataUtil;
import github.com.materialdesignproject.R;
import github.skcodestack.recyclerview.adapter.CommonAdapter;
import github.skcodestack.recyclerview.base.ViewHolder;
import github.skcodestack.recyclerview.refresh.container.AliFooter;
import github.skcodestack.recyclerview.refresh.container.AliHeader;
import github.skcodestack.recyclerview.refresh.widget.RefreshLoadMoreWrapper;
import github.skcodestack.recyclerview.swipe.OnLoadMoreListener;
import github.skcodestack.recyclerview.swipe.OnRefreshListener;
import github.skcodestack.recyclerview.swipe.SwipeToLoadLayout;
import github.skcodestack.recyclerview.wrapper.LoadMoreWrapper;

/**
 * Email  1562363326@qq.com
 * Github https://github.com/skcodestack
 * Created by sk on 2017/11/28
 * Version  1.0
 * Description:
 */

public class PrimaryFragment extends Fragment  {

    private View rootView;
    private RecyclerView recyclerView;
    private List<String> list;
    private CommonAdapter<String> commonAdapter;
    private RefreshLoadMoreWrapper refreshLoadMoreWrapper;

    public static PrimaryFragment newInstance() {
        PrimaryFragment f = new PrimaryFragment();
        return f;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_recycler_refresh, container, false);
        initBase();
        return rootView;
    }

    private void initBase() {
        list = DataUtil.obtainRandomData(100);
        recyclerView = (RecyclerView) rootView.findViewById(R.id.recyclerView);
        refreshLoadMoreWrapper = (RefreshLoadMoreWrapper) rootView.findViewById(R.id.refreshWarapper);
        recyclerView.setLayoutManager(new GridLayoutManager(recyclerView.getContext(), 2, GridLayoutManager.VERTICAL, false));

        commonAdapter = new CommonAdapter<String>(recyclerView.getContext(), R.layout.item1_layout, list) {
            @Override
            protected void convert(ViewHolder holder, String o, int position) {
                holder.setText(R.id.desc, o);
                holder.setImageByUrl(R.id.image, DataUtil.getImageUrl());
            }

        };
        recyclerView.setAdapter(commonAdapter);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        refreshLoadMoreWrapper.setType(RefreshLoadMoreWrapper.Type.FOLLOW);
        refreshLoadMoreWrapper.setListener(new RefreshLoadMoreWrapper.OnFreshListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        list.add(0,"dfdf");
                        recyclerView.getAdapter().notifyDataSetChanged();
                        refreshLoadMoreWrapper.onFinishFreshAndLoad();
                    }
                }, 1000);
            }

            @Override
            public void onLoadmore() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        list.add("dfdf");
                        recyclerView.getAdapter().notifyDataSetChanged();
                        refreshLoadMoreWrapper.onFinishFreshAndLoad();
                    }
                }, 1000);
            }
        });
        refreshLoadMoreWrapper.setHeader(new AliHeader(recyclerView.getContext(), R.drawable.ali, true));   //参数为：logo图片资源，是否显示文字
        refreshLoadMoreWrapper.setFooter(new AliFooter(recyclerView.getContext(), false));


        LayoutAnimationController animation = AnimationUtils.loadLayoutAnimation(recyclerView.getContext(), R.anim.layout_animation_from_bottom);
        recyclerView.setLayoutAnimation(animation);
    }



}
