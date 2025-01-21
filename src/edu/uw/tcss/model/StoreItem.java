package edu.uw.tcss.model;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.Locale;
import java.util.Objects;

/**
 * @author Kassie Whitney
 * @version 1.20.25
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
     *
     * Constructor for the StoreItem class.
     *
     * @param theName The name of the item.
     * @param thePrice  The price of the item.
     */
    public StoreItem(final String theName, final BigDecimal thePrice)
            throws IllegalArgumentException, NullPointerException {

        super();

        if (theName == null || thePrice == null) {

            throw new NullPointerException("Parameters must not be null");
        }
        if (thePrice.doubleValue() < 0 || theName.isEmpty()) {

            throw new IllegalArgumentException("Price must be greater than or equal to zero");

        } else {

            myName = theName;
            myPrice = thePrice;
            myBq = 0;
            myBp = BigDecimal.ZERO;

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
        if (theBq >= 0 && theBp.doubleValue() >= 0 && thePrice.doubleValue() >= 0) {
            myName = theName;
            myPrice = thePrice;
            myBq = theBq;
            myBp = theBp;
            //myIsBulk = true;
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

        return myBq > 0;
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

        final NumberFormat nf = NumberFormat.getCurrencyInstance(Locale.US);

        final String itemInfo;

        final String frmtPrice = nf.format(myPrice);

        final String frmtBulkPrice = nf.format(myBp);

        if (myBq > 0) {
            itemInfo = myName + ", " + frmtPrice + " (" + myBq + " for " + frmtBulkPrice + ")";

        } else {
            itemInfo = myName + ", " + frmtPrice;

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

        final StoreItem items = (StoreItem) theItem;

        return theItem != null && theItem.getClass() == this.getClass()
                && items.getName().equals(this.getName())
                && items.getPrice().equals(this.getPrice())
                && items.getBulkPrice().equals(getBulkPrice())
                && items.getBulkQuantity() == this.getBulkQuantity();
    }

    @Override
    public int hashCode() {
        return Objects.hash(myName, myPrice, myBq, myBp);
    }
}
