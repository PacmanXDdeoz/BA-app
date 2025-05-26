package Model;

import java.time.LocalDate;

public class Prestamos {
    int prestamos_id, cliente_id, producto_id, estado_id, plazo_interes;
    double monto, tasa_interes, pago_mensual;
    LocalDate fecha_inicio, fecha_fin;

    public Prestamos(){
    }

    public Prestamos(int prestamos_id, int cliente_id, int producto_id, int estado_id, int plazo_interes,
    double monto, double tasa_interes, double pago_mensual, LocalDate fecha_fin, LocalDate fecha_inicio){
        this.prestamos_id = prestamos_id;
        this.cliente_id = cliente_id;
        this.estado_id = estado_id;
        this.plazo_interes = plazo_interes;
        this.monto = monto;
        this.tasa_interes = tasa_interes;
        this.pago_mensual = pago_mensual;
        this.fecha_fin = fecha_fin;
        this.fecha_inicio = fecha_inicio;
    }

    public int getPrestamos_id() {
        return prestamos_id;
    }

    public void setPrestamos_id(int prestamos_id) {
        this.prestamos_id = prestamos_id;
    }

    public int getCliente_id() {
        return cliente_id;
    }

    public void setCliente_id(int cliente_id) {
        this.cliente_id = cliente_id;
    }

    public int getProducto_id() {
        return producto_id;
    }

    public void setProducto_id(int producto_id) {
        this.producto_id = producto_id;
    }

    public int getEstado_id() {
        return estado_id;
    }

    public void setEstado_id(int estado_id) {
        this.estado_id = estado_id;
    }

    public int getPlazo_interes() {
        return plazo_interes;
    }

    public void setPlazo_interes(int plazo_interes) {
        this.plazo_interes = plazo_interes;
    }

    public double getMonto() {
        return monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }

    public double getTasa_interes() {
        return tasa_interes;
    }

    public void setTasa_interes(double tasa_interes) {
        this.tasa_interes = tasa_interes;
    }

    public double getPago_mensual() {
        return pago_mensual;
    }

    public void setPago_mensual(double pago_mensual) {
        this.pago_mensual = pago_mensual;
    }

    public LocalDate getFecha_inicio() {
        return fecha_inicio;
    }

    public void setFecha_inicio(LocalDate fecha_inicio) {
        this.fecha_inicio = fecha_inicio;
    }

    public LocalDate getFecha_fin() {
        return fecha_fin;
    }

    public void setFecha_fin(LocalDate fecha_fin) {
        this.fecha_fin = fecha_fin;
    }

    @Override
    public String toString() {
        return "Prestamos [prestamos_id=" + prestamos_id + ", cliente_id=" + cliente_id + ", producto_id=" + producto_id
                + ", estado_id=" + estado_id + ", plazo_interes=" + plazo_interes + ", monto=" + monto
                + ", tasa_interes=" + tasa_interes + ", pago_mensual=" + pago_mensual + ", fecha_inicio=" + fecha_inicio
                + ", fecha_fin=" + fecha_fin + "]";
    }
}