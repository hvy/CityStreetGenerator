package generator.model;

import generator.attribute.RoadAttribute;

public class Query extends Module {
  public static final int UNASSIGNED = 0;
  public static final int FAILED = 1;
  public static final int SUCCEED = 2;

  public int state;
  public RoadAttribute roadAttribute;
}