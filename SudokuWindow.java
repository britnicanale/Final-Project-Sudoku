import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class SudokuWindow extends JFrame implements ActionListener{

    //private Container pane;

    public SudokuWindow(){

	Sudoku puzzle = new Sudoku();

	this.setTitle("Sudoku");
        this.setSize(300,300);
        this.setLocation(0,0);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

	//NOTE: THE NEXT SIX LINES ARE EDITED BUT TAKEN FROM THE FOLLOWING LINK: https://stackoverflow.com/questions/2554684/multiple-layout-managers-in-java  I used this because I am having a problem with panes that I cannot solve and am using this for testing purposes at the moment

	JPanel pane = new JPanel(new BorderLayout());

	JPanel sudokuPane = new JPanel(new GridLayout(9, 9));

	JPanel buttonPane = new JPanel();

	pane.add(sudokuPane, BorderLayout.CENTER);

	pane.add(buttonPane, BorderLayout.PAGE_END);

	this.getContentPane().add(pane);


	//pane = this.getContentPane();


	//pane.setLayout(new GridLayout(9, 9));                      //Britni -- Creates a 9 x 9 Grid for the board

	for(int i = 1; i < 82; i++){                               //Britni -- Creates 81 JTextBoxes that fit within the board
	    JTextField b = new JTextField(puzzle.getData(i / 9, i % 9));
	    b.addActionListener(this);
	    sudokuPane.add(b);
	}
    }
    public void actionPerformed(ActionEvent e){}
    public static void main(String[] args){
	SudokuWindow s = new SudokuWindow();
	s.setVisible(true);
    }
}