
package com.example.lession_menu;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

public class MainActivity extends Activity {

	private static final int OK = 1;
	private static final int CANCLE = 2;
	private static final int ABOUT = 3;
	

	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        
        
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        
    	menu.add(0, OK, 0, "OK");
    	menu.add(0, CANCLE, 0, "CANCLE");
    	Menu file = menu.addSubMenu(0, ABOUT, 0, "鍏充簬");
    	
    	
    	MenuInflater inflater = getMenuInflater();
    	inflater.inflate(R.menu.menu, file);

        return true;
    }



	@Override
	public boolean onOptionsItemSelected(MenuItem item) {

		switch (item.getItemId()) {
		case OK:
			this.setTitle("OK");
			return true;
		case CANCLE:
			this.setTitle("CANCLE");
			return true;
		case ABOUT:
			this.setTitle("ABOUT");
			return true;
		case R.id.help:
			this.setTitle("help");
			return true;
		case R.id.AboutUs:
			this.setTitle("AboutUs");
			return true;
		default:
			break;
		}
		
		return false;
	}
    
    
    
}
