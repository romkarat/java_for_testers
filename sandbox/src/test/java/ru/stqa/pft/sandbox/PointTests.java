package ru.stqa.pft.sandbox;

import org.testng.Assert;
import org.testng.annotations.Test;

public class PointTests {

  @Test
  public void testDistance1(){
    Point p1 = new Point(0, 0);
    Point p2 = new Point(6, 8);
    Assert.assertEquals(p1.distance(p2) , 10.0);
  }

  @Test
  public void testDistance2(){
    Point p1 = new Point(-5, -8);
    Point p2 = new Point(-2, -4);
    Assert.assertEquals(p1.distance(p2) , 5.0);
  }

  @Test
  public void testDistance3(){
    Point p1 = new Point(5.4, 99.66);
    Point p2 = new Point(1.4, 14.21);
    Assert.assertEquals(p1.distance(p2) , 85.54357076952071);
  }

  @Test
  public void testDistance4(){
    Point p1 = new Point(0, 0);
    Point p2 = new Point(0, 0);
    Assert.assertEquals(p1.distance(p2) , 0.0);
  }

}
