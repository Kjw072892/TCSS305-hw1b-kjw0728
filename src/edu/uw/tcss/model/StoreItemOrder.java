package edu.uw.tcss.model;

/**
 * @author Kassie Whitney
 * @version 1.15.25
 */
public final class StoreItemOrder implements ItemOrder {
    /**
     * Stores the reference to the Item Object.
     */
    private final Item myItem;

    /**
     * Stores the quantity of the Item object.
     */
    private final int myQuantity;




    /**
     *
     * Constructor for StoreItemOrder.
     *
     * @param theItem The object reference of the Item object.
     * @param theQuantity The quantity of the given Item; object type inte.
     */
    public StoreItemOrder(final Item theItem, final int theQuantity)
            throws IllegalArgumentException, NullPointerException {

        super();

        //Checks if object passed is null or if quantity is less than 0
        if (theItem == null) {
            throw new NullPointerException("Item object must not be null");
        } else if (theQuantity < 0) {
            throw new IllegalArgumentException("Quantity must be greater than or equal to "
                    + "zero");
        } else {

            myItem = theItem;
            myQuantity = theQuantity;
        }
    }


    @Override
    public Item getItem() {
        return myItem;
    }
    

    @Override
    public int getQuantity() {
        return myQuantity;
    }

    /**
     *
     * Creates a string of the object name and the quantity of the object.
     *
     * @return "[Item] (Qty: [quantity])".
     */
    @Override
    public String toString() {
        return "Item Name= " + myItem.getName() + " (Quantity= " + myQuantity + ")";
    }

}
