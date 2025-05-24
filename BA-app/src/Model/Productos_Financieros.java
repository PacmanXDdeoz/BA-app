package Model;

public class Productos_Financieros {
    int producto_id;
    String nombre_prod, categoria, tasa_interes, comision, beneficios;

    public Productos_Financieros(){
    }

    public Productos_Financieros(int producto_id, String nombre_prod, String categoria,
    String tasa_interes, String comision, String beneficios){
        this.producto_id = producto_id;
        this.nombre_prod = nombre_prod;
        this.categoria = categoria;
        this.tasa_interes = tasa_interes;
        this.comision = comision;
        this.beneficios = beneficios;
    }

    public int getProducto_id() {
        return producto_id;
    }

    public void setProducto_id(int producto_id) {
        this.producto_id = producto_id;
    }

    public String getNombre_prod() {
        return nombre_prod;
    }

    public void setNombre_prod(String nombre_prod) {
        this.nombre_prod = nombre_prod;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getTasa_interes() {
        return tasa_interes;
    }

    public void setTasa_interes(String tasa_interes) {
        this.tasa_interes = tasa_interes;
    }

    public String getComision() {
        return comision;
    }

    public void setComision(String comision) {
        this.comision = comision;
    }

    public String getBeneficios() {
        return beneficios;
    }

    public void setBeneficios(String beneficios) {
        this.beneficios = beneficios;
    }

    @Override
    public String toString() {
        return "Productos_Financieros [producto_id=" + producto_id + ", nombre_prod=" + nombre_prod + ", categoria="
                + categoria + ", tasa_interes=" + tasa_interes + ", comision=" + comision + ", beneficios=" + beneficios
                + "]";
    }
}