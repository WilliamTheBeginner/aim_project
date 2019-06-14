package src;

import arc.*;
import java.awt.image.BufferedImage;
import java.awt.*;
import src.*;

public class Circle{
  final int intDimX = 1920;
  final int intDimY = 1080;

  private int intRadius;
  private int intRadX;
  private int intRadY;

  public Circle()
  {
	  this.intRadius = 75;
	  this.intRadX = (int)(Math.random() * 1845);
	  this.intRadY = (int)(Math.random() * 1005);
  }
  // experiementory. The intRadiuses cannot exceed the dimensions
  public Circle(int intRadius)
  {
	  this.intRadius = intRadius;
	  this.intRadX = (int)(Math.random() * (intDimX - intRadius));
	  this.intRadY = (int)(Math.random() * (intDimY - intRadius));
  }

  public int getRadius()
  {
	  return intRadius;
  }

  public int [] getRadXY()
  {
	  int arr[] = new int[2];

	  arr[0] = intRadX;
	  arr[1] = intRadY;

	  return arr;
  }

  public void draw(Console con)
  {
	  int intDrawX = intRadX - intRadius;
	  int intDrawY = intRadY - intRadius;
    con.setDrawColor(Color.WHITE);
	  con.drawOval(intDrawX, intDrawY, intRadius * 2, intRadius * 2);
  }
  public void appearImage(java.awt.image.BufferedImage theImage)
  {
    ;
  }
}
