/**
 *
 */
package es.map.sgtic.fw.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Convierte una Fecha dada a su representación lógica
 *
 * <p>
 * Modo de usuo:<br/>
 * <code><b>
 * Date2Text conversor = new Date2Text();<br/>
 * Date fecha = new Date();<br/>
 * GregorianCalendar fechaGreg = new GregorianCalendar();<br/>
 * String fechaString = "10/01/2009";<br/>
 * String fechaLogica1 = conversor.convert(fechaGreg);<br/>
 * String fechaLogica2 = conversor.convert(fecha);<br/>
 * String fechaLogica3 = conversor.convert(fechaString);<br/>
 * </b></code>
 * </p>
 */
public final class Date2Text {

    /**
     *
     */
    private static final String[] MESES = { "Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto",
            "Septiembre", "Octubre", "Noviembre", "Diciembre" };

    /**
     *
     */
    private static final String PATTERN_DATE = "dd/MM/yyyy";

    private Date2Text() {
    }

    /**
     * @param fecha
     * @return
     */
    public static String convert(final GregorianCalendar date) {
        if (date == null) {
            throw new IllegalArgumentException("Argument should not be null");
        }
        final String dia = Number2Text.convert(date.get(GregorianCalendar.DAY_OF_MONTH));
        final String mes = MESES[date.get(GregorianCalendar.MONTH)];
        final String anio = Number2Text.convert(date.get(GregorianCalendar.YEAR));
        return dia + " de " + mes + " de " + anio;
    }

    /**
     * @param date
     * @return
     */
    public static String convert(final Date date) {
        if (date == null) {
            throw new IllegalArgumentException("Argument should not be null");
        }
        final GregorianCalendar gc = new GregorianCalendar();
        gc.setTime(date);
        return convert(gc);
    }

    /**
     * @param date
     * @return
     * @throws ParseException
     */
    public static String convert(final String date) throws ParseException {
        final String dateResul = convert(getDate(date, PATTERN_DATE));
        return dateResul;
    }

    /**
     * @param date
     * @param pattern
     * @return
     * @throws ParseException
     */
    public static String convert(final String date, final String pattern) throws ParseException {
        final String dateResul = convert(getDate(date, pattern));
        return dateResul;
    }

    /**
     * @param date
     * @return
     * @throws ParseException
     */
    private static Date getDate(final String date, final String pattern) throws ParseException {
        if (date == null) {
            throw new IllegalArgumentException("Argument should not be null");
        }
        final SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
        return dateFormat.parse(date);
    }

}
