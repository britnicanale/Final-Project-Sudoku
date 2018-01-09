import java.util.Random;
import java.util.Random;


public class Sudoku {
    private int[][] data;
    private int[][] input;
    private Random randgen;

    public Sudoku() {                           //just put in values to get grid working
	data = new int[9][9];
	input = new int[9][9];
	randgen = new Random();
        int[][] actual = new int[][]{           //this is a real solution--we will work with it until we get the generator up and running
	    {2,5,7,9,6,4,1,8,3},
	    {4,9,1,8,7,3,6,5,2},
	    {3,8,6,1,2,5,9,4,7},
	    {6,4,5,7,3,2,8,1,9},
	    {7,1,9,5,4,8,3,2,6},
	    {8,3,2,6,1,9,5,7,4},
	    {1,6,3,2,5,7,4,9,8},
	    {5,7,8,4,9,6,2,3,1},
	    {9,2,4,3,8,1,7,6,5}
	};
	for (int i = 0; i < 9; i++) {
	    for (int x = 0; x < 9; x++) {
		data[i][x] = actual[i][x];
		input[i][x] = actual[i][x];
	    }
	}
        
    }
    public int getInput(int row, int col) {
	createPuzzle();
	return input[row][col];
    }

    public int getData(int row, int col) {   
	return data[row][col];
    }
    public int[][] getData() {
	return data;
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

    private void createPuzzle(){
	for (int i = 0; i < 9; i++) {
	    for (int x = 0; x < 9; x++) {
		int a = randgen.nextInt(50);
		if (a == 0) {
		    input[i][x] = 0;
		}
	    }
	}
    }

    public void changeInput(int row, int col, int value){}
}
