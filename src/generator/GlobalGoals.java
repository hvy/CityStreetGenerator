package generator;

import generator.attribute.RoadAttribute;
import generator.attribute.RuleAttribute;
import generator.model.Branch;
import generator.model.Module;
import generator.model.Road;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GlobalGoals {
  private int mapWidth;
  private int mapHeight;
  private List<PopulationArea> populationAreas;

  public GlobalGoals(int width, int height){
    mapWidth = width;
    mapHeight = height;
    populationAreas = new ArrayList<>();
  }

  public void setPopulationAreas(List<PopulationArea> points) {
    this.populationAreas = points;
  }

  public List<PopulationArea> getPopulationAreas() {
    return populationAreas;
  }

  public void setModuleParams(Module m){
    // TODO: Takes a generator.model.Module object and sets it initial values with the global goals in consideration.
  }

  public boolean checkGlobalGoals(Module m){
    // TODO: Takes a module (?) and checks its params with global goals.
    return false;
  }

  public void p2(int del, RuleAttribute ruleAttr, RoadAttribute roadAttr, Branch b1, Branch b2, Road r) {
    // TODO: based on the map, delay, rule and roadattr, set the parameters for the branches and the road
  }
}