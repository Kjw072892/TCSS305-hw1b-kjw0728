package edu.uw.tcss.model;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;
import org.junit.jupiter.api.Test;




/**
 * @author Kassie Whitney
 * @version 1.20.25
 */
class ItemOrderTest {



    /**
     * The Quantity of the item being ordered used in testing.
     */
    private static final int ORDER_QUANT = 5;

    /**
     * The negative quantity of the item being ordered used in testing.
     */
    private static final int NEG_ORDER_QUANT = -5;


    /**
     * The name of the item used in testing.
     */
    private static final String ITEM_NAME = "Item";


    /**
     * The price of the item used in testing.
     */
    private static final String STORE_ITEM_PRICE = ".50";

    /**
     *The item in storeitem object.
     */
    private static final Item TEST_STORE_ITEM =
            new StoreItem(ITEM_NAME, new BigDecimal(STORE_ITEM_PRICE));

    /**
     * The name of the item being ordered used in testing.
     */
    private static final ItemOrder TEST_ITEM_ORDER = new StoreItemOrder(
            new StoreItem(ITEM_NAME, new BigDecimal(STORE_ITEM_PRICE)), ORDER_QUANT);


    /**
     * Tests the constructor for null arugments in Item param
     */
    @Test
    void testConstructorNullItem() {
        assertAll("Item object as null object in constructor.",
                () -> assertThrows(NullPointerException.class,
                        () -> new StoreItemOrder(null, ORDER_QUANT),
                        "Constructor does not handle null as an argument to theItem "
                        + "properly.")
        );
    }

    /**
     * Tests negative integers in constructors quantity param
     */
    @Test
    void testConstructorNegativeQuantity() {
        assertAll("Negative quantity in constructor.",
                () -> assertThrows(IllegalArgumentException.class,
                        () -> new StoreItemOrder(TEST_STORE_ITEM, NEG_ORDER_QUANT),
                        "Expecting IllegalArgumentException, instead got "
                                + NEG_ORDER_QUANT)
        );
    }

    @Test
    void testArgumentConstructor() {
        assertAll("Argument constructor test.",
                () -> assertEquals(
                        TEST_STORE_ITEM.getName(),
                        TEST_ITEM_ORDER.getItem().getName(),
                        "Item name should be: " + ITEM_NAME),

                () -> assertEquals(
                        TEST_STORE_ITEM.getPrice(),
                        TEST_ITEM_ORDER.getItem().getPrice(),
                        "Price should be: " + STORE_ITEM_PRICE),

                () -> assertNotEquals(
                        TEST_STORE_ITEM.getClass(),
                       null,
                        TEST_STORE_ITEM.getClass() + " expected, but got null")

        );
    }



    /**
     * Tests the getItem method for object of type StoreItem.
     */
    @Test
    void testGetItem() {
        assertAll("Get Item after instantiation of constructor",
                () -> assertEquals(
                        TEST_STORE_ITEM,
                        TEST_ITEM_ORDER.getItem(),
                        "Expecting 'Item, $0.50', but got "
                                + TEST_ITEM_ORDER.getItem()),

                () -> assertNotEquals(
                        TEST_STORE_ITEM,
                        null,
                        "Item Order should not have null"
                )

        );
    }

    /**
     * Tests the GetQuantity method for an integer refrencing the correct quantity of the
     * order.
     */
    @Test
    void testGetQuantity() {
        assertAll("Get quantity after instantion of constructor",
                () -> assertEquals(
                        ORDER_QUANT,
                        TEST_ITEM_ORDER.getQuantity(),
                        "Item quantity should be " + ORDER_QUANT)
        );
    }


}
