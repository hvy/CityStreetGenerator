package generator;

import generator.attribute.RoadAttribute;
import generator.model.Road;

import java.awt.image.BufferedImage;

public class LocalConstraints {

  private BufferedImage img;

  public LocalConstraints(BufferedImage img) {
    this.img = img;
  }

  public

  public void processRoadSegment(RoadAttribute roadAttribute) {
    // TODO
  }

  public void setImg(BufferedImage img) {
    this.img = img;
  }

  private void pruneSegment(Road r){
    // TODO: Takes a road segment and prunes its length
  }

  private void rotateSegment(Road r){
    // TODO: Takes a road segment and rotates its angle
  }
}