package mx.uv;

public class Usuario {
    private String nombre;
    private String correo;
    private String telefono;

    public Usuario (String nombre,String correo, String telefono){
        this.nombre = nombre;
        this.correo = correo;
        this.telefono = telefono;
    }

    public String getNombre(){
        return this.nombre;
    }

    public void setNombre(String nombre){
        this.nombre = nombre;
    }

    public String getCorreo(){
        return this.correo;
    }

    public void setCorreo(String correo){
        this.correo = correo;
    }

    public String getTelefono(){
        return this.telefono;
    }

    public void setTelefono(String telefono){
        this.telefono = telefono;
    }
}
