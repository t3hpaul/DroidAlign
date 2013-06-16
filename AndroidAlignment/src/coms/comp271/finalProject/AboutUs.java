package coms.comp271.finalProject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/*
 * 
 * Author(s): Paul Stasiuk and Zach Romer
 * Class: COMP271
 * Instructor: Dr.Mark V. Albert
 * 
 * A simple class that extends Activity in order to be viewed on an android device. 
 * All this class does is display a very long string describing the appliation and the authors
 * 
 */

public class AboutUs extends Activity{
	 public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.aboutlayout);    
	        
	      //button to take the user back to the home page
	        Button home= (Button) findViewById(R.id.back);
	        home.setOnClickListener( new View.OnClickListener() {

				public void onClick(View v) {
					Intent intent= new Intent();
					setResult(RESULT_OK, intent);
					finish();
				}
			});	        
	        
	 }	
}
