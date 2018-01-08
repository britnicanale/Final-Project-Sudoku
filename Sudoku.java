import java.util.Random;
<<<<<<< HEAD
import java.util.Arrays;

=======
>>>>>>> 6514a3b4fffa1415e56ed2263c26b07ae02743a4
public class Sudoku {
    private int[][] data;
    private int[][] input;
    private Random randgen;
<<<<<<< HEAD
    public Sudoku() {                           //just put in values to get grid working
	int[][] data = new int[9][9];
	for (int i = 0; i < 9; i++) {
	    for (int x = 0; x < 9; x++) {
		data[i][x] = i * 9 + x;
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
=======
    public Sudoku() {                           //just put in values
	int place = 0;                          //to get grid working
	for (int i = 0; i < 9; i++) {
	    for (int x = 0; x < 9; x++) {
		data[i][x] = place;
		place ++;
	    }
	}
    }
    public int getInput() {
        for (int i = 0; i < 9; i++) {
	    for (int x = 0; x < 9; x++) {
		input[i][x] = 0;             //takes the input
	    }                                //from the other class and brings it here
	}
>>>>>>> 6514a3b4fffa1415e56ed2263c26b07ae02743a4
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
<<<<<<< HEAD
	return "";
=======
	return data;
>>>>>>> 6514a3b4fffa1415e56ed2263c26b07ae02743a4
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