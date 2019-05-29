import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.swing.*;

public class draw_test extends JFrame{
  public static void main(String[] args)
  {
    JFrame frame = new JFrame();

    final int FRAME_WIDTH = 300;
    final int FRAME_HEIGHT = 400;

    frame.setSize(FRAME_WIDTH, FRAME_HEIGHT):

    frame.setTitle("Two Rectangles");

    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    Rectangle rect = new Rectangle();

    frame.add(rect);

    frame.setVisible(true);
  }
}

class Rectangle extends JComponent
{
  public void paintComponent(Graphics g)
  {
    Graphics2D g2 = (Grapahics2D) g;

    Rectangle box = new Rectangle(5, 10, 20, 30);

    g2.draw(box);

    box.translate(15, 25);

    g2.draw(box);

  }
}
