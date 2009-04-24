/**
 * 
 */
package es.map.sgtic.fw.utils;

/**
 * 
 * @author <a href="mailto:carlos.alonso1@map.es">carlos.alonso1</a>
 * @version 1.0 Fecha: 25/02/2009
 */
public class Number2TextTest {

	/**
	 * @param args
	 */
	public static void main(String args[]) {
		Number2Text number2text = new Number2Text(2);
		for (int i = 0; i < 100; i++) {
			System.out.println(i + " = " + number2text.convert(i));
		}

		number2text.setIgnoreCerosIzquierdaDecimal(true);
		System.out.println(number2text.convert(12.50d));
		System.out.println(number2text.convert(1.01d));
		System.out.println(number2text.convert(2.05d));
		System.out.println(number2text.convert(0.01d));
		System.out.println(number2text.convert(0.10d));

		number2text.setIgnoreCerosIzquierdaDecimal(false);
		System.out.println(number2text.convert(12.050d));

		number2text.setNumeroDecimales(4);
		number2text.setIgnoreCerosIzquierdaDecimal(false);
		System.out.println(number2text.convert(12.0050d));
	}

}
