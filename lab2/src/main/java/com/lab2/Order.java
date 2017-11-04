package com.lab2;

import java.util.Objects;

public class Order implements Comparable<Order>{
    private int idOrder;
    private int price;
    private int quantity;

    public Order(int idOrder, int price, int quantity) {
        this.idOrder = idOrder;
        this.price = price;
        this.quantity = quantity;
    }

    public Order(int idOrder){
        this.idOrder = idOrder;
    }

    public int getIdOrder() {
        return idOrder;
    }

    public void setIdOrder(int idOrder) {
        this.idOrder = idOrder;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Order order = (Order) o;
        return idOrder == order.idOrder;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idOrder);
    }

    @Override
    public String toString() {
        return "Order{" +
                "idOrder=" + idOrder +
                ", price=" + price +
                ", quantity=" + quantity +
                '}';
    }


    @Override
    public int compareTo(Order o) {
        return Integer.compare(idOrder, o.idOrder);
    }
}
