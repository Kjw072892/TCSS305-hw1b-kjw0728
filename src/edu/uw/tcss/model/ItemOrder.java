package edu.uw.tcss.model;

/**
 * @author Kassie Whitney
 *
 * @version 1.15.25
 */
public interface ItemOrder {
    /**
     *
     * @return returns the object Item
     */
    Item getItem();

    /**
     *
     * @return returns the quantity of the item
     */
    int getQuantity();
}
