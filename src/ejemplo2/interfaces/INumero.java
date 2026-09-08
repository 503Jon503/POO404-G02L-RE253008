package ejemplo2.interfaces;

public interface INumero {
    INumero Sumarle(INumero valor);

    //Decision propia: el diagrama UML del paso 15 de la guia incluye a "Multiplicarle"
    //como metodo sobreescrito de NumFraccionario, aunque el procedimiento escrito nunca
    //lo activa explicitamente (queda comentado en el codigo base). Se activa aqui para
    //respetar fielmente el diagrama de clases solicitado.
    INumero Multiplicarle(INumero valor);

    //Activado en el paso 33 del procedimiento
    INumero Dividir(INumero valor);

    //Activado en el paso 34 del procedimiento
    INumero ElevarA(int n);
}
