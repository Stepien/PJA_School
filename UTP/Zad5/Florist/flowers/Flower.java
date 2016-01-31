package zad1.flowers;

interface IVisitable{
    void accept(IFlowerVisitor visitor);
}

public abstract class Flower implements Cloneable, IVisitable{
    private final String name, color;

    protected Flower(String name, String color) {
        this.name = name;
        this.color = color;
    }

    @Override
    public String toString() {
        return "{" + name + ", " + color + "}";
    }

    public String getName() {
        return name;
    }

    public String getColor() {
        return color;
    }

    abstract public Object clone();
}
