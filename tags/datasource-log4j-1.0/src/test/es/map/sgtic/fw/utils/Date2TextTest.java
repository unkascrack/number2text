/**
 * 
 */
package es.map.sgtic.fw.utils;

import java.util.Date;
import java.util.GregorianCalendar;

/**
 * 
 * @author <a href="mailto:carlos.alonso1@map.es">carlos.alonso1</a>
 * @version 1.0 Fecha: 25/02/2009
 */
public class Date2TextTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Date2Text conversor = new Date2Text();
		Date fecha = new Date();
		GregorianCalendar fechaGreg = new GregorianCalendar();
		String fechaString = "10/01/2009";
		System.out.println(conversor.convert(fechaGreg));
		System.out.println(conversor.convert(fecha));
		System.out.println(conversor.convert(fechaString));
	}
}
