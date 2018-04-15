package fastscan.model;

class Index{
  private LoopCounter x;
  private LoopCounter y;

  Index(LoopCounter x, LoopCounter y){
    this.x = x;
    this.y = y;
  }

  public int getX(){
    return x.getValue();
  }

  public int getY(){
    return y.getValue();
  }

  public void increaseX(){
    x.increase();
  }

  public void increaseY(){
    y.increase();
  }

  public void setXValue(int i){
    x.setValue(i);
  }

  public void setYValue(int i){
    y.setValue(i);
  }

  public void resetX(){
    setXValue(0);
  }

  public void resetY(){
    setYValue(0);
  }

  public void resetXY(){
    resetX();
    resetY();
  }
}
