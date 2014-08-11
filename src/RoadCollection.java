import java.util.ArrayList;
import java.util.HashSet;

public class RoadCollection {
  private ArrayList<HashSet<RoadAttribute>> roads;

  public RoadCollection(int imageWidth){
    roads = new ArrayList<>(imageWidth);
    for(int i = 0; i < roads.size(); i++){
      roads.add(i, new HashSet<RoadAttribute>());
    }
  }

  public void addRoad(RoadAttribute r){
    if(!roads.get(r.xpos).contains(r)){
      roads.get(r.xpos).add(r);
    }
  }

  public boolean checkRoad(RoadAttribute r){
    // TODO
    return false;
  }
}
