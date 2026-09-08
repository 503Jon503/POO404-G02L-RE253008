package herencia;

public class Camion extends Terrestre {

    //Capacidad de carga del camion, en toneladas
    protected double tonelaje;

    public Camion(String nom, String marca, int totruedas, double tonelaje) {
        //inicializa campos con valores por defecto
        tipovehiculo = "Camion";
        this.totruedas = 6;  //por defecto, camion rigido de 2 ejes (6 ruedas)
        this.tonelaje = 10;  //por defecto, capacidad de carga de 10 toneladas
        //modifica campos segun valores de parametros (si son validos)
        setNombre(nom);
        setMarca(marca);
        setTotruedas(totruedas);
        setTonelaje(tonelaje);
    }

    //Segun los estandares de configuracion de ejes/ruedas para camiones,
    //la cantidad total de ruedas (incluyendo llantas dobles en los ejes traseros)
    //normalmente corresponde a: 6 (rigido pequeño, 2 ejes), 10, 12, 14, 16, 18
    //o 22 ruedas (camiones articulados/con remolque de varios ejes).
    //Valor por defecto establecido: 6 ruedas.
    public void setTotruedas(int totruedas) {
        switch (totruedas) {
            case 6:
            case 10:
            case 12:
            case 14:
            case 16:
            case 18:
            case 22:
                super.totruedas = totruedas;
                break;
        }
    }

    //Segun las normativas usuales de peso bruto vehicular, la capacidad de
    //carga aceptada para un camion se establece en un rango de 1 a 40
    //toneladas. Valor por defecto establecido: 10 toneladas (camion mediano).
    public double getTonelaje() {
        return tonelaje;
    }

    public void setTonelaje(double tonelaje) {
        if (tonelaje >= 1 && tonelaje <= 40)
            this.tonelaje = tonelaje;
    }

    //Sobreescribe (override) al metodo heredado desde la superclase Vehiculo
    public void verdatos() {
        //ej: Camion Volvo FH16, Modelo 2023, 10 ruedas, Capacidad de carga: 25.0 Toneladas
        String descrip = String.format(
                "%s %s, %s, %d ruedas, Capacidad de carga: %.1f Toneladas",
                getTipovehiculo(), getNombre(), getMarca(),
                getTotruedas(), getTonelaje()
        );
        System.out.println(descrip);
    }
}
