package com.example.test_ichartjs;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.webkit.WebView;

public class MainActivity extends Activity {

	WebView wv = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		wv = (WebView) findViewById(R.id.wv);  
        wv.getSettings().setJavaScriptEnabled(true);  //设置WebView支持javascript  
        wv.getSettings().setUseWideViewPort(true);//设置是当前html界面自适应屏幕  
        wv.getSettings().setSupportZoom(true); //设置支持缩放  
        wv.getSettings().setBuiltInZoomControls(true);//显示缩放控件  
        wv.getSettings().setLoadWithOverviewMode(true);  
        wv.requestFocus();  
        wv.loadUrl("file:///android_asset/mianji_chart.html"); //加载assert目录下的文件 
		
	}

}
