package src;

import arc.*;
import src.*;

public class Button
{
  private int length;
  private int width;
  private int dimX;
  private int dimY;

  public Button(int dimX, int dimY, int length, int width)
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
