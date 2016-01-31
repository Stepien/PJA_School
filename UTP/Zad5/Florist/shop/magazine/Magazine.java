package zad1.shop.magazine;

import zad1.shop.transaction.BoxedPurchase;
import zad1.shop.transaction.InsufficientFundsException;

import java.util.HashMap;
import java.util.Map;

class StoredPurchase {
    private final BoxedPurchase<?> box;
    private final int priceToBePaid;

    public StoredPurchase(BoxedPurchase<?> box, int priceToBePaid) {
        this.box = box;
        this.priceToBePaid = priceToBePaid;
    }

    public BoxedPurchase<?> getBox() {
        return box;
    }

    public int priceToBePaid() {
        return priceToBePaid;
    }
}

public class Magazine {
    final Map<String, StoredPurchase> boxes;

    public Magazine() {
        boxes = new HashMap<String, StoredPurchase>();
    }

    public void add(String name, BoxedPurchase<?> box, int priceToBePaid) {
        boxes.put(name, new StoredPurchase(box, priceToBePaid));
    }

    public BoxedPurchase<?> pull(String name, int cash) throws InsufficientFundsException {
        StoredPurchase storedPurchase = boxes.get(name);
        if(cash < storedPurchase.priceToBePaid()){
            throw new InsufficientFundsException();
        }

        boxes.remove(name);
        return storedPurchase.getBox();
    }
}
