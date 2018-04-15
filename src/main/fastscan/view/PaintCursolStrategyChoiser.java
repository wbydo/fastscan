package fastscan.view;

import fastscan.model.MainModel;
import fastscan.model.CursolModeModel;
import fastscan.model.ConfigModel;

import fastscan.view.paintcursol.*;

class PaintCursolStrategyChoiser{
  static PaintCursol get(MainModel m){
    switch(m.getCurrentMode()){
      case SELECT_COLUMN:
        return new ColumnPainter(m.getX(), false);
      case CONFIRM_COLUMN:
        return new ColumnPainter(m.getX(), ConfigModel.isChangedColor());
      case SELECT_CHAR:
        return new CharPainter(m.getX(), m.getY(), false);
      case CONFIRM_CHAR:
        return new CharPainter(m.getX(), m.getY(), ConfigModel.isChangedColor());
      default: throw new IllegalStateException(
        "Mode isn't Exist."
      );
    }
  }
}
