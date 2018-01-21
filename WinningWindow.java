import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class WinningWindow extends JFrame implements ActionListener{

    private JPanel pane, scorePane, buttonPane;
    private SudokuWindow sudWin;

    public WinningWindow(int score, SudokuWindow sw){
	
	sudWin = sw;

	this.setTitle("Sudoku -- Congratulations!");

	this.setSize(300, 400);

	this.setLocation(0,0);

	this.setResizable(false);

	pane = new JPanel(new BorderLayout());

	scorePane = new JPanel(new GridLayout(2, 1));

	buttonPane = new JPanel(new FlowLayout());

	this.getContentPane().add(pane);

	JButton playagain = new JButton("Play Again");
	JButton exit = new JButton("Exit");
	playagain.addActionListener(this);
	exit.addActionListener(this);
	JLabel user = new JLabel("britni ............... 100000");
	JLabel user2 = new JLabel("Jeremy ................... 10");

	buttonPane.add(playagain);
	buttonPane.add(exit);

	scorePane.add(user);
	scorePane.add(user2);

	pane.add(buttonPane, BorderLayout.SOUTH);
	pane.add(scorePane, BorderLayout.CENTER);
    }
    public void actionPerformed(ActionEvent e){
	String s = e.getActionCommand();
	if(s.equals("Play Again")){
	    IntroWindow iw = new IntroWindow();
	    iw.setVisible(true);
	    this.dispose();
	    sudWin.dispose();
	}
	if(s.equals("Exit")){
	    this.dispose();
            sudWin.dispose();
	}
    }
}