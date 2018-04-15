package fastscan.model;

import java.util.Observer;
import java.util.Observable;

public class ScanModel{
  private static MainModel mainModel;

  public static void init(MainModel m){
    mainModel = m;
  }

  public static void mousePressed(){
    scanTimer.cancel();
    confirmTimer.cancel();

    if(waitingNextClick){
      clickTimer.cancel();
      doubleClick();
    }
    else{
      startClickTimer();
    }
  }

  private static TimeChecker scanTimer;
  public static void cancelScan(){
    scanTimer.cancel();
  }
  public static void startScan(){
    startConfirmTimer();
    scanTimer = new TimeChecker(
      new Observer(){
        @Override
        public void update(Observable o, Object arg){
          mainModel.increase();
          startScan();
        }
      },
      "Scan Timer",
      ConfigModel.getScanSpeed()
    );
  }

  private static boolean waitingNextClick;
  private static TimeChecker clickTimer;
  private static void startClickTimer(){
    waitingNextClick = true;
    clickTimer = new TimeChecker(
      new Observer(){
        @Override
        public void update(Observable o, Object arg){
          singleClick();
        }
      },
      "Click Timer",
      ConfigModel.getDoubleClickInterval()
    );
  }

  private static void singleClick(){
    mainModel.singleClick();
    waitingNextClick = false;
  }

  private static void doubleClick(){
    mainModel.doubleClick();
    waitingNextClick = false;
  }

  private static boolean confirm = false;
  private static TimeChecker confirmTimer;
  public static boolean needConfirm(){
    return confirm;
  }

  private static void startConfirmTimer(){
    confirm = false;
    // System.out.println(confirm);
    if(confirmTimer != null){
      confirmTimer.cancel();
    }

    long scanSpeed = ConfigModel.getScanSpeed();
    float rate = ConfigModel.getCorrectClickRate();
    Float t = scanSpeed * rate;
    confirmTimer = new TimeChecker(
      new Observer(){
        @Override
        public void update(Observable o, Object arg){
          confirm = true;
          // System.out.println(confirm);
        }
      },
      "Confirm Timer",
      t.longValue()
    );
  }
}
