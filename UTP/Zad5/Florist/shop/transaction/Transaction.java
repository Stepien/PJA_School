package zad1.shop.transaction;

import zad1.clients.Client;
import zad1.flowers.Flower;
import zad1.shop.Shop;

public class Transaction {
    private final Client client;
    private final Shop shop;

    public Transaction(Client client, Shop shop){
        this.client = client;
        this.shop = shop;
    }

    public BoxedPurchase<Flower> complete() throws InsufficientFundsException {
        int basketPrice = shop.calcValueOfClientBasket(client.getId());
        client.getWallet().widthdraw(basketPrice);

        return new BoxedPurchase<Flower>(
                shop.pullBasketOf(client.getId())
                        .getFlowers());
    }
}
