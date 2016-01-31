package zad1.simulation;

import zad1.flowers.Flower;
import zad1.flowers.certain.Daisy;
import zad1.flowers.certain.ForgetMeNot;
import zad1.shop.Shop;
import zad1.clients.Client;
import zad1.mvc.Model;
import zad1.mvc.View;
import zad1.mvc.Controller;

import java.util.ArrayList;
import java.util.Random;

public class Simulator {
    private static Simulator instance = null;

    public static Simulator getInstance() {
        if(instance == null)
            instance = new Simulator();

        return instance;
    }

    public void run() {
        FlowersPackageVisitor flowersVisitor = new FlowersPackageVisitor();
        try {
            flowersVisitor.run();
        } catch(java.lang.Exception e){
            e.printStackTrace();
            return;
        }

        Shop shop = new Shop();
        Random rand = new Random();

        for(Flower flower: flowersVisitor.getCollectedFlowers()) {
            shop.getAssortment().add((Flower)flower.clone(), rand.nextInt(100)+5);
        }

        Model model = new Model(shop, new ArrayList<Client>());
        Controller controller = new Controller(model);
        View view = new View(controller);
        controller.setView(view);
    }

    private Simulator() {}
}
