package com.example.pckosek.customviews_09;

public class RestaurantOrder {

    public int orderNumber;
    public float orderCost;
    public String choice;

    /* ------------------- */
    /*  CONSTRUCTOR
    /* ------------------- */

    public RestaurantOrder() {
    }

    public RestaurantOrder(int num, float cost, String choice) {
        this.orderNumber = num;
        this.orderCost = cost;
        this.choice = choice;
    }


    /* ------------------- */
    /*  GETTERS AND SETTERS
    /* ------------------- */

    public int getOrderNumber() {
        return orderNumber;
    }

    public String getOrderNumberString() {
        return String.valueOf(orderNumber);
    }


    public void setOrderNumber(int orderNumber) {
        this.orderNumber = orderNumber;
    }

    public float getOrderCost() {
        return orderCost;
    }

    public String getOrderCostString() {
        return String.valueOf(orderCost);
    }


    public void setOrderCost(float orderCost) {
        this.orderCost = orderCost;
    }

    public String getChoice() {
        return choice;
    }

    public void setChoice(String item) {
        this.choice = choice;
    }

}