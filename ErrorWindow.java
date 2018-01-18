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

	pane = new JPanel(new BorderLayout());

	JLabel oops = new JLabel("Oops, looks like you made a mistake! Use the Check Answers, Number of Errors, and Display Solutions buttons to locate and fix your errors, then try submitting again to win the game!");

	pane.add(oops, BorderLayout.CENTER);
    }
}
