package fastscan.model;

import java.util.*;

public class CharTableModel {
  private static enum Table implements Iterable{
    DEFAULT(
      new String[][]{
        // { "　", "　", "゛", "゜", "　", "　", "゛", "゜", "　", " 削", "゛", "゜", " \u21B5"},

        { "あ", "か", "さ", "た", "な", "は", "ま", "や",  "ら", "わ",  "ゃ", "ぁ"},
        { "い", "き", "し", "ち", "に", "ひ", "み", "ゆ",  "り", "を",  "ゅ", "ぃ"},
        { "う", "く", "す", "つ", "ぬ", "ふ", "む", "よ",  "る", "ん",  "ょ", "ぅ"},
        { "え", "け", "せ", "て", "ね", "へ", "め", "　",  "れ", "　", "っ",  "ぇ"},
        { "お", "こ", "そ", "と", "の", "ほ", "も", "　",  "ろ", "　", "ー",  "ぉ"}
      }
    );

    final String[][] array;
    Table(String[][] arg){ this.array = arg; }

    @Override
    public Iterator<String> iterator(){
      int i;
      List<String> arr = new ArrayList<String>();
      for(i=0; i<this.array.length; i++){
        List<String> tmp = Arrays.asList(this.array[i]);
        arr.addAll(tmp);
      }
      return arr.iterator();
    }
  }

  // 他のモードが無いのでDEFAULT固定
  private static Table currentTable = Table.DEFAULT;

  private static String getChar(int x, int y){
    return currentTable.array[y][x];
  }

  public static String getChar(Index i){
    return getChar(i.getX(), i.getY());
  }

  public static int getVerticalNumber(){
    return currentTable.array.length;
  }

  public static int getHorizontalNumber(){
    return currentTable.array[0].length;
  }

  public static Iterable<String> eachAllChar(){
    return new Iterable<String>(){
      @Override
      public Iterator<String> iterator(){
        return currentTable.iterator();
      }
    };
  }
}
