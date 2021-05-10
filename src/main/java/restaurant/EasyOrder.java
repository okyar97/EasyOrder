package restaurant;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFrame;
import restaurant.data.Table;
import restaurant.view.TablePicker;

public class EasyOrder {
  public static final int WIDTH = 600;
  public static final int HEIGHT = 600;

  private String customerName = "";
  private Table customerTable;
  final JFrame jFrame = new JFrame("EasyOrder");

  public EasyOrder() {
    jFrame.setSize(WIDTH, HEIGHT);
    jFrame.add(new TablePicker(this));

    jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    jFrame.setVisible(true);
    jFrame.setLocationRelativeTo(null);
  }

  public JFrame getFrame() {
    return jFrame;
  }

  public static void main(String[] args) {
    new EasyOrder();
  }

  public Connection databaseConnection() throws SQLException {

    String url = "jdbc:mysql://localhost:3306";
    String username = "root";
    String password = "projeödevim";

    return DriverManager.getConnection(url, username, password);
  }

  public List<Table> allTable() {
    List<Table> tables = new ArrayList();
    try (Connection connection = databaseConnection();
         PreparedStatement preparedStatement =
                 connection.prepareStatement("select * from `201735038`.table");
         ResultSet resultSet = preparedStatement.executeQuery()) {
      while (resultSet.next()) {
        tables.add(
                new Table(
                        resultSet.getInt("id"), resultSet.getInt("size"), resultSet.getInt("table_no")));
      }
    } catch (SQLException e) {
      System.out.println("hata! " + e.getMessage());
    }
    return tables;
  }

  public String getCustomer() {
    return customerName;
  }

  public void setCustomer(String text) {
    this.customerName = text;
  }

  public Table getCustomerTable() {
    return customerTable;
  }

  public void setCustomerTable(Table customerTable) {
    this.customerTable = customerTable;
  }
}
