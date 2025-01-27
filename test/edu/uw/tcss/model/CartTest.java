package edu.uw.tcss.model;

import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertNotNull;


import java.math.BigDecimal;
import java.util.Hashtable;
import java.util.Map;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


/**
 * @author Kassie Whitney
 * @version 1.26.25
 */
public class CartTest {

     /**
     * Number of items thats been added to shopping cart
     */
    private static final int MAX_NUMBER_OF_ITEMS = 5;

    /**
     * Cost of all goods without membership discount
     */
    private static final BigDecimal PRICE_WITHOUT_MEMBERSHIP = new BigDecimal("52.00");

      /**
     * The name of the bulk item used in testing.
     */
    private static final String BULK_ITEM_NAME = "Bulk Item";

    /**
     * The bulk price of the item used in testing.
     */
    private static final String BULK_PRICE = "4.00";

    /**
     * The bulk quantity of the item used in testing.
     */
    private static final int BULK_QUANTITY = 10;

    /**
     * Cost of all goods with membership
     */
    private static final BigDecimal PRICE_WITH_MEMBERSHIP = new BigDecimal("36.00");

    /**
     * Item name for item quantity change test
     */

    private static final String ITEM_IN_SHOPPINGCART = "ITEM1";

    /**
     * BigDecimal price for store item
     */
    private static final BigDecimal ITEM_PRICE = BigDecimal.valueOf(2.00);

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
    private static final int TEST_NEW_QUANTITY = 11;

    /**
     * Zero quantity constant
     */
    private static final int ZERO_QUANTITY = 0;

     /**
     * Test Item for Item Order
     */
    private static final StoreItem TEST_STORE_ITEM = new StoreItem("ITEM1",
            ITEM_PRICE);

    /**
     * Object of type storeItemOrder for bulk testing
     */
    private static final StoreItemOrder TEST_BULK_ITEM_ORDER =
                new StoreItemOrder(new StoreItem(BULK_ITEM_NAME, ITEM_PRICE, BULK_QUANTITY,
                        new BigDecimal(BULK_PRICE)), TEST_NEW_QUANTITY);

    /**
     * Total number of indvidual items in the TEST_SHOPPING_CART
     */
    private int myTotalNumOfTestItems;


    @BeforeEach
    void createItemOrder() {
        StoreItemOrder itemOrder;
        for (int i = 1; i <= MAX_NUMBER_OF_ITEMS; i++) {
            itemOrder = new StoreItemOrder(new StoreItem("ITEM" + i,
                   ITEM_PRICE), i);
            TEST_SHOPPING_CART.put(itemOrder.getItem().getName(), itemOrder);
        }
        myTotalNumOfTestItems =
                TEST_SHOPPING_CART.values().stream().mapToInt(ItemOrder::getQuantity).sum();
    }

    @BeforeEach
    void fillRealShoppingCart() {

        //Iterates through the Test shopping cart and copies the content to live shopping cart
        for (final Map.Entry<String, ItemOrder> itemOrder : TEST_SHOPPING_CART.entrySet()) {
            STORE_CART_INST.add(itemOrder.getValue());
        }
    }
    @AfterEach
    void revertShoppingCartToDefault() {
        STORE_CART_INST.clear();
        TEST_SHOPPING_CART.clear();
    }


    @Test
    void testAddMethod() {
        assertAll("Testing if Shopping cart stores items.",
                () -> assertEquals(
                        TEST_SHOPPING_CART.size(),
                        STORE_CART_INST.getCartSize().itemOrderCount(),
                        "Either your getCartSize was not configured properly "
                                + "or your add method did not add the item properly.")
        );

    }

    @Test
    void testNewItemQuantity() {
        //STORE_CART_INST is set to a default value of with no bulk-items:
        // Shopping Cart=
        // {ITEM4=[Name-ITEM4 : Qty-4],
        // ITEM3=[Name-ITEM3 : Qty-3],
        // ITEM2=[Name-ITEM2 : Qty-2],
        // ITEM1=[Name-ITEM1 : Qty-1],
        // ITEM5=[Name-ITEM5 : Qty-5]}

        // Created a new itemOrder of an item already in shopping cart
        final ItemOrder overrideItemOrder =
                new StoreItemOrder(TEST_STORE_ITEM, TEST_NEW_QUANTITY);

        // Removed the item order in the Test shopping cart
        TEST_SHOPPING_CART.remove(ITEM_IN_SHOPPINGCART);

        // Added the overriden item order
        TEST_SHOPPING_CART.put(ITEM_IN_SHOPPINGCART, overrideItemOrder);

        // Added the overriden item order to the actual shopping cart
        STORE_CART_INST.add(overrideItemOrder);

        // Storing the value of the number of items from the test shopping cart
        myTotalNumOfTestItems = TEST_SHOPPING_CART.values().stream().
                                mapToInt(ItemOrder::getQuantity).sum();

        assertAll("Testing if shopping cart updates item quantity when similar item is"
                        + " passed.",
                () -> assertEquals(
                        myTotalNumOfTestItems,
                        STORE_CART_INST.getCartSize().itemCount(),
                        "Your item quantity may not have gotten "
                                + "updated in your add method.")
        );
    }


    @Test
    void testZeroQuantityPassed() {
        //STORE_CART_INST is set to a default value of with no bulk-items:
        // Shopping Cart=
        // {ITEM4=[Name-ITEM4 : Qty-4],
        // ITEM3=[Name-ITEM3 : Qty-3],
        // ITEM2=[Name-ITEM2 : Qty-2],
        // ITEM1=[Name-ITEM1 : Qty-1],
        // ITEM5=[Name-ITEM5 : Qty-5]}

        STORE_CART_INST.add(new StoreItemOrder(
                new StoreItem(ITEM_IN_SHOPPINGCART, ITEM_PRICE), ZERO_QUANTITY));

        TEST_SHOPPING_CART.remove(ITEM_IN_SHOPPINGCART);

        assertAll("Testing if shopping cart removes item if same item was passed "
                        + "with a 0 quantity.",
                () -> assertEquals(
                        TEST_SHOPPING_CART.size(),
                        STORE_CART_INST.getCartSize().itemOrderCount(),
                        "Your add method did not handle a zero quantity item order "
                                + "properly.")
        );

    }

    @Test
    void testNumberOfItemsInCartMatches() {
        //STORE_CART_INST is set to a default value of with no bulk-items:
        // Shopping Cart=
        // {ITEM4=[Name-ITEM4 : Qty-4],
        // ITEM3=[Name-ITEM3 : Qty-3],
        // ITEM2=[Name-ITEM2 : Qty-2],
        // ITEM1=[Name-ITEM1 : Qty-1],
        // ITEM5=[Name-ITEM5 : Qty-5]}

        assertAll("Testing the quantity of items in shopping cart.",
                () -> assertEquals(
                        myTotalNumOfTestItems,
                        STORE_CART_INST.getCartSize().itemCount(),
                "Your add method was not configured properly.")
        );

    }

    @Test
    void testZeroQuantityItemOrderAsFirstItem() {
        //STORE_CART_INST is empty

        STORE_CART_INST.clear();

        STORE_CART_INST.add(new StoreItemOrder(
                new StoreItem(ITEM_IN_SHOPPINGCART, ITEM_PRICE), ZERO_QUANTITY));

        assertAll("Testing the quantity of items in shopping cart.",
                () -> assertEquals(
                        0,
                        STORE_CART_INST.getCartSize().itemOrderCount(),
                        "Your cart added a zero quantity item order")
        );
    }

    @Test
    void testNotAddingItemsCorrectly() {
        assertAll("Testing if the add method is adding items.",
                () -> assertNotEquals(
                        ZERO_CART_SIZE,
                        STORE_CART_INST.getCartSize().itemOrderCount(),
                        "Your add method did not add any items into your cart.")
        );
    }

    @Test
    void testClearMethod() {
        //STORE_CART_INST is set to a default value of with no bulk-items:
        // Shopping Cart=
        // {ITEM4=[Name-ITEM4 : Qty-4],
        // ITEM3=[Name-ITEM3 : Qty-3],
        // ITEM2=[Name-ITEM2 : Qty-2],
        // ITEM1=[Name-ITEM1 : Qty-1],
        // ITEM5=[Name-ITEM5 : Qty-5]}

        STORE_CART_INST.clear();

        assertAll("Testing if the clear method successfully clears the shopping cart.",
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

    @Test
    void testCalculateMethod() {
        //STORE_CART_INST is set to a default value of with no bulk-items:
        // Shopping Cart=
        // {ITEM4=[Name-ITEM4 : Qty-4],
        // ITEM3=[Name-ITEM3 : Qty-3],
        // ITEM2=[Name-ITEM2 : Qty-2],
        // ITEM1=[Name-ITEM1 : Qty-1],
        // ITEM5=[Name-ITEM5 : Qty-5]}

        // adding bulk item into test shopping cart
        TEST_SHOPPING_CART.put(BULK_ITEM_NAME, TEST_BULK_ITEM_ORDER);

        //adding bulk item into tested shopping cart
        STORE_CART_INST.add(TEST_BULK_ITEM_ORDER);

        //Setting membership value to true
        STORE_CART_INST.setMembership(true);

        assertAll("Testing the calculate method with membership.",
                () -> assertEquals(
                        PRICE_WITH_MEMBERSHIP,
                        STORE_CART_INST.calculateTotal(),
                        "Expected " + PRICE_WITH_MEMBERSHIP + ", but instead got "
                        + STORE_CART_INST.calculateTotal())
        );

        //Setting membership value to false
        STORE_CART_INST.setMembership(false);

        assertAll("Testing the calculate method without membership.",
                () -> assertEquals(
                        PRICE_WITHOUT_MEMBERSHIP,
                        STORE_CART_INST.calculateTotal(),
                        "Expected " + PRICE_WITH_MEMBERSHIP + ", but instead got "
                        + STORE_CART_INST.calculateTotal())
        );

    }

    @Test
    void testCalculateWithNoItemsInCart() {

        // Clearing the tested cart
        STORE_CART_INST.clear();

        assertAll("Testing the calculate method with no items in cart.",
                () -> assertEquals(
                        new BigDecimal("0.00"),
                        STORE_CART_INST.calculateTotal(),
                        "Expecting 0.00, but instead got " + STORE_CART_INST.
                                calculateTotal())
        );
    }

    @Test
    void testToString() {
        assertAll("Testing to string method for format.",
                () -> assertEquals(
                        "Shopping Cart= " + TEST_SHOPPING_CART,
                        STORE_CART_INST.toString(),
                        "toString() should return: " + "Shopping Cart= "
                                + TEST_SHOPPING_CART)
        );
    }

    @Test
    void testGetCartSize() {

        assertAll("Testing the getCartSize method with a filled cart",
                () -> assertEquals(
                        myTotalNumOfTestItems,
                        STORE_CART_INST.getCartSize().itemCount(),
                        "Your getCartSize method did not record the correct number "
                                + "of individual items."),
                 () -> assertEquals(
                        MAX_NUMBER_OF_ITEMS,
                        STORE_CART_INST.getCartSize().itemOrderCount(),
                        "Your getCartSize method did not record the correct number "
                                + "of item orders.")
        );

        STORE_CART_INST.clear();

        assertAll("Testing the getCartSize method with an empty cart",
                () -> assertNotNull(
                        STORE_CART_INST.getCartSize(),
                        "Your getCartSize returned null, when it should have "
                                + "return 0.")
        );
    }




}
