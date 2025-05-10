package Model;

public class Departamentos {
    int departamento_id;
    String nombre_dept, descripcion, ext_telefonica;

    public Departamentos(){
    }

    public Departamentos(int departamento_id, String nombre_dept, String descripcion, String ext_telefonica){
        this.departamento_id = departamento_id;
        this.nombre_dept = nombre_dept;
        this.descripcion = descripcion;
        this.ext_telefonica = ext_telefonica;
    }

    public int getDepartamento_id() {
        return departamento_id;
    }

    public void setDepartamento_id(int departamento_id) {
        this.departamento_id = departamento_id;
    }

    public String getNombre_dept() {
        return nombre_dept;
    }

    public void setNombre_dept(String nombre_dept) {
        this.nombre_dept = nombre_dept;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getExt_telefonica() {
        return ext_telefonica;
    }

    public void setExt_telefonica(String ext_telefonica) {
        this.ext_telefonica = ext_telefonica;
    }

    @Override
    public String toString() {
        return "Departamentos [departamento_id=" + departamento_id + ", nombre_dept=" + nombre_dept + ", descripcion="
                + descripcion + ", ext_telefonica=" + ext_telefonica + "]";
    }

}
