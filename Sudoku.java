import java.util.Random;

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
	
	int a = randgen.nextInt(5);
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
	if (a == 1) {
	    actual = new int[][] {
		{1,9,2,4,5,6,3,7,8},
		{7,3,4,9,2,8,1,5,6},
		{6,5,8,7,3,1,9,2,4},
		{2,4,7,6,9,5,8,3,1},
		{3,8,6,1,4,7,5,9,2},
		{9,1,5,2,8,3,4,6,7},
		{4,2,1,3,6,9,7,8,5},
		{5,6,9,8,7,4,2,1,3},
		{8,7,3,5,1,2,6,4,9}
	    };
	}
	if (a == 2) {
	    actual = new int[][] {
		{4,2,9,8,1,3,5,6,7},
		{5,1,6,4,7,2,9,3,8},
		{7,8,3,6,5,9,2,4,1},
		{6,7,2,1,3,4,8,5,9},
		{3,9,5,2,8,6,1,7,4},
		{8,4,1,7,9,5,6,2,3},
		{1,5,8,3,6,7,4,9,2},
		{9,3,4,5,2,8,7,1,6},
		{2,6,7,9,4,1,3,8,5}
	    };
	}
	if (a == 3) {
	    actual = new int[][] {
		{5,3,4,6,7,8,9,1,2},
                {6,7,2,1,9,5,3,4,8},
		{1,9,8,3,4,2,5,6,7},
                {8,5,9,7,6,1,4,2,3},
		{4,2,6,8,5,3,7,9,1},
                {7,1,3,9,2,4,8,5,6},
		{9,6,1,5,3,7,2,8,4},
                {2,8,7,4,1,9,6,3,5},
		{3,4,5,2,8,6,1,7,9}
	    };
	}
	if (a == 4) {
	    actual = new int[][] {
		{9,5,3,2,6,7,1,4,8},
		{6,7,1,5,8,4,9,3,2},
		{2,4,8,9,1,3,7,5,6},
		{7,1,4,6,9,2,5,8,3},
		{5,2,9,7,3,8,4,6,1},
		{3,8,6,4,5,1,2,9,7},
		{4,6,7,3,2,5,8,1,9},
		{1,9,5,8,7,6,3,2,4},
		{8,3,2,1,4,9,6,7,5}
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
	for (int i = 0; i < 9; i++) {
	    for (int x = 0; x < 9; x++) {
		data[i][x] = actual[i][x];
		input[i][x] = actual[i][x];
	    }
	}
    }
    public int getInput(int row, int col) {
	return input[row][col];
    }

    public void setInput(int row, int col, int value) {
	input[row][col] = value;
    }
    
    public int getData(int row, int col) {   
	return data[row][col];
    }
    public int[][] getData() {
	return data;
    }

    public Sudoku(int seed) {}

    private boolean checkRow(int row, int col){
	/*for(int col2 = 0; col2 < 9; col2++){
	    if(data[row][col2] == data[row][col] && col2 != col){
		return false;
	    }
	    }*/
	return true;
    }

    private boolean checkCol(int row, int col, int val){
        /*for(int row2 = 0; row2 < 9; row2++){
            if((data[row2][col] != null && data[row2][col].equals(val)) && row2 != row){
                return false;
            }
	    }*/
        return true;
    }

	private boolean checkBox(int row, int col, int val){
	    /*for(int r = (row / 3) * 3; r <(row / 3)*3 + 3; r ++){
	    for(int c = (col / 3) * 3; c <( col / 3) * 3  + 3; c ++){
		System.out.println("Checking box " + r + ", " + c);
		if((data[r][c] != null && data[r][c].equals(val)) && (row != r && col != c)){
		    return false;
		}
	    }
	    }*/
	return true; 	
    }

    public void createPuzzle(){
	for (int i = 0; i < 9; i++) {
	    for (int x = 0; x < 9; x++) {
		int a = randgen.nextInt(3);
		if (a == 0 ) {
		    input[i][x] = data[i][x];
		}
		else {
		    input[i][x] = 0;
		}
	    }
	}
    }

    public void changeInput(int row, int col, int value){
	input[row][col]=value;
    }
}
