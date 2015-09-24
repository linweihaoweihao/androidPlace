package com.fashion.view;


import com.androidquery.AQuery;
import com.fashion.R;
import com.fashion.adater.TestAdapter;
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

public class Fragment1 extends Fragment implements OnLoadMore{
	private AQuery mAQuery;
	private SwipeRefreshLayout mSwRefreshLayout;
	private TestAdapter mAdapter;
	private LoadMoreListView mListView;
@Override
public View onCreateView(LayoutInflater inflater, ViewGroup container,
		Bundle savedInstanceState) {
	// TODO Auto-generated method stub
	View view=inflater.inflate(R.layout.activity_main,container, false);
	mAQuery = new AQuery(view);
	return view;
}

@Override
public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
	// TODO Auto-generated method stub
	super.onViewCreated(view, savedInstanceState);
	initView();
}

private void initView() {
	// TODO Auto-generated method stub
	mSwRefreshLayout = (SwipeRefreshLayout)getActivity().findViewById(R.id.swipeRefreshLayout); 
	mSwRefreshLayout.setColorSchemeResources(R.color.bg_icon,
			R.color.btn_yellow, R.color.bg_icon, R.color.title_bar);
	mSwRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
		@Override
		public void onRefresh() {
			mAdapter.getPullDate(113.3829777, 23.1255555, 10000, 100);
		}
	});
	
	mListView = (LoadMoreListView) getActivity().findViewById(R.id.listView);
	mListView.setLoadMoreListen(this);
	mAdapter = new TestAdapter(getActivity() , mListView , mSwRefreshLayout);
	mListView.setAdapter(mAdapter);
	mSwRefreshLayout.post(new Runnable() {
	        @Override
	        public void run() {
	        	mSwRefreshLayout.setRefreshing(true);
	        	mAdapter.getDate(113.3829777, 23.1255555, 10000, 100, true);
	        }
	    });
}

@Override
public void loadMore() {
	// TODO Auto-generated method stub
	mAdapter.getPushDate(113.3829777, 23.1255555, 10000, 100);
}
}
