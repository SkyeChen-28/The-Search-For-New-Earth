//The Search For New Earth
//By: Skye Chen and Gary Wei
//The "NewEarth" Class
//Start date: May 5, 2017

import java.awt.*;
import java.awt.event.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.*;
import java.io.*;
import java.awt.image.*;
import javax.imageio.*;

import hsa.Console;

public class NewEarth extends Canvas
{
    int myX = 300, myY = 300; // Telescope laser location

    int celOb1 = (int) (Math.random () * 6 + 1); //random planet selector for level 5
    int celOb2 = (int) (Math.random () * 6 + 1);
    int celOb3 = (int) (Math.random () * 3 + 1);

    static int lev0;
    static int lev1;
    static int lev2;
    static int lev3;
    static int lev4;
    static int lev5;
    static int foundlv2;
    static int foundlv3;
    static int foundlv4;
    static int foundlv5;
    static boolean detector;
    static boolean found;
    Image MillFal = loadImage ("game_images/MillFal.jpg");
    Image supernovaImage = loadImage ("game_images/supernova.jpg");
    static Console c;           // The output console

    public static void main (String[] args)
    {
	c = new Console (30, 100); // pixel border x(0,800), y(0,600)
	// Place your program here.  'c' is the output console
	double grant = 0; // Currency
	Image heli = loadImage ("game_images/helicopter.jpg");
	Image err = loadImage ("game_images/ErrorMessage.jpg");
	// Use this to draw image:
	// c.drawImage (heli, x, y, null);

	////////////////////////////////////////////////////////////////////
	// Starry background
	int[] arrayx = new int [20];
	for (int i = 0 ; i <= 19 ; i++)
	{
	    arrayx [i] = (int) (Math.random () * 700);
	}
	int[] arrayy = new int [20];
	for (int i = 0 ; i <= 19 ; i++)
	{
	    arrayy [i] = (int) (Math.random () * 500);
	}

	for (int flash = 1 ; flash < 6 ; flash++) // TITLE
	{
	    // TITLE
	    c.setColor (Color.black);
	    c.fillRect (0, 0, 1000, 1000);
	    c.setColor (Color.blue);
	    Font title = new Font ("Comic Sans MS", Font.BOLD, 30);
	    c.setFont (title);
	    c.drawString ("THE SEARCH FOR THE NEW EARTH!", 135, 300);

	    c.setColor (Color.red);
	    star2 (arrayx, arrayy);
	    delay (15); // DELAY DEPENDS ON HOW FAST YOUR COMPUTER IS!
	    c.setColor (Color.black);
	    c.fillRect (0, 0, 1000, 1000);
	    c.setColor (Color.blue);
	    c.setFont (title);
	    c.drawString ("THE SEARCH FOR THE NEW EARTH!", 135, 300);
	    c.setColor (Color.red);
	    star1 (arrayx, arrayy);
	    delay (15);
	    c.setColor (Color.black);
	    c.fillRect (0, 0, 1000, 1000);
	    c.setColor (Color.blue);
	}
	int disclaimer = JOptionPane.showConfirmDialog (
		null,
		"If at any time, the game doesn`t do what you expect it to do, don`t yell at the programmers. Java is just being a little slow, just pause the program and then resume it.",
		"DISCLAIMER!!!",
		JOptionPane.OK_CANCEL_OPTION);

	int skip = JOptionPane.showConfirmDialog (
		null,
		"Would you like to skip the intro? (If this is your first time, you may want to read it)",
		"To skip or not to skip, that is the question",
		JOptionPane.YES_NO_OPTION);
	if (skip == 1)
	{
	    for (int a = 900 ; a > 100 ; a = a - 2) // Twinkles stars
	    { // ^^^Change to 800 if more text is to be added!
		c.setColor (Color.black);
		c.fillRect (0, 0, 1000, 1000);
		if (a % 50 <= 25)
		{
		    c.setColor (Color.red);
		    star2 (arrayx, arrayy);
		}

		if (a % 50 >= 25)
		{
		    c.setColor (Color.red);
		    star1 (arrayx, arrayy);
		}
		// INTRO
		Font intro = new Font ("Comic Sans MS", Font.PLAIN, 18);
		c.setFont (intro);
		c.setColor (Color.yellow);
		c.drawString ("It is the year 2107. Water levels have risen nearly two meters in the past 9 decades", 41, a - 300);
		c.drawString ("and they are past the point of no return, with much of the coastlines around the", 62, a - 280);
		c.drawString ("world completely submerged. The Global Government realizes the dire situation that", 47, a - 260);
		c.drawString ("Planet Earth has been plunged into over the last century and recognizes the need of the", 39, a - 240);
		c.drawString ("human race for a new home. As such, the Government has quadrupled the budget for", 50, a - 220);
		c.drawString ("space exploration and quintupled the grant money available for research into new", 57, a - 200);
		c.drawString ("planets that could possibly be suitable for human inhabitation. You are part of a team", 45, a - 180);
		c.drawString ("assembled by Dr. Richard Sun, and your goal is to accumulate enough money for your", 48, a - 160);
		c.drawString ("team to carry out their space exploration for a New Earth with the recently-launched", 41, a - 140);
		c.drawString ("James Webb VII Space Telescope.", 240, a - 120);

		delay (10);
	    }
	    c.clear ();
	}


	c.clear ();
	// Choose sex
	Frame sex = new Frame ();

	JRadioButton male = new JRadioButton ("Male");
	JRadioButton female = new JRadioButton ("Female");
	JRadioButton other = new JRadioButton ("Other"); //Apache Attack Helicopter

	boolean male1, female1, other1, sex1, error;
	Color hair = new Color (0, 0, 0);
	Color skin = new Color (0, 0, 0);
	String message = "What is your sex? (Choose only one)";
	Object[] params = {male, female, other};
	c.println ("Welcome! Customize your avatar!");
	c.println (message);
	int n = JOptionPane.showConfirmDialog (sex, params, message, JOptionPane.OK_CANCEL_OPTION);

	male1 = male.isSelected ();
	female1 = female.isSelected ();
	other1 = other.isSelected ();
	sex1 = male.isSelected () || female.isSelected () || other.isSelected ();
	error = (male1 && female1) || (male1 && other1) || (other1 && female1);
	if (error)
	{
	    c.clear ();
	    c.drawImage (err, 250, 150, null);
	    c.println ("ERROR! CHOOSE ONLY ONE OPTION PLEASE!");
	    delay (10);
	    c.println ("Well, you tried to divide by zero and this is what happened,");
	    c.println ("You have failed your mission, but feel free to try again!");
	    delay (10);
	    c.println ("***TERMINATING***");
	    delay (1000);
	    System.exit (0);
	}


	// Choose Name
	c.clear ();
	String name;
	c.println ("Welcome! What is your name?");
	name = c.readLine ();
	c.clear ();

	// Checks your sex
	if (male1)
	{
	    c.println ("Nice to meet you Mr. " + name + "!");
	    sex1 = male1;
	}


	else
	{
	    if (female1)
	    {
		c.println ("Nice to meet you Ms. " + name + "!");
		sex1 = female1;
	    }
	    else
	    {
		other = new JRadioButton ("Other");
		c.println ("Nice to meet you other Apache Attack Helicopter Commander " + name + "!");
		other1 = true;
		sex1 = other1;
	    }
	}


	// Displays Silhouette
	int a = 375, b = 275; //position of silhouette
	c.setColor (Color.black);
	avatar (375, 275, sex1, skin, hair, male1, female1, other1, heli);

	if (sex1 == other1)
	{
	}


	else
	{
	    // Hair Colour
	    Frame frame1 = new Frame (); //Blue, Pink, Purple, Brown, Bioluminescent Green

	    // Needs work on avatar display

	    JRadioButton blue = new JRadioButton ("Blue");
	    JRadioButton pink = new JRadioButton ("Pink");
	    JRadioButton purple = new JRadioButton ("Purple");
	    JRadioButton brown = new JRadioButton ("Brown");
	    JRadioButton green = new JRadioButton ("Bioluminescent Green");

	    boolean blue1, pink1, purple1, brown1, green1;
	    String message1 = "What is your hair colour? (Choose only one)";
	    Object[] params1 = {blue, pink, purple, brown, green, other};
	    c.println (message1);
	    int n1 = JOptionPane.showConfirmDialog (frame1, params1, message1, JOptionPane.OK_CANCEL_OPTION);

	    blue1 = blue.isSelected ();
	    pink1 = pink.isSelected ();
	    purple1 = purple.isSelected ();
	    brown1 = brown.isSelected ();
	    green1 = green.isSelected ();
	    other1 = other.isSelected ();
	    error = (blue1 && pink1) || (blue1 && purple1) || (blue1 && brown1) || (blue1 && green1) || (blue1 && other1) || (pink1 && purple1) || (pink1 && brown1) || (pink1 && green1) || (pink1 && other1) || (purple1 && brown1) || (purple1 && green1) || (purple1 && other1) || (brown1 && green1) || (brown1 && other1) || (green1 && other1);
	    // Conditional statement checks hair colour
	    c.clear ();
	    if (error)
	    {
		c.clear ();
		c.drawImage (err, 250, 150, null);
		c.println ("ERROR! CHOOSE ONLY ONE OPTION PLEASE!");
		delay (10);
		c.println ("Well " + name + ", you tried to divide by zero and this is what happened,");
		c.println ("You have failed your mission, but feel free to try again!");
		delay (10);
		c.println ("***TERMINATING***");
		delay (1000);
		System.exit (0);
	    }
	    else if (blue1)
	    {
		hair = new Color (0, 0, 255);
	    }
	    else if (pink1)
	    {
		hair = new Color (255, 192, 203);
	    }
	    else if (purple1)
	    {
		hair = new Color (128, 0, 128);
	    }
	    else if (brown1)
	    {
		hair = new Color (139, 69, 19);
	    }
	    else if (green1)
	    {
		hair = new Color (0, 255, 0);
	    }
	    else // Cancel + other
	    {
		hair = new Color (255, 255, 255);
		other = new JRadioButton ("Other");
		c.println ("Hello baldie! You found an Easter Egg!");
	    }

	    avatar (375, 275, sex1, skin, hair, male1, female1, other1, heli);

	    delay (1);

	    //Skin colour (Red, Blue, Green) {Easter Egg; BGR Input = White)

	    Frame frame2 = new Frame (); // Accidentally pasted hair into skin

	    JRadioButton redS = new JRadioButton ("Red");
	    JRadioButton blueS = new JRadioButton ("Blue");
	    JRadioButton greenS = new JRadioButton ("Green");
	    boolean redS1, blueS1, greenS1, all;
	    String mess2 = "What is your skin colour? (Choose only one)";
	    Object[] params2 = {redS, blueS, greenS};
	    c.println (mess2);
	    int n2 = JOptionPane.showConfirmDialog (frame2, params2, mess2, JOptionPane.OK_CANCEL_OPTION);

	    redS1 = redS.isSelected ();
	    blueS1 = blueS.isSelected ();
	    greenS1 = greenS.isSelected ();
	    error = (redS1 && blueS1) || (redS1 && greenS1) || (blueS1 && greenS1);
	    all = redS1 && blueS1 && greenS1;
	    c.clear ();
	    if (all)
	    {
		c.println ("You chose a white!");
		c.println ("You found an Easter egg!");
		skin = new Color (250, 250, 250);
	    }
	    else
	    {
		if (error)
		{
		    c.clear ();
		    c.drawImage (err, 250, 150, null);
		    c.println ("ERROR! CHOOSE ONLY ONE OPTION PLEASE!");
		    delay (10);
		    c.println ("Well " + name + ", you tried to divide by zero and this is what happened,");
		    c.println ("You have failed your mission, but feel free to try again!");
		    delay (10);
		    c.println ("***TERMINATING***");
		    delay (1000);
		    System.exit (0);
		}
		else
		{
		    if (redS1)
		    {
			skin = new Color (255, 0, 0);
		    }
		    else
		    {
			if (blueS1)
			{
			    skin = new Color (0, 0, 255);
			}
			else
			{
			    if (greenS1)
			    {
				skin = new Color (0, 255, 0);
			    }
			    else // Cancel
			    {
				c.println ("You chose a white!");
				c.println ("You found an Easter egg!");
				skin = new Color (250, 250, 250);
			    }
			}
		    }
		}
	    }
	    avatar (375, 275, sex1, skin, hair, male1, female1, other1, heli);
	    delay (1);
	}


	c.println ("Nice to meet you " + name + "! Well, let's get started; you need to collect enough grant money ($10000)");
	c.println ("to fund your team's search for a New Earth and for that you will need to win 5 grants.");
	String key;
	c.print ("Input any key to continue then press <Enter>: ");
	key = c.readString ();

	c.clear ();
	if (sex1 == male1)
	{
	    male (a, b, skin, hair);
	}


	else if (sex1 == female1)
	{
	    female (a, b, skin, hair);
	}


	else if (sex1 == other1)
	{
	    c.drawImage (heli, 275, 300, null); // DISPLAY HELICOPTER HERE!
	}


	else
	{
	    c.drawImage (heli, 275, 300, null); // DISPLAY HELICOPTER HERE!
	}


	c.println ("Here's the first grant opportunity (tutorial/Level 0):");
	c.println ("Find a celestial body in this patch of the night sky! You can change the");
	c.println ("trajectory of the telescope with your arrow keys or WASD keys. Give it a try, and");
	c.println ("find one planet, or star.");
	c.println ("Use the WASD keys for a slow and thorough scan or use the Arrow keys for a fast scan");
	c.println ("Your laser beam and crosshair will change from white, to yellow, to orange, and then red as you get");
	c.println ("closer to a celestial object. AND IF ANY ONE ASKS, THIS IS THE TEXT CONSOLE!");
	c.println ("(Hint: do a quick scan in the tutorial) When you are done reading, input any key and press <Enter>: ");
	key = c.readString ();

	// First mission!
	for (found = false ; found == false ;)
	{
	    lev0 = 1;
	    JFrame tutorial = new JFrame ("Tutorial Level 0");
	    tutorial.setDefaultCloseOperation (JFrame.DISPOSE_ON_CLOSE);

	    NewEarth ex = new NewEarth ();

	    tutorial.getContentPane ().add (ex);
	    tutorial.pack ();
	    tutorial.setResizable (false);
	    tutorial.setVisible (true);
	    ex.requestFocus ();

	    int warning = JOptionPane.showConfirmDialog (
		    null,
		    "Please do not drag any of the console screens around, only click back and forth between them!",
		    "WARNING!!!",
		    JOptionPane.OK_CANCEL_OPTION);

	    c.println ("Don't type in any key nor <Enter> until you found a celestial body!");
	    c.readString ();
	} //End of first missioni

	for (boolean tutorialID = false ; tutorialID == false ;) //tutorial lv. classification database
	{

	    for (boolean exit = false ; exit == false ;) //NEIL DEGRASS TYSON DATABASE
	    {
		avatar (375, 275, sex1, skin, hair, male1, female1, other1, heli);
		String objectFound = "";

		c.clear ();

		// int noti = JOptionPane.showConfirmDialog (
		//         null,
		//         "The programmers regret to inform you the user that the game is buggy if you leave the game console on. So write down/memorize/take a picture of the planets info and then close it.",
		//         "Notification!",
		//         JOptionPane.OK_CANCEL_OPTION);

		c.println ("Welcome to the Neil DeGrasse Tyson celestial object data base,");
		c.println ("Feel free to exit when you think you have made a positive identification");
		c.println ("or choose ONE of the following to find out more:");

		Frame Legend = new Frame ();

		JRadioButton Planet = new JRadioButton ("Planets");
		JRadioButton Star = new JRadioButton ("Stars");
		JRadioButton Others = new JRadioButton ("Other celestial objects");
		JRadioButton Exit = new JRadioButton ("Exit the NDGT database");

		boolean Planet1, Star1, Others1, Exit1, errorLegend;
		String messageLegend = "Which category would you like to learn more about " + name + "?";
		c.println ();
		c.println (messageLegend);
		Object[] paramsLegend = {Planet, Star, Others, Exit};
		int category = JOptionPane.showConfirmDialog (Legend, paramsLegend, messageLegend, JOptionPane.OK_CANCEL_OPTION);

		Planet1 = Planet.isSelected ();
		Star1 = Star.isSelected ();
		Others1 = Others.isSelected ();
		Exit1 = Exit.isSelected ();
		errorLegend = ((Planet1 & Star1) || (Planet1 && Others1) || (Star1 && Others1) || (Planet1 && Exit1) || (Star1 && Exit1) || (Others1 && Exit1));

		if (errorLegend)
		{
		    c.clear ();
		    c.drawImage (err, 250, 150, null);
		    c.println ("ERROR! CHOOSE ONLY ONE OPTION PLEASE!");
		    delay (10);
		    c.println ("Well " + name + ", you tried to divide by zero and this is what happened,");
		    c.println ("You have failed your mission, but feel free to try again!");
		    delay (10);
		    c.println ("***TERMINATING***");
		    delay (1000);
		    System.exit (0);
		}
		else if (Planet1)
		{
		    objectFound = "Planets";
		}
		else if (Star1)
		{
		    objectFound = "Stars";
		}
		else if (Others1)
		{
		    objectFound = "Others";
		}

		else
		{
		    exit = true;
		}

		legend (objectFound);
	    } // end of level tutorial database

	    //{<DON'T REMOVE //!  Start of level tutorial classification
	    c.println ("Thank you for using the Neil DeGrasse Tyson celestial object data base.");
	    c.println ("Now you must properly identify what that celestial object was.");
	    c.println ("Choose only ONE option please!");
	    c.println ();
	    c.println ("Just choose whether your newly - discovered space - thing is a planetary body, a");
	    c.println ("star, or something else! Then you can further specify what exactly your");
	    c.println ("space - thing is. At the end of the classification window, you can confirm your");
	    c.println ("classification. But be careful - even though you get reward money from the");
	    c.println ("Global Government for correctly classifying a celestial object, the AstroPhysical");
	    c.println ("Society of Earth (APSE) will dock your pay each time you incorrectly classify a");
	    c.println ("space - thing! Not to mention they 'll mock your scientific prowess while haughtily");
	    c.println ("stroking their science - beards.");

	    Frame tutorialObject = new Frame (); // Quiz for tutorial pt. 1

	    JRadioButton Planet = new JRadioButton ("A Planet");
	    JRadioButton Star = new JRadioButton ("A Star");
	    JRadioButton Others = new JRadioButton ("Other celestial objects");

	    boolean Planet1, Star1, Others1, errorTutorial;
	    String messageTutorial = "Which celestial body do you think this is " + name + "?";
	    c.println ();
	    c.println (messageTutorial);
	    Object[] paramsTutorial = {Planet, Star, Others};

	    for (boolean affirm = false ; affirm == false ;)
	    {
		int category = JOptionPane.showConfirmDialog (tutorialObject, paramsTutorial, messageTutorial, JOptionPane.OK_CANCEL_OPTION);
		int confirm = JOptionPane.showConfirmDialog (
			null,
			"Remember " + name + ", you will lose grant money if you get this wrong! And for your own sake, go back if you chose more than 1 answer, the APSE doesn't like uncertainty!",
			"ARE YOU SURE???!!!",
			JOptionPane.YES_NO_OPTION);
		if (confirm == JOptionPane.YES_OPTION)
		{
		    affirm = true;
		}
		else
		{
		    affirm = false;
		}
	    }

	    Planet1 = Planet.isSelected ();
	    Star1 = Star.isSelected ();
	    Others1 = Others.isSelected ();
	    errorTutorial = ((Planet1 & Star1) || (Planet1 && Others1) || (Star1 && Others1));

	    if (errorTutorial)
	    {
		c.clear ();
		c.drawImage (err, 250, 150, null);
		c.println ("ERROR! CHOOSE ONLY ONE OPTION PLEASE!");
		delay (10);
		c.println ("Well " + name + ", you tried to divide by zero and this is what happened,");
		c.println ("You have failed your mission, but feel free to try again!");
		delay (10);
		c.println ("***TERMINATING***");
		delay (1000);
		System.exit (0);
	    }
	    else if (Planet1)
	    {
		Frame planets = new Frame (); //Quiz for tutorial pt. 2

		JRadioButton Earth = new JRadioButton ("Earth-like");
		JRadioButton SuperEarth = new JRadioButton ("Super Earth");
		JRadioButton IceGiant = new JRadioButton ("Ice Giant");
		JRadioButton GasGiant = new JRadioButton ("Gas Giant");

		boolean Earth1, SuperEarth1, IceGiant1, GasGiant1, errorPlanets;
		String messagePlanets = "Which planet type do you think this is " + name + "?";
		c.println ();
		c.println (messagePlanets);
		Object[] paramsPlanets = {Earth, SuperEarth, IceGiant, GasGiant};
		for (boolean affirm = false ; affirm == false ;)
		{
		    int planetQuiz = JOptionPane.showConfirmDialog (planets, paramsPlanets, messagePlanets, JOptionPane.OK_CANCEL_OPTION);
		    int confirm = JOptionPane.showConfirmDialog (
			    null,
			    "Remember " + name + ", you will lose grant money if you get this wrong! And for your own sake, go back if you chose more than 1 answer, the APSE doesn't like uncertainty!",
			    "ARE YOU SURE???!!!",
			    JOptionPane.YES_NO_OPTION);
		    if (confirm == JOptionPane.YES_OPTION)
		    {
			affirm = true;
		    }
		    else
		    {
			affirm = false;
		    }
		}

		Earth1 = Earth.isSelected ();
		SuperEarth1 = SuperEarth.isSelected ();
		IceGiant1 = IceGiant.isSelected ();
		GasGiant1 = GasGiant.isSelected ();
		errorPlanets = ((Earth1 && SuperEarth1) || (Earth1 && IceGiant1) || (Earth1 && GasGiant1) || (SuperEarth1 && IceGiant1) || (SuperEarth1 && GasGiant1) || (IceGiant1 && GasGiant1));


		if (errorPlanets)
		{
		    c.clear ();
		    c.drawImage (err, 250, 150, null);
		    c.println ("ERROR! CHOOSE ONLY ONE OPTION PLEASE!");
		    delay (10);
		    c.println ("Well " + name + ", you tried to divide by zero and this is what happened,");
		    c.println ("You have failed your mission, but feel free to try again!");
		    delay (10);
		    c.println ("***TERMINATING***");
		    delay (1000);
		    System.exit (0);
		}
		else if (SuperEarth1)
		{
		    tutorialID = true;
		}
		else
		{
		    c.println ("NOPE! The APSE board members make snide remarks about your mother! ");
		    c.println ("Minus 3 dignity points :( How about you go out there and try it again?");
		    c.println ("Since this is the tutorial, the ASPE has decided not to deduct money from you");
		    c.println ("You may want to consult the Neil DeGrasse Tyson database again...");
		    c.print ("Press any key and then <Enter> to continue: ");
		    c.readString ();
		}
	    }
	    else
	    {
		c.println ("NOPE! The APSE board members make snide remarks about your mother! ");
		c.println ("Minus 3 dignity points :( How about you go out there and try it again?");
		c.println ("Since this is the tutorial, the ASPE has decided not to deduct money from you");
		c.println ("You may want to consult the Neil DeGrasse Tyson database again...");
		c.print ("Press any key and then <Enter> to continue: ");
		c.readString ();
	    }
	}


	String tutorialName;
	grant = grant + 1000;
	c.clear ();
	avatar (375, 275, sex1, skin, hair, male1, female1, other1, heli);
	c.println ("Good job " + name + "! You got ($1000) for that correct classification of a Super Earth Planet");
	c.print ("Your balance is now $");
	c.print (grant, 0, 2);
	c.print (" Go ahead, name the planet: "); //Space here is for grant
	tutorialName = c.readLine ();
	c.clear ();
	c.println ("You have named the Super Earth Planet \"" + tutorialName + "\"! Now you're ready to go out ");
	c.print ("there and do some space-searching! Press any key then <Enter> to continue: ");
	c.readString ();
	found = false;
	//}<DON'T REMOVE //! end of level tutorial classification

	for (found = false ; found == false ;) //LEVEL 1
	{
	    lev1 = 1;
	    JFrame level1 = new JFrame ("Level 1");
	    level1.setDefaultCloseOperation (JFrame.DISPOSE_ON_CLOSE);

	    NewEarth ex = new NewEarth ();

	    level1.getContentPane ().add (ex);
	    level1.pack ();
	    level1.setResizable (false);
	    level1.setVisible (true);
	    ex.requestFocus ();

	    int warning = JOptionPane.showConfirmDialog (
		    null,
		    "Remember, please do not drag any of the console screens around, only click back and forth between them!",
		    "WARNING!!!",
		    JOptionPane.OK_CANCEL_OPTION);

	    c.println ("Don't type in any key nor <Enter> until you found a celestial body!");
	    c.readString ();
	} // end of level 1

	for (boolean Level1ID = false ; Level1ID == false ;)  //First lv. classification database
	{

	    for (boolean exit = false ; exit == false ;) //NEIL DEGRASS TYSON DATABASE
	    {
		avatar (375, 275, sex1, skin, hair, male1, female1, other1, heli);
		String objectFound = "";

		c.clear ();

		c.println ("Welcome to the Neil DeGrasse Tyson celestial object data base,");
		c.println ("Feel free to exit when you think you have made a positive identification");
		c.println ("or choose ONE of the following to find out more:");

		Frame Legend = new Frame ();

		JRadioButton Planet = new JRadioButton ("Planets");
		JRadioButton Star = new JRadioButton ("Stars");
		JRadioButton Others = new JRadioButton ("Other celestial objects");
		JRadioButton Exit = new JRadioButton ("Exit the NDGT database");

		boolean Planet1, Star1, Others1, Exit1, errorLegend;
		String messageLegend = "Which category would you like to learn more about " + name + "?";
		c.println ();
		c.println (messageLegend);
		Object[] paramsLegend = {Planet, Star, Others, Exit};
		int category = JOptionPane.showConfirmDialog (Legend, paramsLegend, messageLegend, JOptionPane.OK_CANCEL_OPTION);

		Planet1 = Planet.isSelected ();
		Star1 = Star.isSelected ();
		Others1 = Others.isSelected ();
		Exit1 = Exit.isSelected ();
		errorLegend = ((Planet1 & Star1) || (Planet1 && Others1) || (Star1 && Others1) || (Planet1 && Exit1) || (Star1 && Exit1) || (Others1 && Exit1));

		if (errorLegend)
		{
		    c.clear ();
		    c.drawImage (err, 250, 150, null);
		    c.println ("ERROR! CHOOSE ONLY ONE OPTION PLEASE!");
		    delay (10);
		    c.println ("Well " + name + ", you tried to divide by zero and this is what happened,");
		    c.println ("You have failed your mission, but feel free to try again!");
		    delay (10);
		    c.println ("***TERMINATING***");
		    delay (1000);
		    System.exit (0);
		}
		else if (Planet1)
		{
		    objectFound = "Planets";
		}
		else if (Star1)
		{
		    objectFound = "Stars";
		}
		else if (Others1)
		{
		    objectFound = "Others";
		}

		else
		{
		    exit = true;
		}

		legend (objectFound);
	    } // end of level 1 database

	    //{<DON'T REMOVE //!  Start of level 1 classification
	    c.println ("Thank you for using the Neil DeGrasse Tyson celestial object data base.");
	    c.println ("Now you must properly identify what that celestial object was.");
	    c.println ("Choose only ONE option please!");
	    c.println ();

	    Frame Level1Object = new Frame (); // Quiz for level 1 pt. 1

	    JRadioButton Planet = new JRadioButton ("A Planet");
	    JRadioButton Star = new JRadioButton ("A Star");
	    JRadioButton Others = new JRadioButton ("Other celestial objects");

	    boolean Planet1, Star1, Others1, errorLevel1;
	    String messageLevel1 = "Which celestial body do you think this is " + name + "?";
	    c.println ();
	    c.println (messageLevel1);
	    Object[] paramsLevel1 = {Planet, Star, Others};
	    for (boolean affirm = false ; affirm == false ;)
	    {
		int category = JOptionPane.showConfirmDialog (Level1Object, paramsLevel1, messageLevel1, JOptionPane.OK_CANCEL_OPTION);
		int confirm = JOptionPane.showConfirmDialog (
			null,
			"Remember " + name + ", you will lose grant money if you get this wrong! And for your own sake, go back if you chose more than 1 answer, the APSE doesn't like uncertainty!",
			"ARE YOU SURE???!!!",
			JOptionPane.YES_NO_OPTION);
		if (confirm == JOptionPane.YES_OPTION)
		{
		    affirm = true;
		}
		else
		{
		    affirm = false;
		}
	    }

	    Planet1 = Planet.isSelected ();
	    Star1 = Star.isSelected ();
	    Others1 = Others.isSelected ();
	    errorLevel1 = ((Planet1 & Star1) || (Planet1 && Others1) || (Star1 && Others1));

	    if (errorLevel1)
	    {
		c.clear ();
		c.drawImage (err, 250, 150, null);
		c.println ("ERROR! CHOOSE ONLY ONE OPTION PLEASE!");
		delay (10);
		c.println ("Well " + name + ", you tried to divide by zero and this is what happened,");
		c.println ("You have failed your mission, but feel free to try again!");
		delay (10);
		c.println ("***TERMINATING***");
		delay (1000);
		System.exit (0);
	    }
	    else if (Planet1)
	    {
		Frame planets = new Frame (); //Quiz for level 1 pt. 2

		JRadioButton Earth = new JRadioButton ("Earth-like");
		JRadioButton SuperEarth = new JRadioButton ("Super Earth");
		JRadioButton IceGiant = new JRadioButton ("Ice Giant");
		JRadioButton GasGiant = new JRadioButton ("Gas Giant");

		boolean Earth1, SuperEarth1, IceGiant1, GasGiant1, errorPlanets;
		String messagePlanets = "Which planet type do you think this is " + name + "?";
		c.println ();
		c.println (messagePlanets);
		Object[] paramsPlanets = {Earth, SuperEarth, IceGiant, GasGiant};
		for (boolean affirm = false ; affirm == false ;)
		{
		    int planetQuiz = JOptionPane.showConfirmDialog (planets, paramsPlanets, messagePlanets, JOptionPane.OK_CANCEL_OPTION);
		    int confirm = JOptionPane.showConfirmDialog (
			    null,
			    "Remember " + name + ", you will lose grant money if you get this wrong! And for your own sake, go back if you chose more than 1 answer, the APSE doesn't like uncertainty!",
			    "ARE YOU SURE???!!!",
			    JOptionPane.YES_NO_OPTION);
		    if (confirm == JOptionPane.YES_OPTION)
		    {
			affirm = true;
		    }
		    else
		    {
			affirm = false;
		    }
		}

		Earth1 = Earth.isSelected ();
		SuperEarth1 = SuperEarth.isSelected ();
		IceGiant1 = IceGiant.isSelected ();
		GasGiant1 = GasGiant.isSelected ();
		errorPlanets = ((Earth1 && SuperEarth1) || (Earth1 && IceGiant1) || (Earth1 && GasGiant1) || (SuperEarth1 && IceGiant1) || (SuperEarth1 && GasGiant1) || (IceGiant1 && GasGiant1));


		if (errorPlanets)
		{
		    c.clear ();
		    c.drawImage (err, 250, 150, null);
		    c.println ("ERROR! CHOOSE ONLY ONE OPTION PLEASE!");
		    delay (10);
		    c.println ("Well " + name + ", you tried to divide by zero and this is what happened,");
		    c.println ("You have failed your mission, but feel free to try again!");
		    delay (10);
		    c.println ("***TERMINATING***");
		    delay (1000);
		    System.exit (0);
		}
		else if (IceGiant1)
		{
		    Level1ID = true;
		}
		else
		{
		    c.println ("NOPE! The APSE board members mock your brain size! Minus 3 ");
		    c.println ("dignity points. Also, they docked $100 from your pay! :( How about");
		    grant = grant - 100;
		    c.println ("Your balance is now $" + grant + ". Now you go out there and try it again!");
		    c.println ("You may want to consult the Neil DeGrasse Tyson database again...");
		    c.print ("Press any key and then <Enter> to continue: ");
		    c.readString ();
		    if (grant < 0)
		    {
			c.clear ();
			c.drawImage (err, 250, 150, null);
			c.println ("YOUR COMPANY IS IN DEBT!");
			delay (10);
			c.println ("Well, you tried to square root a negative number, and this is what happened.");
			c.println ("You have failed your mission, but feel free to try again!");
			delay (10);
			c.println ("***TERMINATING***");
			delay (1000);
			System.exit (0);
		    }
		}
	    }
	    else
	    {
		c.println ("NOPE! The APSE board members mock your brain size! Minus 3 ");
		c.println ("dignity points. Also, they docked $100 from your pay! :( How about");
		grant = grant - 100;
		c.println ("Your balance is now $" + grant + ". Now you go out there and try it again!");
		c.println ("You may want to consult the Neil DeGrasse Tyson database again...");
		c.print ("Press any key and then <Enter> to continue: ");
		c.readString ();
		if (grant < 0)
		{
		    c.clear ();
		    c.drawImage (err, 250, 150, null);
		    c.println ("YOUR COMPANY IS IN DEBT!");
		    delay (10);
		    c.println ("Well, you tried to square root a negative number, and this is what happened.");
		    c.println ("You have failed your mission, but feel free to try again!");
		    delay (10);
		    c.println ("***TERMINATING***");
		    delay (1000);
		    System.exit (0);
		}
	    }
	}


	String Level1Name;
	grant = grant + 1500;
	c.clear ();
	avatar (375, 275, sex1, skin, hair, male1, female1, other1, heli);
	c.println ("Good job! You got ($1500) for that correct classification of an Ice Giant Planet!");
	c.print ("Your balance is now $");
	c.print (grant, 0, 2);
	c.print (" Go ahead, name the planet: "); //Space here is for grant
	Level1Name = c.readLine ();
	c.clear ();
	c.println ("You have named the Ice Giant Planet \"" + Level1Name + "\"! Now you're ready to go out ");
	c.print ("there and do some more space-searching! Press any key then <Enter> to continue: ");
	c.readString ();
	found = false;
	//}< DON'T REMOVE end of lv. 1 classification

	for (found = false ; found == false ;) //LEVEL 2
	{
	    lev2 = 1;
	    JFrame level2 = new JFrame ("Level 2");
	    level2.setDefaultCloseOperation (JFrame.DISPOSE_ON_CLOSE);

	    NewEarth ex = new NewEarth ();

	    level2.getContentPane ().add (ex);
	    level2.pack ();
	    level2.setResizable (false);
	    level2.setVisible (true);
	    ex.requestFocus ();

	    c.println ("Don't type in any key nor <Enter> until you found a celestial body!");
	    c.readString ();
	} // end of level 2

	//Level 2 classifying process
	for (boolean level2ID = false ; level2ID == false ;)
	{
	    for (boolean exit = false ; exit == false ;) //NEIL DEGRASS TYSON DATABASE
	    {
		avatar (375, 275, sex1, skin, hair, male1, female1, other1, heli);
		String objectFound = "";

		c.clear ();

		c.println ("Welcome to the Neil DeGrasse Tyson celestial object data base,");
		c.println ("Feel free to exit when you think you have made a positive identification");
		c.println ("or choose ONE of the following to find out more:");

		Frame Legend = new Frame ();

		JRadioButton Planet = new JRadioButton ("Planets");
		JRadioButton Star = new JRadioButton ("Stars");
		JRadioButton Others = new JRadioButton ("Other celestial objects");
		JRadioButton Exit = new JRadioButton ("Exit the NDGT database");

		boolean Planet1, Star1, Others1, Exit1, errorLegend;
		String messageLegend = "Which category would you like to learn more about " + name + "?";
		c.println ();
		c.println (messageLegend);
		Object[] paramsLegend = {Planet, Star, Others, Exit};
		int category = JOptionPane.showConfirmDialog (Legend, paramsLegend, messageLegend, JOptionPane.OK_CANCEL_OPTION);

		Planet1 = Planet.isSelected ();
		Star1 = Star.isSelected ();
		Others1 = Others.isSelected ();
		Exit1 = Exit.isSelected ();
		errorLegend = ((Planet1 & Star1) || (Planet1 && Others1) || (Star1 && Others1) || (Planet1 && Exit1) || (Star1 && Exit1) || (Others1 && Exit1));

		if (errorLegend)
		{
		    c.clear ();
		    c.drawImage (err, 250, 150, null);
		    c.println ("ERROR! CHOOSE ONLY ONE OPTION PLEASE!");
		    delay (10);
		    c.println ("Well " + name + ", you tried to divide by zero and this is what happened,");
		    c.println ("You have failed your mission, but feel free to try again!");
		    delay (10);
		    c.println ("***TERMINATING***");
		    delay (1000);
		    System.exit (0);
		}
		else if (Planet1)
		{
		    objectFound = "Planets";
		}
		else if (Star1)
		{
		    objectFound = "Stars";
		}
		else if (Others1)
		{
		    objectFound = "Others";
		}

		else
		{
		    exit = true;
		}

		legend (objectFound);
	    } // end of level 2 database

	    //{<DON'T REMOVE //!  Start of level 2 classification
	    c.println ("Thank you for using the Neil DeGrasse Tyson celestial object data base.");
	    c.println ("Now you must properly identify what that celestial object was.");
	    c.println ("Choose only ONE option please!");
	    c.println ();

	    Frame Level2Object = new Frame (); // Quiz for level 2 pt. 1

	    JRadioButton Planet = new JRadioButton ("A Planet");
	    JRadioButton Star = new JRadioButton ("A Star");
	    JRadioButton Others = new JRadioButton ("Other celestial objects");

	    boolean Planet1, Star1, Others1, errorLevel2;
	    String messageLevel2 = "Which celestial body do you think this is?";
	    c.println ();
	    c.println (messageLevel2);
	    Object[] paramsLevel2 = {Planet, Star, Others};
	    for (boolean affirm = false ; affirm == false ;)
	    {
		int category = JOptionPane.showConfirmDialog (Level2Object, paramsLevel2, messageLevel2, JOptionPane.OK_CANCEL_OPTION);
		int confirm = JOptionPane.showConfirmDialog (
			null,
			"Remember " + name + ", you will lose grant money if you get this wrong! And for your own sake, go back if you chose more than 1 answer, the APSE doesn't like uncertainty!",
			"ARE YOU SURE???!!!",
			JOptionPane.YES_NO_OPTION);
		if (confirm == JOptionPane.YES_OPTION)
		{
		    affirm = true;
		}
		else
		{
		    affirm = false;
		}
	    }

	    Planet1 = Planet.isSelected ();
	    Star1 = Star.isSelected ();
	    Others1 = Others.isSelected ();
	    errorLevel2 = ((Planet1 & Star1) || (Planet1 && Others1) || (Star1 && Others1));

	    if (errorLevel2)
	    {
		c.clear ();
		c.drawImage (err, 250, 150, null);
		c.println ("ERROR! CHOOSE ONLY ONE OPTION PLEASE!");
		delay (10);
		c.println ("Well " + name + ", you tried to divide by zero and this is what happened,");
		c.println ("You have failed your mission, but feel free to try again!");
		delay (10);
		c.println ("***TERMINATING***");
		delay (1000);
		System.exit (0);
	    }
	    else if (((foundlv2 == 1) && (Planet1)) || ((foundlv2 == 2) && (Planet1)) || ((foundlv2 == 3) && (Others1)))
	    {
		if ((foundlv2 == 1) && (Planet1))
		{
		    Frame planets = new Frame (); //Quiz for level 2 planet 1 pt. 2

		    JRadioButton Earth = new JRadioButton ("Earth-like");
		    JRadioButton SuperEarth = new JRadioButton ("Super Earth");
		    JRadioButton IceGiant = new JRadioButton ("Ice Giant");
		    JRadioButton GasGiant = new JRadioButton ("Gas Giant");

		    boolean Earth1, SuperEarth1, IceGiant1, GasGiant1, errorPlanets;
		    String messagePlanets = "Which planet type do you think this is " + name + "?";
		    c.println ();
		    c.println (messagePlanets);
		    Object[] paramsPlanets = {Earth, SuperEarth, IceGiant, GasGiant};
		    for (boolean affirm = false ; affirm == false ;)
		    {
			int planetQuiz = JOptionPane.showConfirmDialog (planets, paramsPlanets, messagePlanets, JOptionPane.OK_CANCEL_OPTION);
			int confirm = JOptionPane.showConfirmDialog (
				null,
				"Remember " + name + ", you will lose grant money if you get this wrong! And for your own sake, go back if you chose more than 1 answer, the APSE doesn't like uncertainty!",
				"ARE YOU SURE???!!!",
				JOptionPane.YES_NO_OPTION);
			if (confirm == JOptionPane.YES_OPTION)
			{
			    affirm = true;
			}
			else
			{
			    affirm = false;
			}
		    }

		    Earth1 = Earth.isSelected ();
		    SuperEarth1 = SuperEarth.isSelected ();
		    IceGiant1 = IceGiant.isSelected ();
		    GasGiant1 = GasGiant.isSelected ();
		    errorPlanets = ((Earth1 && SuperEarth1) || (Earth1 && IceGiant1) || (Earth1 && GasGiant1) || (SuperEarth1 && IceGiant1) || (SuperEarth1 && GasGiant1) || (IceGiant1 && GasGiant1));


		    if (errorPlanets)
		    {
			c.clear ();
			c.drawImage (err, 250, 150, null);
			c.println ("ERROR! CHOOSE ONLY ONE OPTION PLEASE!");
			delay (10);
			c.println ("Well " + name + ", you tried to divide by zero and this is what happened,");
			c.println ("You have failed your mission, but feel free to try again!");
			delay (10);
			c.println ("***TERMINATING***");
			delay (1000);
			System.exit (0);
		    }
		    else if (GasGiant1)
		    {
			level2ID = true;
			String Level2Name;
			grant = grant + 1500;
			c.clear ();
			avatar (375, 275, sex1, skin, hair, male1, female1, other1, heli);
			c.println ("Good job! You got ($1500) for that correct classification of a Gas Giant Planet!");
			c.print ("Your balance is now $");
			c.print (grant, 0, 2);
			c.print (" Go ahead, name the planet: "); //Space here is for grant
			Level2Name = c.readLine ();
			c.clear ();
			c.println ("You have named the Gas Giant Planet \"" + Level2Name + "\"! Now you're ready to go out ");
			c.print ("there and do some more space-searching! Press any key then <Enter> to continue: ");
			c.readString ();
			found = false;

		    }
		    else
		    {
			c.println ("NOPE! The APSE board members belittle your equipment! Minus 3");
			c.println ("dignity points. Also, they docked $100 from your pay! :(");
			grant = grant - 100;
			c.println ("Your balance is now $" + grant + ". Now you go out there and try it again!");
			c.println ("You may want to consult the Neil DeGrasse Tyson database again...");
			c.print ("Press any key and then <Enter> to continue: ");
			c.readString ();
			if (grant < 0)
			{
			    c.clear ();
			    c.drawImage (err, 250, 150, null);
			    c.println ("YOUR COMPANY IS IN DEBT!");
			    delay (10);
			    c.println ("Well " + name + ", you tried to square root a negative number, and this is what happened.");
			    c.println ("You have failed your mission, but feel free to try again!");
			    delay (10);
			    c.println ("***TERMINATING***");
			    delay (1000);
			    System.exit (0);
			}
		    }
		}
		else if ((foundlv2 == 2) && (Planet1))
		{
		    Frame planets = new Frame (); //Quiz for level 2 planet 2 pt. 2

		    JRadioButton Earth = new JRadioButton ("Earth-like");
		    JRadioButton SuperEarth = new JRadioButton ("Super Earth");
		    JRadioButton IceGiant = new JRadioButton ("Ice Giant");
		    JRadioButton GasGiant = new JRadioButton ("Gas Giant");

		    boolean Earth1, SuperEarth1, IceGiant1, GasGiant1, errorPlanets;
		    String messagePlanets = "Which planet type do you think this is " + name + "?";
		    c.println ();
		    c.println (messagePlanets);
		    Object[] paramsPlanets = {Earth, SuperEarth, IceGiant, GasGiant};
		    for (boolean affirm = false ; affirm == false ;)
		    {
			int planetQuiz = JOptionPane.showConfirmDialog (planets, paramsPlanets, messagePlanets, JOptionPane.OK_CANCEL_OPTION);
			int confirm = JOptionPane.showConfirmDialog (
				null,
				"Remember " + name + ", you will lose grant money if you get this wrong! And for your own sake, go back if you chose more than 1 answer, the APSE doesn't like uncertainty!",
				"ARE YOU SURE???!!!",
				JOptionPane.YES_NO_OPTION);
			if (confirm == JOptionPane.YES_OPTION)
			{
			    affirm = true;
			}
			else
			{
			    affirm = false;
			}
		    }

		    Earth1 = Earth.isSelected ();
		    SuperEarth1 = SuperEarth.isSelected ();
		    IceGiant1 = IceGiant.isSelected ();
		    GasGiant1 = GasGiant.isSelected ();
		    errorPlanets = ((Earth1 && SuperEarth1) || (Earth1 && IceGiant1) || (Earth1 && GasGiant1) || (SuperEarth1 && IceGiant1) || (SuperEarth1 && GasGiant1) || (IceGiant1 && GasGiant1));


		    if (errorPlanets)
		    {
			c.clear ();
			c.drawImage (err, 250, 150, null);
			c.println ("ERROR! CHOOSE ONLY ONE OPTION PLEASE!");
			delay (10);
			c.println ("Well " + name + ", you tried to divide by zero and this is what happened,");
			c.println ("You have failed your mission, but feel free to try again!");
			delay (10);
			c.println ("***TERMINATING***");
			delay (1000);
			System.exit (0);
		    }
		    else if (Earth1)
		    {
			level2ID = true;
			String Level2Name;
			grant = grant + 1500;
			c.clear ();
			avatar (375, 275, sex1, skin, hair, male1, female1, other1, heli);
			c.println ("Good job! You got ($1500) for that correct classification of an Earth-Like Planet!");
			c.print ("Your balance is now $");
			c.print (grant, 0, 2);
			c.print (" Go ahead, name the planet: "); //Space here is for grant
			Level2Name = c.readLine ();
			c.clear ();
			c.println ("You have named the Earth-Like Planet \"" + Level2Name + "\"! Now you're ready to go out ");
			c.print ("there and do some more space-searching! Press any key then <Enter> to continue: ");
			c.readString ();
			found = false;

		    }
		    else
		    {
			c.println ("NOPE! The APSE board members belittle your equipment! Minus 3");
			c.println ("dignity points. Also, they docked $100 from your pay! :(");
			grant = grant - 100;
			c.println ("Your balance is now $" + grant + ". Now you go out there and try it again!");
			c.println ("You may want to consult the Neil DeGrasse Tyson database again...");
			c.print ("Press any key and then <Enter> to continue: ");
			c.readString ();
			if (grant < 0)
			{
			    c.clear ();
			    c.drawImage (err, 250, 150, null);
			    c.println ("YOUR COMPANY IS IN DEBT!");
			    delay (10);
			    c.println ("Well " + name + ", you tried to square root a negative number, and this is what happened.");
			    c.println ("You have failed your mission, but feel free to try again!");
			    delay (10);
			    c.println ("***TERMINATING***");
			    delay (1000);
			    System.exit (0);
			}
		    }
		}
		else if ((foundlv2 == 3) && (Others1)) //object 3, black hole
		{
		    Frame others = new Frame (); //Quiz for level 2 object 3 pt. 2

		    JRadioButton blackHole = new JRadioButton ("Black Hole");
		    JRadioButton superNova = new JRadioButton ("Supernova");
		    JRadioButton millenniumFalcon = new JRadioButton ("Millennium Falcon");

		    boolean blackHole1, superNova1, millennium1, errorOthers;
		    String messageOthers = "Which other object type do you think this is " + name + "?";
		    c.println ();
		    c.println (messageOthers);
		    Object[] paramsOthers = {blackHole, superNova, millenniumFalcon};
		    for (boolean affirm = false ; affirm == false ;)
		    {
			int othersQuiz = JOptionPane.showConfirmDialog (others, paramsOthers, messageOthers, JOptionPane.OK_CANCEL_OPTION);
			int confirm = JOptionPane.showConfirmDialog (
				null,
				"Remember " + name + ", you will lose grant money if you get this wrong! And for your own sake, go back if you chose more than 1 answer, the APSE doesn't like uncertainty!",
				"ARE YOU SURE???!!!",
				JOptionPane.YES_NO_OPTION);
			if (confirm == JOptionPane.YES_OPTION)
			{
			    affirm = true;
			}
			else
			{
			    affirm = false;
			}
		    }

		    blackHole1 = blackHole.isSelected ();
		    superNova1 = superNova.isSelected ();
		    millennium1 = millenniumFalcon.isSelected ();
		    errorOthers = ((blackHole1 && superNova1) || (blackHole1 && millennium1) || (superNova1 && millennium1));

		    if (errorOthers)
		    {
			c.clear ();
			c.drawImage (err, 250, 150, null);
			c.println ("ERROR! CHOOSE ONLY ONE OPTION PLEASE!");
			delay (10);
			c.println ("Well " + name + ", you tried to divide by zero and this is what happened,");
			c.println ("You have failed your mission, but feel free to try again!");
			delay (10);
			c.println ("***TERMINATING***");
			delay (1000);
			System.exit (0);
		    }
		    else if (blackHole1)
		    {
			level2ID = true;
			String Level2Name;
			grant = grant + 3000;
			c.clear ();
			avatar (375, 275, sex1, skin, hair, male1, female1, other1, heli);
			c.println ("You found an Easter Egg!");
			c.println ("Good job! You got ($3000) for finding a brand spankin' new BlackHole! Wow!");
			c.print ("Your balance is now $");
			c.print (grant, 0, 2);
			c.print (" Go ahead, name the black hole: "); //Space here is for grant
			Level2Name = c.readLine ();
			c.clear ();
			c.println ("You have named the Black hole \"" + Level2Name + "\"! Now you're ready to go out ");
			c.print ("there and do some more space-searching! Press any key then <Enter> to continue: ");
			c.readString ();
			found = false;
		    }
		    else
		    {
			c.println ("NOPE! The APSE board members give you bananas, implying that");
			c.println ("you're a monkey! Minus 3 dignity points. Also, they docked $100");
			c.println ("from your pay! :( How about you go out there and try it again?");
			grant = grant - 100;
			c.println ("Your balance is now $" + grant + ". Now you go out there and try it again!");
			c.println ("You may want to consult the Neil DeGrasse Tyson database again...");
			c.print ("Press any key and then <Enter> to continue: ");
			c.readString ();
			if (grant < 0)
			{
			    c.clear ();
			    c.drawImage (err, 250, 150, null);
			    c.println ("YOUR COMPANY IS IN DEBT!");
			    delay (10);
			    c.println ("Well " + name + ", you tried to square root a negative number, and this is what happened.");
			    c.println ("You have failed your mission, but feel free to try again!");
			    delay (10);
			    c.println ("***TERMINATING***");
			    delay (1000);
			    System.exit (0);
			}
		    }
		}
		else
		{
		    c.println ("NOPE! The APSE board members give you bananas, implying that");
		    c.println ("you're a monkey! Minus 3 dignity points. Also, they docked $100");
		    c.println ("from your pay! :( How about you go out there and try it again?");
		    grant = grant - 100;
		    c.println ("Your balance is now $" + grant + ". Now you go out there and try it again!");
		    c.println ("You may want to consult the Neil DeGrasse Tyson database again...");
		    c.print ("Press any key and then <Enter> to continue: ");
		    c.readString ();
		    if (grant < 0)
		    {
			c.clear ();
			c.drawImage (err, 250, 150, null);
			c.println ("YOUR COMPANY IS IN DEBT!");
			delay (10);
			c.println ("Well " + name + ", you tried to square root a negative number, and this is what happened.");
			c.println ("You have failed your mission, but feel free to try again!");
			delay (10);
			c.println ("***TERMINATING***");
			delay (1000);
			System.exit (0);
		    }
		} //black hole, object 3
		if (grant < 0)
		{
		    c.clear ();
		    c.drawImage (err, 250, 150, null);
		    c.println ("YOUR COMPANY IS IN DEBT!");
		    delay (10);
		    c.println ("Well " + name + ", you tried to square root a negative number, and this is what happened.");
		    c.println ("You have failed your mission, but feel free to try again!");
		    delay (10);
		    c.println ("***TERMINATING***");
		    delay (1000);
		    System.exit (0);
		}
	    }
	    else
	    {
		c.println ("NOPE! The APSE board members belittle your equipment! Minus 3");
		c.println ("dignity points. Also, they docked $100 from your pay! :(");
		grant = grant - 100;
		c.println ("Your balance is now $" + grant + ". Now you go out there and try it again!");
		c.println ("You may want to consult the Neil DeGrasse Tyson database again...");
		c.print ("Press any key and then <Enter> to continue: ");
		c.readString ();
		if (grant < 0)
		{
		    c.clear ();
		    c.drawImage (err, 250, 150, null);
		    c.println ("YOUR COMPANY IS IN DEBT!");
		    delay (10);
		    c.println ("Well " + name + ", you tried to square root a negative number, and this is what happened.");
		    c.println ("You have failed your mission, but feel free to try again!");
		    delay (10);
		    c.println ("***TERMINATING***");
		    delay (1000);
		    System.exit (0);
		}
	    }
	} //Level 2 classifying process ends

	//{ Level 3 Planet Proximity Detector breakdown + Mechanic time!!!
	c.clear ();
	String mechanic;
	c.println ("Bad news, the Planet Proximity Detection Component of the telescope broke, that means the scanning");
	c.println ("won't give you colour readings when you are near a planet. However, a sketchy mechanic that you");
	c.println ("have never met before offers to fix the PPDC for a small price of $1000. Do you trust him?");
	c.print ("Your balance is $");
	c.println (grant, 0, 2);

	if (grant < 1000)
	{
	    c.println ("Sorry, you don't have enough grant money to fix the PPDC, you'll have to live without it.");
	}
	else
	{
	    for (boolean affirm = false ; affirm == false ;)
	    {
		int ppdc = JOptionPane.showConfirmDialog (
			null,
			"Do you trust the mechanic to fix the PPDC for $1000?",
			"Do you trust him?",
			JOptionPane.YES_NO_OPTION);
		int confirm = JOptionPane.showConfirmDialog (
			null,
			"Remember " + name + ", on one hand, the PPDC is a luxury, but on the other, this is $1000 on the line!",
			"ARE YOU SURE???!!!",
			JOptionPane.YES_NO_OPTION);
		if (confirm == JOptionPane.YES_OPTION)
		{
		    affirm = true;
		    if (ppdc == JOptionPane.YES_OPTION)
		    {
			grant = grant - 1000;
			c.clear ();
			c.print ("Your balance is now $");
			c.println (grant, 0, 2);
			int chance = (int) (Math.random () * 100);
			if (chance > 30)
			{
			    c.println ("The mechanic fixed your planet proximity detector and now you're good to go!");
			    detector = true;
			}
			else
			{
			    c.println ("The mechanic scammed you! You lost $1000 for nothing :(");
			    detector = false;
			}
		    }
		    else
		    {
			c.println ("You decide to continue your mission without the luxury of the PPDC");
		    }
		    c.print ("Press any key and then <Enter> to continue: ");
		    c.readString ();
		}
		else
		{
		    affirm = false;
		}
	    }
	}
	//} Planet Proximity Detector breakdown + Mechanic time!!!

	for (found = false ; found == false ;) //LEVEL 3
	{
	    lev3 = 1;
	    JFrame level3 = new JFrame ("Level 3");
	    level3.setDefaultCloseOperation (JFrame.DISPOSE_ON_CLOSE);

	    NewEarth ex = new NewEarth ();

	    level3.getContentPane ().add (ex);
	    level3.pack ();
	    level3.setResizable (false);
	    level3.setVisible (true);
	    ex.requestFocus ();

	    c.println ("Don't type in any key nor <Enter> until you found a celestial body!");
	    c.readString ();
	} // end of level 3 screen

	//Level 3 classifying process
	for (boolean level3ID = false ; level3ID == false ;)
	{
	    for (boolean exit = false ; exit == false ;) //NEIL DEGRASS TYSON DATABASE
	    {
		avatar (375, 275, sex1, skin, hair, male1, female1, other1, heli);
		String objectFound = "";

		c.clear ();

		c.println ("Welcome to the Neil DeGrasse Tyson celestial object data base,");
		c.println ("Feel free to exit when you think you have made a positive identification");
		c.println ("or choose ONE of the following to find out more:");

		Frame Legend = new Frame ();

		JRadioButton Planet = new JRadioButton ("Planets");
		JRadioButton Star = new JRadioButton ("Stars");
		JRadioButton Others = new JRadioButton ("Other celestial objects");
		JRadioButton Exit = new JRadioButton ("Exit the NDGT database");

		boolean Planet1, Star1, Others1, Exit1, errorLegend;
		String messageLegend = "Which category would you like to learn more about " + name + "?";
		c.println ();
		c.println (messageLegend);
		Object[] paramsLegend = {Planet, Star, Others, Exit};
		int category = JOptionPane.showConfirmDialog (Legend, paramsLegend, messageLegend, JOptionPane.OK_CANCEL_OPTION);

		Planet1 = Planet.isSelected ();
		Star1 = Star.isSelected ();
		Others1 = Others.isSelected ();
		Exit1 = Exit.isSelected ();
		errorLegend = ((Planet1 & Star1) || (Planet1 && Others1) || (Star1 && Others1) || (Planet1 && Exit1) || (Star1 && Exit1) || (Others1 && Exit1));

		if (errorLegend)
		{
		    c.clear ();
		    c.drawImage (err, 250, 150, null);
		    c.println ("ERROR! CHOOSE ONLY ONE OPTION PLEASE!");
		    delay (10);
		    c.println ("Well " + name + ", you tried to divide by zero and this is what happened,");
		    c.println ("You have failed your mission, but feel free to try again!");
		    delay (10);
		    c.println ("***TERMINATING***");
		    delay (1000);
		    System.exit (0);
		}
		else if (Planet1)
		{
		    objectFound = "Planets";
		}
		else if (Star1)
		{
		    objectFound = "Stars";
		}
		else if (Others1)
		{
		    objectFound = "Others";
		}

		else
		{
		    exit = true;
		}

		legend (objectFound);
	    } // end of level 3 database

	    //{<DON'T REMOVE //!  Start of level 3 classification
	    c.println ("Thank you for using the Neil DeGrasse Tyson celestial object data base.");
	    c.println ("Now you must properly identify what that celestial object was.");
	    c.println ("Choose only ONE option please!");
	    c.println ();

	    Frame Level3Object = new Frame (); // Quiz for level 3 pt. 1

	    JRadioButton Planet = new JRadioButton ("A Planet");
	    JRadioButton Star = new JRadioButton ("A Star");
	    JRadioButton Others = new JRadioButton ("Other celestial objects");

	    boolean Planet1, Star1, Others1, errorLevel3;
	    String messageLevel3 = "Which celestial body do you think this is " + name + "?";
	    c.println ();
	    c.println (messageLevel3);
	    Object[] paramsLevel3 = {Planet, Star, Others};
	    for (boolean affirm = false ; affirm == false ;)
	    {
		int category = JOptionPane.showConfirmDialog (Level3Object, paramsLevel3, messageLevel3, JOptionPane.OK_CANCEL_OPTION);
		int confirm = JOptionPane.showConfirmDialog (
			null,
			"Remember " + name + ", you will lose grant money if you get this wrong!",
			"ARE YOU SURE???!!!",
			JOptionPane.YES_NO_OPTION);
		if (confirm == JOptionPane.YES_OPTION)
		{
		    affirm = true;
		}
		else
		{
		    affirm = false;
		}
	    }

	    Planet1 = Planet.isSelected ();
	    Star1 = Star.isSelected ();
	    Others1 = Others.isSelected ();
	    errorLevel3 = ((Planet1 & Star1) || (Planet1 && Others1) || (Star1 && Others1));

	    if (errorLevel3)
	    {
		c.clear ();
		c.drawImage (err, 250, 150, null);
		c.println ("ERROR! CHOOSE ONLY ONE OPTION PLEASE!");
		delay (10);
		c.println ("Well " + name + ", you tried to divide by zero and this is what happened,");
		c.println ("You have failed your mission, but feel free to try again!");
		delay (10);
		c.println ("***TERMINATING***");
		delay (1000);
		System.exit (0);
	    }
	    else if (((foundlv3 == 1) && (Star1)) || ((foundlv3 == 2) && (Star1)) || ((foundlv3 == 3) && (Others1)))
	    {
		if ((foundlv3 == 1) && (Star1))
		{
		    Frame stars = new Frame (); //Quiz for level 3 planet 1 pt. 2

		    JRadioButton mainStar = new JRadioButton ("Main Sequence Star");
		    JRadioButton neutronStar = new JRadioButton ("Neutron Star");

		    boolean mainStar1, neutronStar1, errorPlanets;
		    String messageStar = "Which star type do you think this is " + name + "?";
		    c.println ();
		    c.println (messageStar);
		    Object[] paramsStar = {mainStar, neutronStar};
		    for (boolean affirm = false ; affirm == false ;)
		    {
			int planetQuiz = JOptionPane.showConfirmDialog (stars, paramsStar, messageStar, JOptionPane.OK_CANCEL_OPTION);
			int confirm = JOptionPane.showConfirmDialog (
				null,
				"Remember " + name + ", you will lose grant money if you get this wrong!",
				"ARE YOU SURE???!!!",
				JOptionPane.YES_NO_OPTION);
			if (confirm == JOptionPane.YES_OPTION)
			{
			    affirm = true;
			}
			else
			{
			    affirm = false;
			}
		    }

		    mainStar1 = mainStar.isSelected ();
		    neutronStar1 = neutronStar.isSelected ();
		    errorPlanets = (neutronStar1 && mainStar1);


		    if (errorPlanets)
		    {
			c.clear ();
			c.drawImage (err, 250, 150, null);
			c.println ("ERROR! CHOOSE ONLY ONE OPTION PLEASE!");
			delay (10);
			c.println ("Well " + name + ", you tried to divide by zero and this is what happened,");
			c.println ("You have failed your mission, but feel free to try again!");
			delay (10);
			c.println ("***TERMINATING***");
			delay (1000);
			System.exit (0);
		    }
		    else if (mainStar1)
		    {
			level3ID = true;
			String Level3Name;
			grant = grant + 1500;
			c.clear ();
			avatar (375, 275, sex1, skin, hair, male1, female1, other1, heli);
			c.println ("Good job! You got ($1500) for that correct classification of a Main Sequence Star!");
			c.print ("Your balance is now $");
			c.print (grant, 0, 2);
			c.print (" Go ahead, name the star: "); //Space here is for grant
			Level3Name = c.readLine ();
			c.clear ();
			c.println ("You have named the Main Sequence Star \"" + Level3Name + "\"! Now you're ready to go out ");
			c.print ("there and do some more space-searching! Press any key then <Enter> to continue: ");
			c.readString ();
			found = false;

		    }
		    else
		    {
			c.println ("NOPE! The APSE board members say you don't even know how to use a PCR machine!");
			c.println ("Minus 3 dignity points. Also, they docked $100 from your pay :(");
			grant = grant - 100;
			c.println ("Your balance is now $" + grant + ". Now you go out there and try it again!");
			c.println ("You may want to consult the Neil DeGrasse Tyson database again...");
			c.print ("Press any key and then <Enter> to continue: ");
			c.readString ();
			if (grant < 0)
			{
			    c.clear ();
			    c.drawImage (err, 250, 150, null);
			    c.println ("YOUR COMPANY IS IN DEBT!");
			    delay (10);
			    c.println ("Well " + name + ", you tried to square root a negative number, and this is what happened.");
			    c.println ("You have failed your mission, but feel free to try again!");
			    delay (10);
			    c.println ("***TERMINATING***");
			    delay (1000);
			    System.exit (0);
			}
		    }
		}
		else if ((foundlv3 == 2) && (Star1))
		{
		    Frame stars = new Frame (); //Quiz for level 3 planet 2 pt. 2

		    JRadioButton mainStar = new JRadioButton ("Main Sequence Star");
		    JRadioButton neutronStar = new JRadioButton ("Neutron Star");

		    boolean mainStar1, neutronStar1, errorPlanets;
		    String messageStar = "Which star type do you think this is " + name + "?";
		    c.println ();
		    c.println (messageStar);
		    Object[] paramsStar = {mainStar, neutronStar};
		    for (boolean affirm = false ; affirm == false ;)
		    {
			int planetQuiz = JOptionPane.showConfirmDialog (stars, paramsStar, messageStar, JOptionPane.OK_CANCEL_OPTION);
			int confirm = JOptionPane.showConfirmDialog (
				null,
				"Remember " + name + ", you will lose grant money if you get this wrong!",
				"ARE YOU SURE???!!!",
				JOptionPane.YES_NO_OPTION);
			if (confirm == JOptionPane.YES_OPTION)
			{
			    affirm = true;
			}
			else
			{
			    affirm = false;
			}
		    }

		    mainStar1 = mainStar.isSelected ();
		    neutronStar1 = neutronStar.isSelected ();
		    errorPlanets = (neutronStar1 && mainStar1);

		    if (errorPlanets)
		    {
			c.clear ();
			c.drawImage (err, 250, 150, null);
			c.println ("ERROR! CHOOSE ONLY ONE OPTION PLEASE!");
			delay (10);
			c.println ("Well " + name + ", you tried to divide by zero and this is what happened,");
			c.println ("You have failed your mission, but feel free to try again!");
			delay (10);
			c.println ("***TERMINATING***");
			delay (1000);
			System.exit (0);
		    }
		    else if (neutronStar1)
		    {
			level3ID = true;
			String Level3Name;
			grant = grant + 1500;
			c.clear ();
			avatar (375, 275, sex1, skin, hair, male1, female1, other1, heli);
			c.println ("Good job! You got ($1500) for that correct classification of a Neutron Star!");
			c.print ("Your balance is now $");
			c.print (grant, 0, 2);
			c.print (" Go ahead, name the star: "); //Space here is for grant
			Level3Name = c.readLine ();
			c.clear ();
			c.println ("You have named the Neutron Star \"" + Level3Name + "\"! Now you're ready to go out ");
			c.print ("there and do some more space-searching! Press any key then <Enter> to continue: ");
			c.readString ();
			found = false;

		    }
		    else
		    {
			c.println ("NOPE! The APSE board members call you short! Minus 3 dignity points.");
			c.println ("Also, they docked $100 from your pay! :( ");
			grant = grant - 100;
			c.println ("Your balance is now $" + grant + ". Now you go out there and try it again!");
			c.println ("You may want to consult the Neil DeGrasse Tyson database again...");
			c.print ("Press any key and then <Enter> to continue: ");
			c.readString ();
			if (grant < 0)
			{
			    c.clear ();
			    c.drawImage (err, 250, 150, null);
			    c.println ("YOUR COMPANY IS IN DEBT!");
			    delay (10);
			    c.println ("Well " + name + ", you tried to square root a negative number, and this is what happened.");
			    c.println ("You have failed your mission, but feel free to try again!");
			    delay (10);
			    c.println ("***TERMINATING***");
			    delay (1000);
			    System.exit (0);
			}
		    }
		}
		else if ((foundlv3 == 3) && (Others1)) //object 3, black hole
		{
		    Frame others = new Frame (); //Quiz for level 3 object 3 pt. 2

		    JRadioButton blackHole = new JRadioButton ("Black Hole");
		    JRadioButton superNova = new JRadioButton ("Supernova");
		    JRadioButton millenniumFalcon = new JRadioButton ("Millennium Falcon");

		    boolean blackHole1, superNova1, millennium1, errorOthers;
		    String messageOthers = "Which other object type do you think this is " + name + "?";
		    c.println ();
		    c.println (messageOthers);
		    Object[] paramsOthers = {blackHole, superNova, millenniumFalcon};
		    for (boolean affirm = false ; affirm == false ;)
		    {
			int othersQuiz = JOptionPane.showConfirmDialog (others, paramsOthers, messageOthers, JOptionPane.OK_CANCEL_OPTION);
			int confirm = JOptionPane.showConfirmDialog (
				null,
				"Remember " + name + ", you will lose grant money if you get this wrong!",
				"ARE YOU SURE???!!!",
				JOptionPane.YES_NO_OPTION);
			if (confirm == JOptionPane.YES_OPTION)
			{
			    affirm = true;
			}
			else
			{
			    affirm = false;
			}
		    }

		    blackHole1 = blackHole.isSelected ();
		    superNova1 = superNova.isSelected ();
		    millennium1 = millenniumFalcon.isSelected ();
		    errorOthers = ((blackHole1 && superNova1) || (blackHole1 && millennium1) || (superNova1 && millennium1));

		    if (errorOthers)
		    {
			c.clear ();
			c.drawImage (err, 250, 150, null);
			c.println ("ERROR! CHOOSE ONLY ONE OPTION PLEASE!");
			delay (10);
			c.println ("Well " + name + ", you tried to divide by zero and this is what happened,");
			c.println ("You have failed your mission, but feel free to try again!");
			delay (10);
			c.println ("***TERMINATING***");
			delay (1000);
			System.exit (0);
		    }
		    else if (superNova1)
		    {
			level3ID = true;
			String Level3Name;
			grant = grant + 5000;
			c.clear ();
			avatar (375, 275, sex1, skin, hair, male1, female1, other1, heli);
			c.println ("You found an Easter Egg!");
			c.println ("Good job! You got ($5000) for finding a brand spankin' new Supernova! Wow!");
			c.print ("Your balance is now $");
			c.print (grant, 0, 2);
			c.println (" You are getting very close to the goal! Keep at it!");
			c.print ("Go ahead, name the Supernova: "); //Space here is for grant
			Level3Name = c.readLine ();
			c.clear ();
			c.println ("You have named the Supernova \"" + Level3Name + "\"! Now you're ready to go out ");
			c.print ("there and do some more space-searching! Press any key then <Enter> to continue: ");
			c.readString ();
			found = false;
		    }
		    else
		    {
			c.println ("NOPE! The APSE board members insult your highschool physics teacher!");
			c.println ("Minus 3 dignity points. Also, they docked $100 from your pay! :(");
			grant = grant - 100;
			c.println ("Your balance is now $" + grant + ". Now you go out there and try it again!");
			c.println ("You may want to consult the Neil DeGrasse Tyson database again...");
			c.print ("Press any key and then <Enter> to continue: ");
			c.readString ();
			if (grant < 0)
			{
			    c.clear ();
			    c.drawImage (err, 250, 150, null);
			    c.println ("YOUR COMPANY IS IN DEBT!");
			    delay (10);
			    c.println ("Well " + name + ", you tried to square root a negative number, and this is what happened.");
			    c.println ("You have failed your mission, but feel free to try again!");
			    delay (10);
			    c.println ("***TERMINATING***");
			    delay (1000);
			    System.exit (0);
			}
		    }
		} // supernova, object 3
		else
		{
		    c.println ("NOPE! The APSE board members give you bananas, implying that");
		    c.println ("you're a monkey! Minus 3 dignity points. Also, they docked $100");
		    c.println ("from your pay! :( How about you go out there and try it again?");
		    grant = grant - 100;
		    c.println ("Your balance is now $" + grant + ". Now you go out there and try it again!");
		    c.println ("You may want to consult the Neil DeGrasse Tyson database again...");
		    c.print ("Press any key and then <Enter> to continue: ");
		    c.readString ();
		    if (grant < 0)
		    {
			c.clear ();
			c.drawImage (err, 250, 150, null);
			c.println ("YOUR COMPANY IS IN DEBT!");
			delay (10);
			c.println ("Well " + name + ", you tried to square root a negative number, and this is what happened.");
			c.println ("You have failed your mission, but feel free to try again!");
			delay (10);
			c.println ("***TERMINATING***");
			delay (1000);
			System.exit (0);
		    }
		}
		if (grant < 0)
		{
		    c.clear ();
		    c.drawImage (err, 250, 150, null);
		    c.println ("YOUR COMPANY IS IN DEBT!");
		    delay (10);
		    c.println ("Well " + name + ", you tried to square root a negative number, and this is what happened.");
		    c.println ("You have failed your mission, but feel free to try again!");
		    delay (10);
		    c.println ("***TERMINATING***");
		    delay (1000);
		    System.exit (0);
		}
	    }
	    else
	    {
		c.println ("NOPE! The APSE board members belittle your equipment! Minus 3");
		c.println ("dignity points. Also, they docked $100 from your pay! :(");
		grant = grant - 100;
		c.println ("Your balance is now $" + grant + ". Now you go out there and try it again!");
		c.println ("You may want to consult the Neil DeGrasse Tyson database again...");
		c.print ("Press any key and then <Enter> to continue: ");
		c.readString ();
		if (grant < 0)
		{
		    c.clear ();
		    c.drawImage (err, 250, 150, null);
		    c.println ("YOUR COMPANY IS IN DEBT!");
		    delay (10);
		    c.println ("Well " + name + ", you tried to square root a negative number, and this is what happened.");
		    c.println ("You have failed your mission, but feel free to try again!");
		    delay (10);
		    c.println ("***TERMINATING***");
		    delay (1000);
		    System.exit (0);
		}
	    }
	} //Level 3 classifying process.

	//{ Level 4 Planet Proximity Detector breakdown + Mechanic time!!!
	c.clear ();
	int chanceBreak = (int) (Math.random () * 100 + 1);
	if (chanceBreak > 49)
	{
	    detector = true;
	}
	else
	{
	    detector = false;
	    //String mechanic;
	    c.println ("Bad news, the Planet Proximity Detection Component of the telescope broke again, that means the");
	    c.println ("scanning won't give you colour readings when you are near a planet. However, a sketchy mechanic that");
	    c.println ("you have never met before offers to fix the PPDC for a small price of $1000. Do you trust him?");
	    c.print ("Your balance is $");
	    c.println (grant, 0, 2);

	    if (grant < 1000)
	    {
		c.println ("Sorry, you don't have enough grant money to fix the PPDC, you'll have to live without it.");
	    }
	    else
	    {
		for (boolean affirm = false ; affirm == false ;)
		{
		    int ppdc = JOptionPane.showConfirmDialog (
			    null,
			    "Do you trust the mechanic to fix the PPDC for $1000?",
			    "Do you trust him?",
			    JOptionPane.YES_NO_OPTION);
		    int confirm = JOptionPane.showConfirmDialog (
			    null,
			    "Remember " + name + ", on one hand, the PPDC is a luxury, but on the other, this is $1000 on the line!",
			    "ARE YOU SURE???!!!",
			    JOptionPane.YES_NO_OPTION);
		    if (confirm == JOptionPane.YES_OPTION)
		    {
			affirm = true;
			if (ppdc == JOptionPane.YES_OPTION)
			{
			    grant = grant - 1000;
			    c.clear ();
			    c.print ("Your balance is now $");
			    c.println (grant, 0, 2);
			    int chance = (int) (Math.random () * 100 + 1);
			    if (chance > 25)
			    {
				c.println ("The mechanic fixed your planet proximity detector and now you're good to go!");
				detector = true;
			    }
			    else
			    {
				c.println ("The mechanic scammed you! You lost $1000 for nothing :(");
				detector = false;
			    }
			}
			else
			{
			    c.println ("You decide to continue your mission without the luxury of the PPDC");
			}
			c.print ("Press any key and then <Enter> to continue: ");
			c.readString ();
		    }
		    else
		    {
			affirm = false;
		    }
		}
	    }
	} //} Planet Proximity Detector breakdown + Mechanic time!!!

	for (found = false ; found == false ;) //LEVEL 4
	{
	    lev4 = 1;
	    JFrame level4 = new JFrame ("Level 4");
	    level4.setDefaultCloseOperation (JFrame.DISPOSE_ON_CLOSE);

	    NewEarth ex = new NewEarth ();

	    level4.getContentPane ().add (ex);
	    level4.pack ();
	    level4.setResizable (false);
	    level4.setVisible (true);
	    ex.requestFocus ();

	    c.println ("Don't type in any key nor <Enter> until you found a celestial body!");
	    c.readString ();
	} // end of level 4 screen

	//Level 4 classifying process
	for (boolean level4ID = false ; level4ID == false ;)
	{
	    for (boolean exit = false ; exit == false ;) //NEIL DEGRASS TYSON DATABASE
	    {
		avatar (375, 275, sex1, skin, hair, male1, female1, other1, heli);
		String objectFound = "";

		c.clear ();

		c.println ("Welcome to the Neil DeGrasse Tyson celestial object data base,");
		c.println ("Feel free to exit when you think you have made a positive identification");
		c.println ("or choose ONE of the following to find out more:");

		Frame Legend = new Frame ();

		JRadioButton Planet = new JRadioButton ("Planets");
		JRadioButton Star = new JRadioButton ("Stars");
		JRadioButton Others = new JRadioButton ("Other celestial objects");
		JRadioButton Exit = new JRadioButton ("Exit the NDGT database");

		boolean Planet1, Star1, Others1, Exit1, errorLegend;
		String messageLegend = "Which category would you like to learn more about " + name + "?";
		c.println ();
		c.println (messageLegend);
		Object[] paramsLegend = {Planet, Star, Others, Exit};
		int category = JOptionPane.showConfirmDialog (Legend, paramsLegend, messageLegend, JOptionPane.OK_CANCEL_OPTION);

		Planet1 = Planet.isSelected ();
		Star1 = Star.isSelected ();
		Others1 = Others.isSelected ();
		Exit1 = Exit.isSelected ();
		errorLegend = ((Planet1 & Star1) || (Planet1 && Others1) || (Star1 && Others1) || (Planet1 && Exit1) || (Star1 && Exit1) || (Others1 && Exit1));

		if (errorLegend)
		{
		    c.clear ();
		    c.drawImage (err, 250, 150, null);
		    c.println ("ERROR! CHOOSE ONLY ONE OPTION PLEASE!");
		    delay (10);
		    c.println ("Well " + name + ", you tried to divide by zero and this is what happened,");
		    c.println ("You have failed your mission, but feel free to try again!");
		    delay (10);
		    c.println ("***TERMINATING***");
		    delay (1000);
		    System.exit (0);
		}
		else if (Planet1)
		{
		    objectFound = "Planets";
		}
		else if (Star1)
		{
		    objectFound = "Stars";
		}
		else if (Others1)
		{
		    objectFound = "Others";
		}

		else
		{
		    exit = true;
		}

		legend (objectFound);
	    } // end of level 4 database

	    //{<DON'T REMOVE //!  Start of level 4 classification
	    c.println ("Thank you for using the Neil DeGrasse Tyson celestial object data base.");
	    c.println ("Now you must properly identify what that celestial object was.");
	    c.println ("Choose only ONE option please!");
	    c.println ();

	    Frame Level4Object = new Frame (); // Quiz for level 4 pt. 1

	    JRadioButton Planet = new JRadioButton ("A Planet");
	    JRadioButton Star = new JRadioButton ("A Star");
	    JRadioButton Others = new JRadioButton ("Other celestial objects");

	    boolean Planet1, Star1, Others1, errorLevel4;
	    String messageLevel4 = "Which celestial body do you think this is  " + name + "?";
	    c.println ();
	    c.println (messageLevel4);
	    Object[] paramsLevel4 = {Planet, Star, Others};
	    for (boolean affirm = false ; affirm == false ;)
	    {
		int category = JOptionPane.showConfirmDialog (Level4Object, paramsLevel4, messageLevel4, JOptionPane.OK_CANCEL_OPTION);
		int confirm = JOptionPane.showConfirmDialog (
			null,
			"Remember  " + name + ", you will lose grant money if you get this wrong!",
			"ARE YOU SURE???!!!",
			JOptionPane.YES_NO_OPTION);
		if (confirm == JOptionPane.YES_OPTION)
		{
		    affirm = true;
		}
		else
		{
		    affirm = false;
		}
	    }

	    Planet1 = Planet.isSelected ();
	    Star1 = Star.isSelected ();
	    Others1 = Others.isSelected ();
	    errorLevel4 = ((Planet1 & Star1) || (Planet1 && Others1) || (Star1 && Others1));

	    if (errorLevel4)
	    {
		c.clear ();
		c.drawImage (err, 250, 150, null);
		c.println ("ERROR! CHOOSE ONLY ONE OPTION PLEASE!");
		delay (10);
		c.println ("Well " + name + ", you tried to divide by zero and this is what happened,");
		c.println ("You have failed your mission, but feel free to try again!");
		delay (10);
		c.println ("***TERMINATING***");
		delay (1000);
		System.exit (0);
	    }
	    else if (((foundlv4 == 1) && (Planet1)) || ((foundlv4 == 2) && (Star1)) || ((foundlv4 == 3) && (Others1)))
	    {
		if ((foundlv4 == 1) && (Planet1))
		{
		    Frame planets = new Frame (); //Quiz for level 4 planet 1 pt. 2

		    JRadioButton Earth = new JRadioButton ("Earth-like");
		    JRadioButton SuperEarth = new JRadioButton ("Super Earth");
		    JRadioButton IceGiant = new JRadioButton ("Ice Giant");
		    JRadioButton GasGiant = new JRadioButton ("Gas Giant");

		    boolean Earth1, SuperEarth1, IceGiant1, GasGiant1, errorPlanets;
		    String messagePlanets = "Which planet type do you think this is?";
		    c.println ();
		    c.println (messagePlanets);
		    Object[] paramsPlanets = {Earth, SuperEarth, IceGiant, GasGiant};
		    for (boolean affirm = false ; affirm == false ;)
		    {
			int planetQuiz = JOptionPane.showConfirmDialog (planets, paramsPlanets, messagePlanets, JOptionPane.OK_CANCEL_OPTION);
			int confirm = JOptionPane.showConfirmDialog (
				null,
				"Remember " + name + ", you will lose grant money if you get this wrong!",
				"ARE YOU SURE???!!!",
				JOptionPane.YES_NO_OPTION);
			if (confirm == JOptionPane.YES_OPTION)
			{
			    affirm = true;
			}
			else
			{
			    affirm = false;
			}
		    }

		    Earth1 = Earth.isSelected ();
		    SuperEarth1 = SuperEarth.isSelected ();
		    IceGiant1 = IceGiant.isSelected ();
		    GasGiant1 = GasGiant.isSelected ();
		    errorPlanets = ((Earth1 && SuperEarth1) || (Earth1 && IceGiant1) || (Earth1 && GasGiant1) || (SuperEarth1 && IceGiant1) || (SuperEarth1 && GasGiant1) || (IceGiant1 && GasGiant1));


		    if (errorPlanets)
		    {
			c.clear ();
			c.drawImage (err, 250, 150, null);
			c.println ("ERROR! CHOOSE ONLY ONE OPTION PLEASE!");
			delay (10);
			c.println ("Well " + name + ", you tried to divide by zero and this is what happened,");
			c.println ("You have failed your mission, but feel free to try again!");
			delay (10);
			c.println ("***TERMINATING***");
			delay (1000);
			System.exit (0);
		    }
		    else if (GasGiant1)
		    {
			level4ID = true;
			String Level4Name;
			grant = grant + 1500;
			c.clear ();
			avatar (375, 275, sex1, skin, hair, male1, female1, other1, heli);
			c.println ("Good job! You got ($1500) for that correct classification of a Gas Giant Planet!");
			c.print ("Your balance is now $");
			c.print (grant, 0, 2);
			c.print (" Go ahead, name the planet: "); //Space here is for grant
			Level4Name = c.readLine ();
			if (grant >= 10000)
			{
			    c.clear ();
			    avatar (375, 275, sex1, skin, hair, male1, female1, other1, heli);
			    c.println ("Hey, " + name + "! You've finally raised enough funds to rent the James");
			    c.println ("Webb VII space telescope and proceed with your search for a new habitable planet!");
			    c.println ("Congratulations: YOU WIN THE GAME!!!");
			    c.println ("Take a moment to bask in the glory, and when you're done,");
			    c.print ("press any key and <Enter> to CLOSE the game. :) ");
			    c.readString ();
			    System.exit (0);

			}
			else
			{
			    c.clear ();
			    c.println ("You have named the Gas Giant Planet \"" + Level4Name + "\"! Now you're ready to go out ");
			    c.print ("there and do some more space-searching! Press any key then <Enter> to continue: ");
			    c.readString ();
			    found = false;
			}
		    }
		    else
		    {
			c.println ("NOPE! The APSE board members claim that you can't spell DNA!");
			c.println ("Minus 3 dignity points. Also, they docked $100 from your pay! :(");
			grant = grant - 100;
			c.println ("Your balance is now $" + grant + ". Now you go out there and try it again!");
			c.println ("You may want to consult the Neil DeGrasse Tyson database again...");
			c.print ("Press any key and then <Enter> to continue: ");
			c.readString ();
			if (grant < 0)
			{
			    c.clear ();
			    c.drawImage (err, 250, 150, null);
			    c.println ("YOUR COMPANY IS IN DEBT!");
			    delay (10);
			    c.println ("Well " + name + ", you tried to square root a negative number, and this is what happened.");
			    c.println ("You have failed your mission, but feel free to try again!");
			    delay (10);
			    c.println ("***TERMINATING***");
			    delay (1000);
			    System.exit (0);
			}
		    }
		}
		else if ((foundlv4 == 2) && (Star1))
		{
		    Frame stars = new Frame (); //Quiz for level 4 star 2 pt. 2

		    JRadioButton mainStar = new JRadioButton ("Main Sequence Star");
		    JRadioButton neutronStar = new JRadioButton ("Neutron Star");

		    boolean mainStar1, neutronStar1, errorPlanets;
		    String messageStar = "Which star type do you think this is " + name + "?";
		    c.println ();
		    c.println (messageStar);
		    Object[] paramsStar = {mainStar, neutronStar};
		    for (boolean affirm = false ; affirm == false ;)
		    {
			int planetQuiz = JOptionPane.showConfirmDialog (stars, paramsStar, messageStar, JOptionPane.OK_CANCEL_OPTION);
			int confirm = JOptionPane.showConfirmDialog (
				null,
				"Remember " + name + ", you will lose grant money if you get this wrong!",
				"ARE YOU SURE???!!!",
				JOptionPane.YES_NO_OPTION);
			if (confirm == JOptionPane.YES_OPTION)
			{
			    affirm = true;
			}
			else
			{
			    affirm = false;
			}
		    }

		    mainStar1 = mainStar.isSelected ();
		    neutronStar1 = neutronStar.isSelected ();
		    errorPlanets = (neutronStar1 && mainStar1);

		    if (errorPlanets)
		    {
			c.clear ();
			c.drawImage (err, 250, 150, null);
			c.println ("ERROR! CHOOSE ONLY ONE OPTION PLEASE!");
			delay (10);
			c.println ("Well " + name + ", you tried to divide by zero and this is what happened,");
			c.println ("You have failed your mission, but feel free to try again!");
			delay (10);
			c.println ("***TERMINATING***");
			delay (1000);
			System.exit (0);
		    }
		    else if (mainStar1)
		    {
			level4ID = true;
			String Level4Name;
			grant = grant + 1500;
			c.clear ();
			avatar (375, 275, sex1, skin, hair, male1, female1, other1, heli);
			c.println ("Good job! You got ($1500) for that correct classification of a Main Sequence Star!");
			c.print ("Your balance is now $");
			c.print (grant, 0, 2);
			c.print (" Go ahead, name the star: "); //Space here is for grant
			Level4Name = c.readLine ();
			if (grant >= 10000)
			{
			    c.clear ();
			    avatar (375, 275, sex1, skin, hair, male1, female1, other1, heli);
			    c.println ("Hey, " + name + "! You've finally raised enough funds to rent the James");
			    c.println ("Webb VII space telescope and proceed with your search for a new habitable planet!");
			    c.println ("Congratulations: YOU WIN THE GAME!!!");
			    c.println ("Take a moment to bask in the glory, and when you're done,");
			    c.print ("press any key and <Enter> to CLOSE the game. :) ");
			    c.readString ();
			    System.exit (0);

			}
			else
			{
			    c.clear ();
			    c.println ("You have named the Gas Giant Planet \"" + Level4Name + "\"! Now you're ready to go out ");
			    c.print ("there and do some more space-searching! Press any key then <Enter> to continue: ");
			    c.readString ();
			    found = false;
			}

		    }
		    else
		    {
			c.println ("NOPE! The APSE board members insult your mother (AGAIN)!");
			c.println ("Minus 3 dignity points. Also, they docked $100 from your pay! :(");
			grant = grant - 100;
			c.println ("Your balance is now $" + grant + ". Now you go out there and try it again!");
			c.println ("You may want to consult the Neil DeGrasse Tyson database again...");
			c.print ("Press any key and then <Enter> to continue: ");
			c.readString ();
			if (grant < 0)
			{
			    c.clear ();
			    c.drawImage (err, 250, 150, null);
			    c.println ("YOUR COMPANY IS IN DEBT!");
			    delay (10);
			    c.println ("Well " + name + ", you tried to square root a negative number, and this is what happened.");
			    c.println ("You have failed your mission, but feel free to try again!");
			    delay (10);
			    c.println ("***TERMINATING***");
			    delay (1000);
			    System.exit (0);
			}
		    }
		}
		else if ((foundlv4 == 3) && (Others1)) //object 3, millennium falcon
		{
		    Frame others = new Frame (); //Quiz for level 4 object 3 pt. 2

		    JRadioButton blackHole = new JRadioButton ("Black Hole");
		    JRadioButton superNova = new JRadioButton ("Supernova");
		    JRadioButton millenniumFalcon = new JRadioButton ("Millennium Falcon");

		    boolean blackHole1, superNova1, millennium1, errorOthers;
		    String messageOthers = "Which other object type do you think this is " + name + "?";
		    c.println ();
		    c.println (messageOthers);
		    Object[] paramsOthers = {blackHole, superNova, millenniumFalcon};
		    for (boolean affirm = false ; affirm == false ;)
		    {
			int othersQuiz = JOptionPane.showConfirmDialog (others, paramsOthers, messageOthers, JOptionPane.OK_CANCEL_OPTION);
			int confirm = JOptionPane.showConfirmDialog (
				null,
				"Remember " + name + ", you will lose grant money if you get this wrong!",
				"ARE YOU SURE???!!!",
				JOptionPane.YES_NO_OPTION);
			if (confirm == JOptionPane.YES_OPTION)
			{
			    affirm = true;
			}
			else
			{
			    affirm = false;
			}
		    }

		    blackHole1 = blackHole.isSelected ();
		    superNova1 = superNova.isSelected ();
		    millennium1 = millenniumFalcon.isSelected ();
		    errorOthers = ((blackHole1 && superNova1) || (blackHole1 && millennium1) || (superNova1 && millennium1));

		    if (errorOthers)
		    {
			c.clear ();
			c.drawImage (err, 250, 150, null);
			c.println ("ERROR! CHOOSE ONLY ONE OPTION PLEASE!");
			delay (10);
			c.println ("Well " + name + ", you tried to divide by zero and this is what happened,");
			c.println ("You have failed your mission, but feel free to try again!");
			delay (10);
			c.println ("***TERMINATING***");
			delay (1000);
			System.exit (0);
		    }
		    else if (millennium1)
		    {
			level4ID = true;
			String Level4Name;
			grant = grant + 10000;
			c.clear ();
			avatar (375, 275, sex1, skin, hair, male1, female1, other1, heli);
			c.println ("You found an Easter Egg!");
			c.println ("You found Han Solo and his crew! Han Solo rewards you for giving him a planet to stay in");
			c.println ("for a while by giving you 1 million Mos Eisley credits! Unfortunetly, Mos Eisley credits");
			c.print ("are worth nothing on Earth... Press any key to continue then <Enter>: ");
			c.readString ();

			c.clear ();
			avatar (375, 275, sex1, skin, hair, male1, female1, other1, heli);
			c.println ("Good job! You got ($10000) for finding the Millennium Falcon! Your ");
			c.println ("ground-breaking discovery baffles scientists all around the globe...");
			c.println ("and now they expect you to prove the existence of midi-chlorians!");
			c.print ("Your balance is now $");
			c.print (grant, 0, 2);
			c.print (" Go ahead, rename the Millenium Falcon: "); //Space here is for grant
			Level4Name = c.readLine ();
			if (grant >= 10000)
			{
			    c.clear ();
			    avatar (375, 275, sex1, skin, hair, male1, female1, other1, heli);
			    c.println ("Hey, " + name + "! You've finally raised enough funds to rent the James");
			    c.println ("Webb VII space telescope and proceed with your search for a new habitable planet!");
			    c.println ("Congratulations: YOU WIN THE GAME!!!");
			    c.println ("Take a moment to bask in the glory, and when you're done,");
			    c.print ("press any key and <Enter> to CLOSE the game. :) ");
			    c.readString ();
			    System.exit (0);

			}
			else
			{
			    c.clear ();
			    c.println ("You have named the Gas Giant Planet \"" + Level4Name + "\"! Now you're ready to go out ");
			    c.print ("there and do some more space-searching! Press any key then <Enter> to continue: ");
			    c.readString ();
			    found = false;
			}
		    }
		    else
		    {
			c.println ("NOPE! The APSE board members insult your highschool physics teacher!");
			c.println ("Minus 3 dignity points. Also, they docked $100 from your pay! :(");
			grant = grant - 100;
			c.println ("Your balance is now $" + grant + ". Now you go out there and try it again!");
			c.println ("You may want to consult the Neil DeGrasse Tyson database again...");
			c.print ("Press any key and then <Enter> to continue: ");
			c.readString ();
			if (grant < 0)
			{
			    c.clear ();
			    c.drawImage (err, 250, 150, null);
			    c.println ("YOUR COMPANY IS IN DEBT!");
			    delay (10);
			    c.println ("Well " + name + ", you tried to square root a negative number, and this is what happened.");
			    c.println ("You have failed your mission, but feel free to try again!");
			    delay (10);
			    c.println ("***TERMINATING***");
			    delay (1000);
			    System.exit (0);
			}
		    }
		} // supernova, object 3
		else
		{
		    c.println ("NOPE! The APSE board members give you bananas, implying that");
		    c.println ("you're a monkey! Minus 3 dignity points. Also, they docked $100");
		    c.println ("from your pay! :( How about you go out there and try it again?");
		    grant = grant - 100;
		    c.println ("Your balance is now $" + grant + ". Now you go out there and try it again!");
		    c.println ("You may want to consult the Neil DeGrasse Tyson database again...");
		    c.print ("Press any key and then <Enter> to continue: ");
		    c.readString ();
		    if (grant < 0)
		    {
			c.clear ();
			c.drawImage (err, 250, 150, null);
			c.println ("YOUR COMPANY IS IN DEBT!");
			delay (10);
			c.println ("Well " + name + ", you tried to square root a negative number, and this is what happened.");
			c.println ("You have failed your mission, but feel free to try again!");
			delay (10);
			c.println ("***TERMINATING***");
			delay (1000);
			System.exit (0);
		    }
		}
		if (grant < 0)
		{
		    c.clear ();
		    c.drawImage (err, 250, 150, null);
		    c.println ("YOUR COMPANY IS IN DEBT!");
		    delay (10);
		    c.println ("Well " + name + ", you tried to square root a negative number, and this is what happened.");
		    c.println ("You have failed your mission, but feel free to try again!");
		    delay (10);
		    c.println ("***TERMINATING***");
		    delay (1000);
		    System.exit (0);
		}
	    }
	    else
	    {
		c.println ("NOPE! The APSE board members belittle your equipment! Minus 3");
		c.println ("dignity points. Also, they docked $100 from your pay! :(");
		grant = grant - 100;
		c.println ("Your balance is now $" + grant + ". Now you go out there and try it again!");
		c.println ("You may want to consult the Neil DeGrasse Tyson database again...");
		c.print ("Press any key and then <Enter> to continue: ");
		c.readString ();
		if (grant < 0)
		{
		    c.clear ();
		    c.drawImage (err, 250, 150, null);
		    c.println ("YOUR COMPANY IS IN DEBT!");
		    delay (10);
		    c.println ("Well " + name + ", you tried to square root a negative number, and this is what happened.");
		    c.println ("You have failed your mission, but feel free to try again!");
		    delay (10);
		    c.println ("***TERMINATING***");
		    delay (1000);
		    System.exit (0);
		}
	    }
	} //Level 4 classifying process

	//{ Level 5 Planet Proximity Detector breakdown + Mechanic time!!!
	for (int levelNum = 5 ; levelNum < 999999 ; levelNum++)
	{
	    c.clear ();
	    chanceBreak = (int) (Math.random () * 100 + 1);
	    if (chanceBreak > (50 - levelNum))
	    {
		detector = true;
	    }
	    else
	    {
		detector = false;
		//String mechanic;
		c.println ("Bad news, the Planet Proximity Detection Component of the telescope broke again, that means the");
		c.println ("scanning won't give you colour readings when you are near a planet. However, a sketchy mechanic that");
		c.println ("you have never met before offers to fix the PPDC for a small price of $1000. Do you trust him?");
		c.print ("Your balance is $");
		c.println (grant, 0, 2);
		if (grant < 1000)
		{
		    c.println ("Sorry, you don't have enough grant money to fix the PPDC, you'll have to live without it.");
		}
		else
		{
		    for (boolean affirm = false ; affirm == false ;)
		    {
			int ppdc = JOptionPane.showConfirmDialog (
				null,
				"Do you trust the mechanic to fix the PPDC for $1000?",
				"Do you trust him?",
				JOptionPane.YES_NO_OPTION);
			int confirm = JOptionPane.showConfirmDialog (
				null,
				"Remember " + name + ", on one hand, the PPDC is a luxury, but on the other, this is $1000 on the line!",
				"ARE YOU SURE???!!!",
				JOptionPane.YES_NO_OPTION);
			if (confirm == JOptionPane.YES_OPTION)
			{
			    affirm = true;
			    if (ppdc == JOptionPane.YES_OPTION)
			    {
				grant = grant - 1000;
				c.clear ();
				c.print ("Your balance is now $");
				c.println (grant, 0, 2);
				int chance = (int) (Math.random () * 100 + 1);
				if (chance > 25 - levelNum)
				{
				    c.println ("The mechanic fixed your planet proximity detector and now you're good to go!");
				    detector = true;
				}
				else
				{
				    c.println ("The mechanic scammed you! You lost $1000 for nothing :(");
				    detector = false;
				}
			    }
			    else
			    {
				c.println ("You decide to continue your mission without the luxury of the PPDC");
			    }
			    c.print ("Press any key and then <Enter> to continue: ");
			    c.readString ();
			}
			else
			{
			    affirm = false;
			}
		    }
		}
	    } //} level 5 Planet Proximity Detector breakdown + Mechanic time!!!

	    for (found = false ; found == false ;) //LEVEL 5
	    {
		lev5 = 1;
		JFrame level5 = new JFrame ("Level " + levelNum);
		level5.setDefaultCloseOperation (JFrame.DISPOSE_ON_CLOSE);

		NewEarth ex = new NewEarth ();

		level5.getContentPane ().add (ex);
		level5.pack ();
		level5.setResizable (false);
		level5.setVisible (true);
		ex.requestFocus ();

		c.println ("Don't type in any key nor <Enter> until you found a celestial body!");
		c.readString ();
	    } // end of level 5 screen

	    //Level 5 classifying process
	    for (boolean level5ID = false ; level5ID == false ;)
	    {
		for (boolean exit = false ; exit == false ;) //NEIL DEGRASS TYSON DATABASE
		{
		    avatar (375, 275, sex1, skin, hair, male1, female1, other1, heli);
		    String objectFound = "";

		    c.clear ();

		    c.println ("Welcome to the Neil DeGrasse Tyson celestial object data base,");
		    c.println ("Feel free to exit when you think you have made a positive identification");
		    c.println ("or choose ONE of the following to find out more:");

		    Frame Legend = new Frame ();

		    JRadioButton Planet = new JRadioButton ("Planets");
		    JRadioButton Star = new JRadioButton ("Stars");
		    JRadioButton Others = new JRadioButton ("Other celestial objects");
		    JRadioButton Exit = new JRadioButton ("Exit the NDGT database");

		    boolean Planet1, Star1, Others1, Exit1, errorLegend;
		    String messageLegend = "Which category would you like to learn more about " + name + "?";
		    c.println ();
		    c.println (messageLegend);
		    Object[] paramsLegend = {Planet, Star, Others, Exit};
		    int category = JOptionPane.showConfirmDialog (Legend, paramsLegend, messageLegend, JOptionPane.OK_CANCEL_OPTION);

		    Planet1 = Planet.isSelected ();
		    Star1 = Star.isSelected ();
		    Others1 = Others.isSelected ();
		    Exit1 = Exit.isSelected ();
		    errorLegend = ((Planet1 & Star1) || (Planet1 && Others1) || (Star1 && Others1) || (Planet1 && Exit1) || (Star1 && Exit1) || (Others1 && Exit1));

		    if (errorLegend)
		    {
			c.clear ();
			c.drawImage (err, 250, 150, null);
			c.println ("ERROR! CHOOSE ONLY ONE OPTION PLEASE!");
			delay (10);
			c.println ("Well " + name + ", you tried to divide by zero and this is what happened,");
			c.println ("You have failed your mission, but feel free to try again!");
			delay (10);
			c.println ("***TERMINATING***");
			delay (1000);
			System.exit (0);
		    }
		    else if (Planet1)
		    {
			objectFound = "Planets";
		    }
		    else if (Star1)
		    {
			objectFound = "Stars";
		    }
		    else if (Others1)
		    {
			objectFound = "Others";
		    }

		    else
		    {
			exit = true;
		    }

		    legend (objectFound);
		} // end of level 5 database

		//{<DON'T REMOVE //!  Start of level 5 classification
		c.println ("Thank you for using the Neil DeGrasse Tyson celestial object data base.");
		c.println ("Now you must properly identify what that celestial object was.");
		c.println ("Choose only ONE option please!");
		c.println ();

		Frame Level5Object = new Frame (); // Quiz for level 5 pt. 1

		JRadioButton Planet = new JRadioButton ("A Planet");
		JRadioButton Star = new JRadioButton ("A Star");
		JRadioButton Others = new JRadioButton ("Other celestial objects");

		boolean Planet1, Star1, Others1, errorLevel5;
		String messageLevel5 = "Which celestial body do you think this is  " + name + "?";
		c.println ();
		c.println (messageLevel5);
		Object[] paramsLevel5 = {Planet, Star, Others};
		for (boolean affirm = false ; affirm == false ;)
		{
		    int category = JOptionPane.showConfirmDialog (Level5Object, paramsLevel5, messageLevel5, JOptionPane.OK_CANCEL_OPTION);
		    int confirm = JOptionPane.showConfirmDialog (
			    null,
			    "Remember  " + name + ", you will lose grant money if you get this wrong!",
			    "ARE YOU SURE???!!!",
			    JOptionPane.YES_NO_OPTION);
		    if (confirm == JOptionPane.YES_OPTION)
		    {
			affirm = true;
		    }
		    else
		    {
			affirm = false;
		    }
		}

		Planet1 = Planet.isSelected ();
		Star1 = Star.isSelected ();
		Others1 = Others.isSelected ();
		errorLevel5 = ((Planet1 & Star1) || (Planet1 && Others1) || (Star1 && Others1));

		if (errorLevel5)
		{
		    c.clear ();
		    c.drawImage (err, 250, 150, null);
		    c.println ("ERROR! CHOOSE ONLY ONE OPTION PLEASE!");
		    delay (10);
		    c.println ("Well " + name + ", you tried to divide by zero and this is what happened,");
		    c.println ("You have failed your mission, but feel free to try again!");
		    delay (10);
		    c.println ("***TERMINATING***");
		    delay (1000);
		    System.exit (0);
		}
		else if (((foundlv5 >= 0) && (foundlv5 <= 4) && (Planet1)) || ((foundlv5 >= 5) && (foundlv5 <= 6) && (Star1)) || ((foundlv5 >= 7) && (foundlv5 <= 9) && (Others1)))
		{
		    if ((foundlv5 >= 0) && (foundlv5 <= 4) && (Planet1))
		    {
			Frame planets = new Frame (); //Quiz for level 5 planet 1 pt. 2

			JRadioButton Earth = new JRadioButton ("Earth-like");
			JRadioButton SuperEarth = new JRadioButton ("Super Earth");
			JRadioButton IceGiant = new JRadioButton ("Ice Giant");
			JRadioButton GasGiant = new JRadioButton ("Gas Giant");

			boolean Earth1, SuperEarth1, IceGiant1, GasGiant1, errorPlanets;
			String messagePlanets = "Which planet type do you think this is?";
			c.println ();
			c.println (messagePlanets);
			Object[] paramsPlanets = {Earth, SuperEarth, IceGiant, GasGiant};
			for (boolean affirm = false ; affirm == false ;)
			{
			    int planetQuiz = JOptionPane.showConfirmDialog (planets, paramsPlanets, messagePlanets, JOptionPane.OK_CANCEL_OPTION);
			    int confirm = JOptionPane.showConfirmDialog (
				    null,
				    "Remember " + name + ", you will lose grant money if you get this wrong!",
				    "ARE YOU SURE???!!!",
				    JOptionPane.YES_NO_OPTION);
			    if (confirm == JOptionPane.YES_OPTION)
			    {
				affirm = true;
			    }
			    else
			    {
				affirm = false;
			    }
			}

			Earth1 = Earth.isSelected ();
			SuperEarth1 = SuperEarth.isSelected ();
			IceGiant1 = IceGiant.isSelected ();
			GasGiant1 = GasGiant.isSelected ();
			errorPlanets = ((Earth1 && SuperEarth1) || (Earth1 && IceGiant1) || (Earth1 && GasGiant1) || (SuperEarth1 && IceGiant1) || (SuperEarth1 && GasGiant1) || (IceGiant1 && GasGiant1));


			if (errorPlanets)
			{
			    c.clear ();
			    c.drawImage (err, 250, 150, null);
			    c.println ("ERROR! CHOOSE ONLY ONE OPTION PLEASE!");
			    delay (10);
			    c.println ("Well " + name + ", you tried to divide by zero and this is what happened,");
			    c.println ("You have failed your mission, but feel free to try again!");
			    delay (10);
			    c.println ("***TERMINATING***");
			    delay (1000);
			    System.exit (0);
			}
			else
			{
			    switch (foundlv5)
			    {
				case 1: //Earth-like
				    if (Earth1)
				    {
					level5ID = true;
					String Level5Name;
					grant = grant + 2000;
					c.clear ();
					avatar (375, 275, sex1, skin, hair, male1, female1, other1, heli);
					c.println ("Good job! You got ($2000) for that correct classification of a Earth-like Planet!");
					c.print ("Your balance is now $");
					c.print (grant, 0, 2);
					c.print (" Go ahead, name the planet: "); //Space here is for grant
					Level5Name = c.readLine ();
					if (grant >= 10000)
					{
					    c.clear ();
					    avatar (375, 275, sex1, skin, hair, male1, female1, other1, heli);
					    c.println ("Hey, " + name + "! You've finally raised enough funds to rent the James");
					    c.println ("Webb VII space telescope and proceed with your search for a new habitable planet!");
					    c.println ("Congratulations: YOU WIN THE GAME!!!");
					    c.println ("Take a moment to bask in the glory, and when you're done,");
					    c.print ("press any key and <Enter> to CLOSE the game. :) ");
					    c.readString ();
					    System.exit (0);

					}
					else
					{
					    c.clear ();
					    c.println ("You have named the Earth-like Planet \"" + Level5Name + "\"! Now you're ready to go out ");
					    c.print ("there and do some more space-searching! Press any key then <Enter> to continue: ");
					    c.readString ();
					    found = false;
					}
				    }
				    else
				    {
					c.println ("NOPE! The APSE board members claim that you can't spell DNA!");
					c.println ("Minus 3 dignity points. Also, they docked $100 from your pay! :(");
					grant = grant - 100;
					c.println ("Your balance is now $" + grant + ". Now you go out there and try it again!");
					c.println ("You may want to consult the Neil DeGrasse Tyson database again...");
					c.print ("Press any key and then <Enter> to continue: ");
					c.readString ();
					if (grant < 0)
					{
					    c.clear ();
					    c.drawImage (err, 250, 150, null);
					    c.println ("YOUR COMPANY IS IN DEBT!");
					    delay (10);
					    c.println ("Well " + name + ", you tried to square root a negative number, and this is what happened.");
					    c.println ("You have failed your mission, but feel free to try again!");
					    delay (10);
					    c.println ("***TERMINATING***");
					    delay (1000);
					    System.exit (0);
					}
				    }
				    break;
				case 2: //Super Earth
				    if (SuperEarth1)
				    {
					level5ID = true;
					String Level5Name;
					grant = grant + 2000;
					c.clear ();
					avatar (375, 275, sex1, skin, hair, male1, female1, other1, heli);
					c.println ("Good job! You got ($2000) for that correct classification of a Super Earth Planet!");
					c.print ("Your balance is now $");
					c.print (grant, 0, 2);
					c.print (" Go ahead, name the planet: "); //Space here is for grant
					Level5Name = c.readLine ();
					if (grant >= 10000)
					{
					    c.clear ();
					    avatar (375, 275, sex1, skin, hair, male1, female1, other1, heli);
					    c.println ("Hey, " + name + "! You've finally raised enough funds to rent the James");
					    c.println ("Webb VII space telescope and proceed with your search for a new habitable planet!");
					    c.println ("Congratulations: YOU WIN THE GAME!!!");
					    c.println ("Take a moment to bask in the glory, and when you're done,");
					    c.print ("press any key and <Enter> to CLOSE the game. :) ");
					    c.readString ();
					    System.exit (0);

					}
					else
					{
					    c.clear ();
					    c.println ("You have named the Super Earth Planet \"" + Level5Name + "\"! Now you're ready to go out ");
					    c.print ("there and do some more space-searching! Press any key then <Enter> to continue: ");
					    c.readString ();
					    found = false;
					}
				    }
				    else
				    {
					c.println ("NOPE! The APSE board members claim that you can't spell DNA!");
					c.println ("Minus 3 dignity points. Also, they docked $100 from your pay! :(");
					grant = grant - 100;
					c.println ("Your balance is now $" + grant + ". Now you go out there and try it again!");
					c.println ("You may want to consult the Neil DeGrasse Tyson database again...");
					c.print ("Press any key and then <Enter> to continue: ");
					c.readString ();
					if (grant < 0)
					{
					    c.clear ();
					    c.drawImage (err, 250, 150, null);
					    c.println ("YOUR COMPANY IS IN DEBT!");
					    delay (10);
					    c.println ("Well " + name + ", you tried to square root a negative number, and this is what happened.");
					    c.println ("You have failed your mission, but feel free to try again!");
					    delay (10);
					    c.println ("***TERMINATING***");
					    delay (1000);
					    System.exit (0);
					}
				    }
				    break;
				case 3: //Ice Giant
				    if (IceGiant1)
				    {
					level5ID = true;
					String Level5Name;
					grant = grant + 2000;
					c.clear ();
					avatar (375, 275, sex1, skin, hair, male1, female1, other1, heli);
					c.println ("Good job! You got ($2000) for that correct classification of a Ice Giant Planet!");
					c.print ("Your balance is now $");
					c.print (grant, 0, 2);
					c.print (" Go ahead, name the planet: "); //Space here is for grant
					Level5Name = c.readLine ();
					if (grant >= 10000)
					{
					    c.clear ();
					    avatar (375, 275, sex1, skin, hair, male1, female1, other1, heli);
					    c.println ("Hey, " + name + "! You've finally raised enough funds to rent the James");
					    c.println ("Webb VII space telescope and proceed with your search for a new habitable planet!");
					    c.println ("Congratulations: YOU WIN THE GAME!!!");
					    c.println ("Take a moment to bask in the glory, and when you're done,");
					    c.print ("press any key and <Enter> to CLOSE the game. :) ");
					    c.readString ();
					    System.exit (0);

					}
					else
					{
					    c.clear ();
					    c.println ("You have named the Ice Giant Planet \"" + Level5Name + "\"! Now you're ready to go out ");
					    c.print ("there and do some more space-searching! Press any key then <Enter> to continue: ");
					    c.readString ();
					    found = false;
					}
				    }
				    else
				    {
					c.println ("NOPE! The APSE board members claim that you can't spell DNA!");
					c.println ("Minus 3 dignity points. Also, they docked $100 from your pay! :(");
					grant = grant - 100;
					c.println ("Your balance is now $" + grant + ". Now you go out there and try it again!");
					c.println ("You may want to consult the Neil DeGrasse Tyson database again...");
					c.print ("Press any key and then <Enter> to continue: ");
					c.readString ();
					if (grant < 0)
					{
					    c.clear ();
					    c.drawImage (err, 250, 150, null);
					    c.println ("YOUR COMPANY IS IN DEBT!");
					    delay (10);
					    c.println ("Well " + name + ", you tried to square root a negative number, and this is what happened.");
					    c.println ("You have failed your mission, but feel free to try again!");
					    delay (10);
					    c.println ("***TERMINATING***");
					    delay (1000);
					    System.exit (0);
					}
				    }
				    break;
				case 4: //Gas Giant
				    if (GasGiant1)
				    {
					level5ID = true;
					String Level5Name;
					grant = grant + 2000;
					c.clear ();
					avatar (375, 275, sex1, skin, hair, male1, female1, other1, heli);
					c.println ("Good job! You got ($2000) for that correct classification of a Gas Giant Planet!");
					c.print ("Your balance is now $");
					c.print (grant, 0, 2);
					c.print (" Go ahead, name the planet: "); //Space here is for grant
					Level5Name = c.readLine ();
					if (grant >= 10000)
					{
					    c.clear ();
					    avatar (375, 275, sex1, skin, hair, male1, female1, other1, heli);
					    c.println ("Hey, " + name + "! You've finally raised enough funds to rent the James");
					    c.println ("Webb VII space telescope and proceed with your search for a new habitable planet!");
					    c.println ("Congratulations: YOU WIN THE GAME!!!");
					    c.println ("Take a moment to bask in the glory, and when you're done,");
					    c.print ("press any key and <Enter> to CLOSE the game. :) ");
					    c.readString ();
					    System.exit (0);

					}
					else
					{
					    c.clear ();
					    c.println ("You have named the Gas Giant Planet \"" + Level5Name + "\"! Now you're ready to go out ");
					    c.print ("there and do some more space-searching! Press any key then <Enter> to continue: ");
					    c.readString ();
					    found = false;
					}
				    }
				    else
				    {
					c.println ("NOPE! The APSE board members claim that you can't spell DNA!");
					c.println ("Minus 3 dignity points. Also, they docked $100 from your pay! :(");
					grant = grant - 100;
					c.println ("Your balance is now $" + grant + ". Now you go out there and try it again!");
					c.println ("You may want to consult the Neil DeGrasse Tyson database again...");
					c.print ("Press any key and then <Enter> to continue: ");
					c.readString ();
					if (grant < 0)
					{
					    c.clear ();
					    c.drawImage (err, 250, 150, null);
					    c.println ("YOUR COMPANY IS IN DEBT!");
					    delay (10);
					    c.println ("Well " + name + ", you tried to square root a negative number, and this is what happened.");
					    c.println ("You have failed your mission, but feel free to try again!");
					    delay (10);
					    c.println ("***TERMINATING***");
					    delay (1000);
					    System.exit (0);
					}
				    }
				    break;

			    } //end of switch}
			} // end of else from error planets
		    } //end of the planets

		    else if ((foundlv5 >= 5) && (foundlv5 <= 6) && (Star1))
		    {
			Frame stars = new Frame (); //Quiz for level 4 star 2 pt. 2

			JRadioButton mainStar = new JRadioButton ("Main Sequence Star");
			JRadioButton neutronStar = new JRadioButton ("Neutron Star");

			boolean mainStar1, neutronStar1, errorPlanets;
			String messageStar = "Which star type do you think this is " + name + "?";
			c.println ();
			c.println (messageStar);
			Object[] paramsStar = {mainStar, neutronStar};
			for (boolean affirm = false ; affirm == false ;)
			{
			    int planetQuiz = JOptionPane.showConfirmDialog (stars, paramsStar, messageStar, JOptionPane.OK_CANCEL_OPTION);
			    int confirm = JOptionPane.showConfirmDialog (
				    null,
				    "Remember " + name + ", you will lose grant money if you get this wrong!",
				    "ARE YOU SURE???!!!",
				    JOptionPane.YES_NO_OPTION);
			    if (confirm == JOptionPane.YES_OPTION)
			    {
				affirm = true;
			    }
			    else
			    {
				affirm = false;
			    }
			}

			mainStar1 = mainStar.isSelected ();
			neutronStar1 = neutronStar.isSelected ();
			errorPlanets = (neutronStar1 && mainStar1);

			if (errorPlanets)
			{
			    c.clear ();
			    c.drawImage (err, 250, 150, null);
			    c.println ("ERROR! CHOOSE ONLY ONE OPTION PLEASE!");
			    delay (10);
			    c.println ("Well " + name + ", you tried to divide by zero and this is what happened,");
			    c.println ("You have failed your mission, but feel free to try again!");
			    delay (10);
			    c.println ("***TERMINATING***");
			    delay (1000);
			    System.exit (0);
			}
			else
			{
			    switch (foundlv5)
			    {
				case 5: //Main Star
				    if (mainStar1)
				    {
					level5ID = true;
					String Level5Name;
					grant = grant + 2000;
					c.clear ();
					avatar (375, 275, sex1, skin, hair, male1, female1, other1, heli);
					c.println ("Good job! You got ($2000) for that correct classification of a Main Sequence Star!");
					c.print ("Your balance is now $");
					c.print (grant, 0, 2);
					c.print (" Go ahead, name the planet: "); //Space here is for grant
					Level5Name = c.readLine ();
					if (grant >= 10000)
					{
					    c.clear ();
					    avatar (375, 275, sex1, skin, hair, male1, female1, other1, heli);
					    c.println ("Hey, " + name + "! You've finally raised enough funds to rent the James");
					    c.println ("Webb VII space telescope and proceed with your search for a new habitable planet!");
					    c.println ("Congratulations: YOU WIN THE GAME!!!");
					    c.println ("Take a moment to bask in the glory, and when you're done,");
					    c.print ("press any key and <Enter> to CLOSE the game. :) ");
					    c.readString ();
					    System.exit (0);

					}
					else
					{
					    c.clear ();
					    c.println ("You have named Main Sequence Star \"" + Level5Name + "\"! Now you're ready to go out ");
					    c.print ("there and do some more space-searching! Press any key then <Enter> to continue: ");
					    c.readString ();
					    found = false;
					}
				    }
				    else
				    {
					c.println ("NOPE! The APSE board members claim that you can't spell DNA!");
					c.println ("Minus 3 dignity points. Also, they docked $100 from your pay! :(");
					grant = grant - 100;
					c.println ("Your balance is now $" + grant + ". Now you go out there and try it again!");
					c.println ("You may want to consult the Neil DeGrasse Tyson database again...");
					c.print ("Press any key and then <Enter> to continue: ");
					c.readString ();
					if (grant < 0)
					{
					    c.clear ();
					    c.drawImage (err, 250, 150, null);
					    c.println ("YOUR COMPANY IS IN DEBT!");
					    delay (10);
					    c.println ("Well " + name + ", you tried to square root a negative number, and this is what happened.");
					    c.println ("You have failed your mission, but feel free to try again!");
					    delay (10);
					    c.println ("***TERMINATING***");
					    delay (1000);
					    System.exit (0);
					}
				    }
				    break;
				case 6: //Neutron Star
				    if (neutronStar1)
				    {
					level5ID = true;
					String Level5Name;
					grant = grant + 2000;
					c.clear ();
					avatar (375, 275, sex1, skin, hair, male1, female1, other1, heli);
					c.println ("Good job! You got ($2000) for that correct classification of a Neutron Star!");
					c.print ("Your balance is now $");
					c.print (grant, 0, 2);
					c.print (" Go ahead, name the planet: "); //Space here is for grant
					Level5Name = c.readLine ();
					if (grant >= 10000)
					{
					    c.clear ();
					    avatar (375, 275, sex1, skin, hair, male1, female1, other1, heli);
					    c.println ("Hey, " + name + "! You've finally raised enough funds to rent the James");
					    c.println ("Webb VII space telescope and proceed with your search for a new habitable planet!");
					    c.println ("Congratulations: YOU WIN THE GAME!!!");
					    c.println ("Take a moment to bask in the glory, and when you're done,");
					    c.print ("press any key and <Enter> to CLOSE the game. :) ");
					    c.readString ();
					    System.exit (0);

					}
					else
					{
					    c.clear ();
					    c.println ("You have named the Neutron Star \"" + Level5Name + "\"! Now you're ready to go out ");
					    c.print ("there and do some more space-searching! Press any key then <Enter> to continue: ");
					    c.readString ();
					    found = false;
					}
				    }
				    else
				    {
					c.println ("NOPE! The APSE board members claim that you can't spell DNA!");
					c.println ("Minus 3 dignity points. Also, they docked $100 from your pay! :(");
					grant = grant - 100;
					c.println ("Your balance is now $" + grant + ". Now you go out there and try it again!");
					c.println ("You may want to consult the Neil DeGrasse Tyson database again...");
					c.print ("Press any key and then <Enter> to continue: ");
					c.readString ();
					if (grant < 0)
					{
					    c.clear ();
					    c.drawImage (err, 250, 150, null);
					    c.println ("YOUR COMPANY IS IN DEBT!");
					    delay (10);
					    c.println ("Well " + name + ", you tried to square root a negative number, and this is what happened.");
					    c.println ("You have failed your mission, but feel free to try again!");
					    delay (10);
					    c.println ("***TERMINATING***");
					    delay (1000);
					    System.exit (0);
					}
				    }
				    break;
			    } //end of switch
			} // end of else from error planets
		    } //end of the stars

		    if ((foundlv5 >= 7) && (foundlv5 <= 9) && (Others1))
		    {
			Frame others = new Frame (); //Quiz for level 5 object 3 pt. 2

			JRadioButton blackHole = new JRadioButton ("Black Hole");
			JRadioButton superNova = new JRadioButton ("Supernova");
			JRadioButton millenniumFalcon = new JRadioButton ("Millennium Falcon");

			boolean blackHole1, superNova1, millennium1, errorOthers;
			String messageOthers = "Which other object type do you think this is " + name + "?";
			c.println ();
			c.println (messageOthers);
			Object[] paramsOthers = {blackHole, superNova, millenniumFalcon};
			for (boolean affirm = false ; affirm == false ;)
			{
			    int othersQuiz = JOptionPane.showConfirmDialog (others, paramsOthers, messageOthers, JOptionPane.OK_CANCEL_OPTION);
			    int confirm = JOptionPane.showConfirmDialog (
				    null,
				    "Remember " + name + ", you will lose grant money if you get this wrong!",
				    "ARE YOU SURE???!!!",
				    JOptionPane.YES_NO_OPTION);
			    if (confirm == JOptionPane.YES_OPTION)
			    {
				affirm = true;
			    }
			    else
			    {
				affirm = false;
			    }
			}

			blackHole1 = blackHole.isSelected ();
			superNova1 = superNova.isSelected ();
			millennium1 = millenniumFalcon.isSelected ();
			errorOthers = ((blackHole1 && superNova1) || (blackHole1 && millennium1) || (superNova1 && millennium1));

			if (errorOthers)
			{
			    c.clear ();
			    c.drawImage (err, 250, 150, null);
			    c.println ("ERROR! CHOOSE ONLY ONE OPTION PLEASE!");
			    delay (10);
			    c.println ("Well " + name + ", you tried to divide by zero and this is what happened,");
			    c.println ("You have failed your mission, but feel free to try again!");
			    delay (10);
			    c.println ("***TERMINATING***");
			    delay (1000);
			    System.exit (0);
			}
			else
			    switch (foundlv5)
			    {
				case 7: //black hole
				    if (blackHole1)
				    {
					level5ID = true;
					String Level5Name;
					grant = grant + 5000;
					c.clear ();
					avatar (375, 275, sex1, skin, hair, male1, female1, other1, heli);
					c.println ("You found an Easter Egg!");
					c.println ("Good job! You got ($5000) for finding a brand spankin' new BlackHole! Wow!");
					c.print ("Your balance is now $");
					c.print (grant, 0, 2);
					c.print (" Go ahead, name the black hole: "); //Space here is for grant
					Level5Name = c.readLine ();
					if (grant >= 10000)
					{
					    c.clear ();
					    avatar (375, 275, sex1, skin, hair, male1, female1, other1, heli);
					    c.println ("Hey, " + name + "! You've finally raised enough funds to rent the James");
					    c.println ("Webb VII space telescope and proceed with your search for a new habitable planet!");
					    c.println ("Congratulations: YOU WIN THE GAME!!!");
					    c.println ("Take a moment to bask in the glory, and when you're done,");
					    c.print ("press any key and <Enter> to CLOSE the game. :) ");
					    c.readString ();
					    System.exit (0);

					}
					else
					{
					    c.clear ();
					    c.println ("You have named the Black hole \"" + Level5Name + "\"! Now you're ready to go out ");
					    c.print ("there and do some more space-searching! Press any key then <Enter> to continue: ");
					    c.readString ();
					    found = false;
					}
				    }
				    else
				    {
					c.println ("NOPE! The APSE board members give you bananas, implying that");
					c.println ("you're a monkey! Minus 3 dignity points. Also, they docked $100");
					c.println ("from your pay! :( How about you go out there and try it again?");
					grant = grant - 100;
					c.println ("Your balance is now $" + grant + ". Now you go out there and try it again!");
					c.println ("You may want to consult the Neil DeGrasse Tyson database again...");
					c.print ("Press any key and then <Enter> to continue: ");
					c.readString ();

					if (grant < 0)
					{
					    c.clear ();
					    c.drawImage (err, 250, 150, null);
					    c.println ("YOUR COMPANY IS IN DEBT!");
					    delay (10);
					    c.println ("Well " + name + ", you tried to square root a negative number, and this is what happened.");
					    c.println ("You have failed your mission, but feel free to try again!");
					    delay (10);
					    c.println ("***TERMINATING***");
					    delay (1000);
					    System.exit (0);
					}
				    }
				    break;
				case 8: //supernova
				    if (superNova1)
				    {
					level5ID = true;
					String Level5Name;
					grant = grant + 10000;
					c.clear ();
					avatar (375, 275, sex1, skin, hair, male1, female1, other1, heli);
					c.println ("You found an Easter Egg!");
					c.println ("Good job! You got ($10000) for finding a brand spankin' new Supernova! Wow!");
					c.print ("Your balance is now $");
					c.print (grant, 0, 2);
					c.println (" You are getting very close to the goal! Keep at it!");
					c.print ("Go ahead, name the Supernova: "); //Space here is for grant
					Level5Name = c.readLine ();
					if (grant >= 10000)
					{
					    c.clear ();
					    avatar (375, 275, sex1, skin, hair, male1, female1, other1, heli);
					    c.println ("Hey, " + name + "! You've finally raised enough funds to rent the James");
					    c.println ("Webb VII space telescope and proceed with your search for a new habitable planet!");
					    c.println ("Congratulations: YOU WIN THE GAME!!!");
					    c.println ("Take a moment to bask in the glory, and when you're done,");
					    c.print ("press any key and <Enter> to CLOSE the game. :) ");
					    c.readString ();
					    System.exit (0);

					}
					else
					{
					    c.clear ();
					    c.println ("You have named the Supernova \"" + Level5Name + "\"! Now you're ready to go out ");
					    c.print ("there and do some more space-searching! Press any key then <Enter> to continue: ");
					    c.readString ();
					    found = false;
					}
				    }
				    else
				    {
					c.println ("NOPE! The APSE board members insult your highschool physics teacher!");
					c.println ("Minus 3 dignity points. Also, they docked $100 from your pay! :(");
					grant = grant - 100;
					c.println ("Your balance is now $" + grant + ". Now you go out there and try it again!");
					c.println ("You may want to consult the Neil DeGrasse Tyson database again...");
					c.print ("Press any key and then <Enter> to continue: ");
					c.readString ();
					if (grant < 0)
					{
					    c.clear ();
					    c.drawImage (err, 250, 150, null);
					    c.println ("YOUR COMPANY IS IN DEBT!");
					    delay (10);
					    c.println ("Well " + name + ", you tried to square root a negative number, and this is what happened.");
					    c.println ("You have failed your mission, but feel free to try again!");
					    delay (10);
					    c.println ("***TERMINATING***");
					    delay (1000);
					    System.exit (0);
					}
				    }
				    break;
				case 9: //millennium falcon
				    if (millennium1)
				    {
					level5ID = true;
					String Level5Name;
					grant = grant + 10000;
					c.clear ();
					avatar (375, 275, sex1, skin, hair, male1, female1, other1, heli);
					c.println ("You found an Easter Egg!");
					c.println ("You found Han Solo and his crew! Han Solo rewards you for giving him a planet to stay in");
					c.println ("for a while by giving you 1 million Mos Eisley credits! Unfortunetly, Mos Eisley credits");
					c.print ("are worth nothing on Earth... Press any key to continue then <Enter>: ");
					c.readString ();

					c.clear ();
					avatar (375, 275, sex1, skin, hair, male1, female1, other1, heli);
					c.println ("Good job! You got ($10000) for finding the Millennium Falcon! Your ");
					c.println ("ground-breaking discovery baffles scientists all around the globe...");
					c.println ("and now they expect you to prove the existence of midi-chlorians!");
					c.print ("Your balance is now $");
					c.print (grant, 0, 2);
					c.print (" Go ahead, rename the Millenium Falcon: "); //Space here is for grant
					Level5Name = c.readLine ();
					if (grant >= 10000)
					{
					    c.clear ();
					    avatar (375, 275, sex1, skin, hair, male1, female1, other1, heli);
					    c.println ("Hey, " + name + "! You've finally raised enough funds to rent the James");
					    c.println ("Webb VII space telescope and proceed with your search for a new habitable planet!");
					    c.println ("Congratulations: YOU WIN THE GAME!!!");
					    c.println ("Take a moment to bask in the glory, and when you're done,");
					    c.print ("press any key and <Enter> to CLOSE the game. :) ");
					    c.readString ();
					    System.exit (0);

					}
					else
					{
					    c.clear ();
					    c.println ("You have renamed the Millennium Falcon to \"" + Level5Name + "\"! Now you're ready to go out ");
					    c.print ("there and do some more space-searching! Press any key then <Enter> to continue: ");
					    c.readString ();
					    found = false;
					}
				    }
				    else
				    {
					c.println ("NOPE! The APSE board members insult your highschool physics teacher!");
					c.println ("Minus 3 dignity points. Also, they docked $100 from your pay! :(");
					grant = grant - 100;
					c.println ("Your balance is now $" + grant + ". Now you go out there and try it again!");
					c.println ("You may want to consult the Neil DeGrasse Tyson database again...");
					c.print ("Press any key and then <Enter> to continue: ");
					c.readString ();
					if (grant < 0)
					{
					    c.clear ();
					    c.drawImage (err, 250, 150, null);
					    c.println ("YOUR COMPANY IS IN DEBT!");
					    delay (10);
					    c.println ("Well " + name + ", you tried to square root a negative number, and this is what happened.");
					    c.println ("You have failed your mission, but feel free to try again!");
					    delay (10);
					    c.println ("***TERMINATING***");
					    delay (1000);
					    System.exit (0);
					}
				    }
				    break;
			    }
		    }
		}
	    } //Level 5 classifying process

	}
    } // main method


    // MAIN METHOD ^^^
    // MAIN METHOD ^^^
    // MAIN METHOD ^^^
    // MAIN METHOD ^^^
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // MAIN METHOD ^^^
    // MAIN METHOD ^^^
    // MAIN METHOD ^^^
    // MAIN METHOD ^^^



    // sub methods
    public static void delay (double seconds)
    {
	for (int s = 0 ; s <= seconds ; s++)
	{
	    for (int t = 1 ; t <= 10000000 ; t++) //100000000
	    {
	    }
	}
    }


    public static void star1 (int[] arrX, int[] arrY)    //star generator
    {

	for (int i = 0 ; i <= 19 ; i++)
	{
	    c.drawLine (arrX [i] - 2, arrY [i], arrX [i] + 2, arrY [i]);
	}


	for (int i = 0 ; i <= 19 ; i++)
	{
	    c.drawLine (arrX [i], arrY [i] - 2, arrX [i], arrY [i] + 2);
	}
    }


    public static void star2 (int[] arrX2, int[] arrY2)
    {

	for (int i = 0 ; i <= 19 ; i++)
	{
	    c.drawLine (arrX2 [i] - 2, arrY2 [i] - 2, arrX2 [i] + 2, arrY2 [i] + 2);
	}


	for (int i = 0 ; i <= 19 ; i++)
	{
	    c.drawLine (arrX2 [i] - 2, arrY2 [i] + 2, arrX2 [i] + 2, arrY2 [i] - 2);
	}
    } //star generator


    public static void male (int ma, int mb, Color male3, Color male4)
    {
	// hair
	c.setColor (male4);
	c.fillStar (ma + 3, mb - 39, 15, 15);
	c.fillStar (ma, mb - 32, 15, 15);
	c.fillStar (ma - 1, mb - 26, 15, 15);
	c.fillStar (ma - 2, mb - 19, 15, 12);
	c.fillStar (ma - 3, mb - 13, 15, 12);
	c.fillStar (ma - 3, mb - 7, 15, 12);
	c.fillStar (ma - 4, mb - 1, 15, 12);

	c.fillStar (ma + 8, mb - 42, 15, 15);
	c.fillStar (ma + 13, mb - 42, 15, 15);
	c.fillStar (ma + 19, mb - 42, 15, 15);
	c.fillStar (ma + 25, mb - 42, 15, 15);

	c.fillStar (ma + 31, mb - 39, 15, 15);
	c.fillStar (ma + 34, mb - 32, 15, 15);
	c.fillStar (ma + 35, mb - 26, 15, 15);
	c.fillStar (ma + 36, mb - 19, 15, 12);
	c.fillStar (ma + 37, mb - 13, 15, 12);
	c.fillStar (ma + 37, mb - 7, 15, 12);
	c.fillStar (ma + 38, mb - 1, 15, 12);

	// male body
	// int ma = 100, mb = 100;
	c.setColor (male3);
	c.fillRoundRect (ma, mb, 50, 60, 40, 40); //body
	c.fillRoundRect (ma - 50, mb + 10, 60, 15, 10, 10); //arm
	c.fillRoundRect (ma + 40, mb + 10, 60, 15, 10, 10); //arm
	c.fillOval (ma + 6, mb - 35, 40, 40); // Head
	c.fillRoundRect (ma, mb + 20, 20, 100, 10, 10); //legs
	c.fillRoundRect (ma + 30, mb + 20, 20, 100, 10, 10); //legs
    } // male method


    public static void female (int fa, int fb, Color female3, Color female4)  // fa 100, fb 100
	// female body
    {
	c.setColor (female4); //Hair
	c.fillStar (fa + 25, fb - 38, 15, 15); // Hair
	c.fillStar (fa + 30, fb - 40, 15, 15);
	c.fillStar (fa + 36, fb - 40, 15, 15);
	c.fillStar (fa + 42, fb - 40, 15, 15);
	c.fillStar (fa + 48, fb - 38, 15, 15);


	c.setColor (female3);
	int[] xpoints = {fa + 20, fa + 70, fa + 90, fa}; //body
	int[] ypoints = {fb, fb, fb + 90, fb + 90};
	c.fillPolygon (xpoints, ypoints, 4);

	c.fillRoundRect (fa - 35, fb + 10, 65, 15, 10, 10); //arm
	c.fillRoundRect (fa + 70, fb + 10, 65, 15, 10, 10); //arm

	c.fillRoundRect (fa + 15, fb + 80, 20, 100, 10, 10); //legs
	c.fillRoundRect (fa + 56, fb + 80, 20, 100, 10, 10); //legs

	c.fillOval (fa + 26, fb - 35, 40, 40); // Head


    } //female body method


    public static void avatar (int a, int b, boolean avatarSex, Color avatarSkin, Color avatarHair, boolean avatarMale, boolean avatarFemale, boolean avatarOther, Image avatarHeli)  // Display avatar method
    {
	if (avatarSex == avatarMale)
	{
	    male (a, b, avatarSkin, avatarHair);
	}


	else
	{
	    if (avatarSex == avatarFemale)
	    {
		female (a, b, avatarSkin, avatarHair);
	    }
	    else
	    {
		if (avatarSex == avatarOther)
		{
		    c.drawImage (avatarHeli, 275, 300, null); // DISPLAY HELICOPTER HERE!
		}
		else // Cancel = other
		{
		    c.drawImage (avatarHeli, 275, 300, null); // DISPLAY HELICOPTER HERE!
		}
	    }
	}


	delay (0.5);
    } // Display avatar method


    public static void legend (String object)
    { //Legend Method
	c.clear ();
	if (!object.equals (""))
	{
	    c.println ("Please note: Temperature (T), Size (S), Mass (M), Chemical Composition (C)");
	    c.println ();
	    if (object.equals ("Planets"))
	    {
		c.println ("Earth - like Planet:");
		c.println ("- T = 223 K - 323 K");
		c.println ("- S = 0.5 - 2.0 Earth Radius");
		c.println ("- M = 0.8 - 1.9 Earth Mass");
		c.println ("- C = Lots of silicate rock and sulphur plus others such as iron, carbon, nitrogen");

		c.println ();
		c.println ("Super Earth Planet:");
		c.println ("- T = 223 K - 323 K");
		c.println ("- S = 2.0 - 4.0 Earth Radius");
		c.println ("- M = 2.0 - 10.0 Earth Mass");
		c.println ("- C = Lots of silicate rock and sulphur plus others such as iron, carbon, nitrogen");

		c.println ();
		c.println ("Ice Giant:");
		c.println ("- T = 30 K - 150 K");
		c.println ("- S = 4.0 - 9.0 Earth Radius");
		c.println ("- M = 10 - 50 Earth Mass");
		c.println ("- C = Mainly oxygen, carbon, nitrogen, sulfur;");
		c.println ("about 20 % hydrogen and helium");

		c.println ();
		c.println ("Gas Giant:");
		c.println ("- T = 150 K - 250 K, 350 K - 7000 K");
		c.println ("- S = 9.0 - 33.0 Earth Radius");
		c.println ("- M = 50 - 20000 Earth Mass");
		c.println ("- C = Primarily Hydrogen and helium plus any of the following;");
		c.println ("ammonia, methane, chlorides or sulfides, carbon monoxide, silicate or iron");
	    }
	    else if (object.equals ("Stars"))
	    {
		c.println ("Main Sequence Star:");
		c.println ("- T = 2600 K - 40000 K");
		c.println ("- S = 13.0 - 190000 Earth Radius");
		c.println ("- M = 25000 - 105000000 Earth Mass");
		c.println ("- C = Primarily Hydrogen and helium plus any of the following;");
		c.println ("iron, chromium, oxygen, nitrogen, carbon, neon, silicon");

		c.println ();
		c.println ("Neutron Star:");
		c.println ("- T = 1000000 K - 1000000000000 K");
		c.println ("- S = 0.0008 - 0.003 Earth Radius");
		c.println ("- M = 366000 - 1000000 Earth Mass");
		c.println ("- C = Hydrogen, helium, iron, atomic nuclei condensed into a lattice structure");
		c.println ("with electrons flowing through the gaps in the structure");
	    }
	    else if (object.equals ("Others"))
	    {
		c.println ("Black Hole:");
		c.println ("- T = < 0.00000002 K");
		c.println ("- S = 0.1 mm - 9400000 Earth Radius");
		c.println ("- M = 366000 - 7000000000000000 Earth Mass");
		c.println ("- C = Hydrogen cyanide");

		c.println ();
		c.println ("Supernova");
		c.println ("- T = 100000000000 K");
		c.println ("- S = ? ? ? ? Earth Radius");
		c.println ("- M = ? ? ? ? Earth Mass");
		c.println ("- C = Hydrogen, helium, iron, gold");

		c.println ();
		c.println ("Millennium Falcon");
		c.println ("- T = 276 K");
		c.println ("- S = 35 metres");
		c.println ("- M = 200 kg");
		c.println ("- C = Steel, iron, titanium, chromium, silver, gold, wookie fur");
	    }
	    c.println ();
	    c.print ("When you are done reading, enter any key to continue then press <Enter>: ");
	    c.readString ();
	}
    } //Legend method


    //Method  called "LoadImage" To Return Image
    public static Image loadImage (String path)
    {
	//variable 'f' is a type of file that opens 'path'
	//'path' - opens the location of the image
	File f = new File (path);
	Image img = null; //Image is declared as null

	//Tries to read in the Image placed in file 'f'
	try
	{
	    img = ImageIO.read (f);
	}


	//If there are any disruptions or 'exception', it will be catched in variable 'e'
	//This prevents the program from crashing
	catch (IOException e)
	{
	    System.out.println ("Error: Could not find image. Throwing error: " + e);
	}


	return img; //Returns the image
    } //LoadImage method


    /////// Arrow keys method //////////////////////////////////////////////////////////////// Arrow keys method
    public NewEarth ()
    {
	setSize (new Dimension (800, 800));
	addKeyListener (new KeyAdapter ()
	{
	    //@Override
	    public void keyPressed (KeyEvent evt)
	    {
		if (!found)
		{
		    moveIt (evt);
		}
	    }
	}


	);
    } //Arrow keys method


    int planetX, planetY, planetX2, planetY2, planetX3, planetY3; // Tutorial Planet random location randomizer tutorial
    {
	planetX = (int) (Math.random () * 700) + 25; //x range (25, 725)
	planetY = (int) (Math.random () * 500) + 25; //y range (25, 525)

	planetX2 = (int) (Math.random () * 700) + 25; //x range (25, 725)
	planetY2 = (int) (Math.random () * 500) + 25; //y range (25, 525)

	planetX3 = (int) (Math.random () * 700) + 25; //x range (25, 725)
	planetY3 = (int) (Math.random () * 500) + 25; //y range (25, 525)
    } // TutorialPlanet random location randomizer tutorial


    int iceGiantX1, iceGiantY1; //level 1 planet location randomizer
    { //Level 1
	iceGiantX1 = (int) (Math.random () * 700) + 25;  //x range (25, 725)
	iceGiantY1 = (int) (Math.random () * 500) + 25;  //y range (25, 525)
    } //Level 1


    int gasGiantX1, gasGiantY1, earthLikeX1, earthLikeY1, blackHoleX1, blackHoleY1; // Level 2 planet location randomizer
    { //Level 2
	gasGiantX1 = (int) (Math.random () * 700) + 25;  //x range (25, 725)
	gasGiantY1 = (int) (Math.random () * 500) + 25;  //y range (25, 525)

	earthLikeX1 = (int) (Math.random () * 700) + 25;  //x range (25, 725)
	earthLikeY1 = (int) (Math.random () * 500) + 25;  //y range (25, 525)

	blackHoleX1 = (int) (Math.random () * 700) + 25;  //x range (25, 725)
	blackHoleY1 = (int) (Math.random () * 500) + 25;  //y range (25, 525)
    } //Level 2


    int mainStarX3, mainStarY3, neutronStarX3, neutronStarY3, superNovaX3, superNovaY3; //Level 3 planet location randomizer
    { //Level 3
	mainStarX3 = (int) (Math.random () * 700) + 25;  //x range (25, 725)
	mainStarY3 = (int) (Math.random () * 500) + 25;  //y range (25, 525)

	neutronStarX3 = (int) (Math.random () * 700) + 25;  //x range (25, 725)
	neutronStarY3 = (int) (Math.random () * 500) + 25;  //y range (25, 525)

	superNovaX3 = (int) (Math.random () * 700) + 25;  //x range (25, 725)
	superNovaY3 = (int) (Math.random () * 500) + 25;  //y range (25, 525)
    } //Level 3


    int gasGiantX4, gasGiantY4, mainStarX4, mainStarY4, millFX4, millFY4;  //Level 4 planet location randomizer
    { //Level 4
	gasGiantX4 = (int) (Math.random () * 700) + 25;  //x range (25, 725)
	gasGiantY4 = (int) (Math.random () * 500) + 25;  //y range (25, 525)

	mainStarX4 = (int) (Math.random () * 700) + 25;  //x range (25, 725)
	mainStarY4 = (int) (Math.random () * 500) + 25;  //y range (25, 525)

	millFX4 = (int) (Math.random () * 700) + 25;  //x range (25, 725)
	millFY4 = (int) (Math.random () * 500) + 25;  //y range (25, 525)
    } //Level 4


    int earthLikeX5, earthLikeY5, superEarthX5, superEarthY5, iceGiantX5, iceGiantY5, gasGiantX5, gasGiantY5, mainStarX5, mainStarY5, neutronStarX5, neutronStarY5, bHoleX5, bHoleY5, sNovaX5, sNovaY5, mFalcX5, mFalcY5; //Level 5+ planet location randomizer pt. 1
    { //Level 5+ pt. 1
	earthLikeX5 = (int) (Math.random () * 700) + 25;  //x range (25, 725)
	earthLikeY5 = (int) (Math.random () * 500) + 25;  //y range (25, 525)

	superEarthX5 = (int) (Math.random () * 700) + 25;  //x range (25, 725)
	superEarthY5 = (int) (Math.random () * 500) + 25;  //y range (25, 525)

	iceGiantX5 = (int) (Math.random () * 700) + 25;  //x range (25, 725)
	iceGiantY5 = (int) (Math.random () * 500) + 25;  //y range (25, 525)

	gasGiantX5 = (int) (Math.random () * 700) + 25;  //x range (25, 725)
	gasGiantY5 = (int) (Math.random () * 500) + 25;  //y range (25, 525)

	mainStarX5 = (int) (Math.random () * 700) + 25;  //x range (25, 725)
	mainStarY5 = (int) (Math.random () * 500) + 25;  //y range (25, 525)

	neutronStarX5 = (int) (Math.random () * 700) + 25;  //x range (25, 725)
	neutronStarY5 = (int) (Math.random () * 500) + 25;  //y range (25, 525)

	bHoleX5 = (int) (Math.random () * 700) + 25;  //x range (25, 725)
	bHoleY5 = (int) (Math.random () * 500) + 25;  //y range (25, 525)

	sNovaX5 = (int) (Math.random () * 700) + 25;  //x range (25, 725)
	sNovaY5 = (int) (Math.random () * 500) + 25;  //y range (25, 525)

	mFalcX5 = (int) (Math.random () * 700) + 25;  //x range (25, 725)
	mFalcY5 = (int) (Math.random () * 500) + 25;  //y range (25, 525)
    } //Level 5+ pt. 1


    int earthLikeX52, earthLikeY52, superEarthX52, superEarthY52, iceGiantX52, iceGiantY52, gasGiantX52, gasGiantY52, mainStarX52, mainStarY52, neutronStarX52, neutronStarY52, bHoleX52, bHoleY52, sNovaX52, sNovaY52, mFalcX52, mFalcY52; //Level 5 planet location randomizer pt. 2
    { //level 5+ pt. 2
	earthLikeX52 = (int) (Math.random () * 700) + 25;  //x range (25, 725)
	earthLikeY52 = (int) (Math.random () * 500) + 25;  //y range (25, 525)

	superEarthX52 = (int) (Math.random () * 700) + 25;  //x range (25, 725)
	superEarthY52 = (int) (Math.random () * 500) + 25;  //y range (25, 525)

	iceGiantX52 = (int) (Math.random () * 700) + 25;  //x range (25, 725)
	iceGiantY52 = (int) (Math.random () * 500) + 25;  //y range (25, 525)

	gasGiantX52 = (int) (Math.random () * 700) + 25;  //x range (25, 725)
	gasGiantY52 = (int) (Math.random () * 500) + 25;  //y range (25, 525)

	mainStarX52 = (int) (Math.random () * 700) + 25;  //x range (25, 725)
	mainStarY52 = (int) (Math.random () * 500) + 25;  //y range (25, 525)

	neutronStarX52 = (int) (Math.random () * 700) + 25;  //x range (25, 725)
	neutronStarY52 = (int) (Math.random () * 500) + 25;  //y range (25, 525)
    } //level 5+ pt. 2


    public void paint (Graphics g)  // Game output screen
    {

	g.fillRect (0, 0, 1000, 1000);

	g.setColor (Color.red);
	g.drawLine (10, 10, 10, 570);
	g.drawLine (10, 10, 795, 10);
	g.drawLine (10, 570, 795, 570);
	g.drawLine (795, 570, 795, 10);

	if (lev0 == 1)
	{
	    g.drawString ("Find a celestial body in this patch of the night sky!", 275, 45);
	    int rad = (int) (Math.sqrt (Math.pow (myX - (planetX + 47.0), 2) + Math.pow (myY - (planetY + 47.0), 2)));
	    int rad2 = (int) (Math.sqrt (Math.pow (myX - (planetX2 + 47.0), 2) + Math.pow (myY - (planetY2 + 47.0), 2)));
	    int rad3 = (int) (Math.sqrt (Math.pow (myX - (planetX3 + 47.0), 2) + Math.pow (myY - (planetY3 + 47.0), 2)));

	    if ((rad < 47)
		    || (rad2 < 47) || (rad3 < 47))
	    {
		//Color col = new Color (, ,);
		g.setColor (Color.black);
		g.drawString ("Find a celestial body in this patch of the night sky!", 275, 45);
		g.setColor (Color.green);
		int textX = 75, textY = 120; //default of textX = 75, textY = 120

		//Planet 1 pop-up
		if (planetX < 75) //Left border
		{
		    textX = -30;
		}
		else if (planetX > 680) //Right border
		{
		    textX = 170;
		}
		else
		{
		}

		if (planetY < 170)  //Upper border
		{
		    textY = -120;
		}

		//Planet 2 pop-up
		int textX2 = 75, textY2 = 120;
		if (planetX2 < 75) //Left border
		{
		    textX2 = -30;
		}
		else if (planetX2 > 680) //Right border
		{
		    textX2 = 170;
		}
		else
		{
		}

		if (planetY2 < 170)  //Upper border
		{
		    textY2 = -120;
		}

		//Planet 3 pop-up
		int textX3 = 75, textY3 = 120;
		if (planetX3 < 75) //Left border
		{
		    textX3 = -30;
		}
		else if (planetX3 > 680) //Right border
		{
		    textX3 = 170;
		}
		else
		{
		}

		if (planetY3 < 170)  //Upper border
		{
		    textY3 = -120;
		}

		//Planet revealer
		if (rad < 47)
		{
		    g.drawString ("YOU FOUND A CELESTIAL OBJECT!", planetX - textX, planetY - textY); //Text for planet
		    g.drawString ("T: 250 Degrees Kelvin", planetX - textX, planetY - textY + 20);
		    g.drawString ("S: 3.2 Earth Radii", planetX - textX, planetY - textY + 40);
		    g.drawString ("M: 7.6 Earth Masses", planetX - textX, planetY - textY + 60);
		    g.drawString ("C: Silicate rock, Iron", planetX - textX, planetY - textY + 80);
		    g.drawString ("NOW GO BACK TO THE TEXT CONSOLE!", planetX - textX, planetY - textY + 100);
		    g.fillOval (planetX, planetY, 94, 94); //Unknown planet
		}
		if (rad2 < 47)
		{
		    g.drawString ("YOU FOUND A CELESTIAL OBJECT!", planetX2 - textX2, planetY2 - textY2);
		    g.drawString ("T: 250 Degrees Kelvin", planetX2 - textX2, planetY2 - textY2 + 20);
		    g.drawString ("S: 3.2 Earth Radii", planetX2 - textX2, planetY2 - textY2 + 40);
		    g.drawString ("M: 7.6 Earth Masses", planetX2 - textX2, planetY2 - textY2 + 60);
		    g.drawString ("C: Silicate rock, Iron", planetX2 - textX2, planetY2 - textY2 + 80);
		    g.drawString ("NOW GO BACK TO THE TEXT CONSOLE!", planetX2 - textX2, planetY2 - textY2 + 100);
		    g.fillOval (planetX2, planetY2, 94, 94); //Unknown planet
		}
		if (rad3 < 47)
		{
		    g.drawString ("YOU FOUND A CELESTIAL OBJECT!", planetX3 - textX3, planetY3 - textY3);
		    g.drawString ("T: 250 Degrees Kelvin", planetX3 - textX3, planetY3 - textY3 + 20);
		    g.drawString ("S: 3.2 Earth Radii", planetX3 - textX3, planetY3 - textY3 + 40);
		    g.drawString ("M: 7.6 Earth Masses", planetX3 - textX3, planetY3 - textY3 + 60);
		    g.drawString ("C: Silicate rock, Iron", planetX3 - textX3, planetY3 - textY3 + 80);
		    g.drawString ("NOW GO BACK TO THE TEXT CONSOLE!", planetX3 - textX3, planetY3 - textY3 + 100);
		    g.fillOval (planetX3, planetY3, 94, 94); //Unknown planet
		}
		g.setColor (Color.red);
	    }


	    else if ((rad < 135)
		    || (rad2 < 135) || (rad3 < 135))
	    {
		Color orange = new Color (255, 140, 0);
		g.setColor (orange);
	    }


	    else if ((rad < 225)
		    || (rad2 < 225) || (rad3 < 225))
	    {
		g.setColor (Color.yellow);
	    }


	    else
	    {
		g.setColor (Color.white);
	    }


	    //crosshair vvv
	    g.fillOval (myX - 1, myY - 1, 2, 2); //crosshair

	    g.drawLine (myX - 18, myY - 18, myX - 10, myY - 18);
	    g.drawLine (myX - 18, myY - 18, myX - 18, myY - 10);

	    g.drawLine (myX + 18, myY - 18, myX + 10, myY - 18);
	    g.drawLine (myX + 18, myY - 18, myX + 18, myY - 10);

	    g.drawLine (myX - 18, myY + 18, myX - 10, myY + 18);
	    g.drawLine (myX - 18, myY + 18, myX - 18, myY + 10);

	    g.drawLine (myX + 18, myY + 18, myX + 10, myY + 18);
	    g.drawLine (myX + 18, myY + 18, myX + 18, myY + 10);
	    //crosshair ^^^

	    g.drawLine (myX, myY, 400, 635); //Laser beam
	    g.fillArc (350, 585, 100, 100, 0, 180); //observatory

	    if ((rad < 47) || (rad2 < 47) || (rad3 < 47))
	    {
		delay (50);

		found = true;
		c.clear ();
		lev0 = 0;

		c.println ("Hey, you found something! Now you have to classify it so that other scientists will");
		c.println ("know what it is if they ever see it again. You'll be doing this based on the");
		c.println ("characteristics that you see whenever you're looking at a planet. You can use the Neil");
		c.println ("DeGrasse Tyson Database to tell you the characteristics of different space-stuff!");
		c.println ("Click back in forth between the 2 consoles when deciding a match, don't drag any of");
		c.println ("them! Type in any key and <Enter> to continue: ");
		lev1 = 1;
	    }
	}


	else if (lev1 == 1) //LEVEL 1
	{
	    g.drawString ("Find a celestial body in this patch of the night sky!", 275, 45);
	    found = false;
	    int radIce1 = (int) (Math.sqrt (Math.pow (myX - (iceGiantX1 + 16.0), 2) + Math.pow (myY - (iceGiantY1 + 16.0), 2)));

	    if (radIce1 < 16)
	    {
		//Color col = new Color (, ,);
		g.setColor (Color.black);
		g.drawString ("Find a celestial body in this patch of the night sky!", 275, 45);
		g.setColor (Color.green);
		int textX = 75, textY = 120; //default of textX = 75, textY = 120

		//Planet 1 pop-up
		if (iceGiantX1 < 75) //Left border
		{
		    textX = -30;
		}
		else if (iceGiantX1 > 680) //Right border
		{
		    textX = 170;
		}
		else
		{
		}

		if (iceGiantY1 < 170)  //Upper border
		{
		    textY = -100;
		}

		g.drawString ("CELESTIAL BODY INFO BUBBLE:", iceGiantX1 - textX, iceGiantY1 - textY);
		g.drawString ("T: 45 degrees Kelvin", iceGiantX1 - textX, iceGiantY1 - textY + 20);
		g.drawString ("S: 6.0 Earth Radii", iceGiantX1 - textX, iceGiantY1 - textY + 40);
		g.drawString ("M: 25 Earth Masses", iceGiantX1 - textX, iceGiantY1 - textY + 60);
		g.drawString ("C: Oxygen, hydrogen, helium", iceGiantX1 - textX, iceGiantY1 - textY + 80);
		g.drawString ("NOW GO BACK TO THE TEXT CONSOLE!", iceGiantX1 - textX, iceGiantY1 - textY + 100);
		g.fillOval (iceGiantX1, iceGiantY1, 32, 32); //Unknown planet
		g.setColor (Color.red);

		delay (50);

		found = true;
		c.clear ();
		lev1 = 0;

		c.println ("Hey, you found something! Now you've just got to classify it!");
		c.println ("Type in any key and <Enter> to continue: ");
		lev2 = 1;
	    }
	    else if (radIce1 < 45)
	    {
		Color orange = new Color (255, 140, 0);
		g.setColor (orange);
	    }


	    else if (radIce1 < 75)
	    {
		g.setColor (Color.yellow);
	    }


	    else
	    {
		g.setColor (Color.white);
	    }
	    //crosshair vvv
	    g.fillOval (myX - 1, myY - 1, 2, 2); //crosshair

	    g.drawLine (myX - 18, myY - 18, myX - 10, myY - 18);
	    g.drawLine (myX - 18, myY - 18, myX - 18, myY - 10);

	    g.drawLine (myX + 18, myY - 18, myX + 10, myY - 18);
	    g.drawLine (myX + 18, myY - 18, myX + 18, myY - 10);

	    g.drawLine (myX - 18, myY + 18, myX - 10, myY + 18);
	    g.drawLine (myX - 18, myY + 18, myX - 18, myY + 10);

	    g.drawLine (myX + 18, myY + 18, myX + 10, myY + 18);
	    g.drawLine (myX + 18, myY + 18, myX + 18, myY + 10);
	    //crosshair ^^^

	    g.drawLine (myX, myY, 400, 635); //Laser beam
	    g.fillArc (350, 585, 100, 100, 0, 180); //observatory

	} //END OF LEVEL 1


	else if (lev2 == 1) //Start of level 2
	{
	    g.drawString ("Find a celestial body in this patch of the night sky!", 275, 45);
	    found = false;
	    int radGas1 = (int) (Math.sqrt (Math.pow (myX - (gasGiantX1 + 16.0), 2) + Math.pow (myY - (gasGiantY1 + 16.0), 2)));
	    int radEarthLike1 = (int) (Math.sqrt (Math.pow (myX - (earthLikeX1 + 14.0), 2) + Math.pow (myY - (earthLikeY1 + 14.0), 2)));
	    int radBlackHole1 = (int) (Math.sqrt (Math.pow (myX - (blackHoleX1 + 5.0), 2) + Math.pow (myY - (blackHoleY1 + 5.0), 2)));

	    if ((radGas1 < 16) || (radEarthLike1 < 14) || (radBlackHole1 < 5))
	    {
		//Color col = new Color (, ,);
		g.setColor (Color.black);
		g.drawString ("Find a celestial body in this patch of the night sky!", 275, 45);
		g.setColor (Color.green);
		int textX = 75, textY = 120; //default of textX = 75, textY = 120

		//Gas Giant pop-up
		if (gasGiantX1 < 75) //Left border
		{
		    textX = -30;
		}
		else if (gasGiantX1 > 680) //Right border
		{
		    textX = 170;
		}
		else
		{
		}

		if (gasGiantY1 < 170)  //Upper border
		{
		    textY = -100;
		}

		//Earth-like pop-up
		int textX2 = 75, textY2 = 120; //default of textX = 75, textY = 120
		if (earthLikeX1 < 75) //Left border
		{
		    textX2 = -30;
		}
		else if (earthLikeX1 > 680) //Right border
		{
		    textX2 = 170;
		}
		else
		{
		}

		if (earthLikeY1 < 170)  //Upper border
		{
		    textY2 = -100;
		}

		//Black hole pop-up
		int textX3 = 75, textY3 = 120; //default of textX = 75, textY = 120
		if (blackHoleX1 < 75) //Left border
		{
		    textX3 = -30;
		}
		else if (blackHoleX1 > 680) //Right border
		{
		    textX3 = 170;
		}
		else
		{
		}

		if (blackHoleY1 < 170)  //Upper border
		{
		    textY3 = -100;
		}

		if (radGas1 < 16) // 1. Gas Giant
		{
		    foundlv2 = 1;
		    g.drawString ("CELESTIAL BODY INFO BUBBLE:", gasGiantX1 - textX, gasGiantY1 - textY);
		    g.drawString ("T: 1200 Degrees Kelvin", gasGiantX1 - textX, gasGiantY1 - textY + 20);
		    g.drawString ("S: 27 Earth Radii", gasGiantX1 - textX, gasGiantY1 - textY + 40);
		    g.drawString ("M: 12000 Earth Masses", gasGiantX1 - textX, gasGiantY1 - textY + 60);
		    g.drawString ("C: Ammonia, methane, hydrogen, helium", gasGiantX1 - textX, gasGiantY1 - textY + 80);
		    g.drawString ("NOW GO BACK TO THE TEXT CONSOLE!", gasGiantX1 - textX, gasGiantY1 - textY + 100);
		    g.fillOval (gasGiantX1, gasGiantY1, 32, 32); //Unknown planet
		}
		if (radEarthLike1 < 14) // 2. Earth-like
		{
		    foundlv2 = 2;
		    g.drawString ("CELESTIAL BODY INFO BUBBLE:", earthLikeX1 - textX2, earthLikeY1 - textY2);
		    g.drawString ("T: 299 Degrees Kelvin", earthLikeX1 - textX2, earthLikeY1 - textY2 + 20);
		    g.drawString ("S: 1.4 Earth Radii", earthLikeX1 - textX2, earthLikeY1 - textY2 + 40);
		    g.drawString ("M: 1.3 Earth Masses", earthLikeX1 - textX2, earthLikeY1 - textY2 + 60);
		    g.drawString ("C: Silicate rock, sulphur, iron, carbon", earthLikeX1 - textX2, earthLikeY1 - textY2 + 80);
		    g.drawString ("NOW GO BACK TO THE TEXT CONSOLE!", earthLikeX1 - textX2, earthLikeY1 - textY2 + 100);
		    g.fillOval (earthLikeX1, earthLikeY1, 28, 28); //Unknown planet
		}
		if (radBlackHole1 < 5) // 3. Black hole
		{
		    foundlv2 = 3;
		    g.drawString ("CELESTIAL BODY INFO BUBBLE:", blackHoleX1 - textX3, blackHoleY1 - textY3);
		    g.drawString ("T: 0.00000001 Degrees Kelvin", blackHoleX1 - textX3, blackHoleY1 - textY3 + 20);
		    g.drawString ("S: 1.0 Earth Radii", blackHoleX1 - textX3, blackHoleY1 - textY3 + 40);
		    g.drawString ("M: 36700000000 Earth Masses", blackHoleX1 - textX3, blackHoleY1 - textY3 + 60);
		    g.drawString ("C: Hydrogen cyanide", blackHoleX1 - textX3, blackHoleY1 - textY3 + 80);
		    g.drawString ("NOW GO BACK TO THE TEXT CONSOLE!", blackHoleX1 - textX3, blackHoleY1 - textY3 + 100);
		}

		g.setColor (Color.red);
		delay (50);

		found = true;
		c.clear ();
		lev2 = 0;

		c.println ("Hey, you found something! Now you've just got to classify it!");
		c.println ("Type in any key and <Enter> to continue: ");
		lev3 = 1;
	    }
	    else if ((radGas1 < 44) || (radEarthLike1 < 42) || (radBlackHole1 < 14))
	    {
		Color orange = new Color (255, 140, 0);
		g.setColor (orange);
	    }


	    else if ((radGas1 < 74) || (radEarthLike1 < 70) || (radBlackHole1 < 23))
	    {
		g.setColor (Color.yellow);
	    }


	    else
	    {
		g.setColor (Color.white);
	    }
	    //crosshair vvv
	    g.fillOval (myX - 1, myY - 1, 2, 2); //crosshair

	    g.drawLine (myX - 18, myY - 18, myX - 10, myY - 18);
	    g.drawLine (myX - 18, myY - 18, myX - 18, myY - 10);

	    g.drawLine (myX + 18, myY - 18, myX + 10, myY - 18);
	    g.drawLine (myX + 18, myY - 18, myX + 18, myY - 10);

	    g.drawLine (myX - 18, myY + 18, myX - 10, myY + 18);
	    g.drawLine (myX - 18, myY + 18, myX - 18, myY + 10);

	    g.drawLine (myX + 18, myY + 18, myX + 10, myY + 18);
	    g.drawLine (myX + 18, myY + 18, myX + 18, myY + 10);
	    //crosshair ^^^

	    g.drawLine (myX, myY, 400, 635); //Laser beam
	    g.fillArc (350, 585, 100, 100, 0, 180); //observatory

	} //END OF LEVEL 2


	else if (lev3 == 1)
	{ //START OF LEVEL 3
	    // mainStarX3, mainStarY3, neutronStarX3, neutronStarY3, superNovaX3, supernovaY3
	    g.drawString ("Find a celestial body in this patch of the night sky!", 275, 45);
	    found = false;
	    int radMainStar1 = (int) (Math.sqrt (Math.pow (myX - (mainStarX3 + 14.0), 2) + Math.pow (myY - (mainStarY3 + 14.0), 2)));
	    int radNeutronStar1 = (int) (Math.sqrt (Math.pow (myX - (neutronStarX3 + 10.0), 2) + Math.pow (myY - (neutronStarY3 + 10.0), 2)));
	    int radSuperNova1 = (int) (Math.sqrt (Math.pow (myX - (superNovaX3 + 5.0), 2) + Math.pow (myY - (superNovaY3 + 5.0), 2)));

	    if ((radMainStar1 < 14) || (radNeutronStar1 < 10) || (radSuperNova1 < 5))
	    {
		//Color col = new Color (, ,);
		g.setColor (Color.black);
		g.drawString ("Find a celestial body in this patch of the night sky!", 275, 45);
		g.setColor (Color.green);
		int textX = 75, textY = 120; //default of textX = 75, textY = 120

		//Main Star pop-up
		if (mainStarX3 < 75) //Left border
		{
		    textX = -30;
		}
		else if (mainStarX3 > 680) //Right border
		{
		    textX = 170;
		}
		else
		{
		}

		if (mainStarY3 < 170)  //Upper border
		{
		    textY = -100;
		}

		//Neutron Star pop-up
		int textX2 = 75, textY2 = 120; //default of textX = 75, textY = 120
		if (neutronStarX3 < 75) //Left border
		{
		    textX2 = -30;
		}
		else if (neutronStarX3 > 680) //Right border
		{
		    textX2 = 170;
		}
		else
		{
		}

		if (neutronStarY3 < 170)  //Upper border
		{
		    textY2 = -100;
		}

		//Super Nova pop-up
		int textX3 = 75, textY3 = 120; //default of textX = 75, textY = 120
		if (superNovaX3 < 75) //Left border
		{
		    textX3 = -30;
		}
		else if (superNovaX3 > 680) //Right border
		{
		    textX3 = 170;
		}
		else
		{
		}

		if (superNovaY3 < 170)  //Upper border
		{
		    textY3 = -100;
		}

		if (radMainStar1 < 14) // 1. Main Star
		{
		    foundlv3 = 1;
		    g.drawString ("CELESTIAL BODY INFO BUBBLE:", mainStarX3 - textX, mainStarY3 - textY);
		    g.drawString ("T: 28000 Degrees Kelvin", mainStarX3 - textX, mainStarY3 - textY + 20);
		    g.drawString ("S: 300 Earth Radii", mainStarX3 - textX, mainStarY3 - textY + 40);
		    g.drawString ("M: 1000000 Earth Masses", mainStarX3 - textX, mainStarY3 - textY + 60);
		    g.drawString ("C: Hydrogen, helium, iron, chromium", mainStarX3 - textX, mainStarY3 - textY + 80);
		    g.drawString ("NOW GO BACK TO THE TEXT CONSOLE!", mainStarX3 - textX, mainStarY3 - textY + 100);
		    g.fillOval (mainStarX3, mainStarY3, 28, 28); //Unknown star
		}
		if (radNeutronStar1 < 10) // 2. Neutron star
		{
		    foundlv3 = 2;
		    g.drawString ("CELESTIAL BODY INFO BUBBLE:", neutronStarX3 - textX2, neutronStarY3 - textY2);
		    g.drawString ("T: 1000000 Degrees Kelvin", neutronStarX3 - textX2, neutronStarY3 - textY2 + 20);
		    g.drawString ("S: 0.001 Earth Radii", neutronStarX3 - textX2, neutronStarY3 - textY2 + 40);
		    g.drawString ("M: 700000 Earth Masses", neutronStarX3 - textX2, neutronStarY3 - textY2 + 60);
		    g.drawString ("C: Hydrogen, helium, structural atomic nuclei lattice", neutronStarX3 - textX2, neutronStarY3 - textY2 + 80);
		    g.drawString ("NOW GO BACK TO THE TEXT CONSOLE!", neutronStarX3 - textX2, neutronStarY3 - textY2 + 100);
		    g.fillOval (neutronStarX3, neutronStarY3, 20, 20); //Unknown star
		}
		if (radSuperNova1 < 5) // 3. Supernova
		{
		    foundlv3 = 3;
		    g.drawImage (supernovaImage, superNovaX3 - 28, superNovaY3 - 30, null);
		    g.drawString ("CELESTIAL BODY INFO BUBBLE:", superNovaX3 - textX3, superNovaY3 - textY3);
		    g.drawString ("T: 100000000001 Degrees Kelvin", superNovaX3 - textX3, superNovaY3 - textY3 + 20);
		    g.drawString ("S: ???? Earth Radii", superNovaX3 - textX3, superNovaY3 - textY3 + 40);
		    g.drawString ("M: ???? Earth Masses", superNovaX3 - textX3, superNovaY3 - textY3 + 60);
		    g.drawString ("C: Hydrogen, helium, iron, oxygen, gold", superNovaX3 - textX3, superNovaY3 - textY3 + 80);
		    g.drawString ("NOW GO BACK TO THE TEXT CONSOLE!", superNovaX3 - textX3, superNovaY3 - textY3 + 100);
		}

		g.setColor (Color.red);
		delay (50);

		found = true;
		c.clear ();
		lev3 = 0;

		c.println ("Hey, you found something! Now you've just got to classify it!");
		c.println ("Type in any key and <Enter> to continue: ");
		lev4 = 1;
	    }
	    else if (((radMainStar1 < 42) || (radNeutronStar1 < 38) || (radSuperNova1 < 14)) && (detector))
	    {
		Color orange = new Color (255, 140, 0);
		g.setColor (orange);
	    }


	    else if (((radMainStar1 < 70) || (radNeutronStar1 < 60) || (radSuperNova1 < 22)) && (detector))
	    {
		g.setColor (Color.yellow);
	    }


	    else
	    {
		g.setColor (Color.white);
	    }
	    //crosshair vvv
	    g.fillOval (myX - 1, myY - 1, 2, 2); //crosshair

	    g.drawLine (myX - 18, myY - 18, myX - 10, myY - 18);
	    g.drawLine (myX - 18, myY - 18, myX - 18, myY - 10);

	    g.drawLine (myX + 18, myY - 18, myX + 10, myY - 18);
	    g.drawLine (myX + 18, myY - 18, myX + 18, myY - 10);

	    g.drawLine (myX - 18, myY + 18, myX - 10, myY + 18);
	    g.drawLine (myX - 18, myY + 18, myX - 18, myY + 10);

	    g.drawLine (myX + 18, myY + 18, myX + 10, myY + 18);
	    g.drawLine (myX + 18, myY + 18, myX + 18, myY + 10);
	    //crosshair ^^^

	    g.drawLine (myX, myY, 400, 635); //Laser beam
	    g.fillArc (350, 585, 100, 100, 0, 180); //observatory
	} //END OF LEVEL 3


	else if (lev4 == 1)
	{ //START OF LEVEL 4
	    // gasGiantX4, gasGiantY4, mainStarX4,  mainStarY4, millFX4, millFY4
	    g.drawString ("Find a celestial body in this patch of the night sky!", 275, 45);
	    found = false;
	    int radgasGiant4 = (int) (Math.sqrt (Math.pow (myX - (gasGiantX4 + 12.0), 2) + Math.pow (myY - (gasGiantY4 + 12.0), 2)));
	    int radMainStar4 = (int) (Math.sqrt (Math.pow (myX - (mainStarX4 + 12.0), 2) + Math.pow (myY - (mainStarY4 + 12.0), 2)));
	    int radmillF4 = (int) (Math.sqrt (Math.pow (myX - (millFX4 + 5.0), 2) + Math.pow (myY - (millFY4 + 5.0), 2)));

	    if ((radgasGiant4 < 12) || (radMainStar4 < 12) || (radmillF4 < 5))
	    {
		//Color col = new Color (, ,);
		g.setColor (Color.black);
		g.drawString ("Find a celestial body in this patch of the night sky!", 275, 45);
		g.setColor (Color.green);
		int textX = 75, textY = 120; //default of textX = 75, textY = 120

		//Gas Giant pop-up
		if (gasGiantX4 < 75) //Left border
		{
		    textX = -30;
		}
		else if (gasGiantX4 > 680) //Right border
		{
		    textX = 170;
		}
		else
		{
		}

		if (gasGiantY4 < 170)  //Upper border
		{
		    textY = -100;
		}

		//Main Star pop-up
		int textX2 = 75, textY2 = 120; //default of textX = 75, textY = 120
		if (mainStarX4 < 75) //Left border
		{
		    textX2 = -30;
		}
		else if (mainStarX4 > 680) //Right border
		{
		    textX2 = 170;
		}
		else
		{
		}

		if (mainStarY4 < 170)  //Upper border
		{
		    textY2 = -100;
		}

		//Millennium Falcon pop-up
		int textX3 = 75, textY3 = 120; //default of textX = 75, textY = 120
		if (millFX4 < 75) //Left border
		{
		    textX3 = -30;
		}
		else if (millFX4 > 650) //Right border
		{
		    textX3 = 170;
		}
		else
		{
		}

		if (millFY4 < 170)  //Upper border
		{
		    textY3 = -100;
		}

		if (radgasGiant4 < 12) // 1. Gas Giant
		{
		    foundlv4 = 1;
		    g.drawString ("CELESTIAL BODY INFO BUBBLE:", gasGiantX4 - textX, gasGiantY4 - textY);
		    g.drawString ("T: 2800 Degrees Kelvin", gasGiantX4 - textX, gasGiantY4 - textY + 20);
		    g.drawString ("S: 30 Earth Radii", gasGiantX4 - textX, gasGiantY4 - textY + 40);
		    g.drawString ("M: 18000 Earth Masses", gasGiantX4 - textX, gasGiantY4 - textY + 60);
		    g.drawString ("C: Hydrogen, helium, iron, ammonia", gasGiantX4 - textX, gasGiantY4 - textY + 80);
		    g.drawString ("NOW GO BACK TO THE TEXT CONSOLE!", gasGiantX4 - textX, gasGiantY4 - textY + 100);
		    g.fillOval (gasGiantX4, gasGiantY4, 24, 24); //Unknown planet
		}
		if (radMainStar4 < 12) // 2. Main Sequence Star
		{
		    foundlv4 = 2;
		    g.drawString ("CELESTIAL BODY INFO BUBBLE:", mainStarX4 - textX2, mainStarY4 - textY2);
		    g.drawString ("T: 26000 Degrees Kelvin", mainStarX4 - textX2, mainStarY4 - textY2 + 20);
		    g.drawString ("S: 15000 Earth Radii", mainStarX4 - textX2, mainStarY4 - textY2 + 40);
		    g.drawString ("M: 6000000 Earth Masses", mainStarX4 - textX2, mainStarY4 - textY2 + 60);
		    g.drawString ("C: Hydrogen, helium, nitrogen, carbon, iron", mainStarX4 - textX2, mainStarY4 - textY2 + 80);
		    g.drawString ("NOW GO BACK TO THE TEXT CONSOLE!", mainStarX4 - textX2, mainStarY4 - textY2 + 100);
		    g.fillOval (mainStarX4, mainStarY4, 24, 24); //Unknown star
		}
		if (radmillF4 < 5) // 3. Millennium Falcon
		{
		    foundlv4 = 3;
		    g.drawImage (MillFal, millFX4 - 30, millFY4 - 30, null);
		    g.drawString ("CELESTIAL BODY INFO BUBBLE:", millFX4 - textX3, millFY4 - textY3);
		    g.drawString ("T: 295 Degrees Kelvin", millFX4 - textX3, millFY4 - textY3 + 20);
		    g.drawString ("S: 35 METRES", millFX4 - textX3, millFY4 - textY3 + 40);
		    g.drawString ("M: 2000 KILOGRAMS", millFX4 - textX3, millFY4 - textY3 + 60);
		    g.drawString ("C: Steel, iron, chromium, aluminum, gold, wookie fur", millFX4 - textX3, millFY4 - textY3 + 80);
		    g.drawString ("NOW GO BACK TO THE TEXT CONSOLE!", millFX4 - textX3, millFY4 - textY3 + 100);
		    //Put Millennium here!
		}

		g.setColor (Color.red);
		delay (50);

		found = true;
		c.clear ();
		lev4 = 0;

		c.println ("Hey, you found something! Now you've just got to classify it!");
		c.println ("Type in any key and <Enter> to continue: ");
		lev5 = 1;
	    }
	    else if (((radgasGiant4 < 38) || (radMainStar4 < 38) || (radmillF4 < 12)) && (detector))
	    {
		Color orange = new Color (255, 140, 0);
		g.setColor (orange);
	    }


	    else if (((radgasGiant4 < 70) || (radMainStar4 < 70) || (radmillF4 < 20)) && (detector))
	    {
		g.setColor (Color.yellow);
	    }


	    else
	    {
		g.setColor (Color.white);
	    }
	    //crosshair vvv
	    g.fillOval (myX - 1, myY - 1, 2, 2); //crosshair

	    g.drawLine (myX - 18, myY - 18, myX - 10, myY - 18);
	    g.drawLine (myX - 18, myY - 18, myX - 18, myY - 10);

	    g.drawLine (myX + 18, myY - 18, myX + 10, myY - 18);
	    g.drawLine (myX + 18, myY - 18, myX + 18, myY - 10);

	    g.drawLine (myX - 18, myY + 18, myX - 10, myY + 18);
	    g.drawLine (myX - 18, myY + 18, myX - 18, myY + 10);

	    g.drawLine (myX + 18, myY + 18, myX + 10, myY + 18);
	    g.drawLine (myX + 18, myY + 18, myX + 18, myY + 10);
	    //crosshair ^^^

	    g.drawLine (myX, myY, 400, 635); //Laser beam
	    g.fillArc (350, 585, 100, 100, 0, 180); //observatory
	} //END OF LEVEL 4


	else if (lev5 == 1)
	{
	    g.drawString ("Find a celestial body in this patch of the night sky!", 275, 45);
	    found = false;
	    /////////////////////////////////////////////////////////////////////////////////
	    int celestialObject1 = celOb1;
	    int celestialObject2 = celOb2;
	    int celestialObject3 = celOb3;

	    //{ //random object gen
	    int objectX1 = 0, objectY1 = 0, objectX2 = 0, objectY2 = 0, objectX3 = 0, objectY3 = 0;

	    switch (celestialObject1)
	    {
		case 1:
		    objectX1 = earthLikeX5;
		    objectY1 = earthLikeY5;

		    break;
		case 2:
		    objectX1 = superEarthX5;
		    objectY1 = superEarthY5;

		    break;
		case 3:
		    objectX1 = iceGiantX5;
		    objectY1 = iceGiantY5;

		    break;
		case 4:
		    objectX1 = gasGiantX5;
		    objectY1 = gasGiantY5;

		    break;
		case 5:
		    objectX1 = mainStarX5;
		    objectY1 = mainStarY5;

		    break;
		case 6:
		    objectX1 = neutronStarX5;
		    objectY1 = neutronStarY5;

		    break;
	    }

	    switch (celestialObject2)
	    {
		case 1:
		    objectX2 = earthLikeX52;
		    objectY2 = earthLikeY52;

		    break;
		case 2:
		    objectX2 = superEarthX52;
		    objectY2 = superEarthY52;

		    break;
		case 3:
		    objectX2 = iceGiantX52;
		    objectY2 = iceGiantY52;

		    break;
		case 4:
		    objectX2 = gasGiantX52;
		    objectY2 = gasGiantY52;

		    break;
		case 5:
		    objectX2 = mainStarX52;
		    objectY2 = mainStarY52;

		    break;
		case 6:
		    objectX2 = neutronStarX52;
		    objectY2 = neutronStarY52;

		    break;
	    }

	    switch (celestialObject3)
	    {
		case 1:
		    objectX3 = bHoleX5;
		    objectY3 = bHoleY5;

		    break;
		case 2:
		    objectX3 = sNovaX5;
		    objectY3 = sNovaY5;

		    break;
		case 3:
		    objectX3 = mFalcX5;
		    objectY3 = mFalcY5;

		    break;
	    }

	    //} //Random object gen

	    int radObject1 = (int) (Math.sqrt (Math.pow (myX - (objectX1 + 12.0), 2) + Math.pow (myY - (objectY1 + 12.0), 2)));
	    int radObject2 = (int) (Math.sqrt (Math.pow (myX - (objectX2 + 12.0), 2) + Math.pow (myY - (objectY2 + 12.0), 2)));
	    int radObject3 = (int) (Math.sqrt (Math.pow (myX - (objectX3 + 5.0), 2) + Math.pow (myY - (objectY3 + 5.0), 2)));

	    if ((radObject1 < 12) || (radObject2 < 12) || (radObject3 < 5))
	    {
		//Color col = new Color (, ,);
		g.setColor (Color.black);
		g.drawString ("Find a celestial body in this patch of the night sky!", 275, 45);
		g.setColor (Color.green);
		int textX = 75, textY = 120; //default of textX = 75, textY = 120

		//object 1 pop-up
		if (objectX1 < 75) //Left border
		{
		    textX = -30;
		}
		else if (objectX1 > 680) //Right border
		{
		    textX = 170;
		}
		else
		{
		}

		if (objectY1 < 170)  //Upper border
		{
		    textY = -100;
		}

		//object 2 pop-up
		int textX2 = 75, textY2 = 120; //default of textX = 75, textY = 120
		if (objectX2 < 75) //Left border
		{
		    textX2 = -30;
		}
		else if (objectX2 > 680) //Right border
		{
		    textX2 = 170;
		}
		else
		{
		}

		if (objectY2 < 170)  //Upper border
		{
		    textY2 = -100;
		}

		//object 3 pop-up
		int textX3 = 75, textY3 = 120; //default of textX = 75, textY = 120
		if (objectX3 < 75) //Left border
		{
		    textX3 = -30;
		}
		else if (objectX3 > 680) //Right border
		{
		    textX3 = 170;
		}
		else
		{
		}

		if (objectY3 < 170)  //Upper border
		{
		    textY3 = -100;
		}

		if (radObject1 < 12) //object 1
		{
		    double temp, size, mass;
		    g.setColor (Color.green);
		    switch (celestialObject1)
		    {
			case 1: //Earth-like
			    foundlv5 = 1;
			    temp = (int) (Math.random () * 101 + 223);
			    size = ((int) (Math.random () * 16 + 5)) / 10.0;
			    mass = ((int) (Math.random () * 12 + 8)) / 10.0;
			    g.drawString ("CELESTIAL BODY INFO BUBBLE:", objectX1 - textX, objectY1 - textY);
			    g.drawString ("T: " + temp + " Degrees Kelvin", objectX1 - textX, objectY1 - textY + 20);
			    g.drawString ("S: " + size + " Earth Radii", objectX1 - textX, objectY1 - textY + 40);
			    g.drawString ("M: " + mass + " Earth Masses", objectX1 - textX, objectY1 - textY + 60);
			    g.drawString ("C: silicate rock, iron, and carbon", objectX1 - textX, objectY1 - textY + 80);
			    break;
			case 2: //Super Earth
			    foundlv5 = 2;
			    temp = (int) (Math.random () * 101 + 223);
			    size = ((int) (Math.random () * 21 + 20)) / 10.0;
			    mass = ((int) (Math.random () * 81 + 20)) / 10.0;
			    g.drawString ("CELESTIAL BODY INFO BUBBLE:", objectX1 - textX, objectY1 - textY);
			    g.drawString ("T: " + temp + " Degrees Kelvin", objectX1 - textX, objectY1 - textY + 20);
			    g.drawString ("S: " + size + " Earth Radii", objectX1 - textX, objectY1 - textY + 40);
			    g.drawString ("M: " + mass + " Earth Masses", objectX1 - textX, objectY1 - textY + 60);
			    g.drawString ("C: silicate rock, sulphur, iron", objectX1 - textX, objectY1 - textY + 80);
			    break;
			case 3: //Ice Giant
			    foundlv5 = 3;
			    temp = (int) (Math.random () * 121 + 30);
			    size = ((int) (Math.random () * 51 + 40)) / 10.0;
			    mass = ((int) (Math.random () * 401 + 100)) / 10.0;
			    g.drawString ("CELESTIAL BODY INFO BUBBLE:", objectX1 - textX, objectY1 - textY);
			    g.drawString ("T: " + temp + " Degrees Kelvin", objectX1 - textX, objectY1 - textY + 20);
			    g.drawString ("S: " + size + " Earth Radii", objectX1 - textX, objectY1 - textY + 40);
			    g.drawString ("M: " + mass + " Earth Masses", objectX1 - textX, objectY1 - textY + 60);
			    g.drawString ("C: oxygen, carbon, nitrogen", objectX1 - textX, objectY1 - textY + 80);
			    break;
			case 4: //Gas Giant
			    foundlv5 = 4;
			    int coin = (int) (Math.random () * 100 + 1);
			    if (coin <= 50)
			    {
				temp = (int) (Math.random () * 101 + 150);
			    }
			    else
			    {
				temp = (int) (Math.random () * 6651 + 350);
			    }
			    size = ((int) (Math.random () * 241 + 90)) / 10.0;
			    mass = (int) (Math.random () * 19951 + 50);
			    g.drawString ("CELESTIAL BODY INFO BUBBLE:", objectX1 - textX, objectY1 - textY);
			    g.drawString ("T: " + temp + " Degrees Kelvin", objectX1 - textX, objectY1 - textY + 20);
			    g.drawString ("S: " + size + " Earth Radii", objectX1 - textX, objectY1 - textY + 40);
			    g.drawString ("M: " + mass + " Earth Masses", objectX1 - textX, objectY1 - textY + 60);
			    g.drawString ("C: hydrogen, helium, methane, chlorides", objectX1 - textX, objectY1 - textY + 80);
			    break;
			case 5: //Main Star
			    foundlv5 = 5;
			    temp = (int) (Math.random () * 37401 + 2600);
			    size = ((int) (Math.random () * 1899871 + 130)) / 10.0;
			    mass = (int) (Math.random () * 10497501 + 2500);
			    g.drawString ("CELESTIAL BODY INFO BUBBLE:", objectX1 - textX, objectY1 - textY);
			    g.drawString ("T: " + temp + " Degrees Kelvin", objectX1 - textX, objectY1 - textY + 20);
			    g.drawString ("S: " + size + " Earth Radii", objectX1 - textX, objectY1 - textY + 40);
			    g.drawString ("M: " + mass + " Earth Masses", objectX1 - textX, objectY1 - textY + 60);
			    g.drawString ("C: hydrogen, helium, nitrogen, carbon, neon", objectX1 - textX, objectY1 - textY + 80);
			    break;
			case 6: //Neutron Star
			    foundlv5 = 6;
			    int tempInt = (int) (Math.random () * 999991 + 1000);
			    size = ((int) (Math.random () * 31 + 8)) / 10000.0;
			    mass = ((int) (Math.random () * 401 + 100)) / 10.0;
			    g.drawString ("CELESTIAL BODY INFO BUBBLE:", objectX1 - textX, objectY1 - textY);
			    g.drawString ("T: " + tempInt + "00000 Degrees Kelvin", objectX1 - textX, objectY1 - textY + 20);
			    g.drawString ("S: " + size + " Earth Radii", objectX1 - textX, objectY1 - textY + 40);
			    g.drawString ("M: " + mass + " Earth Masses", objectX1 - textX, objectY1 - textY + 60);
			    g.drawString ("C: oxygen, carbon, nitrogen", objectX1 - textX, objectY1 - textY + 80);
			    break;
		    }
		    g.drawString ("NOW GO BACK TO THE TEXT CONSOLE!", objectX1 - textX, objectY1 - textY + 100);
		    g.fillOval (objectX1, objectY1, 24, 24); //Unknown planet
		}
		g.setColor (Color.red);
		delay (50);

		found = true;
		c.clear ();

		c.println ("Hey, you found something! Now you've just got to classify it!");
		c.println ("Type in any key and <Enter> to continue: ");


		if (radObject2 < 12) // object 2
		{
		    g.setColor (Color.green);
		    double temp, size, mass;

		    switch (celestialObject2)
		    {
			case 1: //Earth-like
			    foundlv5 = 1;
			    temp = (int) (Math.random () * 101 + 223);
			    size = ((int) (Math.random () * 16 + 5)) / 10.0;
			    mass = ((int) (Math.random () * 12 + 8)) / 10.0;
			    g.drawString ("CELESTIAL BODY INFO BUBBLE:", objectX2 - textX2, objectY2 - textY2);
			    g.drawString ("T: " + temp + " Degrees Kelvin", objectX2 - textX2, objectY2 - textY2 + 20);
			    g.drawString ("S: " + size + " Earth Radii", objectX2 - textX2, objectY2 - textY2 + 40);
			    g.drawString ("M: " + mass + " Earth Masses", objectX2 - textX2, objectY2 - textY2 + 60);
			    g.drawString ("C: silicate rock, iron, and carbon", objectX2 - textX2, objectY2 - textY2 + 80);
			    break;
			case 2: //Super Earth
			    foundlv5 = 2;
			    temp = (int) (Math.random () * 101 + 223);
			    size = ((int) (Math.random () * 21 + 20)) / 10.0;
			    mass = ((int) (Math.random () * 81 + 20)) / 10.0;
			    g.drawString ("CELESTIAL BODY INFO BUBBLE:", objectX2 - textX2, objectY2 - textY2);
			    g.drawString ("T: " + temp + " Degrees Kelvin", objectX2 - textX2, objectY2 - textY2 + 20);
			    g.drawString ("S: " + size + " Earth Radii", objectX2 - textX2, objectY2 - textY2 + 40);
			    g.drawString ("M: " + mass + " Earth Masses", objectX2 - textX2, objectY2 - textY2 + 60);
			    g.drawString ("C: silicate rock, sulphur, iron", objectX2 - textX2, objectY2 - textY2 + 80);
			    break;
			case 3: //Ice Giant
			    foundlv5 = 3;
			    temp = (int) (Math.random () * 121 + 30);
			    size = ((int) (Math.random () * 51 + 40)) / 10.0;
			    mass = ((int) (Math.random () * 401 + 100)) / 10.0;
			    g.drawString ("CELESTIAL BODY INFO BUBBLE:", objectX2 - textX2, objectY2 - textY2);
			    g.drawString ("T: " + temp + " Degrees Kelvin", objectX2 - textX2, objectY2 - textY2 + 20);
			    g.drawString ("S: " + size + " Earth Radii", objectX2 - textX2, objectY2 - textY2 + 40);
			    g.drawString ("M: " + mass + " Earth Masses", objectX2 - textX2, objectY2 - textY2 + 60);
			    g.drawString ("C: oxygen, carbon, nitrogen", objectX2 - textX2, objectY2 - textY2 + 80);
			    break;
			case 4: //Gas Giant
			    foundlv5 = 4;
			    int coin = (int) (Math.random () * 100 + 1);
			    if (coin <= 50)
			    {
				temp = (int) (Math.random () * 101 + 150);
			    }
			    else
			    {
				temp = (int) (Math.random () * 6651 + 350);
			    }
			    size = ((int) (Math.random () * 241 + 90)) / 10.0;
			    mass = (int) (Math.random () * 19951 + 50);
			    g.drawString ("CELESTIAL BODY INFO BUBBLE:", objectX2 - textX2, objectY2 - textY2);
			    g.drawString ("T: " + temp + " Degrees Kelvin", objectX2 - textX2, objectY2 - textY2 + 20);
			    g.drawString ("S: " + size + " Earth Radii", objectX2 - textX2, objectY2 - textY2 + 40);
			    g.drawString ("M: " + mass + " Earth Masses", objectX2 - textX2, objectY2 - textY2 + 60);
			    g.drawString ("C: hydrogen, helium, methane, chlorides", objectX2 - textX2, objectY2 - textY2 + 80);
			    break;
			case 5: //Main Star
			    foundlv5 = 5;
			    temp = (int) (Math.random () * 37401 + 2600);
			    size = ((int) (Math.random () * 1899871 + 130)) / 10.0;
			    mass = (int) (Math.random () * 10497501 + 2500);
			    g.drawString ("CELESTIAL BODY INFO BUBBLE:", objectX2 - textX2, objectY2 - textY2);
			    g.drawString ("T: " + temp + " Degrees Kelvin", objectX2 - textX2, objectY2 - textY2 + 20);
			    g.drawString ("S: " + size + " Earth Radii", objectX2 - textX2, objectY2 - textY2 + 40);
			    g.drawString ("M: " + mass + " Earth Masses", objectX2 - textX2, objectY2 - textY2 + 60);
			    g.drawString ("C: hydrogen, helium, nitrogen, carbon, neon", objectX2 - textX2, objectY2 - textY2 + 80);
			    break;
			case 6: //Neutron Star
			    foundlv5 = 6;
			    int tempInt = (int) (Math.random () * 999991 + 1000);
			    size = ((int) (Math.random () * 31 + 8)) / 10000.0;
			    mass = ((int) (Math.random () * 401 + 100)) / 10.0;
			    g.drawString ("CELESTIAL BODY INFO BUBBLE:", objectX2 - textX2, objectY2 - textY2);
			    g.drawString ("T: " + tempInt + "00000 Degrees Kelvin", objectX2 - textX2, objectY2 - textY2 + 20);
			    g.drawString ("S: " + size + " Earth Radii", objectX2 - textX2, objectY2 - textY2 + 40);
			    g.drawString ("M: " + mass + " Earth Masses", objectX2 - textX2, objectY2 - textY2 + 60);
			    g.drawString ("C: oxygen, carbon, nitrogen", objectX2 - textX2, objectY2 - textY2 + 80);
			    break;
		    }
		    g.drawString ("NOW GO BACK TO THE TEXT CONSOLE!", objectX2 - textX2, objectY2 - textY2 + 100);
		    g.fillOval (objectX2, objectY2, 24, 24); //Unknown planet
		}
		g.setColor (Color.red);
		delay (50);

		found = true;
		c.clear ();

		c.println ("Hey, you found something! Now you've just got to classify it!");
		c.println ("Type in any key and <Enter> to continue: ");

		if (radObject3 < 12) // object 3
		{
		    g.setColor (Color.green);
		    double temp, size, mass;

		    switch (celestialObject3)
		    {
			case 1: //black hole
			    foundlv5 = 7;
			    temp = ((int) (Math.random () * 101 + 1)) / 10000000000.0;
			    size = ((int) (Math.random () * 9400000 + 1)) / 10.0;
			    int massInt = ((int) (Math.random () * 70000000 + 366));
			    g.drawString ("CELESTIAL BODY INFO BUBBLE:", objectX3 - textX3, objectY3 - textY3);
			    g.drawString ("T: " + temp + " Degrees Kelvin", objectX3 - textX3, objectY3 - textY3 + 20);
			    int coin = (int) (Math.random () * 100 + 1);
			    if (coin <= 50)
			    {
				g.drawString ("S: " + size + " mm", objectX3 - textX3, objectY3 - textY3 + 40);
			    }
			    else
			    {
				g.drawString ("S: " + size + " Earth Radii", objectX3 - textX3, objectY3 - textY3 + 40);
			    }
			    g.drawString ("M: " + massInt + "000000 Earth Masses", objectX3 - textX3, objectY3 - textY3 + 60);
			    g.drawString ("C: Hydrogen cyanide", objectX3 - textX3, objectY3 - textY3 + 80);
			    break;
			case 2: //Supernova
			    foundlv5 = 8;
			    size = ((int) (Math.random () * 21 + 20)) / 10.0;
			    mass = ((int) (Math.random () * 81 + 20)) / 10.0;
			    g.drawImage (supernovaImage, objectX3 - 28, objectY3 - 30, null);
			    g.drawString ("CELESTIAL BODY INFO BUBBLE:", objectX3 - textX3, objectY3 - textY3);
			    g.drawString ("T: 100000000000 Degrees Kelvin", objectX3 - textX3, objectY3 - textY3 + 20);
			    g.drawString ("S: ??? Earth Radii", objectX3 - textX3, objectY3 - textY3 + 40);
			    g.drawString ("M: ??? Earth Masses", objectX3 - textX3, objectY3 - textY3 + 60);
			    g.drawString ("C: Hydrogen, helium, gold, oxygen", objectX3 - textX3, objectY3 - textY3 + 80);

			    break;
			case 3: //millennium falcon
			    foundlv5 = 9;
			    temp = (int) (Math.random () * 21 + 283);
			    g.drawString ("CELESTIAL BODY INFO BUBBLE:", objectX3 - textX3, objectY3 - textY3);
			    g.drawString ("T: " + temp + " Degrees Kelvin", objectX3 - textX3, objectY3 - textY3 + 20);
			    g.drawString ("S: 35 metres", objectX3 - textX3, objectY3 - textY3 + 40);
			    g.drawString ("M: 2000 kg Earth Masses", objectX3 - textX3, objectY3 - textY3 + 60);
			    g.drawString ("C: Steel, iron, titanium, chromium, silver, gold, wookie fur, aluminum", objectX3 - textX3, objectY3 - textY3 + 80);
			    //millennium falcon
			    break;
		    }
		    g.drawString ("NOW GO BACK TO THE TEXT CONSOLE!", objectX3 - textX3, objectY3 - textY3 + 100);
		}
		g.setColor (Color.red);
		delay (50);

		found = true;
		c.clear ();

		c.println ("Hey, you found something! Now you've just got to classify it!");
		c.println ("Type in any key and <Enter> to continue: ");
	    }
	    else if (((radObject1 < 38) || (radObject2 < 38) || (radObject3 < 12)) && (detector))
	    {
		Color orange = new Color (255, 140, 0);
		g.setColor (orange);
	    }


	    else if (((radObject1 < 70) || (radObject2 < 70) || (radObject3 < 20)) && (detector))
	    {
		g.setColor (Color.yellow);
	    }


	    else
	    {
		g.setColor (Color.white);
	    }


	    //crosshair vvv
	    g.fillOval (myX - 1, myY - 1, 2, 2); //crosshair

	    g.drawLine (myX - 18, myY - 18, myX - 10, myY - 18);
	    g.drawLine (myX - 18, myY - 18, myX - 18, myY - 10);

	    g.drawLine (myX + 18, myY - 18, myX + 10, myY - 18);
	    g.drawLine (myX + 18, myY - 18, myX + 18, myY - 10);

	    g.drawLine (myX - 18, myY + 18, myX - 10, myY + 18);
	    g.drawLine (myX - 18, myY + 18, myX - 18, myY + 10);

	    g.drawLine (myX + 18, myY + 18, myX + 10, myY + 18);
	    g.drawLine (myX + 18, myY + 18, myX + 18, myY + 10);
	    //crosshair ^^^

	    g.drawLine (myX, myY, 400, 635); //Laser beam
	    g.fillArc (350, 585, 100, 100, 0, 180); //observatory
	}
    } // Planet detection method


    public void moveIt (KeyEvent evt)
    {
	if (myX < 10)
	    myX += 25;
	else if (myY < 10)
	    myY += 25;
	else if (myX > 775)
	    myX -= 25;
	else if (myY > 550)
	    myY -= 25;
	else
	{
	    switch (evt.getKeyCode ())
	    {
		case KeyEvent.VK_DOWN:
		    myY += 15;
		    break;
		case KeyEvent.VK_UP:
		    myY -= 15;
		    break;
		case KeyEvent.VK_LEFT:
		    myX -= 15;
		    break;
		case KeyEvent.VK_RIGHT:
		    myX += 15;
		    break;
		case KeyEvent.VK_S:
		    myY += 1;
		    break;
		case KeyEvent.VK_W:
		    myY -= 1;
		    break;
		case KeyEvent.VK_A:
		    myX -= 1;
		    break;
		case KeyEvent.VK_D:
		    myX += 1;
		    break;
	    }
	    repaint ();
	}
    }


    /////// Arrow keys method ////////////////////////////////////////////////////////////////


} // NewEarth class
