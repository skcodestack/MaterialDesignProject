package github.com.materialdesignproject.fragment;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import github.com.materialdesignproject.R;
import github.com.materialdesignproject.data.DataUtil;
import github.com.materialdesignproject.view.NestedViewGroup;


/**
 * Email  1562363326@qq.com
 * Github https://github.com/skcodestack
 * Created by sk on 2017/11/28
 * Version  1.0
 * Description:
 */

public class CustomSwipeRecyclerViewFragment extends Fragment implements  NestedViewGroup.OnRefreshListener {

    private View rootView;
    private RecyclerView recyclerView;

    private Handler mHandler = new Handler();
    private NestedViewGroup refreshView;
    private List<String> list;
    private CustomAdapter adapter;

    public static CustomSwipeRecyclerViewFragment newInstance() {
        CustomSwipeRecyclerViewFragment f = new CustomSwipeRecyclerViewFragment();
        return f;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_custom_swope, container, false);
        initBase();
        return rootView;
    }

    private void initBase() {
        list = DataUtil.obtainRandomData(100);
        refreshView = (NestedViewGroup) rootView.findViewById(R.id.lay_refresh);
        recyclerView = (RecyclerView) rootView.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new GridLayoutManager(recyclerView.getContext(), 1, GridLayoutManager.VERTICAL, false));

        adapter = new CustomAdapter(recyclerView.getContext(), list);
        recyclerView.setAdapter(adapter);

        refreshView.setOnRefreshListener(this);
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
                            list.add(0,"refresh");
                            adapter.notifyDataSetChanged();
                            refreshView.setRefreshing(false);

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
