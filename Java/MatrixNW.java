/**
 * Zachary Romer
 * 
 * MatrixNW builds a 2D array of MatrixNodes, and calculates their scores based on the sequences used.
 * matrix matrix corresponds to the Needleman-Wunch similarity matrix used to determine optimal 
 * sequence alignment
 */
import java.util.Stack;

public class MatrixNW
{
      
    protected int x;
    protected int y;
    protected String seq1;
    protected String seq2;
    private MatrixNode[][] matrix;
    private Stack sequenceAlign1;
    private Stack sequenceAlign2;
    private boolean isSW;
    
    //Constructor: takes input strings, sets type Smith-Watermann or Needleman-Wunch
    public MatrixNW(String in1, String in2, boolean type){  
        
        //Read in sequences, get lengths for matrix sizing
        seq1 = in1;
        seq2 = in2;
        x = seq1.length();
        y = seq2.length();
        isSW = type;
        matrix = new MatrixNode[y+1][x+1];
    }
    
       public void fillMatrix(int mismatch, int gap, int match){
        
           int score, pathX, pathY;
           String outcome;
        //intialize left column 
        for(int a = 0; a <= y; a++){
            if(isSW == true){score = 0;}
            else{score = 0-a;}
            pathX = 0;
            pathY = 0;
            outcome = "EDGE";
            matrix[a][0] = new MatrixNode(score, pathX, pathY, outcome);           
        }
        //initialize top row
        for(int a = 0; a <= x; a++){
            if(isSW){score = 0;}
            else{score = 0-a;}
            pathX = 0;
            pathY = 0;
            outcome = "EDGE";
            matrix[0][a] = new MatrixNode(score, pathX, pathY, outcome);
        }
    
        //Calculate possible scores and choose maximum
        //Check for SW & set scores to 0 if < 0
        for(int a = 1; a <= y; a++){
            System.out.println(" ");
            for(int b = 1; b <= x; b++){
                
                matrix[a][b] = new MatrixNode(0, 0, 0, null);
                
                if(seq1.charAt(b-1) == seq2.charAt(a-1)){
                    score = matrix[a-1][b-1].returnScore() + match;
                    if(isSW == true && score < 0){score = 0;}
                    pathX = b-1;
                    pathY = a-1;
                    outcome = "MATCH";        
                    matrix[a][b].setScore(score, pathX, pathY, outcome);
                }
                else{
                    int scoreUp = matrix[a-1][b].returnScore() + gap;
                    int scoreLeft = matrix[a][b-1].returnScore() + gap;
                    int scoreDia = matrix[a-1][b-1].returnScore() + mismatch;
                    
                    if(scoreUp > scoreLeft && scoreUp > scoreDia){
                        if(isSW == true && scoreUp < 0){scoreUp = 0;}
                        pathX = b;
                        pathY = a-1;
                        outcome = "GAP";
                        matrix[a][b].setScore(scoreUp, pathX, pathY, outcome);
                    }
                    else if(scoreLeft > scoreUp && scoreLeft > scoreDia){
                        if(isSW == true && scoreLeft < 0){scoreLeft = 0;}
                        pathX = b-1;
                        pathY = a;
                        outcome = "GAP";
                        matrix[a][b].setScore(scoreLeft, pathX, pathY, outcome);
                    }
                    else if(scoreDia > scoreUp && scoreDia > scoreLeft){
                        if(isSW == true && scoreDia < 0){scoreLeft = 0;}
                        pathX = b-1;
                        pathY = a-1;
                        outcome = "MISS";
                        matrix[a][b].setScore(scoreDia, pathX, pathY, outcome);
                    }
                }                
            }
        }
    
    }
        
       public void findPath(){
            
            int max = 0;
            int seqX = 0;
            int seqY = 0;
            int place = 0;
            int length = 0;
            sequenceAlign1 = new Stack();
            sequenceAlign2 = new Stack();
           
            //if called as Smith-Watermann, then find maximum.  Else start from end of matrix
           if(isSW == true){ 
               for(int a = 1; a <= seq2.length(); a++){
                    for(int b = 1; b <= seq1.length(); b++){
                        if(matrix[a][b].returnScore() > max){
                            max = matrix[a][b].returnScore();
                            seqX = b;
                            seqY = a;
                        }   
                    }
                }
            }
            else if(!isSW){
                seqX = seq1.length();
                seqY = seq2.length();
            }
            
        //trace back decrementally from maximum coordinate (a,b)
            for(int a = seqY; a > 0;){
                for(int b = seqX; b > 0;){
            
                    //If node is a match or mismatch, pull char from same position in respective
                    //input sequence and insert into alignment
                    //If node is a gap (coordinate pointed to < coordinate of node), insert gap into
                    //appropriate alignment sequence
                    //Decrement until loop reaches edge (x or y > 0)
                    if(isSW == true && matrix[a][b].returnScore() == 0){
                        a = 0;
                        b = 0;
                    }
                    else{
                        if(matrix[a][b].returnOutcome() == "MATCH" || matrix[a][b].returnOutcome() == "MISS"){
                            sequenceAlign1.push(seq1.charAt(b-1));
                            sequenceAlign2.push(seq2.charAt(a-1));
                            a--;
                            b--;
                        }
                        else{
                            if(matrix[a][b].returnPathX() < b){
                                sequenceAlign1.push(seq1.charAt(b-1));
                                sequenceAlign2.push("-");
                                b--;
                            }
                            else if(matrix[a][b].returnPathY() < a){
                                sequenceAlign1.push("-");
                                sequenceAlign2.push(seq2.charAt(a-1));
                                a--;
                            }
                        }
                    }
                }
            }
            
    }
        
        public MatrixNode[][] returnMatrix(){
            return matrix;
        }
        
        public void printMatrix(){
            for(int y = 0; y < seq2.length() + 1; y++){
                System.out.println("");
                for(int x = 0; x < seq1.length() + 1; x++){
                    System.out.print(matrix[y][x].returnScore());
                }
            }
        }
    
        public void returnAligned(){
            
            System.out.print("\n" + "Sequence 1: ");
            while(!sequenceAlign1.empty()){
                System.out.print(sequenceAlign1.pop());
            }
            System.out.print("\n" +"Sequence 2: ");
            while(!sequenceAlign2.empty()){
                System.out.print(sequenceAlign2.pop());
            }
        }
}
    
