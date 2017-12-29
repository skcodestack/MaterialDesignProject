package github.com.materialdesignproject.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import github.com.materialdesignproject.R;
import github.com.materialdesignproject.view.NestedViewGroup3;
import github.com.materialdesignproject.view.contain.onLoadMoreListener;
import github.com.materialdesignproject.view.contain.onRefreshListener;

/**
 * Email  1562363326@qq.com
 * Github https://github.com/skcodestack
 * Created by sk on 2017/12/12
 * Version  1.0
 * Description:
 */

public class CustomNestedScrollViewFragment extends Fragment implements onLoadMoreListener, onRefreshListener {

    private NestedViewGroup3 refreshView;
    private Handler mHandler = new Handler();
    private View rootView;
    private LinearLayout content;

    public static CustomNestedScrollViewFragment newInstance() {
        CustomNestedScrollViewFragment f = new CustomNestedScrollViewFragment();
        return f;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_viewgroup_layout, container, false);
        initBase();
        return rootView;
    }

    private void initBase() {
        content = (LinearLayout) rootView.findViewById(R.id.content);
        refreshView = (NestedViewGroup3) rootView.findViewById(R.id.lay_refresh);
        LayoutInflater layoutInflater = LayoutInflater.from(refreshView.getContext());
        View headerView = layoutInflater.inflate(R.layout.layout_twitter_header, null);
        View footerView = layoutInflater.inflate(R.layout.layout_classic_footer, null);
        refreshView.setRefreshHeaderView(headerView);
        refreshView.setLoadMoreFooterView(footerView);

        refreshView.setonRefreshListener(this);
        refreshView.setonLoadMoreListener(this);

//        refreshView.setRefreshEnabled(false);
    }


    @Override
    public void onLoadMore() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(5000);
                    mHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            TextView tv = new TextView(content.getContext());
                            tv.setText("loadMore");
                            content.addView(tv);
                            refreshView.setLoadingMore(false);

                        }
                    });
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    @Override
    public void onRefresh() {

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(5000);
                    mHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            TextView tv = new TextView(content.getContext());
                            tv.setText("refresh");
                            content.addView(tv,0);
                            refreshView.setRefreshing(false);

                        }
                    });
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }


}
