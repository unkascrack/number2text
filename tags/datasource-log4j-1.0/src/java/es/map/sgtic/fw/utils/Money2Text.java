/**
 * 
 */
package es.map.sgtic.fw.utils;

import org.apache.commons.lang.StringUtils;

/**
 * Convierte un cuantía económica dada a su representación léxica.
 * 
 * <p>
 * Se puede definir la palabra de la moneda, por defecto la moneda es euro, pero
 * se puede alterar encualquier momento, mediante el método
 * <code>setMonedaSingular() y setMonedaPlural()</code>, o bien en el propio
 * constructor de la clase.
 * </p>
 * 
 * <p>
 * <b>ATENCIÓN: la cifra máxima que se alcanza es
 * "miles de dieciochomillones de euros"</b>
 * </p>
 * 
 * <p>
 * Modo de usuo:<br/>
 * <code><b>
 * Money2Text conversor = new Money2Text();<br/>
 * String dos = conversor.convert(2);<br/>
 * String once = conversor.convert(11);<br/>
 * String quinientos = conversor.convert(500);<br/>
 * </b></code>
 * </p>
 * 
 * @author <a href="mailto:carlos.alonso1@map.es">carlos.alonso1</a>
 * @version 1.0 Fecha: 25/02/2008
 */
public class Money2Text {

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
	private static final String[] MILLARES = { "millón", "billón", "trillón",
			"cuatrillón", "quintillón", "sestillón", "septillón", "octillón",
			"novillón", "decillón", "oncillón", "docillón", "trecillón",
			"catorcillón", "quincillón", "dieciseismillón", "diecisietemillón",
			"dieciochomillón", "millones", "billones", "trillones",
			"cuatrillones", "quintillones", "sestillones", "septillones",
			"octillones", "novillones", "decillones", "oncillones",
			"docillones", "trecillones", "catorcillones", "quincillones",
			"dieciseismillones", "diecisietemillones", "dieciochomillones" };

	/**
	 * 
	 */
	private static final String MONEDA = "euro";
	private static final String MONEDAS = "euros";

	/**
	 * 
	 */
	static final int DECIMALES = 2;

	/**
	 * 
	 */
	private String monedaSingular;
	private String monedaPlural;

	/**
	 * 
	 */
	private final Number2Text number2Text;

	/**
	 * 
	 */
	public Money2Text() {
		this(MONEDA, MONEDAS);
	}

	/**
	 * @param monedaSingular
	 * @param monedaPlural
	 */
	public Money2Text(String monedaSingular, String monedaPlural) {
		this.monedaSingular = StringUtils
				.defaultIfEmpty(monedaSingular, MONEDA);
		this.monedaPlural = StringUtils.defaultIfEmpty(monedaPlural, MONEDAS);
		number2Text = new Number2Text(DECIMALES, true);
	}

	/**
	 * @return the monedaSingular
	 */
	public String getMonedaSingular() {
		return monedaSingular;
	}

	/**
	 * @param monedaSingular
	 *            the monedaSingular to set
	 */
	public void setMonedaSingular(String monedaSingular) {
		this.monedaSingular = monedaSingular;
	}

	/**
	 * @return the monedaPlural
	 */
	public String getMonedaPlural() {
		return monedaPlural;
	}

	/**
	 * @param monedaPlural
	 *            the monedaPlural to set
	 */
	public void setMonedaPlural(String monedaPlural) {
		this.monedaPlural = monedaPlural;
	}

	/**
	 * @param numero
	 * @return
	 */
	public String convert(int numero) {
		return convert((double) numero);
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
		return convert((double) numero);
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
		return convert(number2Text.convert(numero));
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
	private String convert(String numero) {
		String parteEntera = getParteEntera(numero);
		String parteDecimal = getParteDecimal(numero);

		String cantidad = parteEntera;
		if (StringUtils.isNotBlank(parteDecimal)) {
			cantidad += Constantes.CON + parteDecimal;
		}

		return cantidad;
	}

	/**
	 * @param numero
	 * @return
	 */
	private String getParteDecimal(String numero) {
		String parteDecimal = StringUtils
				.substringAfter(numero, Constantes.CON);
		if (StringUtils.isNotBlank(parteDecimal)) {
			parteDecimal = convertUnoUn(parteDecimal);
			parteDecimal = " " + parteDecimal
					+ (UN.equals(parteDecimal) ? CENTIMO : CENTIMOS);
		}

		return parteDecimal;
	}

	/**
	 * @param numero
	 * @return
	 */
	private String getParteEntera(String numero) {
		String parteEntera = StringUtils
				.substringBefore(numero, Constantes.CON).trim();
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
	 * @param numero
	 * @return
	 */
	private String convertUnoUn(String numero) {
		String num = StringUtils.trimToEmpty(numero);
		if (numero.endsWith(VEINTIUNO)) {
			num = StringUtils.removeEnd(num, VEINTIUNO) + VEINTIUN;
		} else if (numero.endsWith(UNO)) {
			num = StringUtils.removeEnd(num, UNO) + UN;
		}
		return num;
	}
}
