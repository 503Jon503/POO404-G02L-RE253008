package clasesabstractas;

/*
 * Investigacion sobre memorias microSD:
 *
 * Campos ADICIONALES (no existen en la clase abstracta UnidAlmac):
 *  - claseVelocidad (Speed Class): 2, 4, 6 o 10 -> indica el MB/s minimo
 *    garantizado de escritura secuencial.
 *  - tipoBus: "UHS-I", "UHS-II" o "UHS-III" -> determina el rendimiento
 *    (velocidad de transferencia) maximo que puede alcanzar la tarjeta.
 *
 * Equivalente de medidas para los campos YA existentes en UnidAlmac:
 *  - capac (capacidad): microSD/SDSC hasta 2 GB, microSDHC de 4 a 32 GB,
 *    microSDXC de 64 GB hasta 2048 GB (2 TB).
 *  - rendim (rendimiento): UHS-I 10-104 MB/s, UHS-II 105-312 MB/s,
 *    UHS-III 313-624 MB/s.
 */
public class MicroSD extends UnidAlmac {

    private int claseVelocidad;
    private String tipoBus;

    public MicroSD() {
        //valores predeterminados: bus UHS-I, Clase de velocidad 10
        this.tipoBus = "-";
        setTipoBus("UHS-I");
        setClaseVelocidad(10);
    }

    public MicroSD(int capac, int rendim, int claseVelocidad, String tipoBus) {
        this.tipoBus = "-";
        setTipoBus("UHS-I");
        setClaseVelocidad(10);

        //intenta actualizar campos con los valores recibidos
        setTipoBus(tipoBus);
        setCapac(capac);
        setRendim(rendim);
        setClaseVelocidad(claseVelocidad);
    }

    public String getTipoBus() {
        return tipoBus;
    }

    public void setTipoBus(String tipoBus) {
        String bus = tipoBus.trim().toUpperCase();
        //si el nuevo tipo de bus es distinto al actual
        if (!bus.equals(this.tipoBus)) {
            //inicializa el resto de campos segun el tipo de bus
            switch (bus) {
                case "UHS-I":
                    this.tipoBus = bus;
                    capac = 32;  //valor tipico microSDHC
                    rendim = 80; //dentro del rango de UHS-I
                    break;
                case "UHS-II":
                    this.tipoBus = bus;
                    capac = 64;
                    rendim = 200;
                    break;
                case "UHS-III":
                    this.tipoBus = bus;
                    capac = 128;
                    rendim = 400;
                    break;
            }
        }
    }

    public int getCapac() {
        return capac;
    }

    public void setCapac(int capac) {
        //capacidades estandar de memorias microSD (en GB)
        switch (capac) {
            case 2:
            case 4:
            case 8:
            case 16:
            case 32:
            case 64:
            case 128:
            case 256:
            case 512:
            case 1024:
            case 2048:
                super.capac = capac;
                break;
        }
    }

    public int getRendim() {
        return rendim;
    }

    public void setRendim(int rendim) {
        //verifica que el rendimiento este en rango segun el tipo de bus
        switch (getTipoBus()) {
            case "UHS-I":
                if (rendim >= 10 && rendim <= 104)
                    super.rendim = rendim;
                break;
            case "UHS-II":
                if (rendim >= 105 && rendim <= 312)
                    super.rendim = rendim;
                break;
            case "UHS-III":
                if (rendim >= 313 && rendim <= 624)
                    super.rendim = rendim;
                break;
        }
    }

    public int getClaseVelocidad() {
        return claseVelocidad;
    }

    public void setClaseVelocidad(int claseVelocidad) {
        //Speed Class estandar de memorias microSD
        switch (claseVelocidad) {
            case 2:
            case 4:
            case 6:
            case 10:
                this.claseVelocidad = claseVelocidad;
                break;
        }
    }

    public String mostrarparametros() {
        //ej: microSD: Bus UHS-I, Clase 10, Capacidad 32 GB, Rendimiento(80 MB/s)
        return String.format(
                "microSD: Bus %s, Clase %d, Capacidad %d GB, Rendimiento(%d MB/s)",
                tipoBus, claseVelocidad, getCapac(), getRendim());
    }
}
