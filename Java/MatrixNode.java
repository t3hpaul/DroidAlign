/**
 * Write a description of class MatrixNode here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class MatrixNode
{
    public int score;      //Score of node
    public int traceX;
    public int traceY;//Where score came from
    public String outcome; //Match, mismatch or gap
    
    public MatrixNode(int inputscore, int pathX, int pathY, String symbol){
    
    score = inputscore;
    traceX = pathX;
    traceY = pathY;
    outcome = symbol;
    }
    
    public int returnScore(){
        return this.score;
    }
    
    public int returnPathX(){
        return this.traceX;
    }
    
    public int returnPathY(){
        return this.traceY;
    }
    
    public  String returnOutcome(){
        return this.outcome;
    }
    
    public void setScore(int num, int pathX, int pathY, String symbol){
        this.score = num;
        this.traceX = pathX;
        this.traceY = pathY;
        this.outcome = symbol;
    }
}