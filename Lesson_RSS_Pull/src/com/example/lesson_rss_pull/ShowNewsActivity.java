package com.example.lesson_rss_pull;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.widget.TextView;

public class ShowNewsActivity extends Activity {

	private TextView descriptionView,pubDataView,titleView,linkView;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_show_news);
		
		titleView = (TextView)findViewById(R.id.tvTitle);
		pubDataView = (TextView)findViewById(R.id.tvDate);
		descriptionView = (TextView)findViewById(R.id.tvDescp);
		linkView = (TextView)findViewById(R.id.tvLink);
		
		Intent intent = getIntent();
		
		String title = intent.getStringExtra("title");
		String pubData = intent.getStringExtra("pubData");
		String description = intent.getStringExtra("description");
		String link = intent.getStringExtra("link");
		
		titleView.setText(formatString(title));
		pubDataView.setText(formatString(pubData));
		descriptionView.setText(formatString(description));
		linkView.setText(formatString(link));
		
	}
	
	public static String formatString(String str){
		Pattern p = Pattern.compile("\\s*|\t|\n");
		Matcher m = p.matcher(str);
		return str.trim();
	}
	
}
