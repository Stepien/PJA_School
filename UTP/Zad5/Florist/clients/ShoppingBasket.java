package zad1.clients;

import zad1.flowers.Flower;

import java.util.ArrayList;
import java.util.List;

public class ShoppingBasket {
    private final List<Flower> flowers;
    private final Client client;

    public ShoppingBasket(Client client) {
        this.flowers = new ArrayList<Flower>();
        this.client = client;
    }

    public void addFlower(Flower flower) {
        flowers.add(flower);
    }

    public final List<Flower> getFlowers() {
        return flowers;
    }

    public Client getClient() {
        return client;
    }
}
