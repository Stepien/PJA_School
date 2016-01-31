/**
 *
 *  @author Stępniewski Jarosław S12391
 *
 */

package zad2;

import java.util.Scanner;

import zad2.calculator.Calculator;

public class Main {

    public static void main(String[] args) {
        Calculator calculator = new Calculator();
        Scanner scanInput = new Scanner(System.in);
        while(scanInput.hasNext()){
            calculator.perform(scanInput.nextLine());
        }
    }
}