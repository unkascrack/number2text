# number2text
Conversor de números (cifras) a su correspondiente en texto.

Ejemplo:
  * 1 - uno
  * 21 - veintiuno
  * 1.120,34 - mil ciento veinte con treinta y cuatro 

También permite la conversión de fechas y cantidades monetarias.

Modo de Uso:

    // Conversor de número a texto
    Number2Text conversor = new Number2Text();
    String dos = conversor.convert(2);
    String once = conversor.convert(11);
    String quinientos = conversor.convert(500);

    // Conversor de número a cantidad monetaria
    Money2Text conversor = new Money2Text();
    String dos = conversor.convert(2);
    String once = conversor.convert(11);
    String quinientos = conversor.convert(500);

    // Conversor de fecha a texto
    Date2Text conversor = new Date2Text();
    Date fecha = new Date();
    GregorianCalendar fechaGreg = new GregorianCalendar();
    String fechaString = "10/01/2009";
    String fechaLogica1 = conversor.convert(fechaGreg);
    String fechaLogica2 = conversor.convert(fecha);
    String fechaLogica3 = conversor.convert(fechaString);

