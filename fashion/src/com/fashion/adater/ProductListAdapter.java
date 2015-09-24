package com.fashion.adater;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.alibaba.sdk.android.AlibabaSDK;
import com.alibaba.sdk.android.trade.ItemService;
import com.alibaba.sdk.android.trade.callback.TradeProcessCallback;
import com.alibaba.sdk.android.trade.model.TaokeParams;
import com.alibaba.sdk.android.trade.model.TradeResult;
import com.androidquery.AQuery;
import com.androidquery.callback.AjaxCallback;
import com.androidquery.callback.AjaxStatus;
import com.fashion.R;
import com.fashion.lib.LoadMoreListView;
import com.fashion.model.Product;

public class ProductListAdapter extends BaseAdapter {

	Activity mContext;
	ArrayList<Product> mItems = new ArrayList<Product>();
	private LayoutInflater minflater;
	AQuery mAQuery;
	LoadMoreListView mlistView;
	SwipeRefreshLayout mSwipeRefreshLayout;
	String search = "%E8%BF%9E%E8%A1%A3%E8%A3%99";
	public ProductListAdapter(Activity mContext, LoadMoreListView listView,
			SwipeRefreshLayout swipeRefreshLayout) {
		this.mContext = mContext;
		this.mAQuery = new AQuery(mContext);
		this.minflater = LayoutInflater.from(mContext);
		this.mlistView = listView;
		this.mSwipeRefreshLayout = swipeRefreshLayout;
	}

	public void getDate(final boolean isPush) {
		String urlString = "http://shishangliu.wx.jaeapp.com/getProductInfo?title="+search;
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
				ArrayList<Product> items = JSON.parseObject(result,
						new TypeReference<ArrayList<Product>>() {
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

	

	@Override
	public int getCount() {
		return mItems.size();
	}

	@Override
	public Product getItem(int position) {
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
		Product product = getItem(position);
		holder.test.setText(product.title);
		convertView.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				ItemService service = AlibabaSDK.getService(ItemService.class);
				TaokeParams taokeParams = new TaokeParams();
				taokeParams.pid = "mm_26890094_0_0";
				service.showTaokeItemDetailByOpenItemId(mContext, new TradeProcessCallback() {
					
					@Override
					public void onFailure(int arg0, String arg1) {
						Log.e("xkz_er","err.."+ arg1);
					}
					
					@Override
					public void onPaySuccess(TradeResult arg0) {
						
						
					}
				}, null, getItem(position).open_iid, 2, null, taokeParams);
			}
		});
		return convertView;
	}
}
