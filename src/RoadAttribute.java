public class RoadAttribute {
  public final static int PRIMARY = 0;
  public final static int SECONDARY = 1;
  public final static int TERTIARY = 2;

  public double angle;
  public int length;
  public int xpos;
  public int ypos;
  public int type;

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    RoadAttribute that = (RoadAttribute) o;

    if (Double.compare(that.angle, angle) != 0) return false;
    if (length != that.length) return false;
    if (type != that.type) return false;
    if (xpos != that.xpos) return false;
    if (ypos != that.ypos) return false;

    return true;
  }

  @Override
  public int hashCode() {
    int result;
    long temp;
    temp = Double.doubleToLongBits(angle);
    result = (int) (temp ^ (temp >>> 32));
    result = 31 * result + length;
    result = 31 * result + xpos;
    result = 31 * result + ypos;
    result = 31 * result + type;
    return result;
  }
}