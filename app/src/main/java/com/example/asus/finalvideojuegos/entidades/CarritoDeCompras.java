package com.example.asus.finalvideojuegos.entidades;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;

public class CarritoDeCompras {

    private  String marca;
    private Double peso;
    private Double precio;
    private Integer cantidad;
    private  String dato;
    private Bitmap imagen;
    private String rutaImagen;

    public CarritoDeCompras(String marca, Double peso, Double precio, Integer cantidad, String dato, Bitmap imagen, String rutaImagen) {
        this.marca = marca;
        this.peso = peso;
        this.precio = precio;
        this.cantidad = cantidad;
        this.dato = dato;
        this.imagen = imagen;
        this.rutaImagen = rutaImagen;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public Double getPeso() {
        return peso;
    }

    public void setPeso(Double peso) {
        this.peso = peso;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public String getDato() {
        return dato;
    }

    public void setDato(String dato) {
        this.dato = dato;
        try {
            //codigficar apra recibir bien la iamgen
            byte [] byteCode= Base64.decode(dato,Base64.DEFAULT);
            //una ves decodificado pueda leerlo como tipo imagen
            // this.imagen=BitmapFactory.decodeByteArray(byteCode,0,byteCode.length);
            int alto=100;//alto en pixeles
            int ancho=150;//ancho en pixeles

            Bitmap foto =BitmapFactory.decodeByteArray(byteCode,0,byteCode.length);
            this.imagen=Bitmap.createScaledBitmap(foto,alto,ancho,true);

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public Bitmap getImagen() {
        return imagen;
    }

    public void setImagen(Bitmap imagen) {
        this.imagen = imagen;
    }

    public String getRutaImagen() {
        return rutaImagen;
    }

    public void setRutaImagen(String rutaImagen) {
        this.rutaImagen = rutaImagen;
    }
}
