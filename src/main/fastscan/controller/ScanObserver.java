package fastscan.controller;

import java.util.Observer;
import java.util.Observable;

import fastscan.view.View;

public class ScanObserver implements Observer{
  @Override
  public void update(Observable obs, Object obj){
    View.repaint();
  }
}
