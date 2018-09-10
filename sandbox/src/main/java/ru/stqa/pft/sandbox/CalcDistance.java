package ru.stqa.pft.sandbox;

public class CalcDistance {

  public static void main(String[] args) {

    Point p1 = new Point(0, 0);
    Point p2 = new Point(6, 8);
    double d = distance(p1, p2);
    System.out.println("Расстояние между точками p1(" + p1.x + ";" + p1.y + ") и p2(" + p2.x + ";" + p2.y + ") = " + d);

    double dd = p1.distance(p2);
    System.out.println("Расстояние между точками (второй способ) = " + dd);

  }

  public static double distance(Point p1, Point p2){
    return Math.sqrt(Math.pow(p2.x - p1.x, 2) + Math.pow(p2.y - p1.y, 2));
  }

}
