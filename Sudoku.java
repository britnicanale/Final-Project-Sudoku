import java.util.Random;
import java.util.Arrays;

public class Sudoku {
    private int[][] data;
    private int[][] input;
    private Random randgen;
    public Sudoku() {                           //just put in values
	int place = 0;                          //to get grid working
	for (int i = 0; i < 9; i++) {
	    for (int x = 0; x < 9; x++) {
		data[i][x] = place;
		place ++;
	    }
	}
    }
    public int getInput(int row, int col) {
	/*        for (int i = 0; i < 9; i++) {
	    for (int x = 0; x < 9; x++) {
		input[i][x] = 0;             //takes the input
	    }                                //from the other class and brings it here
	    }*/

	return input[row][col];
    }
    public int getData(int row, int col) {   
	return data[row][col];
    }
    public Sudoku(int seed) {}
    private boolean checkRows(){
	return true;
    }
    private boolean checkColumns(){
	return true;
    }
    private boolean checkBoxes(){
	return true;
    }
    public String displaySolution(){
	return "";
    }
    public boolean checkAnswer(){
	return true;
    }
    public boolean checkSolution(){
	return true;
    }
    private void createPuzzle(){}
    public void changeInput(int row, int col, int value){}
}