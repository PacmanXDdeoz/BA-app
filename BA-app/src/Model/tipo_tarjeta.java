package Model;

public class tipo_tarjeta {
    int tipot_id;
    String tipo_t;

    public tipo_tarjeta(){
    }

    public tipo_tarjeta(int tipot_id, String tipo_t){
        this.tipot_id = tipot_id;
        this.tipo_t = tipo_t;
    }

    public int getTipot_id() {
        return tipot_id;
    }

    public void setTipot_id(int tipot_id) {
        this.tipot_id = tipot_id;
    }

    public String getTipo_t() {
        return tipo_t;
    }

    public void setTipo_t(String tipo_t) {
        this.tipo_t = tipo_t;
    }

    @Override
    public String toString() {
        return "tipo_tarjeta [tipot_id=" + tipot_id + ", tipo_t=" + tipo_t + "]";
    }
}
