/**
 *
 */
package es.map.sgtic.fw.utils;

import junit.framework.TestCase;

import org.junit.Assert;

/**
 *
 */
public class Number2TextTest extends TestCase {

    private static final Pair[] TEST_DATA_NUMBER = { 
        new Pair(new Integer(0), "cero"), 
        new Pair(new Integer(1), "uno"),
        new Pair(new Integer(10), "diez"),
        new Pair(new Integer(11), "once"),
        new Pair(new Integer(21), "veintiuno"),
        new Pair(new Integer(88), "ochenta y ocho"),
        new Pair(new Integer(1604), "mil seiscientos cuatro"),
        new Pair(new Integer(-43225), "menos cuarenta y tres mil doscientos veinticinco"),
        new Pair(new Integer(1000001), "un millón uno"),
        new Pair(new Long(333l), "trescientos treinta y tres"),
        new Pair(new Long(10000), "diez mil"),
        new Pair(new Float(-13.5f), "menos trece con cincuenta"),
        new Pair(new Float(741.0006f), "setecientos cuarenta y uno"),
        new Pair(new Double(-0.07d), "menos  cero con cero siete"),
        new Pair(new Double(17.9d), "diecisiete con noventa")
    };

    public void test_convertNumber_shouldReturnText() {
        for (int i = 0; i < TEST_DATA_NUMBER.length; i++) {
            final String numberText = Number2Text.convert(TEST_DATA_NUMBER[i].getNumber());
            Assert.assertNotNull(numberText);
            Assert.assertEquals(TEST_DATA_NUMBER[i].getText(), numberText);
        }
    }
}
