/**
 * Write a description of class MainTest here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class MainTest
{
    public static void main(String[] args){
        
    MatrixNode test1 = new MatrixNode(0, 0, 0, "GAP");
    
    String seq1 = "AAGGTTT";
    String seq2 = "TTTGGAA";
   
    MatrixNW test2 = new MatrixNW(seq1,seq2, true);
    test2.fillMatrix(-1,-1,2);
    test2.printMatrix();
    test2.findPath();
    test2.returnAligned();
    }
}
