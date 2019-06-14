package src;

import arc.*;
import src.*;

public class Button
{
  // initialize button properties
  private int intLength;
  private int intWidth;
  private int intDimX;
  private int intDimY;

  // button class constructor
  public Button(int intDimX, int intDimY, int intLength, int intWidth)
  {
    this.intLength = intLength;
    this.intWidth = intWidth;
    this.intDimX = intDimX;;
    this.intDimY = intDimY;
  }

  // if this method is called, draw a rectangle of the button dimensions onto the console
  public void draw(Console con)
  {
    con.drawRect(intDimX, intDimY, intLength, intWidth);
  }
  // if this method is called, get the dimensions of the button, store into array and return it
  public int [] getDims()
  {
    int intDim[] = new int[2];
    intDim[0] = intDimX;
    intDim[1] = intDimY;
    return intDim;
  }

}
