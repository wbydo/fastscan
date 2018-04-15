package fastscan.view;

import javax.swing.*;

import java.awt.Dimension;
import java.awt.Container;
import java.awt.Color;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import fastscan.model.MainModel;
import fastscan.model.ConfigModel;
import fastscan.model.ScanModel;
import fastscan.model.CursolModeModel;

public class View {
  public static final int WIDTH  = 600;
  public static final int HEIGHT = 500;
  public static final String TITLE = ConfigModel.TITLE;

  private static final JFrame frame = new JFrame(TITLE);;
  private static final Container contentPane = frame.getContentPane();
  private static final JMenuBar menubar = new JMenuBar();
  private static MainModel mainModel;

  public static void init(MainModel m){
    mainModel = m;
    Dimension d = new Dimension(WIDTH, HEIGHT);
    contentPane.setPreferredSize(d);

    BoxLayout layout = new BoxLayout(contentPane, BoxLayout.Y_AXIS);
    contentPane.setLayout(layout);

    frame.setJMenuBar(menubar);

    JMenu menu = new JMenu("Option");
    menubar.add(menu);

    JMenuItem item1 = new JMenuItem("スキャン速度変更");
    item1.addActionListener(
      new ActionListener(){
        @Override
        public void actionPerformed(ActionEvent e){
          cancelScan();
          String s = JOptionPane.showInputDialog(
            frame,
            "スキャン速度を入力してください。[ms]",
            ConfigModel.getScanSpeed()
          );

          if(s != null){
            long value = Long.parseLong(s);
            ConfigModel.setScanSpeed(value);
          }
          startScan();
        }
      }
    );
    menu.add(item1);

    JMenuItem item2 = new JMenuItem("正確なクリック時間");
    item2.addActionListener(
      new ActionListener(){
        @Override
        public void actionPerformed(ActionEvent e){
          cancelScan();
          String s = JOptionPane.showInputDialog(
            frame,
            "選択時間のうち確認の不要な時間の割合を入力してください。",
            ConfigModel.getCorrectClickRate()
          );

          if (s != null){
            float value = Float.parseFloat(s);
            ConfigModel.setCorrectClickRate(value);
          }
          startScan();
        }
      }
    );
    menu.add(item2);

    JMenuItem item3 = new JMenuItem("ダブルクリックの間隔");
    item3.addActionListener(
      new ActionListener(){
        @Override
        public void actionPerformed(ActionEvent e){
          cancelScan();
          String s = JOptionPane.showInputDialog(
            frame,
            "ダブルクリックの間隔を入力してください。[ms]",
            ConfigModel.getDoubleClickInterval()
          );

          if (s != null){
            long value = Long.parseLong(s);
            ConfigModel.setDoubleClickInterval(value);
          }
          startScan();
        }
      }
    );
    menu.add(item3);

    menu.addSeparator();

    final JMenuItem item4 = new JCheckBoxMenuItem("確認時にカーソルの色を変える", true);
    item4.addActionListener(
      new ActionListener(){
        @Override
        public void actionPerformed(ActionEvent e){
          if(item4.isSelected()){
            item4.setSelected(true);
            ConfigModel.setChangedColor(true);
          }
          else{
            item4.setSelected(false);
            ConfigModel.setChangedColor(false);
          }
        }
      }
    );
    menu.add(item4);

    JMenuItem menu2 = new JMenuItem("Reset");
    menu2.addActionListener(
      new ActionListener(){
        @Override
        public void actionPerformed(ActionEvent e){
          ScanModel.cancelScan();
          mainModel.resetXY();
          mainModel.resetSentence();
          mainModel.setCurrentMode(CursolModeModel.SELECT_COLUMN);
          ScanModel.startScan();
        }
      }
    );
    menubar.add(menu2);


    InputField.init(mainModel);
    InputField.beAddedTo(contentPane);

    CharTableView.init(mainModel);
    CharTableView.beAddedTo(contentPane);

    frame.addMouseListener(
      new MouseAdapter(){
        @Override
        public void mousePressed(MouseEvent e){
          ScanModel.mousePressed();
        }
      }
    );

    frame.pack();

    CharTableView.setBlockSize();
    frame.setLocationRelativeTo(null);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setVisible(true);

  }

  // コンストラクタ使用禁止
  private View(){
  }

  private static void repaintCharTable(){
    CharTableView.repaint();
  }

  private static void repaintInputField(){
    InputField.repaint();
  }

  public static void repaint(){
    repaintCharTable();
    repaintInputField();
  }

  private static void cancelScan(){
    CursolModeModel m = mainModel.getCurrentMode();
    switch(m){
      case SELECT_COLUMN:
      case SELECT_CHAR:
        ScanModel.cancelScan();
        break;
      default:
        break;
    }
  }

  private static void startScan(){
    CursolModeModel m = mainModel.getCurrentMode();
    switch(m){
      case SELECT_COLUMN:
      case SELECT_CHAR:
        ScanModel.startScan();
        break;
      default:
        break;
    }
  }
}
