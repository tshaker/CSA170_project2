/*
  Nguyen, Kaylyn
  Shaker, Tim     

  April 5, 2024

  CS A170
  Prof. Mayada Alani
  Project 2
*/

import java.util.ArrayList;

import javax.swing.JOptionPane;

public class PizzaTwo {
  final static double DISCOUNT = 2.00;
  final static double TAX_RATE = 0.0795;
  final static double TOPPING_CHARGE = 1.25;
  final static String[] AVAILABLE_TOPPINGS = { "Pepperoni", "Sausage", "Onion", "Mushroom" };
  final static String[] OWNERS = { "Kaylyn", "Tim" };

  private static boolean discounted = false;
  private static int size = 12;
  private static char crustChoice = 'H';
  private static String crust = "";
  private static ArrayList<String> toppings = new ArrayList<String>();
  private static double total = 0;

  private static boolean hasOwnersName(String name) {
    for (String owner : OWNERS) {
      if (owner.equalsIgnoreCase(name)) {
        return true;
      }
    }
    return false;
  }

  private static void printMenu() {
    String msg = "";
    msg += "Pizza Size  |   Cost \n";
    msg += "---------------------\n";
    msg += "    10\"    |  $10.99\n";
    msg += "    12\"    |  $12.99\n";
    msg += "    14\"    |  $14.99\n";
    msg += "    16\"    |  $16.99\n";
    JOptionPane.showMessageDialog(null, msg);
  }

  private static void getToppingChoice(String topping) {
    String msg = "Do you want " + topping + "? (Y/N):";
    char choice = JOptionPane.showInputDialog(msg).toUpperCase().charAt(0);
    while (choice != 'Y' && choice != 'N') {
      choice = JOptionPane.showInputDialog("Please enter Y for Yes or N for No.\n" + msg).toUpperCase().charAt(0);
    }

    if (choice == 'Y') {
      toppings.add(topping);
    }
  }

  public static void main(String[] args) {
    // NAME: user prompt
    JOptionPane.showMessageDialog(null, "Welcome to Tim and Kaylyn’s Pizzeria");
    String name = JOptionPane.showInputDialog("Enter your first name.");
    // NAME: discount eligibility
    if (hasOwnersName(name)) {
      discounted = true;
      JOptionPane.showMessageDialog(null,
          "You have the same name as one of the owners, you are eligible for a $2.00 discount!");
    }

    printMenu();

    // SIZE: user prompt
    String pizzaSize = JOptionPane
        .showInputDialog("What size pizza would you like?\n10, 12, 14, or 16 (enter the number only): ");
    size = Integer.parseInt(pizzaSize);
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
      JOptionPane.showMessageDialog(null, "Your input was not one of the choices, so a 12-inch pizza will be made.");
      total += 12.99;
      size = 12;
    }

    // CRUST: user prompt
    crustChoice = JOptionPane
        .showInputDialog(
            "What type of crust do you want?\n(H) Hand-tossed\n(T) Thin-crust\n(D) Deep-dish\nEnter (H, T, or D): ")
        .toUpperCase().charAt(0);
    switch (crustChoice) {
      case 'H':
        crust = "Hand-tossed crust";
        break;
      case 'T':
        crust = "Thin-crust";
        break;
      case 'D':
        crust = "Deep-dish";
        break;
      default:
        JOptionPane.showMessageDialog(null,
            "Your input was not one of the choices, so a Hand-tossed crust will be made.");
        crust = "Hand-tossed crust";
        break;
    }

    // TOPPINGS: user prompt
    JOptionPane.showMessageDialog(null,
        "All pizzas come with cheese.\nAdditional toppings are $1.25 each, choose from:\nPepperoni, Sausage, Onion, Mushroom");
    toppings.add("Cheese");

    for (String topping : AVAILABLE_TOPPINGS) {
      getToppingChoice(topping);
    }

    // TOPPINGS: additional charge for toppings
    // do not charge for cheese
    total += ((toppings.size() - 1) * TOPPING_CHARGE);
    if (discounted && total >= DISCOUNT) {
      total -= DISCOUNT;
    }

    printSummary();
  }

  private static void printSummary() {
    String msg = "Your order is as follows:\n";
    msg += size + "-inch pizza\n"; // SIZE
    msg += crust + "\n";
    msg += String.join(", ", toppings) + "\n"; // TOPPINGS
    msg += "The cost of your order is: $" + String.format("%.2f", total) + "\n";
    msg += "The tax is: $" + String.format("%.2f", (total * TAX_RATE)) + "\n";
    msg += "The total due is: $" + String.format("%.2f", total + (total * TAX_RATE)) + "\n";
    msg += "Your order will be ready for pickup in 30 minutes.";
    JOptionPane.showMessageDialog(null, msg);
  }
}
