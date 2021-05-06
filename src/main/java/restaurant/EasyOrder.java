package restaurant;

import javax.swing.JFrame;
import restaurant.view.TablePicker;

public class EasyOrder {
  public static final int WIDTH = 400;
  public static final int HEIGHT = 250;
  
  public EasyOrder() {
    final JFrame jFrame = new JFrame("");
    jFrame.setSize(WIDTH, HEIGHT);
    jFrame.add(new TablePicker().setParent(jFrame));

    jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    jFrame.setVisible(true);
    jFrame.setLocationRelativeTo(null);
  }

  public static void main(String[] args) {
    new EasyOrder();
  }
}
