package Model;

import java.time.LocalDate;

public class Tarjetas {
    int tarjeta_id, cliente_id, producto_id, estado_id, tipo_tarjeta;
    String num_tarjeta, cvv;
    LocalDate fecha_expiracion, fecha_emision;
    double saldo_actual;

    public Tarjetas(){
    }

    public Tarjetas(int tarjeta_id, int cliente_id, int producto_id, int estado_id, String num_tarjeta,
    LocalDate fecha_expiracion, int tipo_tarjeta, double saldo_actual, LocalDate fecha_emision, String cvv){
        this.tarjeta_id = tarjeta_id;
        this.cliente_id = cliente_id;
        this.producto_id = producto_id;
        this.estado_id = estado_id;
        this.num_tarjeta = num_tarjeta;
        this.fecha_expiracion = fecha_expiracion;
        this.fecha_emision = fecha_emision;
        this.tipo_tarjeta = tipo_tarjeta;
        this.saldo_actual = saldo_actual;
        this.cvv = cvv;
    }

    public int getTarjeta_id() {
        return tarjeta_id;
    }

    public void setTarjeta_id(int tarjeta_id) {
        this.tarjeta_id = tarjeta_id;
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

    public int getTipo_tarjeta() {
        return tipo_tarjeta;
    }

    public void setTipo_tarjeta(int tipo_tarjeta) {
        this.tipo_tarjeta = tipo_tarjeta;
    }

    public String getNum_tarjeta() {
        return num_tarjeta;
    }

    public void setNum_tarjeta(String num_tarjeta) {
        this.num_tarjeta = num_tarjeta;
    }

    public String getCvv() {
        return cvv;
    }

    public void setCvv(String cvv) {
        this.cvv = cvv;
    }

    public LocalDate getFecha_expiracion() {
        return fecha_expiracion;
    }

    public void setFecha_expiracion(LocalDate fecha_expiracion) {
        this.fecha_expiracion = fecha_expiracion;
    }

    public LocalDate getFecha_emision() {
        return fecha_emision;
    }

    public void setFecha_emision(LocalDate fecha_emision) {
        this.fecha_emision = fecha_emision;
    }

    public double getSaldo_actual() {
        return saldo_actual;
    }

    public void setSaldo_actual(double saldo_actual) {
        this.saldo_actual = saldo_actual;
    }

    @Override
    public String toString() {
        return "Tarjetas [tarjeta_id=" + tarjeta_id + ", cliente_id=" + cliente_id + ", producto_id=" + producto_id
                + ", estado_id=" + estado_id + ", tipo_tarjeta=" + tipo_tarjeta + ", num_tarjeta=" + num_tarjeta
                + ", cvv=" + cvv + ", fecha_expiracion=" + fecha_expiracion + ", fecha_emision=" + fecha_emision
                + ", saldo_actual=" + saldo_actual + "]";
    }
}