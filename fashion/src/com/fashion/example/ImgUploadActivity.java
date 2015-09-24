package com.fashion.example;

import java.io.File;

import org.json.JSONObject;

import com.androidquery.AQuery;
import com.androidquery.callback.AjaxCallback;
import com.androidquery.callback.AjaxStatus;
import com.fashion.R;
import com.qiniu.android.http.ResponseInfo;
import com.qiniu.android.storage.UpCompletionHandler;
import com.qiniu.android.storage.UpProgressHandler;
import com.qiniu.android.storage.UploadManager;
import com.qiniu.android.storage.UploadOptions;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;

public class ImgUploadActivity extends Activity {
	AQuery mAQuery;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.imgupload);
		mAQuery = new AQuery(this);
		mAQuery.id(R.id.uploadbtn).clicked(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				String url = "http://192.168.1.102:3001/getuptoken?bucketname=lushang";
				mAQuery.ajax(url, String.class, new AjaxCallback<String>(){
					@Override
					public void callback(String url, String object,
							AjaxStatus status) {
						// TODO Auto-generated method stub
						super.callback(url, object, status);
						upLoadImg(object);
					}
				});
			}
		});
		String path = "storage/sdcard0/DCIM/Camera/IMG20150521234911.jpg";
		File file = new File(path);
		mAQuery.id(R.id.img).image(file, 200);
	}
	
	protected void upLoadImg(String token) {
		// TODO Auto-generated method stub
		UploadManager uploadManager = new UploadManager();
		String path = "storage/sdcard0/DCIM/Camera/IMG_20140729_210931.jpg";
//		String path = "storage/sdcard0/DCIM/Camera/IMG20150521234911.jpg";
		File data = new File(path);
		String key = "androidImg";
		UpCompletionHandler uHandler = new UpCompletionHandler() {
			
			@Override
			public void complete(String key, ResponseInfo info, JSONObject res) {
				// TODO Auto-generated method stub
				 mAQuery.id(R.id.txt).text("key..."+key+"..info.."+info.isOK()+"..res..."+res);
			}
		};
		uploadManager.put(data, key, token, uHandler,
			    new UploadOptions(null, null, false,
			        new UpProgressHandler(){
			            public void progress(String key, double percent){
			                Log.i("qiniu", key + ": " + percent);
			                mAQuery.id(R.id.txt).text(key + ": " + percent);
			            }
			        }, null));
	}
	
	
}
