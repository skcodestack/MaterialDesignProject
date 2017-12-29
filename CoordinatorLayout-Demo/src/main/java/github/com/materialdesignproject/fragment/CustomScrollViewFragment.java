package github.com.materialdesignproject.fragment;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

import github.com.materialdesignproject.R;
import github.com.materialdesignproject.data.DataUtil;
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

public class CustomScrollViewFragment extends Fragment implements onLoadMoreListener, onRefreshListener {

    private NestedViewGroup3 refreshView;
    private Handler mHandler = new Handler();
    private View rootView;
    private List<String> list;

    public static CustomScrollViewFragment newInstance() {
        CustomScrollViewFragment f = new CustomScrollViewFragment();
        return f;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_scrollview_layout, container, false);
        initBase();
        return rootView;
    }

    private void initBase() {
        list = DataUtil.obtainRandomData(100);
        refreshView = (NestedViewGroup3) rootView.findViewById(R.id.lay_refresh);


        LayoutInflater layoutInflater = LayoutInflater.from(refreshView.getContext());
        View headerView = layoutInflater.inflate(R.layout.layout_twitter_header, null);
        View footerView = layoutInflater.inflate(R.layout.layout_classic_footer, null);
        refreshView.setRefreshHeaderView(headerView);
        refreshView.setLoadMoreFooterView(footerView);

        refreshView.setonRefreshListener(this);
        refreshView.setonLoadMoreListener(this);
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

                            refreshView.setRefreshing(false);

                        }
                    });
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }


    public class  CustomAdapter extends BaseAdapter{

        private List<String> mList;
        private Context mContent;
        private LayoutInflater layoutInflater;

        public CustomAdapter(Context context , List<String> list){
            this.mList = list;
            this.mContent = context;
            layoutInflater = LayoutInflater.from(mContent);
        }

        @Override
        public int getCount() {
            return mList.size();
        }

        @Override
        public Object getItem(int position) {
            return mList.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View view = layoutInflater.inflate(R.layout.layout_item, null);
            TextView textView = (TextView) view.findViewById(R.id.tv_item);
            textView.setText(mList.get(position));
            return view;
        }
    }


}
