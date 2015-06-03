/**
 *
 */
package es.map.sgtic.fw.utils;

import java.math.BigDecimal;

import org.apache.commons.lang.StringUtils;

/**
 * Convierte un número dado a su representación léxica.
 *
 * <p>
 * Se puede definir el número de decimales a representar, por defecto muestra dos, pero se puede alterar encualquier
 * momento, mediante el método <code>setNumeroDecimales()</code> o bien el propio constructor de la clase.
 * </p>
 *
 * <p>
 * <b>ATENCIÓN: la cifra máxima que se alcanza es "miles de dieciochomillones"</b>
 * </p>
 *
 * <p>
 * Modo de usuo:<br/>
 * <code><b>
 * String dos = Number2Text.convert(2);<br/>
 * String once = Number2Text.convert(11);<br/>
 * String quinientos = Number2Text.convert(500);<br/>
 * </b></code>
 * </p>
 */
public final class Number2Text {

    /**
     *
     */
    private static final int DEFAULT_NUMERO_DECIMALES = 2;

    /**
     *
     */
    private static final boolean IGNORE_CEROS_IZQUIERDA_DECIMAL = false;

    private Number2Text() {
    }

    /**
     * @param number
     * @return
     */
    public static String convert(final int number) {
        return convertNumber(Integer.toString(number));
    }

    /**
     * @param number
     * @return
     */
    public static String convert(final long number) {
        return convertNumber(Long.toString(number));
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
        final String valor = convertString(number);

        final String cifraEntera = convertNumber(StringUtils.substringBefore(valor, "."));
        final String cifraDecimal = convertDecimal(StringUtils.substringAfter(valor, "."));

        return (cifraEntera + cifraDecimal).trim();
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
    private static String convertString(final double number) {
        final BigDecimal bigDecimal = new BigDecimal(number);
        return bigDecimal.setScale(DEFAULT_NUMERO_DECIMALES, BigDecimal.ROUND_HALF_DOWN).toString();
    }

    /**
     * @param decimales
     * @return
     */
    private static String convertDecimal(final String decimales) {
        String letrasDecimal = "";

        if (StringUtils.isNotEmpty(StringUtils.remove(decimales, "0"))) {
            letrasDecimal = Constantes.CON;

            String cifra = decimales;
            if (IGNORE_CEROS_IZQUIERDA_DECIMAL) {
                cifra = StringUtils.stripStart(cifra, "0");
            } else {
                while (cifra.startsWith("0")) {
                    letrasDecimal = letrasDecimal + Constantes.CERO;
                    cifra = cifra.substring(1);
                }
            }

            letrasDecimal = letrasDecimal + " " + convertNumber(cifra);
        }
        return letrasDecimal;
    }

    /**
     * @param numero
     * @return
     */
    private static String convertNumber(String numero) {
        String prefijo = "";
        if (numero.startsWith("-")) {
            numero = numero.substring(1);
            prefijo = Constantes.MENOS;
        }

        String cifra = "";

        if (numero.length() <= 6) {
            cifra = convertMillar(Integer.parseInt(numero), 0);
        } else {
            boolean fin = false;
            int millares = 0;
            for (int i = numero.length() - 6; !fin; i = i - 6) {
                int parte = 0;
                if (i <= 0) {
                    parte = Integer.parseInt(numero.substring(0, i + 6));
                    fin = true;
                } else {
                    parte = Integer.parseInt(numero.substring(i, i + 6));
                }

                if (parte != 0) {
                    cifra = convertMillar(parte, millares) + " " + cifra;
                }
                millares += 2;
            }
        }

        return prefijo + cifra;
    }

    /**
     * @param number
     * @param millares
     * @return
     */
    private static String convertMillar(int number, int millares) {
        if (number == 0) {
            return Constantes.CERO;
        }

        final StringBuffer buffer = new StringBuffer(" ");
        do {
            final int n = number % 1000;
            number /= 1000;
            if (n != 0) {
                String s1 = convertMenorMillar(n, millares);
                final String s2 = convertMayorMillar(millares, buffer.toString(), n > 1 || number > 0);
                if (Constantes.UNIDAD.equals(s1) && Constantes.MIL.equals(s2)) {
                    s1 = "";
                }
                buffer.insert(0, s1 + s2);
            }
            millares++;
        } while (number > 0);

        return buffer.toString().trim();
    }

    /**
     * @param millares
     * @param cifraActual
     * @param masCifras
     * @return
     */
    private static String convertMayorMillar(final int millares, final String cifraActual, final boolean masCifras) {
        String cifra = "";
        if (millares > 1 && millares % 2 != 0
                && !StringUtils.contains(cifraActual, Constantes.MILLARES_PLURAL[millares - 1])) {
            cifra = Constantes.MILLARES_PLURAL[millares - 1];
        }
        return (masCifras ? Constantes.MILLARES_PLURAL[millares] : Constantes.MILLARES_SINGULAR[millares]) + cifra;
    }

    /**
     * @param number
     * @param millares
     * @return
     */
    private static String convertMenorMillar(int number, final int millares) {
        if (number == 100) {
            return Constantes.CIEN;
        }

        if (millares > 0 && number % 100 == 21) {
            return Constantes.VEINTIUN;
        }

        String cifras = null;
        if (number % 100 < 30) {
            cifras = Constantes.UNIDADES[number % 100];
            number /= 100;
        } else {
            cifras = Constantes.UNIDADES[number % 10];
            number /= 10;

            cifras = Constantes.DECIMAS[number % 10] + (cifras.length() == 0 ? cifras : Constantes.Y + cifras);
            number /= 10;
        }

        if (millares > 0 && StringUtils.contains(cifras, Constantes.UNIDADES[1])) {
            cifras = StringUtils.replace(cifras, Constantes.UNIDADES[1], Constantes.UNIDAD);
        }

        return Constantes.CENTENAS[number] + cifras;
    }
}
