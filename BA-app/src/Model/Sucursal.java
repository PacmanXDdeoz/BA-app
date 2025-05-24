package Model;

public class Sucursal {
    int sucursal_id;
    String sucursal_nombre, codigo_postal, telefono, horario_apertura, horario_cierre;

    public Sucursal(){
    }

    public Sucursal(int sucursal_id, String sucursal_nombre, String codigo_postal, String telefono, 
    String horario_apertura, String horario_cierre){
        this.sucursal_id = sucursal_id;
        this.sucursal_nombre = sucursal_nombre;
        this.codigo_postal = codigo_postal;
        this.telefono = telefono;
        this.horario_apertura = horario_apertura;
        this.horario_cierre = horario_cierre;
    }

    public int getSucursal_id() {
        return sucursal_id;
    }

    public void setSucursal_id(int sucursal_id) {
        this.sucursal_id = sucursal_id;
    }

    public String getSucursal_nombre() {
        return sucursal_nombre;
    }

    public void setSucursal_nombre(String sucursal_nombre) {
        this.sucursal_nombre = sucursal_nombre;
    }

    public String getCodigo_postal() {
        return codigo_postal;
    }

    public void setCodigo_postal(String codigo_postal) {
        this.codigo_postal = codigo_postal;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getHorario_apertura() {
        return horario_apertura;
    }

    public void setHorario_apertura(String horario_apertura) {
        this.horario_apertura = horario_apertura;
    }

    public String getHorario_cierre() {
        return horario_cierre;
    }

    public void setHorario_cierre(String horario_cierre) {
        this.horario_cierre = horario_cierre;
    }

    @Override
    public String toString() {
        return "Sucursal [sucursal_id=" + sucursal_id + ", sucursal_nombre=" + sucursal_nombre + ", codigo_postal="
                + codigo_postal + ", telefono=" + telefono + ", horario_apertura=" + horario_apertura
                + ", horario_cierre=" + horario_cierre + "]";
    }
    
}
