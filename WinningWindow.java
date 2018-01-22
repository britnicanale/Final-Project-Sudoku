import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class WinningWindow extends JFrame implements ActionListener{

    private JPanel pane, scorePane, buttonPane;
    private SudokuWindow sudWin;

    public WinningWindow(String lev, String username, int timed, SudokuWindow sw){
	
	sudWin = sw;

	this.setTitle("Sudoku -- Congratulations!");

	this.setSize(300, 200);

	this.setLocation(0,0);

	this.setResizable(false);

	pane = new JPanel(new BorderLayout());

	//scorePane = new JPanel(new GridLayout(2, 1));

	buttonPane = new JPanel(new FlowLayout());

	this.getContentPane().add(pane);

	JButton playagain = new JButton("Play Again");
	JButton exit = new JButton("Exit");

	playagain.addActionListener(this);
	exit.addActionListener(this);

	JLabel youwon = new JLabel("You Won!!");
	Font font = new Font("SansSerif", Font.BOLD, 20);
	youwon.setFont(font);

	JLabel win = new JLabel("<html><p>Congratulations " + username + "! You completed a " + lev + " Sudoku Puzzle in "+ timed + " seconds!</p></html>");

	buttonPane.add(playagain);
	buttonPane.add(exit);

	pane.add(youwon, BorderLayout.NORTH);
	pane.add(win, BorderLayout.CENTER);
	pane.add(buttonPane, BorderLayout.SOUTH);
	//pane.add(scorePane, BorderLayout.CENTER);
    }
    public void actionPerformed(ActionEvent e){
	String s = e.getActionCommand();
	if(s.equals("Play Again")){ //Reopens the IntroWindow, closes other windows, keeps program running
	    IntroWindow iw = new IntroWindow();
	    iw.setVisible(true);
	    this.dispose();
	    sudWin.dispose();
	}
	if(s.equals("Exit")){ //Closes everything
	    this.dispose();
            sudWin.dispose(); 
	    System.exit(0); //Terminates the program
	}
    }
}
