package zad1;

import zad1.simulation.Simulator;

public class Main {
	static { /* works fine! ! */
	      System.setProperty("java.awt.headless", "true");
	      System.out.println(java.awt.GraphicsEnvironment.isHeadless());
	      /* ---> prints true */}
	
    public static void main(String[] args) {
    	
    	System.setProperty("java.home", "C:\\Program Files\\Java\\jdk1.8.0_71");
        Simulator.getInstance().run();
    }
}
