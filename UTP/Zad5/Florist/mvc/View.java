package zad1.mvc;

import zad1.clients.Client;
import zad1.clients.ShoppingBasket;
import zad1.flowers.Flower;
import zad1.shop.transaction.BoxedPurchase;
import zad1.shop.transaction.InsufficientFundsException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowAdapter;

public class View{
    private Controller controller;

    private DefaultListModel<String> assortmentListModel = new DefaultListModel<String>();
    private JList<String> assortmentList = new JList<String>(assortmentListModel);

    private DefaultListModel<String> clientsListModel = new DefaultListModel<String>();
    private JList<String> clientsList = new JList<String>(clientsListModel);

    private DefaultListModel<String> clientShoppingBasketModel = new DefaultListModel<String>();
    private JList<String> clientShoppingBasketList = new JList<String>(clientShoppingBasketModel);
    private JButton addToClientShoppingBasketButton = new JButton("Dodaj do koszyka");
    private JButton removeFromClientShoppingBasketButton = new JButton("Usun z koszyka");
    private JButton payForBasketButton = new JButton("Zapłać");
    private JButton payForBasketAsCompanyButton = new JButton("Zapłać jako firma");
    Client currentClient = null;

    private DefaultListModel<String> magazineModel = new DefaultListModel<String>();
    private JList<String> magazineBoxesList = new JList<String>(magazineModel);
    private JButton inspectMagazineButton = new JButton("Inspekcja");

    private JTextField clientNameTextField = new JTextField();
    private JTextField clientCashTextField = new JTextField();
    private JButton createClientButton = new JButton("Dodaj klienta");

    static class CloseListener extends WindowAdapter {
        public void windowClosing(WindowEvent e) {
            e.getWindow().setVisible(false);
            System.exit(0);
        }
    }

    public static void setWarningMsg(String title, String text){
        Toolkit.getDefaultToolkit().beep();
        JOptionPane optionPane = new JOptionPane(text,JOptionPane.WARNING_MESSAGE);
        JDialog dialog = optionPane.createDialog(title);
        dialog.setAlwaysOnTop(true);
        dialog.setVisible(true);
    }

    private void updateAssortmentList() {
        java.util.List<String> items = controller.getAssortmentStrings();

        assortmentListModel.removeAllElements();
        items.stream().forEach(item -> {
            assortmentListModel.addElement(item);
        });
    }

    private void updateClientsList() {
        java.util.List<String> items = controller.getClientStrings();

        clientsListModel.removeAllElements();
        items.stream().forEach(item -> {
            clientsListModel.addElement(item);
        });
    }

    private void addClient() {
        String clientName = clientNameTextField.getText();
        clientNameTextField.setText("");
        int clientCash = Integer.parseInt(clientCashTextField.getText());
        clientCashTextField.setText("");

        controller.addClient(clientName, clientCash);

        updateClientsList();
        System.out.println("AddClient called with: " + clientName + " + " + clientCash);
    }

    private void updateClient() {
        clientShoppingBasketModel.removeAllElements();

        if(clientsList.isSelectionEmpty()) return;

        currentClient = controller.getClientByIndex(clientsList.getSelectedIndex());
        if(currentClient == null) return;
        ShoppingBasket basket = controller.getClientShoppingBasket(currentClient.getId());

        java.util.List<Flower> items = basket.getFlowers();

        for(Flower flower: items) {
            clientShoppingBasketModel.addElement(flower.getName());
        }
    }

    private void addItemToBasket() {
        if(assortmentList.isSelectionEmpty() || clientsList.isSelectionEmpty() || currentClient == null) return;

        controller.addFlowerToClientShoppingBasket(currentClient.getId(), assortmentList.getSelectedValue());
        assortmentList.clearSelection();
        updateClient();
    }

    private void removeItemFromBasket() {
        if(clientShoppingBasketList.isSelectionEmpty() || clientsList.isSelectionEmpty()) return;

        controller.removeFlowerFromClientShoppingBasket(currentClient.getId(), clientShoppingBasketList.getSelectedValue());

        updateClient();
    }

    private void payForBasket() {
        if(clientsList.isSelectionEmpty()) return;

        setWarningMsg("Info", "The basket costs: " + controller.calculateCostsOf(currentClient.getId()));

        BoxedPurchase<Flower> boxedFlowers = null;

        String name = currentClient.getId();

        try {
            boxedFlowers = controller.payForClientBasket(name);
        } catch(InsufficientFundsException e) {
            setWarningMsg("Error!", "Insufficient founds!");
        }

        if(boxedFlowers != null) {
            setWarningMsg("Info", boxedFlowers.toString());
            controller.storeBox(name, boxedFlowers, 0);
            magazineModel.addElement(boxedFlowers.toString());
        }

        updateClientsList();
        updateClient();
    }

    private void inspectMagazine() {
        if(magazineBoxesList.isSelectionEmpty()) return;

        setWarningMsg("Inspekcja", magazineBoxesList.getSelectedValue());
    }

    private Frame initFrame(){
        Frame frame = new Frame("MVC florist shop simulator");
        frame.addWindowListener(new CloseListener());
        frame.setLayout(new BoxLayout(frame, BoxLayout.PAGE_AXIS));
        frame.setSize(340, 600);
        return frame;
    }

    private void addViewSection(Frame frame, String sectionName, Component[] components) {
        frame.add(new JLabel("<"+sectionName+">"));
        for(Component component: components) {
            frame.add(component);
        }
        frame.add(new JLabel("</"+sectionName+">"));
    }

    private void fillFrame(Frame frame) {
        createClientButton.addActionListener(
                l -> addClient());

        assortmentList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        clientsList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        clientsList.addListSelectionListener(l -> updateClient());

        clientShoppingBasketList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        addToClientShoppingBasketButton.addActionListener(l -> addItemToBasket());
        removeFromClientShoppingBasketButton.addActionListener(l -> removeItemFromBasket());
        payForBasketButton.addActionListener(l -> payForBasket());
        //todo: move payment to the moment of reciving package by company
        payForBasketAsCompanyButton.addActionListener(l -> payForBasket());

        magazineBoxesList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        inspectMagazineButton.addActionListener(l -> inspectMagazine());

        addViewSection(frame, "Magazyn", new Component[]{
            magazineBoxesList,
            inspectMagazineButton
        });
        addViewSection(frame, "Dostępne produkty", new Component[]{assortmentList});

        addViewSection(frame, "Klienci", new Component[]{clientsList});

        addViewSection(frame, "Koszyk wybranego klienta", new Component[]{
            clientShoppingBasketList,
            addToClientShoppingBasketButton,
            removeFromClientShoppingBasketButton,
            payForBasketButton,
            payForBasketAsCompanyButton
        });

        addViewSection(frame, "Nowy klient", new Component[]{
                new JLabel("-identyfikator"),
                clientNameTextField,
                new JLabel("-gotówka"),
                clientCashTextField,
                createClientButton
        });
    }

    public View(Controller controller) {
        this.controller = controller;

        Frame frame = initFrame();
        updateAssortmentList();

        fillFrame(frame);
        frame.setVisible(true);
    }
}
