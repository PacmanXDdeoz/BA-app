package Model;

public class Transacciones {
    int transaccion_id, cuenta_id, estado_id;
    double monto;
    String cuenta_destino, fecha_hora;
    long referencia;

    public Transacciones(){
    }

    public Transacciones(int transaccion_id, int cuenta_id, int estado_id, double monto, long referencia,
    String cuenta_destino, String fecha_hora){
        this.transaccion_id = transaccion_id;
        this.cuenta_id = cuenta_id;
        this.estado_id = estado_id;
        this.monto = monto;
        this.referencia = referencia;
        this.cuenta_destino = cuenta_destino;
        this.fecha_hora = fecha_hora;
    }

    public int getTransaccion_id() {
        return transaccion_id;
    }

    public void setTransaccion_id(int transaccion_id) {
        this.transaccion_id = transaccion_id;
    }

    public int getCuenta_id() {
        return cuenta_id;
    }

    public void setCuenta_id(int cuenta_id) {
        this.cuenta_id = cuenta_id;
    }

    public int getEstado_id() {
        return estado_id;
    }

    public void setEstado_id(int estado_id) {
        this.estado_id = estado_id;
    }

    public double getMonto() {
        return monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }

    public String getCuenta_destino() {
        return cuenta_destino;
    }

    public void setCuenta_destino(String cuenta_destino) {
        this.cuenta_destino = cuenta_destino;
    }

    public String getFecha_hora() {
        return fecha_hora;
    }

    public void setFecha_hora(String fecha_hora) {
        this.fecha_hora = fecha_hora;
    }

    public long getReferencia() {
        return referencia;
    }

    public void setReferencia(long referencia) {
        this.referencia = referencia;
    }

    @Override
    public String toString() {
        return "Transacciones [transaccion_id=" + transaccion_id + ", cuenta_id=" + cuenta_id + ", estado_id="
                + estado_id + ", monto=" + monto + ", cuenta_destino=" + cuenta_destino + ", fecha_hora=" + fecha_hora
                + ", referencia=" + referencia + "]";
    }
}
