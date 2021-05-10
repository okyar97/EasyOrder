package restaurant.view;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultListModel;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import restaurant.EasyOrder;
import restaurant.data.Menuitem;

public class OrderSelection extends JPanel {

    private final EasyOrder parent;
    private DefaultListModel menuItemModel = new DefaultListModel<Menuitem>();
    private DefaultListModel orderModel = new DefaultListModel<Menuitem>();
    private JLabel nameLabel = new JLabel();
    private JLabel priceLabel = new JLabel();
    private JLabel menuLabel = new JLabel("Menu");
    private JLabel orderLabel = new JLabel("Your Order");
    private JList menuItemList = new JList();
    private JList orderList = new JList();

    public OrderSelection(EasyOrder parent) {
        super(new GridBagLayout());
        this.parent = parent;
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.anchor = GridBagConstraints.PAGE_START;
        constraints.insets = new Insets(10, 10, 10, 10);

        constraints.ipadx = 25;
        constraints.gridx = 0;
        constraints.gridy = 0;
        nameLabel.setText(
                "Welcome "
                        + parent.getCustomer()
                        + ". Your table is #"
                        + parent.getCustomerTable().getTableNo());
        add(nameLabel, constraints);

        constraints.gridx = 0;
        constraints.gridy = 1;
        add(menuLabel, constraints);

        constraints.gridx = 1;
        constraints.gridy = 1;
        add(orderLabel, constraints);

        constraints.gridx = 0;
        constraints.gridy = 2;
        menuItemList.setModel(menuItemModel);
        menuItemModel.addAll(allFood());
        add(menuItemList, constraints);
        menuItemList
                .getSelectionModel()
                .addListSelectionListener(
                        e -> {
                            if (!menuItemList.isSelectionEmpty() && menuItemList.getValueIsAdjusting()) {
                                Menuitem item = (Menuitem) menuItemList.getSelectedValue();
                                orderModel.addElement(item);
                                priceLabel.setText(getTotal());
                            }
                        });
        constraints.gridx = 1;
        constraints.gridy = 2;
        add(orderList, constraints);
        orderList.setModel(orderModel);
        orderList
                .getSelectionModel()
                .addListSelectionListener(
                        e -> {
                            if (!orderList.isSelectionEmpty() && orderList.getValueIsAdjusting()) {
                                Menuitem item = (Menuitem) orderList.getSelectedValue();
                                orderModel.removeElement(item);
                                priceLabel.setText(getTotal());
                            }
                        });
        constraints.gridx = 1;
        constraints.gridy = 3;
        add(priceLabel, constraints);
    }

    private String getTotal() {
        double price = 0;
        for (int i = 0; i < orderList.getModel().getSize(); i++) {
            Menuitem menuitem = (Menuitem) orderList.getModel().getElementAt(i);
            price += menuitem.getPrice();
        }
        return price + " TL.";
    }

    public List<Menuitem> allFood() {
        List<Menuitem> menuitems = new ArrayList();
        try (Connection connection = parent.databaseConnection();
             PreparedStatement preparedStatement =
                     connection.prepareStatement(
                             "select mg.groupname,mi.id, mi.name, mi.price from `201735038`.menuitem mi join `201735038`.menu_group mg on mg.id = mi.typeid");
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                menuitems.add(
                        new Menuitem(
                                resultSet.getInt("mi.id"),
                                resultSet.getString("mi.name"),
                                resultSet.getDouble("mi.price"),
                                resultSet.getString("mg.groupname")));
            }
        } catch (SQLException e) {
            System.out.println("hata! " + e.getMessage());
        }
        return menuitems;
    }
}
