import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.Color;
import java.awt.GridLayout;

public class SudokuWindow extends JFrame implements ActionListener{

    //private Container pane;
    private Sudoku puzzle;

    private JPanel pane;
    private JPanel sudokuPane;
    private JPanel buttonPane;
    private JTextField[][] texts;
    //private JTextField b;
    public SudokuWindow(){

	puzzle = new Sudoku();

	this.setTitle("Sudoku");
        this.setSize(300,350);
        this.setLocation(0,0);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

	//NOTE: THE NEXT SIX LINES ARE EDITED BUT TAKEN FROM THE FOLLOWING LINK: https://stackoverflow.com/questions/2554684/multiple-layout-managers-in-java  I used this because I am having a problem with panes that I cannot solve and am using this for testing purposes at the moment

	pane = new JPanel(new BorderLayout());

	sudokuPane = new JPanel(new GridLayout(9, 9));

	buttonPane = new JPanel();

	pane.add(sudokuPane, BorderLayout.CENTER);

	pane.add(buttonPane, BorderLayout.PAGE_END);

	this.getContentPane().add(pane);

	JButton createPuzzle = new JButton("Create Puzzle");
	
	createPuzzle.addActionListener(this);

	buttonPane.add(createPuzzle);

	//pane.setLayout(new GridLayout(9, 9));                      //Creates a 9 x 9 Grid for the board
	texts = new JTextField[9][9];

	for(int i = 0; i < 9; i++){                               //Britni -- Creates 81 JTextBoxes that fit within the board
	    for(int j = 0; j < 9; j++){
		
		JTextField b = new JTextField("");
		b.addActionListener(this);
		//b.setEditable(true);
		texts[i][j]= b;
		sudokuPane.add(b);
	    }
	    }
    }
    public void actionPerformed(ActionEvent e){
	String s = e.getActionCommand();
	if(s.equals("Create Puzzle")){
	    for(int i = 0; i < 9; i++){                               //Britni -- Creates 81 JTextBoxes that fit within the board     
		for(int j = 0; j < 9; j++){

		    texts[i][j].setText("" + puzzle.getData(i,j ));
		    //b.addActionListener(this);
		    texts[i][j].setEditable(true);
		    //texts[i][j]= b;
		    //sudokuPane.add(b);
		}
	    }


	}
    }
    public static void main(String[] args){
	SudokuWindow s = new SudokuWindow();
	s.setVisible(true);
    }
}
