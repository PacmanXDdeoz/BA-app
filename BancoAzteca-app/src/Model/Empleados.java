package Model;

public class Empleados {
    int empleados_id, sucursal_id, departamento_id, puesto_id;
    double salario;
    String nombre_empleado, apellido_paterno, apellido_materno, 
    telefono_empleado, email_empleado, password_empleado;

    public Empleados(){
    }

    public Empleados(int empleados_id, int sucursal_id, int departamento_id,
    String nombre_empleado, String apellido_paterno, String apellido_materno,
    String telefono_empleado, String email_empleado, String password_empleado,
    int puesto_id, double salario){
        this.empleados_id = empleados_id;
        this.sucursal_id = sucursal_id;
        this.departamento_id = departamento_id;
        this.nombre_empleado = nombre_empleado;
        this.apellido_paterno = apellido_paterno;
        this.apellido_materno = apellido_materno;
        this.telefono_empleado = telefono_empleado;
        this.email_empleado = email_empleado;
        this.password_empleado = password_empleado;
        this.puesto_id = puesto_id;
        this.salario = salario;
    }

    public int getEmpleados_id() {
        return empleados_id;
    }

    public void setEmpleados_id(int empleados_id) {
        this.empleados_id = empleados_id;
    }

    public int getSucursal_id() {
        return sucursal_id;
    }

    public void setSucursal_id(int sucursal_id) {
        this.sucursal_id = sucursal_id;
    }

    public int getDepartamento_id() {
        return departamento_id;
    }

    public void setDepartamento_id(int departamento_id) {
        this.departamento_id = departamento_id;
    }

    public int getPuesto_id() {
        return puesto_id;
    }

    public void setPuesto_id(int puesto_id) {
        this.puesto_id = puesto_id;
    }

    public double getSalario() {
        return salario;
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }

    public String getNombre_empleado() {
        return nombre_empleado;
    }

    public void setNombre_empleado(String nombre_empleado) {
        this.nombre_empleado = nombre_empleado;
    }

    public String getApellido_paterno() {
        return apellido_paterno;
    }

    public void setApellido_paterno(String apellido_paterno) {
        this.apellido_paterno = apellido_paterno;
    }

    public String getApellido_materno() {
        return apellido_materno;
    }

    public void setApellido_materno(String apellido_materno) {
        this.apellido_materno = apellido_materno;
    }

    public String getTelefono_empleado() {
        return telefono_empleado;
    }

    public void setTelefono_empleado(String telefono_empleado) {
        this.telefono_empleado = telefono_empleado;
    }

    public String getEmail_empleado() {
        return email_empleado;
    }

    public void setEmail_empleado(String email_empleado) {
        this.email_empleado = email_empleado;
    }

    public String getPassword_empleado() {
        return password_empleado;
    }

    public void setPassword_empleado(String password_empleado) {
        this.password_empleado = password_empleado;
    }

    @Override
    public String toString() {
        return "Empleados [empleados_id=" + empleados_id + ", sucursal_id=" + sucursal_id + ", departamento_id="
                + departamento_id + ", puesto_id=" + puesto_id + ", salario=" + salario + ", nombre_empleado="
                + nombre_empleado + ", apellido_paterno=" + apellido_paterno + ", apellido_materno=" + apellido_materno
                + ", telefono_empleado=" + telefono_empleado + ", email_empleado=" + email_empleado
                + ", password_empleado=" + password_empleado + "]";
    }
}