package fastscan.view;

import javax.swing.*;

import java.awt.GridLayout;
import java.awt.Dimension;
import java.awt.Color;
import java.awt.Font;
import java.awt.Container;
import java.awt.Point;
import java.awt.Graphics;

import java.util.Observer;
import java.util.Observable;

import fastscan.model.CharTableModel;
import fastscan.model.MainModel;

import fastscan.view.paintcursol.PaintCursol;

public class CharTableView{
  private static JPanel panel = new JPanel(){
    @Override
    protected void paintComponent(Graphics g){
      if(blockSize == null){
        throw new IllegalStateException("BlockSize is Unknown.");
      }
      super.paintComponent(g);
      paintCursor(g, mainModel);
    }
  };

  private static void paintCursor(Graphics g, MainModel m){
    PaintCursol p = PaintCursolStrategyChoiser.get(m);
    p.paint(g, blockSize);
  }

  private static JLabel tmp; // setBlockSize()のために一時保持が必要
  public static Point blockSize;

  private static MainModel mainModel;
  public static void init(MainModel m) {
    mainModel = m;
    panel.setBackground(Color.WHITE);
    GridLayout layout = new GridLayout(
      CharTableModel.getVerticalNumber(),
      CharTableModel.getHorizontalNumber()
    );

    panel.setLayout(layout);

    // 2:3の分割 1/2
    // マジックナンバーになっているのであとで直す
    panel.setMaximumSize(new Dimension(
      View.WIDTH,
      View.HEIGHT * 2 / 3
    ));

    int i = 0;
    for(String s : CharTableModel.eachAllChar()){
      JLabel l = new JLabel(s);
      l.setFont(new Font(Font.DIALOG, Font.PLAIN, 30));
      panel.add(l);

      if(i == CharTableModel.getHorizontalNumber() + 1){
        tmp = l;
      }
      i++;
    }
  }

  public static void beAddedTo(Container c){
    c.add(panel);
  }

  // 1マスのサイズを取得する。
  // frame.pack()されてから呼び出さないといけない。
  public static void setBlockSize(){
    blockSize = tmp.getLocation();
  }

  public static void repaint() {
    panel.repaint();
  }
}
