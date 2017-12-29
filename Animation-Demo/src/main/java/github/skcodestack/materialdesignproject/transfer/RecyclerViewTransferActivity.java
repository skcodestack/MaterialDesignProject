package github.skcodestack.materialdesignproject.transfer;

import android.animation.Animator;
import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SimpleItemAnimator;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.Window;
import android.view.animation.AccelerateInterpolator;
import android.widget.ImageView;

import github.skcodestack.materialdesignproject.R;
import github.skcodestack.materialdesignproject.adapter.BaseImageAdapter;
import github.skcodestack.materialdesignproject.adapter.ViewHolder;
import github.skcodestack.materialdesignproject.data.DataUtil;

public class RecyclerViewTransferActivity extends AppCompatActivity {


    Activity mActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);
        setContentView(R.layout.activity_recycler_view_transfer);
        this.mActivity = this;
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerview);

        BaseImageAdapter adapter = new BaseImageAdapter(this, DataUtil.obtainRandomData(50),R.layout.item_layout) {
            @Override
            public void onBindViewHolder(ViewHolder holder, int position) {
                ImageView imageView
                        = holder.getView(R.id.iv);



                imageView.setImageResource(R.drawable.meinv3);
                holder.setOnClickListener(R.id.iv,new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ActivityOptionsCompat optionsCompat = ActivityOptionsCompat.makeSceneTransitionAnimation(mActivity, v,mActivity.getResources().getString(R.string.transitionName) );
                        Intent intent = new Intent(mActivity, RecyclerTransferDetailActivity.class);
                        mActivity.startActivity(intent,optionsCompat.toBundle());
                    }
                });
            }
        } ;

        GridLayoutManager layoutManager = new GridLayoutManager(recyclerView.getContext(),2);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
//        recyclerView.setItemAnimator(new DefaultItemAnimator());
    }



}
