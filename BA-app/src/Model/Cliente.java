package Model;

import java.time.LocalDate;

public class Cliente {
    int cliente_id, cuenta_id, prestamo_id, inversion_id;
    String nombre_cliente, apellido_paterno, apellido_materno, 
    telefono, direccion, email, ocupacion, rfc, curp, password;
    LocalDate fecha_nacimiento;
    

    public Cliente(){
    }
    
    public Cliente(int cliente_id, int cuenta_id, int prestamo_id, int inversion_id, String nombre_cliente, String apellido_paterno, String apellido_materno,
    LocalDate fecha_nacimiento, String telefono, String direccion, String email, String ocupacion, String rfc, 
    String curp, String password){
        this.cliente_id = cliente_id;
        this.cuenta_id = cuenta_id;
        this.prestamo_id = prestamo_id;
        this.inversion_id = inversion_id;
        this.nombre_cliente = nombre_cliente;
        this.apellido_paterno = apellido_paterno;
        this.apellido_materno = apellido_materno;
        this.fecha_nacimiento = fecha_nacimiento;
        this.telefono = telefono;
        this.direccion = direccion;
        this.email = email;
        this.ocupacion = ocupacion;
        this.rfc = rfc;
        this.curp = curp;
        this.password = password;
    }

    public int getCliente_id() {
        return cliente_id;
    }

    public void setCliente_id(int cliente_id) {
        this.cliente_id = cliente_id;
    }

    public int getCuenta_id() {
        return cuenta_id;
    }

    public void setCuenta_id(int cuenta_id) {
        this.cuenta_id = cuenta_id;
    }

    public int getPrestamo_id() {
        return prestamo_id;
    }

    public void setPrestamo_id(int prestamo_id) {
        this.prestamo_id = prestamo_id;
    }

    public int getInversion_id() {
        return inversion_id;
    }

    public void setInversion_id(int inversion_id) {
        this.inversion_id = inversion_id;
    }

    public String getNombre_cliente() {
        return nombre_cliente;
    }

    public void setNombre_cliente(String nombre_cliente) {
        this.nombre_cliente = nombre_cliente;
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

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getOcupacion() {
        return ocupacion;
    }

    public void setOcupacion(String ocupacion) {
        this.ocupacion = ocupacion;
    }

    public String getRfc() {
        return rfc;
    }

    public void setRfc(String rfc) {
        this.rfc = rfc;
    }

    public String getCurp() {
        return curp;
    }

    public void setCurp(String curp) {
        this.curp = curp;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public LocalDate getFecha_nacimiento() {
        return fecha_nacimiento;
    }

    public void setFecha_nacimiento(LocalDate fecha_nacimiento) {
        this.fecha_nacimiento = fecha_nacimiento;
    }

    @Override
    public String toString() {
        return "Cliente [cliente_id=" + cliente_id + ", cuenta_id=" + cuenta_id + ", prestamo_id=" + prestamo_id
                + ", inversion_id=" + inversion_id + ", nombre_cliente=" + nombre_cliente + ", apellido_paterno="
                + apellido_paterno + ", apellido_materno=" + apellido_materno + ", telefono=" + telefono
                + ", direccion=" + direccion + ", email=" + email + ", ocupacion=" + ocupacion + ", rfc=" + rfc
                + ", curp=" + curp + ", password=" + password + ", fecha_nacimiento=" + fecha_nacimiento + "]";
    }
}
