package ejemplo1;

public class PagoConTarjeta implements IPago {

    @Override
    public String ProcesarPago(float monto) {
        String resultado =
                String.format("Pago con Tarjeta del monto recibido es $ %.2f", monto);
        return resultado;
    }
}
