import arc.*;
import java.util.Arrays;

public class arc_test
{
  public static void main(String[] args)
  {
    Console con = new Console(1920, 1080);

    Circle cir = new Circle();

    cir.draw(con);

    con.println(Arrays.toString(cir.getRadXY()));


	/*
    int ovalX = (int)(Math.random() * 1770);
    int ovalY = (int)(Math.random() * 930);

    con.drawOval(ovalX, ovalY, 150, 150);


    int ovalRadX = ovalX + 75;
    int ovalRadY = ovalY + 75;

    con.drawLine(ovalRadX, ovalRadY, 1200, 700);
    */

    clickCircle(cir, con);

    int foo = 1;

  }
  public static boolean clickCircle(Circle circle, Console con)
  {
	  //Console con2 = new Console();

	  int [] radarr = new int[2];

	  radarr = circle.getRadXY();

	  int radX = radarr[0];
	  int radY = radarr[1];

	  int foo = 0;

    con.println(radX);
    con.println(radY);

    con.println(circle.getRadius());

	  while(true)
	  {
		  foo++;
		  if(con.currentMouseButton() == 1)
		  {
			  if(((Math.pow((con.currentMouseX() - radX), 2) + Math.pow((con.currentMouseY() - radY), 2)) < Math.pow(circle.getRadius(), 2)))
			  {
				  con.println(foo);
			  }
		  }
	  }
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
  public Circle(int radius, int radX, int radY)
  {
	  this.radius = radius;
	  this.radX = radX;
	  this.radY = radY;
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
	  con.drawOval(drawX, drawY, radius * 2, radius * 2);
  }
}
