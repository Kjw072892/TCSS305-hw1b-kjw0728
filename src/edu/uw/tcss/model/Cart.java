package edu.uw.tcss.model;

import java.math.BigDecimal;

/**
 * @author Kassie Whitney
 * @version 1.15.25
 */
public interface Cart {
    /**
     *
     * @param theOrder Adds ItemOrder objects to the shopping cart
     */
    void add(ItemOrder theOrder);

    /**
     *
     * @param theMembership boolean if the customer has a membership
     */
    void setMembership(boolean theMembership);

    /**
     *
     * @return the calculated total of all the item
     */
    BigDecimal calculateTotal();

    /**
     * Clears the shopping cart entirely.
     */
    void clear();

    /**
     *
     * @return gets the number of items in a shopping cart.
     */
    CartSize getCartSize();

    /**
     *
     * @param itemOrderCount the number of items within the order (not individual items).
     * @param itemCount the number of individual items in the order.
     */
    record CartSize(int itemOrderCount, int itemCount) { }

    // https://docs.oracle.com/en/java/javase/17/language/records.html
}
