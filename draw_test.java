import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import javax.swing.*;

public class draw_test extends JFrame{
  public static void main(String[] args)
  {
    JFrame frame = new JFrame();

    final int FRAME_WIDTH = 300;
    final int FRAME_HEIGHT = 400;

    frame.setSize(FRAME_WIDTH, FRAME_HEIGHT);

    frame.setTitle("Two Rectangles");

    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    DrawRectangle rect = new DrawRectangle();

    frame.add(rect);

    frame.pack();

    @Override
    frame.addComponentListener(new ComponentListener()
    {
      public void ComponentMoved(ComponentEvent e)
      {
        Component c = (Component) e.getSource();

        Point newLoc = c.getLocation();
        System.out.println("Get new location");
      }
    });

    frame.setVisible(true);
  }
}

class DrawRectangle extends JComponent
{
  public void paintComponent(Graphics g)
  {
    Graphics2D g2 = (Graphics2D) g;

    Rectangle box = new Rectangle(5, 10, 20, 30);

    g2.draw(box);

    box.translate((int)(Math.random() * 300), (int)(Math.random() * 400));

    g2.draw(box);

  }
}
