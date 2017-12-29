package github.com.materialdesignproject;

import android.content.Context;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import github.com.materialdesignproject.behavior.RecyclerFlingBehavior;

public class SimpleCoordinatorLayoutActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple_coordinator_layout);

        RecyclerView recyclerView
                = (RecyclerView) findViewById(R.id.recyclerview);

        CustomAdapter adapter = new CustomAdapter(this,initData());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(recyclerView.getContext()));


        AppBarLayout appBarLayout = (AppBarLayout) findViewById(R.id.main_appbar);

//        RecyclerFlingBehavior behavior = new RecyclerFlingBehavior();
//        CoordinatorLayout.LayoutParams layoutParams
//                = (CoordinatorLayout.LayoutParams) appBarLayout.getLayoutParams();
//        layoutParams.setBehavior(behavior);
    }


    public List<String> initData(){
        List<String> list = new ArrayList<>();
        int count = 100;
        for (int i = 0; i < count; i++) {
            list.add("元素："+i);
        }
        return list;
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
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = layoutInflater.inflate(R.layout.layout_item, parent, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
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
