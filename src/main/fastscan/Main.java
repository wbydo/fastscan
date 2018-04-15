package fastscan;

import javax.swing.SwingUtilities;

import java.lang.reflect.InvocationTargetException;

import java.util.Observer;

import fastscan.model.MainModel;
import fastscan.model.ScanModel;
import fastscan.view.View;
import fastscan.controller.ScanObserver;

public class Main{
  static Observer scanObserver = new ScanObserver();
  static MainModel m = new MainModel(scanObserver);
  public static void main(String[] args) throws InterruptedException,InvocationTargetException {

    // Viewの処理
    SwingUtilities.invokeAndWait(new Runnable(){
        @Override
        public void run(){
          View.init(m);
          View.repaint();

          ScanModel.init(m);
          ScanModel.startScan();
        }
    });
  }
}
