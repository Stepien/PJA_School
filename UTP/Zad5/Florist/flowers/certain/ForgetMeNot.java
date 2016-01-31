package zad1.flowers.certain;

import zad1.flowers.Flower;
import zad1.flowers.IFlowerVisitor;

public class ForgetMeNot extends Flower {
    public ForgetMeNot() {
        super("Forget-me-not", "blue-ish");
    }

    @Override
    public Object clone() {
        return new ForgetMeNot();
    }

    @Override
    public void accept(IFlowerVisitor visitor){
        visitor.visit(this);
    }
}