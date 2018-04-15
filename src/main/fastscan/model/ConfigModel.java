package fastscan.model;

public class ConfigModel {
  public static final String TITLE = "FastScan";

  private static long scanSpeed = 500;
  public static long getScanSpeed(){
    return scanSpeed;
  }
  public static void setScanSpeed(long s){
    scanSpeed = s;
  }

  private static long doubleClickInterval = 200;
  public static long getDoubleClickInterval(){
    return doubleClickInterval;
  }
  public static void setDoubleClickInterval(long i){
    doubleClickInterval = i;
  }

  private static float correctClickRate = 0.5f;
  public static float getCorrectClickRate(){
    return correctClickRate;
  }
  public static void setCorrectClickRate(float r){
    correctClickRate = r;
  }

  private static boolean changedColor = true;
  public static boolean isChangedColor(){
    return changedColor;
  }

  public static void setChangedColor(boolean b){
    changedColor = b;
  }
}
