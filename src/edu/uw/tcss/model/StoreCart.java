package edu.uw.tcss.model;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Hashtable;
import java.util.Map;

/**
 * @author Kassie Whitney
 * @version 1.20.25
 */
public class StoreCart implements Cart {

    /**
     * Stores Item object reference to add to shopping cart.
     */
    private final Hashtable<String, ItemOrder> myShoppingCart;

    /**
     * Stores membership data
     */
    private boolean myMembership;

    /**
     * StoreCart constructor creates a new empty shopping cart.
     */
    public StoreCart() {
        super();
        myShoppingCart = new Hashtable<>();
        myMembership = false;

    }


    @Override
    public void add(final ItemOrder theOrder) {

        final String itemName = theOrder.getItem().getName();

        // Checks if the hashTable myShoppingCart contains the key (item name)
        if (myShoppingCart.containsKey(itemName)) {
            // Checks if the obj. are equal and theOrder quantity is not zero
            if (myShoppingCart.get(itemName).getItem().equals(theOrder.getItem())
                    && theOrder.getQuantity() != 0) {
                myShoppingCart.replace(itemName, theOrder);
                getCartSize();
            }
            //removes the item from the shopping cart if
            if (theOrder.getQuantity() != myShoppingCart.get(itemName).getQuantity()
                    || theOrder.getQuantity() == 0) {
                myShoppingCart.remove(itemName);
                getCartSize();
            }
        } else {

            if (theOrder.getQuantity() != 0) {
                myShoppingCart.put(itemName, theOrder);
                getCartSize();
            }
            getCartSize();
        }
    }


    @Override
    public void setMembership(final boolean theMembership) {
        myMembership = theMembership;
    }

    @Override
    public BigDecimal calculateTotal() {
        BigDecimal total = BigDecimal.ZERO;

        for (final Map.Entry<String, ItemOrder> itemOrder : myShoppingCart.entrySet()) {
            // stores the item in storeItem variable
            final Item storeItem = itemOrder.getValue().getItem();
           // Stores the itemQuantity into quantity variable
            final int quantity = itemOrder.getValue().getQuantity();

            //Chekcs if the item is a bulk item and if the quantity is more than bulk item
            if (storeItem.isBulk() && quantity >= storeItem.getBulkQuantity()
                    && myMembership) {

                // divide the quanity by the items bulk quantity
                final int bulkSets = quantity / storeItem.getBulkQuantity();

                //gives you the number of indivual items left over
                final int remainderItems = quantity % storeItem.getBulkQuantity();

                //adds up the total price of bulk sets and remaining individual items that
                // was left over from the bulk set.
                total = total.add(storeItem.getBulkPrice().
                        multiply(BigDecimal.valueOf(bulkSets))).
                        add(storeItem.getPrice().multiply(BigDecimal.valueOf(remainderItems)));

            } else {

                //if not bulk item, item price is multiplied by quantity to get total price
                total = total.add(storeItem.getPrice().multiply(BigDecimal.valueOf(quantity)));
            }

        }
        return total.setScale(2, RoundingMode.HALF_EVEN);
    }

    @Override
    public void clear() {
        myShoppingCart.clear();
    }

    @Override
    public CartSize getCartSize() {

        //Stores the number of unique items thats in the shopping cart
        final int cartSize = myShoppingCart.size();

        //Streams through the hash table adding up all the quantities of ItemOrder
        final int itemQuantity = myShoppingCart.values().stream().
                mapToInt(ItemOrder::getQuantity).sum();

        return new CartSize(cartSize, itemQuantity);
    }

    @Override
    public String toString() {
        return "Shopping Cart= " + myShoppingCart;
    }
}
