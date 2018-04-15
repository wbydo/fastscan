package fastscan.model;

class SentenceModel {
  String s;

  SentenceModel(String arg){
    this.s = arg;
  }

  public SentenceModel add(String arg){
    return new SentenceModel(this.s + arg);
  }

  public String get(){
    return this.s;
  }

  public SentenceModel delete(){
    if(this.s != null && s.length() > 0){
      String s = this.s;
      s = s.substring(0, s.length()-1);
      return new SentenceModel(s);
    }
    return this;
  }
}
