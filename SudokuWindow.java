import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class SudokuWindow extends JFrame implements ActionListener{

    //private Container pane;
    private Sudoku puzzle;
    //private JTextField b;
    public SudokuWindow(){

	puzzle = new Sudoku();

	this.setTitle("Sudoku");
        this.setSize(300,350);
        this.setLocation(0,0);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

	//NOTE: THE NEXT SIX LINES ARE EDITED BUT TAKEN FROM THE FOLLOWING LINK: https://stackoverflow.com/questions/2554684/multiple-layout-managers-in-java  I used this because I am having a problem with panes that I cannot solve and am using this for testing purposes at the moment

	JPanel pane = new JPanel(new BorderLayout());

	JPanel sudokuPane = new JPanel(new GridLayout(9, 9));

	JPanel buttonPane = new JPanel();

	pane.add(sudokuPane, BorderLayout.CENTER);

	pane.add(buttonPane, BorderLayout.PAGE_END);

	this.getContentPane().add(pane);

	JButton createPuzzle = new JButton("Create Puzzle");
	
	createPuzzle.addActionListener(this);

	buttonPane.add(createPuzzle);


	for(int i = 0; i < 9; i++){                               //Britni -- Creates 81 JTextBoxes that fit within the board
	    for(int j = 0; j < 9; j++){
		
		JTextField b = new JTextField("" + puzzle.getData(i, j));
		b.addActionListener(this);
		b.setEditable(false);
		sudokuPane.add(b);
	    }
	}
    }
    public void actionPerformed(ActionEvent e){
	String s = e.getActionCommand();
	if(s.equals("Create Puzzle")){
	    //b.setText("0");
	}
    }
    public static void main(String[] args){
	SudokuWindow s = new SudokuWindow();
	s.setVisible(true);
    }
}