package com.popsilog.popsilogorderingsystem.ui;

import com.popsilog.popsilogorderingsystem.model.FoodItem;
import java.util.ArrayList;
import java.util.List;

public class MenuRepository {
    private List<FoodItem> menuList;

    public MenuRepository() {
        menuList = new ArrayList<>();
        loadDefaultMenu();
    }

    private void loadDefaultMenu() {
    menuList.add(new FoodItem("P1", "Basic Pares", "Tender beef stew with rich broth, egg & fried rice", 109.00, "Pares", "basic pares.jpg", "Bestseller", true));
    menuList.add(new FoodItem("P2", "Mercy Pares", "Special rich pares stew with siomai, egg & fried rice", 149.00, "Pares", "mercy pares.jpg", "Special", true));

    menuList.add(new FoodItem("S1", "Tapsilog (Tapa, Egg, Rice)", "Signature marinated beef tapa with egg and garlic rice", 129.00, "Silog", "beef tapa.jpg", "Popular", true));
    menuList.add(new FoodItem("S2", "Tocisilog (Tocino, Egg, Rice)", "Sweet cured chicken tocino with egg and garlic rice", 129.00, "Silog", "chicken tocino.jpg", null, true));
    menuList.add(new FoodItem("S3", "Bangsilog (Bangus, Egg, Rice)", "Crispy fried marinated milkfish with egg and garlic rice", 110.00, "Silog", "bangsilog.jpg", null, true));
    menuList.add(new FoodItem("S4", "Chixilog (Chicken, Egg, Rice)", "Golden fried chicken leg quarter with egg and garlic rice", 139.00, "Silog", "chixilog.jpg", null, true));
    menuList.add(new FoodItem("S5", "Beef Longganisa", "Savory garlic beef sausages served with egg & garlic rice", 129.00, "Silog", "beef longganisa.jpg", null, true));

    menuList.add(new FoodItem("CP1", "Chicken Pops Sriracha", "Crispy chicken bites glazed with spicy sriracha sauce", 139.00, "Chicken Pops", "chicken sriracha.jpg", "Spicy", true));
    menuList.add(new FoodItem("CP2", "Chicken Pops Sweet Soy", "Tender chicken pops coated in rich sweet soy glaze", 139.00, "Chicken Pops", "chicken sweet soy.jpg", null, true));

    menuList.add(new FoodItem("S6", "Mix & Match", "Choose 2 silog viands of your choice", 199.00, "Silog", null, "Combo", true));
    menuList.add(new FoodItem("S7", "Mix & Match (with Chixi)", "Choose 2 silog viands including fried chicken", 209.00, "Silog", null, "Combo", true));

    menuList.add(new FoodItem("D1", "Water", "Purified drinking water in bottle", 20.00, "Beverage", null, null, true));
    menuList.add(new FoodItem("D2", "Soda", "Refreshing ice-cold canned soda", 30.00, "Beverage", null, null, true));

    menuList.add(new FoodItem("A1", "Extra Silog Viand", "Single serving extra silog portion", 85.00, "Add-Ons", null, "Add-on", true));
    menuList.add(new FoodItem("A2", "Extra Pares Meat", "Additional tender beef pares meat portion", 45.00, "Add-Ons", null, "Add-on", true));
    menuList.add(new FoodItem("A3", "Siomai (1pc)", "Steamed chicken siomai piece", 10.00, "Add-Ons", null, "Add-on", true));
    menuList.add(new FoodItem("A4", "12pcs Siomai", "Party tray 12 pieces steamed pork siomai", 105.00, "Add-Ons", null, "Best Value", true));
    menuList.add(new FoodItem("A5", "Extra Rice", "Steamed white rice scoop", 20.00, "Add-Ons", null, "Add-on", true));
    menuList.add(new FoodItem("A6", "Extra Fried Rice", "Aromatic toasted garlic fried rice scoop", 30.00, "Add-Ons", null, "Add-on", true));
    menuList.add(new FoodItem("A7", "Extra Fried Egg", "Fresh sunny-side up or scrambled egg", 20.00, "Add-Ons", null, "Add-on", true));
    menuList.add(new FoodItem("A8", "Extra Boiled Egg", "Hard-boiled fresh egg", 20.00, "Add-Ons", null, "Add-on", true));
    menuList.add(new FoodItem("A9", "Bone Marrow", "Rich roasted bone marrow with broth", 45.00, "Add-Ons", null, "Special", true));
}
    public List<FoodItem> getMenuList() { return menuList; }

    public List<FoodItem> getByCategory(String category) {
        if ("All".equalsIgnoreCase(category)) return menuList;
        List<FoodItem> filtered = new ArrayList<>();
        for (FoodItem item : menuList) {
            if (item.getCategory().equalsIgnoreCase(category)) filtered.add(item);
        }
        return filtered;
    }
}