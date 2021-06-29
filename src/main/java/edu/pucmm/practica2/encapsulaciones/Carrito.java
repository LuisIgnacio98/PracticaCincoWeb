package edu.pucmm.practica2.encapsulaciones;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

public class Carrito {
    private static final AtomicInteger count = new AtomicInteger(0);
    private Integer id;
    private ArrayList<Producto> productosCarrito;

    public Carrito(){
        this.id = count.incrementAndGet();
        this.productosCarrito = new ArrayList<>();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public ArrayList<Producto> getProductosCarrito() {
        return productosCarrito;
    }

    public void setProductosCarrito(ArrayList<Producto> productosCarrito) {
        this.productosCarrito = productosCarrito;
    }

    public void agregarCarrito(Producto producto){
        getProductosCarrito().add(producto);
    }
}
