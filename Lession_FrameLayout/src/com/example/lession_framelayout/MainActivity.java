
package com.example.lession_framelayout;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.FrameLayout;

public class MainActivity extends Activity {

	FrameLayout frame = null;
	private boolean flag = true;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        frame = (FrameLayout)findViewById(R.id.frame);
		
		
		final MyHandler myhandler = new MyHandler();
		myhandler.sleep(10);
		
		frame.setOnClickListener(new OnClickListener(){
			public void onClick(View arg0) {
				flag = !flag;
				myhandler.sleep(100);
			}
		});
        
    }
    
    
    class MyHandler extends Handler{
		int i = 0;
		public void handleMessage(Message msg) {
			i++;
			show(i%3);
			sleep(100);
		}
		
		public void sleep(long delayMillis){
			if(flag){
				sendMessageDelayed(obtainMessage(0), delayMillis);
			}
		}
		
	}
    

    public void show(int j){
  		Drawable a = getResources().getDrawable(R.drawable.a);
  		Drawable b = getResources().getDrawable(R.drawable.b);
  		Drawable c = getResources().getDrawable(R.drawable.c);
  		
  		switch (j) {
  		case 0:
  			frame.setForeground(a);
  			break;
  		case 1:
  			frame.setForeground(b);
  			break;
  		case 2:
  			frame.setForeground(c);
  			break;
  		default:
  			break;
  		}
  	}
    
    


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
}
