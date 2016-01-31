package zad1.shop;

import zad1.flowers.Flower;

import java.util.Map;
import java.util.HashMap;
import java.util.Set;

public class Assortment {
    public Map<String, AssortmentEntity> entities;

    public Assortment() {
        entities = new HashMap<String, AssortmentEntity>();
    }

    public void add(Flower flower, int price) {
        entities.put(flower.getName(), new AssortmentEntity(flower, price));
    }

    public Set<String> getEntityNames() {
        return entities.keySet();
    }

    public AssortmentEntity get(String flowerName) {
        return entities.get(flowerName);
    }

    public Map<String, AssortmentEntity> getEntities() {
        return entities;
    }
}
