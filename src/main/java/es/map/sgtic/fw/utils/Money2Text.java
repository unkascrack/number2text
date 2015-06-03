/**
 *
 */
package es.map.sgtic.fw.utils;

import org.apache.commons.lang.StringUtils;

/**
 * Convierte un cuantía económica dada a su representación léxica.
 *
 * <p>
 * Se puede definir la palabra de la moneda, por defecto la moneda es euro, pero se puede alterar encualquier momento,
 * mediante el método <code>setMonedaSingular() y setMonedaPlural()</code>, o bien en el propio constructor de la clase.
 * </p>
 *
 * <p>
 * <b>ATENCIÓN: la cifra máxima que se alcanza es "miles de dieciochomillones de euros"</b>
 * </p>
 *
 * <p>
 * Modo de usuo:<br/>
 * <code><b>
 * String dos = Money2Text.convert(2);<br/>
 * String once = Money2Text.convert(11);<br/>
 * String quinientos = Money2Text.convert(500);<br/>
 * </b></code>
 * </p>
 *
 */
public final class Money2Text {

    /**
     *
     */
    private Money2Text() {
    }

    /**
     *
     */
    private static final String UNO = Constantes.UNO.trim();
    private static final String UN = Constantes.UNIDAD.trim();
    private static final String VEINTIUNO = Constantes.VEINTIUNO.trim();
    private static final String VEINTIUN = Constantes.VEINTIUN.trim();
    private static final String CENTIMO = " céntimo";
    private static final String CENTIMOS = " céntimos";
    private static final String DE = " de";

    /**
     *
     */
    private static final String[] MILLARES = { "millón", "billón", "trillón", "cuatrillón", "quintillón", "sestillón",
        "septillón", "octillón", "novillón", "decillón", "oncillón", "docillón", "trecillón", "catorcillón",
        "quincillón", "dieciseismillón", "diecisietemillón", "dieciochomillón", "millones", "billones",
        "trillones", "cuatrillones", "quintillones", "sestillones", "septillones", "octillones", "novillones",
        "decillones", "oncillones", "docillones", "trecillones", "catorcillones", "quincillones",
        "dieciseismillones", "diecisietemillones", "dieciochomillones" };

    /**
     *
     */
    private static final String MONEDA = "euro";
    private static final String MONEDAS = "euros";

    /**
     * @param number
     * @return
     */
    public static String convert(final int number) {
        return convert((double) number);
    }

    /**
     * @param number
     * @return
     */
    public static String convert(final long number) {
        return convert((double) number);
    }

    /**
     * @param number
     * @return
     */
    public static String convert(final float number) {
        return convert((double) number);
    }

    /**
     * @param number
     * @return
     */
    public static String convert(final double number) {
        return convertString(Number2Text.convert(number));
    }

    /**
     * @param number
     * @return
     */
    public static String convert(final Number number) {
        return number != null ? convert(number.doubleValue()) : null;
    }

    /**
     * @param number
     * @return
     */
    private static String convertString(final String number) {
        final String parteEntera = getParteEntera(number);
        final String parteDecimal = getParteDecimal(number);

        String cantidad = parteEntera;
        if (StringUtils.isNotBlank(parteDecimal)) {
            cantidad += Constantes.CON + parteDecimal;
        }

        return cantidad;
    }

    /**
     * @param number
     * @return
     */
    private static String getParteDecimal(final String number) {
        String parteDecimal = StringUtils.substringAfter(number, Constantes.CON);
        if (StringUtils.isNotBlank(parteDecimal)) {
            parteDecimal = convertUnoUn(parteDecimal);
            parteDecimal = " " + parteDecimal + (UN.equals(parteDecimal) ? CENTIMO : CENTIMOS);
        }

        return parteDecimal;
    }

    /**
     * @param number
     * @return
     */
    private static String getParteEntera(final String number) {
        String parteEntera = StringUtils.substringBefore(number, Constantes.CON).trim();
        parteEntera = convertUnoUn(parteEntera);
        int i = 0;
        do {
            if (parteEntera.endsWith(MILLARES[i++])) {
                i = MILLARES.length;
                parteEntera += DE;
            }
        } while (i < MILLARES.length);

        return parteEntera + " " + (UN.equals(parteEntera) ? MONEDA : MONEDAS);
    }

    /**
     * @param number
     * @return
     */
    private static String convertUnoUn(final String number) {
        String num = StringUtils.trimToEmpty(number);
        if (number.endsWith(VEINTIUNO)) {
            num = StringUtils.removeEnd(num, VEINTIUNO) + VEINTIUN;
        } else if (number.endsWith(UNO)) {
            num = StringUtils.removeEnd(num, UNO) + UN;
        }
        return num;
    }
}
