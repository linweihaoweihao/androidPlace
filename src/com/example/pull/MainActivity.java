package com.example.pull;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v4.widget.SwipeRefreshLayout.OnRefreshListener;
import com.androidquery.AQuery;
import com.example.pull.LoadMoreListView.OnLoadMore;

public class MainActivity extends Activity implements OnLoadMore {
	SwipeRefreshLayout swipeRefreshLayout;
	LoadMoreListView listView;
	TestAdapter adapter;
	AQuery aq;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipeRefreshLayout);
		aq = new AQuery(this);
		swipeRefreshLayout.setColorSchemeResources(R.color.bg_icon,
				R.color.btn_yellow, R.color.bg_icon, R.color.title_bar);
		swipeRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
			@Override
			public void onRefresh() {
				adapter.getPullDate(113.3829777, 23.1255555, 10000, 100);
			}
		});
		
		listView = (LoadMoreListView) findViewById(R.id.listView);
		listView.setLoadMoreListen(this);
		adapter = new TestAdapter(this , listView , swipeRefreshLayout);
		listView.setAdapter(adapter);
		swipeRefreshLayout.post(new Runnable() {
		        @Override
		        public void run() {
		        	swipeRefreshLayout.setRefreshing(true);
		        	adapter.getDate(113.3829777, 23.1255555, 10000, 100, true);
		        }
		    });

	}

	@Override
	public void loadMore() {
		adapter.getPushDate(113.3829777, 23.1255555, 10000, 100);
	}

}
