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
 * Se puede definir el número de decimales a representar, por defecto muestra
 * dos, pero se puede alterar encualquier momento, mediante el método
 * <code>setNumeroDecimales()</code> o bien el propio constructor de la clase.
 * </p>
 * 
 * <p>
 * <b>ATENCIÓN: la cifra máxima que se alcanza es
 * "miles de dieciochomillones"</b>
 * </p>
 * 
 * <p>
 * Modo de usuo:<br/>
 * <code><b>
 * Number2Text conversor = new Number2Text();<br/>
 * String dos = conversor.convert(2);<br/>
 * String once = conversor.convert(11);<br/>
 * String quinientos = conversor.convert(500);<br/>
 * </b></code>
 * </p>
 * 
 * @author <a href="mailto:carlos.alonso1@map.es">carlos.alonso1</a>
 * @version 1.0 Fecha: 24/04/2008
 */
public class Number2Text {

	/**
	 * 
	 */
	private static final int DEFAULT_NUMERO_DECIMALES = 2;

	/**
	 * Variable que guarda el número de decimales que se permite mostrar de un
	 * número
	 */
	private int numeroDecimales = DEFAULT_NUMERO_DECIMALES;

	/**
	 * Variable para indicars si se deben tener en cuenta los ceros a la
	 * izquierda de las cifras decimales
	 */
	private boolean ignoreCerosIzquierdaDecimal = false;

	/**
	 * 
	 */
	public Number2Text() {
	}

	/**
	 * @param numeroDecimales
	 */
	public Number2Text(int numeroDecimales) {
		this.numeroDecimales = numeroDecimales;
	}

	/**
	 * @param ignoreCerosIzquierdaDecimal
	 */
	public Number2Text(boolean ignoreCerosIzquierdaDecimal) {
		this.ignoreCerosIzquierdaDecimal = ignoreCerosIzquierdaDecimal;
	}

	/**
	 * @param numeroDecimales
	 * @param ignoreCerosIzquierdaDecimal
	 */
	public Number2Text(int numeroDecimales, boolean ignoreCerosIzquierdaDecimal) {
		this.numeroDecimales = numeroDecimales;
		this.ignoreCerosIzquierdaDecimal = ignoreCerosIzquierdaDecimal;
	}

	/**
	 * @return the numeroDecimales
	 */
	public int getNumeroDecimales() {
		return numeroDecimales;
	}

	/**
	 * @param numeroDecimales
	 *            the numeroDecimales to set
	 */
	public void setNumeroDecimales(int numeroDecimales) {
		this.numeroDecimales = numeroDecimales;
	}

	/**
	 * @return the ignoreCerosIzquierdaDecimal
	 */
	public boolean isIgnoreCerosIzquierdaDecimal() {
		return ignoreCerosIzquierdaDecimal;
	}

	/**
	 * @param ignoreCerosIzquierdaDecimal
	 *            the ignoreCerosIzquierdaDecimal to set
	 */
	public void setIgnoreCerosIzquierdaDecimal(
			boolean ignoreCerosIzquierdaDecimal) {
		this.ignoreCerosIzquierdaDecimal = ignoreCerosIzquierdaDecimal;
	}

	/**
	 * @param numero
	 * @return
	 */
	public String convert(int numero) {
		return convert(Integer.toString(numero));
	}

	/**
	 * @param numero
	 * @return
	 */
	public String convert(Integer numero) {
		return numero != null ? convert(numero.intValue()) : null;
	}

	/**
	 * @param numero
	 * @return
	 */
	public String convert(long numero) {
		return convert(Long.toString(numero));
	}

	/**
	 * @param numero
	 * @return
	 */
	public String convert(Long numero) {
		return numero != null ? convert(numero.longValue()) : null;
	}

	/**
	 * @param numero
	 * @return
	 */
	public String convert(float numero) {
		return convert((double) numero);
	}

	/**
	 * @param numero
	 * @return
	 */
	public String convert(Float numero) {
		return numero != null ? convert(numero.floatValue()) : null;
	}

	/**
	 * @param numero
	 * @return
	 */
	public String convert(double numero) {
		String valor = getNumeroDouble(numero);

		String cifraEntera = convert(StringUtils.substringBefore(valor, "."));
		String cifraDecimal = convertDecimal(StringUtils.substringAfter(valor,
				"."));

		return (cifraEntera + cifraDecimal).trim();
	}

	/**
	 * @param decimales
	 * @return
	 */
	private String convertDecimal(final String decimales) {
		String letrasDecimal = "";

		if (StringUtils.isNotEmpty(StringUtils.remove(decimales, "0"))) {
			letrasDecimal = Constantes.CON;

			String cifra = decimales;
			if (isIgnoreCerosIzquierdaDecimal()) {
				cifra = StringUtils.stripStart(cifra, "0");
			} else {
				while (cifra.startsWith("0")) {
					letrasDecimal = letrasDecimal + Constantes.CERO;
					cifra = cifra.substring(1);
				}
			}

			letrasDecimal = letrasDecimal + " " + convert(cifra);
		}
		return letrasDecimal;
	}

	/**
	 * @param numero
	 * @return
	 */
	public String convert(Double numero) {
		return numero != null ? convert(numero.doubleValue()) : null;
	}

	/**
	 * @param numero
	 * @return
	 */
	private String getNumeroDouble(double numero) {
		BigDecimal bigDecimal = new BigDecimal(numero);
		return bigDecimal.setScale(getNumeroDecimales(),
				BigDecimal.ROUND_HALF_DOWN).toString();
	}

	/**
	 * @param numero
	 * @return
	 */
	private String convert(String numero) {
		String prefijo = "";
		if (numero.startsWith("-")) {
			numero = numero.substring(1);
			prefijo = Constantes.MENOS;
		}

		String cifra = "";

		if (numero.length() <= 6) {
			cifra = convert(Integer.parseInt(numero), 0);
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
					cifra = convert(parte, millares) + " " + cifra;
				}
				millares += 2;
			}
		}

		return prefijo + cifra;
	}

	/**
	 * @param numero
	 * @param millares
	 * @return
	 */
	private String convert(int numero, int millares) {
		if (numero == 0) {
			return Constantes.CERO;
		}

		StringBuffer buffer = new StringBuffer(" ");
		do {
			int n = numero % 1000;
			numero /= 1000;
			if (n != 0) {
				String s1 = convertMenorMillar(n, millares);
				String s2 = convertMayorMillar(millares, buffer.toString(),
						n > 1 || numero > 0);
				if (Constantes.UNIDAD.equals(s1) && Constantes.MIL.equals(s2)) {
					s1 = "";
				}
				buffer.insert(0, s1 + s2);
			}
			millares++;
		} while (numero > 0);

		return buffer.toString().trim();
	}

	/**
	 * @param millares
	 * @param cifraActual
	 * @param masCifras
	 * @return
	 */
	private String convertMayorMillar(int millares, String cifraActual,
			boolean masCifras) {
		String cifra = "";
		if (millares > 1
				&& millares % 2 != 0
				&& !StringUtils.contains(cifraActual,
						Constantes.MILLARES_PLURAL[millares - 1])) {
			cifra = Constantes.MILLARES_PLURAL[millares - 1];
		}
		return (masCifras ? Constantes.MILLARES_PLURAL[millares]
				: Constantes.MILLARES_SINGULAR[millares])
				+ cifra;
	}

	/**
	 * @param numero
	 * @param millares
	 * @return
	 */
	private String convertMenorMillar(int numero, int millares) {
		if (numero == 100) {
			return Constantes.CIEN;
		}

		if (millares > 0 && numero % 100 == 21) {
			return Constantes.VEINTIUN;
		}

		String cifras = null;
		if (numero % 100 < 30) {
			cifras = Constantes.UNIDADES[numero % 100];
			numero /= 100;
		} else {
			cifras = Constantes.UNIDADES[numero % 10];
			numero /= 10;

			cifras = Constantes.DECIMAS[numero % 10]
					+ (cifras.length() == 0 ? cifras : Constantes.Y + cifras);
			numero /= 10;
		}

		if (millares > 0
				&& StringUtils.contains(cifras, Constantes.UNIDADES[1])) {
			cifras = StringUtils.replace(cifras, Constantes.UNIDADES[1],
					Constantes.UNIDAD);
		}

		return Constantes.CENTENAS[numero] + cifras;
	}
}
