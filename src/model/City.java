package model;

public class City {

  private int x;
  private int y;
  private int size;

  public City(int x, int y, int size) {
    this.x = x;
    this.y = y;
    this.size = size;
  }

  public int getX() {
    return x;
  }

  public int getY() {
    return y;
  }

  public int getSize() {
    return size;
  }

  public void setX(int x) {
    this.x = x;
  }

  public void setY(int y) {
    this.y = y;
  }

  public void setSize(int size) {
    this.size = size;
  }
}
