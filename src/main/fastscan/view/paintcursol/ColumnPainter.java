package fastscan.view.paintcursol;

import java.awt.Graphics;
import java.awt.Color;
import java.awt.Point;

import fastscan.model.CharTableModel;

public class ColumnPainter extends PaintCursol{
  private int x;
  private boolean changedColor;

  public ColumnPainter(int x, boolean changedColor){
    this.x = x;
    this.changedColor = changedColor;
  }

  @Override
  public void paint(Graphics g, Point blockSize){
    g.setColor(this.SELECT);
    if(changedColor){
      g.setColor(this.CONFIRM);
    }
    g.fillRect(
      (blockSize.x - 1) * this.x,
      0,
      blockSize.x,
      blockSize.y * CharTableModel.getVerticalNumber()
    );
  }
}
