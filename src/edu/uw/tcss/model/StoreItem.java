package edu.uw.tcss.model;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.Locale;
import java.util.Objects;

/**
 *  @author Kassie Whitney
 * @version 1.20.25
 */
public class StoreItem implements Item {

    /**
     * Stores the default minValue
     */
    private static final int DEFAULT_INT = 0;

    /**
     * Stores the default Bigdecimal minValue
     */
    private static final BigDecimal DEFAULT_BD = BigDecimal.ZERO;

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
     * @throws IllegalArgumentException when thePrice is less than zero.
     * @throws NullPointerException when theName is null.
     */
    public StoreItem(final String theName, final BigDecimal thePrice)
        throws IllegalArgumentException, NullPointerException {

        this(theName, thePrice, 0, BigDecimal.ZERO);

    }

    /**
     *
     * Constructor for the StoreItem Class.
     *
     * @param theName The name of the item.
     * @param thePrice The price of the item.
     * @param theBq The bulk quanity of the item.
     * @param theBp The price of the item in bulk quanity.
     * @throws NullPointerException theName, thePrice, or theBp can not be null.
     * @throws IllegalArgumentException thePrice or theBq or theBp must not be negative,
     * @throws IllegalArgumentException theName must not be empty
     */
    public StoreItem(final String theName, final BigDecimal thePrice, final int theBq,
                 final BigDecimal theBp) throws IllegalArgumentException {
        super();
        new StoreItem(theName, thePrice);

        //Checks if objects are null
        Objects.requireNonNull(theName, "theName can't be null.");
        Objects.requireNonNull(thePrice, "thePrice can not be null.");
        Objects.requireNonNull(theBp, "theBP cannot be null");

        //Checks if quantities are less than zero and if theName is empty.
        if (thePrice.doubleValue() > 0 || theBq > 0 || theBp.doubleValue() < 0
                || theName.isEmpty()) {
            throw new IllegalArgumentException("Parameters must not be negative and theName "
                    + "must not be empty");
        } else {
            myName = theName;
            myPrice = thePrice;
            myBq = theBq;
            myBp = theBp;
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

        return theItem != null && theItem.getClass() == this.getClass()
                && ((StoreItem) theItem).getName().equals(this.myName)
                && ((StoreItem) theItem).getPrice().equals(this.myPrice)
                && ((StoreItem) theItem).getBulkPrice().equals(myBp)
                && ((StoreItem) theItem).getBulkQuantity() == myBq;
    }

    @Override
    public int hashCode() {
        return Objects.hash(myName, myPrice, myBq, myBp);
    }
}
