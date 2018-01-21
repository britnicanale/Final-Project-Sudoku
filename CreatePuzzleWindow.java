import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.Font;

public class CreatePuzzleWindow extends JFrame implements ActionListener{

    private JPanel pane;
    private JPanel infoPane;
    private JPanel buttonPane;
    private SudokuWindow sw;

    public CreatePuzzleWindow(SudokuWindow sudWin){

        sw = sudWin;

        this.setTitle("Sudoku -- Create Puzzle");
        this.setSize(400, 200);
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

        JLabel info = new JLabel("Are you sure you want to create a new puzzle?");
        info.setFont(font);

        JLabel moreInfo = new JLabel("<html><p>Choosing to create a new puzzle  will delete your progress and create a new puzzle on the same current level. Are you sure you want to continue?<p></html>");

        JButton cancel = new JButton("Cancel");
        cancel.addActionListener(this);

        JButton createnewpuzzle = new JButton("Create New Puzzle");
        createnewpuzzle.addActionListener(this);

        infoPane.add(info);
        infoPane.add(moreInfo);

        buttonPane.add(cancel);
	buttonPane.add(createnewpuzzle);
    }
    public void actionPerformed(ActionEvent e){
        String s = e.getActionCommand();
        if(s.equals("Cancel")){
            this.setVisible(false);
            this.dispose();
        }
        if(s.equals("Create New Puzzle")){
            this.setVisible(false);
	    sw.createPuzzle();
            this.dispose();
	}
    }
}



