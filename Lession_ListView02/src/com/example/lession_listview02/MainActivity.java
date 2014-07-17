
package com.example.lession_listview02;

import android.os.Bundle;
import android.app.ListActivity;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends ListActivity {

	private String[] name = {"Jhone","Smith","Jack","Owen","Wasention","Cage"};
	
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        //setContentView(R.layout.activity_main);
        
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,name);
        
        setListAdapter(adapter);
        
    }
    

    @Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		Toast.makeText(MainActivity.this, name[position], Toast.LENGTH_SHORT).show();
	}



	@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
}
