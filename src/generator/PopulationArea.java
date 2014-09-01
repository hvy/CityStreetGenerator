package generator;

import generator.model.Pos;

public class PopulationArea {

  private Pos pos;
  private int width;

  public PopulationArea(int xPos, int yPos, int width){
    this.pos = new Pos(xPos, yPos);
    this.width = width;
  }

  public Pos getPos() {
    return pos;
  }

  public int getWidth() {
    return width;
  }
}
