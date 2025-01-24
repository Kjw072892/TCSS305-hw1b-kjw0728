package edu.uw.tcss.model;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;
import java.util.Hashtable;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
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

     /**
     * Zero constant to test cart size.
     */
    private static final int ZERO_CART_SIZE = 0;

    /**
     * Quantity constant to test cart size
     */
    private static final int TEST_NEW_QUANTITY = 9;

    /**
     * Total number of items created for testing
     */
    private int myTotalNumOfItems;



    @BeforeEach
    void createItemOrder() {
        StoreItemOrder sI;

        for (int i = 1; i < SIX; i++) {
            sI = new StoreItemOrder(new StoreItem("ITEM" + i,
                   BigDecimal.valueOf(i)), i);
            TEST_SHOPPING_CART.put(sI.getItem().getName(), sI);
        }
        myTotalNumOfItems =
                TEST_SHOPPING_CART.values().stream().mapToInt(ItemOrder::getQuantity).sum();

        for (final Map.Entry<String, ItemOrder> itemOrder : TEST_SHOPPING_CART.entrySet()) {
            STORE_CART_INST.add(itemOrder.getValue());
        }
    }


    @Test
    void testAddMethod() {
        assertAll("Testing if Shopping cart stores items",
                () -> assertEquals(
                        TEST_SHOPPING_CART.size(),
                        STORE_CART_INST.getCartSize().itemOrderCount(),
                        "Either your getCartSize was not configured properly "
                                + "or your add method did not add the item properly")
        );

        STORE_CART_INST.add(new StoreItemOrder(
                new StoreItem("ITEM1", BigDecimal.valueOf(1)), TEST_NEW_QUANTITY));

        assertAll("Testing if shopping cart duplicated items when quantity changes.",
                () -> assertEquals(
                        TEST_SHOPPING_CART.size(),
                        STORE_CART_INST.getCartSize().itemOrderCount(),
                        "You may have a duplicate itemOrder in your shopping cart, "
                        + "or your add method removed the item order.")
        );
    }

    @Test
    void testNumberOfItemsInCartMatches() {
        assertAll("Testing the quantity of items in shopping cart",
                () -> assertEquals(
                        myTotalNumOfItems,
                        STORE_CART_INST.getCartSize().itemCount(),
                "Was expecting " + TEST_SHOPPING_CART.values().stream())
        );

    }

    @Test
    void testClearMethod() {

        STORE_CART_INST.clear();

        assertAll("Testing if the clear method successfully clears the shopping cart",
                () -> assertEquals(
                        ZERO_CART_SIZE,
                        STORE_CART_INST.getCartSize().itemCount(),
                        "Expected 0 individual items but got "
                                + STORE_CART_INST.getCartSize().itemCount()),
                () -> assertEquals(
                        ZERO_CART_SIZE,
                        STORE_CART_INST.getCartSize().itemOrderCount(),
                        "Expected 0 in itemOrderCount, but got "
                                +  STORE_CART_INST.getCartSize().itemOrderCount())
        );
    }
}
