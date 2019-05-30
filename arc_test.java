import arc.*;

public class arc_test
{
  public static void main(String[] args)
  {
    Console con = new Console(1920, 1080);

    int ovalX = (int)(Math.random() * 1770);
    int ovalY = (int)(Math.random() * 930);

    con.drawOval(ovalX, ovalY, 150, 150);

    int ovalRadX = ovalX + 75;
    int ovalRadY = ovalY + 75;

    con.drawLine(ovalRadX, ovalRadY, 1200, 700);

    int foo = 1;

    while(true)
    {
      foo++;
      if(con.currentMouseButton() == 1)
      {
        if(((Math.pow((con.currentMouseX() - ovalRadX), 2) + Math.pow((con.currentMouseY() - ovalRadY), 2)) < Math.pow(75, 2)))
        {
          con.println(foo);
        }
      }
    }
  }
}
class Circle
{
  private int radius;

  public Circle(int r, Console con)
  {
    
  }
}
