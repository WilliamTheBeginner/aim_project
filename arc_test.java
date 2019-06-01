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

  public static void clearCircle(Circle cir, Console con)
  {
    ;
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
    int arr[] = new int[2]
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

    TextInputFile infile = new TextInputFile("Settings.txt");

    while(true)
    {
      int mouseX = con.currentMouseX();
      int mouseY = con.currentMouseY();

      if(con.currentMouseButton() == 1)
      {
        switch(menuClick(con))
        {
          case 1:
          case 2:
          case 3:
          case 4:
        }
      }
    }

  }
  public void displayCircleGame(Circle cir, Console con)
  {

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
  public int menuClick(Console con)
  {
    while (true)
    {
      int mouseX = con.currentMouseX();
      int mouseY = con.currentMouseY();

      if(con.currentMouseButton == 1)
      {
        if(between(MouseX, 300, 800) && between(MouseY, 500, 700))
        {
          return 1;
        }
        else if(between(MouseX, 300, 800) && between(MouseY, 790, 1190))
        {
          return 2;
        }
        else if(between(MouseX, 1120, 1620) && between(MouseY, 500, 700))
        {
          return 3;
        }
        else if(between(MouseX, 1120, 1620) && between(MouseY, 790, 1190))
        {
          return 4;
        }
      }
    }
  }

}
