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
import javax.swing.JScrollPane;
import javax.swing.JTable;

/**
 * Class representing an order of pizza to make.
 */
public class PizzaOrder {
    // CONSTANTS

    /**
     * The toppings that are available to put on a pizza.
     */
    final static String[] AVAILABLE_TOPPINGS = { "Pepperoni", "Sausage", "Onion", "Mushroom" };

    /**
     * The names of the owners.
     */
    final static String[] OWNERS = { "Kaylyn", "Tim" };

    /**
     * The amount to deduct from the subtotal when the order is discounted.
     */
    final static double DISCOUNT = 2.00;

    /**
     * The sales tax rate to apply to the order.
     */
    final static double TAX_RATE = 0.0795;

    /**
     * The charge for each topping, besides cheese.
     */
    final static double TOPPING_CHARGE = 1.25;

    // INSTANCE VARIABLES

    /**
     * The size of the pizza to be made.
     */
    int pizzaSize = 12;

    /**
     * The type of crust for the pizza.
     */
    String crust = "Hand-tossed";

    /**
     * Whether or not the order is discounted.
     */
    boolean discounted = false;

    /**
     * The subtotal of the order.
     */
    double subtotal = 0;

    /**
     * The name of the customer for the order.
     */
    String customerName;

    /**
     * An array representing the toppings to put on the pizza.
     */
    ArrayList<String> toppings = new ArrayList<String>();

    /**
     * Creates a new instance of a PizzaOrder object and adds Cheese as a topping.
     */
    public PizzaOrder() {
        this.toppings.add("Cheese"); // all pizzas come with cheese
    }

    /**
     * Executes the pizza ordering program, creating a new PizzaOrder object and
     * setting the necessary attributes.
     * 
     * @param args the command line arguments.
     */
    public static void main(String[] args) {
        PizzaOrder order = new PizzaOrder();
        // NAME: user prompt
        JOptionPane.showMessageDialog(null, "Welcome to Tim and Kaylyn’s Pizzeria");
        order.customerName = JOptionPane.showInputDialog("Enter your first name.");
        // NAME: discount eligibility
        if (hasOwnersName(order.customerName)) {
            order.discounted = true;
            JOptionPane.showMessageDialog(null,
                    "You have the same name as one of the owners, you are eligible for a $2.00 discount!");
        }

        // Show menu
        printMenu();

        // SIZE: user prompt
        order.handlePizzaSize();

        // CRUST: user prompt
        order.handleCrustChoice();

        // TOPPINGS: user prompt
        JOptionPane.showMessageDialog(null,
                "All pizzas come with cheese.\nAdditional toppings are $1.25 each, choose from:\nPepperoni, Sausage, Onion, Mushroom");

        for (String topping : AVAILABLE_TOPPINGS) {
            order.handleToppingChoice(topping);
        }

        order.printSummary();
    }

    /**
     * Prompts the user for their choice of pizza crust and sets the crust type on
     * their order.
     */
    public void handleCrustChoice() {
        char choice = '*';
        try {
            choice = JOptionPane.showInputDialog(
                    "What type of crust do you want?\n(H) Hand-tossed\n(T) Thin-crust\n(D) Deep-dish\nEnter (H, T, or D): ")
                    .toUpperCase().charAt(0);
        } catch (StringIndexOutOfBoundsException e) {
            choice = '*';
        }

        switch (choice) {
            case 'H':
                this.crust = "Hand-tossed";
                break;
            case 'T':
                this.crust = "Thin-crust";
                break;
            case 'D':
                this.crust = "Deep-dish";
                break;
            default:
                this.crust = "Hand-tossed";
                JOptionPane.showMessageDialog(null,
                        "Your input was not one of the choices, so a Hand-tossed crust will be made.");
                break;
        }
    }

    /**
     * Prompts the user for their choice of pizza size and adds the corresponding
     * price to the subtotal.
     */
    public void handlePizzaSize() {
        try {
            this.pizzaSize = Integer.parseInt(JOptionPane
                    .showInputDialog("What size pizza would you like?\n10, 12, 14, or 16 (enter the number only): "));
        } catch (NumberFormatException e) {
            this.pizzaSize = -1;
        }

        if (this.pizzaSize == 10) {
            this.subtotal += 10.99;
        } else if (this.pizzaSize == 12) {
            this.subtotal += 12.99;
        } else if (this.pizzaSize == 14) {
            this.subtotal += 14.99;
        } else if (this.pizzaSize == 16) {
            this.subtotal += 16.99;
        } else {
            this.subtotal += 12.99;
            this.pizzaSize = 12;
            JOptionPane.showMessageDialog(null,
                    "Your input was not one of the choices, so a 12-inch pizza will be made.");
        }
    }

    /**
     * Prompts the user to ask if they want the specified topping. If they do, it is
     * added to the order's toppings array and the TOPPING_CHARGE is added to the
     * subtotal.
     * 
     * @param topping the topping to ask the customer if they want
     */
    public void handleToppingChoice(String topping) {
        char choice = '*';
        while (choice != 'Y' && choice != 'N') {
            try {
                choice = JOptionPane.showInputDialog("Do you want " + topping + "? (Y/N):").toUpperCase().charAt(0);
            } catch (StringIndexOutOfBoundsException e) {
                choice = '*';
            }
        }

        if (choice == 'Y') {
            this.toppings.add(topping);
            this.subtotal += TOPPING_CHARGE;
        }
    }

    /**
     * Checks whether a specified name matches that of one of the owners.
     * 
     * @param name name to check if it matches one of the owners
     * @return true if the name matches one of the owners, false otherwise.
     */
    public static boolean hasOwnersName(String name) {
        for (String owner : OWNERS) {
            if (owner.equalsIgnoreCase(name)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Prints the menu for the user to see.
     */
    public static void printMenu() {
        String[] cols = { "Pizza Size", "Cost" };
        String[][] data = { { "10\"", "$10.99" }, { "12\"", "$12.99" }, { "14\"", "$14.99" }, { "16\"", "$16.99" } };
        JTable menu = new JTable(data, cols);
        menu.setEnabled(false); // disables editing the menu
        JOptionPane.showMessageDialog(null, new JScrollPane(menu));
    }

    /**
     * Calculates the subtotal, tax, and total cost of the order and prints a
     * summary of the order for the user, then tells them when it will be ready.
     */
    public void printSummary() {
        double subtotal = this.subtotal;
        if (this.discounted && subtotal >= DISCOUNT) {
            subtotal -= DISCOUNT;
        }
        double tax = subtotal * TAX_RATE;
        double total = subtotal + tax;
        // ITEMIZATION
        StringBuilder msg = new StringBuilder("Your order is as follows:\n");
        msg.append(this.pizzaSize + "-inch pizza\n"); // SIZE
        msg.append(this.crust + " crust\n"); // CRUST
        msg.append(String.join(", ", this.toppings) + "\n"); // TOPPINGS

        // RECIEPT
        msg.append("The cost of your order is: $" + String.format("%.2f", subtotal) + "\n");
        msg.append("The tax is: $" + String.format("%.2f", tax) + "\n");
        msg.append("The total due is: $" + String.format("%.2f", total) + "\n");
        msg.append("Your order will be ready for pickup in 30 minutes.");
        JOptionPane.showMessageDialog(null, msg.toString());
    }
}
