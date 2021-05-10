package restaurant.view;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import restaurant.EasyOrder;
import restaurant.data.Table;

public class TablePicker extends JPanel {

  private DefaultComboBoxModel tableModel = new DefaultComboBoxModel<Table>();
  private JLabel labelUsername = new JLabel("Your email ");
  private JLabel labelTable = new JLabel("Your table ");
  private JTextField textUsername = new JTextField(50);
  private JComboBox tableCombo = new JComboBox();
  private JButton buttonLogin = new JButton("Next");
  private JButton buttonAdmin = new JButton();
  private EasyOrder parent;

  public TablePicker(EasyOrder parent) {
    super(new GridBagLayout());
    this.parent = parent;
    GridBagConstraints constraints = new GridBagConstraints();
    constraints.anchor = GridBagConstraints.WEST;
    constraints.insets = new Insets(10, 10, 10, 10);

    // add components to the panel
    constraints.gridx = 0;
    constraints.gridy = 0;
    add(labelUsername, constraints);

    constraints.gridx = 1;
    add(textUsername, constraints);

    constraints.gridx = 0;
    constraints.gridy = 1;
    add(labelTable, constraints);

    tableCombo.setModel(tableModel);
    tableModel.addAll(parent.allTable());
    constraints.gridx = 1;
    add(tableCombo, constraints);

    constraints.gridx = 0;
    constraints.gridy = 2;
    constraints.gridwidth = 2;
    constraints.anchor = GridBagConstraints.CENTER;
    add(buttonLogin, constraints);

    constraints.gridx = 0;
    constraints.gridy = 5;
    constraints.anchor = GridBagConstraints.SOUTHWEST;
    buttonAdmin.setBackground(null);
    buttonAdmin.setIcon(new ImageIcon("src/main/resources/admin.png"));
    add(buttonAdmin, constraints);

    setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "welcome"));

    initActions();
  }

  private void initActions() {
    buttonAdmin.addActionListener(
            actionEvent -> {
              parent.getFrame().remove(this);
              parent.getFrame().add(new AdminPage(parent));
              parent.getFrame().revalidate();
            });
  }


}
