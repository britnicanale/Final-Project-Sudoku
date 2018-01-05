import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class SudokuWindow extends JFrame implements ActionListener{

    private Container pane;

    public SudokuWindow(){

	Sudoku puzzle = new Sudoku();

	this.setTitle("Sudoku");
        this.setSize(300,300);
        this.setLocation(0,0);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

        pane = this.getContentPane();

	pane = this.getContentPane();

	pane.setLayout(new GridLayout(9, 9));                      //Britni -- Creates a 9 x 9 Grid for the board

	for(int i = 1; i < 82; i++){                               //Britni -- Creates 81 JTextBoxes that fit within the board
	    JTextField b = new JTextField(puzzle.getData(i / 9, i % 9));
	    b.addActionListener(this);
	    pane.add(b);
	}
    }
    public void actionPerformed(ActionEvent e){}
    public static void main(String[] args){
	SudokuWindow s = new SudokuWindow();
	s.setVisible(true);
    }
}