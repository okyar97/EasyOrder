package restaurant.view;

import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class TablePicker extends JPanel {

  private JLabel labelUsername = new JLabel("Your name: ");
  private JTextField textUsername = new JTextField(20);
  private JPasswordField fieldPassword = new JPasswordField(20);
  private JButton buttonLogin = new JButton("Next");
  private JButton buttonAdmin = new JButton();
  private JFrame parent;

  public TablePicker() {
    super(new GridBagLayout());
    GridBagConstraints constraints = new GridBagConstraints();
    constraints.anchor = GridBagConstraints.WEST;
    constraints.insets = new Insets(10, 10, 10, 10);

    // add components to the panel
    constraints.gridx = 0;
    constraints.gridy = 0;
    add(labelUsername, constraints);

    constraints.gridx = 1;
    add(textUsername, constraints);

    constraints.gridx = 1;
    add(fieldPassword, constraints);

    constraints.gridx = 0;
    constraints.gridy = 2;
    constraints.gridwidth = 2;
    constraints.anchor = GridBagConstraints.CENTER;
    add(buttonLogin, constraints);

    constraints.gridx = 0;
    constraints.gridy = 5;
    constraints.gridwidth = 1;
    constraints.anchor = GridBagConstraints.SOUTHWEST;
    buttonAdmin.setBackground(null);
    buttonAdmin.setIcon(new ImageIcon("src/main/resources/admin.png"));
    add(buttonAdmin, constraints);

    setBorder(BorderFactory.createTitledBorder(
        BorderFactory.createEtchedBorder(), "Please select your table"));

    initActions();
  }

  public Component setParent(JFrame parent) {
    this.parent = parent;
    return this;
  }

  private void initActions() {
    buttonAdmin.addActionListener(actionEvent -> {
      parent.remove(this);
      parent.add(new AdminPage().setParent(parent));
      parent.repaint();
    });
  }
}
