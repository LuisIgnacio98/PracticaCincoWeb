package edu.pucmm.practica2.encapsulaciones;

import java.util.concurrent.atomic.AtomicInteger;

public class Producto {
    private static final AtomicInteger count = new AtomicInteger(0);
    private Integer id;
    private String nombre;
    private Double precio;

    public Producto(String nombre, Double precio){
        this.id = count.incrementAndGet();
        this.nombre = nombre;
        this.precio = precio;
    }

    public Integer getId() {return id;}
    public void setId(Integer id) { this.id = id;}

    public String getNombre() {return nombre;}
    public void setNombre(String nombre) { this.nombre = nombre;}

    public Double getPrecio() {return precio;}
    public void setPrecio(Double precio) { this.precio = precio;}

    public void mezclar(Producto e){
        id = e.getId();
        nombre = e.getNombre();
        precio = e.getPrecio();
    }

}
