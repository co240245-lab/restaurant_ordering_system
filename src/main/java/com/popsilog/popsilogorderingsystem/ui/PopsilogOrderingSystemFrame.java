package com.popsilog.popsilogorderingsystem.ui;

import com.popsilog.popsilogorderingsystem.model.FoodItem;
import com.popsilog.popsilogorderingsystem.model.InventoryItem;
import com.popsilog.popsilogorderingsystem.model.Order;
import com.popsilog.popsilogorderingsystem.model.OrderItem;
import com.popsilog.popsilogorderingsystem.model.RestaurantTable;
import com.popsilog.popsilogorderingsystem.model.User;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.net.URL;
import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class PopsilogOrderingSystemFrame extends JFrame {

    private static final Color COLOR_PRIMARY = new Color(200, 80, 10);
    private static final Color COLOR_BACKGROUND = new Color(245, 246, 248);
    private static final Color COLOR_CARD_BG = Color.WHITE;
    private static final Color COLOR_TEXT_DARK = new Color(40, 40, 40);
    private static final Color COLOR_ACCENT_ORANGE = new Color(225, 90, 15);
    private static final Color COLOR_LIGHT_ORANGE = new Color(255, 224, 178);
    private static final Color COLOR_GREEN = new Color(39, 174, 96);

    private MenuRepository menuRepo;
    private List<InventoryItem> inventoryList;
    private List<RestaurantTable> tableList;
    private Order currentOrder;
    private List<Order> activeKitchenOrders;
    private DecimalFormat currencyFormat;
    private List<User> userList;
    private User currentUser;

    private JTabbedPane mainTabs;
    private JLabel userSessionLabel;
    private JPanel menuGridPanel;
    private JTable cartTable;
    private DefaultTableModel cartTableModel;
    private JLabel subtotalValLabel;
    private JLabel totalValLabel;
    private JCheckBox seniorDiscountCheckBox;
    private JTextField cashReceivedField;
    private JLabel changeValLabel;
    private JPanel cartCardContainer;
    private CardLayout cartCardLayout;
    private JPanel kitchenTicketsContainer;
    private JLabel pendingCountBadge;
    private DefaultTableModel inventoryTableModel;
    private JPanel tablesGridPanel;
    private JComboBox<String> tableSelectCombo;
    private JComboBox<String> eggPrepCombo;
    private JComboBox<String> riceChoiceCombo;
    private JLabel orderIdLabel;

    private JPanel posViewPanel;
    private JPanel kitchenViewPanel;
    private JPanel tablesViewPanel;
    private JPanel inventoryViewPanel;
    private JPanel settingsViewPanel;

    public PopsilogOrderingSystemFrame() {
        menuRepo = new MenuRepository();
        activeKitchenOrders = new ArrayList<>();
        currencyFormat = new DecimalFormat("₱#,##0.00");

        initUsers();
        initInventoryData();
        initTablesData();

        currentOrder = new Order("Table 1", "Walk-in Customer");
        initGUI();
    }

    private void initUsers() {
        userList = new ArrayList<>();
        userList.add(new User("admin", "admin_", "Manager Admin", "Admin"));
        userList.add(new User("cashier", "cashier_", "Staff", "Cashier"));
    }

    private void initInventoryData() {
        inventoryList = new ArrayList<>();
        inventoryList.add(new InventoryItem("EGG", "Raw Ingredients", 120.0, 20.0, "pcs", "Low Stock"));
        inventoryList.add(new InventoryItem("CHICKEN POPS", "Raw Ingredients", 15.5, 5.0, "kg", "In Stock"));
        inventoryList.add(new InventoryItem("BEEF MEAT", "Raw Ingredients", 25.0, 5.0, "kg", "In Stock"));
        inventoryList.add(new InventoryItem("RICE", "Raw Ingredients", 50.0, 10.0, "kg", "In Stock"));
        inventoryList.add(new InventoryItem("WHITE SAUCE", "Raw Ingredients", 5.0, 2.0, "kg", "In Stock"));
        inventoryList.add(new InventoryItem("PEPPER", "Raw Ingredients", 2.0, 0.5, "kg/g", "No Stock"));
        inventoryList.add(new InventoryItem("SALT", "Raw Ingredients", 5.0, 1.0, "kg/g", "No Stock"));
        inventoryList.add(new InventoryItem("SPRING ONION", "Raw Ingredients", 1.5, 0.5, "kg/grams", "In Stock"));
        inventoryList.add(new InventoryItem("FRIED GARLIC", "Raw Ingredients", 3.0, 1.0, "kg/g", "Low stock"));
        inventoryList.add(new InventoryItem("CALAMANSI", "Raw Ingredients", 4.0, 1.0, "kg/g", "Low Stock"));
        inventoryList.add(new InventoryItem("VINEGAR", "Raw Ingredients", 10.0, 2.0, "liters", "In Stock"));

        inventoryList.add(new InventoryItem("BASIC PARES", "Prepared Portions", 30.0, 10.0, "portion", "In Stock"));
        inventoryList.add(new InventoryItem("MERCY PARES", "Prepared Portions", 25.0, 8.0, "portion", "Low Stock"));
        inventoryList.add(new InventoryItem("BEEF TAPA", "Prepared Portions", 40.0, 10.0, "portion", "No Stock"));
        inventoryList.add(new InventoryItem("CHICKEN TOCINO", "Prepared Portions", 35.0, 10.0, "portion", "No Stock"));
        inventoryList.add(new InventoryItem("BEEF LONGGANISA", "Prepared Portions", 30.0, 8.0, "portion", "No Stock"));
        inventoryList.add(new InventoryItem("CHIXILOG", "Prepared Portions", 20.0, 5.0, "portion", "Low Stock"));
        inventoryList.add(new InventoryItem("SRIRACHA CHICKEN POPS", "Prepared Portions", 25.0, 5.0, "portion", "In Stock"));
        inventoryList.add(new InventoryItem("SWEET SOY", "Prepared Portions", 25.0, 5.0, "portion", "In Stock"));
    }

    private void initTablesData() {
        tableList = new ArrayList<>();
        tableList.add(new RestaurantTable("T1", "Table 1", "OCCUPIED"));
        tableList.add(new RestaurantTable("T2", "Table 2", "OCCUPIED"));
        tableList.add(new RestaurantTable("T3", "Table 3", "AVAILABLE"));
        tableList.add(new RestaurantTable("T4", "Table 4", "AVAILABLE"));
        tableList.add(new RestaurantTable("T5", "Table 5", "RESERVED"));
    }

    public void showLoginDialog() {
        JTextField usernameField = new JTextField();
        JPasswordField passwordField = new JPasswordField();

        JPanel panel = new JPanel(new GridLayout(2, 2, 8, 8));
        panel.add(new JLabel("Username:"));
        panel.add(usernameField);
        panel.add(new JLabel("Password:"));
        panel.add(passwordField);

        while (true) {
            int option = JOptionPane.showConfirmDialog(
                this, panel, "Popsilog System Login",
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE
            );

            if (option == JOptionPane.OK_OPTION) {
                String inputUser = usernameField.getText().trim();
                String inputPass = new String(passwordField.getPassword());
                User authenticated = null;

                for (User u : userList) {
                    if (u.getUsername().equalsIgnoreCase(inputUser) && u.getPassword().equals(inputPass)) {
                        authenticated = u;
                        break;
                    }
                }

                if (authenticated != null) {
                    currentUser = authenticated;
                    updateUserSessionHeader();
                    applyRolePermissions();
                    setVisible(true);
                    break;
                } else {
                    JOptionPane.showMessageDialog(
                        this, "Invalid credentials!",
                        "Login Failed", JOptionPane.ERROR_MESSAGE
                    );
                    usernameField.setText("");
                    passwordField.setText("");
                }
            } else {
                System.exit(0);
            }
        }
    }

    private void applyRolePermissions() {
        if (currentUser == null) return;
        mainTabs.removeAll();

        mainTabs.addTab("New Order (POS)", posViewPanel);
        mainTabs.addTab("Tables & Reservations", tablesViewPanel);
        mainTabs.addTab("Kitchen Queue", kitchenViewPanel);

        if ("Admin".equalsIgnoreCase(currentUser.getRole())) {
            mainTabs.addTab("Inventory", inventoryViewPanel);
            mainTabs.addTab("Settings", settingsViewPanel);
        }
    }

    private void handleLogout() {
        int confirm = JOptionPane.showConfirmDialog(this, "Are you sure you want to log out?", "Confirm Logout", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            setVisible(false);
            currentUser = null;
            showLoginDialog();
        }
    }

    private void updateUserSessionHeader() {
        if (currentUser != null && userSessionLabel != null) {
            userSessionLabel.setText(
                "<html><div align='right'><b>" + currentUser.getFullName() + "</b><br><font size='2'>"
                + currentUser.getRole().toUpperCase() + " SHIFT #1</font></div></html>"
            );
        }
    }

    private void initGUI() {
        setTitle("Popsilog Sta. Maria Branch");
        setSize(1280, 760);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        getContentPane().setBackground(COLOR_BACKGROUND);
        setLayout(new BorderLayout());

        JPanel topHeader = new JPanel(new BorderLayout());
        topHeader.setBackground(COLOR_PRIMARY);
        topHeader.setPreferredSize(new Dimension(1280, 60));
        topHeader.setBorder(new EmptyBorder(8, 15, 8, 15));

        JLabel brandLabel = new JLabel(
            "<html><font color='white' size='5'><b>POPSILOG</b></font> "
            + "<font color='#FFE0B2' size='3'>[STA. MARIA BRANCH]</font>"
            + "<br><font color='#FFF3E0' size='2'>Zamboanga's Best Tasting Pares & Silog</font></html>"
        );

        JPanel statusRight = new JPanel(new FlowLayout(FlowLayout.RIGHT, 12, 0));
        statusRight.setOpaque(false);

        JLabel posStatus = new JLabel("● POS Online");
        posStatus.setFont(new Font("SansSerif", Font.BOLD, 12));
        posStatus.setForeground(new Color(144, 238, 144));

        userSessionLabel = new JLabel();
        userSessionLabel.setForeground(Color.WHITE);

        JButton logoutBtn = new JButton("Logout");
        logoutBtn.addActionListener(e -> handleLogout());

        statusRight.add(posStatus);
        statusRight.add(userSessionLabel);
        statusRight.add(logoutBtn);

        topHeader.add(brandLabel, BorderLayout.WEST);
        topHeader.add(statusRight, BorderLayout.EAST);
        add(topHeader, BorderLayout.NORTH);

        posViewPanel = createPOSView();
        kitchenViewPanel = createKitchenView();
        tablesViewPanel = createTablesView();
        inventoryViewPanel = createInventoryView();
        settingsViewPanel = createSettingsView();

        mainTabs = new JTabbedPane();
        add(mainTabs, BorderLayout.CENTER);
    }

    private JPanel createPOSView() {
        JPanel posView = new JPanel(new BorderLayout(10, 10));
        posView.setBackground(COLOR_BACKGROUND);
        posView.setBorder(new EmptyBorder(10, 10, 10, 10));

        JPanel leftContainer = new JPanel(new BorderLayout(10, 10));
        leftContainer.setOpaque(false);

        JPanel filterBar = new JPanel(new FlowLayout(FlowLayout.LEFT, 8, 5));
        filterBar.setOpaque(false);

        String[] cats = {"All", "Pares", "Silog", "Chicken Pops", "Beverage", "Add-Ons"};
        for (String cat : cats) {
            JButton catBtn = new JButton(cat);
            catBtn.setFont(new Font("SansSerif", Font.BOLD, 12));
            catBtn.addActionListener(e -> renderMenuGrid(cat));
            filterBar.add(catBtn);
        }

        leftContainer.add(filterBar, BorderLayout.NORTH);

        menuGridPanel = new JPanel(new GridLayout(0, 2, 12, 12));
        menuGridPanel.setBackground(COLOR_BACKGROUND);

        JPanel gridWrapper = new JPanel(new BorderLayout());
        gridWrapper.setBackground(COLOR_BACKGROUND);
        gridWrapper.add(menuGridPanel, BorderLayout.NORTH);

        JScrollPane gridScroll = new JScrollPane(gridWrapper);
        gridScroll.setBorder(null);
        gridScroll.getVerticalScrollBar().setUnitIncrement(16);
        leftContainer.add(gridScroll, BorderLayout.CENTER);

        JPanel rightCartPanel = new JPanel(new BorderLayout(8, 8));
        rightCartPanel.setPreferredSize(new Dimension(400, 0));
        rightCartPanel.setBackground(Color.WHITE);
        rightCartPanel.setBorder(new EmptyBorder(12, 12, 12, 12));

        JPanel cartHeader = new JPanel(new BorderLayout(5, 5));
        cartHeader.setOpaque(false);

        orderIdLabel = new JLabel();
        orderIdLabel.setFont(new Font("SansSerif", Font.BOLD, 14));
        updateOrderHeaderLabel();

        JLabel clearOrderLabel = new JLabel("Clear Order");
        clearOrderLabel.setFont(new Font("SansSerif", Font.BOLD, 12));
        clearOrderLabel.setForeground(Color.RED);
        clearOrderLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        clearOrderLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {
                currentOrder.clearOrder();
                refreshCartUI();
            }
        });

        cartHeader.add(orderIdLabel, BorderLayout.WEST);
        cartHeader.add(clearOrderLabel, BorderLayout.EAST);

        JPanel selectorPanel = new JPanel(new GridLayout(2, 3, 5, 2));
        selectorPanel.setOpaque(false);

        selectorPanel.add(new JLabel("Table"));
        selectorPanel.add(new JLabel("Egg Prep"));
        selectorPanel.add(new JLabel("Rice Choice"));

        tableSelectCombo = new JComboBox<>(new String[]{"Table 1", "Table 2", "Table 3", "Table 4", "Table 5"});
        eggPrepCombo = new JComboBox<>(new String[]{"Sunny Side Up", "Scrambled", "Boiled"});
        riceChoiceCombo = new JComboBox<>(new String[]{"Garlic Rice", "Plain Rice"});

        tableSelectCombo.addActionListener(e -> currentOrder.setTableNumber((String) tableSelectCombo.getSelectedItem()));

        selectorPanel.add(tableSelectCombo);
        selectorPanel.add(eggPrepCombo);
        selectorPanel.add(riceChoiceCombo);

        JPanel cartTopContainer = new JPanel(new BorderLayout(0, 8));
        cartTopContainer.setOpaque(false);
        cartTopContainer.add(cartHeader, BorderLayout.NORTH);
        cartTopContainer.add(selectorPanel, BorderLayout.SOUTH);

        String[] cartCols = {"Item", "Qty", "Modify Qty", "Subtotal"};
        cartTableModel = new DefaultTableModel(cartCols, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return column == 2;
            }
        };

        cartTable = new JTable(cartTableModel);
        cartTable.setRowHeight(32);

        cartTable.getColumnModel().getColumn(0).setPreferredWidth(130);
        cartTable.getColumnModel().getColumn(1).setPreferredWidth(35);
        cartTable.getColumnModel().getColumn(2).setPreferredWidth(105);
        cartTable.getColumnModel().getColumn(3).setPreferredWidth(90);

        cartTable.getColumnModel().getColumn(2).setCellRenderer(new CartActionRenderer());
        cartTable.getColumnModel().getColumn(2).setCellEditor(new CartActionEditor());

        cartCardLayout = new CardLayout();
        cartCardContainer = new JPanel(cartCardLayout);
        cartCardContainer.add(new JScrollPane(cartTable), "POPULATED");

        JPanel cartBottom = new JPanel();
        cartBottom.setLayout(new BoxLayout(cartBottom, BoxLayout.Y_AXIS));
        cartBottom.setOpaque(false);

        subtotalValLabel = new JLabel("₱0.00", SwingConstants.RIGHT);
        totalValLabel = new JLabel("₱0.00", SwingConstants.RIGHT);
        totalValLabel.setFont(new Font("SansSerif", Font.BOLD, 18));
        totalValLabel.setForeground(COLOR_ACCENT_ORANGE);

        seniorDiscountCheckBox = new JCheckBox("Senior/PWD Discount (20% + VAT Ex.)");
        seniorDiscountCheckBox.setFont(new Font("SansSerif", Font.BOLD, 11));
        seniorDiscountCheckBox.addActionListener(e -> {
            currentOrder.setSeniorDiscount(seniorDiscountCheckBox.isSelected());
            refreshCartUI();
        });

        cashReceivedField = new JTextField();
        cashReceivedField.setHorizontalAlignment(JTextField.RIGHT);
        changeValLabel = new JLabel("₱0.00", SwingConstants.RIGHT);
        changeValLabel.setFont(new Font("SansSerif", Font.BOLD, 14));
        changeValLabel.setForeground(COLOR_GREEN);

        JPanel quickCashPanel = new JPanel(new GridLayout(1, 4, 5, 0));
        quickCashPanel.setOpaque(false);

        int[] amounts = {100, 200, 500, 1000};
        for (int amt : amounts) {
            JButton btn = new JButton("₱" + amt);
            btn.setFont(new Font("SansSerif", Font.PLAIN, 11));
            btn.addActionListener(e -> {
                cashReceivedField.setText(String.valueOf(amt));
                calculateChange();
            });
            quickCashPanel.add(btn);
        }

        JButton checkoutBtn = new JButton("CHECKOUT");
        checkoutBtn.setOpaque(true);
        checkoutBtn.setContentAreaFilled(true);
        checkoutBtn.setBorderPainted(false);
        checkoutBtn.setFocusPainted(false);
        checkoutBtn.setBackground(COLOR_ACCENT_ORANGE);
        checkoutBtn.setForeground(Color.WHITE);
        checkoutBtn.setFont(new Font("SansSerif", Font.BOLD, 14));
        checkoutBtn.setPreferredSize(new Dimension(380, 42));
        checkoutBtn.setMaximumSize(new Dimension(380, 42));
        checkoutBtn.addActionListener(e -> handleCheckout());

        JPanel subRow = new JPanel(new BorderLayout()); subRow.setOpaque(false); subRow.add(new JLabel("Subtotal:"), BorderLayout.WEST); subRow.add(subtotalValLabel, BorderLayout.EAST);
        JPanel totRow = new JPanel(new BorderLayout()); totRow.setOpaque(false); totRow.add(new JLabel("TOTAL AMOUNT:"), BorderLayout.WEST); totRow.add(totalValLabel, BorderLayout.EAST);
        JPanel cashRow = new JPanel(new BorderLayout(5, 0)); cashRow.setOpaque(false); cashRow.add(new JLabel("Cash Received (₱)"), BorderLayout.WEST); cashRow.add(cashReceivedField, BorderLayout.CENTER);
        JPanel chgRow = new JPanel(new BorderLayout()); chgRow.setOpaque(false); chgRow.add(new JLabel("Change Due:"), BorderLayout.WEST); chgRow.add(changeValLabel, BorderLayout.EAST);

        cartBottom.add(subRow);
        cartBottom.add(Box.createVerticalStrut(4));
        cartBottom.add(seniorDiscountCheckBox);
        cartBottom.add(Box.createVerticalStrut(4));
        cartBottom.add(totRow);
        cartBottom.add(Box.createVerticalStrut(6));
        cartBottom.add(cashRow);
        cartBottom.add(Box.createVerticalStrut(6));
        cartBottom.add(quickCashPanel);
        cartBottom.add(Box.createVerticalStrut(6));
        cartBottom.add(chgRow);
        cartBottom.add(Box.createVerticalStrut(8));
        cartBottom.add(checkoutBtn);

        rightCartPanel.add(cartTopContainer, BorderLayout.NORTH);
        rightCartPanel.add(cartCardContainer, BorderLayout.CENTER);
        rightCartPanel.add(cartBottom, BorderLayout.SOUTH);

        posView.add(leftContainer, BorderLayout.CENTER);
        posView.add(rightCartPanel, BorderLayout.EAST);

        renderMenuGrid("All");
        return posView;
    }

    private void updateOrderHeaderLabel() {
        if (orderIdLabel != null && currentOrder != null) {
            orderIdLabel.setText("<html><b>" + currentOrder.getOrderId() + "</b> | Walk-in Customer</html>");
        }
    }

    private void calculateChange() {
        try {
            double cash = Double.parseDouble(cashReceivedField.getText().trim());
            double total = currentOrder.calculateFinalTotal();
            double change = cash - total;
            if (change >= 0) {
                changeValLabel.setText(currencyFormat.format(change));
            } else {
                changeValLabel.setText("₱0.00");
            }
        } catch (NumberFormatException ignored) {}
    }

    private void renderMenuGrid(String category) {
        menuGridPanel.removeAll();
        for (FoodItem item : menuRepo.getByCategory(category)) {
            JPanel card = new JPanel(new BorderLayout(8, 8));
            card.setBackground(COLOR_CARD_BG);
            card.setBorder(BorderFactory.createCompoundBorder(
                new LineBorder(new Color(230, 230, 230), 1, true),
                new EmptyBorder(10, 10, 10, 10)
            ));
            card.setPreferredSize(new Dimension(260, 170));

            JLabel nameLabel = new JLabel("<html><b>" + item.getName() + "</b></html>");
            nameLabel.setFont(new Font("SansSerif", Font.BOLD, 13));

            JLabel badgeLabel = new JLabel();
            badgeLabel.setOpaque(true);
            badgeLabel.setFont(new Font("SansSerif", Font.BOLD, 10));

            if (item.getBadge() != null) {
                badgeLabel.setText(" " + item.getBadge() + " ");
                badgeLabel.setBackground(new Color(255, 243, 224));
                badgeLabel.setForeground(COLOR_ACCENT_ORANGE);
            }

            JPanel cardTop = new JPanel(new BorderLayout());
            cardTop.setOpaque(false);
            cardTop.add(nameLabel, BorderLayout.CENTER);
            cardTop.add(badgeLabel, BorderLayout.EAST);

            JPanel centerPanel = new JPanel(new BorderLayout(8, 5));
            centerPanel.setOpaque(false);

            JLabel descLabel = new JLabel("<html><font color='#757575' size='2'>" + item.getDescription() + "</font></html>");
            centerPanel.add(descLabel, BorderLayout.CENTER);

            if (item.getImageFileName() != null) {
                JLabel imageHolder = new JLabel();
                imageHolder.setPreferredSize(new Dimension(110, 90));
                ImageIcon icon = loadScaledImage(item.getImageFileName(), 110, 90);
                if (icon != null) {
                    imageHolder.setIcon(icon);
                    centerPanel.add(imageHolder, BorderLayout.WEST);
                }
            }

            JLabel priceLabel = new JLabel(currencyFormat.format(item.getBasePrice()));
            priceLabel.setFont(new Font("SansSerif", Font.BOLD, 14));
            priceLabel.setForeground(COLOR_ACCENT_ORANGE);

            JButton addBtn = new JButton("+ Add");
            addBtn.setFont(new Font("SansSerif", Font.BOLD, 12));
            addBtn.setBackground(COLOR_LIGHT_ORANGE);
            addBtn.setForeground(COLOR_TEXT_DARK);
            addBtn.addActionListener(e -> addItemToCart(item));

            JPanel cardBottom = new JPanel(new BorderLayout());
            cardBottom.setOpaque(false);
            cardBottom.add(priceLabel, BorderLayout.WEST);
            cardBottom.add(addBtn, BorderLayout.EAST);

            card.add(cardTop, BorderLayout.NORTH);
            card.add(centerPanel, BorderLayout.CENTER);
            card.add(cardBottom, BorderLayout.SOUTH);

            menuGridPanel.add(card);
        }
        menuGridPanel.revalidate();
        menuGridPanel.repaint();
    }

    private ImageIcon loadScaledImage(String fileName, int width, int height) {
        try {
            String path = "/images/" + fileName;
            URL imgUrl = getClass().getResource(path);
            if (imgUrl != null) {
                ImageIcon icon = new ImageIcon(imgUrl);
                Image scaled = icon.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
                return new ImageIcon(scaled);
            } else {
                System.err.println("Resource not found at: " + path);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private void addItemToCart(FoodItem item) {
        for (OrderItem oi : currentOrder.getItems()) {
            if (oi.getItem().getName().equalsIgnoreCase(item.getName())) {
                oi.setQuantity(oi.getQuantity() + 1);
                refreshCartUI();
                return;
            }
        }

        try {
            String eggStyle = (String) eggPrepCombo.getSelectedItem();
            String riceChoice = (String) riceChoiceCombo.getSelectedItem();
            OrderItem orderItem = new OrderItem(item, 1, eggStyle, riceChoice);
            currentOrder.addItem(orderItem);
            refreshCartUI();
        } catch (IllegalArgumentException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage());
        }
    }

    private void refreshCartUI() {
        cartTableModel.setRowCount(0);
        for (OrderItem oi : currentOrder.getItems()) {
            cartTableModel.addRow(new Object[]{
                oi.getItem().getName(), oi.getQuantity(), "",
                currencyFormat.format(oi.getSubtotal())
            });
        }

        double subtotal = currentOrder.calculateRawSubtotal();
        double finalTotal = currentOrder.calculateFinalTotal();

        subtotalValLabel.setText(currencyFormat.format(subtotal));
        totalValLabel.setText(currencyFormat.format(finalTotal));
        calculateChange();
    }

    private void handleCheckout() {
        try {
            if (currentOrder.getItems().isEmpty()) throw new IllegalStateException("Cart is empty!");
            double cash = Double.parseDouble(cashReceivedField.getText().trim());
            double total = currentOrder.calculateFinalTotal();

            if (cash < total) {
                JOptionPane.showMessageDialog(this, "Insufficient cash received!", "Payment Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            double change = cash - total;
            changeValLabel.setText(currencyFormat.format(change));
            activeKitchenOrders.add(currentOrder);
            updateKitchenQueueUI();

            showReceiptDialog(cash, change);

            currentOrder = new Order((String) tableSelectCombo.getSelectedItem(), "Walk-in Customer");
            updateOrderHeaderLabel();

            seniorDiscountCheckBox.setSelected(false);
            refreshCartUI();
            cashReceivedField.setText("");
            changeValLabel.setText("₱0.00");

        } catch (IllegalStateException | NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Checkout Error", JOptionPane.WARNING_MESSAGE);
        }
    }

    private void showReceiptDialog(double cash, double change) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        String nowFormatted = LocalDateTime.now().format(dtf);

        StringBuilder receipt = new StringBuilder();
        receipt.append("=========================================\n");
        receipt.append("       POPSILOG - STA. MARIA BRANCH      \n");
        receipt.append("     Zamboanga's Best Pares & Silog Place \n");
        receipt.append("=========================================\n");
        receipt.append(String.format("Order Ref : %s\n", currentOrder.getOrderId()));
        receipt.append(String.format("Table     : %s\n", currentOrder.getTableNumber()));
        receipt.append(String.format("Cashier   : %s\n", currentUser.getFullName()));
        receipt.append(String.format("Date/Time : %s\n", nowFormatted));
        receipt.append("-----------------------------------------\n");
        receipt.append(String.format("%-22s %-5s %-10s\n", "Item", "Qty", "Amount"));
        receipt.append("-----------------------------------------\n");

        for (OrderItem item : currentOrder.getItems()) {
            receipt.append(String.format("%-22s %-5d %-10s\n",
                item.getItem().getName(), item.getQuantity(), currencyFormat.format(item.getSubtotal())));
        }

        receipt.append("-----------------------------------------\n");
        receipt.append(String.format("Gross Subtotal : %25s\n", currencyFormat.format(currentOrder.calculateRawSubtotal())));
        
        if (currentOrder.isSeniorDiscount()) {
            receipt.append(String.format("Vatable Sales  : %25s\n", currencyFormat.format(currentOrder.getVatableSales())));
            receipt.append(String.format("12%% VAT Amount : %25s\n", "₱0.00 (EXEMPT)"));
            receipt.append(String.format("Senior Discount: %25s\n", currencyFormat.format(currentOrder.getSeniorDiscountAmount())));
        } else {
            receipt.append(String.format("Vatable Sales  : %25s\n", currencyFormat.format(currentOrder.getVatableSales())));
            receipt.append(String.format("12%% VAT Amount : %25s\n", currencyFormat.format(currentOrder.getVatAmount())));
        }

        receipt.append("-----------------------------------------\n");
        receipt.append(String.format("TOTAL AMOUNT   : %25s\n", currencyFormat.format(currentOrder.calculateFinalTotal())));
        receipt.append(String.format("CASH PAID      : %25s\n", currencyFormat.format(cash)));
        receipt.append(String.format("CHANGE DUE     : %25s\n", currencyFormat.format(change)));
        receipt.append("=========================================\n");
        receipt.append("           THANK YOU! COME AGAIN!        \n");

        JTextArea area = new JTextArea(receipt.toString());
        area.setFont(new Font("Monospaced", Font.PLAIN, 12));
        area.setEditable(false);
        JOptionPane.showMessageDialog(this, new JScrollPane(area), "Official Receipt", JOptionPane.INFORMATION_MESSAGE);
    }

    class CartActionPanel extends JPanel {
        JButton btnAdd = new JButton("+");
        JButton btnSub = new JButton("-");

        public CartActionPanel() {
            setLayout(new FlowLayout(FlowLayout.CENTER, 4, 0));
            setOpaque(false);

            btnAdd.setFont(new Font("SansSerif", Font.BOLD, 12));
            btnSub.setFont(new Font("SansSerif", Font.BOLD, 12));

            btnAdd.setPreferredSize(new Dimension(42, 26));
            btnSub.setPreferredSize(new Dimension(42, 26));

            add(btnAdd);
            add(btnSub);
        }
    }

    class CartActionRenderer implements TableCellRenderer {
        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            return new CartActionPanel();
        }
    }

    class CartActionEditor extends AbstractCellEditor implements TableCellEditor {
        CartActionPanel panel = new CartActionPanel();

        public CartActionEditor() {
            panel.btnAdd.addActionListener(e -> {
                int row = cartTable.getSelectedRow();
                if (row >= 0 && row < currentOrder.getItems().size()) {
                    OrderItem item = currentOrder.getItems().get(row);
                    item.setQuantity(item.getQuantity() + 1);
                    refreshCartUI();
                }
                fireEditingStopped();
            });

            panel.btnSub.addActionListener(e -> {
                int row = cartTable.getSelectedRow();
                if (row >= 0 && row < currentOrder.getItems().size()) {
                    OrderItem item = currentOrder.getItems().get(row);
                    if (item.getQuantity() > 1) {
                        item.setQuantity(item.getQuantity() - 1);
                    } else {
                        currentOrder.getItems().remove(row);
                    }
                    refreshCartUI();
                }
                fireEditingStopped();
            });
        }

        @Override
        public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
            return panel;
        }

        @Override
        public Object getCellEditorValue() { return ""; }
    }

    private JPanel createTablesView() {
        JPanel panel = new JPanel(new BorderLayout(15, 15));
        panel.setBackground(COLOR_BACKGROUND);
        panel.setBorder(new EmptyBorder(15, 15, 15, 15));

        JPanel topBanner = new JPanel(new BorderLayout());
        topBanner.setBackground(Color.WHITE);
        topBanner.setBorder(new EmptyBorder(12, 15, 12, 15));

        JLabel title = new JLabel(
            "<html><b>Dining Floor & Table Layout</b><br>"
            + "<font color='gray' size='2'>Click any table to update occupancy or mark as finished eating</font></html>"
        );
        topBanner.add(title, BorderLayout.WEST);
        panel.add(topBanner, BorderLayout.NORTH);

        tablesGridPanel = new JPanel(new GridLayout(2, 3, 15, 15));
        tablesGridPanel.setOpaque(false);

        renderTableCards();

        panel.add(tablesGridPanel, BorderLayout.CENTER);
        return panel;
    }

    private void renderTableCards() {
        tablesGridPanel.removeAll();
        for (RestaurantTable t : tableList) {
            Color statusColor = COLOR_GREEN;
            if ("OCCUPIED".equalsIgnoreCase(t.getStatus())) {
                statusColor = new Color(231, 76, 60);
            } else if ("RESERVED".equalsIgnoreCase(t.getStatus())) {
                statusColor = new Color(241, 196, 15);
            }

            JPanel card = new JPanel(new BorderLayout(8, 8));
            card.setBackground(Color.WHITE);
            card.setBorder(BorderFactory.createCompoundBorder(
                new LineBorder(statusColor, 2, true),
                new EmptyBorder(15, 15, 15, 15)
            ));

            JLabel codeLbl = new JLabel(t.getCode(), SwingConstants.CENTER);
            codeLbl.setFont(new Font("SansSerif", Font.BOLD, 18));

            JLabel nameLbl = new JLabel(t.getName(), SwingConstants.CENTER);
            nameLbl.setFont(new Font("SansSerif", Font.BOLD, 14));

            JLabel statusBadge = new JLabel(t.getStatus(), SwingConstants.CENTER);
            statusBadge.setOpaque(true);
            statusBadge.setBackground(statusColor);
            statusBadge.setForeground(Color.WHITE);
            statusBadge.setFont(new Font("SansSerif", Font.BOLD, 12));

            card.add(codeLbl, BorderLayout.NORTH);
            card.add(nameLbl, BorderLayout.CENTER);
            card.add(statusBadge, BorderLayout.SOUTH);

            card.setCursor(new Cursor(Cursor.HAND_CURSOR));
            card.addMouseListener(new java.awt.event.MouseAdapter() {
                @Override
                public void mouseClicked(java.awt.event.MouseEvent e) {
                    String[] options = {"AVAILABLE (Finished Eating)", "OCCUPIED", "RESERVED"};
                    String selected = (String) JOptionPane.showInputDialog(
                        PopsilogOrderingSystemFrame.this,
                        "Update status for " + t.getName() + ":",
                        "Table Status Update",
                        JOptionPane.QUESTION_MESSAGE,
                        null, options, t.getStatus()
                    );

                    if (selected != null) {
                        if (selected.contains("AVAILABLE")) t.setStatus("AVAILABLE");
                        else t.setStatus(selected);
                        renderTableCards();
                    }
                }
            });

            tablesGridPanel.add(card);
        }
        tablesGridPanel.revalidate();
        tablesGridPanel.repaint();
    }

    private JPanel createKitchenView() {
        JPanel kitchenPanel = new JPanel(new BorderLayout(15, 15));
        kitchenPanel.setBackground(COLOR_BACKGROUND);
        kitchenPanel.setBorder(new EmptyBorder(15, 15, 15, 15));

        JPanel headerCard = new JPanel(new BorderLayout());
        headerCard.setBackground(Color.WHITE);
        headerCard.setBorder(new EmptyBorder(12, 15, 12, 15));

        JLabel title = new JLabel(
            "<html><b>Kitchen Display System (KDS)</b>"
            + "<br><font color='gray' size='2'>"
            + "Live order tickets sent from POS - Staff can confirm completed orders"
            + "</font></html>"
        );

        pendingCountBadge = new JLabel(" 0 Pending Tickets ");
        pendingCountBadge.setOpaque(true);
        pendingCountBadge.setBackground(new Color(255, 243, 224));
        pendingCountBadge.setFont(new Font("SansSerif", Font.BOLD, 12));

        headerCard.add(title, BorderLayout.WEST);
        headerCard.add(pendingCountBadge, BorderLayout.EAST);

        kitchenPanel.add(headerCard, BorderLayout.NORTH);

        kitchenTicketsContainer = new JPanel(new FlowLayout(FlowLayout.LEFT, 15, 15));
        kitchenTicketsContainer.setOpaque(false);

        JScrollPane scrollPane = new JScrollPane(kitchenTicketsContainer);
        scrollPane.setBorder(null);

        kitchenPanel.add(scrollPane, BorderLayout.CENTER);
        return kitchenPanel;
    }

    private void updateKitchenQueueUI() {
        kitchenTicketsContainer.removeAll();
        pendingCountBadge.setText(" " + activeKitchenOrders.size() + " Pending ");

        for (Order order : activeKitchenOrders) {
            JPanel ticket = new JPanel(new BorderLayout(5, 5));
            ticket.setPreferredSize(new Dimension(280, 200));
            ticket.setBackground(Color.WHITE);
            ticket.setBorder(BorderFactory.createLineBorder(COLOR_ACCENT_ORANGE, 2));

            JPanel ticketTop = new JPanel(new BorderLayout());
            ticketTop.setBackground(COLOR_ACCENT_ORANGE);
            ticketTop.setBorder(new EmptyBorder(5, 8, 5, 8));

            JLabel orderLbl = new JLabel(order.getOrderId() + " (" + order.getTableNumber() + ")");
            orderLbl.setForeground(Color.WHITE);
            orderLbl.setFont(new Font("SansSerif", Font.BOLD, 12));
            ticketTop.add(orderLbl, BorderLayout.WEST);

            DefaultListModel<String> itemsModel = new DefaultListModel<>();
            for (OrderItem oi : order.getItems()) {
                itemsModel.addElement(oi.getQuantity() + "x " + oi.getItem().getName());
            }

            JButton actionBtn = new JButton("READY");
            actionBtn.setOpaque(true);
            actionBtn.setContentAreaFilled(true);
            actionBtn.setBorderPainted(false);
            actionBtn.setFocusPainted(false);
            actionBtn.setBackground(COLOR_GREEN);
            actionBtn.setForeground(Color.WHITE);
            actionBtn.setFont(new Font("SansSerif", Font.BOLD, 11));
            actionBtn.setPreferredSize(new Dimension(270, 32));

            actionBtn.addActionListener(e -> {
                activeKitchenOrders.remove(order);
                updateKitchenQueueUI();
            });

            ticket.add(ticketTop, BorderLayout.NORTH);
            ticket.add(new JScrollPane(new JList<>(itemsModel)), BorderLayout.CENTER);
            ticket.add(actionBtn, BorderLayout.SOUTH);

            kitchenTicketsContainer.add(ticket);
        }
        kitchenTicketsContainer.revalidate();
        kitchenTicketsContainer.repaint();
    }

    private JPanel createInventoryView() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBackground(COLOR_BACKGROUND);
        panel.setBorder(new EmptyBorder(15, 15, 15, 15));

        JPanel header = new JPanel(new BorderLayout());
        header.setBackground(Color.WHITE);
        header.setBorder(new EmptyBorder(12, 15, 12, 15));
        header.add(new JLabel("<html><b>Raw Ingredients & Stock Inventory</b><br><font color='gray' size='2'>Track critical ingredients for Pares & Silog menu items</font></html>"), BorderLayout.WEST);
        panel.add(header, BorderLayout.NORTH);

        String[] cols = {"Ingredient Name", "Category", "In Stock", "Min. Threshold", "Status"};
        inventoryTableModel = new DefaultTableModel(cols, 0);
        refreshInventoryTableData();

        JTable table = new JTable(inventoryTableModel);
        table.setRowHeight(28);

        panel.add(new JScrollPane(table), BorderLayout.CENTER);

        JButton restockBtn = new JButton(" Restock Selected Stock Item");
        restockBtn.setFont(new Font("SansSerif", Font.BOLD, 12));
        restockBtn.addActionListener(e -> {
            int selectedRow = table.getSelectedRow();
            if (selectedRow >= 0) {
                InventoryItem item = inventoryList.get(selectedRow);
                String qtyStr = JOptionPane.showInputDialog(this, "Enter new stock level for " + item.getName() + ":", item.getInStock());
                if (qtyStr != null) {
                    try {
                        item.setInStock(Double.parseDouble(qtyStr));
                        item.setStatus("Healthy");
                        refreshInventoryTableData();
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(this, "Invalid quantity entered.");
                    }
                }
            } else {
                JOptionPane.showMessageDialog(this, "Select an item to restock.");
            }
        });

        JPanel bottomControl = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        bottomControl.setOpaque(false);
        bottomControl.add(restockBtn);
        panel.add(bottomControl, BorderLayout.SOUTH);

        return panel;
    }

    private void refreshInventoryTableData() {
        inventoryTableModel.setRowCount(0);
        for (InventoryItem item : inventoryList) {
            inventoryTableModel.addRow(new Object[]{
                item.getName(), item.getCategory(),
                item.getInStock() + " " + item.getUnit(),
                item.getMinThreshold() + " " + item.getUnit(),
                item.getStatus()
            });
        }
    }

    private JPanel createSettingsView() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(COLOR_BACKGROUND);

        JPanel card = new JPanel(new GridLayout(4, 2, 10, 10));
        card.setBackground(Color.WHITE);
        card.setBorder(BorderFactory.createCompoundBorder(
            new LineBorder(Color.LIGHT_GRAY, 1),
            new EmptyBorder(20, 20, 20, 20)
        ));

        card.add(new JLabel("Branch Name:"));
        card.add(new JTextField("Sta. Maria Branch"));

        card.add(new JLabel("Thermal Receipt Printer:"));
        card.add(new JComboBox<>(new String[]{"EPSON", "BROTHER"}));

        card.add(new JLabel("Receipt Footer Note:"));
        card.add(new JTextField("Zamboanga's Best Tasting Pares & Silog! Thank you!"));

        JButton saveBtn = new JButton("Save System Settings");
        saveBtn.setOpaque(true);
        saveBtn.setContentAreaFilled(true);
        saveBtn.setBorderPainted(false);
        saveBtn.setFocusPainted(false);
        saveBtn.setBackground(COLOR_ACCENT_ORANGE);
        saveBtn.setForeground(Color.WHITE);
        saveBtn.addActionListener(e -> JOptionPane.showMessageDialog(this, "Settings saved successfully!"));

        card.add(new JLabel());
        card.add(saveBtn);

        panel.add(card);
        return panel;
    }
}