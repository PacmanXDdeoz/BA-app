package Model;

import java.time.LocalDate;

public class Inversiones {
    int inversion_id, cliente_id, producto_id;
    double monto, tasa_interes;
    String tipo_inversion;
    LocalDate fecha_inicio, fecha_vencimiento;

    public Inversiones(){
    }

    public Inversiones(int inversion_id, int cliente_id, int producto_id, double monto, double tasa_interes,
    LocalDate fecha_inicio, LocalDate fecha_vencimiento, String tipo_inversion){
        this.inversion_id = inversion_id;
        this.cliente_id = cliente_id;
        this.producto_id = producto_id;
        this.monto = monto;
        this.tasa_interes = tasa_interes;
        this.fecha_inicio = fecha_inicio;
        this.fecha_vencimiento = fecha_vencimiento;
        this.tipo_inversion = tipo_inversion;
    }

    public int getInversion_id() {
        return inversion_id;
    }

    public void setInversion_id(int inversion_id) {
        this.inversion_id = inversion_id;
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

    public String getTipo_inversion() {
        return tipo_inversion;
    }

    public void setTipo_inversion(String tipo_inversion) {
        this.tipo_inversion = tipo_inversion;
    }

    public LocalDate getFecha_inicio() {
        return fecha_inicio;
    }

    public void setFecha_inicio(LocalDate fecha_inicio) {
        this.fecha_inicio = fecha_inicio;
    }

    public LocalDate getFecha_vencimiento() {
        return fecha_vencimiento;
    }

    public void setFecha_vencimiento(LocalDate fecha_vencimiento) {
        this.fecha_vencimiento = fecha_vencimiento;
    }

    @Override
    public String toString() {
        return "Inversiones [inversion_id=" + inversion_id + ", cliente_id=" + cliente_id + ", producto_id="
                + producto_id + ", monto=" + monto + ", tasa_interes=" + tasa_interes + ", tipo_inversion="
                + tipo_inversion + ", fecha_inicio=" + fecha_inicio + ", fecha_vencimiento=" + fecha_vencimiento + "]";
    }
}