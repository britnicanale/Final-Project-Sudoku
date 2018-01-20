import javax.swing.*;
import java.awt.*;
import java.text.*;

public class EndPage extends JFrame {
    private JPanel pane;
    private JEditorPane win;

    public EndPage() {
	this.setTitle("You Win!!");
	this.setSize(250,250);
	this.setLocation(400,200);
	this.setResizable(false);

	pane = new JPanel(new BorderLayout());

	this.getContentPane().add(pane);

	win = new JEditorPane("text/html", "");

	win.setText(" <font color = 'blue'><b>Congradulations!</font><br /><font color = 'red'> To play again, please exit this window and click 'Create Puzzle' to randomly generate a new board!</b></font>");
	win.setEditable(false);

	pane.add(win);
    }

    public static void main(String[] args) {
	EndPage w = new EndPage();
	w.setVisible(true);
    }
}
