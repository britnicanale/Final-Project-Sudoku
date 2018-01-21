import javax.swing.*;
import java.awt.*;
import java.text.*;

public class HelpWindow extends JFrame{
    private JPanel pane;
    private JLabel instructions;

    public HelpWindow(){

	this.setTitle("Sudoku -- Help");
	this.setSize(700, 600);
	this.setLocation(650, 0);
	this.setResizable(false);

	pane = new JPanel(new BorderLayout());
	pane.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));

	this.getContentPane().add(pane);

	instructions = new JLabel("<html><p> <b>Help</b> <br/>Hello, and welcome to the Sudoku Generator!\n Use this guide to get started playing Sudoku! <br/> <br/> <b>How to Play</b> <br/> In Sudoku, the goal is to fill the 9 x 9 board with numbers from 1-9. However, the same number can only occur once within the same column, row, and 3 x 3 subgrid. If you complete the board while following these rules, you win the game. <br/> <br/> <b> Controls </b> <br /> The following buttons will help you play the game. <br/> <br/><b> -Create Puzzle </b> To get started, select the Create Puzzle button. This will display a partially filled in Sudoku board. Input your guesses into the clear spaces. All of your input numbers will appear blue, and you will only be allowed to input numbers from 1 to 9.<br/><br/> <b> -Display Solution </b> <br/> Select the Display Solution button to completely fill in the board with the correct solution for the puzzle. <br/><br/> <b> -Check Answers </b> <br/>The Check Answers button will tell you whether the answers you have inputted are correct (they will be colored green) or incorrect (they will be colored red), but it will not display any new answers. <br/><br/> <b> -Number of Errors </b><br/> The Number of Errors button will display the number of errors that you have in the textbox below it, but will not display the location of the errors. <br/><br/> <b> -Hint </b> <br/> If you need help, select the Hint button, which will reveal one correct square of the puzzle. <br/><br/> <b> -Reset </b> <br/> If you want to reset the puzzle, select the Reset button, which will clear your inputs but not the original numbers displayed.</p></html> ");

	pane.add(instructions);
    }
    public static void main(String[] args){
	HelpWindow h = new HelpWindow();
	h.setVisible(true);
    }
}
