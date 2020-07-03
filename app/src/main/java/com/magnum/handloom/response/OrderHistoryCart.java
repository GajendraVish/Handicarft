package com.magnum.handloom.response;

public class OrderHistoryCart {

    private String MenuId;
    private String MenuName;
    private String MenuQuantity;
    private String MenuPrice;
    private String MenuImage;

    public String getMenuId() {
        return MenuId;
    }

    public void setMenuId(String menuId) {
        MenuId = menuId;
    }

    public String getMenuName() {
        return MenuName;
    }

    public void setMenuName(String menuName) {
        MenuName = menuName;
    }

    public String getMenuQuantity() {
        return MenuQuantity;
    }

    public void setMenuQuantity(String menuQuantity) {
        MenuQuantity = menuQuantity;
    }

    public String getMenuPrice() {
        return MenuPrice;
    }

    public void setMenuPrice(String menuPrice) {
        MenuPrice = menuPrice;
    }

    public String getMenuImage() {
        return MenuImage;
    }

    public void setMenuImage(String menuImage) {
        MenuImage = menuImage;
    }
}
