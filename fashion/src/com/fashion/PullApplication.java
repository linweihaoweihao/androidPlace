package com.fashion;

import com.alibaba.sdk.android.AlibabaSDK;
import com.alibaba.sdk.android.callback.InitResultCallback;
import com.qiniu.android.storage.Configuration;
import com.qiniu.android.storage.KeyGenerator;
import com.qiniu.android.storage.Recorder;
import com.qiniu.android.storage.UploadManager;
import com.qiniu.android.storage.Zone;

import android.app.Application;
import android.widget.Toast;

public class PullApplication extends Application {
	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		initqiniu();
		initBaichun();
	}
	
	private void initBaichun() {
		AlibabaSDK.turnOnDebug(); 
		AlibabaSDK.asyncInit(this, new InitResultCallback() {
			 
            @Override
            public void onSuccess() {
                Toast.makeText(PullApplication.this, "初始化成功", Toast.LENGTH_SHORT)
                        .show();
            }
 
            @Override
            public void onFailure(int code, String message) {
                Toast.makeText(PullApplication.this, "初始化异常", Toast.LENGTH_SHORT)
                        .show();
            }
 
        });
	}

	public void initqiniu(){
		Recorder recorder =null;
		KeyGenerator keyGen = null;
		Configuration config = new Configuration.Builder()
        .chunkSize(256 * 1024)  //分片上传时，每片的大小。 默认 256K
        .putThreshhold(512 * 1024)  // 启用分片上传阀值。默认 512K
        .connectTimeout(10) // 链接超时。默认 10秒
        .responseTimeout(60) // 服务器响应超时。默认 60秒
        .recorder(recorder)  // recorder 分片上传时，已上传片记录器。默认 null
        .recorder(recorder, keyGen)  // keyGen 分片上传时，生成标识符，用于片记录器区分是那个文件的上传记录
        .zone(Zone.zone0) // 设置区域，指定不同区域的上传域名、备用域名、备用IP。默认 Zone.zone0
        .build();
//重用 uploadManager。一般地，只需要创建一个 uploadManager 对象
		UploadManager uploadManager = new UploadManager(config);
	}
}
