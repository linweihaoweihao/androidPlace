package com.fashion.view;

import com.androidquery.AQuery;
import com.fashion.R;
import com.fashion.controller.HomeController;

import android.os.Bundle;
import android.app.Activity;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;

public class HomeActivity extends FragmentActivity {
	HomeController homeController;
	AQuery aQuery;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home);
		homeController=new HomeController(this);
	}

}
