package restaurant.view;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextField;
import restaurant.EasyOrder;
import restaurant.data.Table;

public final class AdminPage extends JPanel {

  private EasyOrder parent;
  private JList list = new JList();
  private DefaultListModel model = new DefaultListModel<Table>();
  private JButton saveButton = new JButton("Save");
  private JButton addNewButton = new JButton("New");
  private JButton deleteButton = new JButton("Delete");
  private JLabel tableIdLabel = new JLabel("Table Id:");
  private JTextField tableIdText = new JTextField();
  private JLabel tableNoLabel = new JLabel("Table No:");
  private JTextField tableNoText = new JTextField();
  private JLabel tableSizeLabel = new JLabel("Size:");
  private JTextField tableSizeText = new JTextField();

  public AdminPage(EasyOrder parent) {
    super(new GridBagLayout());
    this.parent = parent;
    list.setModel(model);
    model.addAll(parent.allTable());
    list.getSelectionModel()
            .addListSelectionListener(
                    e -> {
                      if (!list.isSelectionEmpty()) {
                        Table p = (Table) list.getSelectedValue();
                        tableIdText.setText(p.getTableId() + "");
                        tableNoText.setText(p.getTableNo() + "");
                        tableSizeText.setText(p.getSize() + "");
                      }
                    });

    GridBagConstraints constraints = new GridBagConstraints();
    constraints.anchor = GridBagConstraints.WEST;
    constraints.insets = new Insets(10, 10, 10, 10);

    constraints.ipadx = 25;
    constraints.gridx = 0;
    constraints.gridy = 0;
    add(tableIdLabel, constraints);
    constraints.gridx = 1;
    constraints.gridy = 0;
    add(tableIdText, constraints);

    constraints.gridx = 0;
    constraints.gridy = 1;
    add(tableNoLabel, constraints);
    constraints.gridx = 1;
    constraints.gridy = 1;
    add(tableNoText, constraints);

    constraints.gridx = 0;
    constraints.gridy = 2;
    add(tableSizeLabel, constraints);
    constraints.gridx = 1;
    constraints.gridy = 2;
    add(tableSizeText, constraints);

    constraints.gridx = 0;
    constraints.gridy = 9;
    add(saveButton, constraints);
    constraints.gridx = 1;
    constraints.gridy = 9;
    add(addNewButton, constraints);
    constraints.gridx = 2;
    constraints.gridy = 9;
    add(deleteButton, constraints);

    constraints.gridx = 0;
    constraints.gridy = 10;
    constraints.ipady = 150;
    add(list, constraints);

    setSize(WIDTH, HEIGHT);
    setBorder(
            BorderFactory.createTitledBorder(
                    BorderFactory.createEtchedBorder(), "Add / Update / Delete Table"));

    addActions();
    setVisible(true);
  }

  private void addActions() {
    deleteButton.addActionListener(
            new ActionListener() {
              public void actionPerformed(ActionEvent evt) {
                deleteTableFromDatabase((Table) list.getSelectedValue());
                model.clear();
                model.addAll(parent.allTable());
              }
            });
    saveButton.addActionListener(
            new ActionListener() {
              public void actionPerformed(ActionEvent evt) {
                int id = Integer.parseInt(tableIdText.getText());
                int no = Integer.parseInt(tableNoText.getText());
                int size = Integer.parseInt(tableSizeText.getText());
                updateTable(id, no, size);
                model.clear();
                model.addAll(parent.allTable());
              }
            });
    addNewButton.addActionListener(
            new ActionListener() {
              public void actionPerformed(ActionEvent evt) {
                int no = Integer.parseInt(tableNoText.getText());
                int size = Integer.parseInt(tableSizeText.getText());
                addTable(new Table(0, size, no));
                model.clear();
                model.addAll(parent.allTable());
              }
            });
  }

  private void updateTable(int id, int no, int size) {

    try (Connection connection = parent.databaseConnection();
         PreparedStatement preparedStatement =
                 connection.prepareStatement(
                         "UPDATE `201735038`.`table` SET table_no = "
                                 + no
                                 + ", size = "
                                 + size
                                 + " WHERE id ="
                                 + id)) {
      preparedStatement.executeUpdate();
    } catch (SQLException e) {
      System.out.println("not good" + e.getMessage());
    }
  }

  private void deleteTableFromDatabase(Table table) {
    try (Connection connection = parent.databaseConnection();
         PreparedStatement preparedStatement =
                 connection.prepareStatement(
                         "delete from `201735038`.table where id = " + table.getTableId() + " ")) {
      preparedStatement.executeUpdate();
    } catch (SQLException e) {
      System.out.println("not good" + e.getMessage());
    }
  }

  private void addTable(Table newTable) {

    try (Connection connection = parent.databaseConnection();
         PreparedStatement preparedStatement =
                 connection.prepareStatement(
                         "INSERT INTO `201735038`.`table` (table_no, size) VALUES ("
                                 + newTable.getTableNo()
                                 + ","
                                 + newTable.getSize()
                                 + " )")) {
      preparedStatement.executeUpdate();
    } catch (SQLException e) {
      System.out.println("not good" + e.getMessage());
    }
  }
}
