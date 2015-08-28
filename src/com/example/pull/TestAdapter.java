package com.example.pull;

import java.util.ArrayList;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.androidquery.AQuery;
import com.androidquery.callback.AjaxCallback;
import com.androidquery.callback.AjaxStatus;
import com.example.pull.model.People;

import android.R.integer;
import android.content.Context;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

public class TestAdapter extends BaseAdapter {

	Context mContext;
	ArrayList<People> mItems = new ArrayList<People>();
	private LayoutInflater minflater;
	AQuery mAQuery;
	LoadMoreListView mlistView;
	SwipeRefreshLayout mSwipeRefreshLayout;

	public TestAdapter(Context mContext, LoadMoreListView listView,
			SwipeRefreshLayout swipeRefreshLayout) {
		this.mContext = mContext;
		this.mAQuery = new AQuery(mContext);
		this.minflater = LayoutInflater.from(mContext);
		this.mlistView = listView;
		this.mSwipeRefreshLayout = swipeRefreshLayout;
	}

	public void getDate(double j, double w, int max, int min,
			final boolean isPush) {
		String urlString = "http://192.168.1.128:3001/pfind?j=" + j + "&w=" + w
				+ "&max=" + max + "&min=" + min;
		mAQuery.ajax(urlString, String.class, new AjaxCallback<String>() {
			@Override
			public void callback(String url, String result, AjaxStatus status) {

				if (status.getCode() != 200) {
					Toast.makeText(mContext, "网络出错！", Toast.LENGTH_SHORT)
							.show();
					mSwipeRefreshLayout.setRefreshing(false);
					mlistView.onLoadComplete();
					return;
				}

				if (!isPush) {
					mItems.clear();
				}
				ArrayList<People> items = JSON.parseObject(result,
						new TypeReference<ArrayList<People>>() {
						});
				mItems.addAll(items);
				notifyDataSetChanged();
				mlistView.onLoadComplete();

				mSwipeRefreshLayout.setRefreshing(false);
				// for (int i = 0; i < result.length(); i++) {
				// try {
				// JSONObject jsonObject = result.getJSONObject(i);
				// JSONObject obj = jsonObject.getJSONObject("obj");
				// String name = obj.getString("name");
				// double dis = jsonObject.getDouble("dis");
				// mItems.add(name + ":" + dis);
				// } catch (JSONException e) {
				// e.printStackTrace();
				// }
				// }
			}
		});
	}

	public void getPullDate(double j, double w, int max, int min) {
		getDate(j, w, max, min, false);
	}

	public void getPushDate(double j, double w, int max, int min) {
		getDate(j, w, max, min, true);
	}

	@Override
	public int getCount() {
		return mItems.size();
	}

	@Override
	public People getItem(int position) {
		// TODO Auto-generated method stub
		return mItems.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	class Holder {
		private TextView test;
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		final Holder holder;

		if (convertView == null) {
			holder = new Holder();
			convertView = minflater.inflate(R.layout.item, null);
			holder.test = (TextView) convertView.findViewById(R.id.test);
			convertView.setTag(holder);
		} else {
			holder = (Holder) convertView.getTag();
		}
		People people = getItem(position);
		holder.test.setText(people.getObj().name + "");
		return convertView;
	}
}
