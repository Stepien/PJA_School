package zad1.shop.transaction;

import zad1.shop.AssortmentEntity;

import java.util.List;

public class BoxedPurchase<T> {
    private final List<T> entities;

    public BoxedPurchase(List<T> entities) {
        this.entities = entities;
    }

    public List<T> getEntities() {
        return entities;
    }

    @Override
    public String toString() {
        return entities.toString();
    }
}
