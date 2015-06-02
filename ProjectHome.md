Conversor de números (cifras) a su correspondiente en texto.

Ejemplo:
  * 1 - uno
  * 21 - veintiuno
  * 1.120,34 - mil ciento veinte con treinta y cuatro

También permite la conversión de fechas y cantidades monetarias.

Modo de Uso:

---

**#Conversor de número a texto**<br>
<code>Number2Text conversor = new Number2Text();</code><br>
<code>String dos = conversor.convert(2);</code><br>
<code>String once = conversor.convert(11);</code><br>
<code>String quinientos = conversor.convert(500);</code><br>

<b>#Conversor de número a cantidad monetaria</b><br>
<code>Money2Text conversor = new Money2Text();</code><br>
<code>String dos = conversor.convert(2);</code><br>
<code>String once = conversor.convert(11);</code><br>
<code>String quinientos = conversor.convert(500);</code><br>

<b>#Conversor de fecha a texto</b><br>
<code>Date2Text conversor = new Date2Text();</code><br>
<code>Date fecha = new Date();</code><br>
<code>GregorianCalendar fechaGreg = new GregorianCalendar();</code><br>
<code>String fechaString = "10/01/2009";</code><br>
<code>String fechaLogica1 = conversor.convert(fechaGreg);</code><br>
<code>String fechaLogica2 = conversor.convert(fecha);</code><br>
<code>String fechaLogica3 = conversor.convert(fechaString);</code><br>
<hr />