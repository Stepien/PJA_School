package zad1.flowers.certain;

import zad1.flowers.Flower;
import zad1.flowers.IFlowerVisitor;

public class Daisy extends Flower {
    public Daisy() {
        super("Daisy", "white");
    }

    @Override
    public Object clone() {
        return new Daisy();
    }

    @Override
    public void accept(IFlowerVisitor visitor){
        visitor.visit(this);
    }
}