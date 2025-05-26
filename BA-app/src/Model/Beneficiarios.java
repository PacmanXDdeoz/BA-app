package Model;

public class Beneficiarios {
    int beneficiario_id, cliente_id; 
    String nombre_beneficiario, apellido_paterno, apellido_materno, parentesco, telefono, email;

    public Beneficiarios(){
    }

    public Beneficiarios(int beneficiario_id, int cliente_id, String nombre_beneficiario, String apellido_paterno,
    String apellido_materno, String parentesco, String telefono, String email){
        this.beneficiario_id = beneficiario_id;
        this.cliente_id = cliente_id;
        this.nombre_beneficiario = nombre_beneficiario;
        this.apellido_paterno = apellido_paterno;
        this.apellido_materno = apellido_materno;
        this.parentesco = parentesco;
        this.telefono = telefono;
        this.email = email;
    }

    public int getBeneficiario_id() {
        return beneficiario_id;
    }

    public void setBeneficiario_id(int beneficiario_id) {
        this.beneficiario_id = beneficiario_id;
    }

    public int getCliente_id() {
        return cliente_id;
    }

    public void setCliente_id(int cliente_id) {
        this.cliente_id = cliente_id;
    }

    public String getNombre_beneficiario() {
        return nombre_beneficiario;
    }

    public void setNombre_beneficiario(String nombre_beneficiario) {
        this.nombre_beneficiario = nombre_beneficiario;
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

    public String getParentesco() {
        return parentesco;
    }

    public void setParentesco(String parentesco) {
        this.parentesco = parentesco;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Beneficiarios [beneficiario_id=" + beneficiario_id + ", cliente_id=" + cliente_id
                + ", nombre_beneficiario=" + nombre_beneficiario + ", apellido_paterno=" + apellido_paterno
                + ", apellido_materno=" + apellido_materno + ", parentesco=" + parentesco + ", telefono=" + telefono
                + ", email=" + email + "]";
    }
}