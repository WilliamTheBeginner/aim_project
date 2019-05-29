import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import javax.swing.*;

/**
 * @author Alvin Alexander, http://devdaily.com
 *
 * A Java class to demonstrate how to create a
 * custom Java mouse cursor, in this case, a
 * mouse cursor that shows the x/y coordinates of the
 * mouse as the mouse is moved over a Java/Swing application.
 *
 */
public class aim_test extends JFrame
{

  public static void main(String[] args)
  {
    SwingUtilities.invokeLater(new Runnable()
    {
      public void run()
      {
        displayJFrame();
      }
    });
  }

  static void displayJFrame()
  {
    // create a jframe as usual
    JFrame jFrame = new JFrame();
    jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    jFrame.setTitle("Mouse Cursor with Label");

    // set the jframe size and center it
    jFrame.setPreferredSize(new Dimension(1920 , 1080));
    jFrame.pack();
    jFrame.setLocationRelativeTo(null);

    // create an instance of my custom mouse cursor component
    final AlsXYMouseLabelComponent alsXYMouseLabel = new AlsXYMouseLabelComponent();

    // add my component to the DRAG_LAYER of the layered pane (JLayeredPane)
    JLayeredPane layeredPane = jFrame.getRootPane().getLayeredPane();
    layeredPane.add(alsXYMouseLabel, JLayeredPane.DRAG_LAYER);
    alsXYMouseLabel.setBounds(0, 0, jFrame.getWidth(), jFrame.getHeight());


    // add a mouse motion listener, and update my custom mouse cursor with the x/y
    // coordinates as the user moves the mouse
    jFrame.addMouseMotionListener(new MouseMotionAdapter() {
      public void mouseMoved(MouseEvent me)
      {
        alsXYMouseLabel.x = me.getX();
        alsXYMouseLabel.y = me.getY();
        alsXYMouseLabel.repaint();
      }
    });

    jFrame.addMouseListener(new MouseAdapter() {
      public void mouseClicked(MouseEvent me)
      {
        if(me.getButton() == MouseEvent.NOBUTTON)
        {
          System.out.println("Nothing was pressed");
        }
        else if(me.getButton() == MouseEvent.BUTTON1)
        {
          System.out.println("Button 1" + me.getX() + " " + me.getY());
        }
        else if(me.getButton() == MouseEvent.BUTTON2)
        {
          System.out.println("Button 2" + me.getX() + " " + me.getY());
        }
        else if(me.getButton() == MouseEvent.BUTTON3)
        {
          System.out.println("Button 3" + me.getX() + " " + me.getY());
        }
      }
    });



    // make the cursor a crosshair shape
    jFrame.setCursor(new Cursor(Cursor.CROSSHAIR_CURSOR));

    // display the jframe
    jFrame.setVisible(true);
  }

}


/**
 * This is the class that draws the x/y coordinates
 * near the mouse cursor/pointer.
 */
class AlsXYMouseLabelComponent extends JComponent
{
  public int x;
  public int y;

  public AlsXYMouseLabelComponent() {
    this.setBackground(Color.blue);
  }

  // use the xy coordinates to update the mouse cursor text/label
  protected void paintComponent(Graphics g)
  {
    super.paintComponent(g);
    String s = x + ", " + y;
    g.setColor(Color.red);
    g.drawString(s, x, y);
  }
}

/*
class DrawCircle extends JFrame{
  public DrawCircle(JFrame j)
  {
    JPanel p = new JPanel();

    public void paintComponent(Graphics g)
    {
      Graphics 2D 2g =  (Graphics2D) g;
      Shape circle = new Ellipse2D.Double(100, 100, 100, 100);
    }

    p.getContentPane().add(p);
  }
}
*/

/*
class Ball
{

    private Color ballFillColor;
    private Color ballBorderColor;

    private int ballX = 0;
    private int ballY = 0;
    private int ballWidth = 0;
    private int ballHeight = 0;

    public boolean fillBall = false;

    Ball(){ //Constructor
        ballBorderColor = Color.black;
    }

    Ball(int ballX, int ballY, int ballWidth, int ballHeight, Color ballBorderColor, JFrame window){ //Constructor
        // X , Y , Width, Height, Border Colour, container
        this.setBallBorderColor(ballBorderColor);
        this.setBallWidth(ballWidth);
        this.setBallHeight(ballHeight);
        this.setBallX(ballX);
        this.setBallY(ballY);
        this.drawBall(window);
    }

    public Color getBallFillColor() {
        return ballFillColor;
    }
    public void setBallFillColor(Color BallFillColor) {
        this.ballFillColor = BallFillColor;
    }

    public Color getBallBorderColor() {
        return ballBorderColor;
    }
    public void setBallBorderColor(Color BallBorderColor) {
        this.ballBorderColor = BallBorderColor;
    }

    public int getBallX() {
        return ballX;
    }
    public void setBallX(int ballX) {
        this.ballX = ballX;
    }

    public int getBallY() {
        return ballY;
    }
    public void setBallY(int ballY) {
        this.ballY = ballY;
    }

    public int getBallWidth() {
        return ballWidth;
    }
    public void setBallWidth(int ballWidth) {
        this.ballWidth = ballWidth;
    }


    public int getBallHeight() {
        return ballHeight;
    }
    public void setBallHeight(int ballHeight) {
        this.ballHeight = ballHeight;
    }


    public void drawBall(JFrame frame)
    {
        frame.getContentPane().add(new MyComponent());
    }


    private class MyComponent extends JComponent{
        public void paint(Graphics g){

            if (fillBall) //Fill first, and then draw outline.
            {
                g.setColor(ballFillColor);
                g.fillOval(getBallX(),getBallY(), getBallHeight(),getBallWidth());
            }

            g.setColor(getBallBorderColor());
            g.drawOval(getBallX(),getBallY(), getBallHeight(),getBallWidth());

        }
    }


}
*/
