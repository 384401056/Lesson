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
        wv.getSettings().setJavaScriptEnabled(true);  //����WebView֧��javascript  
        wv.getSettings().setUseWideViewPort(true);//�����ǵ�ǰhtml��������Ӧ��Ļ  
        wv.getSettings().setSupportZoom(true); //����֧������  
        wv.getSettings().setBuiltInZoomControls(true);//��ʾ���ſؼ�  
        wv.getSettings().setLoadWithOverviewMode(true);  
        wv.requestFocus();  
        wv.loadUrl("file:///android_asset/mianji_chart.html"); //����assertĿ¼�µ��ļ� 
		
	}

}
