package zad1.shop;

import zad1.flowers.Flower;

public class AssortmentEntity {
    public final Flower flower;
    public final int price;

    public AssortmentEntity(Flower flower, int price){
        this.flower = flower;
        this.price = price;
    }

    @Override
    public String toString() {
        return "{flower=" + flower + ", price=" + price + "}";
    }
}