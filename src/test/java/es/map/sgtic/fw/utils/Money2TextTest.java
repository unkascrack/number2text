/**
 *
 */
package es.map.sgtic.fw.utils;

import junit.framework.TestCase;

import org.junit.Assert;

/**
 *
 */
public class Money2TextTest extends TestCase {

    private static final Pair[] TEST_DATA_NUMBER = { 
        new Pair(new Integer(0), "cero euros"), 
        new Pair(new Integer(1), "un euro"),
        new Pair(new Integer(10), "diez euros"),
        new Pair(new Integer(11), "once euros"),
        new Pair(new Integer(21), "veintiún euros"),
        new Pair(new Integer(88), "ochenta y ocho euros"),
        new Pair(new Integer(1604), "mil seiscientos cuatro euros"),
        new Pair(new Integer(-43225), "menos cuarenta y tres mil doscientos veinticinco euros"),
        new Pair(new Integer(1000001), "un millón un euros"),
        new Pair(new Long(333l), "trescientos treinta y tres euros"),
        new Pair(new Long(10000), "diez mil euros"),
        new Pair(new Float(-13.5f), "menos trece euros con cincuenta céntimos"),
        new Pair(new Float(741.0006f), "setecientos cuarenta y un euros"),
        new Pair(new Double(-0.01d), "menos  cero euros con cero un céntimos"),
        new Pair(new Double(17.9d), "diecisiete euros con noventa céntimos")
    };

    public void test_convertNumber_shouldReturnText() {
        for (int i = 0; i < TEST_DATA_NUMBER.length; i++) {
            final String numberText = Money2Text.convert(TEST_DATA_NUMBER[i].getNumber());
            Assert.assertNotNull(numberText);
            Assert.assertEquals(TEST_DATA_NUMBER[i].getText(), numberText);
        }
    }

}
