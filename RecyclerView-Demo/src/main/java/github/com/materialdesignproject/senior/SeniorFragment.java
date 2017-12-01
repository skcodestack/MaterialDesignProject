package github.com.materialdesignproject.senior;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.List;

import github.com.materialdesignproject.DataUtil;
import github.com.materialdesignproject.R;
import github.com.materialdesignproject.middle.MiddleFragment;
import github.skcodestack.banner.BannerAdapter;
import github.skcodestack.banner.BannerView;
import github.skcodestack.recyclerview.base.ViewHolder;
import github.skcodestack.recyclerview.section.SectionRVAdapter;
import github.skcodestack.recyclerview.section.StatelessSection;
import github.skcodestack.recyclerview.wrapper.WrapperRecyclerView;

/**
 * Email  1562363326@qq.com
 * Github https://github.com/skcodestack
 * Created by sk on 2017/11/28
 * Version  1.0
 * Description:
 */

public class SeniorFragment extends Fragment {

    private View rootView;
    private WrapperRecyclerView recyclerView;

    public static SeniorFragment newInstance() {
        SeniorFragment f = new SeniorFragment();
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
        recyclerView = (WrapperRecyclerView) rootView.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new GridLayoutManager(recyclerView.getContext(), 6, GridLayoutManager.VERTICAL, false));
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

        recyclerView.setAdapter(adapter);

    }








}
