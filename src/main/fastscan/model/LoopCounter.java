package fastscan.model;

class LoopCounter{
  private int value;
  private int max;
  private boolean increased = false;

  LoopCounter(int arg_max){
    if(arg_max <= 0){
      throw new IllegalArgumentException("max is Not NaturalNumber.");
    }
    value = 0;
    max = arg_max;
  }

  int getValue(){
    return this.value;
  }

  void increase(){
    this.value ++;
    if(this.max < this.value){
      this.value = 0;
    }
  }

  void setValue(int i){
    if(this.max < i){
      throw new IllegalArgumentException("Too Big Number");
    }
    this.value = i;
  }

  private LoopCounter(int arg_max, int  arg_value){
    this.value = arg_value;
    this.max = arg_max;
  }
}
