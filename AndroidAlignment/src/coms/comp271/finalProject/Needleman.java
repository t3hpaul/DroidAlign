package coms.comp271.finalProject;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/* Author(s): Paul Stasiuk and  Zach Romer
* Class: COMP271
* Instructor: Dr.Mark V. Albert
* 
* This is the class that runs the Needleman algorithm. It takes input in from the user(two sequences) then runs then through
* the algorithm. Once the algorithm is finished, we display the matrix IF the user input is less than 14 characters, otherwise
* we still display the aligned sequences, but we go ahead and print it all to a text document and store it on the SD card
* 
*/


public class Needleman extends Activity{
	//The String for the first sequence that is entered
	private String seq1;
	//The EditText for the first string that is entered
	private EditText seq1ET;
	//The String for the second sequence that is entered
	private String seq2;
	//The EditText for the second sequence that is entered
	private EditText seq2ET;
	//The TextView for the final results
	private TextView aligned;
	//The TextView for the alignment Matrix
	private TextView matrix;
	//The array for the matrix to be printed
	private int[][] arrayMatrix;
	//The oringial input of the size of the sequences used to determine if the matrix needs to be printed elsewhere
	private int seq1Size=0;
	private int seq2Size=0;
	//The text file location for the matrix/information print-out
	private String fileLocation="/sdcard/";
	
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	    setContentView(R.layout.needlemanlayout);
	    //Initialize the EditText(s)
	    seq1ET=(EditText)findViewById(R.id.seq1);
	    seq2ET=(EditText)findViewById(R.id.seq2);
	    aligned=(TextView)findViewById(R.id.sequnces);
	    matrix=(TextView)findViewById(R.id.matrix);
	    
	    //Button to take the user back to the home page
        Button home= (Button) findViewById(R.id.back);
        home.setOnClickListener( new View.OnClickListener() {
			public void onClick(View v) {
				Intent intent= new Intent();
				setResult(RESULT_OK, intent);
				finish();
			}
		});	  
        
        //The initialization of the align button and the onClickListener
        Button align=(Button) findViewById(R.id.align);
        align.setOnClickListener( new View.OnClickListener() {
			public void onClick(View v) {
				//When he user clicks the align button, all of the computations are done here..
				//First, we get the text from the EditTexts and put them in strings
				seq1=seq1ET.getText().toString();
				seq1Size=seq1.length();
				seq2=seq2ET.getText().toString();
				seq2Size=seq2.length();
				//Next, we construct the algorithm to run the alignment..
				MatrixNW alignment= new MatrixNW(seq1,seq2,false);
				alignment.fillMatrix(-1,-1,2);
				alignment.findPath();
				alignment.returnAligned();
				arrayMatrix=alignment.printMatrix();
				//Now that we have the sequences lined up, we can go ahead and print them
				seq1=alignment.getAligned1();
				seq2=alignment.getAligned2();
				aligned.setText(seq1+"\n"+seq2);
				printMatrix();
				
			}
		});	  	    
	}
	
	//A Method that prints the matrix in the matrix TextView
	private void printMatrix(){
		String totalMatrix=String.valueOf(arrayMatrix[0][0]);
		for(int y=1; y<arrayMatrix.length;y++ ){
			for(int x=0; x<arrayMatrix[y].length;x++){
				totalMatrix=totalMatrix+String.valueOf(arrayMatrix[y][x]);
			}
			totalMatrix=totalMatrix+"\n";
		}	
		//If the input is to large, then we go ahead and print everything to a text file, still allowing the user to utilize the
		//programs abilities
		if(seq1Size>=14 || seq2Size>=14){
			try{
				String fileName=seq1+seq2;
				fileName=String.valueOf(fileName.hashCode());
				File textOut= new File(fileLocation+fileName);
				FileWriter outputFile= new FileWriter(textOut);
				PrintWriter output=new PrintWriter(outputFile);
				matrix.setText("Sequence matrix is to big to display! Printing to text file to "+ textOut.getAbsolutePath()+ " instead!");
				output.println("Sequence1: "+seq1);
				output.println("Sequence2: "+ seq2);
				output.print(totalMatrix);
			}catch(IOException e){
				Toast.makeText(this, "There was an error pritning to a text file! Do you have an SD card installed?", Toast.LENGTH_LONG);
			}
		}else{	
			matrix.setText(totalMatrix);
		}
	}
	
}
