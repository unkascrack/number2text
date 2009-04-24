/**
 * 
 */
package es.map.sgtic.fw.utils;

/**
 * 
 * @author <a href="mailto:carlos.alonso1@map.es">carlos.alonso1</a>
 * @version 1.0 Fecha: 25/02/2009
 */
public class Money2TextTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Money2Text money2text = new Money2Text();
		System.out.println(money2text.convert(1.01d));
		System.out.println(money2text.convert(2.05d));
		System.out.println(money2text.convert(0.01d));
		System.out.println(money2text.convert(0.10d));
		System.out.println(money2text.convert(51));
		System.out.println(money2text.convert(21));
		System.out.println(money2text.convert(11));
		System.out.println(money2text.convert(101.41));
		System.out.println(money2text.convert(1000000));
		System.out.println(money2text.convert(10000000));
		System.out.println(money2text.convert(10000001));
	}

}
