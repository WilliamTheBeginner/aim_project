package src; //package this file in source

import arc.*;
import src.*;
import java.util.Arrays; //declare java array functionality
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.Font;



public class Menu
{
  // declare varible for total score
  int intScore = 0;

  // declare constructor
  public Menu(Console con)
  {
    // call on displayMain method to display the menu
    displayMain(con);

    // listen for mouse input
    while(true)
    {
      //declare mouse coordinates in console
      int intMouseX = con.currentMouseX();
      int intMouseY = con.currentMouseY();

      //if the user clicks on the left mouse button
      if(con.currentMouseButton() == 1)
      {
        // call on menuClick to find where the user clicked. Different cases correspond to different buttons
        switch(menuClick(con))
        {
          case 1: // if the user clicks READY
            // open settings file
            TextInputFile infile = new TextInputFile("Settings.txt");

            // declare each variable that's being taken from settings
            int intRadius = 0;
            int intSeconds = 0;
            int intGun = 0;

            // read the variables
            intRadius = infile.readInt();
            intSeconds = infile.readInt();
            intGun = infile.readInt();

            // since the user clicked READy, clear Console menu
            clearConsoleMenu(con);

            // set a timer for 3 seconds... to get the user ready for the game
            for(int i = 1; i <= 3; i++){
              //tiemr for 3 intSeconds
              con.print(i + " ");
              con.sleep(1000);
            }

            // open a new console for the timer. Optional: the console screen will be behind the actual game.
            Console timerConsole = new Console(200, 100);
            // intialize a new timer. Pass in the console as an argument.
            Timer timer = new Timer(timerConsole);

            // from the settings file, select which timer will be used.
            if(intSeconds == 30){
              timer.addDur(intSeconds - 1); // set duration
              timer.start(); // start the timer
              Timer30 t30 = new Timer30(timer); // start a 30 seconds countdown
              t30.start(); // start the 30 second countdown alongside the parent timer
            } else {
              timer.addDur(intSeconds - 1); // set duration
              timer.start(); // start the timer
              Timer60 t60 = new Timer60(timer); // start a 60 seconds countdown
              t60.start(); // start the 60 second countdown alongside the parent timer
            }

            boolean bln_intGun; // gun setting. If true, the console will refresh. If not, there will only be one console throughout

            if(intGun == 0){
              bln_intGun = true;
            } else {
              bln_intGun = false;
            }

            displayCircleGame(intRadius, con, bln_intGun, timer); // display the circle game. Pass in each parameter as specified above

            displayEnding(); // display the ending of the game.

            // open a new console and start a new menu
            con = new Console(1920, 1080);

            Menu menu = new Menu(con);

          case 2:
            // display the options using the method. Pass in the console as an argument
            displayOptions(con);

            // sleep for 0.1 seconds so that when the user clicks back, it doens't interfere with the quit button right underneath it
            con.sleep(100);

            // start a new menu
            menu = new Menu(con);
          case 3:
            // display help screen using the help method
            displayHelp(con);

            // sleep for 300 milliseconds so that it doesn't interfere with the button under
            con.sleep(300);

            // start a new menu
            menu = new Menu(con);
          case 4:
            // open a new console for credits and print out all credits
            Console credits = new Console();
            credits.println("Developer: William Kwan");
            credits.println("Inspired by: Mr. Astorino");
            credits.println("Special thanks to Alfred Cadawas for the ARC library");
            // sleep for 5 seconds to wait for user. Close console right after.
            credits.sleep(5000);
            credits.closeConsole();
        }
      }
    }

  }

  public void displayCircleGame(int intRadius, Console con, boolean blnReset,Timer timer)
  {
    // keep checking for timer status. While the user is clicking on circles, keep spawning them.
    while(true){
      if(timer.isAlive() == false){
        con.closeWindow();
        return;
      }

      Circle cir = new Circle(intRadius);
      if(blnReset){
        con.closeWindow();
        con = new Console(1920, 1080);
      }
      cir.draw(con);
      con.repaint();
      // print out array of radius
      con.println(Arrays.toString(cir.getRadXY()));
      //method returns after user has clicked on the circle
      clickCircle(cir, con);
      // add score
      intScore++;


    }
  }
  public void displayEnding(){
    // open a new console
	  Console cons = new Console(1920, 1080);
    // load fortnite font
    Font font = cons.loadFont("Burbank.ttf", 100);
    // repaint console
	  cons.repaint();
    // load image, draw font and image
	  BufferedImage end = cons.loadImage("final.jpg");
    cons.setDrawFont(font);
	  cons.drawImage(end, 0, 0);
    // draw score
	  cons.drawString(Integer.toString(intScore), 257, 330);
	  cons.repaint();
    // listen for mouse input
	  while(true){
      // get co ordingates
		  int intMouseX = cons.currentMouseX();
		  int intMouseY = cons.currentMouseY();
      // if the user clicks on the left button, and the user is on RETURN to lobby
		  if(cons.currentMouseButton() == 1){
			  if(between(intMouseX, 1524, 1916) && between(intMouseY, 980, 1079)){
				  cons.closeWindow();
          // get out of screen
				  return;
			  }
		  }
	  }
  }

  public void displayHelp(Console con){
    // load image and draw image
	  BufferedImage help = con.loadImage("help.jpg");
	  con.drawImage(help, 0, 0);
	  con.repaint();
	  while(true){
      // if the user clicks back, return no value
		  int intMouseX = con.currentMouseX();
		  int intMouseY = con.currentMouseY();
		  if(con.currentMouseButton() == 1){
			  if(between(intMouseX, 1519, 1913) && between(intMouseY, 978, 1077)){
				  return;
				}

			}
		}
	}
  // display the main menu
  public void displayMain(Console con)
  {
	con.repaint();
  // draw the menu image
	BufferedImage mainmenu = con.loadImage("mainmenu.jpg");
	con.drawImage(mainmenu, 0, 0);
	con.repaint();
  // initialize new classes of buttons. Each with their respective variables
    Button start = new Button(305, 590, 545, 216);
    Button options = new Button(285, 858, 585, 185);
    Button help = new Button(1025, 595, 635, 205);
    Button quit = new Button(1080, 830, 525, 200);

    // draw each into the console
    start.draw(con);
    options.draw(con);
    help.draw(con);
    quit.draw(con);
  }
  public boolean between(int i, int min, int max)
  {
    if(i >= min && i <= max) // if your i is in between of your min and max
    {
      return true;
    }
    else
    {
      return false;
    }
  }
  public static boolean clickCircle(Circle circle, Console con)
  {
	  int [] radarr = new int[2]; // initialize a new array with the co-coordinates

	  radarr = circle.getRadXY(); // get the co ordinates from the circle.

    // set each variable
	  int radX = radarr[0];
	  int radY = radarr[1];

    // print the radius co ordinates
    con.println(radX);
    con.println(radY);

    // print the radius size
    con.println(circle.getRadius());

    // liste for mouse input
	  while(true)
	  {
      if(con.currentMouseButton() == 1)
		  {
        // if the user clicks on the circle
			  if(((Math.pow((con.currentMouseX() - radX), 2) + Math.pow((con.currentMouseY() - radY), 2)) < Math.pow(circle.getRadius(), 2)))
			  {
          // clear the console to make room for the next circle
				  clearConsoleMenu(con);
				  return true;
			  }
		  }
	  }
  }
  public static void clearConsoleMenu(Console con)
  {
    // paste a black retangle over the entire console
    con.setDrawColor(Color.BLACK);
    con.fillRect(0, 0, 1920, 1080);
    con.repaint();
  }
  public void displayOptions(Console con){
    // display options image
    BufferedImage options = con.loadImage("options_updated.jpg");
	  con.drawImage(options, 0, 0);
	  con.repaint();
    // initialize options variables
	  int intDuration = 0, intRadius = 0, intGun = 0;

    // open file and get variables from the file
	  TextInputFile infile = new TextInputFile("Settings.txt");

    intRadius = infile.readInt();
    intDuration = infile.readInt();
    intGun = infile.readInt();

    // initalize all proper radius varibles.
    // find teh index of which ther current radius is
	  Integer [] rad_arr = {30, 60, 75, 100};
	  int curRadIndex = Arrays.binarySearch(rad_arr, intRadius);

    // initalize all proper duration variables
    // find the index of which the currrent duration is
	  Integer [] dur_arr = {30, 60};
	  int curDurIndex = Arrays.binarySearch(dur_arr, intDuration);
    // load images for covers
	  BufferedImage cover = con.loadImage("settings_fill.jpg");
    BufferedImage intGun_cover = con.loadImage("gun_fill.jpg");

    //get the correspondant integers and turn them to strings
	  String strRadius = Integer.toString(rad_arr[curRadIndex]);
	  String strDuration = Integer.toString(dur_arr[curDurIndex]);
    String strGun = "Selected";

    // drawstring on which gun is selected
    if(intGun == 0){
      con.drawString("Selected", 262, 903);
    }
    else{
      con.drawString("Selected", 648, 897);
    }

    // draw string on which radius is selected
	  con.drawString(strRadius, 863, 356);
	  con.drawString(strDuration, 863, 512);

	  con.repaint();

    
	  while(true){

		 int intMouseX = con.currentMouseX();
	     int intMouseY = con.currentMouseY();
	     if(con.currentMouseButton() == 1){
			 if(between(intMouseX, 1093, 1212) && between(intMouseY, 345, 402)){
				 strRadius = Integer.toString(rad_arr[curRadIndex]);
				 con.drawImage(cover, 863, 356);
				 con.repaint();
				 con.drawString(strRadius, 863, 356);
				 con.repaint();
				 intRadius = rad_arr[curRadIndex];
				 con.sleep(100);
				 curRadIndex = (curRadIndex + 1) % rad_arr.length;
			 }
			 if(between(intMouseX, 1092, 1212) && between(intMouseY, 502, 553)){
				 strDuration = Integer.toString(dur_arr[curDurIndex]);
				 con.drawImage(cover, 863, 509);
				 con.repaint();
				 con.drawString(strDuration, 863, 512);
				 con.repaint();
				 intDuration = dur_arr[curDurIndex];
				 con.sleep(100);
				 curDurIndex = (curDurIndex + 1) % dur_arr.length;
			 }
       if(between(intMouseX, 256, 484) && between(intMouseY, 637, 822)){
         con.drawString(strGun, 262, 903);
         con.drawImage(intGun_cover, 648, 903);
         con.repaint();
         intGun = 0;
         con.sleep(100);

       }
       if(between(intMouseX, 628, 972) && between(intMouseY, 646, 829)){
         con.drawString(strGun, 648, 903);
         con.drawImage(intGun_cover, 262, 903);
         con.repaint();
         intGun = 1;
         con.sleep(100);
       }
			 if(between(intMouseX, 1139, 1504) && between(intMouseY, 933, 1078)){
				 TextOutputFile outfile = new TextOutputFile("Settings.txt", false);
				 outfile.println(intRadius);
				 outfile.println(intDuration);
         outfile.println(intGun);
			 }
			 if(between(intMouseX, 1505, 1913) && between(intMouseY, 932, 1077)){
				 return;
			 }
		 }
	  }

  }



  public int menuClick(Console con)
  {
    while (true)
    {
      int intMouseX = con.currentMouseX();
      int intMouseY = con.currentMouseY();

      if(con.currentMouseButton() == 1)
      {
        if(between(intMouseX, 305, 850) && between(intMouseY, 590, 806))
        {
          return 1;
        }
        else if(between(intMouseX, 285, 870) && between(intMouseY, 858, 1043))
        {
          return 2;
        }
        else if(between(intMouseX, 1025, 1660) && between(intMouseY, 595, 800))
        {
          return 3;
        }
        else if(between(intMouseX, 1080, 1605) && between(intMouseY, 830, 1130))
        {
          return 4;
        }
      }
    }
  }
}
