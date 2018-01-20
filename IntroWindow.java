import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.text.*;
import java.awt.Font;
import javax.swing.JComboBox;
import javax.swing.BoxLayout;
import java.awt.Dimension;

public class IntroWindow extends JFrame implements ActionListener{

    private JPanel pane;
    private JComboBox<String> difficulty;
    private JTextField user;
    public IntroWindow(){

	this.setTitle("Welcome to Sudoku!");

	this.setSize(300,250);

	this.setLocation(0,0);
	this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	this.setResizable(false);

	pane = new JPanel();
	pane.setLayout(new BoxLayout(pane, BoxLayout.Y_AXIS));
	pane.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));

	Font font = new Font("SansSerif", Font.BOLD, 20);
	JLabel welc = new JLabel("Welcome!");

	//welc.setAlignmentX(Component.CENTER_ALIGNMENT);
	welc.setFont(font);

	JLabel userLabel = new JLabel("Choose a Username");
	userLabel.setAlignmentX(Component.LEFT_ALIGNMENT);

	user = new JTextField("", 15);
	user.addActionListener(this);
	//user.setPreferredSize(new Dimension(250, 10));
	user.setMaximumSize(new Dimension(Integer.MAX_VALUE, user.getPreferredSize().height) );

	JLabel levelsLabel = new JLabel("Choose your level");
	levelsLabel.setAlignmentX(Component.LEFT_ALIGNMENT);

	String[] levels = {"Easy", "Medium", "Hard"};
	difficulty = new JComboBox<String>(levels);
	difficulty.setSelectedIndex(0);
	difficulty.addActionListener(this);

	JButton play = new JButton("Play");
	play.addActionListener(this);

	pane.add(welc);
	pane.add(Box.createRigidArea(new Dimension(0,20)));
	pane.add(userLabel);
	pane.add(user);
	pane.add(Box.createRigidArea(new Dimension(0,20)));
	pane.add(levelsLabel);
	pane.add(difficulty);
	pane.add(Box.createRigidArea(new Dimension(0,20)));
	pane.add(play);

	this.getContentPane().add(pane);
    }	
    public void actionPerformed(ActionEvent e){
	String s = e.getActionCommand();
	if(s.equals("Play")){
	    String username = user.getText();
	    if(username.equals("")){
		username = "Guest";
	    }
	    String x = difficulty.getSelectedItem().toString();
	    SudokuWindow sw = new SudokuWindow(username, x);
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
