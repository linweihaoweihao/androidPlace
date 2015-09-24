package com.fashion.controller;

import android.R.integer;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.view.View.OnClickListener;

import com.androidquery.AQuery;
import com.fashion.R;
import com.fashion.view.Fragment1;
import com.fashion.view.Fragment2;
import com.fashion.view.Fragment3;
import com.fashion.view.Fragment4;
import com.fashion.view.Fragment5;
import com.fashion.view.ProductListFragment;

public class HomeController {
	private AQuery aQuery;
	private FragmentActivity mActivity;
	private Fragment tempFragment;
	private FragmentManager fm;
	private Fragment productListFragment;
	private Fragment fragment2;
	private Fragment fragment3;
	private Fragment fragment4;
	private Fragment fragment5;

	public HomeController(FragmentActivity fActivity) {
		this.mActivity = fActivity;
		init();
	}
	private int[] tab = new int[] { R.id.tab_main, R.id.tab_loan,
//			R.id.tab_invest,
			R.id.tab_account, R.id.tab_setting

	};
	private void init() {
		// TODO Auto-generated method stub
		aQuery=new AQuery(mActivity);
		fm=mActivity.getSupportFragmentManager();
		initfragment();
		for (int id : tab) {
			aQuery.id(id).clicked(listener);
		}
		listener.onClick(aQuery.id(R.id.tab_main).getView());
	}
	private void initfragment() {
		// TODO Auto-generated method stub
		productListFragment=new ProductListFragment();
		fragment2=new Fragment2();
		fragment3=new Fragment3();
		fragment4=new Fragment4();
		fragment5=new Fragment5();
	}
//	public void goInvest() {
//		listener.onClick(mAQuery.id(R.id.tab_invest).getView());
//	}
//	
//	public void goLoan() {
//		listener.onClick(mAQuery.id(R.id.tab_loan).getView());
//	}
OnClickListener listener=new OnClickListener() {
	
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		for (int i = 0; i < tab.length; i++) {
			if (tab[i]==v.getId()) {
				aQuery.id(tab[i]).checked(true);
				
			}else {
				aQuery.id(tab[i]).checked(false);
			}
		}
		switch (v.getId()) {
		case R.id.tab_main:
			switchContent(productListFragment);
			break;

		case R.id.tab_loan:
			switchContent(fragment2);
			break;
			
//		case R.id.tab_invest:
//			switchContent(fragment3);
//			break;
//			
		case R.id.tab_account:
			switchContent(fragment4);
			break;
		case R.id.tab_setting:
			switchContent(fragment5);
			break;
		}
	}

	private void switchContent(Fragment mcontent) {
		// TODO Auto-generated method stub
		FragmentTransaction ft=fm.beginTransaction();
		if (tempFragment==null) {
			ft.add(R.id.frame_content, mcontent).commit();
			
		}else {
			if (tempFragment!=mcontent) {
				if (mcontent.isAdded()) {
					ft.hide(tempFragment).show(mcontent).commitAllowingStateLoss();
				}else {
					ft.hide(tempFragment).add(R.id.frame_content, mcontent).commitAllowingStateLoss();
				}
			
		}
			
		}
		tempFragment=mcontent;
	}
};
	
}
