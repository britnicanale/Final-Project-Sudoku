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
    private int startTime;
    private int endTime;
    private int seed;
    private String currentTime;
    
    public SudokuWindow(String user, String lev, int s){

	puzzle = null;

	username = user;
	level = lev;
	seed = s;
	
	randgen = new Random();

	this.setTitle(username + "'s " + lev + " Sudoku Game");   //Allows the heading of the window to have the username and level

        this.setSize(750,650);

        this.setLocation(0,0);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	this.setResizable(false);

	//NOTE: THE NEXT SIX LINES ARE EDITED BUT TAKEN FROM THE FOLLOWING LINK: https://stackoverflow.com/questions/2554684/multiple-layout-managers-in-java  I used this because I am having a problem with panes that I cannot solve and am using this for testing purposes at the moment

	pane = new JPanel(new BorderLayout());

	sudokuPane = new JPanel(new GridLayout(9, 9));    //Creates a grid for the Sudoku board

	buttonPane = new JPanel(new GridLayout(11, 1));   //Creates a pane for the buttons

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

	//Britni - this is the timer, I edited the code from the following link: https://stackoverflow.com/questions/13811224/java-display-current-time        
	Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
    
	SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
	int interval = 1000; 

	timer = new Timer(interval, new ActionListener() {
        @Override
	    public void actionPerformed(ActionEvent e) {
            Calendar now = Calendar.getInstance();
            currentTime = "" + dateFormat.format(now.getTime());
        }
	    });
	timer.start();




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

	submit.addActionListener(this);

	restart.addActionListener(this);

	buttonPane.add(seedText);
	buttonPane.add(createPuzzle);
	buttonPane.add(displaySolution);
	buttonPane.add(checkAnswers);
	buttonPane.add(numErrors);
	buttonPane.add(numErrorsText);
     	buttonPane.add(hint);
	buttonPane.add(help);
	buttonPane.add(reset);
	buttonPane.add(submit);
	buttonPane.add(restart);

	Font font = new Font("SansSerif", Font.BOLD, 20);
	
	texts = new JFormattedTextField[9][9];

	for(int i = 0; i < 9; i++){                               //Britni -- Creates 81 JFormattedTextFields that fit within the board

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
		formatter.setValueClass(Integer.class);  //This sets the specificities for the Number Formatters, only allows it to take ints from 1-9 and null
		formatter.setMinimum(1);
		formatter.setMaximum(9);
		formatter.setAllowsInvalid(false);    
		formatter.setCommitsOnValidEdit(true);
		JFormattedTextField b = new JFormattedTextField(formatter);
		b.setHorizontalAlignment(JTextField.CENTER);
		b.setFont(font);
		b.addActionListener(this);
		b.setEditable(false);  //You can't add random values into the board
		texts[i][j]= b;
		if ((i == 2 || i == 5) && (j == 2 || j == 5)) {  //Creates borders that separate 3x3 subgrids
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


    public int calcTime() {
	String time = currentTime;
	int ans = 0;
	ans += Integer.valueOf(time.substring(time.length()-2));
	ans += Integer.valueOf(time.substring(3,5)) * 60;
	ans += Integer.valueOf(time.substring(0,2)) * 60 * 60;
	return ans;
    }

    
    public void createPuzzle(){
	if (seed >= 0 && seed <= 5) {
	    puzzle = new Sudoku(level, seed);
	}
	else {
	    puzzle = new Sudoku(level);
	}
	puzzle.createPuzzle();         
	for(int i = 0; i < 9; i++){
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
	
	seedText.setText("Seed: " + puzzle.getSeed());
    }

    public void reset(){  // Resets all values to those originally displayed
	for (int i = 0; i < 9; i++) {
	    for (int j = 0; j < 9; j++) {  
		if(texts[i][j].getForeground() != Color.BLACK){                                                       
		    texts[i][j].setText(null);          
		}  
	    }
	}     
    }

    public boolean isFull(){
	for (int i = 0; i < 9;i++){
	    for (int x = 0; x < 9; x++){
		if(texts[i][x].getValue()  == null){
		    return false;
		}
	    }
	}
	return true;
    }

    public void actionPerformed(ActionEvent e){
	String s = e.getActionCommand();
	if(s.equals("Create Puzzle")){
	    startTime = calcTime();
	    if(puzzle != null){
		CreatePuzzleWindow cpw = new CreatePuzzleWindow(this);  //Alerts the user that their current puzzle will be terminated if they select this while they already have a puzzle opened
		cpw.setVisible(true);
	    }else{
		createPuzzle();
	    }
	}	
	if(s.equals("Display Solution")){  // Opens a new window with a filled in board
	    if(puzzle != null){
		SolutionWindow sw = new SolutionWindow(puzzle.getData());
		sw.setVisible(true);
	    }
	}
	if(s.equals("Check Answers") && puzzle != null){ // If not null, compare the input to the data
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
	    for (int i = 0; i < 9;i++){
		for (int x = 0; x < 9; x++){
		    if (texts[i][x].getValue() != null){
			if((int)texts[i][x].getValue() != puzzle.getData(i,x)) {
			    win = false;
			}
		    }else{
			win = false;
		    }
		}
	    }
	    WinningWindow ww;
	    if (win) {  //Checks if you won 
		endTime = calcTime();
		ww = new WinningWindow(level, username, endTime - startTime, this);
		ww.setVisible(true);
	    }
	}
	if (s.equals("Hint")) {  // Adds a correct number to the puzzle 
	    if(puzzle != null && !isFull()){  // Prevents errors if Hint is pressed before Create Puzzle
		boolean added = false;
		int x, i;
		while (!(added)) {
		    x = randgen.nextInt(9);
		    i = randgen.nextInt(9);
		    if(texts[i][x].getValue() == null && !(added)) {
			texts[i][x].setText("" + puzzle.getData(i, x));
			texts[i][x].setForeground(Color.BLUE);
			texts[i][x].setEditable(false);
			added = true;
		    }
		}
	    }
	    
	}
	if(s.equals("Help")){  // Opens a window with instructions
	    HelpWindow h = new HelpWindow();
	    h.setVisible(true);
	}
	if(s.equals("Reset")){
	    ResetWindow rsw = new ResetWindow(this); //Opens window alerting user that their progress will be lost if they select this option, if they continue the reset() is called
	    rsw.setVisible(true);
	}
	if(s.equals("Number of Errors:")){ //Counts and returns number of errors, doesn't display what is right and wrong
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
		if (numErrorsText.getText().equals("0") && isFull()) {

		    endTime = calcTime();
		    WinningWindow ww = new WinningWindow(level, username, endTime - startTime, this);

		    ww.setVisible(true);
		}
	    }
	}
	if(s.equals("Submit") && puzzle != null){
	    boolean error = false;
	    WinningWindow ww;
	    ErrorWindow ew;
		for(int i = 0; i < 9; i++) {
		    for (int j = 0; j < 9; j++) {
			if(texts[i][j].getValue() != null){
			    if((int)texts[i][j].getValue() != puzzle.getData(i, j)){
				//OPENS ERROR WINDOW saying that their are still mistakes/empty boxes;
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

		    endTime = calcTime(); //Opens window displaying time took to complete the puzzle
		    ww = new WinningWindow(level, username, endTime - startTime, this);
		    ww.setVisible(true);
		}
	}
	if(s.equals("Restart")){ //Reopens IntroWindow
	    RestartWindow rw = new RestartWindow(this);
	    rw.setVisible(true);
	}

    }

    public static void main(String[] args){
	SudokuWindow s = new SudokuWindow("Guest","Medium", 6);
	s.setVisible(true);
    }
   
}
