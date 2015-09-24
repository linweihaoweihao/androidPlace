package com.fashion.view;

import com.alibaba.sdk.android.AlibabaSDK;
import com.alibaba.sdk.android.ResultCode;
import com.alibaba.sdk.android.trade.ItemService;
import com.alibaba.sdk.android.trade.callback.TradeProcessCallback;
import com.alibaba.sdk.android.trade.model.TaokeParams;
import com.alibaba.sdk.android.trade.model.TradeResult;
import com.androidquery.AQuery;
import com.fashion.R;
import com.fashion.adater.ProductListAdapter;
import com.fashion.lib.LoadMoreListView;
import com.fashion.lib.LoadMoreListView.OnLoadMore;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v4.widget.SwipeRefreshLayout.OnRefreshListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

public class ProductListFragment extends Fragment implements OnLoadMore {
	private AQuery mAQuery;
	private SwipeRefreshLayout mSwRefreshLayout;
	private ProductListAdapter mAdapter;
	private LoadMoreListView mListView;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view = inflater.inflate(R.layout.fragment_goods_list, container, false);
		mAQuery = new AQuery(view);
		return view;
	}

	@Override
	public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onViewCreated(view, savedInstanceState);
		initView();
		TaokeParams taokeParams = new TaokeParams();
		taokeParams.pid = "mm_26890094_0_0";

		
		AlibabaSDK.getService(ItemService.class).showTaokeItemDetailByItemId(getActivity(), new TradeProcessCallback() {

            @Override
            public void onPaySuccess(TradeResult tradeResult) {
                Toast.makeText(getActivity(), "支付成功", Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onFailure(int code, String msg) {
                if (code == ResultCode.QUERY_ORDER_RESULT_EXCEPTION.code) {
                    Toast.makeText(getActivity(), "确认交易订单失败", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getActivity(), "交易取消" + code, Toast.LENGTH_SHORT).show();
                }
                Toast.makeText(getActivity(), "交易shibai..." + code, Toast.LENGTH_SHORT).show();
            }

        }, null, Long.parseLong("2100502266765"), 1, null, taokeParams);
	}

	private void initView() {
		// TODO Auto-generated method stub
		mSwRefreshLayout = (SwipeRefreshLayout) getActivity().findViewById(
				R.id.swipeRefreshLayout);
		mSwRefreshLayout.setColorSchemeResources(R.color.bg_icon,
				R.color.btn_yellow, R.color.bg_icon, R.color.title_bar);
		mSwRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
			@Override
			public void onRefresh() {
				
			}
		});

		mListView = (LoadMoreListView) getActivity()
				.findViewById(R.id.listView);
		mListView.setLoadMoreListen(this);
		mAdapter = new ProductListAdapter(getActivity(), mListView, mSwRefreshLayout);
		mListView.setAdapter(mAdapter);
		mSwRefreshLayout.post(new Runnable() {
			@Override
			public void run() {
				mSwRefreshLayout.setRefreshing(true);
				mAdapter.getDate(true);
			}
		});
	}

	@Override
	public void loadMore() {
		// TODO Auto-generated method stub
	}
}
