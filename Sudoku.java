import java.util.Random;
import java.util.ArrayList;
import java.util.Arrays;

public class Sudoku {
    private Integer[][] data;
    private Integer[][] input;
    private Random randgen;
    private Integer[][] actual;

    public Sudoku() {                           //just put in values to get grid working
	data = new Integer[9][9];
	input = new Integer[9][9];
	actual = new Integer[9][9];
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
	ArrayList<Integer> nums = new ArrayList<Integer>();
	for (Integer i = 1; i < 10; i++) {
	    pos.clear();
	    for(int j = 0; j < 9; j++){
		pos.add(j);
	    }
            for (int x = 0; x < 9; x++) {
	        nums.clear();
		for(int k = 0; k < pos.size(); k++){
		    nums.add(pos.get(k));
		}
		int col = nums.get(randgen.nextInt(nums.size()));
		//System.out.println(nums.size()+ " " + col);
		
		while(data[x][col] != null || !(checkBox(x, col, i) && checkCol(x, col, i))){
		    System.out.println(checkBox(x, col, i));
		    System.out.println(checkCol(x, col, i));
		    System.out.println("Tried to add " + i + " to " + x + ", " + col + "; size: " + nums.size());
		    nums.remove(nums.indexOf(col));
		    //System.out.println(nums.size());
		    col = nums.get(randgen.nextInt(nums.size()));
		    //data[i][col] = x;
		}
		System.out.println(Arrays.toString(data[0]));
		System.out.println(Arrays.toString(data[1]));
		System.out.println(Arrays.toString(data[2]));
		System.out.println(Arrays.toString(data[3]));
		System.out.println(Arrays.toString(data[4]));
		System.out.println(Arrays.toString(data[5]));
		System.out.println(Arrays.toString(data[6]));
		System.out.println(Arrays.toString(data[7]));
		System.out.println(Arrays.toString(data[8]));
		data[x][col] = i;
		pos.remove(pos.indexOf(col));
		System.out.println("added " + i + " to "+  x + ", " + col);
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
    public Integer[][] getData() {
	return data;
    }

    public String toString(){                                       //toString loops through all values in the 2D Array, separates them with spaces and   
        String retStr = "";                                         //separates each inner Array with a new line.                                         
        for(int index =0; index < data.length; index++){
            for (int subIndex = 0; subIndex < data[index].length; subIndex++){
                retStr += data[index][subIndex] + " ";
            }
            retStr+= "\n";
        }
        return retStr;
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

    private boolean checkCol(int row, int col, Integer val){
        for(int row2 = 0; row2 < 9; row2++){
            if((data[row2][col] != null && data[row2][col].equals(val)) && row2 != row){
                return false;
            }
	}
        return true;
    }

	private boolean checkBox(int row, int col, Integer val){
	for(int r = (row / 3) * 3; r <(row / 3)*3 + 3; r ++){
	    for(int c = (col / 3) * 3; c <( col / 3) * 3  + 3; c ++){
		System.out.println("Checking box " + r + ", " + c);
		if((data[r][c] != null && data[r][c].equals(val)) && (row != r && col != c)){
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
