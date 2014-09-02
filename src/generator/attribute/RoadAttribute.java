package generator.attribute;
import generator.model.Pos;
import generator.model.Road;
import java.lang.Math;

public class RoadAttribute {
  public final static int PRIMARY = 0;
  public final static int SECONDARY = 1;
  public final static int TERTIARY = 2;

  public double angle;
  public int length;
  public Pos startPos;
  public Pos endPos;
  public int type;
  public Road r;

  public RoadAttribute(double angle, int length, Pos startPos, int type, Road r){
    this.angle = angle;
    this.length = length;
    this.startPos = startPos;
    this.type = type;
    this.r = r;

    int x = (int) Math.round(Math.cos(angle) * (double)length);
    int y = (int) Math.round(Math.sin(angle) * (double)length);
    endPos = new Pos(x,y);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    RoadAttribute that = (RoadAttribute) o;

    if (Double.compare(that.angle, angle) != 0) return false;
    if (length != that.length) return false;
    if (type != that.type) return false;
    if (startPos != that.startPos) return false;
    if (endPos != that.endPos) return false;

    return true;
  }

  @Override
  public int hashCode() {
    int result;
    long temp;
    temp = Double.doubleToLongBits(angle);
    result = (int) (temp ^ (temp >>> 32));
    result = 31 * result + length;
    result = 31 * result + startPos.getX();
    result = 31 * result + startPos.getY();
    result = 31 * result + endPos.getX();
    result = 31 * result + endPos.getY();
    result = 31 * result + type;
    return result;
  }
}