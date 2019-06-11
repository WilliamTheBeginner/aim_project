import arc.*;
import java.awt.Font;
public class score_test{
  public static void main(String[] args)
  {
    Console con = new Console();

    Font font = con.loadFont("Burbank.ttf", 50);

    con.setDrawFont(font);

    con.drawString("helo", 0, 0);
  }
}
