package zad1.mvc;

import zad1.clients.Client;
import zad1.clients.ShoppingBasket;
import zad1.flowers.Flower;
import zad1.money.Wallet;
import zad1.shop.transaction.BoxedPurchase;
import zad1.shop.transaction.InsufficientFundsException;
import zad1.shop.transaction.Transaction;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Controller {
    private Model model;
    private View view;

    public Controller(Model model) {
        this.model = model;
    }

    public void setModel(Model model) {
        this.model = model;
    }

    public void setView(View view) {
        this.view = view;
    }

    public List<String> getClientStrings() {
        return model.getClients().stream()
                .map(client -> client.getId() + ", $" + client.getWallet().getCash()).collect(Collectors.toList());
    }

    public Client getClientByIndex(int idx) {
        int max = model.getClients().size();

        return idx < max? model.getClients().get(idx) : null;
    }

    public ShoppingBasket getClientShoppingBasket(String clientId){
        return model.getShoppingBasketOf(clientId);
    }

    public void addFlowerToClientShoppingBasket(String clientId, String flowerName) {
        model.getShoppingBasketOf(clientId).addFlower(model.getAssortment().get(flowerName).flower);
    }

    public void removeFlowerFromClientShoppingBasket(String clientId, String flowerName) {
        List<Flower> flowers = model.getShoppingBasketOf(clientId).getFlowers();
        for(Flower f: flowers){
            if(f.getName() == flowerName){
                flowers.remove(f);
                break;
            }
        }
    }

    public List<String> getAssortmentStrings() {
        return new ArrayList<String>(model.getAssortment().getEntityNames());
    }

    public void addClient(String clientName, int clientCash) {
        Client client = new Client(clientName, clientCash);
        model.addClient(client);
    }

    public BoxedPurchase<Flower> payForClientBasket(String clientId) throws InsufficientFundsException{
        Client client = model.getClients().stream().filter(c -> c.getId() == clientId).findFirst().get();
        Transaction transaction = new Transaction(client, model.getShop());

        BoxedPurchase<Flower> result = transaction.complete();
        model.getClients().remove(client);
        return result;
    }

    public int calculateCostsOf(String clientId) {
        return model.getShop().calcValueOfClientBasket(clientId);
    }

    void storeBox(String name, BoxedPurchase<Flower> boxedPurchase, int priceToBePaid){
        model.getShop().getMagazine().add(name, boxedPurchase, priceToBePaid);
    }
}
