package fastscan.model;

import java.util.Observer;
import java.util.Observable;

public class MainModel extends Observable{
  private CursolModeModel currentMode;
  private Index currentIndex;
  private SentenceModel currentSentence = new SentenceModel("");
  private boolean needConfirmAgain = false;
  private boolean prevIndex = false;
  private int prevX;
  private int prevY;

  public MainModel(Observer o){
    addObserver(o);
    currentMode = CursolModeModel.SELECT_COLUMN;
    LoopCounter x = new LoopCounter(
      CharTableModel.getHorizontalNumber() - 1
    );

    LoopCounter y = new LoopCounter(
      CharTableModel.getVerticalNumber() - 1
    );
    currentIndex = new Index(x, y);
  }

  public void resetXY(){
    currentIndex.resetXY();
  }

  public void resetY(){
    currentIndex.resetY();
  }

  public int getX(){
    return currentIndex.getX();
  }

  public int getY(){
    return currentIndex.getY();
  }

  private void setIndex(int x, int y){
    currentIndex.setXValue(x);
    currentIndex.setYValue(y);
  }

  public void setPrev(){
    prevIndex = true;
    prevX = currentIndex.getX();
    prevY = currentIndex.getY();
  }

  public void restore(){
    prevIndex = false;
    setIndex(prevX, prevY);
  }

  public boolean hasPrevIndex(){
    return prevIndex;
  }

  public CursolModeModel getCurrentMode(){
    return currentMode;
  }

  public void setCurrentMode(CursolModeModel cm){
    currentMode = cm;
    setChanged();
    notifyObservers();
  }

  public void increase(){
    currentMode.increase(currentIndex);
    setChanged();
    notifyObservers();
  }

  public void setCursolMode(CursolModeModel m){
    currentMode = m;
  }

  public boolean getNeedConfirmAgain(){
    return needConfirmAgain;
  }

  public void setNeedConfirmAgain(boolean b){
    needConfirmAgain = b;
  }

  public String getSentence(){
    return currentSentence.get();
  }

  public void addChar(){
    setPrev();
    String s = CharTableModel.getChar(currentIndex);
    currentSentence = currentSentence.add(s);
    setChanged();
    notifyObservers();
  }

  public void resetSentence(){
    currentSentence = new SentenceModel("");
    setChanged();
    notifyObservers();
  }

  public void deleteChar(){
    currentSentence = currentSentence.delete();
    setChanged();
    notifyObservers();
  }

  public void singleClick(){
    currentMode.singleClick(this);
  }

  public void doubleClick(){
    currentMode.doubleClick(this);
  }
}
