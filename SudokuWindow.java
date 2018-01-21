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
import java.text.SimpleDateFormat;
import java.util.Calendar;


public class SudokuWindow extends JFrame implements ActionListener{

    private String username;
    private String level;
    private Sudoku puzzle;
    private Random randgen;
    private JPanel pane;
    private JPanel sudokuPane;
    private JPanel buttonPane;
    private JPanel buttonPane2;    
    private JFormattedTextField[][] texts; 
    private JTextField numErrorsText;
    private Timer timer;
    private JLabel timerLabel;
    private JTextField seedText;

    //public final static int ONE_SECOND = 1000;

    public SudokuWindow(String user, String lev){

	puzzle = null;

	username = user;
	level = lev;
	
	randgen = new Random();

	this.setTitle(username + "'s Sudoku Game");

        this.setSize(750,650);

        this.setLocation(0,0);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	this.setResizable(false);

	//NOTE: THE NEXT SIX LINES ARE EDITED BUT TAKEN FROM THE FOLLOWING LINK: https://stackoverflow.com/questions/2554684/multiple-layout-managers-in-java  I used this because I am having a problem with panes that I cannot solve and am using this for testing purposes at the moment

	pane = new JPanel(new BorderLayout());

	sudokuPane = new JPanel(new GridLayout(9, 9));

	buttonPane = new JPanel(new GridLayout(11, 1));

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
        seedText = new JTextField(1);

	//JTextField time = new JTextField("0");
	//JTextField seed = new JTextField("Load Puzzle");
	//JTextField timerLabel = new JTextField("Timer");
	//timerLabel.setBounds(30,30,30,30);


	//Britni - this is the timer thing I added and I made it start in Create Puzzle       
	Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        timerLabel = new JLabel( sdf.format(cal.getTime()));
    
	SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
	int interval = 1000; // 1000 ms

	timer = new Timer(interval, new ActionListener() {
        @Override
	    public void actionPerformed(ActionEvent e) {
            Calendar now = Calendar.getInstance();
            timerLabel.setText(dateFormat.format(now.getTime()));
        }
	    });





	JButton submit = new JButton("Submit");
	JButton restart = new JButton("Restart");

	seedText.addActionListener(this);
	seedText.setEditable(false);
	seedText.setText("Seed: ");
	
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

	submit.addActionListener(this);

	restart.addActionListener(this);

	buttonPane.add(seedText);
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
	buttonPane.add(submit);
	buttonPane.add(restart);

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
	/*int time1 = 0;
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
	this.getContentPane().add(pane);*/
    }
    

    public void actionPerformed(ActionEvent e){
	String s = e.getActionCommand();
	if(s.equals("Create Puzzle")){
	    puzzle = new Sudoku(level);
	    // seedText.setText(puzzle.getSeed());
	    puzzle.createPuzzle();   //We need to clear board first
	    for(int i = 0; i < 9; i++){                               //Britni -- Creates 81 JTextBoxes that fit within the board     
		for(int j = 0; j < 9; j++){
		    texts[i][j].setText(null);
		    texts[i][j].setEditable(true);
		    texts[i][j].setForeground(Color.BLUE);
		    if(puzzle.getInput(i,j) != 0){
			texts[i][j].setText("" + puzzle.getInput(i, j));
			texts[i][j].setEditable(false);
			texts[i][j].setForeground(Color.BLACK);
		    }else{
			texts[i][j].setForeground(Color.BLUE);

		    }
		}
	    }
	    timer.start();
	    seedText.setText("Seed: " + puzzle.getSeed());
	}
	
	if(s.equals("Display Solution")){
	    if(puzzle != null){
		SolutionWindow sw = new SolutionWindow(puzzle.getData());
		sw.setVisible(true);
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
	    WinningWindow ww;
	    if (win) {
		ww = new WinningWindow(10);
		ww.setVisible(true);
	    }
	}
	if (s.equals("Hint")) {
	    if(puzzle != null){
		boolean added = false;
		int x, i;
		while (!(added)) {
		    x = randgen.nextInt(9);
		    i = randgen.nextInt(9);
		    if(texts[i][x].isEditable() && !(added)) {
			texts[i][x].setText("" + puzzle.getData(i, x));
			texts[i][x].setForeground(Color.BLACK);
			texts[i][x].setEditable(false);
			added = true;
		    }

		}
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
	    if (puzzle != null) {
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
	if(s.equals("Submit") && puzzle != null){
	    boolean error = false;
	    //while(!isGood){
	    WinningWindow ww;
	    ErrorWindow ew;
		for(int i = 0; i < 9; i++) {
		    for (int j = 0; j < 9; j++) {
			if(texts[i][j].getValue() != null){
			    if((int)texts[i][j].getValue() != puzzle.getData(i, j)){
				//OPEN ERROR WINDOW;
				ew = new ErrorWindow();
				ew.setVisible(true);
			        i = 10;
				j = 10;
				error = true;
			    }
			}else{
			    ew = new ErrorWindow();
			    ew.setVisible(true);
			    i = 10;
			    j = 10;
			    error = true;
			}
		    }
		}
		if(!error){
		    ww = new WinningWindow(10);
		    ww.setVisible(true);
		}
	}
	if(s.equals("Restart")){
	    RestartWindow rw = new RestartWindow(this);
	    rw.setVisible(true);
	}

    }

    public static void main(String[] args){
	SudokuWindow s = new SudokuWindow("Guest","Medium");
	s.setVisible(true);
    }
}
