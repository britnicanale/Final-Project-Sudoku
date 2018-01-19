import javax.swing.*;
import java.awt.*;
import javax.swing.border.*;
import java.awt.Font;

public class ErrorWindow extends JFrame{

    private JPanel pane;

    public ErrorWindow(){

        this.setTitle("Sudoku -- Error Message");

        this.setSize(300,200);

        this.setLocation(225,125);

        this.setResizable(false);

	pane = new JPanel(new GridLayout(1, 1));

	this.getContentPane().add(pane);

	JLabel oops = new JLabel("Oops, looks like you made a mistake!\n Use the Check Answers, Number of Errors, and Display Solutions buttons to locate and fix your errors, then try submitting again to win the game!", JLabel.CENTER);

	pane.add(oops);
    }
}
