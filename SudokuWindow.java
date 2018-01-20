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
import java.util.Random;

public class SudokuWindow extends JFrame implements ActionListener{


    private Sudoku puzzle;
    private Random randgen;
    private JPanel pane;
    private JPanel sudokuPane;
    private JPanel buttonPane;
    private JPanel buttonPane2;    
    private JFormattedTextField[][] texts; 
    private JTextField numErrorsText;
    private Timer timer;
    //private JLabel timerLabel;

    //public final static int ONE_SECOND = 1000;

    public SudokuWindow(){
	
	randgen = new Random();

	this.setTitle("Sudoku");

        this.setSize(650,575);

        this.setLocation(0,0);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	this.setResizable(false);

	//NOTE: THE NEXT SIX LINES ARE EDITED BUT TAKEN FROM THE FOLLOWING LINK: https://stackoverflow.com/questions/2554684/multiple-layout-managers-in-java  I used this because I am having a problem with panes that I cannot solve and am using this for testing purposes at the moment

	pane = new JPanel(new BorderLayout());

	sudokuPane = new JPanel(new GridLayout(9, 9));

	buttonPane = new JPanel();
	buttonPane.setLayout(new BoxLayout(buttonPane, BoxLayout.Y_AXIS));

	pane.add(sudokuPane, BorderLayout.CENTER);

	pane.add(buttonPane, BorderLayout.EAST);

	this.getContentPane().add(pane);

	JButton createPuzzle = new JButton("Create Puzzle");
	JButton displaySolution = new JButton("Display Solution");
	JButton checkAnswers = new JButton("Check Answers");
	JButton numErrors = new JButton("Number of Errors:");
	numErrorsText = new JTextField(2);
	JButton hint = new JButton("Hint");
	JButton help = new JButton("Help");
	JButton reset = new JButton("Reset");
	//JTextField time = new JTextField("0");
	//JTextField seed = new JTextField("Load Puzzle");
	JTextField timerLabel = new JTextField("Timer");
	//timerLabel.setBounds(30,30,30,30);
	
	displaySolution.addActionListener(this);

	checkAnswers.addActionListener(this);
	
	numErrors.addActionListener(this);

	numErrorsText.addActionListener(this);
	numErrorsText.setEditable(false);

	createPuzzle.addActionListener(this);

	hint.addActionListener(this);

	help.addActionListener(this);

	reset.addActionListener(this);

	//time.addActionListener(this);
	//time.setEditable(false);

	buttonPane.add(createPuzzle);
	buttonPane.add(displaySolution);
	buttonPane.add(checkAnswers);
	buttonPane.add(numErrors);
	//buttonPane.add(time);
	buttonPane.add(numErrorsText);
     	buttonPane.add(hint);
	buttonPane.add(help);
	buttonPane.add(reset);
	buttonPane.add(timerLabel);

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
		b.setEditable(false);
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
	int time1 = 0;
	timer = new Timer(1000, new ActionListener() {
	        //time1 += 1;
		public void actionPerformed(ActionEvent evt) {
		    boolean finish = true;
		    //timer.start();
		    
		    for (int i = 0; i < 9; i++) {
			for (int x = 0; x < 9; x++) {
			    if ((int)texts[i][x].getValue() != puzzle.getData(i, x)) {
				finish = false;
			    }
			}
		    }
		    if (finish) {
			timer.stop();
		    }
		}
	        //timerLabel.setText("" + time);
	    });
	//buttonPane.add(timer);
	this.getContentPane().add(pane);
    }
    

    public void actionPerformed(ActionEvent e){
	String s = e.getActionCommand();
	if(s.equals("Create Puzzle")){
	    puzzle = new Sudoku();
	    
	    puzzle.createPuzzle();
	    
	    for(int i = 0; i < 9; i++){                               //Creates 81 JTextBoxes that fit within the board     
		for(int j = 0; j < 9; j++){
		    texts[i][j].setText(null);
		    texts[i][j].setEditable(true);
		    texts[i][j].setForeground(Color.BLUE);
		    if(puzzle.getInput(i,j) != 0){
			texts[i][j].setText("" + puzzle.getInput(i, j));
			texts[i][j].setEditable(false);
			texts[i][j].setForeground(Color.BLACK);
		    }
		}
	    }
	}
	
	if(s.equals("Display Solution")){
	    try {
		for(int i = 0; i < 9; i++){                
		    for(int j = 0; j < 9; j++){
			texts[i][j].setForeground(Color.BLACK);
			texts[i][j].setText("" + puzzle.getData(i, j));
			texts[i][j].setEditable(false);
			
		    }
		}
	    }
	    catch (NullPointerException a) {
	    }
	}
	if(s.equals("Check Answers")){ // If not null, compare the input to the data
	    for(int i = 0; i < 9; i++){         //turn green if correct, red if not
		for(int j = 0; j < 9; j++){
		    if (texts[i][j].getValue() != null) {                        
			if((int)texts[i][j].getValue() == puzzle.getData(i, j)){
			    if (texts[i][j].isEditable()) {
				texts[i][j].setForeground(Color.GREEN);
				texts[i][j].setEditable(false);
			    }
			}
			else if (texts[i][j].isEditable()){
			    texts[i][j].setForeground(Color.RED);
			}
		    }
		}
	    }
	    boolean win = true;
	    for (int i = 0; i < 9;i++) {
		for (int x = 0; x < 9; x++) {
		    if((int)texts[i][x].getValue() != puzzle.getData(i,x)) {
			win = false;
		    }
		}
	    }
	    if (win) {
		EndPage w = new EndPage();
		w.setVisible(true);
	    }
	}
	if (s.equals("Hint")) {
	    boolean added = false;
	    int x, i;
	    int checks = 0;
	    while (!(added) && checks < 1000) {
		x = randgen.nextInt(9);
		i = randgen.nextInt(9);
		if(texts[i][x].isEditable() && !(added)) {
		    texts[i][x].setText("" + puzzle.getData(i, x));
		    texts[i][x].setForeground(Color.BLACK);
		    texts[i][x].setEditable(false);
		    added = true;
		    checks = 1000;
		}
		checks ++;
	    }
	}
	if(s.equals("Help")){
	    HelpWindow h = new HelpWindow();
	    h.setVisible(true);
	}
	if(s.equals("Reset")){
	    for (int i = 0; i < 9; i++) {
                for (int j = 0; j < 9; j++) {
                    if(texts[i][j].isEditable()){
			texts[i][j].setText(null);
		    }
                }
	    }
	}
	if(s.equals("Number of Errors:")){
	    int numErrs = 0;
	    for (int i = 0; i < 9; i++) {
                for (int j = 0; j < 9; j++) {
		    if(texts[i][j].getValue() != null){
			if((int)texts[i][j].getValue() != puzzle.getData(i, j)){
			    numErrs++;
			}
		    }
		}
	    }
	    numErrorsText.setText("" + numErrs);
	    boolean win = true;
	    for (int i = 0; i < 9;i++) {
		for (int x = 0; x < 9; x++) {
		    if((int)texts[i][x].getValue() != puzzle.getData(i,x)) {
			win = false;
		    }
		}
	    }
	    if (win) {
		EndPage w = new EndPage();
		w.setVisible(true);
	    }
	}
    }
    public static void main(String[] args){
	SudokuWindow s = new SudokuWindow();
	s.setVisible(true);
    }
}
