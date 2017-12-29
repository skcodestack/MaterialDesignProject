package github.skcodestack.materialdesignproject;

import android.graphics.Canvas;
import android.graphics.Color;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;

import java.util.Collections;
import java.util.List;

import github.skcodestack.materialdesignproject.recyclerview.AlbumItemTouchHelperCallBack;
import github.skcodestack.materialdesignproject.recyclerview.BaseRecyclerAdapter;
import github.skcodestack.materialdesignproject.recyclerview.layoutmanager.AlbumLayoutManager;

public class AlbumActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private BaseRecyclerAdapter adapter;
    private List<String> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_album2);
//        list = DataUtil.obtainRandomData(6);
//        recyclerView = (RecyclerView) findViewById(R.id.recyclerview);
//        adapter = new BaseRecyclerAdapter(this, list);
//        recyclerView.setLayoutManager(new AlbumLayoutManager());
//        recyclerView.setAdapter(adapter);
//
//        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new AlbumItemTouchHelperCallBack<String>(adapter,list));
//
//
//
//        itemTouchHelper.attachToRecyclerView(recyclerView);


    }
}
