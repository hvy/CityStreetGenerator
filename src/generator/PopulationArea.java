package generator;

import generator.model.Pos;
import java.lang.Math;

public class PopulationArea {

  private Pos pos;
  private int radius;

  public PopulationArea(int xPos, int yPos, int radius){
    this.pos = new Pos(xPos, yPos);
    this.radius = radius;
  }

  public Pos getPos() {
    return pos;
  }

  public int getWidth() {
    return radius;
  }

  public boolean isPosInside(Pos p){
    double distance = Math.pow((double)(p.getX() - pos.getX()), 2) + Math.pow((double)(p.getY() - pos.getY()), 2);
    return (distance <= Math.pow(radius, 2));
  }
}
