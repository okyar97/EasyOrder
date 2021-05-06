package restaurant.data;

public class Table {

  int tableId;
  int size;
  int tableNo;

  public Table(int tableId, int size, int tableNo) {
    this.tableId = tableId;
    this.size = size;
    this.tableNo = tableNo;
  }

  public int getTableId() {
    return tableId;
  }

  public void setTableId(int tableId) {
    this.tableId = tableId;
  }

  public int getSize() {
    return size;
  }

  public void setSize(int size) {
    this.size = size;
  }

  public int getTableNo() {
    return tableNo;
  }

  public void setTableNo(int tableNo) {
    this.tableNo = tableNo;
  }

  @Override
  public String toString() {
    return "Table#" + tableNo +
        " size: " + size;
  }
}
