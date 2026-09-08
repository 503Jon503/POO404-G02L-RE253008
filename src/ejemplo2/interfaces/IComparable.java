package ejemplo2.interfaces;

public interface IComparable {
    boolean EsMayorQue(IComparable valor);

    //Decision propia: el diagrama UML del paso 15 de la guia incluye a "EsMenorQue"
    //como metodo sobreescrito de NumFraccionario, aunque el codigo base de esta interface
    //lo deja comentado. Se activa aqui para respetar fielmente el diagrama de clases.
    boolean EsMenorQue(IComparable valor);
}
