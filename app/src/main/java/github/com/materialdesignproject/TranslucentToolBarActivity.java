package github.com.materialdesignproject;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class TranslucentToolBarActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_translucent_tool_bar);

        toolbar = (Toolbar) findViewById(R.id.toolbar);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerview);

        List<String> list = initData();

        CustomAdapter adapter = new CustomAdapter(this,list);
        recyclerView.setAdapter(adapter);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {

            int countDy = 0;

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                int scrollY = countDy+=dy;
//                int scrollY = recyclerView.getScrollY();
                Log.e("addOnScrollListener","====>"+scrollY);
                int screen_height = recyclerView.getContext().getResources().getDisplayMetrics().heightPixels;
                int height = screen_height / 3;
                if(scrollY <= height){
                    float percent = scrollY * 1.0f / height;
                    toolbar.setAlpha(1-percent);

                }else {
//                    toolbar.setAlpha(1);
                }

            }
        });
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
