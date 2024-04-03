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

public class PizzaOrder {
    // CONSTANTS
    final static String[] AVAILABLE_TOPPINGS = { "Pepperoni", "Sausage", "Onion", "Mushroom" };
    final static String[] OWNERS = { "Kaylyn", "Tim" };
    final static double DISCOUNT = 2.00;
    final static double TAX_RATE = 0.0795;
    final static double TOPPING_CHARGE = 1.25;

    // INSTANCE VARIABLES
    int pizzaSize = 12;
    String crust = "Hand-tossed";
    boolean discounted = false;
    double subtotal = 0;
    String customerName;
    ArrayList<String> toppings = new ArrayList<String>();

    public PizzaOrder() {
        this.toppings.add("Cheese");
    }

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
        order.getPizzaSize();

        // CRUST: user prompt
        order.getCrustChoice();

        // TOPPINGS: user prompt
        JOptionPane.showMessageDialog(null,
                "All pizzas come with cheese.\nAdditional toppings are $1.25 each, choose from:\nPepperoni, Sausage, Onion, Mushroom");

        for (String topping : AVAILABLE_TOPPINGS) {
            order.getToppingChoice(topping);
        }

        order.printSummary();
    }

    private void getCrustChoice() {
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

    private void getPizzaSize() {
        try {
            this.pizzaSize = Integer.parseInt(
                    JOptionPane.showInputDialog(
                            "What size pizza would you like?\n10, 12, 14, or 16 (enter the number only): "));
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

    private void getToppingChoice(String topping) {
        char choice = '*';
        while (choice != 'Y' && choice != 'N') {
            try {
                choice = JOptionPane.showInputDialog("Do you want " + topping + "? (Y/N):").toUpperCase().charAt(0);
            } catch (StringIndexOutOfBoundsException e) {
                choice = 'N';
            }
        }

        if (choice == 'Y') {
            this.toppings.add(topping);
            this.subtotal += TOPPING_CHARGE;
        }
    }

    private static boolean hasOwnersName(String name) {
        for (String owner : OWNERS) {
            if (owner.equalsIgnoreCase(name)) {
                return true;
            }
        }
        return false;
    }

    private static void printMenu() {
        String[] cols = { "Pizza Size", "Cost" };
        String[][] data = { { "10\"", "$10.99" }, { "12\"", "$12.99" }, { "14\"", "$14.99" }, { "16\"", "$16.99" } };
        JTable menu = new JTable(data, cols);
        menu.setEnabled(false); // disables editing the menu
        JOptionPane.showMessageDialog(null, new JScrollPane(menu));
    }

    private void printSummary() {
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
