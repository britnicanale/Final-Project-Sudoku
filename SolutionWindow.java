import javax.swing.*;
import java.awt.*;
import javax.swing.border.*;
import java.awt.Font;

public class SolutionWindow extends JFrame{

    private JPanel pane;

    public SolutionWindow(int[][] data){

	this.setTitle("Sudoku -- Solution");

        this.setSize(550,550);

        this.setLocation(650,0);

	this.setResizable(false);

	pane = new JPanel(new GridLayout(9,9));

	this.getContentPane().add(pane);

	Font font = new Font("SansSerif", Font.BOLD, 20);

        for(int i = 0; i < 9; i++){                               //Britni -- Creates 81 JTextBoxes that fit within the board         

            for(int j = 0; j < 9; j++){

		JTextField b = new JTextField();
		b.setHorizontalAlignment(JTextField.CENTER);
		b.setFont(font);
		b.setText("" + data[i][j]);
		b.setEditable(false);
		if ((i == 2 || i == 5) && (j == 2 || j == 5)) {
		    Border border = BorderFactory.createMatteBorder(1, 1, 3, 3, Color.BLACK);
		    b.setBorder(border);
		}
		else if (i == 2 || i == 5) {
		    Border border = BorderFactory.createMatteBorder(1, 1, 3, 1, Color.BLACK);
		    b.setBorder(border);
		}
		else if (j == 2 || j == 5) {
		    Border border = BorderFactory.createMatteBorder(1, 1, 1, 3, Color.BLACK);
		    b.setBorder(border);
		}
		else {
		    Border border = BorderFactory.createMatteBorder(1, 1, 1, 1, Color.BLACK);
		    b.setBorder(border);
		}
		pane.add(b);
            }
        }
    }
}
    
