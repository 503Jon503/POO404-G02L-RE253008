package ejemplo1;

public class PagoConBitcoin implements IPago {

    @Override
    public String ProcesarPago(float monto) {
        String resultado =
                String.format("Pago con Bitcoins del monto recibido es $ %.2f", monto);
        return resultado;
    }
}
