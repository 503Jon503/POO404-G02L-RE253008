package validaciones;

/**
 * Clase dedicada exclusivamente a validar los datos ingresados por el
 * usuario en los Ejercicios Complementarios (asi lo exigen las Notas
 * finales de la guia: "Debe elaborar una clase dedicada exclusivamente
 * para validar datos brindados por el usuario").
 *
 * Criterios de validacion investigados/decididos por no estar definidos
 * en la guia (se documentan aqui y se resumen tambien en el README):
 *  - Edad de alumno: se acepta un rango de 15 a 100 anios. La guia no
 *    fija un rango; se tomo como referencia la edad minima habitual de
 *    ingreso a educacion media/superior en El Salvador (aprox. 15 anios)
 *    y un limite superior amplio y razonable (100 anios) para cubrir
 *    estudios de adultos/educacion continua.
 *  - Codigos (Cod_alumno, Cod_materia): deben ser enteros positivos,
 *    ya que son llave primaria y no tiene sentido un codigo negativo o cero.
 *  - Nombre/Apellido/Nombre de materia: no se aceptan vacios ni nulos.
 */
public class ValidadorDatos {

    private static final int EDAD_MINIMA = 15;
    private static final int EDAD_MAXIMA = 100;

    /** Valida que un texto no sea nulo ni este vacio (tras quitar espacios). */
    public static boolean esTextoValido(String texto) {
        return texto != null && !texto.trim().isEmpty();
    }

    /** Valida que un codigo (llave primaria) sea un entero positivo. */
    public static boolean esCodigoValido(int codigo) {
        return codigo > 0;
    }

    /** Valida que la edad este dentro del rango razonable para un alumno. */
    public static boolean esEdadValida(int edad) {
        return edad >= EDAD_MINIMA && edad <= EDAD_MAXIMA;
    }

    /**
     * Intenta convertir un String a entero de forma segura.
     * Retorna null si el texto no es un numero valido.
     */
    public static Integer parsearEntero(String texto) {
        try {
            return Integer.parseInt(texto.trim());
        } catch (Exception e) {
            return null;
        }
    }

    public static int getEdadMinima() {
        return EDAD_MINIMA;
    }

    public static int getEdadMaxima() {
        return EDAD_MAXIMA;
    }
}
