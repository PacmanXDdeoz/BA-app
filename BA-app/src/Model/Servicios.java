package Model;

public class Servicios {
    int servicios_id, sucursal_id;
    String nombre_serv;
    double costo;

    public Servicios(){
    }

    public Servicios(int servicios_id, int sucursal_id, String nombre_serv, double costo){
        this.servicios_id = servicios_id;
        this.sucursal_id = sucursal_id;
        this.nombre_serv = nombre_serv;
        this.costo = costo;
    }

    public int getServicios_id() {
        return servicios_id;
    }

    public void setServicios_id(int servicios_id) {
        this.servicios_id = servicios_id;
    }

    public int getSucursal_id() {
        return sucursal_id;
    }

    public void setSucursal_id(int sucursal_id) {
        this.sucursal_id = sucursal_id;
    }

    public String getNombre_serv() {
        return nombre_serv;
    }

    public void setNombre_serv(String nombre_serv) {
        this.nombre_serv = nombre_serv;
    }

    public double getCosto() {
        return costo;
    }

    public void setCosto(double costo) {
        this.costo = costo;
    }

    @Override
    public String toString() {
        return "Servicios [servicios_id=" + servicios_id + ", sucursal_id=" + sucursal_id + ", nombre_serv="
                + nombre_serv + ", costo=" + costo + "]";
    }
}
