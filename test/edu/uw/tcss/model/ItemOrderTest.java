package edu.uw.tcss.model;

import org.junit.jupiter.api.Test;
import java.math.BigDecimal;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;


/**
 * @author Kassie Whitney
 * @version 1.16.25
 */
public class ItemOrderTest {



    /**
     * The Quantity of the item being ordered used in testing.
     */
    private static final int ORDER_AMMOUNT = 5;

    /**
     * The negative quantity of the item being ordered used in testing.
     */
    private static final int NEG_ORDER_AMMOUNT = -5;


    /**
     * The name of the item used in testing.
     */
    private static final String ITEM_NAME = "Item";


    /**
     * The price of the item used in testing.
     */
    private static final String ITEM_PRICE = ".50";

    /**
     *
     */
    private static final Item TEST_ITEM = new StoreItem(ITEM_NAME, new BigDecimal(ITEM_PRICE));


    /**
     * The name of the item being ordered used in testing.
     */
    private static final ItemOrder ITEM_ORDER = new StoreItemOrder(
            new StoreItem(ITEM_NAME, new BigDecimal(ITEM_PRICE)), ORDER_AMMOUNT);


    /**
     * Tests the constructor for any null values when first instantiated.
     */
    @Test
    public final void testEmptyconstructor() {
        assertAll("Item object as null object in constructor",
                () -> assertThrows(NullPointerException.class,
                        () -> new StoreItemOrder(null, ORDER_AMMOUNT),
                        "Constructor does not handle null as an argument to theItem "
                        + "properly."),
                () -> assertThrows(IllegalArgumentException.class,
                        () -> new StoreItemOrder(TEST_ITEM, NEG_ORDER_AMMOUNT),
                        "Expecting IllegalArgumentException, instead got "
                                + NEG_ORDER_AMMOUNT)
        );
    }

    @Test
    public final void testGetItem() {
        assertAll("Get Item after instantiation of constructor",
                () -> assertEquals(
                        TEST_ITEM.getName(),
                        ITEM_ORDER.getItem().getName(),
                        "Item name should be " + ITEM_NAME),
                () -> assertEquals(
                        TEST_ITEM.getPrice(),
                        ITEM_ORDER.getItem().getPrice(),
                        "Item price should be " + ITEM_PRICE)
        );

    }

    @Test
    public final void testGetQuantity() {
        assertAll("Get quantity after instantion of constructor",
                () -> assertEquals(
                        ORDER_AMMOUNT,
                        ITEM_ORDER.getQuantity(),
                        "Item quantity should be " + ORDER_AMMOUNT)
        );
    }


}
