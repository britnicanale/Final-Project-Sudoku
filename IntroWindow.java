import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.text.*;
import java.awt.Font;
import javax.swing.JComboBox;
import javax.swing.BoxLayout;

public class IntroWindow extends JFrame implements ActionListener{

    private JPanel pane;

    public IntroWindow(){

	this.setTitle("Welcome to Sudoku!");

	this.setSize(300,300);

	this.setLocation(0,0);
	this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	this.setResizable(false);

	pane = new JPanel(new BoxLayout(pane, BoxLayout.Y_AXIS));

	Font font = new Font("SansSerif", Font.BOLD, 20);
	JLabel welc = new JLabel("Welcome!");
	welc.setFont(font);
	//welc.addActionListener(this);


	JTextField user = new JTextField("", 15);
	user.addActionListener(this);

	JButton play = new JButton("Play");
	play.addActionListener(this);
	String[] levels = {"Easy", "Medium", "Hard"};
	JComboBox difficulty = new JComboBox(levels);
	difficulty.setSelectedIndex(0);
	difficulty.addActionListener(this);

	pane.add(welc);
	pane.add(user);
	pane.add(difficulty);
	pane.add(play);

	this.getContentPane().add(pane);
    }	
    public void actionPerformed(ActionEvent e){
	String s = e.getActionCommand();
	if(s.equals("Play")){
	    SudokuWindow sw = new SudokuWindow();
	    sw.setVisible(true);
	    this.setVisible(false);
	    this.dispose();
	}
    }

    public static void main(String[] args){
	IntroWindow iw = new IntroWindow();
	iw.setVisible(true);
    }
}
