package github.com.materialdesignproject.custom.qq5;

import java.util.Random;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import github.com.materialdesignproject.R;
import github.com.materialdesignproject.data.Cheeses;

public class LeftFrament extends BaseFrament {

	private ListView left_content;
	public LeftFrament(Activity ctx) {
		super(ctx);
	}

	@Override
	public View initView() {
		View inflate = View.inflate(mActivity, R.layout.left_layout,null);
		left_content = (ListView) inflate.findViewById(R.id.lv_left);
		
		return inflate;
	}
	@Override
	public void initDate() {
		super.initDate();
		left_content.setAdapter(new ArrayAdapter<String>(mActivity, android.R.layout.simple_list_item_1, Cheeses.sCheeseStrings){
				@Override
				public View getView(int position,
						View convertView, ViewGroup parent) {
					View  view=super.getView(position, convertView, parent);
					TextView tv= (TextView) view;
					tv.setTextColor(Color.WHITE);
					return view;
				}
		});
	}

	public void scrolListView() {
		Random rand=new Random();
		 int position=rand.nextInt(50);
		left_content.smoothScrollToPosition(position);
		
	}

	
	
}
