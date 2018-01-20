import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.Font;

public class RestartWindow extends JFrame implements ActionListener{

    private JPanel pane;
    private JPanel infoPane;
    private JPanel buttonPane;
    private SudokuWindow sw;

    public RestartWindow(SudokuWindow sudWin){

	sw = sudWin;

	this.setTitle("Sudoku -- Restart");
	this.setSize(350, 150);
	this.setLocation(150, 200);
	this.setResizable(false);

	pane = new JPanel(new BorderLayout());
	infoPane = new JPanel(new GridLayout(2, 1));
	buttonPane = new JPanel(new FlowLayout());

	pane.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));

	pane.add(infoPane, BorderLayout.CENTER);

        pane.add(buttonPane, BorderLayout.SOUTH);

        this.getContentPane().add(pane);

	Font font = new Font("SansSerif", Font.BOLD, 14);

	JLabel info = new JLabel("Are you sure you want to restart the game?");
	info.setFont(font);

	JLabel moreInfo = new JLabel("<html><p>Choosing to restart will delete your progress and exit the game. Are you sure you want to continue?<p></html>");

	JButton cancel = new JButton("Cancel");
	cancel.addActionListener(this);

	JButton restart = new JButton("Restart");
	restart.addActionListener(this);

	infoPane.add(info);
	infoPane.add(moreInfo);

	buttonPane.add(cancel);
	buttonPane.add(restart);
    }
    public void actionPerformed(ActionEvent e){
        String s = e.getActionCommand();
	if(s.equals("Cancel")){
	    this.setVisible(false);
            this.dispose();
	}
	if(s.equals("Restart")){
	    this.setVisible(false);
	    IntroWindow iw = new IntroWindow();
	    iw.setVisible(true);
	    sw.setVisible(false);
	    sw.dispose();
	    this.dispose();
	    }
    }
}




