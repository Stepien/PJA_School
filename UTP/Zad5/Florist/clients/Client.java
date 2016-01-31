package zad1.clients;

import zad1.money.Wallet;

public class Client {
    private final String id;
    private final Wallet wallet;

    public Client(String id, int cash){
        this.id = id;
        this.wallet = new Wallet(cash);
    }

    public String getId() {
        return id;
    }

    public Wallet getWallet() {
        return wallet;
    }
}
