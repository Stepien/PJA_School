package zad1.shop;

import zad1.clients.Client;
import zad1.clients.ShoppingBasket;
import zad1.flowers.Flower;
import zad1.shop.magazine.Magazine;
import zad1.shop.transaction.BoxedPurchase;

import java.util.HashMap;
import java.util.Map;
import java.util.List;

public class Shop {
    private final Map<String, ShoppingBasket> shoppingBaskets;
    private final Assortment assortment;
    private final Magazine magazine;

    public Shop() {
        this.shoppingBaskets = new HashMap<String, ShoppingBasket>();
        this.assortment = new Assortment();
        this.magazine = new Magazine();
    }

    public void addClient(Client client) {
        shoppingBaskets.put(client.getId(), new ShoppingBasket(client));
    }


    public ShoppingBasket basketOf(String clientId) {
        return shoppingBaskets.get(clientId);
    }

    public ShoppingBasket pullBasketOf(String clientId) {
        ShoppingBasket result = basketOf(clientId);
        shoppingBaskets.remove(clientId);
        return result;
    }

    public Assortment getAssortment() {
        return assortment;
    }

    public Magazine getMagazine() {
        return magazine;
    }

    public int calcValueOfClientBasket(String clientId){
        List<Flower> flowers = shoppingBaskets.get(clientId).getFlowers();
        int result = 0;
        for(Flower flower: flowers) {
            result += assortment.get(flower.getName()).price;
        }

        return result;
    }
}