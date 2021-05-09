package restaurant.view;

import restaurant.data.Table;

import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.sql.*;
import javax.swing.*;

public class TablePicker extends JPanel {

  private DefaultComboBoxModel tableModel = new DefaultComboBoxModel<Table>();
  private JLabel labelUsername = new JLabel("Your email ");
  private JLabel labelTable = new JLabel("Your table ");
  private JTextField textUsername = new JTextField(50);
  private JComboBox tableCombo = new JComboBox();
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

    constraints.gridx = 0;
    constraints.gridy = 1;
    add(labelTable, constraints);


    tableCombo.setModel(tableModel);
    fillthetable();
    constraints.gridx = 1;
    add(tableCombo, constraints);

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
        BorderFactory.createEtchedBorder(), "welcome"));

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
  private void fillthetable() {
    String url = "jdbc:mysql://localhost:3306/zeynep";
    String username = "root";
    String password = "projeödevim";


    try (Connection connection = DriverManager.getConnection(url, username, password);
         PreparedStatement preparedStatement = connection
                 .prepareStatement("select * from zeynep.table");
         ResultSet resultSet = preparedStatement.executeQuery()) {
      while (resultSet.next()) {
        tableModel.addElement(new Table(resultSet.getInt("id"), resultSet.getInt("size"),
                resultSet.getInt("table_no")));
      }
    } catch (SQLException e) {
      System.out.println("not good" + e.getMessage());
    }
  }
}
