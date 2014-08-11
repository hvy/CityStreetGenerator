package generator.utility;

public class ColorUtility {

  public static int[] toARGBArr(int argb) {
    int[] rgbArr = new int[4];

    rgbArr[0] = (argb >> 24) & 0xFF; // Alpha
    rgbArr[1] = (argb >> 16) & 0xFF; // Red
    rgbArr[2] = (argb >>  8) & 0xFF; // Green
    rgbArr[3] = (argb      ) & 0xFF; // Blue

    return rgbArr;
  }

  public static boolean isBlack(int argb) {
    int rgb = 0;

    if (((argb >> 16) & 0xFF) == 0) {
      if (((argb >> 8) & 0xFF) == 0) {
        if (((argb) & 0xFF) == 0) {
          return true;
        }
      }
    }

    return false;
  }


  public static boolean isWhite(int argb) {
    int rgb = 0;

    if (((argb >> 16) & 0xFF) == 0xFF) {
      if (((argb >> 8) & 0xFF) == 0xFF) {
        if (((argb) & 0xFF) == 0xFF) {
          return true;
        }
      }
    }

    return false;
  }
}
