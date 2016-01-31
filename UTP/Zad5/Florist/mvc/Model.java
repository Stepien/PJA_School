package zad1.mvc;

import zad1.clients.Client;
import zad1.clients.ShoppingBasket;
import zad1.shop.Assortment;
import zad1.shop.Shop;

import java.util.List;
import java.util.stream.Collectors;

public class Model{
    private Shop shop;
    private List<Client> clients;

    public Model(Shop shop, List<Client> clients) {
        this.shop = shop;
        this.clients = clients;
    }

    public Assortment getAssortment() {
        return shop.getAssortment();
    }

    public List<Client> getClients() {
        //eventual todo: return immutable list
        return clients;
    }

    public ShoppingBasket getShoppingBasketOf(String clientId) {
        return shop.basketOf(clientId);
    }

    public void addClient(Client client) {
        clients.add(client);
        shop.addClient(client);
    }

    public Shop getShop() {
        return shop;
    }
}
