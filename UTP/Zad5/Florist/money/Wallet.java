package zad1.money;

import zad1.shop.transaction.InsufficientFundsException;

public class Wallet {
    private int cash;

    public Wallet() {
        this.cash = 0;
    }

    public Wallet(int cash) {
        this.cash = cash;
    }

    public int getCash() {
        return cash;
    }

    public void widthdraw(int money) throws InsufficientFundsException {
        if(cash < money){
            throw new InsufficientFundsException();
        }

        cash -= money;
    }
}
