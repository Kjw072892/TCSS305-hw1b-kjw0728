package edu.uw.tcss.model;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;
import java.util.Hashtable;
import org.junit.jupiter.api.Test;


/**
 * @author Kassie Whitney
 * @version 1.20.25
 */
public class CartTest {
    /**
     * Number of items thats been added to shopping cart
     */
    private static final int SIX = 6;


    /**
     * Instance of StoreCart
     */
    private static final StoreCart STORE_CART_INST = new StoreCart();

    /**
     * Test Shopping cart data container
     */
    private static final Hashtable<String, ItemOrder> TEST_SHOPPING_CART = new Hashtable<>();


    @Test
    void testAddMethod() {
        StoreItemOrder sI;
        int totalNumOfItems = 0;
        for (int i = 1; i < SIX; i++) {
            sI = new StoreItemOrder(new StoreItem("ITEM" + i,
                   BigDecimal.valueOf(i)), i);
            TEST_SHOPPING_CART.put(sI.getItem().getName(), sI);
            STORE_CART_INST.add(sI);

            totalNumOfItems = TEST_SHOPPING_CART.values().stream().
                    mapToInt(ItemOrder::getQuantity).sum();
        }

        final int finalTotalNumOfItems = totalNumOfItems;
        assertAll("Testing if Shopping cart stores items",
                () -> assertEquals(
                        TEST_SHOPPING_CART.size(),
                    STORE_CART_INST.getCartSize().itemOrderCount(),
                        "Either your getCartSize was not configured properly "
                                + "or your add method did not add the item properly"),

                () -> assertEquals(
                        finalTotalNumOfItems,
                        STORE_CART_INST.getCartSize().itemCount(),
                        "Was expecting " + TEST_SHOPPING_CART.values().stream())
        );
    }
}
