package entities;

import java.util.Objects;

import static service.CurrencyFormatter.formatToVND;

public abstract class Product {
    private String name;
    private String brand;
    private String id;
    private String color;
    private int price;
    private int stock;
    private String status;

    protected Product(Builder<?> builder) {
        this.name = builder.name;
        this.brand = builder.brand;
        this.id = builder.id;
        this.color = builder.color;
        this.price = builder.price;
        this.stock = builder.stock;
        this.status = builder.status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public abstract static class Builder<T extends Builder<T>> {
        protected String name;
        protected String brand;
        protected String id;
        protected String color;
        protected int price;
        protected int stock;
        protected String status;

        public T setName(String name) {
            this.name = name;
            return (T) this;
        }

        public T setBrand(String brand) {
            this.brand = brand;
            return (T) this;
        }

        public T setId(String id) {
            this.id = id;
            return (T) this;
        }

        public T setColor(String color) {
            this.color = color;
            return (T) this;
        }

        public T setPrice(int price) {
            this.price = price;
            return (T) this;
        }

        public T setStock(int stock) {
            this.stock = stock;
            return (T) this;
        }

        public T setStatus(String status) {
            this.status = status;
            return (T) this;
        }

        public abstract Product build();
    }

    @Override
    public String toString() {
        return "Product{" +
                "name='" + name + '\'' +
                ", brand='" + brand + '\'' +
                ", ID='" + id + '\'' +
                ", color='" + color + '\'' +
                ", price=" + formatToVND(price) +
                ", stock=" + stock +
                ", status='" + status + '\'' +
                '}';
    }

    public String toCSV() {
        return name + "," + brand + "," + id + "," + color + "," + price + "," + stock + "," + status;
    }
}