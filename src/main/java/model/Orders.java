package model;

public class Orders {
    private Integer ID;
    private Integer idClient;
    private Integer idProduct;
    private Integer quantity;
    private Double price;

    public Orders(){

    }

    public Orders(Integer ID,Integer idClient, Integer idProduct, Integer quantity, Double price) {
        this.ID = ID;
        this.idClient = idClient;
        this.idProduct = idProduct;
        this.quantity = quantity;
        this.price = price;
    }

    public Integer getID() {
        return ID;
    }

    public void setID(Integer ID) {
        this.ID = ID;
    }

    public Integer getIdClient() {
        return idClient;
    }

    public void setIdClient(Integer idClient) {
        this.idClient = idClient;
    }

    public Integer getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(Integer idProduct) {
        this.idProduct = idProduct;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String toString() {
        return "('" + this.ID + "', '" + this.idClient + "', '" + this.idProduct + "', '" + this.quantity + "', '" + this.price + "')";
    }
}
