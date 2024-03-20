/*
  Nguyen, Kaylyn
  Shaker, Tim     

  April 5, 2024

  CS A170
  Prof. Mayada Alani
  Project 2
*/

import java.util.Scanner;

public class PizzaOrder {

  public static void main(String[] args) {
    // Scanner and Variables
    Scanner sc = new Scanner(System.in);

    int size = 12;
    char crust = 'H';

    String toppings = "Cheese ";
    char yesNo = ' ';

    // user-prompt NAME
    System.out.println("Welcome to Tim and Kaylyn’s Pizzeria");
    System.out.print("Enter your first name: ");
    String name = sc.next();

    // MENU: SIZE & COST
    System.out.println();
    System.out.println(" Pizza Size |   Cost ");
    System.out.println("---------------------");
    System.out.println("    10\"     |  $10.99");
    System.out.println("    12\"     |  $12.99");
    System.out.println("    14\"     |  $14.99");
    System.out.println("    16\"     |  $16.99");
    System.out.println();

    // user-prompt SIZE
    System.out.println("What size pizza would you like?");
    System.out.print("10, 12, 14, or 16 (enter the number only): ");
    size = sc.nextInt();

    System.out.println(); // line-break

    // user-prompt CRUST
    System.out.println("What type of crust do you want?");
    System.out.println("	(H)Hand-tossed, (T) Thin-crust, or (D) Deep-dish");
    System.out.print("enter (H, T, or D):");
    crust = sc.next().toUpperCase().charAt(0);

    System.out.println(); // line-break

    // user-prompt TOPPINGS
    System.out.println("All pizzas come with cheese.");
    System.out.println("Additional toppings are $1.25 each, choose from:");
    System.out.println("Pepperoni, Sausage, Onion, Mushroom");

    System.out.print("	Do you want Pepperoni? (Y/N): ");
    yesNo = sc.next().toUpperCase().charAt(0);
    if (yesNo == 'Y') {
      toppings += "Pepperoni ";
    }
    System.out.println();// line-break

    System.out.print("	Do you want Sausage? (Y/N): ");
    yesNo = sc.next().toUpperCase().charAt(0);
    if (yesNo == 'Y') {
      toppings += "Sausage ";
    }

    System.out.println();// line-break

    System.out.print("	Do you want Onion? (Y/N): ");
    yesNo = sc.next().toUpperCase().charAt(0);
    if (yesNo == 'Y') {
      toppings += "Onion ";
    }

    System.out.println();// line-break

    System.out.print("	Do you want Mushroom? (Y/N): ");
    yesNo = sc.next().toUpperCase().charAt(0);
    if (yesNo == 'Y') {
      toppings += "Mushroom ";
    }

    System.out.println();
    System.out.println(); // line-break

    // Order RECEIPT
    System.out.println("Your order is as follows:");
    System.out.println(size + "-inch pizza");
    System.out.println(toppings);
    switch (crust) {
      case 'H':
        System.out.println("Hand-tossed crust");
        break;
      case 'T':
        System.out.println("Thin-crust");
        break;
      case 'D':
        System.out.println("Deep-dish");
        break;
      default:
        System.out.println("Invalid crust choice");
        break;
    }

    sc.close();

  }

}
