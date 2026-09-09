package modelos;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Clase del Procedimiento - Parte 4.
 * Contiene metodos estaticos para validar, mediante expresiones regulares,
 * el formato de un numero de telefono y de una fecha.
 */
public class Testing {

    public static boolean compareTelephone(String telefono) {
        String expresion = "(2|7)\\d{3}-\\d{4}";
        Pattern pat = Pattern.compile(expresion);
        Matcher mat = pat.matcher(telefono);
        if (mat.matches())
            return true;
        return false;
    }

    public static boolean compareDate(String date) {
        String expresion = "\\d{4}-\\d{2}-\\d{2}";
        Pattern pat = Pattern.compile(expresion);
        Matcher mat = pat.matcher(date);
        if (mat.matches())
            return true;
        return false;
    }
}
