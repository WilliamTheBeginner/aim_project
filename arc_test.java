import arc.*;
import java.util.Arrays;
import java.awt.*;
import java.awt.image.BufferedImage;

public class arc_test
{
  public static void main(String[] args)
  {
    Console con = new Console(1920, 1080);

    for(int i = 0; i < 100; i++)
    {

      Circle cir = new Circle(50);

      cir.draw(con);

      con.repaint();

      con.println(Arrays.toString(cir.getRadXY()));

      clickCircle(cir, con);

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
          clearConsole(con);
          return true;
			  }
		  }
	  }
  }

  public static void clearConsole(Console con)
  {
    con.setDrawColor(Color.BLACK);
    con.fillRect(0, 0, 1920, 1080);
    con.repaint();
  }
}
class Circle
{
  final int dimX = 1920;
  final int dimY = 1080;

  private int radius;
  private int radX;
  private int radY;

  public Circle()
  {
	  this.radius = 75;
	  this.radX = (int)(Math.random() * 1845);
	  this.radY = (int)(Math.random() * 1005);
  }

  // experiementory. The radiuses cannot exceed the dimensions
  public Circle(int radius)
  {
	  this.radius = radius;
	  this.radX = (int)(Math.random() * (dimX - radius));
	  this.radY = (int)(Math.random() * (dimY - radius));
  }

  public int getRadius()
  {
	  return radius;
  }

  public int [] getRadXY()
  {
	  int arr[] = new int[2];

	  arr[0] = radX;
	  arr[1] = radY;

	  return arr;
  }

  public void draw(Console con)
  {
	  int drawX = radX - radius;
	  int drawY = radY - radius;
    con.setDrawColor(Color.WHITE);
	  con.drawOval(drawX, drawY, radius * 2, radius * 2);
  }
  public void appearImage(java.awt.image.BufferedImage theImage)
  {
    ;
  }
}

class Button
{
  private int length;
  private int width;
  private int dimX;
  private int dimY;

  public Button(int length, int width, int dimX, int dimY)
  {
    this.length = length;
    this.width = width;
    this.dimX = dimX;;
    this.dimY = dimY;
  }

  public void draw(Console con)
  {
    con.drawRect(dimX, dimY, length, width);
  }
  public int [] getDims()
  {
    int arr[] = new int[2];
    arr[0] = dimX;
    arr[1] = dimY;
    return arr;
  }

}

class Menu
{
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
            // put timer in the middle of screen... THEN displaycirclegame
            // choose which timer to use
            TextInputFile infile = new TextInputFile("Settings.txt");

            int radius, seconds;

            while(infile.eof() != true)
            {
              radius = infile.readInt();
              seconds = infile.readInt();
            }

            clearConsoleMenu(con);

            for(int i = 1; i <= 3; i++){
              //tiemr for 3 seconds
              con.print(i + " ");
              con.sleep(1000);
            }

            Console timerConsole = new Console();

            Timer timer = new Timer();
            timer.start();

            if(seconds == 30){
              Timer30 t30 = new Timer30(timer);
              t30.start();
            } else {
              Timer60 t60 = new Timer60(timer);
              t60.start();
            }

            displayCircleGame(radius, con);





          case 2:
            // change file options
          case 3:
          case 4:
        }
      }
    }

  }

  public int displayCircleGame(int radius, Console con)
  {
    while(catchTimer() != true){
      // display circles
      Circle cir = new Circle(radius);
      cir.draw(con);
      con.repaint();
      con.println(Arrays.toString(cir.getRadXY()));
      clickCircle(cir, con);
    }
  }
  public static void catchTimer(){
    return true;
  }
  public void displayMain(Console con)
  {
    Button start = new Button(300, 500, 500, 200);
    Button options = new Button(300, 790, 500, 200);
    Button help = new Button(1120, 500, 500, 200);
    Button quit = new Button(1120, 790, 500, 200);

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
  public int menuClick(Console con)
  {
    while (true)
    {
      int mouseX = con.currentMouseX();
      int mouseY = con.currentMouseY();

      if(con.currentMouseButton() == 1)
      {
        if(between(mouseX, 300, 800) && between(mouseY, 500, 700))
        {
          return 1;
        }
        else if(between(mouseX, 300, 800) && between(mouseY, 790, 1190))
        {
          return 2;
        }
        else if(between(mouseX, 1120, 1620) && between(mouseY, 500, 700))
        {
          return 3;
        }
        else if(between(mouseX, 1120, 1620) && between(mouseY, 790, 1190))
        {
          return 4;
        }
      }
    }
  }
}

class Timer extends Thread{
    private int time = 1;
    private boolean setting = false;
    private volatile boolean exit = false;

    Console con = new Console("Time left:");

    @Override
    public void run(){
        while(exit != true){
            try {
                sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            con.clear();
            con.print(29 - time + " ");
            //System.out.print(29 - time + " ");
            setTime(time + 1);
            //Switching the order of these 2 ^^^ statements and initializing time to 0 will give an output that is more accurate to the time.
        }
    }
    public void close() {
      exit = true;
      con.closeWindow();
      Console cons = new Console();

    }
    public synchronized int getTime(){
        while(setting){
            try {
                wait(); //This will only be run on the off-chance that setTime is being run at the same time.
            } catch (InterruptedException e) {  }
        }

        return time;
    }
    public synchronized void setTime(int t){
        setting = true;
        this.time = t;
        setting = false;
        notifyAll();
    }
}

class Timer30 extends Thread{
    Timer timer;

    public int five = 1;
    public Timer7(Timer t){
        this.timer = t;
    }

    public void run(){
        synchronized(timer){
            while(true){

                try {
                    timer.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                if(timer.getTime() % 30 == 0){
                    timer.close();
                }

            }
        }
    }
}

class Timer60 extends Thread{
    Timer timer;

    public int five = 1;
    public Timer7(Timer t){
        this.timer = t;
    }

    public void run(){
        synchronized(timer){
            while(true){

                try {
                    timer.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                if(timer.getTime() % 60 == 0){
                    timer.close();
                }

            }
        }
    }
}

class DScore {
  public DScore(){
    ;
  }
  public DScore(int score, Console con){
    con.println("Game over. Score: " + score);
  }
}
