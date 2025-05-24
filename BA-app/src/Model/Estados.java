package Model;

public class Estados {
    int estado_id;
    String estado;

    public Estados(){
    }

    public Estados(int estado_id, String estado){
        this.estado_id = estado_id;
        this.estado = estado;
    }

    public int getEstado_id() {
        return estado_id;
    }

    public void setEstado_id(int estado_id) {
        this.estado_id = estado_id;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return "Estados [estado_id=" + estado_id + ", estado=" + estado + "]";
    }
}
