package clasesabstractas;

public class SSD extends UnidAlmac {

    private char interfaz; //'s': SATA o 'p': PCI express

    public SSD() {
        this.interfaz = '-';
        setInterfaz('s'); //define valores para interfaz SATA
    }

    public SSD(char interfaz, int capac, int rendim) {
        setInterfaz('s'); //define valores para interfaz SATA
        setInterfaz(interfaz);
        setCapac(capac);
        setRendim(rendim);
    }

    public char getInterfaz() {
        return interfaz;
    }

    public void setInterfaz(char interfaz) {
        //convierte parametro en minuscula
        interfaz = Character.toLowerCase(interfaz);
        //si el nuevo tipo de interfaz es distinto al actual
        if (interfaz != this.interfaz) {
            //inicializa el resto de campos segun el tipo de interfaz
            switch (interfaz) {
                case 's': //'s': SSD SATA
                    this.interfaz = interfaz;
                    capac = 256;  //(256, 512) GB
                    rendim = 190; //(190 - 600) MB/s
                    break;
                case 'p': //'p': PCIe (pci express)
                    this.interfaz = interfaz;
                    capac = 1;    //(1 - 4) TB
                    rendim = 3500; //(3500 - 7000) MB/s
                    break;
            }
        }
    }

    public int getCapac() {
        return capac;
    }

    public void setCapac(int capac) {
        //verifica que la capacidad este en rango segun el tipo de interfaz
        switch (getInterfaz()) {
            case 's': // SATA
                if (capac == 256 || capac == 512)
                    super.capac = capac;
                break;
            case 'p': // PCIe
                if (capac >= 1 && capac <= 4)
                    super.capac = capac;
                break;
        }
    }

    public int getRendim() {
        return super.rendim;
    }

    public void setRendim(int rendim) {
        //verifica que el rendimiento este en rango segun el tipo de interfaz
        switch (getInterfaz()) {
            case 's': // SATA
                if (rendim >= 190 && rendim <= 600)
                    super.rendim = rendim;
                break;
            case 'p': // PCIe
                if (rendim >= 3500 && rendim <= 7000)
                    super.rendim = rendim;
                break;
        }
    }

    public String mostrarparametros() {
        //ej: interface SATA, Capacidad 256 GB, Rendimiento(300 MB/s)
        //ej: interface PCIe, Capacidad 3 TB, Rendimiento(5000 MB/s)
        String resul = "";
        String nominterfaz;
        switch (interfaz) {
            case 's':
                nominterfaz = "SATA";
                resul = String.format(
                        "interface %s, Capacidad %d GB, Rendimiento(%d MB/s)",
                        nominterfaz, getCapac(), getRendim());
                break;
            case 'p':
                nominterfaz = "PCIe";
                resul = String.format(
                        "interface %s, Capacidad %d TB, Rendimiento(%d MB/s)",
                        nominterfaz, getCapac(), getRendim());
                break;
        }
        return resul;
    }
}
