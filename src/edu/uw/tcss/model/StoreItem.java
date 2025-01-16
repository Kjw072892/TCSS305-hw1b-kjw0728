package edu.uw.tcss.model;

import java.math.BigDecimal;
import java.util.Objects;

/**
 * @author Kassie Whitney
 * @version 1.15.25
 */
public class StoreItem implements Item {

    /**
     * Field Variable that stores the item name.
     */
    private final String myName;

    /**
     * Stores the item price.
     */
    private final BigDecimal myPrice;

    /**
     * Stores the item bulk quantity.
     */
    private final int myBq;

    /**
     * Stores the item's price in bulk.
     */
    private final BigDecimal myBp;

    /**
     * Stores boolean values denoting if item is in bulk.
     */
    private final boolean myIsBulk;


    /**
     *
     * Constructor for the StoreItem class.
     *
     * @param theName The name of the item.
     * @param thePrice  The price of the item.
     */
    public StoreItem(final String theName, final BigDecimal thePrice)
            throws IllegalArgumentException, NullPointerException {

        super();

        if (theName.isEmpty() || thePrice == null) {

            throw new NullPointerException("Parameters must not be null");
        }
        if (thePrice.intValue() < 0) {

            throw new IllegalArgumentException("Price must be greater than or equal to zero");

        } else {

            myName = theName;
            myPrice = thePrice;
            myBq = 0;
            myBp = BigDecimal.ZERO;
            myIsBulk = false;

        }
    }

    /**
     *
     * Constructor for the StoreItem Class.
     *
     * @param theName The name of the item.
     * @param thePrice The price of the item.
     * @param theBq The bulk quanity of the item.
     * @param theBp The price of the item in bulk quanity.
     */
    public StoreItem(final String theName, final BigDecimal thePrice, final int theBq,
                     final BigDecimal theBp) throws IllegalArgumentException {

        super();

        new StoreItem(theName, thePrice);

        if (0 <= theBq && 0.0 <= theBp.doubleValue()) {

            myName = theName;
            myPrice = thePrice;
            myBq = theBq;
            myBp = theBp;
            myIsBulk = true;

        } else {

            throw new IllegalArgumentException("Parameters must be greater than zero");

        }
    }

    @Override
    public String getName() {
        return myName;
    }

    @Override
    public BigDecimal getPrice() {
        return myPrice;
    }

    @Override
    public int getBulkQuantity() {
        return myBq;
    }

    @Override
    public BigDecimal getBulkPrice() {
        return myBp;
    }

    @Override
    public boolean isBulk() {
        return myIsBulk;
    }

    /**
     *
     * Returns a string representing of the item name, price, and if applicable, bulk price
     * and bulk quantity.
     *
     * @return "[ItemName], $[price]" or
     * "[ItemName], $[price] ([bulk quantity] for $[bulk price])".
     */
    @Override
    public String toString() {
        final String itemInfo;

        if (myIsBulk) {
            itemInfo = myName + ", $" + myPrice + " (" + myBq + " for $" + myBp + ")";
        } else {
            itemInfo = myName + ", $" + myPrice;
        }
        return itemInfo;
    }

    /**
     *
     * Equals method takes in any object and checks if its the same object as THIS StoreItem
     * object.
     *
     * @param theItem Item object of type Object.
     * @return True if the two objects are equal, false if not equal.
     */

    @Override
    public boolean equals(final Object theItem) {

        // checks if object reference are the same
        if (this == theItem) {
            return true;
        }

        // checks if object is null, or if the items are of the same class
        if (theItem == null || getClass() != theItem.getClass()) {
            return false;
        }

        // casting the StoreItem object to theItem
        final StoreItem ti = (StoreItem) theItem;

        //returns true if both objects are equal, else returns false
        return Objects.equals(ti.getName(), getName())
                && Objects.equals(ti.getPrice(), getPrice())
                && ti.getBulkQuantity() == getBulkQuantity()
                && Objects.equals(ti.getBulkPrice(), getBulkPrice());
    }

    @Override
    public int hashCode() {
        return Objects.hash(myName, myPrice, myBq, myBp);
    }
}
