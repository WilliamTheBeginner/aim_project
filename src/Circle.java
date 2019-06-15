package src;

import arc.*;
import java.awt.image.BufferedImage;
import java.awt.*;
import src.*;

public class Circle{
  // Console properties. These are always constant
  final int intDimX = 1920;
  final int intDimY = 1080;

  // circle properties
  private int intRadius;
  private int intRadX;
  private int intRadY;

  public Circle()
  {
	  // default settings, set radius to 75, and randomly make points
	  this.intRadius = 75;
	  this.intRadX = (int)(Math.random() * 1845);
	  this.intRadY = (int)(Math.random() * 1005);
  }
  // experiementory. The intRadiuses cannot exceed the dimensions
  public Circle(int intRadius)
  {
	  // optional constructor, set radius to the settings option
	  this.intRadius = intRadius;
	  // set radiuses to random co-ordinates
	  this.intRadX = (int)(Math.random() * (intDimX - intRadius));
	  this.intRadY = (int)(Math.random() * (intDimY - intRadius));
  }

  public int getRadius()
  {
	  // return the radius
	  return intRadius;
  }

  public int [] getRadXY()
  {
	  // make new array
	  int arr[] = new int[2];

	  // return the radius co ordinates
	  arr[0] = intRadX;
	  arr[1] = intRadY;

	  return arr;
  }

  public void draw(Console con)
  {
	  //Using the radius size and co ordinates, find out where to draw the circle
	  int intDrawX = intRadX - intRadius;
	  int intDrawY = intRadY - intRadius;
	  // draw in white
    con.setDrawColor(Color.WHITE);
	  // draw the oval
	  con.drawOval(intDrawX, intDrawY, intRadius * 2, intRadius * 2);
  }
  public void appearImage(java.awt.image.BufferedImage theImage)
  {
    ;
  }
}
