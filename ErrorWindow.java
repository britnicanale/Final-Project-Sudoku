import javax.swing.*;
import java.awt.*;
import javax.swing.border.*;
import java.awt.Font;

public class ErrorWindow extends JFrame{

    private JPanel pane;

    public ErrorWindow(){

        this.setTitle("Sudoku -- Error Message");

        this.setSize(300,175);

        this.setLocation(225,125);

        this.setResizable(false);

	pane = new JPanel();
	pane.setLayout(new BoxLayout(pane, BoxLayout.Y_AXIS));
	pane.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));

	this.getContentPane().add(pane);

	Font font = new Font("SansSerif", Font.BOLD, 20);

	JLabel error = new JLabel("Error");
	error.setFont(font);

	JLabel oops = new JLabel("<html><p>Oops, looks like you made a mistake! Use the Check Answers, Number of Errors, and Display Solutions buttons to locate and fix your errors, then try submitting again to win the game!</p></html>", JLabel.CENTER);

	pane.add(error);
	pane.add(oops);
    }
}
