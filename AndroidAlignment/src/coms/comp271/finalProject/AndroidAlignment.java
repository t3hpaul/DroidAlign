package coms.comp271.finalProject;



import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/*
 * Author(s): Paul Stasiuk and Zach Romer
 * Class: COMP271
 * Instructor: Dr.Mark V. Albert
 * 
 * The main class of the application, here three buttons are initialized and onClickListeners are added
 * for each button. The clicking of a button takes the user to a different activity
 * 
 * 
 */


public class AndroidAlignment extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
      //This initiates the needleMan button, and sets an onClick listener to listen for any activity on the screen
        Button needleMan= (Button)findViewById(R.id.Needleman);
        needleMan.setOnClickListener(new View.OnClickListener() {
 			public void onClick(View v) {
 				//Clicking this button will take the user to the Needleman-Wunsch algorithm
 				Intent myIntent= new Intent(v.getContext(), Needleman.class);
 				startActivityForResult(myIntent,0);
 			}
 		});
        
        
      //This initiates the smith button, and sets an onClick listener to listen for any activity on the screen
        Button smith= (Button)findViewById(R.id.Smith);
        smith.setOnClickListener(new View.OnClickListener() {
 			public void onClick(View v) {
 				//Clicking this button will take the user to the Smith-Waterman algorithm
 				Intent myIntent= new Intent(v.getContext(), Smith.class);
 				startActivityForResult(myIntent,0);
 			}
 		});
        
        
      //This initiates the about button, and sets an onClick listener to listen for any activity on the screen
        Button about= (Button)findViewById(R.id.About);
        about.setOnClickListener(new View.OnClickListener() {
 			public void onClick(View v) {
 				//Clicking this button will take the user to the About Us acivity/screen
 				Intent myIntent= new Intent(v.getContext(), AboutUs.class);
 				startActivityForResult(myIntent,0);
 			}
 		});
       
           
    }
}