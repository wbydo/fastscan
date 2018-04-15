package fastscan.view;

import javax.swing.*;

import java.awt.Dimension;
import java.awt.Container;
import java.awt.Color;
import java.awt.Font;

import fastscan.model.MainModel;

class InputField {
  private static JTextArea area;
  private static JLabel label;
  private static MainModel mainModel;

  static void init(MainModel m){
    mainModel = m;

    area = new JTextArea();
    area.setEditable(false);
    area.setLineWrap(true);
    area.setFont(new Font(Font.DIALOG, Font.PLAIN, 20));

    // 2:3の分割 1/2
    // マジックナンバーになっているのであとで直す
    area.setMaximumSize(
      new Dimension(View.WIDTH, View.HEIGHT * 1 / 3)
    );
    area.setBackground(Color.WHITE);

  }

  static void beAddedTo(Container c){
    c.add(area);
  }

  static void repaint(){
    if(!(area.getText()).equals("")){
      area.replaceRange(null, 0, area.getText().length());
    }
    
    area.append(mainModel.getSentence());
    area.repaint();
  }
}
