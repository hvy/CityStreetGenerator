package generator.model;

public class Pos {

  private final int x;
  private final int y;

  public Pos(int x, int y) {
    this.x = x;
    this.y = y;
  }

  public int getX() {
    return x;
  }

  public int getY() {
    return y;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    Pos pos = (Pos) o;

    if (x != pos.x) return false;
    if (y != pos.y) return false;

    return true;
  }

  @Override
  public int hashCode() {
    int result = x;
    result = 31 * result + y;
    return result;
  }
}
