package fastscan.view.paintcursol;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.Color;

abstract public class PaintCursol{
  abstract public void paint(Graphics g, Point blockSize);

  final Color SELECT  = new Color(190, 190, 250);
  final Color CONFIRM = new Color(250, 220, 190);
}
