package github.com.materialdesignproject.fragment;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.widget.NestedScrollView;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import github.com.materialdesignproject.R;
import github.com.materialdesignproject.SimpleCoordinatorLayoutActivity;
import github.com.materialdesignproject.data.DataUtil;


/**
 * Email  1562363326@qq.com
 * Github https://github.com/skcodestack
 * Created by sk on 2017/11/28
 * Version  1.0
 * Description:
 */

public class RecyclerViewFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {

    private View rootView;
    private RecyclerView recyclerView;
    private SwipeRefreshLayout refreshLayout;

    private Handler mHandler = new Handler();

    public static RecyclerViewFragment newInstance() {
        RecyclerViewFragment f = new RecyclerViewFragment();
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

        refreshLayout = (SwipeRefreshLayout) rootView.findViewById(R.id.lay_refresh);
        recyclerView = (RecyclerView) rootView.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new GridLayoutManager(recyclerView.getContext(), 1, GridLayoutManager.VERTICAL, false));

        CustomAdapter adapter = new CustomAdapter(recyclerView.getContext(),list);
        recyclerView.setAdapter(adapter);


        refreshLayout.setOnRefreshListener(this);

    }

    @Override
    public void onRefresh() {
        refreshLayout.setRefreshing(true);
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(5000);
                    mHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            refreshLayout.setRefreshing(false);
                        }
                    });
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }


    public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.ViewHolder>{

        Context mContext;
        List<String> list;
        LayoutInflater layoutInflater;

        public CustomAdapter(Context context , List<String> list){
            this.mContext=context;
            this.list = list;
            layoutInflater = LayoutInflater.from(context);
        }

        @Override
        public CustomAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = layoutInflater.inflate(R.layout.layout_item, parent, false);
            return new CustomAdapter.ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(CustomAdapter.ViewHolder holder, int position) {
            TextView item
                    = (TextView) holder.itemView.findViewById(R.id.tv_item);
            item.setText(list.get(position));
        }

        @Override
        public int getItemCount() {
            return list.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder{

            public ViewHolder(View itemView) {
                super(itemView);
            }
        }

    }
}
