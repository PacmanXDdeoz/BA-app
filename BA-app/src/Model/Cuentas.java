package Model;

import java.time.LocalDate;

public class Cuentas {
    int cuenta_id, cliente_id, sucursal_id, producto_id, estado_id;
    String numero_cuenta;
    LocalDate fecha_apertura;
    double saldo;

    public Cuentas(){
    }

    public Cuentas(int cuenta_id, int cliente_id, int sucursal_id, int estado_id, String numero_cuenta,
    double saldo, LocalDate fecha_apertura, int producto_id){
        this.cliente_id = cliente_id;
        this.cuenta_id = cuenta_id;
        this.sucursal_id = sucursal_id;
        this.producto_id = producto_id;
        this.estado_id = estado_id;
        this.numero_cuenta = numero_cuenta;
        this.saldo = saldo;
        this.fecha_apertura = fecha_apertura;
    }

    public int getCuenta_id() {
        return cuenta_id;
    }

    public void setCuenta_id(int cuenta_id) {
        this.cuenta_id = cuenta_id;
    }

    public int getCliente_id() {
        return cliente_id;
    }

    public void setCliente_id(int cliente_id) {
        this.cliente_id = cliente_id;
    }

    public int getSucursal_id() {
        return sucursal_id;
    }

    public void setSucursal_id(int sucursal_id) {
        this.sucursal_id = sucursal_id;
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

    public String getNumero_cuenta() {
        return numero_cuenta;
    }

    public void setNumero_cuenta(String numero_cuenta) {
        this.numero_cuenta = numero_cuenta;
    }

    public LocalDate getFecha_apertura() {
        return fecha_apertura;
    }

    public void setFecha_apertura(LocalDate fecha_apertura) {
        this.fecha_apertura = fecha_apertura;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    @Override
    public String toString() {
        return "Cuentas [cuenta_id=" + cuenta_id + ", cliente_id=" + cliente_id + ", sucursal_id=" + sucursal_id
                + ", producto_id=" + producto_id + ", estado_id=" + estado_id + ", numero_cuenta=" + numero_cuenta
                + ", fecha_apertura=" + fecha_apertura + ", saldo=" + saldo + "]";
    }
}