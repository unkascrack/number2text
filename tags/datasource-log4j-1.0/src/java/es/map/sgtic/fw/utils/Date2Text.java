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
 * 
 * 
 * @author <a href="mailto:rafael.delarosa@map.es">rafael.delarosa</a>
 * @version 1.0 Fecha: 11/02/2009
 */
public class Date2Text {

	/**
	 * 
	 */
	private static final String[] MESES = { "Enero", "Febrero", "Marzo",
			"Abril", "Mayo", "Junio", "Julio", "Agosto", "Septiembre",
			"Octubre", "Noviembre", "Diciembre" };

	/**
	 * 
	 */
	private static final String PATTERN_DATE = "dd/MM/yyyy";

	/**
	 * @param fecha
	 * @return
	 */
	public String convert(GregorianCalendar fecha) {
		Number2Text number2Text = new Number2Text();
		String dia = number2Text.convert(fecha.get(GregorianCalendar.DAY_OF_MONTH));
		String mes = getMes(fecha.get(GregorianCalendar.MONTH));
		String anno = number2Text.convert(fecha.get(GregorianCalendar.YEAR));
		return dia + " de " + mes + " de " + anno;
	}

	/**
	 * @param fecha
	 * @return
	 */
	public String convert(Date fecha) {
		GregorianCalendar gc = new GregorianCalendar();
		gc.setTime(fecha);
		return convert(gc);
	}

	/**
	 * @param fecha
	 * @return
	 */
	public String convert(String fecha) {
		String fechaResul = convert(getDate(fecha));
		return fechaResul;
	}

	/**
	 * @param fecha
	 * @return
	 */
	private Date getDate(String fecha) {
		Date fechaTemp = null;
		SimpleDateFormat date_format = new SimpleDateFormat(PATTERN_DATE);
		try {
			fechaTemp = date_format.parse(fecha);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return fechaTemp;
	}

	/**
	 * @param mes
	 * @return
	 */
	private String getMes(int mes) {
		return MESES[mes];
	}
}
