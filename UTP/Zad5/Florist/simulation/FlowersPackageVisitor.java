package zad1.simulation;

import zad1.PackageUtil;
import zad1.flowers.Flower;
import zad1.flowers.IFlowerVisitor;

import java.lang.reflect.Executable;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

public class FlowersPackageVisitor implements IFlowerVisitor {
    private List<Flower> flowers = new ArrayList<Flower>();

    public List<Flower> getCollectedFlowers() {
        return flowers;
    }

    public void run() throws java.lang.Exception {
        final String pack = "zad1.flowers.certain";
        PackageUtil.getClasses(pack).stream().filter(Flower.class::isAssignableFrom).forEach(c -> {
            try{
                this.visit((Flower)c.getConstructor().newInstance());
            } catch(Exception e){
                e.printStackTrace();
            }
        });
    }

    @Override
    public void visit(Flower flower){
        flowers.add((Flower)flower.clone());
    }
}
