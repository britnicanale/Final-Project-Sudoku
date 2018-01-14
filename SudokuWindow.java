import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.Color;
import java.awt.GridLayout;
import java.text.NumberFormat;
import javax.swing.text.NumberFormatter;
import java.text.*;
import java.awt.Font;
import javax.swing.border.*;

public class SudokuWindow extends JFrame implements ActionListener{


    private Sudoku puzzle;
    private JPanel pane;
    private JPanel sudokuPane;
    private JPanel buttonPane;
    private JPanel buttonPane2;    
    private JFormattedTextField[][] texts; 

    public SudokuWindow(){

	puzzle = new Sudoku();

	this.setTitle("Sudoku");
        this.setSize(650,600);
        this.setLocation(0,0);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	this.setResizable(false);

	//NOTE: THE NEXT SIX LINES ARE EDITED BUT TAKEN FROM THE FOLLOWING LINK: https://stackoverflow.com/questions/2554684/multiple-layout-managers-in-java  I used this because I am having a problem with panes that I cannot solve and am using this for testing purposes at the moment

	pane = new JPanel(new BorderLayout());

	sudokuPane = new JPanel(new GridLayout(9, 9));

	buttonPane = new JPanel();

	buttonPane2 = new JPanel(new FlowLayout());

	pane.add(sudokuPane, BorderLayout.CENTER);

	pane.add(buttonPane2, BorderLayout.EAST);

	pane.add(buttonPane, BorderLayout.PAGE_END);

	this.getContentPane().add(pane);

	JButton createPuzzle = new JButton("Create Puzzle");
	JButton displaySolution = new JButton("Display Solution");
	JButton checkAnswers = new JButton("Check Answers");
	JButton clearPuzzle = new JButton("Clear Puzzle");

	JButton random = new JButton("whoops");
	buttonPane2.add(random);

	displaySolution.addActionListener(this);

	checkAnswers.addActionListener(this);
	
	createPuzzle.addActionListener(this);

	clearPuzzle.addActionListener(this);
	
	buttonPane.add(clearPuzzle);
	buttonPane.add(createPuzzle);
	buttonPane.add(displaySolution);
	buttonPane.add(checkAnswers);

	Font font = new Font("SansSerif", Font.BOLD, 20);
	
	texts = new JFormattedTextField[9][9];

	for(int i = 0; i < 9; i++){                               //Britni -- Creates 81 JTextBoxes that fit within the board

	    for(int j = 0; j < 9; j++){

		NumberFormat intFormat = NumberFormat.getNumberInstance(); 

		//THIS NUMBERFORMATTER STUFF IS TAKEN FROM https://www.experts-exchange.com/questions/20453713/Allowing-blank-JFormattedTextField-fields.html
		NumberFormatter formatter = new NumberFormatter(intFormat) {
			public Object stringToValue(String string) throws ParseException {
			    if (string == null || string.length() == 0) {
				return null;
			    }
			    return super.stringToValue(string);
			}
		    };
		formatter.setValueClass(Integer.class);
		formatter.setMinimum(1);
		formatter.setMaximum(9);
		formatter.setAllowsInvalid(false);    
		formatter.setCommitsOnValidEdit(true);
		JFormattedTextField b = new JFormattedTextField(formatter);
		b.setHorizontalAlignment(JTextField.CENTER);
		b.setFont(font);
		b.addActionListener(this);
		b.setEditable(true);
		texts[i][j]= b;
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
		sudokuPane.add(b);
	    }
	}
	

    }


    public void actionPerformed(ActionEvent e){
	String s = e.getActionCommand();
	if(s.equals("Create Puzzle")){
	    puzzle.createPuzzle();   //We need to clear board first
	    for(int i = 0; i < 9; i++){                               //Britni -- Creates 81 JTextBoxes that fit within the board     
		for(int j = 0; j < 9; j++){
		    texts[i][j].setText(null);
		    texts[i][j].setEditable(true);
		    if(puzzle.getInput(i,j) != 0){
			texts[i][j].setText("" + puzzle.getInput(i, j));
			texts[i][j].setForeground(Color.BLACK);
			texts[i][j].setEditable(false); 
			
		    }else{
			texts[i][j].setForeground(Color.BLUE);
		    }
			
		}
	    }
	}


	if(s.equals("Display Solution")){
	    for(int i = 0; i < 9; i++){                
		for(int j = 0; j < 9; j++){
		    texts[i][j].setText("" + puzzle.getData(i, j));
		}
	    }
	}
	if(s.equals("Check Answers")){ // If not null, compare the input to the data
	    for(int i = 0; i < 9; i++){         //turn green if correct, red if not
		for(int j = 0; j < 9; j++){
		    if (texts[i][j].getValue() != null) {                        
			if((int)texts[i][j].getValue() == puzzle.getData(i, j)){
			    if (texts[i][j].isEditable()) {
				texts[i][j].setForeground(Color.GREEN);
			    }
			}else{
			    texts[i][j].setForeground(Color.RED);
			}
		    }
		}
	    }
	}
	if (s.equals("Clear Puzzle")) { //erases the puzzle to a blank board
	    for (int i = 0; i < 9; i++) {
		for (int j = 0; j < 9; j++) {
		    texts[i][j].setText(null);
		    texts[i][j].setEditable(true);
		}
	    }
	}
    }
    /*public void paintComponent(Graphics g) {
	super.paintComponent(g);
	int width = getWidth();
	int height = getHeight();
	
	g.setColor(Color.BLACK); // SETS COLOR OF THE LINE
	g.drawLine(0, height/2, width, height/2); // X-AXIS STRAIGHT DOWN (ACROSS?) THE MIDDLE
	g.drawLine(width/2, 0, width/2, height); // Y-AXIS STRAIGHT DOWN THE MIDDLE
	}*/
    public static void main(String[] args){
	SudokuWindow s = new SudokuWindow();
	s.setVisible(true);
    }
}
