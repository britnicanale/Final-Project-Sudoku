import javax.swing.*;
import java.awt.*;
import java.text.*;

public class HelpWindow extends JFrame{
    private JPanel pane;
    private JTextArea instructions;

    public HelpWindow(){

	this.setTitle("Sudoku -- Help");
	this.setSize(310, 310);
	this.setLocation(650, 0);
	this.setResizable(false);

	pane = new JPanel(new BorderLayout());

	this.getContentPane().add(pane);

	instructions = new JTextArea(" Hello, and welcome to the Sudoku Generator!\n To get started, select the Create Puzzle button,\n which will display a board. Input your guesses\n into the clear spaces. All of your input numbers\n will appear blue. If you need help, select the\n Hint button, which will reveal one correct\n square of the puzzle, colored green. The Check\n Answers button will tell you whether the \n answers you have inputted are correct (they \n will be colored green) or incorrect (they will \n be colored red), but it will not display any \n new answers. The Display Solution button will \n change all of the inputs to the correct answers.");

	instructions.setEditable(false);

	pane.add(instructions);
    }
    public static void main(String[] args){
	HelpWindow h = new HelpWindow();
	h.setVisible(true);
    }
}