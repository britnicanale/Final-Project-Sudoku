import java.util.Random;
import java.util.ArrayList;


public class Sudoku {
    private int[][] data;
    private int[][] input;
    private Random randgen;
    private int[][] actual;

    public Sudoku() {                           //just put in values to get grid working
	data = new int[9][9];
	input = new int[9][9];
	actual = new int[9][9];
	randgen = new Random();
	/*
	int a = randgen.nextInt(2);
	if (a == 0) {
	    actual = new int[][]{     //this is a real solution--we will work with it until we get the generator up and running
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
	}
	else  {
	    actual = new int[][] {
		{1,2,3,4,5,6,7,8,9},
		{4,5,6,7,8,9,1,2,3},
		{7,8,9,1,2,3,4,5,6},
		{2,3,1,5,6,4,8,9,7},
		{5,6,4,8,9,7,2,3,1},
		{8,9,7,2,3,1,5,6,4},
		{3,1,2,6,4,5,9,7,8},
		{6,4,5,9,7,8,3,1,2},
		{9,7,8,3,1,2,6,4,5}
	    };
	}
	

LONG VERSION OLD
	for (int i = 0; i < 9; i++) {
            for (int x = 0; x < 9; x++) {
		//int[] bad = new int[8];
                data[i][x] = randgen.nextInt(9);
		while(!(checkRow(i, x) && (checkCol(i,x) && checkBox(i,x)))){
		    data[i][x] = randgen.nextInt(9);
		}
            }
        }

	
	//Long Version New
	ArrayList<Integer> pos = new ArrayList<Integer>();
	for(int i = 0; i < 9; i++){
	    pos.add(i);
	}
*/

	ArrayList<Integer> pos = new ArrayList<Integer>();
	for (int i = 0; i < 9; i++) {
	    pos.clear();
	    for(int j = 0; j < 9; j++){
		pos.add(j);
	    }
            for (int x = 0; x < 9; x++) {
		int col = pos.get(randgen.nextInt(pos.size()));;
		System.out.println(pos.size()+ " " + col);
		while("" + data[i][col] == null && !(checkBox(i, col) && checkCol(i, col))){
		    pos.remove(col);
		    System.out.println(pos.size());
		    col = pos.get(randgen.nextInt(pos.size()));
		}
		data[i][col] = x;
		System.out.println("added " + x + " to "+  i + ", " + col);
	    }
	}
	for (int i = 0; i < 9; i++) {
	    for (int x = 0; x < 9; x++) {
		data[i][x] = actual[i][x];
		input[i][x] = actual[i][x];
	    }
	}
    }
    public int getInput(int row, int col) {
	//createPuzzle();
	return input[row][col];
    }

    public int getData(int row, int col) {   
	return data[row][col];
    }
    public int[][] getData() {
	return data;
    }

    public Sudoku(int seed) {}

    private boolean checkRow(int row, int col){
	for(int col2 = 0; col2 < 9; col2++){
	    if(data[row][col2] == data[row][col] && col2 != col){
		return false;
	    }
	}
	return true;
    }

    private boolean checkCol(int row, int col){
        for(int row2 = 0; row2 < 9; row2++){
            if(data[row2][col] == data[row][col] && row2 != row){
                return false;
            }
	}
        return true;
    }

    private boolean checkBox(int row, int col){
	for(int r = row / 3; r < row / 3 + 3; r ++){
	    for(int c = col / 3; c < col / 3 + 3; c ++){
		if(data[r][c] == data[row][col] && (row != r && col != c)){
		    return false;
		}
	    }
	}
	return true; 	
    }

    public void createPuzzle(){
	for (int i = 0; i < 9; i++) {
	    for (int x = 0; x < 9; x++) {
		int a = randgen.nextInt(3);
		if (a == 0) {
		    input[i][x] = 0;
		}
	    }
	}
    }

    public void changeInput(int row, int col, int value){
	input[row][col]=value;
    }
}
