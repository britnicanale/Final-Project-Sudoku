import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.Color;
import java.awt.GridLayout;
import java.text.NumberFormat;
import javax.swing.text.NumberFormatter;
import java.text.*;
import java.awt.Font;
import javax.swing.border.*;

public class HelpWindow extends JFrame{
    private JPanel pane;
    private JTextField instructions;

    public HelpWindow(){

	this.SetTitle("Sudoku -- Help");
	this.setSize(300, 300);
	this.setLocation(650, 0);
	this .setDefaultCloseOperation(EXIT_ON_CLOSE);
	this.setResizable(false);

	pane = new JPanel();

	instructions = new JTextField("Hello, and welcome to the Sudoku Generator! To get started, select the Create Puzzle button, which will display a board. Input your guesses into the clear spaces. All of your input numbers will appear blue. If you need help, select the Help button, which will reveal one correct square of the puzzle, colored green. The Check Answers button will tell you whether the answers you have inputted are correct (they will be colored green) or incorrect (they will be colored red), but it will not display any new answers. The Display Solution button will change all of the inputs to the correct answers.");

	instructions.setEditable(false);
    }
    public static void main(String[] args){
	HelpWindow h = new HelpWindow();
	h.setVisible(true);
    }
}