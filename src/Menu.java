package src;

import arc.*;
import src.*;
import java.util.Arrays;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.Font;



public class Menu
{
  int score = 0;

  public Menu(Console con)
  {
    displayMain(con);

    while(true)
    {
      int mouseX = con.currentMouseX();
      int mouseY = con.currentMouseY();

      if(con.currentMouseButton() == 1)
      {
        switch(menuClick(con))
        {
          case 1:
            TextInputFile infile = new TextInputFile("Settings.txt");

            int radius = 0;
            int seconds = 0;
            int gun = 0;

            radius = infile.readInt();
            seconds = infile.readInt();
            gun = infile.readInt();

            clearConsoleMenu(con);

            con.println("move console");

            for(int i = 1; i <= 3; i++){
              //tiemr for 3 seconds
              con.print(i + " ");
              con.sleep(1000);
            }

            Console timerConsole = new Console(200, 100);
            Timer timer = new Timer(timerConsole);

            if(seconds == 30){
              timer.addDur(seconds - 1);
              timer.start();
              Timer30 t30 = new Timer30(timer);
              t30.start();
            } else {
              timer.addDur(seconds - 1);
              timer.start();
              Timer60 t60 = new Timer60(timer);
              t60.start();
            }

            boolean bln_gun;

            if(gun == 0){
              bln_gun = true;
            } else {
              bln_gun = false;
            }

            displayCircleGame(radius, con, bln_gun, timer);

            displayEnding();

            con = new Console(1920, 1080);

            Menu menu = new Menu(con);

          case 2:
            displayOptions(con);

            con.sleep(100);

            menu = new Menu(con);
          case 3:
            displayHelp(con);

            con.sleep(300);

            menu = new Menu(con);
          case 4:
            Console credits = new Console();
            credits.println("Developer: William Kwan");
            credits.println("Inspired by: Mr. Astorino");
            credits.println("Special thanks to Alfred Cadawas for the ARC library");
            credits.sleep(5000);
            credits.closeConsole();
        }
      }
    }

  }

  public void displayCircleGame(int radius, Console con, boolean reset,Timer timer)
  {
    while(true){
      if(timer.isAlive() == false){
        con.closeWindow();
        return;
      }

      Circle cir = new Circle(radius);
      if(reset){
        con.closeWindow();
        con = new Console(1920, 1080);
      }
      cir.draw(con);
      con.repaint();
      con.println(Arrays.toString(cir.getRadXY()));
      clickCircle(cir, con);
      score++;


    }
  }
  public void displayEnding(){
	  Console cons = new Console(1920, 1080);
    Font font = cons.loadFont("Burbank.ttf", 100);
	  cons.repaint();
	  BufferedImage end = cons.loadImage("final.jpg");
    cons.setDrawFont(font);
	  cons.drawImage(end, 0, 0);
	  cons.drawString(Integer.toString(score), 257, 330);
	  cons.repaint();
	  while(true){
		  int mouseX = cons.currentMouseX();
		  int mouseY = cons.currentMouseY();
		  if(cons.currentMouseButton() == 1){
			  if(between(mouseX, 1524, 1916) && between(mouseY, 980, 1079)){
				  cons.closeWindow();
				  return;
			  }
		  }
	  }
  }

  public void displayHelp(Console con){
	  BufferedImage help = con.loadImage("help.jpg");
	  con.drawImage(help, 0, 0);
	  con.repaint();
	  while(true){
		  int mouseX = con.currentMouseX();
		  int mouseY = con.currentMouseY();
		  if(con.currentMouseButton() == 1){
			  if(between(mouseX, 1519, 1913) && between(mouseY, 978, 1077)){
				  return;
				}

			}
		}
	}
  public void displayMain(Console con)
  {
	con.repaint();
	BufferedImage mainmenu = con.loadImage("mainmenu.jpg");
	con.drawImage(mainmenu, 0, 0);
	con.repaint();
    Button start = new Button(305, 590, 545, 216);
    Button options = new Button(285, 858, 585, 185);
    Button help = new Button(1025, 595, 635, 205);
    Button quit = new Button(1080, 830, 525, 200);

    start.draw(con);
    options.draw(con);
    help.draw(con);
    quit.draw(con);
  }
  public boolean between(int i, int min, int max)
  {
    if(i >= min && i <= max)
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
	  int [] radarr = new int[2];

	  radarr = circle.getRadXY();

	  int radX = radarr[0];
	  int radY = radarr[1];

    con.println(radX);
    con.println(radY);

    con.println(circle.getRadius());

	  while(true)
	  {
      if(con.currentMouseButton() == 1)
		  {
			  if(((Math.pow((con.currentMouseX() - radX), 2) + Math.pow((con.currentMouseY() - radY), 2)) < Math.pow(circle.getRadius(), 2)))
			  {
				  clearConsoleMenu(con);
				  return true;
			  }
		  }
	  }
  }
  public static void clearConsoleMenu(Console con)
  {
    con.setDrawColor(Color.BLACK);
    con.fillRect(0, 0, 1920, 1080);
    con.repaint();
  }
  public void displayOptions(Console con){
	  BufferedImage options = con.loadImage("options_updated.jpg");
	  con.drawImage(options, 0, 0);
	  con.repaint();
	  int duration = 0, radius = 0, gun = 0;

	  TextInputFile infile = new TextInputFile("Settings.txt");

    radius = infile.readInt();
    duration = infile.readInt();
    gun = infile.readInt();

	  Integer [] rad_arr = {30, 60, 75, 100};
	  int curRadIndex = Arrays.binarySearch(rad_arr, radius);

	  Integer [] dur_arr = {30, 60};
	  int curDurIndex = Arrays.binarySearch(dur_arr, duration);
	  BufferedImage cover = con.loadImage("settings_fill.jpg");
    BufferedImage gun_cover = con.loadImage("gun_fill.jpg");

	  String strRadius = Integer.toString(rad_arr[curRadIndex]);
	  String strDuration = Integer.toString(dur_arr[curDurIndex]);
    String strGun = "Selected";

    if(gun == 0){
      con.drawString("Selected", 262, 903);
    }
    else{
      con.drawString("Selected", 648, 897);
    }

	  con.drawString(strRadius, 863, 356);
	  con.drawString(strDuration, 863, 512);

	  con.repaint();

	  while(true){

		 int mouseX = con.currentMouseX();
	     int mouseY = con.currentMouseY();
	     if(con.currentMouseButton() == 1){
			 if(between(mouseX, 1093, 1212) && between(mouseY, 345, 402)){
				 strRadius = Integer.toString(rad_arr[curRadIndex]);
				 con.drawImage(cover, 863, 356);
				 con.repaint();
				 con.drawString(strRadius, 863, 356);
				 con.repaint();
				 radius = rad_arr[curRadIndex];
				 con.sleep(100);
				 curRadIndex = (curRadIndex + 1) % rad_arr.length;
			 }
			 if(between(mouseX, 1092, 1212) && between(mouseY, 502, 553)){
				 strDuration = Integer.toString(dur_arr[curDurIndex]);
				 con.drawImage(cover, 863, 509);
				 con.repaint();
				 con.drawString(strDuration, 863, 512);
				 con.repaint();
				 duration = dur_arr[curDurIndex];
				 con.sleep(100);
				 curDurIndex = (curDurIndex + 1) % dur_arr.length;
			 }
       if(between(mouseX, 256, 484) && between(mouseY, 637, 822)){
         con.drawString(strGun, 262, 903);
         con.drawImage(gun_cover, 648, 903);
         con.repaint();
         gun = 0;
         con.sleep(100);

       }
       if(between(mouseX, 628, 972) && between(mouseY, 646, 829)){
         con.drawString(strGun, 648, 903);
         con.drawImage(gun_cover, 262, 903);
         con.repaint();
         gun = 1;
         con.sleep(100);
       }
			 if(between(mouseX, 1139, 1504) && between(mouseY, 933, 1078)){
				 TextOutputFile outfile = new TextOutputFile("Settings.txt", false);
				 outfile.println(radius);
				 outfile.println(duration);
         outfile.println(gun);
			 }
			 if(between(mouseX, 1505, 1913) && between(mouseY, 932, 1077)){
				 return;
			 }
		 }
	  }

  }



  public int menuClick(Console con)
  {
    while (true)
    {
      int mouseX = con.currentMouseX();
      int mouseY = con.currentMouseY();

      if(con.currentMouseButton() == 1)
      {
        if(between(mouseX, 305, 850) && between(mouseY, 590, 806))
        {
          return 1;
        }
        else if(between(mouseX, 285, 870) && between(mouseY, 858, 1043))
        {
          return 2;
        }
        else if(between(mouseX, 1025, 1660) && between(mouseY, 595, 800))
        {
          return 3;
        }
        else if(between(mouseX, 1080, 1605) && between(mouseY, 830, 1130))
        {
          return 4;
        }
      }
    }
  }
}