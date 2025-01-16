package edu.uw.tcss.model;

import java.math.BigDecimal;

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
     * Stores the item price
     */
    private final BigDecimal myPrice;

    /**
     * Stores the item bulk quantity
     */
    private final int myBq;

    /**
     * Stores the item's price in bulk
     */
    private final BigDecimal myBp;

    /**
     * Stores boolean if item is bulk
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
}
