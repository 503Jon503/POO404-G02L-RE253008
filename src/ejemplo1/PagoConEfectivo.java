package ejemplo1;

public class PagoConEfectivo implements IPago {

    @Override
    public String ProcesarPago(float monto) {
        String resultado =
                String.format("Pago en Efectivo del monto recibido es $ %.2f", monto);
        return resultado;
    }
}
