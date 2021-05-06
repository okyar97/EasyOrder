package restaurant.view;

import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Panel;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import restaurant.data.Table;

public final class AdminPage extends Panel {

  private JFrame parent;
  private JList list = new JList();
  private DefaultListModel model = new DefaultListModel<Table>();
  private JButton nextButton = new JButton("Next");
  private JLabel label = new JLabel();
  private JPanel panel = new JPanel();
  private JSplitPane splitPane = new JSplitPane();

  public AdminPage() {
    super(new GridBagLayout());

    populateTables();
    list.setModel(model);

    list.getSelectionModel().addListSelectionListener(e -> {
      Table p = (Table) list.getSelectedValue();
      label.setText("Table No: " + p.getTableNo() + " size:" + p.getSize());
    });

    panel.add(label);

    splitPane.setLeftComponent(new JScrollPane(list));
    splitPane.setRightComponent(panel);

    GridBagConstraints constraints = new GridBagConstraints();
    constraints.anchor = GridBagConstraints.WEST;
    constraints.insets = new Insets(10, 10, 10, 10);

    constraints.gridx = 0;
    constraints.gridy = 0;
    constraints.gridwidth = 10;
    add(splitPane, constraints);

    constraints.gridx = 1;
    constraints.gridy = 1;
    add(nextButton, constraints);
    setSize(WIDTH, HEIGHT);
  }

  private void populateTables() {
    String url = "jdbc:mysql://localhost:3306/zeynep?serverTimezone=UTC";
    String username = "root";
    String password = "projeödevim";
    list = new JList<Table>();

    try (Connection connection = DriverManager.getConnection(url, username, password);
        PreparedStatement preparedStatement = connection
            .prepareStatement("select * from zeynep.table");
        ResultSet resultSet = preparedStatement.executeQuery()) {
      while (resultSet.next()) {
        model.addElement(new Table(resultSet.getInt("id"), resultSet.getInt("size"),
            resultSet.getInt("table_no")));
      }
    } catch (SQLException e) {
      System.out.println("not good" + e.getMessage());
    }
  }

  public Component setParent(JFrame parent) {
    this.parent = parent;
    return this;
  }

}