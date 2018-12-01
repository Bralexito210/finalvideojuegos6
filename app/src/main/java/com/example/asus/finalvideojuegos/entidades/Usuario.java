package com.example.asus.finalvideojuegos.entidades;

public class Usuario {

    private String nick;
    private String password;
    private Integer celular;
    private String nombre;
    private String apellidos;
    private String direccion;
    private Integer credit_card;
    private String correo;
    private Integer cod_tipo;


    private String cargo;

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getCelular() {
        return celular;
    }

    public void setCelular(Integer celular) {
        this.celular = celular;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public Integer getCredit_card() {
        return credit_card;
    }

    public void setCredit_card(Integer credit_card) {
        this.credit_card = credit_card;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public Integer getCod_tipo() {
        return cod_tipo;
    }

    public void setCod_tipo(Integer cod_tipo) {
        this.cod_tipo = cod_tipo;
    }
}
