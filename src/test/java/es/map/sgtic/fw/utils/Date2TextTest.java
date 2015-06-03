/**
 *
 */
package es.map.sgtic.fw.utils;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import junit.framework.TestCase;

import org.junit.Assert;

/**
 *
 */
public class Date2TextTest extends TestCase {

    private static final String DATE_TEXT = "uno de Enero de dos mil nueve";

    private Calendar calendar;

    public void setUp() {
        calendar = Calendar.getInstance();
        calendar.set(2009, 0, 1);
    }

    public void test_whenConvertDateNull_shouldThrowIllegalArgumentException() {
        try {
            Date2Text.convert((Date) null);
            Assert.fail("When convert Date null should throw IllegalArgumentException");
        } catch (final IllegalArgumentException e) {
        }
    }

    public void test_whenConvertDateIsNoNull_shouldReturnText() {
        testDateText(Date2Text.convert(calendar.getTime()));
    }

    public void test_whenConvertGregorianCalendarNull_shouldThrowIllegalArgumentException() {
        try {
            Date2Text.convert((GregorianCalendar) null);
            Assert.fail("When convert GregorianCalendar null should throw IllegalArgumentException");
        } catch (final IllegalArgumentException e) {
        }
    }

    public void test_whenConvertGregorianCalendarIsNoNull_shouldReturnText() {
        final GregorianCalendar gregorianCalendar = new GregorianCalendar();
        gregorianCalendar.setTime(calendar.getTime());
        testDateText(Date2Text.convert(gregorianCalendar));
    }

    public void test_whenConvertStringNull_shouldThrowIllegalArgumentException() throws ParseException {
        try {
            Date2Text.convert((String) null);
            Assert.fail("When convert String null should throw IllegalArgumentException");
        } catch (final IllegalArgumentException e) {
        }
    }

    public void test_whenConvertStringIsIncorrectDate_shouldThrowParseException() {
        try {
            Date2Text.convert("dd/mm/yyyy");
            Assert.fail("When convert date to String is incorrect should throw ParseException");
        } catch (final ParseException e) {
        }
    }

    public void test_whenConvertStringIsDate_shouldReturnText() throws ParseException {
        testDateText(Date2Text.convert("01/01/2009"));
    }

    private void testDateText(final String dateText) {
        Assert.assertNotNull(dateText);
        Assert.assertEquals(DATE_TEXT, dateText);
    }
}
