package github.com.materialdesignproject.twitter;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import github.com.materialdesignproject.DataUtil;
import github.com.materialdesignproject.R;
import github.com.materialdesignproject.middle.MiddleFragment;
import github.com.materialdesignproject.senior.HeaderSection;
import github.com.materialdesignproject.senior.ItemSection;
import github.skcodestack.recyclerview.adapter.CommonAdapter;
import github.skcodestack.recyclerview.base.ViewHolder;
import github.skcodestack.recyclerview.section.SectionRVAdapter;
import github.skcodestack.recyclerview.swipe.OnLoadMoreListener;
import github.skcodestack.recyclerview.swipe.OnRefreshListener;
import github.skcodestack.recyclerview.swipe.SwipeToLoadLayout;
import github.skcodestack.recyclerview.wrapper.WrapperRecyclerView;

/**
 * Email  1562363326@qq.com
 * Github https://github.com/skcodestack
 * Created by sk on 2017/12/6
 * Version  1.0
 * Description:
 */

public class TwitterFragment extends Fragment implements OnRefreshListener, OnLoadMoreListener {

    private View rootView;
    private RecyclerView recyclerView;
    private SwipeToLoadLayout swipeToLoadLayout;

    private int mType = 2;
    private List<String> list;

    public static TwitterFragment newInstance() {
        TwitterFragment f = new TwitterFragment();
        return f;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_twitter_recycler, container, false);
        return rootView;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        list = DataUtil.obtainRandomData(10);
        swipeToLoadLayout = (SwipeToLoadLayout) view.findViewById(R.id.swipeToLoadLayout);
        recyclerView = (RecyclerView) view.findViewById(R.id.swipe_target);
        RecyclerView.LayoutManager layoutManager = null;
        if (mType == 1) {
            layoutManager = new LinearLayoutManager(getContext());
        } else if (mType == 2) {
            layoutManager = new GridLayoutManager(getContext(), 6);
        } else if (mType == 3) {
            layoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        }

        SectionRVAdapter adapter = new SectionRVAdapter(recyclerView.getContext());
        HeaderSection headerSection = new HeaderSection(recyclerView.getContext());
        adapter.addSection(headerSection);


        ItemSection section1 = new ItemSection(recyclerView.getContext(),R.layout.section_head_layout,
                R.layout.item1_layout,"第一分组",DataUtil.obtainRandomData(4));
        adapter.addSection(section1);

        ItemSection section2 = new ItemSection(recyclerView.getContext(),R.layout.section_head_layout,
                R.layout.item1_layout,"第二分组",3,DataUtil.obtainRandomData(9));
        adapter.addSection(section2);

        ItemSection section3 = new ItemSection(recyclerView.getContext(),R.layout.section_head_layout,
                R.layout.item1_layout,"第三分组",2,list);
        adapter.addSection(section3);

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

        swipeToLoadLayout.setOnRefreshListener(this);
        swipeToLoadLayout.setOnLoadMoreListener(this);

        recyclerView.setOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                if (newState == RecyclerView.SCROLL_STATE_IDLE ){
                    if (!ViewCompat.canScrollVertically(recyclerView, 1)){
                        swipeToLoadLayout.setLoadingMore(true);
                    }
                }
            }
        });
    }
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        swipeToLoadLayout.post(new Runnable() {
            @Override
            public void run() {
                swipeToLoadLayout.setRefreshing(true);
            }
        });
    }


    @Override
    public void onPause() {
        super.onPause();
//        App.getRequestQueue().cancelAll(TAG + "refresh" + mType);
//        App.getRequestQueue().cancelAll(TAG + "loadmore" + mType);
        if (swipeToLoadLayout.isRefreshing()) {
            swipeToLoadLayout.setRefreshing(false);
        }
        if (swipeToLoadLayout.isLoadingMore()) {
            swipeToLoadLayout.setLoadingMore(false);
        }
//        mAdapter.stop();
    }

    @Override
    public void onRefresh() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                list.add(0,"dfdf");
                recyclerView.getAdapter().notifyDataSetChanged();
                swipeToLoadLayout.setRefreshing(false);
            }
        }, 3000);
    }

    @Override
    public void onLoadMore() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                list.add("dfdf");
                recyclerView.getAdapter().notifyDataSetChanged();
                swipeToLoadLayout.setLoadingMore(false);
            }
        }, 3000);
    }
}
