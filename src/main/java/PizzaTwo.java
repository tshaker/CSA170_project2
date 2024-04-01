/*
  Nguyen, Kaylyn
  Shaker, Tim     

  April 5, 2024

  CS A170
  Prof. Mayada Alani
  Project 2
*/

import java.util.Scanner;

public class PizzaTwo {
  private static boolean hasOwnersName(String name) {
    return name.equalsIgnoreCase("Kaylyn") || name.equalsIgnoreCase("Tim");
  }

  public static void main(String[] args) {
    // Scanner and Variables
    Scanner sc = new Scanner(System.in);

    boolean discounted = false;
    int size = 12;
    char crust = 'H';
    String printCrust = "";

    int numOfToppings = 0;
    String toppings = "Cheese ";
    char yesNo = ' ';

    final double DISCOUNT = 2.00;
    final double TAX_RATE = 0.0795;
    final double TOPPING_CHARGE = 1.25;
    double total = 0;

    // NAME: user prompt
    System.out.println("Welcome to Tim and Kaylyn’s Pizzeria");
    System.out.print("Enter your first name: ");
    String name = sc.next();
    // NAME: discount eligibility
    if (hasOwnersName(name)) {
      discounted = true;
      if (discounted) {
        System.out.println();
        System.out.println("You have the same name as one of the owners, you are eligible for a $2.00 discount!");
        total -= DISCOUNT;
      }
    }

    // MENU: SIZE & COST
    System.out.println();
    System.out.println(" Pizza Size |   Cost ");
    System.out.println("---------------------");
    System.out.println("    10\"     |  $10.99");
    System.out.println("    12\"     |  $12.99");
    System.out.println("    14\"     |  $14.99");
    System.out.println("    16\"     |  $16.99");
    System.out.println();

    // SIZE: user prompt
    System.out.println("What size pizza would you like?");
    System.out.print("10, 12, 14, or 16 (enter the number only): ");
    size = sc.nextInt();
    // SIZE: pricing
    if (size == 10) {
      total += 10.99;
    } else if (size == 12) {
      total += 12.99;
    } else if (size == 14) {
      total += 14.99;
    } else if (size == 16) {
      total += 16.99;
    } else {
      System.out.println("Your input was not one of the choices, so a 12-inch pizza will be made.");
      total += 12.99;
      size = 12;
    }

    System.out.println(); // line-break

    // CRUST: user prompt
    System.out.println("What type of crust do you want?");
    System.out.println("	(H)Hand-tossed, (T) Thin-crust, or (D) Deep-dish");
    System.out.print("enter (H, T, or D):");
    crust = sc.next().toUpperCase().charAt(0);
    switch (crust) {
      case 'H':
        printCrust = "Hand-tossed crust";
        break;
      case 'T':
        printCrust = "Thin-crust";
        break;
      case 'D':
        printCrust = "Deep-dish";
        break;
      default:
        System.out.println("Your input was not one of the choices, so a Hand-tossed crust will be made.");
        printCrust = "Hand-tossed crust";
        break;
    }

    System.out.println(); // line-break

    // TOPPINGS: user prompt
    System.out.println("All pizzas come with cheese.");
    System.out.println("Additional toppings are $1.25 each, choose from:");
    System.out.println("Pepperoni, Sausage, Onion, Mushroom");

    System.out.print("	Do you want Pepperoni? (Y/N): ");
    yesNo = sc.next().toUpperCase().charAt(0);
    if (yesNo == 'Y') {
      numOfToppings++;
      toppings += "Pepperoni ";
    }

    System.out.print("	Do you want Sausage? (Y/N): ");
    yesNo = sc.next().toUpperCase().charAt(0);
    if (yesNo == 'Y') {
      numOfToppings++;
      toppings += "Sausage ";
    }

    System.out.print("	Do you want Onion? (Y/N): ");
    yesNo = sc.next().toUpperCase().charAt(0);
    if (yesNo == 'Y') {
      numOfToppings++;
      toppings += "Onion ";
    }

    System.out.print("	Do you want Mushroom? (Y/N): ");
    yesNo = sc.next().toUpperCase().charAt(0);
    if (yesNo == 'Y') {
      numOfToppings++;
      toppings += "Mushroom ";
    }
    // TOPPINGS: additional charge
    total += (numOfToppings * TOPPING_CHARGE);

    System.out.println(); // line-break

    // Order RECEIPT
    System.out.println("Your order is as follows:");
    System.out.println(size + "-inch pizza"); // SIZE
    System.out.println(printCrust);
    System.out.println(toppings); // TOPPINGS

    // TOTAL PRICING
    System.out.println();
    System.out.printf("The cost of your order is: $%.2f%n", total);
    System.out.printf("The tax is: $%.2f%n", (total * TAX_RATE));
    System.out.printf("The total due is: $%.2f%n", (total + (total * TAX_RATE)));
    System.out.println("Your order will be ready for pickup in 30 minutes.");

    sc.close();

  }

}
