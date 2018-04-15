package fastscan.view.paintcursol;

import java.awt.Graphics;
import java.awt.Color;
import java.awt.Point;

import fastscan.model.CharTableModel;

public class CharPainter extends PaintCursol{
  private int x;
  private int y;
  private boolean changedColor;

  public CharPainter(int x, int y, boolean changedColor){
    this.x = x;
    this.y = y;
    this.changedColor = changedColor;
  }

  @Override
  public void paint(Graphics g, Point blockSize){
    // g.setColor(this.C2);
    // g.fillRect(
    //   (blockSize.x - 1) * this.x,
    //   0,
    //   blockSize.x,
    //   blockSize.y * CharTableModel.getVerticalNumber()
    // );

    g.setColor(this.SELECT);
    if(changedColor){
      g.setColor(this.CONFIRM);
    }
    g.fillRect(
      (blockSize.x - 1) * this.x,
      blockSize.y * this.y,
      blockSize.x,
      blockSize.y
    );

  }
}
