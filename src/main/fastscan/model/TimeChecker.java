package fastscan.model;

import java.util.Timer;
import java.util.TimerTask;
import java.util.Observer;
import java.util.Observable;

public class TimeChecker extends Observable{
  private String name;
  private long time;
  private Timer timer;

  private TimerTask task = new TimerTask(){
    @Override
    public void run(){
      setChanged();
      notifyObservers();
    }
  };

  TimeChecker(Observer o, String name, long t){
    addObserver(o);
    this.name = name;
    timer = new Timer(name);
    timer.schedule(task, t);
  }

  void cancel(){
    timer.cancel();
  }
}
