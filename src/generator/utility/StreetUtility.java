package generator.utility;

import generator.model.Pos;

import java.awt.image.BufferedImage;

public class StreetUtility {

  public static boolean isValidPath(BufferedImage mapBImg, Pos from, Pos to) {

    int w = to.getX() - from.getX();
    int h = to.getY() - from.getY();

    int x = from.getX();
    int y = from.getY();

    int dx1 = 0;
    int dy1 = 0;
    int dx2 = 0;
    int dy2 = 0;

    if (w < 0) dx1 = -1; else if (w > 0) dx1 = 1;
    if (h < 0) dy1 = -1; else if (h > 0) dy1 = 1;
    if (w < 0) dx2 = -1; else if (w > 0) dx2 = 1;

    int longest = Math.abs(w);
    int shortest = Math.abs(h);

    if (!(longest > shortest)) {

      longest = Math.abs(h) ;
      shortest = Math.abs(w) ;

      if (h < 0) dy2 = -1; else if (h > 0) dy2 = 1 ;
      dx2 = 0 ;

    }

    int numerator = longest >> 1 ;

    for (int i = 0; i <= longest; i++) {

      if (!ColorUtility.isBlack(mapBImg.getRGB(x, y))) {
        return false;
      }

      numerator += shortest;
      if (!(numerator<longest)) {
        numerator -= longest;
        x += dx1;
        y += dy1;
      } else {
        x += dx2;
        y += dy2;
      }
    }

    return true;
  }
}